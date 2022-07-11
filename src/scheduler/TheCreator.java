/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalTime;
import javafx.scene.control.Alert;

/**
 *
 * @author Misker
 */
public class TheCreator 
{
private static Connection conn ;
private static Statement stmnt;

public static void main(String [] args)
{
    System.out.println("----------------------------------------------");
    try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
        stmnt = conn.createStatement();
        //stmnt.execute("INSERT INTO CUSTOMERS VALUES (5, 'MISKER', 4)");

//        stmnt.execute("CREATE TABLE COUNTRY(countryID INT(10), country VARCHAR(50), "
//                + "createDate DATETIME, createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(countryID))");
//        
//       stmnt.execute("CREATE TABLE CITY(cityID INT(10), city VARCHAR(50), countryID INT(10), "
//                + "createDate DATETIME, createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(cityID), FOREIGN KEY(countryID) references COUNTRY(countryID))");
//        
//        stmnt.execute("CREATE TABLE ADDRESS(addressID INT(10), address VARCHAR(50), address2 VARCHAR(50), cityID INT(10), postalCode VARCHAR(10), phone VARCHAR(20), "
//                + "createDate DATETIME, createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(addressID), FOREIGN KEY(cityID) references CITY(cityID))");
//        
//        stmnt.execute("CREATE TABLE USERS (userID INT, userName VARCHAR(50), password VARCHAR(50), active TINYINT, createDate DATETIME, "
//                        + "createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(userID) )");
//           
//        stmnt.execute("CREATE TABLE CUSTOMERS (customerID INT(10), customerName VARCHAR(45), addressID INT(10), active TINYINT(1), createDate DATETIME, "
//                + "createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(customerID), FOREIGN KEY(addressID) references ADDRESS(addressID))");
//        
//        stmnt.execute("CREATE TABLE APPOINTMENTS(appointmentID INT(10), customerID INT(10), userID INT, title VARCHAR(255), description TEXT, location TEXT, contact TEXT, type TEXT,"
//                + "url VARCHAR(255), start TIMESTAMP, end DATETIME, createDate DATETIME, createdBy VARCHAR(40), lastUpdate TIMESTAMP, lastUpdateBy VARCHAR(40), PRIMARY KEY(appointmentID),"
//                + " FOREIGN KEY(customerID) references CUSTOMERS(customerID), FOREIGN KEY(userID) references USERS(UserID))");

 //      stmnt.executeUpdate("INSERT INTO TABLE ACCOUNTS VALUES (3,'USA','123')");
 //    stmnt.execute("INSERT INTO TABLE country (countryID, country) VALUES (1,'USA')");
//     stmnt.execute("INSERT INTO TABLE CITY VALUES (1, 'DC',null,null,null,null,null)");
//     stmnt.execute("INSERT INTO TABLE ADDRESS VALUES (1, '1234 grove street', 'Los Santos', 1,null,null,null,null,null,null)");
//     stmnt.execute("INSERT INTO TABLE USERS VALUES (1, 'Judy', '123456', '1',null,null,null,null)");
//     stmnt.execute("INSERT INTO TABLE CUSTOMERS VALUES (1, 'Jesus', 1,null,null,null,null,null,null,null,null,null,null,null,null)");
//     stmnt.executeUpdate("INSERT INTO TABLE APPOINTMENTS VALUES (1, 1, 1, 'meeting', 'lets discuss something')");
//          stmnt.execute("INSERT INTO USERS (userID, userName, password) VALUES (2, 'test', 'test')");
//          stmnt.execute("INSERT INTO USERS (userID, userName, password) VALUES (3, 'misker', 'misker')");

//          String query = "INSERT INTO APPOINTMENTS (appointmentID, customerID, userID, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) "
//                  + "VALUES(?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,?)";
//
//             PreparedStatement statement = conn.prepareStatement(query);
//             statement.setInt(1, 2);
//             statement.setInt(2, 1);
//             statement.setInt(3, 1);
//             statement.setString(4, "Drinks");
//             statement.setString(5, "what to drink.");
//             statement.setString(6, "Mall");
//             statement.setString(7, "Nick");
//             statement.setString(8, "ffff");
//             statement.setString(9, "fadsfa");
//             
//             statement.setString(10, "Misker");
//             
//             statement.setString(11, "Misker");
//             
//             statement.execute();
       //stmnt.execute("INSERT INTO APPOINTMENTS (appointmentID, title, customerID, description) VALUES(6,'Relifion', 3, 'Jesus take the wheel') ");
               
                       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                       alert.setTitle("Confirmation Dialog");
                       alert.setHeaderText("There is an appointment coming up!");
               

    }catch(SQLException e)
    {
        e.printStackTrace();
    }
}
    
}
