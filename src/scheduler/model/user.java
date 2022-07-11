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
public class user {
    
private int userId;
private String userName;
private String password;
private int active;
private Date createDate;
private String createdBy;
private Timestamp lastUpdate;
private String lastUpdateBy;

public user()
{
}
public user(int userId)
{
    this.userId = userId;
}
public user(int userId, String userName, String password)
{
    this.userId = userId;
    this.userName = userName;
    this.password = password;
}

public void setUserId(int userId)
{
    this.userId = userId;
}
public void setUserName(String userName)
{
    this.userName = userName;
}
public void setPassword(String password)
{
    this.password = password;
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

public int getUserId()
{
    return userId;
}
public String getUserName()
{
    return userName;
}
public String getPassword()
{
    return password;
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
