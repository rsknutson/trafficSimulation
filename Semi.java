/*
 * CSE 205: 17566 / M W 4:35PM - 5:50PM 
 * Assignment: Assignment 6
 * Authors: Randall Knutson 1212366088
 * Description: Class defining Semi as a Vehicle.
 */
public class Semi extends Vehicle
{
	//Semi constructor
    public Semi()
    {
        super(3,false);
    }
    
  //get the amount of time needed for semi to pass through intersection
     public int getTime()
    {
        return time;
    }
    
   //tells whether Semi is an emergency vehicle
    public boolean getIsEmergency()
    {
        return isEmergency;
    }
}