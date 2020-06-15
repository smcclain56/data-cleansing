import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("========================= Welcome to Data Cleanser ==========================");
        Scanner scan = new Scanner(System.in);
        String filename;
        try {
            do {
                System.out.println("\nPlease enter path for text file (type 'exit' to quit program): ");
                filename = scan.nextLine();
                if(!filename.equals("exit")) {
                    File file = new File(filename);
                    System.out.println();
                    System.out.println("Generating SQL statements...");
                    readFile(file);
                }
            } while (!filename.equals("exit"));

            // print exit message
            System.out.println("Exiting... bye.");
            System.exit(0);
        }catch(InputMismatchException e) {
            throw new InputMismatchException();
        }
    }

    /*
    *   This function takes in a filename and reads through each line of the file,
    *   printing out a script to be used to add the data into an SQLite Database
    *   @param Name of the file to be read
     */
    public static void readFile(File fileName) throws FileNotFoundException{
        Scanner scanner = new Scanner(fileName);
        String line;
        ArrayList<String> majorsToAdd = new ArrayList<String>();
        scanner.nextLine(); //get rid of line with attribute names
        scanner.nextLine(); //get rid of dashed line
        while(scanner.hasNextLine()){
            line = scanner.nextLine(); //get first line
            String[] tokens = line.split(",");
            if(tokens[0].equals("")){ //either adding a dept or course
                if(tokens.length == 11){
                    insertCourse(tokens[5], tokens[6], tokens[7], tokens[8], tokens[9], tokens[10]);
                }else {
                    insertDept(tokens[6],tokens[11], tokens[12]);
                }
                continue;
            }
            //otherwise we must add the student
            insertStudent(tokens[0], tokens[1], tokens[2], tokens[3]);
            if(tokens.length > 5){ //add dept and course
                insertDept(tokens[6],tokens[11], tokens[12]);insertCourse(tokens[5], tokens[6], tokens[7], tokens[8], tokens[9], tokens[10]);
                enrollStudent(tokens[5],tokens[6],tokens[0]);
            }
            if(tokens.length >= 5 && tokens[4].equals("") == false){ //hold onto majors to add
                String addMajor = tokens[0] + "," + tokens[4];
                majorsToAdd.add(addMajor);
            }
        }

        //insert all the majors
        for(int i=0; i<majorsToAdd.size(); i++){
            String[] tokens = majorsToAdd.get(i).split(",");
            if(tokens.length > 0){
                String[] majors = tokens[1].split(";");
                for(int j = 0; j<majors.length; j++){
                    insertMajor(tokens[0], majors[j]);
                }
            }
        }

    }

    /*
    *   This function creates an SQL statement to insert data into the Student table of the schema
    */
    public static void insertStudent(String studentID, String studentName, String classStanding, String gpa){
        //check for inconsistent data in class standing
        if(classStanding.equals("SR")){
            classStanding = "Senior";
        }else if(classStanding.equals("JR")){
            classStanding = "Junior";
        }

        System.out.println("INSERT INTO Student VALUES ("+studentID + ", \'"+ studentName + "\', \'" + classStanding + "\', \'" + gpa + "\');");
    }

    /*
     *   This function creates an SQL statement to insert data into the Course table of the schema
     */
    public static void insertCourse(String courseNum, String deptID, String courseName, String location, String meetDay, String meetTime){
        // check for inconsistent data of deptID
        if( deptID.equals("ENG")){
            deptID = "ENGL";
        }
        System.out.println("INSERT INTO Course VALUES ("+courseNum + ", \'"+ deptID + "\', \'" + courseName+ "\', \'"
                + location + "\', \'" + meetDay + "\', \'" + meetTime +"\');");

    }

    /*
     *   This function creates an SQL statement to insert data into the Major table of the schema
     */
    public static void insertMajor(String studentID, String major){
        // check for inconsistent data of deptID
        if( major.equals("ENG")){
            major = "ENGL";
        }
        System.out.println("INSERT INTO Major VALUES ("+studentID + ", \'"+ major + "\');");

    }

    /*
     *   This function creates an SQL statement to insert data into the Dept table of the schema
     */
    public static void insertDept(String deptID, String name, String building){
        // check for inconsistent data of deptID
        if( deptID.equals("ENG")){
            deptID = "ENGL";
        }
        System.out.println("INSERT INTO Dept VALUES (\'"+deptID + "\', \'"+ name + "\', \'" + building +"\');");

    }

    /*
     *   This function creates an SQL statement to insert data into the Enroll table of the schema
     */
    public static void enrollStudent(String courseNum, String deptID, String studentID){
        // check for inconsistent data of deptID
        if( deptID.equals("ENG")){
            deptID = "ENGL";
        }
        System.out.println("INSERT INTO Enroll VALUES ("+ courseNum+ ", \'"+ deptID + "\', " + studentID + ");");

    }

}
