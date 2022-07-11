/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
/**
 *
 * @author Misker
 */
public class country {

private int countryId;
private String country;
private Date createDate;
private String createdBy;
private Timestamp lastUpdate;
private String lastUpdateBy;

public country()
{
}
public country(String country)
{
    this.country = country;
}
public country(int countryId)
{
    this.countryId = countryId;           
}
public country(int countryId, String country)
{
    this.country = country;
    this.countryId = countryId;
}

public void setCountryId(int countryId)
{
    this.countryId = countryId;
}
public void setCountry(String country)
{
    this.country = country;
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

public String getCountry()
{
    return country;
}
public int getCountryId()
{
    return countryId;
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
