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
public class EditCustomerScreenController implements Initializable {

    private Scheduler app;
    private user person;
    
    @FXML
    private TextField cIDField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox<String> cityBox;
    @FXML
    private TextField phoneField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    
    private String customerID;
    private String customerName;
    private String address;
    private String phone;
    private int cityID;
    private Connection conn;
    private Statement stmnt;
  
    //initializes the edit customer screen and populates the fields from the selected customer
    public void editScreen(Scheduler app, customer target)
    {
        this.app = app;
        this.person = person;
        cityBox.getItems().addAll("Phoenix, Arizona","New York, New York","London, England");
        
        cIDField.setText(target.getCustomerID());
        nameField.setText(target.getCustomerName());
        addressField.setText(target.getAddress());
        phoneField.setText(target.getPhone());
        
        String city = target.getCity();
        switch(city)
        {
            case "Phoenix, Arizona": cityBox.getSelectionModel().select("Phoenix, Arizona"); break;
            case "New York, New York": cityBox.getSelectionModel().select("New York, New York"); break;
            case "London, England": cityBox.getSelectionModel().select("London, England"); break;
        }
        
        //Lambda expression to handle the cancel button to go to customer screen without having to create a separate handler.
        cancelBtn.setOnAction((ActionEvent e) -> { app.launchCustomerScreen(person); });  
    }
    
    //handles the save button. Updates the seleced customer in the database
    @FXML 
    public void saveHandler(ActionEvent event)
    {
        cityID = 0;
        customerID = cIDField.getText();
        customerName = nameField.getText();
        address = addressField.getText();
        phone= phoneField.getText();
        String city = cityBox.getSelectionModel().getSelectedItem();
        switch(city)
        {
            case "Phoenix, Arizona": cityID  = 1; break;
            case "New York, New York": cityID = 2; break;
            case "London, England": cityID = 3; break;
        }
        
        //if statement verifies customer info before adding it to the database
        if(verifyData(customerName, address, cityID ,phone))
        {
            try{
            conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
            stmnt = conn.createStatement();
            
            //updates the customer name in database
            PreparedStatement prepared = conn.prepareStatement("UPDATE CUSTOMERS SET customerName= ? WHERE customerID="+ customerID);
            prepared.setString(1, customerName);
            prepared.execute();

            ResultSet rs = stmnt.executeQuery("SELECT addressID FROM CUSTOMERS WHERE customerID=" + customerID);
            String id = "";
         
            while(rs.next()){
                String addressID = rs.getString("addressID");
                id = addressID;
            }
            //updates the address of customer in database
            PreparedStatement prepared2 = conn.prepareStatement("UPDATE ADDRESS SET address = ? , phone = ?, cityID = ? WHERE addressID=" + id);
            prepared2.setString(1, address);
            prepared2.setString(2, phone);
            prepared2.setInt(3, cityID);
            prepared2.execute();

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
