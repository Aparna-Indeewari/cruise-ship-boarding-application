import java.util.NoSuchElementException;

public class Queue {
    private int front, rear;
    private Passenger [] passengerQueue = new Passenger[5];        //Creating an array of Passengers for queue

    public Queue() {
        this.front = this.rear = -1;     //Initializing front and rear into -1
    }

    /**
     Add passengers to queue
     Parameters- Passenger object with first name, last name and expenses
     Return- None
     */
    public void enqueue (Passenger passenger) {
        if (isEmpty())          //Checks if the queue is empty
            front ++;
        rear = (rear+1) % passengerQueue.length;
        passengerQueue[rear] = passenger;
    }

    /**
     Check if the queue is empty
     Parameters- None
     Return- True if empty, False if not
     */
    public boolean isEmpty () {
        return front == -1;
    }

    /**
     Check if the queue is full
     Parameters- None
     Return- True if full, False if not
     */
    public boolean isFull () {
        return (rear+1) % passengerQueue.length == front;
    }

    /**
     Returns Passenger object of the first in queue
     Parameters- None
     Return- True if empty, False if not
     */
    public Passenger dequeue () {
        if (isEmpty())
            throw new  NoSuchElementException();
        Passenger temp = passengerQueue[front];
        passengerQueue[front] = null;

        if (front == rear)
            front = rear -1;
        else
            front = (front+1) % passengerQueue.length;
        return temp;
    }
}
