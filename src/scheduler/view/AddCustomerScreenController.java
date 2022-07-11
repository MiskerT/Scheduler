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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import scheduler.Scheduler;
import scheduler.model.customer;
import scheduler.model.user;


/**
 * FXML Controller class
 *
 * @author Misker
 */
public class AddCustomerScreenController implements Initializable {
    
@FXML
private TextField idField;
@FXML
private TextField nameField;
@FXML
private TextField addressField;
@FXML
private TextField cityField;
@FXML
private TextField countryField;
@FXML
private TextField phoneField;
@FXML
private Button saveBtn;
@FXML
private Button cancelBtn;
@FXML
private ComboBox<String> cityBox;
@FXML
private ComboBox countryBox;
        

private Scheduler app;
private user person;
private Connection conn;
private Connection conn2;
private Statement stmnt;
private Statement stmnt2;


//initizlize addCustomer screen
public void addCustomer(Scheduler app, user person)
{
    this.app = app;
    this.person = person;
    cityBox.getItems().addAll("Phoenix", "New York", "London");
    
    //Lambda expression to handle the cancel button to go to customer screen without having to create a separate handler.
    cancelBtn.setOnAction((ActionEvent e) -> { app.launchCustomerScreen(person); });  
       
}

//Handles the save button 
@FXML
public void saveCustomer(ActionEvent event)
{
    String name = nameField.getText();
    String address = addressField.getText();
    String city = cityBox.getSelectionModel().getSelectedItem().toString();
    int cityID = 0;
        switch(city){
            case "Phoenix": cityID = 1; break;
            case "New York": cityID = 2; break;
            case "London": cityID = 3; break;
            default: System.out.println("None selected");
        }
    
    String phone = phoneField.getText();
    
    //if statement verifies customer info before adding it to the database
    if(verifyData(name, address, cityID, phone))
    {
        try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
        stmnt = conn.createStatement();

        ResultSet rs = stmnt.executeQuery("SELECT * FROM ADDRESS");
        int highest = 0;
        while(rs.next())
        { 
            int record = rs.getInt("addressID");  
            if(record > highest)
            {
                highest = record;
            }
        }

        int addressID = highest + 1;

        ResultSet rs2 = stmnt.executeQuery("SELECT * FROM CUSTOMERS");
        int highest2 = 0;
        while(rs2.next()){ int record = rs2.getInt("customerID"); if(record > highest2){highest2 = record;};}
        int customerID = highest2 +1;
        rs2.close();

        System.out.println("Recent customer ID = " + customerID);

        PreparedStatement prepared = conn.prepareStatement("INSERT INTO ADDRESS (addressID, address, cityID, phone) VALUES (?,?,?,?)");
        prepared.setInt(1, addressID);
        prepared.setString(2, address);
        prepared.setInt(3, cityID);
        prepared.setString(4, phone);
        prepared.execute();
        prepared.close();

        PreparedStatement prepared2 = conn.prepareStatement("INSERT INTO CUSTOMERS (customerID, customerName, addressID) VALUES (?,?,?)");
        prepared2.setInt(1, customerID);
        prepared2.setString(2, name);
        prepared2.setInt(3, addressID);
        prepared2.execute();
        prepared2.close();

        }catch(Exception e){e.printStackTrace();}

    }
    else
    {
       //alert box to inform the user that it failed to add info
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("Confirmation Dialog");
       alert.setHeaderText("There was something wrong with the data you entered. Please make sure the fields are not empty and that you use the proper format.");
       alert.showAndWait();
    }
    app.launchCustomerScreen(person);
    
}
//verifies that the data entered is appropriate
public boolean verifyData(String name, String address, int cityID, String phone)
{
    boolean pass = true;
    //checks if the fields are empty
    if(name.isEmpty() || address.isEmpty() || cityID == 0 || phone.isEmpty())
    {
        pass = false;
    }
    //checks if name has digits
    if(name.matches(".*\\d+.*"))
    {
        pass = false;
    }
    //checks if phone number contains letters
    if(!phone.matches(".*\\d+.*"))
    {
        pass = false;
    }
    
    return pass;
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
