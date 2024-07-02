package COEN6761_Group_Project.Robot_Motion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Robot{
	Scanner sc = new Scanner(System.in);
	public int[][] floor; // Declaring Floor
	public String penStatus; // Pen Up or Pen Down
	public int x,y; // Robot's Current Position
	public int N;
	public int s;
	public String Direction;
	boolean programEnded;
	
	List<String> commandHistory = new ArrayList<>();
	
	public void command() {
		//Command System
		/*
		 * Commands:
		 * U/u: Pen Up - Char
		 * D/d: Pen Down - Char
		 * R/r: Turn Right - Char
		 * L/l: Turn Left - Char
		 * M s/ m s: Move forward s spaces - M is Char and s is a non-negative number
		 * P/p: Print the N x N array and also display indices - Char
		 * C/c: Print current position of the pen  and whether it is up or down and whether it is 
		 * facing up or down - Char
		 * Q/q: Stop the program - Char
		 * I n/i n: Initialize the system, system is reset, n is an non-negative integer  
		 */
		
		
		while(true) // Infinite loop until command 'q'
		{
			System.out.println();
			System.out.println("Please enter your command");
			//char command_code =  sc.next().charAt(0);
			String command= sc.nextLine();
			String[] parts = command.split(" ");
			char command_code = parts[0].charAt(0);
			
			//Add command to history
			
			
			// switch case for commands -- HAVE TO CHANGE THIS
			
			switch (command_code) 
			{
			case 'I':
			case 'i':
				int num = Integer.parseInt(parts[1]);
				initializeArrayFloor(num);
				break;
			case 'P':
			case 'p':
				displayFloor();
				break;
			case 'C':
			case 'c':
				currentPosition();
				break;
			case 'U':
			case 'u':
				penUp();
				break;
			case 'D':
			case 'd':
				penDown();
				break;
			case 'R':
			case 'r':
				turnRight();
				break;
			case 'L':
			case 'l':
				turnLeft();
				break;
			case 'Q':
			case 'q':
				System.out.println(quit());
				return;
			case 'M':
			case 'm':
				num = Integer.parseInt(parts[1]);
				moveForward(num);
				break;
			case 'H':
			case 'h':
				history();
				break;
			default:
				System.out.println("Invalid Command, Please enter a valid command");
				break;
			}
		}
	}
	
	
	public void initializeArrayFloor(int n) {
		commandHistory.add("Initialize Array "+n);
		
		// Initializing the array
		N = n;
		if( N < 0 )
		{
			System.out.println("Invalid Number. Array Dimension should be a Positive Value");
		}
		else
		{
			floor = new int[N][N];
			penStatus = "up";
			//Initializing array values to 0
			for (int i = 0; i<N; i++)
			{
				for (int j = 0;j<N;j++)
				{
					floor[i][j]= 0;
				}
			}
			//Setting Robot Position
			x = y = 0;
			floor[x][y]=0;
			//Setting Robot Direction 
			Direction = "north";
		}
	}
	
	
	public void displayFloor() {
		commandHistory.add("Display Floor");
		
		for(int i = N-1;i>=0;i--)
		{
			System.out.println();
			System.out.print(i+"  ");
			for (int j = 0;j<N;j++)
			{
				if(floor[j][i] == 1)
					System.out.print("*  ");
				else
					System.out.print("   ");
			}
			System.out.println();
		}
		System.out.println(" ");
		System.out.print("   ");
		for(int k = 0;k<N;k++)
		{
			System.out.print(k+"  ");
		}
	}
	
	
	public void currentPosition() {
		commandHistory.add("Current Position of the Robot");
		System.out.println("Position: "+x + "," + y + " - Pen: " + penStatus+" - Facing: "+Direction);	
	}
	
	
	public void penUp() {
		commandHistory.add("Pen Up ");
		penStatus = "up";
	}
	
	
	public void penDown() {
		commandHistory.add("Pen Down");
		penStatus = "down";
	}
	
	public void turnRight() {
		commandHistory.add("Turn Right");
		if(Direction == "north")
		{
			Direction = "east";
		}
		else if(Direction == "south")
		{
			Direction = "west";
		}
		else if(Direction == "west")
		{
			Direction = "north";
		}
		else
		{
			Direction = "south";
		}
	}
	
	public void turnLeft() {
		commandHistory.add("Turn Left");
		if(Direction == "north")
		{
			Direction = "west";
		}
		else if(Direction == "south")
		{
			Direction = "east";
		}
		else if(Direction == "west")
		{
			Direction = "south";
		}
		else
		{
			Direction = "north";
		}
	}
	
	
	public boolean moveForward(int S) {
		commandHistory.add("Move Forward "+S);
		s = S;
		if((s<0 || s>=N) == true)
		{
			System.out.println("'s' should be a positive number and should be within the Floor");
			return false;
		}
		else
		{
			if(Direction == "north" && (y+s < N)) 
			{
				int m = y;
				y = y + s; // Setting new coordinates
				if(penStatus == "down")
				{
					for(int l=m;l<=y;l++)
					{
						floor[x][l] = 1;
					}
				}
			}
			else if(Direction == "east" && (x+s < N))
			{
				int m = x;
				x = x + s;
				if(penStatus == "down")
				{
					for(int l=m;l<=x;l++)
					{
						floor[l][y] = 1;
					}
				}
			}
			else if(Direction == "west" && (x-s >= 0))
			{
				int m = x;
				x = x - s;
				if(penStatus == "down")
				{
					for(int l=m;l<=y;l++)
					{
						floor[l][y] = 1;
					}
				}
			}
			else if(Direction == "south" && (y-s >= 0))
			{
				int m = y;
				y = y - s;
				if(penStatus == "down")
				{
					for(int l=m;l<=y;l++)
					{
						floor[x][l] = 1;
					}
				}
			}
			else {
				System.out.println("Robot Cannot leave the floor");
				return false;
			}
		}
		return true;
	}
	

	public String quit() {
		return "ROBOT MOTION TERMINATED";
	}
	
	public void history() {
		commandHistory.add("Show Command History");
		
		System.out.println("COMMAND HISTORY");
		for(String command:commandHistory)
		{
			System.out.println(command);
		}
	}
}