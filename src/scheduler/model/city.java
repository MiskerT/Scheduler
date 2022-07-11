/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

import java.sql.Timestamp;

/**
 *
 * @author Misker
 */
public class city extends country{

private int cityId;
private String city;
private int countryId;
private String createdBy;
private Timestamp lastUpdate;
private String lastUpdateBy;


public city()
{
}

public city(int cityId)
{
    this.cityId = cityId;
}

public city(String city)
{
    this.city = city;
}

public city(int cityId, int countryId)
{
    this.cityId = cityId;
    this.countryId = countryId;
}

public city(int cityId, int countryId, String city)
{
    this.cityId = cityId;
    this.countryId = countryId;
    this.city = city;
}

public void setCity(String city)
{
    this.city = city;
}
public void setCityId(int cityId)
{
    this.cityId = cityId;
}
public void setCountryId(int countryId)
{
    this.countryId = countryId;
}
public void setCreatedBy(String createdBy)
{
    this.createdBy = createdBy;
}
public void setLastUpdate(Timestamp lastUpdate)
{
    this.lastUpdate = lastUpdate;
}
public void setLastUpdateBy(String lastUpdateBy)
{
    this.lastUpdateBy = lastUpdateBy;
}

public String getCity()
{
    return city;
}
public int getCityId()
{
    return cityId;
}
public int getCountryId()
{
     return countryId;
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
