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
public class address extends city{
    
private int addressId;
private String address;
private String address2;
private int cityId;
private String postalCode;
private String phone;
private Date createDate;
private String createdBy;
private Timestamp lastUpdate;
private String lastUpdateBy;


public address()
{
}
public address(int addressId, String address)
{
    this.addressId = addressId;
    this.address = address;
}
public address(int addressId, String address, String address2)
{
    this.addressId = addressId;
    this.address = address;
    this.address2 = address2;
}

public void setAddressId(int addressId)
{
    this.addressId = addressId;
}
public void setAddress(String address)
{
    this.address = address;
}
public void setAddress2(String address2)
{
    this.address2 = address2;
}
public void setPostalCode(String postalCode)
{
    this.postalCode = postalCode;
}
public void setPhone(String phone)
{
    this.phone = phone;
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

public int getAddressId()
{
    return addressId;
}
public String getAddress()
{
    return address;
}
public String getAddress2()
{
    return address2;
}
public int getCityId()
{
    return cityId;
}
public String postalCode()
{
    return postalCode;
}
public String getPhone()
{
    return phone;
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
