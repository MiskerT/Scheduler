/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

/**
 *
 * @author Misker
 */
public class report
{

private String month;
private String type;
private String total;
    
public report()
{

}
public report(String month, String type, String total)
{
    this.month = month;
    this.type = type;
    this.total = total;
}
public report(String type, String total)
{
    this.month = month;
    this.type = type;
    this.total = total;
}

public void setMonth(String month)
{
    this.month = month;
}
public void setType(String type)
{
    this.type = type;
}
public void setTotal(String total)
{
    this.total = total;
}

public String getMonth()
{
    return this.month;
}
public String getType()
{
    return this.type;
}
public String getTotal()
{
    return this.total;
}
    
}
