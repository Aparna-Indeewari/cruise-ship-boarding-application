import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CabinManager {

    public static void main(String[] args) {
        Cabin [] cabinNo = new Cabin[12];
        String userInput = " ";
        Scanner input = new Scanner(System.in);

        for ( int i = 0; i < cabinNo.length; i++){             //Creating an array of 12 cabin objects
            cabinNo[i] = new Cabin("Empty");          //Initializing each cabin object to "Empty" using constructors
        }

        /**
         Menu for the program. User gets to select which option to run
         Menu loops until user inputs Q or q to exit
         */
        while (!userInput.equals("Q")) {
            System.out.println("\n\n                                             Welcome to the Cruise Cabin Boarding System\n");
            System.out.println("               MENU\n");
            System.out.println("               V : View all cabins");
            System.out.println("               A : Add a customer to a Cabin");
            System.out.println("               E : Display empty cabin");
            System.out.println("               D : Delete customer from a cabin");
            System.out.println("               F : Find cabin from customer name");
            System.out.println("               S : Store program data into file");
            System.out.println("               L : Load program data from file");
            System.out.println("               O : View passengers Ordered alphabetically by name");
            System.out.println("               T : View passengers expenses and expenses total");
            System.out.println("               Q : Exit");

            System.out.print("\n               Please enter your selection:  ");
            userInput = input.nextLine().toUpperCase();
            System.out.println("\n_____________________________________________________________________________________________________________________________________________________");

            switch (userInput) {         //Calling methods according to user selection
                case ("A"):
                    addPassengers(cabinNo);
                    break;

                case ("V"):
                    viewAllCabins(cabinNo);
                    break;

                case ("E"):
                    displayEmptyCabins(cabinNo);
                    break;

                case ("D"):
                    deleteCustomer(cabinNo);
                    break;

                case ("F"):
                    findCustomers(cabinNo);
                    break;

                case ("S"):
                    writingToFile(cabinNo);
                    break;

                case ("L"):
                    readFile(cabinNo);
                    break;

                case ("O"):
                    sortingPassengers(cabinNo);
                    break;

                case ("T"):
                    expenses(cabinNo);
                    break;

                case ("Q"): //When user chooses to exit the program
                    System.out.println("\n\n\n                                                             EXITING PROGRAM !!!");
                    System.out.println("                                                                     GOOD BYE");
                    break;

                default:
                    System.out.println("Invalid Input!! Please select an option from below menu");   //If user enters an incorrect option
            }
        }
    }

    /**
     Prompts cabin number they want to add customers from user. Checks if the cabin is full and if not calls the method
     getDetails() to get customer details. Adds passenger to cabin.
     This loops till user enters 0 to stop.
     parameters- array of 12 Cabin Objects
     Return- None
     */
    public static void addPassengers(Cabin[] pCabinNo){
        Scanner input = new Scanner(System.in);
        while (true){
            try{
                System.out.print("\nEnter cabin number (1-12) or 0 to exit: ");
                int cabNo = input.nextInt();
                if ( cabNo <= 12 && cabNo > 0){          //Checking if the prompted cabin number is within the cabin range to handle ArrayOutofBoundry exception
                    if ( !pCabinNo[cabNo-1].isCabinFull()){     //Checking if each cabin is full of 3 passengers before adding new passengers
                        Passenger passengerDetails = getDetails();       //calling getDetails method to get passenger details
                        pCabinNo[cabNo-1].addPassengersToArray(passengerDetails);      //Adding passengers
                        pCabinNo[cabNo-1].setAvailability("Occupied");        //Changing initialized Empty status
                        System.out.println("\nYou have successfully booked cabin "+ cabNo + " !!");
                    } else {
                        System.out.println("Sorry no available space. Cabin " + cabNo + " is fully booked!!");
                    }
                }
                else if (cabNo == 0){       //Exiting if user enter 0
                    System.out.println("\n                                                            EXITING !!");
                    break;
                }
                else{
                    System.out.println("Invalid Cabin number!! Cabin number range (1- 12)");     //Validating cabin number
                }
            }catch (InputMismatchException e){
                System.out.println(" Input must be an integer(1-12)");
                input.nextLine();
            }

        }
    }
    /**
     Prompts first name, last name and expenses from user. Validates expenses.
     parameters- None
     Return- A Passenger object with first name, last name and expenses
     */
    public static Passenger getDetails () {
        Scanner input = new Scanner (System.in);
        System.out.print("\nEnter your first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();
        while (true) {
            try {     //Validating if the entered expense is a double
                System.out.print("Enter your expenses: ");
                double expenses = input.nextDouble();
                if (expenses > 0){
                    Passenger passDetails = new Passenger(firstName,lastName, expenses);
                    return passDetails;
                }else{
                    System.out.println("Invalid Input !!! Try Again");          //Validating if expenses > 0
                }
            } catch ( InputMismatchException e) {
                System.out.println("Invalid Input !!! Try Again");
                input.nextLine();
            }
        }
    }

    /**
     Displays the Cabins with passenger details in it. Displays empty if cabins are empty.
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void viewAllCabins (Cabin [] pCabinNo) {
        System.out.println("\n                                             Cabins in the Cruise Ship\n");
        for (int i = 0; i < pCabinNo.length; i++ ) {
            if (!pCabinNo[i].getAvailability().equals("Empty")){        //Display passenger details if cabins are not empty
                System.out.println("Cabin " + (i+1));
                for ( int j = 0; j < 3; j++ ) {
                    if (pCabinNo[i].getPassengers()[j] != null) {
                        System.out.println("    Passenger " + (j+1) + "- " + pCabinNo[i].getPassengers()[j].toString() );
                    } else {
                        System.out.println("    Passenger " + (j+1) + "- Empty" );
                    }
                }
                System.out.println(" ");
            }else{
                System.out.println("Cabin " + (i+1) + "- Empty");        //Display empty for empty cabins
            }
        }
    }

    /**
     Displays empty cabins.
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void displayEmptyCabins (Cabin [] pCabinNo) {
        System.out.println("\n                                            Empty Cabins\n");
        for (int i = 0; i < pCabinNo.length ; i++ ) {
            if (pCabinNo[i].getAvailability().equals("Empty")) {              //Checking if the cabins are empty
                System.out.println("Cabin number " + (i+1));
            }
        }
    }

    /**
     Prompts first name from user and deletes the passenger from cabin he is in.
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void deleteCustomer (Cabin [] pCabinNo){
        Scanner input = new Scanner(System.in);
        boolean isFound = false;

        System.out.print("Enter the Customer first name you want to remove: ");
        String cusName = input.nextLine();
        for (int i = 0; i < pCabinNo.length; i++ ) {
            for (int j = 0; j < 3; j++) {
                if (pCabinNo[i].getPassengers()[j] != null) {
                    if (pCabinNo[i].getPassengers()[j].getFName().equalsIgnoreCase(cusName)) {
                        isFound = true;
                        pCabinNo[i].getPassengers()[j] = null;
                        System.out.println("\nPassenger " + cusName + " got successfully removed!");
                        if (pCabinNo[i].isCabinEmpty()){            //Checking if the cabin is empty and if so change availability into "Empty"
                            pCabinNo[i].setAvailability("Empty");
                        }
                    }
                }
            }
        }
        if (!isFound) {
            System.out.println("Sorry ! The passenger you are looking for is not in the list");
        }
    }

    /**
     Finds Cabin number by prompting first name from user
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void findCustomers (Cabin [] pCabinNo) {
        Scanner input = new Scanner(System.in);
        boolean isFound = false;

        System.out.print("Enter customer name: ");
        String cusName = input.nextLine();
        for (int i = 0; i < pCabinNo.length; i++ ) {
            for (int j = 0; j < 3; j++) {
                if (pCabinNo[i].getPassengers()[j] != null) {
                    if (pCabinNo[i].getPassengers()[j].getFName().equalsIgnoreCase(cusName)){       //Comparing first names
                        isFound = true;
                        System.out.println("Customer is in Cabin " + (i+1));
                    }
                }
            }
        }
        if (!isFound){     //If the entered name is not in the list
            System.out.println("The passenger you are looking for is not in the list");
        }
    }

    /**
     Writing all the cabin and passenger details in to a file
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void writingToFile (Cabin [] pCabinNo) {
        int i = 0;
        try {
            FileWriter myWriter = new FileWriter("CabinDetails.txt");     //Create a FileWriter class object, and specify the filename
            for ( i = 0; i < pCabinNo.length; i++ ) {
                if (!pCabinNo[i].getAvailability().equals("Empty")){
                    myWriter.write("Cabin " + (i+1) + "\n");           //Writing to file
                    for ( int j = 0; j < 3; j++ ) {
                        if (pCabinNo[i].getPassengers()[j] != null) {
                            myWriter.write("     Passenger " + (j+1) + "- " + pCabinNo[i].getPassengers()[j].toString() + "\n" );
                        }
                        else{
                            myWriter.write("     Passenger " + (j+1) + "- Empty\n");
                        }
                    }
                }
                else{
                    myWriter.write("Cabin " + (i+1) + "- Empty\n");
                }
            }
            myWriter.close();       //Closing file
            System.out.println("Congratulations!! Your data has successfully wrote to the cabinDetails.txt file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**
     Loads and displays written file to user
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void readFile (Cabin [] pCabinNo) {
        int lineCount = 1;
        try {
            File inputFile = new File("CabinDetails.txt");        //Specifies file name
            Scanner rf = new Scanner(inputFile);           //Reads input from file
            String fileLine;

            while (rf.hasNext()) {       //Starts reading single line at a time
                fileLine = rf.nextLine();
                System.out.println(fileLine);        //Display line
                lineCount++;
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**
     Sorts passengers alphabetically by first name and displays
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void sortingPassengers (Cabin [] pCabinNo){
        System.out.println("Alphabetically Ordered Passenger List");
        String fNames = passengerString(pCabinNo);

        String[] tempPassengers = fNames.split(",");        //Adding first names to an array by splitting by commas
        for (int i = 0; i < tempPassengers.length; i++) {
            for (int j = 0; j < tempPassengers.length - 1; j++) {
                if (tempPassengers[j].compareTo(tempPassengers[j+1]) > 0) {      //Compares Strings lexicograhically using toString()
                    //Swap j with j+1
                    String temp = tempPassengers[j];
                    tempPassengers[j] = tempPassengers[j+1];
                    tempPassengers[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < tempPassengers.length; i++) {
            System.out.println("   Customer Name: " + tempPassengers[i]);
        }
    }

    /**
     Creates a String with first names
     parameters- Array of 12 Cabin Objects
     Return- String of cabin names
     */
    public static String passengerString (Cabin [] pCabinNo){
        String firstNames = "";
        for (int i = 0; i < pCabinNo.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (pCabinNo[i].getPassengers()[j] != null) {
                    firstNames += pCabinNo[i].getPassengers()[j].getFName() + ",";   //Creating a string of first names separated by commas
                }
            }
        }
        return firstNames;
    }

    /**
     Displays expenses per customer and total expenses
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void expenses (Cabin [] pCabinNo) {
        System.out.println("Passenger Expenses: ");
        for (int i = 0; i < pCabinNo.length; i++ ) {
            if (!pCabinNo[i].getAvailability().equals("Empty")){
                for ( int j = 0; j < 3; j++ ) {
                    if (pCabinNo[i].getPassengers()[j] != null) {
                        System.out.println("       " + pCabinNo[i].getPassengers()[j].toString() );    //Displaying expenses per customer
                    }
                }
            }
        }
        System.out.println("\n\nTotal Expenses for passengers: " + totalExpenses(pCabinNo));     //Calling a method to calculate total expenses
    }

    /**
     Calculates total expenses
     parameters- Array of 12 Cabin Objects
     Return- total expenses
     */
    public static double totalExpenses (Cabin [] pCabinNo) {
        double total = 0;
        for (int i = 0; i < pCabinNo.length; i++ ) {
            if (!pCabinNo[i].getAvailability().equals("Empty")) {
                for (int j = 0; j < 3; j++) {
                    if (pCabinNo[i].getPassengers()[j] != null) {
                        total += pCabinNo[i].getPassengers()[j].getExpenses();    //Calculating total
                    }
                }
            }
        }
        return total;
    }
}

