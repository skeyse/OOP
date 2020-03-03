/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;
import java.util.*;

/**
 * Skeleton for creating your own FSM. 
 * To create your own FSM, do the following:
 * 1) Remove the existing "flower" states (Night, Morning, Afternoon)
 * 2) Add your own classes. They must be static classes derived from
 *    the State class, and must implement enter, exit, and updateAndCheckTransitions.
 * 3) Add any state variables you need to keep track of throughout the
 *    FSM (like the static int water below).
 * 4) Modify main to populate the array of State objects with an instance
 *    of each of your states.
 * @author jrsullins
 */
public class Test {
    
    private static int water = 3;
    
    /**
     * Abstract base class of all states. Note that it has
     * been made static so we can use it in the static main
     * method. Do not change this!
     */
    public static abstract class State {
        protected Scanner input = new Scanner(System.in);
        protected Random random = new Random();
        public State() {}
        public abstract void enter();
        public abstract void exit();
        public abstract int updateAndCheckTransitions();
    }
    
    /** 
     * Replace the below classes with your state classes. Each must be
     * static and must extend the State class, implementing enter, exit,
     * and updateAndCheckTranistions.
     */
    
    /**
     * This class represents the state in which the flower
     * is closed for the night. It transitions to Morning
     * when it is light out.
     */
    
    public static class Night extends State {
        public Night() {
            super();
        }
        public void enter() {
            System.out.println("The flower is closed.");
        }
        public void exit() {            
            System.out.println("Dawn is breaking...");
        }
        public int updateAndCheckTransitions() {        
            int light;
            System.out.print("Is it light out? (1 or 0) ");
            light = input.nextInt();
            if (light == 0) {
                System.out.println("The flower is still closed.");
                return 0;
            }
            else {
                return 1;
            } 
        }
    }
    
    /**
     * This class represents the state in which the flower is open and facing east. 
     * It transitions to Afternoon when the sun is to the west.
     */
    public static class Morning extends State {
        public Morning() {
            super();
        }
        
        public void enter() {
            System.out.println("The flower is open!");
        }
        public void exit() {            
            System.out.println("The flower is turning...");
        }
        public int updateAndCheckTransitions() { 
            int degrees;
            System.out.println("The flower is facing east.");
            System.out.print("Enter current position of sun in degrees:  ");
            degrees = input.nextInt();
            // If the sun is below 90 degrees, it is still morning
            if (degrees < 90) return 1;

            // If the sun is above 110 degrees, it is definitely afternoon (state 2)
            if (degrees >110) return 2;

            // If the sun is between 90 and 110, there is a chance that the flower 
            // will turn west. This chance is proportional to how close to 110 the sun is.
            else {
                // This will change the degrees to a number between 0 and 20.
                int earlyAfternoon = degrees - 90;  

                // Get a random number between 0 and 20.
                int randomNumber = random.nextInt(20);

                if (randomNumber < earlyAfternoon) {
                    return 2;  // Go to afternoon
                }
                else {
                    return 1;  // Stay in morning
                }
            }
        }
    }
    
    /**
     * This class represents the state in which the flower
     * is open and facing west. It transitions to Night
     * when it is no longer light for a time.
     */
    public static class Afternoon extends State {
	private int count; // How many turns has it been dark (for timeout)
        public Afternoon() {
            super();
        }
        
        public void enter() {
            System.out.println("The flower is now facing west.");
        }
        public void exit() {            
            System.out.println("The flower is closing...");
        }
        public int updateAndCheckTransitions() {             
            int light, raining;
            System.out.println("The flower is facing west.");
            
            // If it is raining, increment the static water level (using the
            // static water variable). If the flower was wilting before, it no
            // longer is.
            System.out.print("Is it raining? (1 or 0):  ");
            raining = input.nextInt();
            if (raining == 1) {
                if (water == 0) System.out.println("The flower is no longer wilting.");
                water+=5;
            }
            // Otherwise, decrement the water level. If it is 0, the flower
            // will wilt.
            else {
                if (water > 0) water--;
                if (water <= 0) System.out.println("The flower is wilting!");
            }
            
            System.out.print("Is it still light out? (1 or 0): ");
            light = input.nextInt();
            if (light == 1) count = 0; // Reset count if light

            // To keep the flower from closing if a cloud passes, don't
            // close until it has been dark at least 3 turns in a row.
            if (light == 0) count++;
            if (count == 3) return 0;
            else return 2;
        }
    }

    /**
     * Main driver for FSM
     */
    public static void main(String[] args) {
        /* IMPORTANT! Change the 3 to the number of states you have ***/
        int numberOfStates = 3;
        State[] states = new State[numberOfStates];

        /* IMPORTANT! Modify this code to create each of your states ***/
        states[0] = new Night();
        states[1] = new Morning();
        states[2] = new Afternoon();

        /**** End of code to modify ****/

        int currentState = 0;
        int nextState;  

        states[0].enter();
        while(true) {
            nextState = states[currentState].updateAndCheckTransitions();
            if (nextState != currentState) {
                states[currentState].exit();
                currentState = nextState;
                states[currentState].enter();
            }
        }
    }
}
