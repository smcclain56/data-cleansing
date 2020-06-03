-- Turn on foreign keys
PRAGMA foreign_keys = ON;

-- Delete the tables if they already exist
drop table if exists Enroll;
drop table if exists Course;
drop table if exists Major;
drop table if exists Dept;
drop table if exists Student;

-- Create the schema for your tables below
CREATE TABLE Student (
  studentID INTEGER PRIMARY KEY,
  studentName TEXT NOT NULL,
  class TEXT check(class LIKE 'Freshman' or class LIKE 'Sophomore'
    or class GLOB 'Junior' or class GLOB 'Senior'),
  gpa REAL check(gpa<=4.0 and gpa>=0.0)
);

-- HOW TO ENSURE DEPARTMENTS ARE HOUSES IN ONE BUILDING AND ONLY HAVE ONE LABEL?
CREATE TABLE Dept (
  deptID  TEXT PRIMARY KEY UNIQUE check(LENGTH(deptID) <= 4),
  name TEXT UNIQUE NOT NULL,
  building TEXT
);

CREATE TABLE Major (
  studentID INTEGER,
  major TEXT,
  PRIMARY KEY (studentID, major),
  FOREIGN KEY (studentID) REFERENCES Student(studentID)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (major) REFERENCES Dept(deptID)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

CREATE TABLE Course (
  courseNum INTEGER,
  deptID TEXT,
  courseName TEXT,
  location TEXT,
  meetDay TEXT,
  meetTime TEXT check(meetTime > '07:00' and meetTime < '17:00'),
  PRIMARY KEY (courseNum, deptID),
  FOREIGN KEY (deptID) REFERENCES Dept(deptID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Enroll (
  courseNum INTEGER,
  deptID TEXT,
  studentID INTEGER,
  PRIMARY KEY(courseNum, deptID, studentID),
  FOREIGN KEY (courseNum, deptID) REFERENCES Course(courseNum,deptID)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (studentID) REFERENCES Student(studentID)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
