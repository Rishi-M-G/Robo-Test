package COEN6761_Group_Project.Robot_Motion;

import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	
	
	Robot robot = new Robot(); // instance of Robot Class
	
	
	
	@Test
	public void testTurnRight()
	{
		robot.Direction = "north";
		robot.turnRight();
		assertEquals("east",robot.Direction);
		
		robot.Direction = "south";
		robot.turnRight();
		assertEquals("west",robot.Direction);
		
		robot.Direction = "west";
		robot.turnRight();
		assertEquals("north",robot.Direction);
		
		robot.Direction = "east";
		robot.turnRight();
		assertEquals("south",robot.Direction);
	}
	
	@Test
	public void testTurnLeft()
	{
		robot.Direction = "north";
		robot.turnLeft();
		assertEquals("west",robot.Direction);
		
		robot.Direction = "south";
		robot.turnLeft();
		assertEquals("east",robot.Direction);
		
		robot.Direction = "west";
		robot.turnLeft();
		assertEquals("south",robot.Direction);
		
		robot.Direction = "east";
		robot.turnLeft();
		assertEquals("north",robot.Direction);
	}
	int n =5;
	int s = 2;
	
	
	@Test
	public void testFloorValues() {
		robot.initializeArrayFloor(n);
		robot.penDown();
		robot.moveForward(s);
		robot.turnRight();
		robot.moveForward(s);
		
		 assertEquals(1,robot.floor[0][0]);
		 assertEquals(1,robot.floor[0][1]);
		 assertEquals(1,robot.floor[0][2]);
		 assertEquals(1,robot.floor[1][2]);
		 assertEquals(1,robot.floor[2][2]);
		 assertEquals(0,robot.floor[0][3]);
		 assertEquals(0,robot.floor[0][4]);
		 assertEquals(0,robot.floor[1][0]);
		 assertEquals(1,robot.floor[1][2]);
		 assertEquals(0,robot.floor[1][3]);
		 assertEquals(0,robot.floor[1][4]);
		 assertEquals(0,robot.floor[2][0]);
		 assertEquals(0,robot.floor[2][1]);
		 assertEquals(0,robot.floor[2][3]);
		 assertEquals(0,robot.floor[2][4]);
		 assertEquals(0,robot.floor[3][0]);
		 assertEquals(0,robot.floor[3][1]);
		 assertEquals(0,robot.floor[3][2]);
		 assertEquals(0,robot.floor[3][3]);
		 assertEquals(0,robot.floor[3][4]);
		 assertEquals(0,robot.floor[4][0]);
		 assertEquals(0,robot.floor[4][1]);
		 assertEquals(0,robot.floor[4][3]);
		 assertEquals(0,robot.floor[4][4]);
	}
	
	@Test
	public void testFloorOutput() {
		robot.initializeArrayFloor(n);
		robot.penDown();
		robot.moveForward(s);
		robot.turnRight();
		robot.moveForward(s);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		
		robot.displayFloor();
		String expectedOutput = "4                 \n" +
	            				"\n3                 \n" +
	            				"\n2  *  *  *        \n" +
	            				"\n1  *              \n" +
	            				"\n0  *              \n \n" +
	            				"   0  1  2  3  4";
		
		//assertEquals("\n4                 \n3                 \n2  *  *  *        \n1  *              \n0  *              \n   0  1  2  3  4  ",output.toString());
		assertEquals(expectedOutput.trim().replaceAll("\\s",""),output.toString().trim().replaceAll("\\s",""));
	}
	
	
	@Test
	public void testPenDownAndTracing() {
		robot.initializeArrayFloor(n);
		robot.penDown();
		robot.moveForward(s);
		assertEquals(1,robot.floor[0][2]);
	}
	
	@Test
	public void testMovingOutOfBounds() {
		robot.initializeArrayFloor(n);
		robot.penDown();
        assertEquals(true, robot.moveForward(n-1)); //to prove that for any n, n-1 is possible so no prob received
        assertEquals(false, robot.moveForward(n)); //as int n=5, and array size is 0-4 x 0-4, moving 5 is out of bound
	}
	
	@Test
	public void testCurrentPosition() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output));
		
		robot.initializeArrayFloor(n);
		
		robot.currentPosition();
		assertEquals("Position: 0,0 - Pen: up - Facing: north", output.toString().trim());
	}
	
	@Test
	public void testPenUp() {
		robot.penUp();
		assertEquals("up",robot.penStatus);
	}
	
	@Test
	public void testPenDown() {
		robot.penDown();
		assertEquals("down",robot.penStatus);
	}
	
	
	
	@Test
    public void testInitializeSystem() {
		
		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output1));
	    robot.initializeArrayFloor(-1);
		assertEquals("Invalid Number. Array Dimension should be a Positive Value",output1.toString().trim());
		
		ByteArrayOutputStream output2 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output2));
		
		robot.initializeArrayFloor(n);
        assertEquals("up",robot.penStatus);
        assertEquals(0, robot.floor[0][0]); //return true if y,x values = [x],[y]
        robot.currentPosition();
        assertEquals("Position: 0,0 - Pen: up - Facing: north", output2.toString().trim()); // current position after initializing
	}
	
	@Test
	public void testMoveForwardPenDown() {
		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output1));
		
		robot.initializeArrayFloor(n);
		robot.penDown();
		
		robot.moveForward(-1);
		assertEquals("'s' should be a positive number and should be within the Floor", output1.toString().trim());
		
		ByteArrayOutputStream output2 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output2));
		robot.moveForward(s);
		robot.currentPosition();
		assertEquals("Position: 0,2 - Pen: down - Facing: north", output2.toString().trim()); // current position after initializing
		assertEquals(1,robot.floor[0][2]);
		
		ByteArrayOutputStream output3 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output3));
		robot.moveForward(3);
		assertEquals("Robot Cannot leave the floor",output3.toString().trim());
		
		ByteArrayOutputStream output4 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output4));
	    robot.turnRight();
		robot.moveForward(s);
		robot.currentPosition();
		assertEquals("Position: 2,2 - Pen: down - Facing: east", output4.toString().trim()); // current position after initializing
		assertEquals(1,robot.floor[2][2]);
		
		ByteArrayOutputStream output5 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output5));
		robot.moveForward(3);
		assertEquals("Robot Cannot leave the floor",output5.toString().trim());
		
		ByteArrayOutputStream output6 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output6));
	    robot.turnLeft();
	    robot.turnLeft();
		robot.moveForward(s);
		robot.currentPosition();
		assertEquals("Position: 0,2 - Pen: down - Facing: west", output6.toString().trim()); // current position after initializing
		assertEquals(1,robot.floor[0][2]);
		
		ByteArrayOutputStream output7 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output7));
		robot.moveForward(1);
		assertEquals("Robot Cannot leave the floor",output7.toString().trim());
		
		ByteArrayOutputStream output8 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output8));
	    robot.turnLeft();
		robot.moveForward(1);
		robot.currentPosition();
		assertEquals("Position: 0,1 - Pen: down - Facing: south", output8.toString().trim()); // current position after initializing
		assertEquals(1,robot.floor[0][1]);
		
		ByteArrayOutputStream output9 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output9));
		robot.moveForward(2);
		assertEquals("Robot Cannot leave the floor",output9.toString().trim());
	}
	
	@Test
	public void testMoveForwardPenUp() {
		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output1));
		
		robot.initializeArrayFloor(n);
		robot.penUp();
		
		robot.moveForward(-1);
		assertEquals("'s' should be a positive number and should be within the Floor", output1.toString().trim());
		
		ByteArrayOutputStream output2 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output2));
		robot.moveForward(s);
		robot.currentPosition();
		assertEquals("Position: 0,2 - Pen: up - Facing: north", output2.toString().trim()); // current position after initializing
		assertEquals(0,robot.floor[0][2]);
		
		ByteArrayOutputStream output3 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output3));
		robot.moveForward(3);
		assertEquals("Robot Cannot leave the floor",output3.toString().trim());
		
		ByteArrayOutputStream output4 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output4));
	    robot.turnRight();
		robot.moveForward(s);
		robot.currentPosition();
		assertEquals("Position: 2,2 - Pen: up - Facing: east", output4.toString().trim()); // current position after initializing
		assertEquals(0,robot.floor[2][2]);
		
		ByteArrayOutputStream output5 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output5));
		robot.moveForward(3);
		assertEquals("Robot Cannot leave the floor",output5.toString().trim());
		
		ByteArrayOutputStream output6 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output6));
	    robot.turnLeft();
	    robot.turnLeft();
		robot.moveForward(s);
		robot.currentPosition();
		assertEquals("Position: 0,2 - Pen: up - Facing: west", output6.toString().trim()); // current position after initializing
		assertEquals(0,robot.floor[0][2]);
		
		ByteArrayOutputStream output7 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output7));
		robot.moveForward(1);
		assertEquals("Robot Cannot leave the floor",output7.toString().trim());
		
		ByteArrayOutputStream output8 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output8));
	    robot.turnLeft();
		robot.moveForward(1);
		robot.currentPosition();
		assertEquals("Position: 0,1 - Pen: up - Facing: south", output8.toString().trim()); // current position after initializing
		assertEquals(0,robot.floor[0][1]);
		
		ByteArrayOutputStream output9 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output9));
		robot.moveForward(2);
		assertEquals("Robot Cannot leave the floor",output9.toString().trim());
	}        
	
	@Test
	public void testExitProgram() {
		Robot robot = new Robot();
		assertEquals("ROBOT MOTION TERMINATED", robot.quit());
	}
	
	@Test
	public void testCommandHistory () {
		Robot robot = new Robot();
		
		List<String> commandHistory = new ArrayList<>();
		
		robot.initializeArrayFloor(5);
		commandHistory.add("Initialize Array 5");
		robot.currentPosition();
		commandHistory.add("Current Position of the Robot");
		robot.displayFloor();
		commandHistory.add("Display Floor");
		robot.penDown();
		commandHistory.add("Pen Down");
		robot.moveForward(2);
		commandHistory.add("Move Forward 2");
		robot.displayFloor();
		commandHistory.add("Display Floor");
		robot.currentPosition();
		commandHistory.add("Current Position of the Robot");
		robot.turnLeft();
		commandHistory.add("Turn Left");
		robot.turnRight();
		commandHistory.add("Turn Right");
		robot.history();
		commandHistory.add("Show Command History");
		
		assertEquals(commandHistory,robot.commandHistory);
		
	}

//	@Test
//	public void testCommandQ() {
//
//		Robot robot = new Robot();
//		InputStream in = new ByteArrayInputStream("q".getBytes());
//		System.setIn(in);
//		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(outContent));
//		robot.command();
//		assertEquals("\n"+"Please enter your command\n"+"ROBOT MOTION TERMINATED\n", outContent.toString());
//
//	}
//	@Test
//	public void testQuit() {
//		Robot r1 = new Robot();
//		assertEquals("ROBOT MOTION TERMINATED", r1.quit());
//	}
}
		
