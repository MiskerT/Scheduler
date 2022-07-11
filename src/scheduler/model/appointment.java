/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

import com.mysql.cj.conf.StringProperty;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author Misker
 */
public class appointment {

private int appointmentId;
private int customerId;
private int userId;
private String title;
private String description;
private String location;
private String contact;
private String type;
private String url;
private Timestamp startTimestamp;
private Timestamp endTimestamp;
private String start;
private StringProperty startString;
private StringProperty endString;
private String end;
private java.sql.Date createDate;
private String createdBy;
private Timestamp lastUpdate;
private String lastUpdateBy;
private ObjectProperty<LocalDate> date;
private String cheese;

public appointment()
{
}

public appointment(String contact, String start, String endDate, String type, String description, int appointmentID)
{
    this.contact = contact;
    this.start = start;
    this.cheese = cheese;
    this.end = endDate;
    this.type = type;
    this.description = description;
    this.appointmentId = appointmentID;
}

public void setAppointmentId(int appointmentId)
{
    this.appointmentId = appointmentId;
}
public void setCustomerId(int userId)
{
    this.userId = userId;
}
public void setTitle(String title) 
 {
        this.title = title;
    }
public void setDescription(String description)
{
    this.description = description;
}
public void setLocation(String location)
{
    this.location = location;
}
public void setContact(String contact) 
{
    this.contact = contact;
}
public void setUrl(String url) 
{
    this.url = url;
}
public void setStartTimestamp(Timestamp startTimestamp) 
{
    this.startTimestamp = startTimestamp;
}
public void setEndTimestamp(Timestamp endTimestamp)
{
    this.endTimestamp = endTimestamp;
}
public void setStart(String start) 
{
    this.start = start;
}
public void setEnd(String end) 
{
    this.end = end;
}
public void setCreatedBy(String createdBy)
{
    this.createdBy = createdBy;
}
public void setCreateDate(java.sql.Date createDate)
{
    this.createDate = createDate;
}
public void setLastUpdate(Timestamp lastUpdate)
{
    this.lastUpdate = lastUpdate;
}
public void setLastUpdateBy(String lastUpdateBy)
{
    this.lastUpdateBy = lastUpdateBy;
}
public void setType(String type)
{
    this.type = type;
}
//Getters

public int getAppointmentId() 
{
    return this.appointmentId;
}
public int getCustomerId() 
{
    return this.customerId;
}
public String getTitle() 
{
    return this.title;
}
public String getDescription()
{
    return this.description;
}
public String getLocation()
{
    return this.location;
}
public String getContact() 
{
    return this.contact;
}
public String getUrl() 
{
    return this.url;
}
public Timestamp getStartTimestamp() 
{
    return this.startTimestamp;
}
public Timestamp getEndTimestamp() 
{
    return this.endTimestamp;
}
public String getStart() 
{
    return this.start;
}
public String getEnd() 
{
    return this.end;
}
public String getType()
{
    return this.type;
}



public String toString()
{
    return "start: " + this.start + "   end: " + this.end ;
}

}