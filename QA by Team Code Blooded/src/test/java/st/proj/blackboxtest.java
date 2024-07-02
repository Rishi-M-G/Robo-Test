package st.proj;
import static com.google.common.base.Objects.equal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class blackboxtest {
    Robot r = new Robot();
    @Test  // for 0 initializing value of floor for (Min Value-1)= 0
    void initializeFloorvaluezero(){
    int floor = 0;
    boolean result = r.initializeFloor(floor);
    assertEquals(false, result, "Zero value: Floor cant be Initialize for zero value");  
}  
    @Test //For positive initializing value as Min value = 1
    void initializeFloorTest1(){
    int floor = 1;
    boolean result = r.initializeFloor(floor);
    int[][] expectedFloor = new int[floor][floor];
    String expectedDirection = "north";
    String expectedPen = "up";
    int[] expectedPosition = new int[]{0, 0};

    assertEquals(true, result);
    assertArrayEquals(expectedFloor, r.getFloor());
    assertEquals(expectedDirection, r.direction);
    assertEquals(expectedPen, r.pen);
    assertArrayEquals(expectedPosition, r.robotPosition); 
        }
    @Test //For positive initializing value as Min+1= 2
    void initializeFloorTest2(){
    int floor = 2;
    boolean result = r.initializeFloor(floor);
    int[][] expectedFloor = new int[floor][floor];
    String expectedDirection = "north";
    String expectedPen = "up";
    int[] expectedPosition = new int[]{0, 0};
    
    assertEquals(true, result);
    assertArrayEquals(expectedFloor, r.getFloor());
    assertEquals(expectedDirection, r.direction);
    assertEquals(expectedPen, r.pen);
    assertArrayEquals(expectedPosition, r.robotPosition); 
            }
 
 
 @Test // for moving forward for (Min Value-1)= -1
 void testMoveForward1() {
     r.initializeFloor(10);
     r.setPenDown();
     r.moveForward(-1);
     assertEquals("Position: 0, 0 - Pen: down - Facing: north",r.showCurrentPositionStatus(), "its a negative number, choose another value to move");
 }
 @Test // for moving forward for (Min Value)= 0
 void testMoveForward2() {
     r.initializeFloor(10);
     r.setPenDown();
     r.moveForward(0);
     assertEquals("Position: 0, 0 - Pen: down - Facing: north",r.showCurrentPositionStatus());
     
}
@Test // for moving forward for for (Min Value + 1)= 1
void testMoveForward3() {
    r.initializeFloor(10);
    r.setPenDown();r.moveForward(1);
    assertEquals("Position: 0, 1 - Pen: down - Facing: north",r.showCurrentPositionStatus());
     
}
@Test // for moving forward for (Max Value)= 9
void testMoveForward4() {
    r.initializeFloor(10);
    r.setPenDown();
    r.moveForward(9);
    assertEquals("Position: 0, 10 - Pen: down - Facing: north",r.showCurrentPositionStatus());
     
}
@Test // for moving forward for (Max Value-1)= 8
void testMoveForward5() {
    r.initializeFloor(10);
    r.setPenDown();
    r.moveForward(8);
    assertEquals("Position: 0, 9 - Pen: down - Facing: north",r.showCurrentPositionStatus());
    
 } 
 @Test // for moving forward for (Max Value+ 1)= 10
 void testMoveForward6() {
     r.initializeFloor(5);
     r.setPenDown();
     r.moveForward(10);
     assertEquals("Position: 0, 0 - Pen: down - Facing: north",r.showCurrentPositionStatus(), "Still at initial position: out of bound problem");
    }
}