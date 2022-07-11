/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scheduler.view.LoginScreenController;
import javafx.scene.layout.AnchorPane;
import scheduler.view.UserScreenController;
import scheduler.model.user;
import scheduler.view.AppointmentScreenController;
import scheduler.model.appointment;
import scheduler.model.customer;
import scheduler.view.AddCustomerScreenController;
import scheduler.view.CalandarScreenController;
import scheduler.view.CustomerScreenController;
import scheduler.view.EditAppointmentScreenController;
import scheduler.view.EditCustomerScreenController;
import scheduler.view.NewAppointmentController;
import scheduler.view.ReportScreenController;


/**
 *
 * @author Misker
 */
public class Scheduler extends Application {
    private Stage mainPage; 
    
    @Override
    public void start(Stage stage) {
       
        this.mainPage = stage;
        loginPage();
    }

   //launches the login page  
    private void loginPage()
    {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(getClass().getResource("/scheduler/view/LoginScreen.fxml"));
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Scheduler.class.getResource("/scheduler/view/LoginScreen.fxml"));
        Parent parent =(Parent) loader.load();
        
        LoginScreenController controller = loader.getController();
        controller.startLogin(this);
        
        Scene scene = new Scene(parent);
        mainPage.setScene(scene);
        mainPage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Launches the user screen
    public void launchUserScreen(user person)
    {
        try{
        FXMLLoader root = new FXMLLoader();
        root.setLocation(Scheduler.class.getResource("/scheduler/view/UserScreen.fxml"));
        Parent parent =(Parent) root.load();
        
        UserScreenController controller = root.getController();
        controller.startUserScreen(this, person);
        
        Scene scene = new Scene(parent);
        mainPage.setScene(scene);
        mainPage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Launches the Appointmnent screen and passes the user
    public void launchAppointmentScreen(user person)
    {
        try{
        FXMLLoader root = new FXMLLoader();
        root.setLocation(Scheduler.class.getResource("/scheduler/view/AppointmentScreen.fxml"));
        Parent parent =(Parent) root.load();
        
        AppointmentScreenController controller = root.getController();
        controller.showAppointments(this, person);
        
        Scene scene = new Scene(parent);
        mainPage.setScene(scene);
        mainPage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Launches the customer screen 
    public void launchCustomerScreen(user person)
    {
        try{
        FXMLLoader root = new FXMLLoader();
        root.setLocation(Scheduler.class.getResource("/scheduler/view/CustomerScreen.fxml"));
        Parent parent =(Parent) root.load();
        
        CustomerScreenController controller = root.getController();
        controller.customerView(this, person);
        
        Scene scene = new Scene(parent);
        mainPage.setScene(scene);
        mainPage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Launches the Add Customer screen 
    public void launchAddCustomerScreen(user person)
    {
        try{
        FXMLLoader root = new FXMLLoader();
        root.setLocation(Scheduler.class.getResource("/scheduler/view/AddCustomerScreen.fxml"));
        Parent parent =(Parent) root.load();
        
        AddCustomerScreenController controller = root.getController();
        controller.addCustomer(this, person);
        
        Scene scene = new Scene(parent);
        mainPage.setScene(scene);
        mainPage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Launches the edit customer screen and passes the user and the target customer
    public void launchEditCustomerScreen(user person, customer target)
    {
        try{
        FXMLLoader root = new FXMLLoader();
        root.setLocation(Scheduler.class.getResource("/scheduler/view/EditCustomerScreen.fxml"));
        System.out.println("---------------" + root.getLocation() +"-----------------");
        Parent parent =(Parent) root.load();
        
        EditCustomerScreenController controller = root.getController();
        controller.editScreen(this, target);
        
        Scene scene = new Scene(parent);
        mainPage.setScene(scene);
        mainPage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Launches the edit appointment screen and passes the user and the target appointment
    public void launchEditAppointmentScreen(user person, appointment target)
    {
        try{
        FXMLLoader root = new FXMLLoader();
        root.setLocation(Scheduler.class.getResource("/scheduler/view/EditAppointmentScreen.fxml"));
        Parent parent =(Parent) root.load();
        
        EditAppointmentScreenController controller = root.getController();
        controller.editAppointment(this, person, target);
        
        Scene scene = new Scene(parent);
        mainPage.setScene(scene);
        mainPage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Launches the report screen
    public void launchReportScreen(user person)
    {
        try{
        FXMLLoader root = new FXMLLoader();
        root.setLocation(Scheduler.class.getResource("/scheduler/view/ReportScreen.fxml"));
        Parent parent =(Parent) root.load();
        
        ReportScreenController controller = root.getController();
        controller.bySchedule(this, person);
        
        Scene scene = new Scene(parent);
        mainPage.setScene(scene);
        mainPage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Launches the add appointment screen
    public void addAppointment(user person)
    {
        try
        {
            FXMLLoader root = new FXMLLoader(); 
            root.setLocation(getClass().getResource("/scheduler/view/newAppointment.fxml"));
            Parent parent= (Parent)root.load();
            
            NewAppointmentController controller = root.getController();
            controller.newAppointment(this, person);
            
            Scene scene = new Scene(parent);
            mainPage.setScene(scene);
            mainPage.show();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //Launches the calandar screen
    public void launchCalandarScreen(user person)
    {
        try
        {
            FXMLLoader root = new FXMLLoader(); 
            root.setLocation(getClass().getResource("/scheduler/view/CalandarScreen.fxml"));
            Parent parent= (Parent)root.load();
            
            CalandarScreenController controller = root.getController();
            controller.showCalandar(this, person);
            
            Scene scene = new Scene(parent);
            mainPage.setScene(scene);
            mainPage.show();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
