/*
Christopher Bain
Module 7 Database Project
202610 Object-Oriented Programming COP-3330C-13949
Date Friday, October 31st 2025

The Objective of this project is to manipulate data in a database with java
This program does not take any inputs from the user
 */
import DBHelper.EngineeringStudents;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;


public class Module7Project {
    public static void main(String[] args) {
        //preperation code
        EngineeringStudents db1 = new EngineeringStudents();
        db1.update("Department","Electronics","Department","Electronics/Circuits");


        //1
        //2: adding 2 records
        //When adding records to the database they are "permanent"
        db1.insert(2203018,"Electrical","Rachel","Carter",2022,5);
        System.out.println("A new row of data has been added");
        db1.insert(2306019,"Information Technology", "John","Hancock",2023,17);
        System.out.println("A new row of data has been added");

        //3 : printing the data as 2d array list
        System.out.println("Output with 2d array");
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        data = db1.getExecuteResult("SELECT * FROM EngineeringStudents");
        for(List<Object> record : data){
            System.out.println(record.toString());
        }
        System.out.println("---------------------------------------------------------------------");

        //4: Delete 2 rows from the database
        //Originally I was going to remove 2 different records of information from the table until I saw that the changes that are made
        //are persistant between the program therefore when removing what was added it allows easier retest of the application
        db1.delete("Student_ID","2203018");
        System.out.println("A row of data has been deleted");
        db1.delete("Student_ID","2306019");
        System.out.println("A row of data has been deleted");

        //5: printing the database with the default table model
        DefaultTableModel table = new DefaultTableModel();
        table = db1.selectToTable("Student_ID, Department, First_Name, Last_Name, PassOutYear, UniversityRank",
                null, null,null,null);
        System.out.println("output with vector");
        for(int row = 0; row < table.getRowCount(); row++){
            for(int col = 0; col < table.getColumnCount(); col++){
                System.out.print(table.getValueAt(row,col).toString() + " | ");
            }
            System.out.println();
        }

        System.out.println("---------------------------------------------------------------------");

        //6: Query to search for a specific group of students or a student
        System.out.println(); //creating space
        data = db1.select("Student_ID, Department, First_Name, Last_Name, PassOutYear, UniversityRank",
                "PassOutYear","2023","UniversityRank","ASC");

        //7: outputting the results of the previous query to the screen because the select method uses a 2d array list.
        System.out.println("output with 2d array");
        for(List<Object> record : data){
            System.out.println(record.toString());
        }

        System.out.println("---------------------------------------------------------------------");

        //8: update three values within the table
        //The three values that im updating are the rows that have Electronics in the Department description to make it more descriptive im changing it to Electronics and Circuit boards
        db1.update("Department","Electronics/Circuits","Department","Electronics");



        //9: Print the database as a default Table model
        table = db1.selectToTable("Student_ID, Department, First_Name, Last_Name, PassOutYear, UniversityRank",
                null, null,null,null);
        System.out.println("output with vector");
        for(int row = 0; row < table.getRowCount(); row++){
            for(int col = 0; col < table.getColumnCount(); col++){
                System.out.print(table.getValueAt(row,col).toString() + " | ");
            }
            System.out.println();
        }
    }// end of main function
}
