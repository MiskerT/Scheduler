/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Misker
 */
public class customer extends address{
 
private String customerID;
private String customerName;
private int addressID;
private int active;
private Date createDate;
private String createdBy;
private Timestamp lastUpdate;
private String lastUpdateBy;

public customer()
{
}
public customer(String customerID)
{
    this.customerID = customerID;
}

public customer(String customerID, String customerName)
{
    this.customerID = customerID;
    this.customerName = customerName;
}
public customer(String customerID, String customerName, int addressID)
{
    this.customerID = customerID;
    this.customerName = customerName;
    this.addressID = addressID;
}

public void setCustomerName(String customerName)
{
    this.customerName = customerName;
}
public void setCustomerID(String customerId)
{
    this.customerID = customerId;
}
public void setAddressID(int addressId)
{
    this.addressID = addressId;
}
public void setActive(int active)
{
    this.active = active;
}
public void setCreatedBy(String createdBy)
{
    this.createdBy = createdBy;
}
public void setCreateDate(Date createDate)
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

public String getCustomerName()
{
    return customerName;
}
public String getCustomerID()
{
    return customerID;
}
public String customerName()
{
    return customerName;
}
public int getAddressID()
{
    return addressID;
}
public int getActive()
{
    return active;
}
public Date getCreateDate()
{
    return createDate;
}
public String getCreatedBy()
{
    return createdBy;
}
public Timestamp getLastUpdate()
{
    return lastUpdate;
}
public String getLastUpdateBy()
{
    return lastUpdateBy;
}
        
    
}
