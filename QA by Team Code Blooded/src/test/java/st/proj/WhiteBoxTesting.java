package st.proj;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class WhiteBoxTesting {
    Robot r = new Robot();

    @Test
    void currentPositionCheckWithValidCommandsTest1() {

        r.initializeFloor(10);
        r.moveForward(6);
        r.turnRight();
        r.moveForward(4);
        r.turnLeft();
        r.moveForward(2);

        String res = r.showCurrentPositionStatus();
        String[] arrOfStr = res.split(":", 4);
        String pos = arrOfStr[1].substring(0,  arrOfStr[1].indexOf("-")-1).trim();
        String pen = arrOfStr[2].substring(0,  arrOfStr[2].indexOf("-")-1).trim();
        String dir = arrOfStr[3].trim();

        assertEquals("up", pen, "Pen Status");
        assertEquals("north", dir, "Direction Facing");
        assertEquals("4, 8", pos, "Robot Position");

    }
    @Test
    void currentPositionCheckWithValidCommandsTest2() {

        r.initializeFloor(50);
        r.moveForward(20);
        r.turnRight();
        r.moveForward(10);
        r.turnLeft();
        r.moveForward(10);
        r.turnRight();
        r.turnRight();
        r.moveForward(5);

        String res = r.showCurrentPositionStatus();
        String[] arrOfStr = res.split(":", 4);
        String pos = arrOfStr[1].substring(0,  arrOfStr[1].indexOf("-")-1).trim();
        String pen = arrOfStr[2].substring(0,  arrOfStr[2].indexOf("-")-1).trim();
        String dir = arrOfStr[3].trim();

        assertEquals("up", pen, "Pen Status");
        assertEquals("south", dir, "Direction Facing");
        assertEquals("10, 25", pos, "Robot Position");

    }
    @Test
    void displayMatrixTest(){
        r.displayMatrix();
        r.initializeFloor(4);
        r.setPenDown();
        r.moveForward(2);
        r.turnRight();
        r.moveForward(3);
        r.displayMatrix();
        int[][] floor = {{1,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0}};
        assertTrue(Arrays.deepEquals(r.getFloor(),floor));
    }
    @Test
    void penDownTest() {
        var penDown = r.setPenDown();
        assertEquals("down", penDown);
        assertNotEquals("up", penDown);
    }

    @Test
    void penUpTest() {
        var penUp = r.setPenUp();
        assertEquals("up", penUp);
        assertNotEquals("down", penUp);
    }


    @Test
    void nullOrZeroInitializeFloorValuesTest() {

        int desiredFloorSize = 40;
        r.initializeFloor(desiredFloorSize);

        String directionAfterInitialize = r.direction;
        int floorSizeAfterInitialize = r.floorSize;
        String penAfterInitialize = r.pen;

        assertNotEquals(null, penAfterInitialize, "Desired Pen Status");
        assertNotEquals(null,directionAfterInitialize,  "Desired Direction");
        assertNotEquals(0,floorSizeAfterInitialize,  "Desired Floor Size");
    }

    @Test
    void validInitializeFloorValuesTest() {

        int desiredFloorSize = 40;
        r.initializeFloor(desiredFloorSize);

        String directionAfterInitialize = r.direction;
        int floorSizeAfterInitialize = r.floorSize;
        String penAfterInitialize = r.pen;
        int[] robotPositionAfterInitialize = r.robotPosition;

        assertEquals("up", penAfterInitialize, "Desired Pen Status");
        assertEquals("north",directionAfterInitialize,  "Desired Direction");
        assertEquals(desiredFloorSize,floorSizeAfterInitialize,  "Desired Floor Size");
        assertEquals(0,robotPositionAfterInitialize[0],  "Robot Position at X axis");
        assertEquals(0,robotPositionAfterInitialize[1],  "Robot Position at Y axis");
    }
    @Test
    void invalidInitializeFloorValuesTest() {

        int desiredFloorSize = -5;
        boolean res =  r.initializeFloor(desiredFloorSize);
        assertEquals(false, res, "Does not Initialize");

    }
    @Test
    void currentPositionCheckWithoutInitializedTest() {

        String res = r.showCurrentPositionStatus();
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            String pos = res.substring(res.indexOf(":") + 1, res.indexOf("-") - 1);
        });

    }
    @Test
    void noMovementPossibleTest() {
        r.initializeFloor(10);
        String before = r.showCurrentPositionStatus();
        r.turnLeft();
        r.moveForward(7);
        String after = r.showCurrentPositionStatus();
        assertEquals(before.substring(0,14),after.substring(0,14));
        r.turnLeft();
        r.moveForward(5);
        after = r.showCurrentPositionStatus();
        assertEquals(before.substring(0,14),after.substring(0,14));
        r.turnLeft();
        r.moveForward(15);
        after = r.showCurrentPositionStatus();
        assertEquals(before.substring(0,14),after.substring(0,14));
        r.turnLeft();
        r.moveForward(30);
        after = r.showCurrentPositionStatus();
        assertEquals(before.substring(0,14),after.substring(0,14));
    }

    @Test
    void callingCommandTest(){
        String input = "i\ni 10\nm\nm4\nc \nr \nl \np \nu \nd \ns \nq";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        r.runRobot();
        input = "\n\nq";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        r.runRobot();
    }

    @Test
    void turnRightTest() {
        assertEquals(r.turnRight(),"0");
        r.initializeFloor(30);
        String dir = r.turnRight();
        assertEquals("east", dir, "Direction After Turning Right");

        r.direction = "east";
        String dir2 = r.turnRight();
        assertEquals("south", dir2, "Direction After Turning Right");

        r.direction = "south";
        String dir3 = r.turnRight();
        assertEquals("west", dir3, "Direction After Turning Right");

        r.direction = "west";
        String dir4 = r.turnRight();
        assertEquals("north", dir4, "Direction After Turning Right");
    }

    @Test
    void turnLeftTest() {
        assertEquals(r.turnLeft(),"0");
        r.initializeFloor(10);
        String dir = r.turnLeft();
        assertEquals("west", dir, "Direction After Turning Left");

        r.direction = "east";
        String dir2 = r.turnLeft();
        assertEquals("north", dir2, "Direction After Turning Left");

        r.direction = "south";
        String dir3 = r.turnLeft();
        assertEquals("east", dir3, "Direction After Turning Left");

        r.direction = "west";
        String dir4 = r.turnLeft();
        assertEquals("south", dir4, "Direction After Turning Left");
    }

    @Test
    void moveForwardTest() {
        assertFalse(r.moveForward(10));
        r.initializeFloor(10);
        r.moveForward(2);
        r.setPenDown();
        assertEquals("Position: 0, 2 - Pen: down - Facing: north",r.showCurrentPositionStatus());
        r.turnRight();
        r.moveForward(6);
        assertEquals("Position: 6, 2 - Pen: down - Facing: east",r.showCurrentPositionStatus());
        r.turnLeft();
        r.moveForward(4);
        assertEquals("Position: 6, 6 - Pen: down - Facing: north",r.showCurrentPositionStatus());
        r.turnRight();
        r.turnRight();
        r.moveForward(3);
        assertEquals("Position: 6, 3 - Pen: down - Facing: south",r.showCurrentPositionStatus());
    }

    @Test
    void commandFormatTest(){
        int valid = r.splitArray("I 3");
        assertEquals(valid,3);
        valid = r.splitArray("I07");
        assertEquals(valid,7);
        int invalidIIntegerValue = r.splitArray("I 1.5");
        int invalidCommandFormat = r.splitArray("i 10 10");
        assertEquals(invalidIIntegerValue,-1);
        assertEquals(invalidCommandFormat,-1);


    }




}
