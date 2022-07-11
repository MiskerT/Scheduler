/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Misker
 */
public class Creator2 
{
    private static Connection conn ;
    private static Statement stmnt;

    public static void main(String [] args)
    {
        System.out.println("----------------------------------------------");
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
            stmnt = conn.createStatement();
            

//stmnt.execute("RENAME TABLE city TO city2");
//stmnt.execute("RENAME TABLE address TO address2");
//stmnt.execute("RENAME TABLE appointment TO appointment2");
//stmnt.execute("RENAME TABLE country TO country2");
//stmnt.execute("RENAME TABLE customer TO customer2");
//stmnt.execute("RENAME TABLE user TO user2");


//            stmnt.execute("CREATE TABLE USERS (userID INT, userName VARCHAR(50), password VARCHAR(50), active TINYINT, createDate DATETIME, "
//                    + "createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(userID) )");

     
//            stmnt.execute("INSERT INTO USERS VALUES (1, 'Judy', '123456', '1',null,null,null,null)");
//            stmnt.execute("INSERT INTO USERS (userID, userName, password) VALUES (2, 'test', 'test')");
//            stmnt.execute("INSERT INTO USERS (userID, userName, password) VALUES (3, 'misker', 'misker')");
//            


//stmnt.execute("CREATE TABLE COUNTRY(countryID INT(10), country VARCHAR(50), "
//                + "createDate DATETIME, createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(countryID))");

//stmnt.execute("CREATE TABLE CITY(cityID INT(10), city VARCHAR(50), countryID INT(10), "
//                + "createDate DATETIME, createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(cityID), FOREIGN KEY(countryID) references COUNTRY(countryID))");
       
//        stmnt.execute("CREATE TABLE ADDRESS(addressID INT(10), address VARCHAR(50), address2 VARCHAR(50), cityID INT(10), postalCode VARCHAR(10), phone VARCHAR(20), "
//                + "createDate DATETIME, createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(addressID), FOREIGN KEY(cityID) references CITY(cityID))");
//     
//        stmnt.execute("CREATE TABLE CUSTOMERS (customerID INT(10), customerName VARCHAR(45), addressID INT(10), active TINYINT(1), createDate DATETIME, "
//                + "createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(customerID), FOREIGN KEY(addressID) references ADDRESS(addressID))");

//        stmnt.execute("CREATE TABLE APPOINTMENTS(appointmentID INT(10), customerID INT(10), userID INT, title VARCHAR(255), description TEXT, location TEXT, contact TEXT, type TEXT,"
//                + "url VARCHAR(255), start TIMESTAMP, end DATETIME, createDate DATETIME, createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(appointmentID),"
//                + " FOREIGN KEY(customerID) references CUSTOMERS(customerID), FOREIGN KEY(userID) references USERS(UserID))");

//stmnt.execute("INSERT INTO COUNTRY (countryID, country, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (1, 'USA',null,null,null,null)");
//stmnt.execute("INSERT INTO COUNTRY VALUES (2, 'UK',null, null, null, null)");

//stmnt.execute("INSERT INTO CITY VALUES (1, 'Phoenix',null,null,null,null,null)");
//stmnt.execute("INSERT INTO CITY VALUES (2, 'New York',null,null,null,null,null)");
//stmnt.execute("INSERT INTO CITY VALUES (3, 'London',null,null,null,null,null)");

            ResultSet rs = stmnt.executeQuery("show tables;");
            while(rs.next())
            {
                String tables = rs.getString(1);
                System.out.println(tables);
                System.out.println("----------------------------------------------");
            }
            
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
