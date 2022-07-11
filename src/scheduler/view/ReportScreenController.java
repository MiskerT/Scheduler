/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import scheduler.Scheduler;
import scheduler.model.appointment;
import scheduler.model.user;
import scheduler.model.report;
/**
 * FXML Controller class
 *
 * @author Misker
 */
public class ReportScreenController{
// Table elements for the first Report    
@FXML 
private TableView<appointment> apptTbl;
@FXML
private TableColumn<appointment, String> contactClmn;
@FXML
private TableColumn<appointment, String> startClmn;
@FXML
private TableColumn<appointment, Date> endClmn;
@FXML
private TableColumn<appointment, String> titleClmn;
@FXML
private TableColumn<appointment, String> descriptionClmn;

// Table elements for the second report
@FXML
private TableView typeTbl;
@FXML
private TableColumn monthClmn;
@FXML 
private TableColumn typeClmn;
@FXML
private TableColumn totalClmn;

// Table elements for the third report
@FXML
private TableView customTbl;
@FXML
private TableColumn typeClmn2;
@FXML
private TableColumn totalClmn2;

//Other elements
@FXML
private Button scheduleBtn;
@FXML
private Button monthBtn;
@FXML
private Button typeBtn;
@FXML
private Button apptBtn;
@FXML
private Button customerBtn;

private Scheduler app;
private user person;  
private Connection conn;
private Statement stmnt;
ObservableList<appointment> oblist;
ObservableList<report> oblist2;
ObservableList<report> oblist3;

//initializes the reports screen and populates the table with appointments for the speciified user
public void bySchedule(Scheduler app, user person) 
{
    this.app = app;
    this.person = person;
    
    contactClmn.setCellValueFactory(new PropertyValueFactory<>("contact"));
    startClmn.setCellValueFactory(new PropertyValueFactory<>("start"));
    endClmn.setCellValueFactory(new PropertyValueFactory<>("end"));
    titleClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
    descriptionClmn.setCellValueFactory(new PropertyValueFactory<>("description"));
    oblist= FXCollections.observableArrayList();
    
    try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
        stmnt = conn.createStatement();
        
        
        ResultSet rs = stmnt.executeQuery("SELECT * FROM APPOINTMENTS WHERE userID=" + person.getUserId()); //gets the appointments for the user by using the userID to filter
        while(rs.next())
        {
                    int appointmentID = rs.getInt("appointmentID");
                    String contact = rs.getString("contact");
                    Timestamp start = rs.getTimestamp("start");
                    Timestamp end = rs.getTimestamp("end");
                    String title = rs.getString("type");
                    String description = rs.getString("description");
                    
                    start.toLocalDateTime();
                    end.toLocalDateTime();
                    
                    String st = toLocal(start);
                    String en = toLocal(end);
                    
                   
                    oblist.add(new appointment(contact, st, en, title, description, appointmentID));
        }
    }catch(Exception e){e.printStackTrace();}    
        
    apptTbl.getItems().setAll(oblist);    
    byType();
    customTbl();
}  

//populates the table with appointments using type as a filter
public void byType()
{
    monthClmn.setCellValueFactory(new PropertyValueFactory<>("month"));
    typeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
    totalClmn.setCellValueFactory(new PropertyValueFactory<>("total"));
    oblist2= FXCollections.observableArrayList();
    
    try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
        stmnt = conn.createStatement();
        
        ResultSet rs = stmnt.executeQuery("SELECT start, type, COUNT(*) AS 'total' FROM APPOINTMENTS GROUP BY type");
              while(rs.next())
              {
                 Timestamp start = rs.getTimestamp("start");
                 String month = start.toLocalDateTime().getMonth().toString();
                 String type = rs.getString("type");
                 String total = rs.getString("total");
                    
                 oblist2.add(new report(month, type, total));
              }
              
    }catch(Exception e){e.printStackTrace();}    
    typeTbl.getItems().setAll(oblist2);
}

//populates the table using type and number of appointments of that type
public void customTbl()
{
    typeClmn2.setCellValueFactory(new PropertyValueFactory<>("type"));
    totalClmn2.setCellValueFactory(new PropertyValueFactory<>("total"));
    oblist3= FXCollections.observableArrayList();
    
    try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
        stmnt = conn.createStatement();
        
        ResultSet rs = stmnt.executeQuery("SELECT type, COUNT(*) AS 'total' FROM APPOINTMENTS GROUP BY type");
              while(rs.next())
              {
                 String type = rs.getString("type");
                 String total = rs.getString("total");
                    
                 oblist3.add(new report(type, total));
              }
              
    }catch(Exception e){e.printStackTrace();}    
    customTbl.getItems().setAll(oblist3);
}

//Handlers for all the buttons to change the visibility of all the tableviews depending on the button pressed
@FXML
public void scheduleBtn(ActionEvent event)
{
    apptTbl.setVisible(true);
    typeTbl.setVisible(false);
    customTbl.setVisible(false);
}
@FXML
public void monthBtn(ActionEvent event)
{
    typeTbl.setVisible(true);
    apptTbl.setVisible(false);
    customTbl.setVisible(false);
}
@FXML
public void typeBtn(ActionEvent event)
{
    customTbl.setVisible(true);
    typeTbl.setVisible(false);
    apptTbl.setVisible(false);
}

//launches the appointment screen
@FXML
public void apptBtn(ActionEvent event)
{
    app.launchAppointmentScreen(person);
}

//launches customer screen
@FXML
public void customerBtn(ActionEvent event)
{
    app.launchCustomerScreen(person);
}

//converts the timestamp to local time format.
public String toLocal(Timestamp stamp)
    {
       
        LocalDate date = stamp.toLocalDateTime().toLocalDate();
        LocalTime time = stamp.toLocalDateTime().toLocalTime();
        LocalTime twelve = LocalTime.of(12, 0);
        if((time.compareTo(twelve)) > 0)
        {
            time = time.minusHours(12);
            return date + " " + time + " PM";
        }
        
        return date + " " + time + "AM";
    }

}
