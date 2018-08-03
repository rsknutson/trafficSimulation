/*
 * CSE 205: 17566 / M W 4:35PM - 5:50PM 
 * Assignment: Assignment 6
 * Authors: Randall Knutson 1212366088
 * Description: Abstract class defining Vehicle.
 */
public abstract class Vehicle
{
    int time;
    boolean isEmergency;
    
    //vehicle default constructor
    public Vehicle()
    {
        time = 0;
        isEmergency = false;
    }
    
    //vehicle constructor with parameters
    public Vehicle(int time, boolean isEmergency)
    {
        this.time = time;
        this.isEmergency = isEmergency;
    }
    
    public abstract int getTime();
    public abstract boolean getIsEmergency();
    
    
}