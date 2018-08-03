/*
 * CSE 205: 17566 / M W 4:35PM - 5:50PM 
 * Assignment: Assignment 6
 * Authors: Randall Knutson 1212366088
 * Description: Class defining Emergency as a Vehicle.
 */
public class Emergency extends Vehicle
{
	//emergency vehicle constructor
    public Emergency()
    {
        super(4,true);
    }
    
  //get the amount of time needed for emergency vehicle to pass through intersection
     public int getTime()
    {
        return time;
    }
    
   //tells whether emergency vehicle is an emergency vehicle
    public boolean getIsEmergency()
    {
        return isEmergency;
    }
}