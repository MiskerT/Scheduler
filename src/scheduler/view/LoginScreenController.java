/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import scheduler.*;
import java.net.URL;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scheduler.model.user;

/**
 * FXML Controller class
 *
 * @author Misker
 */
public class LoginScreenController implements Initializable {
@FXML
private Label title;
@FXML
private Label usernameLbl;
@FXML
private Label pwdLbl;
@FXML 
private Button loginBtn;
@FXML
private Button cancelBtn;
@FXML
private TextField usernameTxt;
@FXML
private PasswordField pwdTxt;
@FXML
private Label errorMsg;

private Scheduler app;
private Connection conn;
private Statement stmnt;
private Path loginFile = Paths.get("./src/scheduler/login.txt");
private File file;
private Path loginFile2 = Paths.get("C:/Users/Misker/Documents/NetBeansProjects/Scheduler/src/scheduler/logins.txt");

public LoginScreenController()
{
}

//gets location of the user to set language
void getLocation(){
        ResourceBundle rb = ResourceBundle.getBundle("login", Locale.getDefault());
        title.setText(rb.getString("title"));
        usernameLbl.setText(rb.getString("username"));
        pwdLbl.setText(rb.getString("password"));
        loginBtn.setText(rb.getString("login"));
        cancelBtn.setText(rb.getString("cancel"));
        
        file = new File(loginFile.toAbsolutePath().normalize().toString());
        
        //Lambda expression used to handle the cancel button. It closes the screen without me having to add a separate handler.
        cancelBtn.setOnAction((ActionEvent e) -> { Platform.exit(); });
    }

//gets the username and password and calls verify 
@FXML
public void loginBtnHandler(ActionEvent event)
{
    String username = usernameTxt.getText();
    String pass = pwdTxt.getText();
    verify(username, pass);
    
}
@FXML
public void cancelBtnHandler(ActionEvent event)
{
    try{
    conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
    
    stmnt =  conn.createStatement();
    ResultSet rs = stmnt.executeQuery("SELECT * FROM USERS");
    
        while(rs.next())
        {
            int x = rs.getInt("userID");
            String c = rs.getString("userName");
            String n = rs.getString("password");

            System.out.println(x + "----"+ c + "----" + n);
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    
}

//Initilize the login screen
@FXML
public void startLogin(Scheduler app)
{
    this.app = app;
    getLocation();              
}

//verifies the username and password
@FXML
public void verify(String userName, String password)
{
    ResourceBundle rb = ResourceBundle.getBundle("login", Locale.getDefault());
    //Checks if username or password are empty
    if(userName.equals("") || password.equals(""))
    {
        //errorMsg.setVisible(true);
        errorMsg.setText(rb.getString("empty"));
    
    }
    else
    {
       try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");

        stmnt =  conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM USERS");

            while(rs.next())
            {
                int x = rs.getInt("userID");
                String c = rs.getString("userName");
                String n = rs.getString("password");

                if(c.equals(userName) && n.equals(password))
                {                  
                    user person = new user(x, c , n);
                    
                    recordLogin(c);            
                    app.launchAppointmentScreen(person);
         
                }
                else
                {
                    errorMsg.setText(rb.getString("wrong"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
    
    
}


//Checks if the text file exists and writes login info 
public void recordLogin(String name)
{
    Timestamp timeSt = new Timestamp(System.currentTimeMillis());
    try{
        if(!file.exists())
        {
            Files.createFile(loginFile);
        }
        else
        {
            BufferedWriter fw = new BufferedWriter(new FileWriter(loginFile.toAbsolutePath().normalize().toString(), true));
            fw.write("\nThe user {" +name + "} logged in at {" + timeSt +"}");
            fw.close();
           
        }
    
    }catch(IOException e){e.printStackTrace();}
}
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
