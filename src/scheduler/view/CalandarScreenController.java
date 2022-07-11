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
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import scheduler.Scheduler;
import scheduler.model.appointment;
import scheduler.model.user;

/**
 * FXML Controller class
 *
 * @author Misker
 */
public class CalandarScreenController implements Initializable 
{
    //Table elements for the two tables
    @FXML 
    private TableView<appointment> monthTbl;
    @FXML 
    private TableView<appointment> weekTbl;
    @FXML
    private TableColumn<appointment, String> contactClmnM;
    @FXML
    private TableColumn<appointment, String> startClmnM;
    @FXML
    private TableColumn<appointment, Date> endClmnM;
    @FXML
    private TableColumn<appointment, String> typeClmnM;
    @FXML
    private TableColumn<appointment, String> descriptionClmnM;
    @FXML
    private TableColumn<appointment, String> contactClmnW;
    @FXML
    private TableColumn<appointment, String> startClmnW;
    @FXML
    private TableColumn<appointment, Date> endClmnW;
    @FXML
    private TableColumn<appointment, String> typeClmnW;
    @FXML
    private TableColumn<appointment, String> descriptionClmnW;
    @FXML
    private Button monthBtn;
    @FXML
    private Button weekBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Label weekLbl;
    @FXML
    private Label monthLbl;
    
    ObservableList<appointment> oblist;
    ObservableList<appointment> oblist2;

    private Scheduler app;
    private user person;
    private Connection conn;
    private Statement stmnt;
    private boolean byWeek = true;
    private ZoneId zone = ZoneId.systemDefault();
    
    
    
    
    //initizlize the appointment screen for this month    
    public void showCalandar(Scheduler app, user person)
    {
        this.app = app;
        this.person = person;
        
        contactClmnM.setCellValueFactory(new PropertyValueFactory<>("contact"));
        startClmnM.setCellValueFactory(new PropertyValueFactory<>("start"));
        endClmnM.setCellValueFactory(new PropertyValueFactory<>("end"));
        typeClmnM.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionClmnM.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    
        oblist= FXCollections.observableArrayList(); 
        
       
        //gets the current month
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
     
        //Checks the database and adds the appointments to the observable list
        try{
                conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
                stmnt=conn.createStatement();
                
                //gets the appointments and matches the start date with current month
                //PreparedStatement statement = conn.prepareStatement("SELECT appointmentID, contact, start, end, type, description FROM APPOINTMENTS WHERE MONTH(start) =" + month);
                
                ResultSet rs = stmnt.executeQuery("SELECT appointmentID, contact, start, end, type, description FROM APPOINTMENTS WHERE MONTH(start) =" + month);
                
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
       
        
        monthTbl.getItems().setAll(oblist); //populates the table  
        byWeek();
        
    }
    //initizlize the appointment screen for this week   
    public void byWeek()
    {
        contactClmnW.setCellValueFactory(new PropertyValueFactory<>("contact"));
        startClmnW.setCellValueFactory(new PropertyValueFactory<>("start"));
        endClmnW.setCellValueFactory(new PropertyValueFactory<>("end"));
        typeClmnW.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionClmnW.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        oblist2= FXCollections.observableArrayList(); 
        
        //Gets the current week number
        int weekNumber = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1;
        
        //Checks the database and adds the appointments to the observable list
        try{
                conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
                stmnt=conn.createStatement();
                
                //gets the appointments and matches the start date with current week number
                
                ResultSet rs = stmnt.executeQuery("SELECT appointmentID, contact, start, end, type, description FROM APPOINTMENTS WHERE WEEK(start) = " + weekNumber);
                
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
                    
                   
                    oblist2.add(new appointment(contact, st, en, type, description, appointmentID));
                    
                }
            }catch(SQLException e)
            {
                e.printStackTrace();
            }catch(Exception e)
            {
                e.printStackTrace();
            }    
        
        weekTbl.getItems().setAll(oblist2); //populates the second table
    }
    
    @FXML
    public void monthButton(ActionEvent event) //when This Month button is clicked it sets the table and lable of the week table invisible
    {
        weekTbl.setVisible(false);
        weekLbl.setVisible(false);
        monthLbl.setVisible(true);
        monthLbl.setVisible(true);
    }
    @FXML
    public void weekButton(ActionEvent event)  //when This Week button is clicked it sets the table and lable of the month table invisible
    {
        //monthTbl.setVisible(false);
        monthLbl.setVisible(false);
        weekTbl.setVisible(true);
        weekLbl.setVisible(true);
    }
    @FXML
    public void backButton(ActionEvent event) //Back button to go back to appointments screen
    {
        app.launchAppointmentScreen(person);
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
