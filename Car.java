/*
 * CSE 205: 17566 / M W 4:35PM - 5:50PM 
 * Assignment: Assignment 6
 * Authors: Randall Knutson 1212366088
 * Description: Class defining car as a Vehicle.
 */
public class Car extends Vehicle
{
	//car constructor
    public Car()
    {
        super(1, false);
    }
    
    //get the amount of time needed for car to pass through intersection
    public int getTime()
    {
        return time;
    }
    
    //tells whether car is an emergency vehicle
    public boolean getIsEmergency()
    {
        return isEmergency;
    }
}