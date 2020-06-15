# Data Cleansing
This project was an assignment given in my undergraduate class, "Principles of Database Systems". We were given a problem in which a local college uses a software called "ShmeepleSoft" to manage its data. ShmeepleSoft has been collecting years worth of data on students and course enrollment in a single spreadsheet, and over time, the size of this file has gotten unmanageable. The project asked us to define a database schema in SQLite and to write a script that will take the data from its raw format and insert it into the database. Along the way, the script also resolves all of the anomalies in the text file (inconsistencies, redundancies, etc.).

# Features
The current data file suffers from numerous problems that our script must resolve. These problems include:
- Redundancies: A students information is duplicate for each course they are enrolled in
- Multi-valued attributes: One of the attributes has multiple values. A student can have 0-2 majors, and each of them is seperated by a colon.
- Incomplete data: When a student is not enrolled in any course, the course-enrollment fields are delimited by commas, but there's no data. There are also courses that exist on the books that no one is enrolled in. There are also departments without student or course info. This happens when an existing department has not yet added any courses to the schedule.
- Inconsistencies: The redundancies led to bad or stale data over time. The school administrators alert us that sometimes "ENGL" is mislabeled as simply "ENG" and some people tend to abbreviate "Junior" as "JR" and "Senior" as "SR" under a student's class rank. The abbreviated versions need to be corrected to the full spelling. 

# Example
<pre>
========================= Welcome to Data Cleanser ==========================

Please enter path for text file (type 'exit' to quit program): 
shmeeplesoft.raw.txt

Generating SQL statements...
INSERT INTO Student VALUES (1001, 'Lia', 'Junior', '3.6');
INSERT INTO Student VALUES (1282, 'Kelly', 'Freshman', '2.5');
INSERT INTO Dept VALUES ('BUS', 'School of Business', 'McIntyre Hall');
INSERT INTO Course VALUES (122, 'BUS', 'Economics', 'WY 30', 'MW', '13:30');
INSERT INTO Enroll VALUES (122, 'BUS', 1282);
INSERT INTO Student VALUES (1025, 'John', 'Senior', '3.6');
INSERT INTO Dept VALUES ('PHYS', 'Department of Physics', 'Harned Hall');
INSERT INTO Course VALUES (101, 'PHYS', 'How Things Move', 'HH 191', 'MWF', '10:00');
INSERT INTO Enroll VALUES (101, 'PHYS', 1025);
INSERT INTO Dept VALUES ('HIST', 'Department of History', 'Wyatt Hall');
INSERT INTO Student VALUES (1247, 'Alexis', 'Senior', '3.9');
INSERT INTO Dept VALUES ('MATH', 'Department of Mathematics', 'Tower of Babel');
INSERT INTO Course VALUES (320, 'MATH', 'Discrete Mathematics', 'TH 307', 'F', '11:00');
INSERT INTO Enroll VALUES (320, 'MATH', 1247);
INSERT INTO Student VALUES (1101, 'Haley', 'Senior', '4.0');
INSERT INTO Dept VALUES ('MATH', 'Department of Mathematics', 'Tower of Babel');
INSERT INTO Course VALUES (120, 'MATH', 'Algebra', 'MH 10', 'MW', '12:00');
INSERT INTO Enroll VALUES (120, 'MATH', 1101);
INSERT INTO Student VALUES (1247, 'Alexis', 'Senior', '3.9');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (351, 'CSCI', 'Database Systems', 'TH 19', 'MW', '12:00');
INSERT INTO Enroll VALUES (351, 'CSCI', 1247);
INSERT INTO Student VALUES (1304, 'Jordan', 'Senior', '2.9');
INSERT INTO Dept VALUES ('PHYS', 'Department of Physics', 'Harned Hall');
INSERT INTO Course VALUES (101, 'PHYS', 'How Things Move', 'HH 191', 'MWF', '10:00');
INSERT INTO Enroll VALUES (101, 'PHYS', 1304);
INSERT INTO Student VALUES (1101, 'Haley', 'Senior', '4.0');
INSERT INTO Dept VALUES ('MATH', 'Department of Mathematics', 'Tower of Babel');
INSERT INTO Course VALUES (230, 'MATH', 'Linear Algebra', 'HH 308', 'TR', '15:00');
INSERT INTO Enroll VALUES (230, 'MATH', 1101);
INSERT INTO Student VALUES (1709, 'Cassandra', 'Junior', '2.8');
INSERT INTO Dept VALUES ('SOAN', 'Department of Anthropology', 'Wyatt Hall');
INSERT INTO Course VALUES (102, 'SOAN', 'Sociology 2', 'WY 205', 'MTWRF', '09:00');
INSERT INTO Enroll VALUES (102, 'SOAN', 1709);
INSERT INTO Student VALUES (1101, 'Haley', 'Senior', '4.0');
INSERT INTO Dept VALUES ('PHYS', 'Department of Physics', 'Harned Hall');
INSERT INTO Course VALUES (401, 'PHYS', 'Quantum Mechanics', 'HH 372', 'TR', '09:00');
INSERT INTO Enroll VALUES (401, 'PHYS', 1101);
INSERT INTO Student VALUES (1225, 'Sarah', 'Freshman', '2.9');
INSERT INTO Dept VALUES ('ENGL', 'Department of English', 'Wyatt Hall');
INSERT INTO Course VALUES (101, 'ENGL', 'How to Read', 'WY 100', 'MWF', '13:00');
INSERT INTO Enroll VALUES (101, 'ENGL', 1225);
INSERT INTO Student VALUES (1247, 'Alexis', 'Senior', '3.9');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (453, 'CSCI', 'Capstone in Computer Science', 'TH 398', 'MWF', '16:00');
INSERT INTO Enroll VALUES (453, 'CSCI', 1247);
INSERT INTO Student VALUES (1911, 'David', 'Senior', '3.2');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (453, 'CSCI', 'Capstone in Computer Science', 'TH 398', 'MWF', '16:00');
INSERT INTO Enroll VALUES (453, 'CSCI', 1911);
INSERT INTO Student VALUES (1025, 'John', 'Senior', '3.6');
INSERT INTO Dept VALUES ('ENGL', 'Department of English', 'Wyatt Hall');
INSERT INTO Course VALUES (520, 'ENGL', 'Shakespeare Was Da Bomb', 'HH 20', 'TR', '13:00');
INSERT INTO Enroll VALUES (520, 'ENGL', 1025);
INSERT INTO Student VALUES (1282, 'Kelly', 'Freshman', '2.5');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (351, 'CSCI', 'Database Systems', 'TH 19', 'MW', '12:00');
INSERT INTO Enroll VALUES (351, 'CSCI', 1282);
INSERT INTO Student VALUES (1247, 'Alexis', 'Senior', '3.9');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (520, 'CSCI', 'High Performance Computing', 'WY 307', 'TR', '15:00');
INSERT INTO Enroll VALUES (520, 'CSCI', 1247);
INSERT INTO Course VALUES (330, 'MATH', 'Trigonometry', 'WEY 113', 'TR', '08:30');
INSERT INTO Student VALUES (1510, 'Jordan', 'Freshman', '3.0');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (351, 'CSCI', 'Database Systems', 'TH 19', 'MW', '12:00');
INSERT INTO Enroll VALUES (351, 'CSCI', 1510);
INSERT INTO Student VALUES (1247, 'Alexis', 'Senior', '3.9');
INSERT INTO Dept VALUES ('ENGL', 'Department of English', 'Wyatt Hall');
INSERT INTO Course VALUES (101, 'ENGL', 'How to Read', 'WY 100', 'MWF', '13:00');
INSERT INTO Enroll VALUES (101, 'ENGL', 1247);
INSERT INTO Student VALUES (1025, 'John', 'Senior', '3.6');
INSERT INTO Dept VALUES ('BUS', 'School of Business', 'McIntyre Hall');
INSERT INTO Course VALUES (351, 'BUS', 'Finance', 'WY 29', 'TR', '12:00');
INSERT INTO Enroll VALUES (351, 'BUS', 1025);
INSERT INTO Course VALUES (122, 'CSCI', 'How to Code Good', 'TH 19', 'TR', '12:00');
INSERT INTO Student VALUES (1661, 'Logan', 'Freshman', '0.5');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (351, 'CSCI', 'Database Systems', 'TH 19', 'MW', '12:00');
INSERT INTO Enroll VALUES (351, 'CSCI', 1661);
INSERT INTO Student VALUES (1304, 'Jordan', 'Senior', '2.9');
INSERT INTO Dept VALUES ('ENGL', 'Department of English', 'Wyatt Hall');
INSERT INTO Course VALUES (102, 'ENGL', 'How to Write', 'WY 100', 'MWF', '14:00');
INSERT INTO Enroll VALUES (102, 'ENGL', 1304);
INSERT INTO Student VALUES (1316, 'Austin', 'Sophomore', '2.1');
INSERT INTO Dept VALUES ('BUS', 'School of Business', 'McIntyre Hall');
INSERT INTO Course VALUES (122, 'BUS', 'Economics', 'WY 30', 'MW', '13:30');
INSERT INTO Enroll VALUES (122, 'BUS', 1316);
INSERT INTO Student VALUES (1025, 'John', 'Senior', '3.6');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (351, 'CSCI', 'Database Systems', 'TH 19', 'MW', '12:00');
INSERT INTO Enroll VALUES (351, 'CSCI', 1025);
INSERT INTO Student VALUES (1316, 'Austin', 'Sophomore', '2.1');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (460, 'CSCI', 'Operating Systems', 'TH 8', 'MW', '14:00');
INSERT INTO Enroll VALUES (460, 'CSCI', 1316);
INSERT INTO Student VALUES (1381, 'Tiffany', 'Junior', '4.0');
INSERT INTO Student VALUES (1468, 'Kris', 'Sophomore', '1.0');
INSERT INTO Student VALUES (1487, 'Erin', 'Sophomore', '3.9');
INSERT INTO Student VALUES (1501, 'Jessica', 'Freshman', '3.3');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (351, 'CSCI', 'Database Systems', 'TH 19', 'MW', '12:00');
INSERT INTO Enroll VALUES (351, 'CSCI', 1501);
INSERT INTO Student VALUES (1510, 'Jordan', 'Freshman', '3.0');
INSERT INTO Dept VALUES ('BUS', 'School of Business', 'McIntyre Hall');
INSERT INTO Course VALUES (122, 'BUS', 'Economics', 'WY 30', 'MW', '13:30');
INSERT INTO Enroll VALUES (122, 'BUS', 1510);
INSERT INTO Student VALUES (1934, 'Kyle', 'Junior', '2.1');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (453, 'CSCI', 'Capstone in Computer Science', 'TH 398', 'MWF', '16:00');
INSERT INTO Enroll VALUES (453, 'CSCI', 1934);
INSERT INTO Student VALUES (1782, 'Andrew', 'Sophomore', '3.7');
INSERT INTO Dept VALUES ('MATH', 'Department of Mathematics', 'Tower of Babel');
INSERT INTO Course VALUES (230, 'MATH', 'Linear Algebra', 'HH 308', 'TR', '15:00');
INSERT INTO Enroll VALUES (230, 'MATH', 1782);
INSERT INTO Student VALUES (1510, 'Jordan', 'Freshman', '3.0');
INSERT INTO Dept VALUES ('BUS', 'School of Business', 'McIntyre Hall');
INSERT INTO Course VALUES (351, 'BUS', 'Finance', 'WY 29', 'TR', '12:00');
INSERT INTO Enroll VALUES (351, 'BUS', 1510);
INSERT INTO Student VALUES (1629, 'Brad', 'Senior', '1.6');
INSERT INTO Student VALUES (1640, 'Adam', 'Senior', '3.6');
INSERT INTO Student VALUES (1304, 'Jordan', 'Senior', '2.9');
INSERT INTO Dept VALUES ('BUS', 'School of Business', 'McIntyre Hall');
INSERT INTO Course VALUES (351, 'BUS', 'Finance', 'WY 29', 'TR', '12:00');
INSERT INTO Enroll VALUES (351, 'BUS', 1304);
INSERT INTO Student VALUES (1304, 'Jordan', 'Senior', '2.9');
INSERT INTO Dept VALUES ('ENGL', 'Department of English', 'Wyatt Hall');
INSERT INTO Course VALUES (520, 'ENGL', 'Shakespeare Was Da Bomb', 'HH 20', 'TR', '13:00');
INSERT INTO Enroll VALUES (520, 'ENGL', 1304);
INSERT INTO Student VALUES (1641, 'Brittany', 'Senior', '2.7');
INSERT INTO Student VALUES (1661, 'Logan', 'Freshman', '0.5');
INSERT INTO Dept VALUES ('BUS', 'School of Business', 'McIntyre Hall');
INSERT INTO Course VALUES (351, 'BUS', 'Finance', 'WY 29', 'TR', '12:00');
INSERT INTO Enroll VALUES (351, 'BUS', 1661);
INSERT INTO Student VALUES (1025, 'John', 'Senior', '3.6');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (520, 'CSCI', 'High Performance Computing', 'WY 307', 'TR', '15:00');
INSERT INTO Enroll VALUES (520, 'CSCI', 1025);
INSERT INTO Student VALUES (1689, 'Gabriel', 'Senior', '2.4');
INSERT INTO Dept VALUES ('ENGL', 'Department of English', 'Wyatt Hall');
INSERT INTO Course VALUES (520, 'ENGL', 'Shakespeare Was Da Bomb', 'HH 20', 'TR', '13:00');
INSERT INTO Enroll VALUES (520, 'ENGL', 1689);
INSERT INTO Student VALUES (1661, 'Logan', 'Freshman', '0.5');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (460, 'CSCI', 'Operating Systems', 'TH 8', 'MW', '14:00');
INSERT INTO Enroll VALUES (460, 'CSCI', 1661);
INSERT INTO Course VALUES (101, 'SOAN', 'Sociology 1', 'WY 105', 'MWF', '08:00');
INSERT INTO Student VALUES (1782, 'Andrew', 'Sophomore', '3.7');
INSERT INTO Dept VALUES ('ENGL', 'Department of English', 'Wyatt Hall');
INSERT INTO Course VALUES (520, 'ENGL', 'Shakespeare Was Da Bomb', 'HH 20', 'TR', '13:00');
INSERT INTO Enroll VALUES (520, 'ENGL', 1782);
INSERT INTO Student VALUES (1911, 'David', 'Senior', '3.2');
INSERT INTO Dept VALUES ('MATH', 'Department of Mathematics', 'Tower of Babel');
INSERT INTO Course VALUES (230, 'MATH', 'Linear Algebra', 'HH 308', 'TR', '15:00');
INSERT INTO Enroll VALUES (230, 'MATH', 1911);
INSERT INTO Student VALUES (1689, 'Gabriel', 'Senior', '2.4');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (460, 'CSCI', 'Operating Systems', 'TH 8', 'MW', '14:00');
INSERT INTO Enroll VALUES (460, 'CSCI', 1689);
INSERT INTO Student VALUES (1661, 'Logan', 'Freshman', '0.5');
INSERT INTO Dept VALUES ('MATH', 'Department of Mathematics', 'Tower of Babel');
INSERT INTO Course VALUES (460, 'MATH', 'Calculus 3', 'WEY 102', 'TR', '12:30');
INSERT INTO Enroll VALUES (460, 'MATH', 1661);
INSERT INTO Student VALUES (1911, 'David', 'Senior', '3.2');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (351, 'CSCI', 'Database Systems', 'TH 19', 'MW', '12:00');
INSERT INTO Enroll VALUES (351, 'CSCI', 1911);
INSERT INTO Student VALUES (1934, 'Kyle', 'Junior', '2.1');
INSERT INTO Dept VALUES ('CSCI', 'School of Computer Science', 'Thompson Hall');
INSERT INTO Course VALUES (351, 'CSCI', 'Database Systems', 'TH 19', 'MW', '12:00');
INSERT INTO Enroll VALUES (351, 'CSCI', 1934);
INSERT INTO Student VALUES (1934, 'Kyle', 'Junior', '2.1');
INSERT INTO Dept VALUES ('ENGL', 'Department of English', 'Wyatt Hall');
INSERT INTO Course VALUES (520, 'ENGL', 'Shakespeare Was Da Bomb', 'HH 20', 'TR', '13:00');
INSERT INTO Enroll VALUES (520, 'ENGL', 1934);
INSERT INTO Major VALUES (1025, 'ENGL');
INSERT INTO Major VALUES (1247, 'ENGL');
INSERT INTO Major VALUES (1101, 'BUS');
INSERT INTO Major VALUES (1101, 'MATH');
INSERT INTO Major VALUES (1247, 'ENGL');
INSERT INTO Major VALUES (1304, 'MATH');
INSERT INTO Major VALUES (1101, 'BUS');
INSERT INTO Major VALUES (1101, 'MATH');
INSERT INTO Major VALUES (1709, 'CSCI');
INSERT INTO Major VALUES (1709, 'SOAN');
INSERT INTO Major VALUES (1101, 'BUS');
INSERT INTO Major VALUES (1101, 'MATH');
INSERT INTO Major VALUES (1247, 'ENGL');
INSERT INTO Major VALUES (1911, 'CSCI');
INSERT INTO Major VALUES (1911, 'ENGL');
INSERT INTO Major VALUES (1025, 'ENGL');
INSERT INTO Major VALUES (1247, 'ENGL');
INSERT INTO Major VALUES (1510, 'MATH');
INSERT INTO Major VALUES (1510, 'PHYS');
INSERT INTO Major VALUES (1247, 'ENGL');
INSERT INTO Major VALUES (1025, 'ENGL');
INSERT INTO Major VALUES (1661, 'CSCI');
INSERT INTO Major VALUES (1304, 'MATH');
INSERT INTO Major VALUES (1316, 'CSCI');
INSERT INTO Major VALUES (1025, 'ENGL');
INSERT INTO Major VALUES (1316, 'CSCI');
INSERT INTO Major VALUES (1381, 'CSCI');
INSERT INTO Major VALUES (1468, 'ENGL');
INSERT INTO Major VALUES (1487, 'ENGL');
INSERT INTO Major VALUES (1501, 'CSCI');
INSERT INTO Major VALUES (1510, 'MATH');
INSERT INTO Major VALUES (1510, 'PHYS');
INSERT INTO Major VALUES (1934, 'BUS');
INSERT INTO Major VALUES (1934, 'ENGL');
INSERT INTO Major VALUES (1782, 'BUS');
INSERT INTO Major VALUES (1510, 'MATH');
INSERT INTO Major VALUES (1510, 'PHYS');
INSERT INTO Major VALUES (1304, 'MATH');
INSERT INTO Major VALUES (1304, 'MATH');
INSERT INTO Major VALUES (1641, 'ENGL');
INSERT INTO Major VALUES (1661, 'CSCI');
INSERT INTO Major VALUES (1025, 'ENGL');
INSERT INTO Major VALUES (1689, 'BUS');
INSERT INTO Major VALUES (1661, 'CSCI');
INSERT INTO Major VALUES (1782, 'BUS');
INSERT INTO Major VALUES (1911, 'CSCI');
INSERT INTO Major VALUES (1911, 'ENGL');
INSERT INTO Major VALUES (1689, 'BUS');
INSERT INTO Major VALUES (1661, 'CSCI');
INSERT INTO Major VALUES (1911, 'CSCI');
INSERT INTO Major VALUES (1911, 'ENGL');
INSERT INTO Major VALUES (1934, 'BUS');
INSERT INTO Major VALUES (1934, 'ENGL');
INSERT INTO Major VALUES (1934, 'BUS');
INSERT INTO Major VALUES (1934, 'ENGL');

Please enter path for text file (type 'exit' to quit program): 
exit
Exiting... bye.
</pre>
# Credits
My professor, David Chiu, supplied the raw ShmeepleSoft data for this assignment.
