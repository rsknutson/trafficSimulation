/*
 * CSE 205: 17566 / M W 4:35PM - 5:50PM 
 * Assignment: Assignment 6
 * Authors: Randall Knutson 1212366088
 * Description: Class defining Truck as a Vehicle.
 */
public class Truck extends Vehicle
{
	//truck constructor
    public Truck()
    {
        super(2, false);
    }
    
  //get the amount of time needed for truck to pass through intersection
    public int getTime()
    {
        return time;
    }
    
  //tells whether truck is an emergency vehicle
    public boolean getIsEmergency()
    {
        return isEmergency;
    }
}