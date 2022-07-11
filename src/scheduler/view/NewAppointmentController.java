/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import scheduler.Scheduler;
import scheduler.model.user;


/**
 * FXML Controller class
 *
 * @author Misker
 */

public class NewAppointmentController implements Initializable {
    @FXML
    private TextField contactField;
    @FXML
    private ComboBox<String> startTime;
    @FXML
    private ComboBox<String> endTime;
    @FXML
    private ComboBox<String> customers;
    @FXML
    private TextField typeField;
    @FXML
    private TextArea descField;
    @FXML
    private DatePicker datePick;
    @FXML
    private Button saveBtn;
    @FXML 
    private Button cancelBtn;
    
    private Connection conn;
    private Statement stmnt;
    private Scheduler app;
    private user person;
    private String contact;
    private String type;
    private String description;
    private String start;
    private String end;
    private LocalDate day;
    private LocalTime startTime2;
    private LocalTime endTime2;
    private LocalDateTime startLDT;
    private LocalDateTime endLDT;
    private ZoneId zone = ZoneId.systemDefault();

    //initializes the new appointment screen and populates the options for the Combo boxes
    public void newAppointment(Scheduler app, user person)
    {
        this.app = app;
        this.person = person;
        try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
        stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT customerName FROM CUSTOMERS");
        while(rs.next())
        {
            String name = rs.getString("customerName");
            customers.getItems().add(name);
        }
        }catch(Exception e){e.printStackTrace();}
        
        startTime.getItems().addAll("09:00 AM","09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "01:00 PM", "01:30 PM",
                "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM");
        endTime.getItems().addAll("09:00 AM","09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "01:00 PM", "01:30 PM",
                "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM", "05:00 PM");  
        
        
        //Lambda expression to handle the cancel button to go to appointment screen without having to create a separate handler.
        cancelBtn.setOnAction((ActionEvent e) -> { app.launchAppointmentScreen(person); });
    }
    
    //Handles the save button. Saves the appointment in database using the info from the fields
    @FXML
    public void saveHandler(ActionEvent event)
    {
        
        day= datePick.getValue(); 
        type = typeField.getText();
        description = descField.getText();
        startTime2 = getTime(startTime.getSelectionModel().getSelectedItem());
        endTime2 = getTime(endTime.getSelectionModel().getSelectedItem());
        startLDT = LocalDateTime.of(day, startTime2);
        endLDT = LocalDateTime.of(day, endTime2);
        Timestamp startTimestamp = Timestamp.valueOf(startLDT);
        Timestamp endTimestamp = Timestamp.valueOf(endLDT);
        
        ZonedDateTime zone1 = ZonedDateTime.of(startLDT, zone);
        
        // if statement makes sure that there is no other appointment during that time.
        if(verifyTime(startTimestamp, endTimestamp))
        {
            try{
                conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
                stmnt = conn.createStatement();

                ResultSet rs = stmnt.executeQuery("SELECT * FROM APPOINTMENTS");
                int highest = 0;
                while(rs.next()){ int record = rs.getInt("appointmentID"); if(record > highest){highest = record;}}
                int appointmentID = highest + 1;

                PreparedStatement prepared = conn.prepareStatement("INSERT INTO APPOINTMENTS (appointmentID, start, end, type, description, contact, userID) VALUES (?,?,?,?,?,?,?)");
                prepared.setInt(1, appointmentID);
                prepared.setTimestamp(2, startTimestamp);
                prepared.setTimestamp(3, endTimestamp);
                prepared.setString(4, type);
                prepared.setString(5, description);
                prepared.setString(6, customers.getSelectionModel().getSelectedItem());
                prepared.setInt(7, person.getUserId());
                prepared.execute();

            }catch(Exception e){e.printStackTrace();}       
        }
        else //Gives an alert to try again
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText("There was an error in setting up the appointment! Please try again!");
           alert.showAndWait();
        }
        app.launchAppointmentScreen(person);
    }
    
    // receives the time of the appointment and makes sure that it does not conflict with other appointments
    public boolean verifyTime(Timestamp start, Timestamp end) 
    {
        boolean pass = true;
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
            stmnt = conn.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM APPOINTMENTS");
            while(rs.next())
            {
                Timestamp start2 = rs.getTimestamp("start");
                Timestamp end2 = rs.getTimestamp("end");
                
                if(start.after(start2) && start.before(end2))
                {
                    System.out.println("Time is taken!~");
                    
                    System.out.println(start + "----" + end + "----" + start2 + "-----" + end2);
                    
                    pass = false;
                }
                if(end.after(start2) && end.before(end2))
                {
                    System.out.println("Time is taken!");
                    
                    System.out.println(start + "----" + end + "----" + start2 + "-----" + end2);
                    pass = false;
                }
                if(start.equals(start2))
                {   
                    System.out.println("Time is taken!");
                    
                    System.out.println(start + "----" + end + "----" + start2 + "-----" + end2);
                    pass = false;
                }
            }
            
            
            
            }catch(Exception e){e.printStackTrace();}
        return pass;
    }
    
    //Changes the string time to local time format
    public LocalTime getTime(String time)
    {
        LocalTime result;
        int hour;
        int minutes;
        if(time.charAt(6) == 'P' && !time.substring(0, 2).equals("12"))
        {
            hour = Integer.parseInt(time.substring(0, 2)) + 12;
            minutes = Integer.parseInt(time.substring(3, 5));
            result= LocalTime.of(hour, minutes);
            System.out.println(result);
        }else{
            hour = Integer.parseInt(time.substring(0, 2));
            minutes = Integer.parseInt(time.substring(3, 5));
            result= LocalTime.of(hour, minutes);
            System.out.println(result);
        }
        return result;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}

