//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 Bottle Factory
// Course:   CS 300 Spring 2023
//
// Author:   Shourjo Aditya Chaudhuri
// Email:    sachaudhuri@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    No Pair Programming in this project
// Partner Email:   No Pair Programming in this project
// Partner Lecturer's Name: No Pair Programming in this project
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:   None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * This class models an circular-indexing array queue which stores elements of type Bottle.
 */
public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
    private Bottle[] bottles; // array of bottle objects
    private int front; //indicates the earliest added bottle
    private int back; // indicates the recently added bottle
    private int size; // indicates the number of bottles in the queue

    /**
     * Constructs a CircularBottleQueue object, initializing its data fields as follows:
     * the bottles oversize array to an empty array of Bottle objects whose length is the input
     * capacity,
     * its size to zero, and
     * both its front and back to -1.
     *
     * @param capacity Positive integer defining the max number of bottles the queue can hold
     * @throws IllegalArgumentException when the capacity is not positive
     *                                  (meaning less or equal to zero).
     */
    public CircularBottleQueue(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) { // if the capacity is not positive
            throw new IllegalArgumentException("Invalid Capacity");
        }
        this.bottles = new Bottle[capacity];
        this.front = -1;
        this.back = -1;
        this.size = 0;
    }

    /**
     * Checks and returns true if the queue is empty
     *
     * @return true if there are no bottle elements in the linked list, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0; //returns true if there are no bottle elements in the linked list
    }

    /**
     * Checks if the size has hit the maximum capacity that the queue can hold.
     *
     * @return true is the size is equal to the capacity, false otherwise
     */
    @Override
    public boolean isFull() {
        return size == bottles.length;
    }

    /**
     * Returns the number of elements (size) present in the queue
     *
     * @return size of the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Add a bottle to the end of the queue
     *
     * @param bottle bottle object to be added
     * @throws IllegalStateException if the queue is full
     * @throws NullPointerException  if the bottle (argument) is null
     */
    @Override
    public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
        if (isFull()) { //checks if the queue is full
            throw new IllegalStateException("Array is full");
        }
        if (bottle == null) { //checks if the bottle (argument) is null
            throw new NullPointerException("Bottle to add is null");
        }
        if (isEmpty()) {
            front++;
        }
        back = (back + 1) % bottles.length; //next index to add
        bottles[back] = bottle;
        size++; //increments in the size
    }

    /**
     * Removes and returns the first bottle in the queue.
     *
     * @return Top/First bottle in the queue
     * @throws NoSuchElementException when queue is empty
     */
    @Override
    public Bottle dequeue() throws NoSuchElementException {
        if (isEmpty()) { //checks if the queue is empty
            throw new NoSuchElementException("Array is empty");
        }
        Bottle toBeDequeued = bottles[front];
        if (front == back) {
            bottles[front] = null;
            front = -1;
            back = -1;
        } else {
            bottles[front] = null;
            front = (front + 1) % bottles.length; // next index to remove from
        }
        size--; // decrement size
        return toBeDequeued;
    }

    /**
     * Returns the first bottle in the queue without removing it
     *
     * @return Top/First bottle in the queue
     * @throws NoSuchElementException when queue is empty
     */
    @Override
    public Bottle peek() throws NoSuchElementException {
        if (isEmpty()) { //checks if the queue is empty
            throw new NoSuchElementException("Nothing to peek at!");
        }
        return bottles[front]; //the first bottle is obtained and returned
    }

    /**
     * Returns a deep copy of this queue.
     *
     * @return deep copy of this queue
     */
    @Override
    public QueueADT<Bottle> copy() {
        //a deep copy of the queue is made
        CircularBottleQueue copiedQueue = new CircularBottleQueue(this.bottles.length);
        copiedQueue.bottles = Arrays.copyOf(this.bottles, this.bottles.length);
        //appropriately changing the size, front, and back variables of the copy
        copiedQueue.size = this.size;
        copiedQueue.front = this.front;
        copiedQueue.back = this.back;
        return copiedQueue; //returns the deep copy of this queue
    }

    /**
     * Returns a string representation of the queue from the front to its back with the string
     * representation of each Bottle in a separate line.
     *
     * @return String in expected format, empty string when queue is empty
     */
    @Override
    public String toString() {
        String requiredOutput = "";
        int index = front;
        int indexAhead = front + 1;
        int count = 0; // to keep track about whether the following loop ends after one cycle of iteration
        boolean newLine = false;
        while (bottles[index] != null && count < size) {
            if (!newLine && bottles[indexAhead] != null) {
                requiredOutput += bottles[index].toString() + "\n"; //forms the required output
                index = (index + 1) % bottles.length; //does circular indexing
                count++;//increments count
                if (count < size - 1) {
                    indexAhead = (indexAhead + 1) % bottles.length; //does circular indexing
                } else {
                    newLine = true;
                }
            } else if (newLine) {
                requiredOutput += bottles[index].toString();
                index = (index + 1) % bottles.length;
                count++;//increments count
            }
        }
        return requiredOutput;
    }

    /**
     * Returns an iterator for traversing the queue's items
     *
     * @return iterator to traverse the LinkedListQueue
     */
    @Override
    public Iterator<Bottle> iterator() {
        return new BottleQueueIterator(this); //an iterator is returned
    }
}
