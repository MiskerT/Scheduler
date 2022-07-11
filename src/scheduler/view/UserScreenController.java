/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import scheduler.Scheduler;
import scheduler.model.user;

/**
 * FXML Controller class
 *
 * @author Misker
 */
public class UserScreenController {
@FXML 
private MenuItem appt;
@FXML 
private MenuItem customerBtn;

private Scheduler app;
private user person;

public void startUserScreen(Scheduler app, user person)
{
    this.app = app;
    this.person = person;
}

@FXML
public void appointmentBtn(ActionEvent event)
{
    app.launchAppointmentScreen(person);
}
@FXML
public void customerBtn(ActionEvent event)
{
    app.launchCustomerScreen(person);
}
    
}
