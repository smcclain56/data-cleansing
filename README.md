# Data Cleansing
This project was an assignment given in my undergraduate class, "Principles of Database Systems". We were given a problem in which a local college uses a software called "ShmeepleSoft" to manage its data. ShmeepleSoft has been collecting years worth of data on students and course enrollment in a single spreadsheet, and over time, the size of this file has gotten unmanageable. The project asked us to define a database schema in SQLite and to write a script that will take the data from its raw format and insert it into the database. Along the way, the script also resolves all of the anomalies in the text file (inconsistencies, redundancies, etc.).

# Features
The current data file suffers from numerous problems that our script must resolve. These problems include:
- Redundancies: A students information is duplicate for each course they are enrolled in
- Multi-valued attributes: One of the attributes has multiple values. A student can have 0-2 majors, and each of them is seperated by a colon.
- Incomplete data: When a student is not enrolled in any course, the course-enrollment fields are delimited by commas, but there's no data. There are also courses that exist on the books that no one is enrolled in. There are also departments without student or course info. This happens when an existing department has not yet added any courses to the schedule.
- Inconsistencies: The redundancies led to bad or stale data over time. The school administrators alert us that sometimes "ENGL" is mislabeled as simply "ENG" and some people tend to abbreviate "Junior" as "JR" and "Senior" as "SR" under a student's class rank. The abbreviated versions need to be corrected to the full spelling. 

# Credits
My professor, David Chiu, supplied the raw ShmeepleSoft data for this assignment.
