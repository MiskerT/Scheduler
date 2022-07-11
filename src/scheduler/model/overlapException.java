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
class overlapException extends Exception 
{
    //Constructor thats going to pass a custom error message.
    overlapException(String s)
    {
        super(s);
    }
}

class CustomException
{
    //checks if the appointment time is in the timeframe of another appointment
    static void validate(Timestamp start, Timestamp end, Timestamp start2, Timestamp end2) throws overlapException
    {
        if(start2.after(start) && start2.before(end))
            throw new overlapException("There is already an appointment scheduled during that time. Please select a different time.");
        if(end2.after(start) && end2.before(end))
            throw new overlapException("There is already an appointment scheduled during that time. Please select a different time.");
    }
}
