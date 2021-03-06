from datetime import timedelta, date
import requests
from bs4 import BeautifulSoup
import boto3


def date_list(startdate, enddate):
    months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October',
              'November', 'December']
    delta = enddate - startdate  # as timedelta
    days = []
    for i in range(delta.days + 1):
        day = startdate + timedelta(days=i)
        days.append((months[day.month-1], day.day, day.year))
    return days


def extract_snowpack_data(url='https://wcc.sc.egov.usda.gov/reports/UpdateReport.html?report=Washington'):
    r = requests.get(url)
    html = r.content
    soup = BeautifulSoup(html, 'html.parser')
    snowpackTable = soup.find('table', {'id': 'update_report_data'})
    metrics = ['Elev (ft) ', 'Snow Current (in)', 'Snow Median (in)', 'Snow Pct of Median', 'Water Current ',
               'Water Average (in)', 'Water Pct of Average']

    region_dictionary = {}

    for i, row in enumerate(snowpackTable.select('tr')):
        columns = row.find_all('td')
        if len(columns) == 1:
            col_text = columns[0].getText()
            region_name = ''.join(filter(str.isalpha, col_text))
            current_region = region_name
            region_dictionary[region_name] = {}

        if len(columns) == 3:
            for j, column in enumerate(columns):
                column_text = column.getText()
                if j == 1:
                    try:
                        pct_med = int(''.join(filter(str.isdigit, column_text)))
                    except:
                        pct_med = 0
                    region_dictionary[current_region]['Basin Index'] = {}
                    region_dictionary[current_region]['Basin Index']['pctmedian'] = pct_med
                elif j == 2:
                    pct_avg = int(''.join(filter(str.isdigit, column_text)))
                    region_dictionary[current_region]['Basin Index']['pctavg'] = pct_avg

        if len(columns) == 8:

            for j, column in enumerate(columns):
                column_text = column.getText()
                if j == 0:
                    current_loc = column_text.split('\xa0\xa0')[1]
                    if "." in current_loc:
                        current_loc = current_loc.replace(".", "")
                    # This is to handle a corner case
                    region_dictionary[current_region][current_loc] = {}
                else:
                    try:
                        region_dictionary[current_region][current_loc][metrics[j - 1]] = int(
                            ''.join(filter(str.isdigit, column_text)))
                    except:
                        region_dictionary[current_region][current_loc][metrics[j - 1]] = ''
    return region_dictionary


def gen_url(month, day, year):
    return  'https://wcc.sc.egov.usda.gov/reports/UpdateReport.html?textReport=Washington&textRptKey=12&textFormat=SNOTEL+Snow%2FPrecipitation+Update+Report&StateList=12&RegionList=Select+a+Region+or+Basin&SpecialList=Select+a+Special+Report&MonthList={}&DayList={}&YearList={}&FormatList=N3&OutputFormatList=HTML&textMonth={}&textDay={}&CompYearList=select+a+year'.format(month,day,year, month, day)


def write_snotel_measurement(location, date_, measurment_dict, dynamoDB):

    months = {'January': 1, 'February': 2, 'March': 3, 'April': 4, 'May': 5, 'June': 6,
        'July': 7, 'August': 8, 'September': 9, 'October': 10, 'November': 11, 'December': 12}
    month = months[date_[0]]
    day = date_[1]
    year = date_[2]
    if day < 10:
        day = '0'+str(day)
    if month < 10:
        month = '0'+str(month)
    datestring = "{}{}{}".format(year,month,day)

    print(datestring)
    dynamoDB.put_item(
        TableName="Snotel",
        Item={
            "LocationID": {"S": location },
            "SnotelDate": {"S": datestring},
            "SnowCurrent":{"N": str(measurment_dict["snow_current"]) },
            "SnowMedian": {"N": str(measurment_dict["snow_median"])},
            "SnowPctMedian": {"N": str(measurment_dict["snow_pct_median"])},
            "WaterCurrent": {"N": str(measurment_dict["water_current"])},
            "WaterCurrentAverage": {"N": str(measurment_dict["snow_median"])},
            "WaterPctAverage": {"N": str(measurment_dict["snow_pct_median"])}
        }
    )
#
#
def write_location_item(location, elevation, dynamoDB):
    try:
        dynamoDB.put_item(
            TableName="BasinLocations",
            Item={
                "LocationID": {"S": location },
                "Elevation": {"N": str(elevation)},
            }
        )
    except Exception as e:
        print(e)


def validate_data(data):
    if data == '':
        data = 0
    return data
#
#
def insert_data(basins_dict, date_, dynamodb):

    for region in basins_dict.keys():
        basins_dict[region].pop('Basin Index')
        locations = basins_dict[region].keys()

        for location in locations:
            location_dict = basins_dict[region][location]
            measurment_dict = {
            "snow_current" : validate_data(location_dict['Snow Current (in)']),
            "snow_median" : validate_data(location_dict['Snow Median (in)']),
            "snow_pct_median" : validate_data(location_dict['Snow Pct of Median']),
            "water_current" : validate_data(location_dict['Water Current ']),
            "water_current_average" :  validate_data(location_dict['Water Average (in)']),
            "water_pct_average" : validate_data(location_dict['Water Pct of Average'])
            }

            write_snotel_measurement(location, date_, measurment_dict, dynamodb)


def populate_location_table(dynamodb):
    basins_dict = extract_snowpack_data()
    for region in basins_dict.keys():
        locations = basins_dict[region].keys()
        for location in locations:
            try:
                location_dict = basins_dict[region][location]
                elevation = location_dict['Elev (ft) ']
                write_location_item(location, elevation, dynamodb)
            except Exception as e:
                print(e)

def scrape_snowpack_data(startdate, enddate, dynamodb ):
    populate_location_table(dynamodb)
    dates = date_list(startdate, enddate)

    for date_ in dates:
        url = gen_url(date_[0], date_[1],date_[2])
        insert_data(extract_snowpack_data(url),date_, dynamodb)


dynamo = boto3.client('dynamodb',region_name='us-west-2')
start_date = date(2014, 1, 1)
end_date = date(2017, 1, 1)

scrape_snowpack_data(start_date, end_date, dynamo)

