from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import datetime
import psycopg2


def get_postgres_connection(db, schema = None):
    conn = psycopg2.connect("host=localhost dbname={} user=postgres password=postgres".format(db)) if not schema else \
        psycopg2.connect(host="localhost", port="5432", user="postgres",  password="postgres", database=db, options="-c search_path={}".format(schema))
    return conn


create_trip_report_table = (""" CREATE TABLE IF NOT EXISTS trip_reports(
                            trip_id serial PRIMARY KEY ,
                            trip_name VARCHAR NOT NULL,
                            trip_report VARCHAR NOT NULL,
                            elevationGain float NOT NULL,
                            mileage INT NOT NUll,
                            trip_date DATE NOT NULL,
                            locations text[] NOT NULL);
                        """)

trip_location =(""" CREATE TABLE IF NOT EXISTS geographic_location(
                            elevation INT NOT NULL,
                            lat INT NOT NULL,
                            lng INT NOT NULL,
                            location_name VARCHAR NOT NULL,
                            terrain_features text[],
                            water_availability bool);
                        """)

trip_report_table_insert = ("""INSERT INTO trip_reports (trip_name, trip_report, elevationGain, mileage, locations, trip_date) VALUES (%s, %s, %s, %s, %s, %s);""")


def get_page_soup(page_url):
    client = uReq(page_url)
    page_html = client.read()
    client.close()
    page_soup = soup(page_html, "html.parser")
    return page_soup


def parse_trip_report(trip_report_url):
    client = uReq(trip_report_url)
    trip_report_html = client.read()
    client.close()
    tripsoup = soup(trip_report_html, "html.parser")
    content = tripsoup.find("article", {"id": "content"})
    trip_text = content.find('p').text
    region = content.find_all('span')[1].text
    trip_title = content.find('h1').text
    date = content.find('span', {"class": "elapsed-time"})
    date_string = str(date).split('datetime=')[1].split('"')[1]

    docuemntHead = content.find("h1", {"class": "documentFirstHeading"})
    location = docuemntHead.text
    location_link = str(docuemntHead.find('a')).split('"')[1]
    trip_report = {'title': trip_title, 'region': region, 'trip_report': trip_text, 'date': date_string,
                   'location': location, 'location_link': location_link}
    return trip_report


def insert_trip_report(conn, trip_report):
    cur = conn.cursor()
    cur.execute(trip_report_table_insert, (
    trip_report['title'], trip_report['trip_report'], 2300, 45.3, ['location1', 'location2'], datetime.datetime.now()))
    conn.commit()


def scrape():
    conn = get_postgres_connection('snowpackDB', 'snowpack')

    for page_number in range(0,100):
        report_list_url = 'https://www.wta.org/@@search_tripreport_listing?b_size=100&amp;b_start:int=%d&amp;_=1584045459199"' % int(
            100 * page_number)
        try:
            report_list_soup = get_page_soup(report_list_url)
            reports = report_list_soup.find_all("a", {"class": "listitem-title"})
            for report in reports:
                report_url = str(report).split('href=')[1].split('"')[1]
                trip_report = parse_trip_report(report_url)
                insert_trip_report(conn, trip_report)
                print("Inserted ")
                conn.commit()
        except Exception as e:
            print("We have a problem with the url at")
            print(e)
    conn.close()


conn = get_postgres_connection('snowpackDB')
cur = conn.cursor()
query = 'CREATE SCHEMA IF NOT EXISTS snowpack;'
params = ('schema_name', 'user_name')
cur.execute(query, params)
conn.commit()

conn = get_postgres_connection('snowpackDB','snowpack')
cur = conn.cursor()
create_table_queries = [create_trip_report_table, trip_location]

for query in create_table_queries:
    cur.execute(query)
conn.commit()

scrape()

