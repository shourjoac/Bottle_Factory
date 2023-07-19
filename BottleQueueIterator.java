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
 * This class models an iterator to iterate over a queue of Bottle objects. When the queue is not
 * empty, Bottle objects are iterated over from the front to the back of the queue. No more Bottle
 * objects are returned by this iterator when all the Bottle objects are traversed (returned).
 * This Iterator iterates over any queue which implements the QueueADT<Bottle> interface.
 * It uses the QueueADT.isEmpty() and QueueADT.dequeue() methods to iterate over a deep copy of the
 * queue.
 */
public class BottleQueueIterator implements Iterator<Bottle> {

    private QueueADT<Bottle> bottleQueue; // queue of the bottle to be iterated over

    /**
     * Initializes the instance fields. The bottle queue of this iterator MUST be initialized to a
     * deep copy of the queue passed as input.
     *
     * @param queue The queue to iterate over, should implement the QueueADT interface.
     * @throws IllegalArgumentException when queue is null
     */
    public BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException {
        if (queue == null) { //checks if the queue is null
            throw new IllegalArgumentException("Given Queue is null");
        }
        bottleQueue = queue.copy(); //the bottleQueue is initialized to the deep copy of the queue
    }

    /**
     * Returns true if there is the iteration is not yet exhausted, meaning at least one bottle is
     * not iterated over
     *
     * @return true if there is a bottle that has not been iterated over yet, false otherwise
     */
    @Override
    public boolean hasNext() {
        return !bottleQueue.isEmpty(); // true if there is the iteration is not yet exhausted
    }

    /**
     * Returns the next bottle in the iteration
     *
     * @return Bottle The next bottle in the iteration
     * @throws NoSuchElementException if there are no more elements in the iteration
     */
    @Override
    public Bottle next() throws NoSuchElementException {
        if (!hasNext()) { //checks if there are no more elements in the iteration
            throw new NoSuchElementException("empty stack");
        }
        return bottleQueue.dequeue(); //returns the next bottle in the iteration
    }
}
