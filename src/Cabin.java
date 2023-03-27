public class Cabin {
    private String availability;
    private Passenger[] passengers = new Passenger[3];  //Creating an array for 3 passengers in the cabin

    /**
     Constructor to add availability
     */
    public Cabin (String availability){
        this.availability = availability;
    }

    public void addPassengersToArray (Passenger passenger){
        for (int i = 0; i < passengers.length; i++){
            if (passengers[i] == null){
                passengers[i] = passenger;
                break;
            }
        }
    }

    /**
     Checking if the cabin is full.
     parameters- None
     Return- Boolean true if cabin is full or false if not
     */
    public boolean isCabinFull() {
        return passengers [0] != null && passengers [1] != null && passengers [2] != null;
    }

    /**
     Checking if the cabin is empty.
     parameters- None
     Return- Boolean true if cabin is empty or false if not
     */
    public boolean isCabinEmpty(){
        return passengers [0] == null && passengers [1] == null && passengers [2] == null;
    }

    /**
     Checking if passenger 1 filled.
     parameters- None
     Return- Boolean true if a cabin consist of at least 1 passenger
     */
    public boolean isPassenger1Full() {
        return passengers [0] != null && passengers [1] == null && passengers [2] == null;
    }

    /**
     Returns passenger array objects
     parameters- None
     Return- passenger objects
     */
    public Passenger[] getPassengers() {
        return passengers;
    }

    /**
     Returns availability
     parameters- None
     Return- String assigned to availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     Sets availability to string passed from parameter
     parameters- A string(empty or occupied)
     Return- None
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
