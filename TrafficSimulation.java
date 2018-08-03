/*
 * CSE 205: 17566 / M W 4:35PM - 5:50PM 
 * Assignment: Assignment 6
 * Authors: Randall Knutson 1212366088
 * Description: Main class running the simulation.
 */
import java.util.*;
public class TrafficSimulation
{
	public static Scanner scan = new Scanner(System.in);
    //main method
	public static void main(String[] args)
    {      
        TrafficLight light = new TrafficLight();
        light.printIntersection();
        
        while (light.running)
        {
            runSimulation(light);
            menu();
            //scan.nextLine();
        }
    }

	//runs the simulation
    public static void runSimulation(TrafficLight light)
    {
        light.runTraffic();
        light.printIntersection();
    }
    
    //gives the menu options to the user
    public static void menu() {
    	System.out.println("What would you like to do?");
    	System.out.println("1.) Let traffic flow");
    	System.out.println("2.) Mergesort the north vehicle array by time to pass through intersection");
    	int option = scan.nextInt();
    	
    	if (option == 2) {
    		MergeSort mergeSort = new MergeSort();
    		mergeSort.sort(TrafficLight.north);
    		System.out.println("Mergesorted!\n");
    		menu();
    	}
    		
    }
    	
}
