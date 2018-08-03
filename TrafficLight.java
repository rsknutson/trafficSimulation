/*
 * CSE 205: 17566 / M W 4:35PM - 5:50PM 
 * Assignment: Assignment 6
 * Authors: Randall Knutson 1212366088
 * Description: Class creating the traffic light intersection and methods involved in running the simulation.
 */
import java.util.Arrays;

public class TrafficLight
{
    public static Vehicle[] north = new Vehicle[5];
    public Vehicle[] east = new Vehicle[5];
    public Vehicle[] south = new Vehicle[5];
    public Vehicle[] west = new Vehicle[5];

    public boolean running = true;
    public boolean isNSGreen = true;
    public boolean isEWGreen = false;

    public final int time = 4;
    public int currentTime = 4;

    //constructor for TrafficLight, puts random vehicles at each direction
    public TrafficLight()
    {
        for(int i = 0; i <= (int)(Math.random() * 5); i++) //random number 1-5 for each array
        {
            north[i] = randomVehicle();
        }

        for(int i = 0; i <= (int)(Math.random() * 5); i++) //random number 1-5 for each array
        {
            east[i] = randomVehicle();
        }

        for(int i = 0; i <= (int)(Math.random() * 5); i++) //random number 1-5 for each array
        {
            south[i] = randomVehicle();
        }

        for(int i = 0; i <= (int)(Math.random() * 5); i++) //random number 1-5 for each array
        {
            west[i] = randomVehicle();
        }
    }

    //to have cars pass through that have enough time to go through the intersection
    public void runTraffic()
    {
        if(isNSGreen)
        {              
            if (north[0] != null && south[0] != null) 
            {
                int northTime = north[0].getTime();
                int southTime = south[0].getTime();
                int maxTime = north[0].getTime();
                if (south[0].getTime() > maxTime) {
                    maxTime = south[0].getTime();
                }
                if (currentTime >= maxTime)
                {
                    currentTime -= maxTime;
                    removeVehicle("north");
                    removeVehicle("south");
                }
                else if (currentTime >= northTime)
                {
                    currentTime -= northTime;
                    removeVehicle("north");
                }
                else if (currentTime >= southTime)
                {
                    currentTime -= southTime;
                    removeVehicle("south");
                }
            }
            else if (north[0] != null) 
            {
                int northTime = north[0].getTime();
                if (northTime <= currentTime)
                {
                    currentTime -= northTime;
                    removeVehicle("north");
                }
            }
            else if (south[0] != null)
            {
                int southTime = south[0].getTime();
                if (southTime <= currentTime)
                {
                    currentTime -= southTime;
                    removeVehicle("south");
                }
            }
        }
        else if(isEWGreen)
        {   
            if (east[0] != null && west[0] != null) 
            {
                int eastTime = east[0].getTime();
                int westTime = west[0].getTime();
                int maxTime = east[0].getTime();
                if (west[0].getTime() > maxTime) {
                    maxTime = west[0].getTime();
                }
                if (currentTime >= maxTime)
                {
                    currentTime -= maxTime;
                    removeVehicle("east");
                    removeVehicle("west");
                }
                else if (currentTime >= eastTime)
                {
                    currentTime -= eastTime;
                    removeVehicle("east");
                }
                else if (currentTime >= westTime)
                {
                    currentTime -= westTime;
                    removeVehicle("west");
                }
            }
            else if (east[0] != null) 
            {
                int eastTime = east[0].getTime();
                if (eastTime <= currentTime)
                {
                    currentTime -= eastTime;
                    removeVehicle("east");
                }
            }
            else if (west[0] != null)
            {
                int westTime = west[0].getTime();
                if (westTime <= currentTime)
                {
                    currentTime -= westTime;
                    removeVehicle("west");
                }
            }
        }

        if(isNSGreen)
        {
            if (currentTime == 0 || (north[0] == null && south[0] == null) || !enoughTime(currentTime))
            {
                isNSGreen = false;
                isEWGreen = true;
                currentTime = time;
                if (noVehiclesLeft())
                {
                    running = false;
                }
                else 
                {
                    String str = "";
                    int num = (int)(Math.random() * 4);
                    if (num == 0)
                    {
                        str = "north";
                    }
                    else if (num == 1)
                    {
                        str = "south";
                    }
                    else if (num == 2)
                    {
                        str = "east";
                    }
                    else if (num == 3)
                    {
                        str = "west";
                    }
                    addVehicle(str, true);
                }
            }
        }
        else if(isEWGreen)
        {
            if (currentTime == 0 || (east[0] == null && west[0] == null) || !enoughTime(currentTime))
            {
                isEWGreen = false;
                isNSGreen = true;
                currentTime = time;
                if (noVehiclesLeft())
                {
                    running = false;
                }
                else 
                {
                    String str = "";
                    int num = (int)(Math.random() * 4);
                    if (num == 0)
                    {
                        str = "north";
                    }
                    else if (num == 1)
                    {
                        str = "south";
                    }
                    else if (num == 2)
                    {
                        str = "east";
                    }
                    else if (num == 3)
                    {
                        str = "west";
                    }
                    addVehicle(str, true);
                }
            }          
        }
        if (isEmergencyVehicle())
        {
            moveEmergency();
        }
        System.out.println("N/S Light is Green: " + isNSGreen);
        System.out.println("E/W Light is Green: " + isEWGreen);
        System.out.println("Current time is: " + currentTime);
    }

    //check if there is enough time for current vehicle to pass through the intersection
    public boolean enoughTime(int currentTime)
    {
        boolean result = false;
        if (isNSGreen)
        {
            if (north[0] != null && north[0].getTime() <= currentTime)
            {
                result = true;
            }
            if (south[0] != null && south[0].getTime() <= currentTime)
            {
                result = true;
            }
        }
        else if (isEWGreen)
        {
            if (east[0] != null && east[0].getTime() <= currentTime)
            {
                result = true;
            }
            if (west[0] != null && west[0].getTime() <= currentTime)
            {
                result = true;
            }
        }
        return result;
    }

    //remove vehicle from queue
    public void removeVehicle(String dir)
    {
        boolean result = false;
        if (dir.equals("north"))
        {
            for (int i = 0; i < 4; i++)
            {
                if (north[i] != null && north[i+1] != null) {
                    north[i] = north[i+1];
                    result = true;
                }
                else if (north[i] != null && north[i+1] == null) {
                    north[i] = null;
                }
            }
        }
        else if (dir.equals("east"))
        {
            for (int i = 0; i < 4; i++)
            {
                if (east[i] != null && east[i+1] != null) {
                    east[i] = east[i+1];
                    result = true;
                }
                else if (east[i] != null && east[i+1] == null) {
                    east[i] = null;
                }
            }
        }
        else if (dir.equals("south"))
        {
            for (int i = 0; i < 4; i++)
            {
                if (south[i] != null && south[i+1] != null) {
                    south[i] = south[i+1];
                    result = true;
                }
                else if (south[i] != null && south[i+1] == null) {
                    south[i] = null;
                }
            }
        }
        else if (dir.equals("west"))
        {
            for (int i = 0; i < 4; i++)
            {
                if (west[i] != null && west[i+1] != null) {
                    west[i] = west[i+1];
                    result = true;
                }
                else if (west[i] != null && west[i+1] == null) {
                    west[i] = null;
                }
            }
        }
    }

    //method for creating random vehicle excluding emergency vehicle
    public static Vehicle randomVehicle()
    {
        int rand = (int)(Math.random() * 3);

        if (rand == 0)
        {
            Car car = new Car();
            return car;
        }
        else if (rand == 1)
        {
            Truck truck = new Truck();
            return truck;
        }
        else
        {
            Semi semi = new Semi();
            return semi;
        }
    }

    //create random vehicle including an emergency vehicle
    public static Vehicle randomVehicleEmergency()
    {
        int rand = (int)(Math.random() * 4);

        if (rand == 0)
        {
            Car car = new Car();
            return car;
        }
        else if (rand == 1)
        {
            Truck truck = new Truck();
            return truck;
        }
        else if (rand == 2)
        {
            Semi semi = new Semi();
            return semi;
        }
        else
        {
            Emergency emergen = new Emergency();
            return emergen;
        }
    }

    //move emergency vehicle to front of array
    public void moveEmergency()
    {
        int emerIndex = -1;
        if (isEmergency("north"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (north[i] != null) {
                    if (north[i].getClass() == Emergency.class) {
                        emerIndex = i;
                    }
                }
            }
            Vehicle temp = north[emerIndex];
            for (int i = 0; i < 4; i++)
            {
                if (north[4-i] != null){
                    north[4-i] = north[3-i];
                }
            }
            north[0] = temp;
            isNSGreen = true;
        }
        else if (isEmergency("east"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (east[i] != null) {
                    if (east[i].getClass() == Emergency.class) {
                        emerIndex = i;
                    }
                }
            }
            Vehicle temp = east[emerIndex];
            for (int i = 0; i < 4; i++)
            {
                if (east[4-i] != null){
                    east[4-i] = east[3-i];
                }
            }
            east[0] = temp;
            isEWGreen = true;
        }
        else if (isEmergency("south"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (south[i] != null) {
                    if (south[i].getClass() == Emergency.class) {
                        emerIndex = i;
                    }
                }
            }
            Vehicle temp = south[emerIndex];
            for (int i = 0; i < 4; i++)
            {
                if (south[4-i] != null){
                    south[4-i] = south[3-i];
                }
            }
            south[0] = temp;
            isNSGreen = true;
        }
        else if (isEmergency("west"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (west[i] != null) {
                    if (west[i].getClass() == Emergency.class) {
                        emerIndex = i;
                    }
                }
            }
            Vehicle temp = west[emerIndex];
            for (int i = 0; i < 4; i++)
            {
                if (west[4-i] != null){
                    west[4-i] = west[3-i];
                }
            }
            west[0] = temp;
            isEWGreen = true;
        }
    }

    //get a letter associated with the vehicle
    public static char VehicleChar(Vehicle veh)
    {
        if (veh.getClass() == Car.class)
        {
            return 'C';
        }
        else if (veh.getClass() == Truck.class)
        {
            return 'T';
        }
        else if (veh.getClass() == Semi.class)
        {
            return 'S';
        }
        else
        {
            return 'E';
        }
    }

    //add an emergency vehicle to a direction
    public void addEmergency()
    {
        Vehicle emer = new Emergency();
        int counter = -1;
        for (int i = 0; i < 5; i++)
        {
            if (north[i] == null)
            {
                north[i] = emer;
                counter = 1;
            }
            if (counter ==1) {
                break;   
            }
        }
    }

    //add vehicle to certain direction
    public void addVehicle(String dir, boolean isEmergency)
    {
        if (dir.equals("north"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (north[i] == null)
                {
                    if (isEmergency){
                        north[i] = randomVehicleEmergency();
                        break;
                    }
                    else {
                        north[i] = randomVehicle();
                        break;
                    }
                }
            }
        }
        if (dir .equals ("east"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (east[i] == null)
                {
                    if (isEmergency) {
                        east[i] = randomVehicleEmergency();
                        break;
                    }
                    else {
                        east[i] = randomVehicle();
                        break;
                    }
                }
            }
        }
        if (dir .equals ("south"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (south[i] == null)
                {
                    if (isEmergency) {
                        south[i] = randomVehicleEmergency();
                        break;
                    }
                    else {
                        south[i] = randomVehicle();
                        break;
                    }
                }
            }
        }
        if (dir .equals ("west"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (west[i] == null)
                {
                    if (isEmergency) {
                        west[i] = randomVehicleEmergency();
                        break;
                    }
                    else {
                        west[i] = randomVehicle();
                        break;
                    }
                }
            }
        }
    }

    //check if there are vehicles left in the intersection
    public boolean noVehiclesLeft()
    {
        boolean result = true;
        for (int i = 0; i < 5; i ++)
        {
            if (north[i] != null || east[i] != null || south[i] != null || west[i] != null)
                result = false;
        }      
        return result;
    }

    //check if there is an emergency vehicle anywhere in the intersection
    public boolean isEmergencyVehicle()
    {
        boolean result = false;
        for (int i = 0; i < 5; i ++)
        {
            if (north[i] != null)
            {
                if (north[i].getClass() == Emergency.class)
                    result = true;
            }
            if (east[i] != null)
            {
                if (east[i].getClass() == Emergency.class)
                    result = true;
            }
            if (south[i] != null)
            {
                if (south[i].getClass() == Emergency.class)
                    result = true;
            }
            if (west[i] != null)
            {
                if (west[i].getClass() == Emergency.class)
                    result = true;
            }
        }      
        return result;
    }

    //check if there is an emergency vehicle in the specific direction
    public boolean isEmergency(String dir)
    {
        boolean result = false;
        if (dir .equals ("north"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (north[i] != null)
                {
                    if (north[i].getClass() == Emergency.class)
                        result = true;
                }
            }
        }
        else if (dir .equals ("east"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (east[i] != null)
                {
                    if (east[i].getClass() == Emergency.class)
                        result = true;
                }
            }
        }
        else if (dir .equals ("south"))
        {
            for (int i = 0; i < 5; i++)
            {
                if (south[i] != null)
                {
                    if (south[i].getClass() == Emergency.class)
                        result = true;
                }
            }
        }
        else
        {
            for (int i = 0; i < 5; i++)
            {
                if (west[i] != null)
                {
                    if (west[i].getClass() == Emergency.class)
                        result = true;
                }
            }
        }
        return result;
    }

    //print the intersection to the console
    public void printIntersection()
    {
        String str = "";

        str += ("                     |  ");
        if (north[4] != null)
        {
            str += VehicleChar(north[4]) + "  |\n";
        }
        else
        {
            str += "   |\n";
        }

        str += ("                     |  ");
        if (north[3] != null)
        {
            str += VehicleChar(north[3]) + "  |\n";
        }
        else
        {
            str += "   |\n";
        }

        str += ("                     |  ");
        if (north[2] != null)
        {
            str += VehicleChar(north[2]) + "  |\n";
        }
        else
        {
            str += "   |\n";
        }

        str += ("                     |  ");
        if (north[1] != null)
        {
            str += VehicleChar(north[1]) + "  |\n";
        }
        else
        {
            str += "   |\n";
        }

        str += ("---------------------|  ");
        if (north[0] != null)
        {
            str += VehicleChar(north[0]) + "  |---------------------\n";
        }
        else
        {
            str += "   |---------------------\n";
        }

        str += "                                                 \n";

        if (west[4] != null)
        {
            str += "    " + VehicleChar(west[4]) + "   ";
        }
        else
        {
            str += "       ";
        }

        if (west[3] != null)
        {
            str += VehicleChar(west[3]) + "   ";
        }
        else
        {
            str += "    ";
        }

        if (west[2] != null)
        {
            str += VehicleChar(west[2]) + "   ";
        }
        else
        {
            str += "    ";
        }

        if (west[1] != null)
        {
            str += VehicleChar(west[1]) + "   ";
        }
        else
        {
            str += "    ";
        }

        if (west[0] != null)
        {
            str += VehicleChar(west[0]) + "   ";
        }
        else
        {
            str += "    ";
        }

        str += "      ";

        if (east[0] != null)
        {
            str += VehicleChar(east[0]) + "   ";
        }
        else
        {
            str += "    ";
        }

        if (east[1] != null)
        {
            str += VehicleChar(east[1]) + "   ";
        }
        else
        {
            str += "   ";
        }

        if (east[2] != null)
        {
            str += VehicleChar(east[2]) + "   ";
        }
        else
        {
            str += "   ";
        }

        if (east[3] != null)
        {
            str += VehicleChar(east[3]) + "   ";
        }
        else
        {
            str += "   ";
        }

        if (east[4] != null)
        {
            str += VehicleChar(east[4]) + "   \n";
        }
        else
        {
            str += "   \n";
        }

        str+= "                                                 \n";

        str += ("---------------------|  ");
        if (south[0] != null)
        {
            str += VehicleChar(south[0]) + "  |---------------------\n";
        }
        else
        {
            str += "   |---------------------\n";
        }

        str += ("                     |  ");
        if (south[1] != null)
        {
            str += VehicleChar(south[1]) + "  |\n";
        }
        else
        {
            str += "   |\n";
        }

        str += ("                     |  ");
        if (south[2] != null)
        {
            str += VehicleChar(south[2]) + "  |\n";
        }
        else
        {
            str += "   |\n";
        }

        str += ("                     |  ");
        if (south[3] != null)
        {
            str += VehicleChar(south[3]) + "  |\n";
        }
        else
        {
            str += "   |\n";
        }

        str += ("                     |  ");
        if (south[4] != null)
        {
            str += VehicleChar(south[4]) + "  |\n";
        }
        else
        {
            str += "   |\n";
        }

        System.out.println(str);
    }
}