package st.proj;


import java.util.Scanner;
import java.util.SortedMap;

public class Robot {

	// VARIABLES FOR ROBOT
	
    int floorSize;
    int[] robotPosition;
    String direction;
    String pen;
    int[][] floor;
    int initialized = 0;
    
    // CONSTRUCTOR
    public Robot(){

    }
    
    /*
     * ***** METHOD 1 : GET FLOOR *****
     * variable definitions : 0
     * 
     * if : 0
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 1
     * c-use variable : floor(1)
     * 
     * p-use : 0
     */
    
    public int[][] getFloor() {
        return floor; // c-use(1) : floor
    }
    
    /*
     * ***** METHOD 2 : SPLIT ARRAY *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    
    public int splitArray(String cmd){ //def (1): cmd (1)
        String commandArray=""; // def(2) : commandArray(1)
        if(cmd.length()>1) // p-use(1) : cmd(1)
        {
            if(cmd.contains(" ")){ // p-use(2) : cmd(2)
                if(cmd.split(" ").length>2){ // p-use(3) :cmd (3)
                    System.out.println("Please provide command in specified format displayed above");
                    return -1;
                }
                else{
                    commandArray= cmd.split(" ")[1]; // def (3) : commandArray(2) , c-use (1) : cmd (1)
                }

            }
            else {
                commandArray = cmd.substring(1); // def (4) : commandArray (3) , c-use (2) : cmd (2)
            }
        }
        int s = 0; // def(5) : s (1)
        try{
            s = Integer.parseInt(commandArray); //def (6) : s(2) , c-use (3) : commandArray (1)
        }
        catch(NumberFormatException e){
            System.out.println(("Enter a valid integer"));
            return -1;
        }
        return s; // c-use (4) :  s (1) 
    };
    
    
    /*
     * ***** METHOD 3 : RUN ROBOT *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    
    public void runRobot(){
        char c; 
        int result; 

        System.out.print("i x : Initialize the Program \np : Display the Floor \nu: Pen Up \nd: Pen Down \nr: Turn Right \nl : Turn Left \nm x : Move Forward in that Direction \nc: Print Current Position\n");
        Scanner sc = new Scanner(System.in);
        String command;
        do{
            System.out.print("Enter command : ");
            command = sc.nextLine(); // def : command
            try{
                c = command.toLowerCase().charAt(0); //def : c , c-use : command
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("Please provide command in specified format displayed above");
                continue;
            }
            switch (c) { // p-use : c
                //Initialise
                case 'i':
                    result = splitArray(command); //def : result , c-use : command
                    if (result != -1) { // p-use : result
                        this.initializeFloor(splitArray(command)); // c-use : command
                    }
                    break;
                // Move eg m 4
                case 'm':
                    result = splitArray(command); // def : result
                    if (result != -1) { // p-use : result
                        this.moveForward(result); // c-use : result
                    }
                    break;
                //Print Current Position
                case 'c':
                    System.out.println(this.showCurrentPositionStatus());
                    break;
                //Turn Right
                case 'r':
                    this.turnRight();
                    break;
                //Turn Left
                case 'l':
                    this.turnLeft();
                    break;
                //Display Matrix
                case 'p':
                    this.displayMatrix();
                    break;
                // Pen Up command
                case 'u':
                    this.setPenUp();
                    break;
                // Pen Down command
                case 'd':
                    this.setPenDown();
                    break;
                case 'q':
                    System.out.println("Program ends");
                    break;
                // Invalid command
                default:
                    System.out.println("Enter valid command");
                    break;
            }

        }
        while(!command.toLowerCase().equals("q")); // p-use : command
    }
    
    /*
     * ***** METHOD 4 : INITIALIZE FLOOR *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    
    
    public boolean initializeFloor(int size){ // def : size
        if(size>0) // p-use : size
        {
            floorSize = size; // def : floorSize , c-use : size
            floor = new int[floorSize][floorSize]; // def : floor, c-use:floorSize
            direction = "north"; // def : direction
            pen = "up"; // def : pen
            initialized = 1; // def : initialized 
            robotPosition = new int[]{0, 0}; // def : robotPosition
            return true;
        }
        else{
            System.out.println("Size of array cannot be negative or zero");
            return false;
        }
    }
    
    /*
     * ***** METHOD 5 : PEN DOWN *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    //Method to Change Pen to Down
    public String setPenDown(){
        pen = "down"; // def : pen
        return pen; // c-use : pen
    };
    
    /*
     * ***** METHOD 6 : PEN UP *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    //Method to Change Pen to Up
    public String setPenUp(){
        pen = "up"; // def : pen
        return pen; // c-use : pen
    };


    /*
     * ***** METHOD 7 : TURN RIGHT *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    //Method to Turn Right
    public String turnRight(){
        if(initialized==0){ // p-use : initialized
            System.out.println("Please initialize the array first");
            return "0";
        }
        switch (direction) { //p-use : direction
            case "north":
                direction = "east"; // def : direction
                break;
            case "south":
                direction = "west"; // def : direction
                break;
            case "east":
                direction = "south"; // def : direction
                break;
            case "west":
                direction = "north"; // def : direction
                break;
        }
        return direction; //c-use : direction
    };
    
    /*
     * ***** METHOD 8 : TURN LEFT *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    //Method to Turn Left
    public String turnLeft(){
        if(initialized==0){ //def : initialized
            System.out.println("Please initialize the array first");
            return "0";
        }
        switch (direction) { //p-use : direction
            case "north":
                direction = "west"; // def : direction
                break;
            case "south":
                direction = "east"; //def : direction
                break;
            case "east":
                direction = "north"; // def:direction
                break;
            case "west":
                direction = "south"; // def:direction
                break;
        }
        return direction; // c-use : direction
    };
    
    /*
     * ***** METHOD 9 : CURRENT POSITION AND STATUS *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    //Method to display current position
    public String showCurrentPositionStatus(){
        if(initialized==0){ // p-use : initialized
//            System.out.print("");
            return "Please initialize the program first";
        }
        String position = "Position: " + robotPosition[0] + ", " + robotPosition[1]; //def : position , c-use : robotPosition
        String penStatus = "Pen: " + pen; // def : penStatus , c-use : pen
        String face = "Facing: " + direction; // def : face , c - use : direction
        return position + " - " + penStatus + " - " + face; // c-use : position, penStatus, face

    }
    /*
     * ***** METHOD 10 : MOVE FORWARD *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    //Method to make the Robot move
    public boolean moveForward(int spaces){ // def : spaces
        if(initialized==0){ // p-use : initialized
            System.out.println("Please initialize the array first");
            return false;
        }
        int pos1 = robotPosition[1]; //def : pos1 , c-use : robotPosition
        int pos0 = robotPosition[0]; //def : pos0 , c-use : robotPosition
        //Check the direction in which robot is facing
        //Then check if desired motion is not exceeding available space, then move, else display error message
        switch (direction) { //p-use : direction
            case "north":
                robotPosition[1] =  ((floorSize - robotPosition[1]) - spaces - 1)>=0 ? robotPosition[1] + spaces: robotPosition[1]; // def : robotPosition , c-use : robotPosition, floorSize,spaces
                if(pos1 == robotPosition[1]) // p-use : pos1, robotPosition
                {
                    System.out.println("Robot motion for this command was not possible");
                }
                if(pen == "down"){//p-use : pen
                    for (int i = pos1; i <= robotPosition[1]; i++){ //def :i , c-use:pos1,i , p-use:i , robotPosition
                        floor[robotPosition[0]][i] = 1; // def : floor, c-use : robotPosition,i
                    }
                }
                break;
            case "south":
                robotPosition[1] =  (robotPosition[1]- spaces - 1)>=0 ? robotPosition[1] - spaces : robotPosition[1]; //def : robotPosition , c-use : robotPosition, floorSize,spaces
                if(pos1 == robotPosition[1]) // p-use : pos1, robotPosition
                {
                    System.out.println("Robot motion for this command was not possible");
                }
                if(pen == "down"){//p-use : pen
                    for (int i = pos1; i >=robotPosition[1]; i--){ //def :i , c-use:pos1,i , p-use:i , robotPosition
                        floor[robotPosition[0]][i] = 1; // def : floor, c-use : robotPosition
                    }
                }
                break;
            case "east":
                robotPosition[0] = (floorSize - robotPosition[0] - spaces -1 )>=0 ? robotPosition[0] + spaces: robotPosition[0];//def : robotPosition , c-use : robotPosition, floorSize,spaces
                if(pos0 == robotPosition[0])// p-use : pos1, robotPosition
                {
                    System.out.println("Robot motion for this command was not possible");
                }
                if(pen == "down"){//p-use : pen
                    for (int i = pos0; i <= robotPosition[0]; i++){//def :i , c-use:pos1,i , p-use:i , robotPosition
                        floor[i][robotPosition[1]] = 1;// def : floor, c-use : robotPosition
                    };
                }
                break;
            case "west":
                robotPosition[0] =  (robotPosition[0]- spaces - 1)>=0 ? robotPosition[0] - spaces : robotPosition[0];//def : robotPosition , c-use : robotPosition, floorSize,spaces
                if(pos0 == robotPosition[0])// p-use : pos1, robotPosition
                {
                    System.out.println("Robot motion for this command was not possible");
                }
                if(pen == "down"){//p-use : pen
                    for (int i = pos0; i >=robotPosition[0]; i--){//def :i , c-use:pos1,i , p-use:i , robotPosition
                        floor[i][robotPosition[1]] = 1;// def : floor, c-use : robotPosition
                    };
                }
                break;
        }
        return true;
    }
    
    
    /*
     * ***** METHOD 11 : DISPLAY MATRIX *****
     * variable definitions : 6
     * 
     * if : 3
     * while : 0
     * do - while : 0
     * for : 0
     * 
     * c-use : 4
     * 
     * p-use : 3
     */
    //Method to make the Robot move
    //Method to print Robot traversed path
    public void displayMatrix(){
        if(initialized==0){ //p-use : initialized
            System.out.println("Please initialize the array first");
            return;
        }
        for (int i = floorSize-1; i>=0; i--){ //def :i , c-use:floorSize,i , p-use:i 
            //Format the output, so we have equal spaces everywhere
            //Print the indices for y-axis
            System.out.print(String.format("%-3s%-3d","",i));//c-use : i
            for (int j = 0; j<floorSize; j++){ //def : j , p-use:j, floorsize, c-use : j
                if(floor[j][i] == 1) //p-use : floor
                {
                    //If the array value is 1, print it as "*"
                    System.out.print(String.format("%-3s%-3s","","*"));
                }
                else
                {
                    //If the array value is 0, print it as " "
                    System.out.print(String.format("%-3s%-3s",""," "));
                }
            }
            System.out.println();
        }
        //Print the indices for x-axis
        System.out.print(String.format("%-6s",""));
        for(int i=0;i<floorSize;i++){ //def :i, p-use :floorSize, i c-use:i
            System.out.print(String.format("%-2s%-2d","",i)); //c-use : i
        }
        System.out.println();
    }
}

