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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import scheduler.Scheduler;
import scheduler.model.user;
import scheduler.model.appointment;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.time.ZoneId;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Misker
 */



public class AppointmentScreenController {

@FXML 
private TableView<appointment> apptTbl;
@FXML
private TableColumn<appointment, String> contactClmn;
@FXML
private TableColumn<appointment, String> startClmn;
@FXML
private TableColumn<appointment, Date> endClmn;
@FXML
private TableColumn<appointment, String> typeClmn;
@FXML
private TableColumn<appointment, String> descriptionClmn;
@FXML
private Button deleteBttn;
@FXML
private Button editBtn;
@FXML
private Button customerBtn;
@FXML
private Button reportBtn;
@FXML
private Button calandarBtn;


ObservableList<appointment> oblist;

private Scheduler app;
private user person;
private Connection conn;
private Statement stmnt;
private boolean byWeek = true;
private ZoneId zone = ZoneId.systemDefault();


//initizlize the appointment screen    
    public void showAppointments(Scheduler app, user person)
    {
        this.app = app;
        this.person = person;
        
        contactClmn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        startClmn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endClmn.setCellValueFactory(new PropertyValueFactory<>("end"));
        typeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionClmn.setCellValueFactory(new PropertyValueFactory<>("description"));

        
        oblist= FXCollections.observableArrayList();  
    
        //Checks the database and adds the appointments to the observable list
        try{
                conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
                stmnt=conn.createStatement();
                
                PreparedStatement statement = conn.prepareStatement("SELECT appointmentID, contact, start, end, type, description FROM APPOINTMENTS");
                
                ResultSet rs = statement.executeQuery();
                
                while(rs.next())
                {
                    int appointmentID = rs.getInt("appointmentID");
                    String contact = rs.getString("contact");
                    Timestamp start = rs.getTimestamp("start");
                    Timestamp end = rs.getTimestamp("end");
                    String type = rs.getString("type");
                    String description = rs.getString("description");
                    
                    start.toLocalDateTime();
                    end.toLocalDateTime();
                    
                    String st = toLocal(start);
                    String en = toLocal(end);
                    
                   
                    oblist.add(new appointment(contact, st, en, type, description, appointmentID));
                    
                }
                                
            }catch(SQLException e)
            {
                e.printStackTrace();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
       
        
        apptTbl.getItems().setAll(oblist); //populates the table
        checkAppt();
       
    }
    
    //Checks if there is an appointment within 15 minutes
    public void checkAppt()
    {
       try{
            conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");

            stmnt =  conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT start FROM APPOINTMENTS");

                while(rs.next())
                {
                    Timestamp start = rs.getTimestamp("start");
                    LocalDate date = start.toLocalDateTime().toLocalDate();
                    LocalTime time = start.toLocalDateTime().toLocalTime();
                    LocalDate localD = LocalDate.now();
                    LocalTime localT = LocalTime.now();
                    Duration duration = Duration.ofMinutes(15);
                    long minutes = ChronoUnit.MINUTES.between(time, localT);
                    
                // Checks the conditions and launches an alert box if there is an appointment within 15 minutes
                    if(date.compareTo(localD) == 0 && minutes <= 15 && localT.isBefore(time))
                    {               
                       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                       alert.setTitle("Confirmation Dialog");
                       alert.setHeaderText("There is an appointment coming up!");
                       alert.showAndWait();
                    }

                }
            }catch(Exception e){
                e.printStackTrace();
            } 
    }
    
    //Takes the timestamp and converts it to local time for comparrison
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
    
    //deletes selected appointment
    @FXML
    public void deteteHandler(ActionEvent event)
    {
        appointment target = apptTbl.getSelectionModel().getSelectedItem();
        deleteAppointment(target);
    }
    
    //launches the add appointment screen
    @FXML
    public void addHandler(ActionEvent event)
    {
        app.addAppointment(person);
    }
    
    //launches the edit appointment screen and passes the selected appointment
    @FXML
    public void editHandler(ActionEvent event)
    {
        appointment target = apptTbl.getSelectionModel().getSelectedItem();
        app.launchEditAppointmentScreen(person, target);
    }
    
    //Deletes the appointment from the database
    public void deleteAppointment(appointment target)
    {
        try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
        stmnt=conn.createStatement();
        
        int appointmentID = target.getAppointmentId();
        System.out.println(appointmentID);
        
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM APPOINTMENTS WHERE appointmentID= ?");
        prepared.setInt(1, appointmentID);
        prepared.execute();
        
        
        }catch(Exception e){e.printStackTrace();}
        app.launchAppointmentScreen(person);
    }
    
    //launches the customer screen
    @FXML
    public void customerView(ActionEvent event)
    {
        app.launchCustomerScreen(person);
    }
    
    //Launches the report screen
    @FXML
    public void reportView(ActionEvent event)
    {
        app.launchReportScreen(person);
    }
    
    //Launches Caladar View
    @FXML
    public void calandarView(ActionEvent event)
    {
        app.launchCalandarScreen(person);
    }
    
            
    
    
     
    
}
