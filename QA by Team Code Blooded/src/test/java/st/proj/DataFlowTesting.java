package st.proj;

import static com.google.common.base.Objects.equal;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

class DataFlowTesting {
	
	Robot r = new Robot();
	
	
	/*
	 * Test Case 1:
	 * Author : Rishi Murugesan Gopalakrishnan
	 * Test-Suite : Data Flow Testing
	 * Method under test : moveForward ( )
	 */
	@Test
	public void testCaseOne()
	{
		r.initializeFloor(5); //initialize floor to 5 x 5
		r.setPenDown();
		r.moveForward(2);
		assertEquals(1,r.floor[0][0]);
		assertEquals(1,r.floor[0][1]);
		assertEquals(1,r.floor[0][2]);
	}
	
	
	/*
	 * Test Case 2:
	 * Author : Rishi Murugesan Gopalakrishnan
	 * Test-Suite : Data Flow Testing
	 * Method under test : moveForward ( )
	 */
	@Test
	public void testCaseTwo()
	{
		r.initializeFloor(5); //initialize floor to 5 x 5
		r.moveForward(4);
		r.turnRight();
		r.moveForward(4);
		r.turnRight();
		r.setPenDown();
		r.moveForward(3); 
		assertEquals(1,r.floor[4][4]);
		assertEquals(1,r.floor[4][3]);
		assertEquals(1,r.floor[4][2]);	
	}
	
	
	/*
	 * Test Case 3:
	 * Author : Rishi Murugesan Gopalakrishnan
	 * Test-Suite : Data Flow Testing
	 * Method under test : moveForward ( )
	 */
	@Test
	public void testCaseThree()
	{
		r.initializeFloor(5); //initialize floor to 5 x 5
		r.turnRight();
		r.setPenDown();
		r.moveForward(3); 
		assertEquals(1,r.floor[0][0]);
		assertEquals(1,r.floor[1][0]);
		assertEquals(1,r.floor[2][0]);
		assertEquals(1,r.floor[3][0]);
	}
	
	
	/*
	 * Test Case 4:
	 * Author : Rishi Murugesan Gopalakrishnan
	 * Test-Suite : Data Flow Testing
	 * Method under test : moveForward ( )
	 */
	@Test
	public void testCaseFour()
	{
		r.initializeFloor(5); //initialize floor to 5 x 5
		r.turnRight();
		r.moveForward(4); 
		r.turnLeft();
		r.turnLeft();
		r.setPenDown();
		r.moveForward(3);
		assertEquals(1,r.floor[1][0]);
		assertEquals(1,r.floor[2][0]);

	}
	
	
	/*
	 * Test Case 5:
	 * Author : Rishi Murugesan Gopalakrishnan
	 * Test-Suite : Data Flow Testing
	 * Method under test : moveForward ( )
	 */
	@Test
	public void testCaseFive()
	{
		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output1));
		r.initializeFloor(5); //initialize floor to 5 x 5
		r.moveForward(4);
		r.setPenDown();
		r.moveForward(1);
		assertEquals("Robot motion for this command was not possible",output1.toString().trim());
	}
	
	
	/*
	 * Test Case 6:
	 * Author : Rishi Murugesan Gopalakrishnan
	 * Test-Suite : Data Flow Testing
	 * Method under test : moveForward ( )
	 */
	@Test
	public void testCaseSix()
	{
		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output1));
		r.initializeFloor(5); //initialize floor to 5 x 5
		r.turnRight();
		r.moveForward(4);
		r.turnRight();
		r.moveForward(1);
		assertEquals("Robot motion for this command was not possible",output1.toString().trim());
	}
	
	
	/*
	 * Test Case 7:
	 * Author : Rishi Murugesan Gopalakrishnan
	 * Test-Suite : Data Flow Testing
	 * Method under test : moveForward ( )
	 */
	@Test
	public void testCaseSeven()
	{
		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output1));
		r.initializeFloor(5); //initialize floor to 5 x 5
		r.turnRight();
		r.moveForward(4);
		r.moveForward(1);
		assertEquals("Robot motion for this command was not possible",output1.toString().trim());
	}

	
	/*
	 * Test Case 8:
	 * Author : Rishi Murugesan Gopalakrishnan
	 * Test-Suite : Data Flow Testing
	 * Method under test : moveForward ( )
	 */
	@Test
	public void testCaseEight()
	{
		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output1));
		r.initializeFloor(5); //initialize floor to 5 x 5
		r.turnLeft();
		r.moveForward(1);
		assertEquals("Robot motion for this command was not possible",output1.toString().trim());
	}
	
}