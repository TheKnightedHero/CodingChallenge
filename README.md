# CodingChallenge

Purpose of the Application:

This project was built as a simple application, therefore it requires a java IDE to run it.
Simply load the project file and run the program through the IDE. 
I used Eclipse as my IDE.
You could probably run it through the command prompt, I just never have before.

Make sure the .jar files are added in properties when loaded into the IDE. It should be already applied, but just in case. It will not function correctly without them.

The application should: 

Take the given .csv file renamed codingChallengeRecords.csv
parse the data
sort the data between Valid entries and Invalid entires.
Write the bad data into a new .csv file
Write the good data into a new .csv file, posing as the "database"
write a log.txt file listing how many records were recorded, how many were Valid, and how many were Invalid

-------------------
# Design Choice

A lot of my design choices arose because I haven't developed in Java in about 2-3 years and have used this weekend as a crash-course refresher. I also have never made a database before with Java, instead I decided to output the good data into a new .csv file.
I did, however, try to keep to good coding practices as best as I could, such as having multiple functions carrying out different tasks.

I approached the project under the assumption that blank fields were acceptable, and just acting as indicators of missing data entires. This allowed me to focus on making sure the data given met the requirement that data outside the 10 column limit was considered bad data.

I split the project into four objectives, which are shown through the four functions I wrote: 
1) Read from the .csv and store bad data
2) Write bad data to a new.csv file
3) Write good data to a new .csv file
4) Write a log.txt file

I grabbed a few custom Java libraries to handle the escaped characters thanks to the image data that was included in the .csv file. This allowed me to have a very simple and strightfoward setup, without writing a custom parser that could potentially miss entries and read the data as is.

Once the functions were written, I simply created an object in main, and called the methods of the object.