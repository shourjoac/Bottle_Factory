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

import java.util.NoSuchElementException;

/**
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 */
public class BottleFactoryTester {

    /**
     * Ensures the correctness of the constructor and methods defined in the Bottle class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean bottleTester() {
        // test equals method
        // (1) When two bottle have same serial number
        {
            Bottle.resetBottleCounter();
            Bottle bottle1 = new Bottle("black");
            Bottle.resetBottleCounter();
            Bottle bottle2 = new Bottle("black");
            boolean actual = bottle1.equals(bottle2);
            boolean expected = true;
            if (actual != expected) return false;
        }
        //(2) When two bottles have different serial numbers
        {
            Bottle.resetBottleCounter();
            Bottle bottle1 = new Bottle("black");
            ;
            Bottle bottle2 = new Bottle("brown");
            boolean actual = bottle1.equals(bottle2);
            boolean expected = false;
            if (actual != expected) return false;
        }
        // test toString method
        // (1) testing to string with initial conditions - empty and open
        {
            Bottle.resetBottleCounter();
            Bottle.resetBottleCounter();
            Bottle bottle1 = new Bottle("black");
            String expected = "SN1black:Empty:Open";
            String actual = bottle1.toString();
            if (!expected.equals(actual)) return false;

        }
        // (2) testing to string with after filling and sealing the bottle
        { // note: we did not reset the counter here
            Bottle bottle1 = new Bottle("yellow");
            bottle1.fillBottle();
            bottle1.sealBottle();
            String expected = "SN2yellow:Filled:Capped";
            String actual = bottle1.toString();
            if (!expected.equals(actual)) return false;
        }
        return true;
    }

    /**
     * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean linkedBottleQueueTester() {
        // test constructor - verify fields and exception behavior (when capacity is invalid)
        {
            try {
                LinkedBottleQueue temp = new LinkedBottleQueue(-5);
                return false;
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
                return false;
            }
        }
        // when capacity is valid
        {
            try {
                LinkedBottleQueue temp = new LinkedBottleQueue(6);
                int expectedSize = 0;
                int actualSize = temp.size();
                boolean expectedIsFull = false;
                boolean actualIsFull = temp.isFull();
                if (expectedSize != actualSize || expectedIsFull != actualIsFull) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        /* test enqueue, dequeue and peek methods using different scenarios
         * 1) all methods on an empty queue
         * 2) all methods on a full queue
         * 3) all methods on a partially filled queue
         * 4) Verify queue contents (using peek and size) after a sequence of
         *    enqueue-dequeue and dequeue-enqueue
         * 5) Enqueue until queue is full and dequeue until queue is empty
         */
        // test for empty queue
        {
            LinkedBottleQueue temp = new LinkedBottleQueue(7);
            if (!temp.isEmpty()) {
                return false; // testing isEmpty() method returns true
            }
            // testing peek
            try {
                if (temp.peek() != null) return false;
            } catch (NoSuchElementException e) {
            } catch (Exception e) {
                return false;
            }

            //testing dequeue
            try {
                temp.dequeue();
                return false;
            } catch (NoSuchElementException e) {
            } catch (Exception e) {
                return false;
            }

            // testing enqueue
            try {
                temp.enqueue(new Bottle("black")); // adding 1st element
            } catch (Exception e) {
                return false;
            }
        }
        // test for partially filled queue
        {
            Bottle.resetBottleCounter();
            LinkedBottleQueue temp = new LinkedBottleQueue(4);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("blue");
            temp.enqueue(bottle1); // adding 1st element
            temp.enqueue(bottle2); // adding 2nd element
            temp.enqueue(bottle3); // adding 3rd element
            //  Queue : bottle1 , bottle2, bottle3
            // testing isEmpty() method
            if (temp.isEmpty()) {
                return false;
            }
            // testing peek
            try {
                if (temp.peek() != bottle1) return false;
            } catch (Exception e) {
                return false;
            }
            //testing dequeue
            try {
                Bottle returned = temp.dequeue();
                if (returned != bottle1) ;
            } catch (Exception e) {
                return false;
            }
            //  Queue : bottle2, bottle3
            // testing enqueue
            try {
                temp.enqueue(bottle1); // adding bottle1 again after removing it before
            } catch (Exception e) {
                return false;
            }
            //  Queue : bottle2, bottle 3, bottle 1
        }
        // test for completely filled queue
        {
            Bottle.resetBottleCounter();
            LinkedBottleQueue temp = new LinkedBottleQueue(4);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            Bottle bottle4 = new Bottle("yellow");
            temp.enqueue(bottle1); // adding 1st element
            temp.enqueue(bottle2); // adding 2nd element
            temp.enqueue(bottle3); // adding 3rd element
            temp.enqueue(bottle4); // adding 4th element
            // Queue : bottle1, bottle2, bottle3, bottle4
            if (temp.isEmpty()) {
                return false;
            }
            // testing peek
            try {
                if (temp.peek() != bottle1) return false;
            } catch (Exception e) {
                return false;
            }
            //testing dequeue
            try {
                Bottle returned = temp.dequeue();
                if (returned != bottle1) ;
            } catch (Exception e) {
                return false;
            }
            //  Queue : bottle2, bottle3, bottle 4
            // testing enqueue
            try {
                temp.enqueue(bottle1); // adding bottle1 again after removing it before
            } catch (Exception e) {
                return false;
            }
        }
        //Verifying size and peek after dequeue and enqueue
        {
            Bottle.resetBottleCounter();
            LinkedBottleQueue temp = new LinkedBottleQueue(9);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            Bottle bottle4 = new Bottle("red");
            Bottle bottle5 = new Bottle("white");
            Bottle bottle6 = new Bottle("blue");
            temp.enqueue(bottle1); // adding 1st element
            temp.enqueue(bottle2); // adding 2nd element
            temp.enqueue(bottle3); // adding 3rd element
            temp.enqueue(bottle4); // adding 4th element
            temp.enqueue(bottle5); // adding 5th element
            temp.enqueue(bottle6); // adding 6th element
            // Queue: bottle1, bottle2, bottle3, bottle4, bottle5, bottle6
            try {
                int expectedSize = 6;
                int actualSize = temp.size();
                Bottle actualPeek = temp.peek();
                Bottle expectedPeek = bottle1;
                if (expectedSize != actualSize || expectedPeek != actualPeek) return false;
            } catch (Exception e) {
                return false;
            }
            temp.dequeue();
            temp.dequeue();
            // Queue: bottle3, bottle4, bottle5, bottle6
            try {
                int expectedSize = 4;
                int actualSize = temp.size();
                Bottle actualPeek = temp.peek();
                Bottle expectedPeek = bottle3;
                if (expectedSize != actualSize || expectedPeek != actualPeek) return false;
            } catch (Exception e) {
                return false;
            }
            temp.enqueue(bottle2);
            // Queue: bottle3, bottle4, bottle5, bottle6, bottle2
            try {
                int expectedSize = 5;
                int actualSize = temp.size();
                Bottle actualPeek = temp.peek();
                Bottle expectedPeek = bottle3;
                if (expectedSize != actualSize || expectedPeek != actualPeek) return false;
            } catch (Exception e) {
                return false;
            }
        }
        // Enqueuing until queue is full and dequeueing until empty
        {
            Bottle.resetBottleCounter();
            LinkedBottleQueue temp = new LinkedBottleQueue(3);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            Bottle bottle4 = new Bottle("red");
            Bottle bottle5 = new Bottle("white");
            try {
                temp.enqueue(bottle1);
                temp.enqueue(bottle2);
                temp.enqueue(bottle3);
            } catch (Exception e) {
                return false;
            }
            if (!temp.isFull()) return false; // confirming that queue is full
            // now queue is full, so adding another element would throw IllegalStateException
            try {
                temp.enqueue(bottle4);
                return false;
            } catch (IllegalStateException e) {
            } catch (Exception e) {
                return false;
            }
            try {
                temp.dequeue();
                temp.dequeue();
                temp.dequeue();
            } catch (Exception e) {
                return false;
            }
            if (!temp.isEmpty()) return false; // checking if queue is empty
            // now queue is empty, so removing an element would throw NoSuchElementException
            try {
                temp.dequeue();
                return false;
            } catch (NoSuchElementException e) {

            } catch (Exception e) {
                return false;
            }
        }
        // test copy method
        {
            Bottle.resetBottleCounter();
            LinkedBottleQueue temp = new LinkedBottleQueue(3);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            temp.enqueue(bottle1);
            temp.enqueue(bottle2);
            temp.enqueue(bottle3);
            QueueADT<Bottle> copiedQueue = temp.copy();
            if (copiedQueue instanceof LinkedBottleQueue) {
                copiedQueue = (LinkedBottleQueue) copiedQueue;
            } else {
                return false;
            }
            try {
                Bottle expectedRemoved1 = bottle1;
                Bottle expectedRemoved2 = bottle2;
                Bottle expectedRemoved3 = bottle3;
                Bottle actualRemoved1 = copiedQueue.dequeue();
                Bottle actualRemoved2 = copiedQueue.dequeue();
                Bottle actualRemoved3 = copiedQueue.dequeue();
                if (actualRemoved1 != expectedRemoved1 || expectedRemoved2 != actualRemoved2 ||
                        actualRemoved3 != expectedRemoved3) return false;
            } catch (Exception e) {
                return false;
            }
            // let us verify that the size of the actual queue is 3 (we haven't modified the actual
            //queue
            if (temp.size() != 3) return false;
        }
        // test toString method
        {
            Bottle.resetBottleCounter();
            LinkedBottleQueue temp = new LinkedBottleQueue(3);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            temp.enqueue(bottle1);
            temp.enqueue(bottle2);
            temp.enqueue(bottle3);
            String actual = temp.toString();
            String expected = "SN1black:Empty:Open" + "\n" + "SN2brown:Empty:Open" + "\n" +
                    "SN3yellow:Empty:Open";
            if (!actual.equals(expected)) return false;
        }
        return true;
    }

    /**
     * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean circularBottleQueueTester() {
        // test constructor - verify fields and exception behavior
        {
            try {
                CircularBottleQueue temp = new CircularBottleQueue(-4);
                return false;
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
                return false;
            }
        }
        // when capacity is valid
        {
            try {
                CircularBottleQueue temp = new CircularBottleQueue(4);
                int expectedSize = 0;
                int actualSize = temp.size();
                boolean expectedIsFull = false;
                boolean actualIsFull = temp.isFull();
                if (expectedSize != actualSize || expectedIsFull != actualIsFull) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        /* test enqueue, dequeue and peek methods using different scenarios
         * 1) all 3 methods on an empty queue
         * 2) all 3 methods on a full queue
         * 3) all 3 methods on a partially filled queue
         * 4) Verify queue contents and size after a sequence of enqueue-dequeue and
         *    dequeue-enqueue
         * 5) Enqueue until queue is full and dequeue until queue is empty
         */
        // test for empty queue
        {
            CircularBottleQueue temp = new CircularBottleQueue(7);
            if (!temp.isEmpty()) {
                return false; // testing isEmpty() method returns true
            }
            // testing peek
            try {
                if (temp.peek() != null) return false;
            } catch (NoSuchElementException e) {
            } catch (Exception e) {
                return false;
            }
            //testing dequeue
            try {
                temp.dequeue();
                return false;
            } catch (NoSuchElementException e) {
            } catch (Exception e) {
                return false;
            }
            // testing enqueue
            try {
                temp.enqueue(new Bottle("black")); // adding 1st element
            } catch (Exception e) {
                return false;
            }
        }
        // test for partially filled queue
        {
            Bottle.resetBottleCounter();
            CircularBottleQueue temp = new CircularBottleQueue(8);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            temp.enqueue(bottle1); // adding 1st element
            temp.enqueue(bottle2); // adding 2nd element
            temp.enqueue(bottle3); // adding 3rd element
            //  Queue : bottle1 , bottle2, bottle3
            // testing isEmpty() method
            if (temp.isEmpty() == true) {
                return false;
            }
            // testing peek
            try {
                if (temp.peek() != bottle1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }

            //testing dequeue
            try {
                Bottle returned = temp.dequeue();
                if (returned != bottle1) return false;
            } catch (Exception e) {
                return false;
            }

            //  Queue : bottle2, bottle3

            // testing enqueue
            try {
                temp.enqueue(bottle1); // adding bottle1 again after removing it before
            } catch (Exception e) {
                return false;
            }

            //  Queue : bottle2, bottle3, bottle 1
        }

        // test for completely filled queue
        {
            Bottle.resetBottleCounter();
            CircularBottleQueue temp = new CircularBottleQueue(3);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            temp.enqueue(bottle1); // adding 1st element
            temp.enqueue(bottle2); // adding 2nd element
            temp.enqueue(bottle3); // adding 3rd element

            // Queue : bottle1, bottle2, bottle3

            if (temp.isEmpty()) {
                return false;
            }

            // testing peek
            try {
                if (temp.peek() != bottle1) return false;
            } catch (Exception e) {
                return false;
            }

            //testing dequeue
            try {
                Bottle returned = temp.dequeue();
                if (returned != bottle1) ;
            } catch (Exception e) {
                return false;
            }

            //  Queue : bottle2, bottle3

            // testing enqueue
            try {
                temp.enqueue(bottle1); // adding bottle1 again after removing it before
            } catch (Exception e) {
                return false;
            }

        }
        //Verifying size and peek after dequeue and enqueue
        {
            Bottle.resetBottleCounter();
            CircularBottleQueue temp = new CircularBottleQueue(9);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            Bottle bottle4 = new Bottle("red");
            Bottle bottle5 = new Bottle("white");
            Bottle bottle6 = new Bottle("blue");
            temp.enqueue(bottle1); // adding 1st element
            temp.enqueue(bottle2); // adding 2nd element
            temp.enqueue(bottle3); // adding 3rd element
            temp.enqueue(bottle4); // adding 4th element
            temp.enqueue(bottle5); // adding 5th element
            temp.enqueue(bottle6); // adding 6th element

            // Queue: bottle1, bottle2, bottle3, bottle4, bottle5, bottle6

            try {
                int expectedSize = 6;
                int actualSize = temp.size();
                Bottle actualPeek = temp.peek();
                Bottle expectedPeek = bottle1;
                if (expectedSize != actualSize || expectedPeek != actualPeek) return false;
            } catch (Exception e) {
                return false;
            }
            temp.dequeue();
            temp.dequeue();
            // Queue: bottle3, bottle4, bottle5, bottle6
            try {
                int expectedSize = 4;
                int actualSize = temp.size();
                Bottle actualPeek = temp.peek();
                Bottle expectedPeek = bottle3;
                if (expectedSize != actualSize || expectedPeek != actualPeek) return false;
            } catch (Exception e) {
                return false;
            }

            temp.enqueue(bottle2);

            // Queue: bottle3, bottle4, bottle5, bottle6, bottle2

            try {
                int expectedSize = 5;
                int actualSize = temp.size();
                Bottle actualPeek = temp.peek();
                Bottle expectedPeek = bottle3;
                if (expectedSize != actualSize || expectedPeek != actualPeek) return false;
            } catch (Exception e) {
                return false;
            }

        }

        // Enqueuing until queue is full and dequeueing until empty

        {
            Bottle.resetBottleCounter();
            CircularBottleQueue temp = new CircularBottleQueue(3);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            Bottle bottle4 = new Bottle("red");
            Bottle bottle5 = new Bottle("white");

            try {
                temp.enqueue(bottle1);
                temp.enqueue(bottle2);
                temp.enqueue(bottle3);
            } catch (Exception e) {
                return false;
            }

            if (!temp.isFull()) return false; // confirming that queue is full

            // now queue is full, so adding another element would throw IllegalStateException

            try {
                temp.enqueue(bottle4);
                return false;
            } catch (IllegalStateException e) {
            } catch (Exception e) {
                return false;
            }

            try {
                temp.dequeue();
                temp.dequeue();
                temp.dequeue();
            } catch (Exception e) {
                return false;
            }

            if (!temp.isEmpty()) return false; // checking if queue is empty

            // now queue is empty, so removing an element would throw NoSuchElementException
            try {
                temp.dequeue();
                return false;
            } catch (NoSuchElementException e) {

            } catch (Exception e) {
                return false;
            }

        }

        // test copy method

        {
            Bottle.resetBottleCounter();
            CircularBottleQueue temp = new CircularBottleQueue(3);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            temp.enqueue(bottle1);
            temp.enqueue(bottle2);
            temp.enqueue(bottle3);

            QueueADT<Bottle> copiedQueue = temp.copy();


            if (copiedQueue instanceof CircularBottleQueue) {
                copiedQueue = (CircularBottleQueue) copiedQueue;
            } else {
                return false;
            }

            try {
                Bottle expectedRemoved1 = bottle1;
                Bottle expectedRemoved2 = bottle2;
                Bottle expectedRemoved3 = bottle3;

                Bottle actualRemoved1 = copiedQueue.dequeue();
                Bottle actualRemoved2 = copiedQueue.dequeue();
                Bottle actualRemoved3 = copiedQueue.dequeue();


                if (actualRemoved1 != expectedRemoved1 || expectedRemoved2 != actualRemoved2 ||
                        actualRemoved3 != expectedRemoved3) {
                    return false;
                }

            } catch (Exception e) {
                return false;
            }

            // let us verify that the size of the actual queue is 3 (we haven't modified the actual
            //queue

            if (temp.size() != 3) return false;

        }

        // test toString method


        {
            Bottle.resetBottleCounter();
            CircularBottleQueue temp = new CircularBottleQueue(3);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            temp.enqueue(bottle1);
            temp.enqueue(bottle2);
            temp.enqueue(bottle3);
            String actual = temp.toString();

            String expected = "SN1black:Empty:Open" + "\n" + "SN2brown:Empty:Open" + "\n" +
                    "SN3yellow:Empty:Open";

            if (!actual.equals(expected)) return false;
        }

        {
            Bottle.resetBottleCounter();
            CircularBottleQueue temp = new CircularBottleQueue(3);
            Bottle bottle1 = new Bottle("black");
            Bottle bottle2 = new Bottle("brown");
            Bottle bottle3 = new Bottle("yellow");
            temp.enqueue(bottle1);
            temp.enqueue(bottle2);
            temp.enqueue(bottle3);
            temp.dequeue();
            String actual = temp.toString();

            String expected = "SN2brown:Empty:Open" + "\n" + "SN3yellow:Empty:Open";

            if (!actual.equals(expected)) return false;
        }


        return true;
    }

    /**
     * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator
     * class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean bottleQueueIteratorTester() {

        /*
         *  test 01: Create a LinkedBottleQueue with at least n bottles and
         *  use the bottleQueueIterator to traverse the queue. Verify if all
         *  the bottles are returned correctly
         */
        {
            try {
                Bottle.resetBottleCounter();
                LinkedBottleQueue temp = new LinkedBottleQueue(3);
                Bottle bottle1 = new Bottle("black");
                Bottle bottle2 = new Bottle("brown");
                Bottle bottle3 = new Bottle("yellow");
                temp.enqueue(bottle1);
                temp.enqueue(bottle2);
                temp.enqueue(bottle3);

                int iteration = 0;
                for (Bottle actual : temp) {
                    Bottle expected = bottle1;
                    if (iteration == 0) {
                        if (actual != expected) return false;
                        iteration++;
                    } else if (iteration == 1) {
                        expected = bottle2;
                        if (actual != expected) return false;
                        iteration++;
                    } else if (iteration == 2) {
                        expected = bottle3;
                        if (actual != expected) return false;
                    }
                }

                String actualText = "";

                for (Bottle actual : temp) {
                    actualText += actual.toString();
                }
                String expectedText = bottle1.toString() + bottle2.toString() + bottle3.toString();
                if (!actualText.equals(expectedText)) {
                    return false;
                }

                if (temp.size() != 3) return false;
            } catch (Exception e) {
                return false;
            }
        }

        /*
         *  test 02: Create a CircularBottleQueue with at least n bottles and
         *  use the bottleQueueIterator to traverse the queue. Verify if all
         *  the bottles are returned correctly
         */

        {
            try {
                Bottle.resetBottleCounter();
                CircularBottleQueue temp = new CircularBottleQueue(3);
                Bottle bottle1 = new Bottle("black");
                Bottle bottle2 = new Bottle("brown");
                Bottle bottle3 = new Bottle("yellow");
                temp.enqueue(bottle1);
                temp.enqueue(bottle2);
                temp.enqueue(bottle3);

                int iteration = 0;
                for (Bottle actual : temp) {
                    Bottle expected = bottle1;
                    if (iteration == 0) {
                        if (actual != expected) return false;
                        iteration++;
                    } else if (iteration == 1) {
                        expected = bottle2;
                        if (actual != expected) return false;
                        iteration++;
                    } else if (iteration == 2) {
                        expected = bottle3;
                        if (actual != expected) return false;
                    }
                }

                String actualText = "";

                for (Bottle actual : temp) {
                    actualText += actual.toString();
                }
                String expectedText = bottle1.toString() + bottle2.toString() + bottle3.toString();
                if (!actualText.equals(expectedText)) {
                    return false;
                }

                if (temp.size() != 3) return false;
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    /**
     * Runs all the tester methods defined in this class.
     *
     * @return true if no bugs are detected.
     */
    public static boolean runAllTests() {
        System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed!"));
        System.out.println("bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" :
                "Failed!"));
        System.out
                .println("linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" :
                        "Failed!"));
        System.out.println(
                "circularBottleQueueTester: " + (circularBottleQueueTester() ? "Pass" :
                        "Failed!"));

        return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester()
                && circularBottleQueueTester();
    }

    /**
     * Main method to run this tester class.
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    }

}
