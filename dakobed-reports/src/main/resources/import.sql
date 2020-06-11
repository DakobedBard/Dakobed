--
INSERT INTO report  (id, distance, elevation_gain, name, region, report, post_date, imageURL) VALUES ('42b4bbac-3df1-4b08-960e-d9260ffd3464', 23.21, 4021, 'Entiat Traverse', 'Entiat','Spent 3 days traversing the Entiat Wilderness','2013-06-01', 'https://dakobed.s3-us-west-1.amazonaws.com/pugh.jpg' );
INSERT INTO report  (id, distance, elevation_gain, name, region, report, post_date, imageURL) VALUES ('178a8bf9-4e66-4fff-bc92-d4359e388bfc', 23.21, 4021, 'Dakobed Traverse', 'Glacier Peak','Did an epic traverse of the Dakobeds.  Hit Honeycomb and Butterfly glaciers.  Rounded Clark Mountain & Ten Peak','2013-06-01', 'https://dakobed.s3-us-west-1.amazonaws.com/challenger.jpeg' );
INSERT INTO report  (id, distance, elevation_gain, name, region, report, post_date, imageURL) VALUES ('fe112ee5-d043-4511-807c-cd09e7559fe7', 23.21, 4021, 'Golden Horn', 'North Cadscades','Trekked to the northcascades and spent a night at cutthroat pass and spent another at Williams Lake ','2013-06-01', 'https://dakobed.s3-us-west-1.amazonaws.com/stuart.jpg' );
--
INSERT INTO comment  (id,content, username, report_id) VALUES ('7b562a76-669d-4189-b943-461e312ef2a1', 'What a sick Trip!', 'JosephK','178a8bf9-4e66-4fff-bc92-d4359e388bfc');
INSERT INTO comment  (id,content, username, report_id) VALUES ('7f298fd8-fe78-44f2-a408-0be828edf229', 'Man up Mathias you weak!', 'HaroldD','178a8bf9-4e66-4fff-bc92-d4359e388bfc');

INSERT INTO comment  (id,content, username, report_id) VALUES ('12e00461-5c46-49b4-b92a-addc3a257e27', 'Still not adequete for my liking!', 'JosephK','fe112ee5-d043-4511-807c-cd09e7559fe7');
INSERT INTO comment  (id,content, username, report_id) VALUES ('ff4c605a-818b-4d80-80bc-48b0119a85aa', 'This is a little better..!', 'HaroldD','fe112ee5-d043-4511-807c-cd09e7559fe7');

