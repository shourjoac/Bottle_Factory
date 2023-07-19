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
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a linked queue storing objects of type Bottle. Bottle are added and
 * removed with respect to the First In First Out (FIFO) scheduling policy.
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {

    private LinkedNode<Bottle> front; // indicates the beginning in the linked list queue, we
    // remove from this index
    private LinkedNode<Bottle> back;  // indicates the end of the linked list queue, we add to
    // the end of the queue
    private int size; // number if bottles in the queue
    private int capacity; // maximum number of bottles the queue can hold

    /**
     * Initializes the fields of this queue including its capacity.
     * A newly created queue is empty, meaning that both its front and back are null and its
     * size is zero.
     *
     * @param capacity Positive integer defining the max number of bottles the queue can hold
     * @throws IllegalArgumentException when the capacity is not positive
     *                                  (meaning less or equal to zero).
     */
    public LinkedBottleQueue(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) { // checks when the capacity is not positive
            throw new IllegalArgumentException("Non-Positive Capacity");
        }
        front = null; //front set to null
        back = null;//back set to null
        size = 0;
        this.capacity = capacity;
    }

    /**
     * Checks and returns true if the queue is empty
     *
     * @return true if there are no bottle elements in the linked list, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0; // checks and returns true if there are no bottle elements in the linked list
    }

    /**
     * Checks if the size has hit the maximum capacity that the queue can hold.
     *
     * @return true is the size is equal to the capacity, false otherwise
     */
    @Override
    public boolean isFull() {
        return size == capacity;
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
     * @param bottle bottle to add to queue
     * @throws IllegalStateException if the queue is full
     * @throws NullPointerException  if the bottle (argument) is null
     */
    @Override
    public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
        if (size == capacity) { //checks if the queue is full
            throw new IllegalStateException("No place left");
        }
        if (bottle == null) { //checks if the bottle (argument) is null
            throw new NullPointerException("Bottle is null");
        }
        LinkedNode<Bottle> toBeAdded = new LinkedNode<Bottle>(bottle);
        if (size == 0) {
            front = toBeAdded;
            back = toBeAdded;
        } else {
            back.setNext(toBeAdded);
            back = toBeAdded;
        }
        size++; // increments the size data field
    }

    /**
     * Removes and returns the first bottle in the queue
     *
     * @return Top/First bottle in the queue
     * @throws NoSuchElementException when queue is empty
     */
    @Override
    public Bottle dequeue() throws NoSuchElementException {
        if (size == 0) { //checks if queue is empty
            throw new NoSuchElementException("No element in the Queue");
        }
        if (size == 1) { // checks if there is only one element in the linked list
            LinkedNode<Bottle> toBeRemoved = front;
            front = null;
            size--;
            back = null;
            return toBeRemoved.getData();
        } else { // deletes from the start of the list
            LinkedNode<Bottle> toBeRemoved = front;
            front = front.getNext();
            size--;
            return toBeRemoved.getData();
        }
    }

    /**
     * Returns the first bottle in the queue without removing it
     *
     * @return Top/First bottle in the queue
     * @throws NoSuchElementException When queue is empty
     */
    @Override
    public Bottle peek() throws NoSuchElementException {
        if (size == 0) { //checks if the queue is empty
            throw new NoSuchElementException("Nothing to peak at");
        }
        LinkedNode<Bottle> temp = front;//sets temp to front node
        return temp.getData();
    }


    /**
     * Returns a deep copy of this queue.
     *
     * @return deep copy of this queue
     */
    @Override
    public QueueADT<Bottle> copy() {
        LinkedBottleQueue copyQueue = new LinkedBottleQueue(capacity);
        if (front == null) {
            return null; //if it is empty
        }
        copyQueue.enqueue(front.getData());
        LinkedNode<Bottle> temp1 = this.front.getNext();
        while (temp1 != null) { // iterates until there is no next node
            copyQueue.enqueue(temp1.getData());
            temp1 = temp1.getNext(); //obtains the next node
        }
        return copyQueue;
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
        LinkedNode<Bottle> temp = front; //sets temp the front node
        LinkedNode<Bottle> temp1 = front.getNext();
        while (temp != null) {// iterates until there is no next node
            if (temp1 != null) {
                requiredOutput += temp.getData().toString() + "\n"; //forms the required string
                temp = temp.getNext();
                temp1 = temp1.getNext();
            } else if (temp1 == null) {
                requiredOutput += temp.getData().toString(); // if it is the last node then it prints it
                temp = temp.getNext();
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
        return new BottleQueueIterator(this); //Returns an iterator for traversing the queue's items
    }
}

