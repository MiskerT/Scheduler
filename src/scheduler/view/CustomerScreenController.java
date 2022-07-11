/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import scheduler.model.customer;
import scheduler.model.user;

/**
 * FXML Controller class
 *
 * @author Misker
 */
public class CustomerScreenController implements Initializable {
    @FXML
    private TableView<customer> customerTbl;
    @FXML
    private TableColumn<customer, String> IDClmn;
    @FXML
    private TableColumn<customer, String> nameClmn;
    @FXML
    private TableColumn<customer, String> addClmn;
    @FXML
    private TableColumn<customer, String> cityClmn;
    @FXML
    private TableColumn<customer, String> countryClmn;
    @FXML
    private TableColumn<customer, String> phoneClmn; 
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;    
    @FXML
    private Button deleteBtn;
    @FXML
    private Button apptBtn;
    @FXML
    private Button reportBtn;
    
    private Scheduler app;
    private user person;
    private Connection conn;
    private Statement stmnt;
    private Statement stmnt2;
    
    private ObservableList<customer> cusList;
    
    //initializes the customer screen
    public void customerView(Scheduler app, user person)
    {
        this.app = app;
        this.person = person;
        
        IDClmn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameClmn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addClmn.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityClmn.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryClmn.setCellValueFactory(new PropertyValueFactory<>("country"));
        phoneClmn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        //connects to the database and adds the customers to an observable list
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
            stmnt = conn.createStatement();
            stmnt2 = conn.createStatement();
            cusList = FXCollections.observableArrayList();
            
            
            ResultSet rs = stmnt.executeQuery("SELECT customerID, customerName, addressID FROM CUSTOMERS"); 
            while(rs.next())
            {
                String cID, cName, cAddress, cCity, cCountry, cPhone;
                cID = cName= cAddress = cCity = cCountry = cPhone = "";
                String customerID = rs.getString("customerID");
                String customerName = rs.getString("customerName"); 
                String addressID = rs.getString("addressID");                
                    
                    ResultSet rs2 = stmnt2.executeQuery("SELECT address, phone, cityID FROM ADDRESS WHERE addressID =" + addressID);
                    while(rs2.next())
                    {
                        String address = rs2.getString("address");
                        cAddress = address;
                        String phone = rs2.getString("phone");
                        cPhone = phone;
                        int cityID = rs2.getInt("cityID");      
                        if(cityID == 1)
                        {
                            cCity = "Phoenix, Arizona";
                        }
                        else if(cityID == 2)
                        {
                            cCity = "New York, New York";
                        }
                        else if(cityID == 3)
                        {
                            cCity = "London, England";
                        }
                        
                    }
                    rs2.close();
                if(cCity.equals("Phoenix, Arizona") || cCity.equals("New York, New York"))
                {
                    cCountry = "USA";
                }
                else if(cCity.equals("London, England")) 
                {
                    cCountry = "England";
                }
                
                customer cus = new customer();
                cus.setCustomerID(customerID);
                cus.setCustomerName(customerName);
                cus.setAddress(cAddress);
                cus.setCity(cCity);
                cus.setCountry(cCountry);
                cus.setPhone(cPhone);
                cusList.add(cus); //adds customer to observable list
                System.out.println();
            }
            
        }catch(Exception e){e.printStackTrace();}
        
        
        customerTbl.getItems().setAll(cusList); //populates the customer table
    }
    
    //launches the add customer screen
    @FXML
    public void addCustomer(ActionEvent event)
    {
        app.launchAddCustomerScreen(person);
    }
    
    //launches the edit customer screen and passes the targeted customer
    @FXML
    public void editCustomer(ActionEvent event)
    {
        customer target = customerTbl.getSelectionModel().getSelectedItem();
        app.launchEditCustomerScreen(person, target);
    }
    
    //deletes the customer from database
    @FXML
    public void deleteCustomer(ActionEvent event)
    {
        customer target = customerTbl.getSelectionModel().getSelectedItem();
        try{
        conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U05bjE", "U05bjE", "53688457042");
        stmnt = conn.createStatement();
        String customerID = target.getCustomerID();
        stmnt.execute("DELETE FROM CUSTOMERS WHERE customerID=" + customerID);
        }catch(Exception e){e.printStackTrace();}
    }
    
    //launches the appointment screen
    @FXML
    public void apptView(ActionEvent event)
    {
        app.launchAppointmentScreen(person);
    }
    
    //Launches the report screen
    @FXML
    public void reportView(ActionEvent event)
    {
        app.launchReportScreen(person);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
