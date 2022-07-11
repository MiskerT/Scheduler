/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import scheduler.Scheduler;
import scheduler.model.appointment;
import scheduler.model.user;



/**
 * FXML Controller class
 *
 * @author Misker
 */
public class EditAppointmentScreenController implements Initializable {
    
    @FXML
    private ComboBox<String> contactBox;
    @FXML
    private DatePicker datePick;
    @FXML 
    private ComboBox<String> startBox;
    @FXML
    private ComboBox<String> endBox;
    @FXML
    private TextField typeField;
    @FXML
    private TextArea descField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    
    private Scheduler app;
    private user person;
    
    private Connection conn;
    private Statement stmnt;
    private int appointmentID;
    
    //initialize edit appointment screen and populate the field using the selected appointment
    public void editAppointment(Scheduler app, user person, appointment target)
    {
        this.app = app;
        this.person = person;
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
            stmnt = conn.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT customerName FROM CUSTOMERS");
            while(rs.next())
            {
                String name = rs.getString("customerName");
                contactBox.getItems().add(name);
            }
        }catch(Exception e){e.printStackTrace();}
        
        
            startBox.getItems().addAll("09:00 AM","09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "01:00 PM", "01:30 PM",
                "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM");
            endBox.getItems().addAll("09:00 AM","09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "01:00 PM", "01:30 PM",
                "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM", "05:00 PM");
            
            
            String contact = target.getContact();
            String start = target.getStart();
            String end = target.getEnd();
            String type = target.getType();
            String description = target.getDescription();
            appointmentID = target.getAppointmentId();
            int year = Integer.parseInt(start.substring(0,4));
            int month = Integer.parseInt(start.substring(5,7));
            int day = Integer.parseInt(start.substring(8,10));
            String startTime = start.substring(11,16) + " " + start.substring(16,18);
            String endTime = end.substring(11,16) + " " + end.substring(16,18);
            LocalDate date = LocalDate.of(year, month, day);
            
            //populates the fields
            contactBox.getSelectionModel().select(contact);
            datePick.setValue(date);
            startBox.getSelectionModel().select(startTime);
            endBox.getSelectionModel().select(endTime);
            typeField.setText(type);
            descField.setText(description);
            
       //Lambda expression to handle the cancel button to go to appointment screen without having to create a separate handler.
        cancelBtn.setOnAction((ActionEvent e) -> { app.launchAppointmentScreen(person); });   
    }
    
    //handles the save button by updating the selected appointment in the database
    @FXML
    public void saveHandler(ActionEvent event)
    {
        
        
        LocalDate day= datePick.getValue(); 
        String type = typeField.getText();
        String description = descField.getText();
        LocalTime startTime2 = getTime(startBox.getSelectionModel().getSelectedItem());
        LocalTime endTime2 = getTime(endBox.getSelectionModel().getSelectedItem());
        LocalDateTime startLDT = LocalDateTime.of(day, startTime2);
        LocalDateTime endLDT = LocalDateTime.of(day, endTime2);
        Timestamp startTimestamp = Timestamp.valueOf(startLDT);
        Timestamp endTimestamp = Timestamp.valueOf(endLDT);
        
        if(verifyTime(startTimestamp, endTimestamp)) //Checks if there is a time conflict
        {    
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
            stmnt = conn.createStatement();    
            
            //Updates appointment
            PreparedStatement prepared = conn.prepareStatement("UPDATE APPOINTMENTS SET start = ?, end = ?, type = ?, description = ?,  WHERE appointmentID=" + appointmentID );
            prepared.setTimestamp(1, startTimestamp);
            prepared.setTimestamp(2, endTimestamp);
            prepared.setString(3, type);
            prepared.setString(4, description);


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
    
    
    //converts the time to local time
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
