public class Passenger {
    private String fName;
    private String lName;
    private double expenses;

    //Default Constructor
    public Passenger (){

    }

    //Constructor for first name, last name, expenses
    public Passenger ( String fName, String lName, double expenses ){
        this.fName = fName;
        this.lName = lName;
        this.expenses = expenses;
    }

    /**
     Returns first name
     parameters- None
     Return- first name
     */
    public String getFName() {
        return fName;
    }

    /**
     Returns last name
     parameters- None
     Return- Last name
     */
    public double getExpenses() {
        return expenses;
    }

    /**
     Overriding toString method to create a string with passenger details
     Parameters- None
     Return- String
     */
    @Override
    public String toString () {
        return " First Name: " + fName +
                "  Last Name: " + lName +
                "  Expenses: " + expenses;
    }

}
