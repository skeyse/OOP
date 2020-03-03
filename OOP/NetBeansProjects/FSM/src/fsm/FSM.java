package fsm;

import java.util.*;

/**
 * Author: Sean Keyse
 * 
 * This is my cat game :)
 * 
 * The game takes you on an adventure through the streets as a cat!
 * You have to balance your three attributes as you search the streets looking for food and water.
 * Your attributes are Energy, Food and Water.
 * The max of each attribute is 
 */


public class FSM {

    public static final int NUM_PLAYERS = 1;
    public static final int NUM_NPCS = 1;
    public static final int MAX_STAT = 5;
    public static final double FIGHT_CHANCE = 0.05;
    
    static Computer[] characters = new Computer[NUM_PLAYERS+NUM_NPCS];
    
    private static void fight(int p, int opp) {
        int choice;
        boolean fighting = true;
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Player " + (p+1) + " attacks player " + (opp+1) + "!\n");
        System.out.print("Player " + (opp+1) + " you have " + characters[opp].energy + " Energy, would you like to (0)Fight or (1)Walk Away?: ");
        // Opponent chooses to fightf
        if(opp>=NUM_PLAYERS) {
            choice = characters[opp].updateOppFighting(1, 1,true);
            System.out.println("\nPlayer " + (opp+1) + " chose: " + choice);
            }
        else
            choice = input.nextInt();
        if(choice == 1) {
            System.out.println("Player " + (opp+1) + " decides to walk away.");
            return;
        }
        
        int pHp = 10;
        int oppHp = 10;
        int attack;
        int pHit = random.nextInt(5);
        int oppHit = random.nextInt(5);
        oppHp -= pHit;
        pHp -= oppHit;
        boolean start = true;
        System.out.println("Player " + (p+1) + " hit: " + pHit + ".\nPlayer " + (opp+1) + " hit: " + oppHit + ". \n");
        while(fighting) {
            //Player chooses to hit or run
            System.out.println("Player " + (p+1) + " HP: " + pHp + ".  (0)Attack or (1)Run Away?: ");
            if(p >= NUM_PLAYERS) {
                choice = characters[p].updateFighting(pHp, oppHp, start, false, false);
                start = false;
                System.out.println("\nPlayer " + (p+1) + " chose: " + choice);
            }
            else
                choice = input.nextInt();
            if(choice == 0) {
                attack = random.nextInt(5);
                System.out.println("Player " + (p+1) + " hit a " + attack); 
                oppHp -= attack;
            }
            else {
                System.out.println("Player " + (p+1) + " runs away from the fight! (Energy - 1");
                characters[p].energy--;
                fighting=false;
            }
            
            if(oppHp <= 0) {
                System.out.println("Player " + (p+1) + " wins the fight!");
                System.out.println("Player " + (p+1) + " energy -1, Player " + (opp+2) + " energy -2.");
                characters[p].energy--;
                characters[opp].energy -= 2;
                fighting = false;
                continue;
            }
            
            System.out.println("Player " + (opp+1) + " HP: " + oppHp + ".  (0)Attack or (1)Run Away?: ");
            if(opp>=NUM_PLAYERS) {
                    choice =characters[opp].updateOppFighting(pHp, oppHp, false);
                    System.out.println("\nPlayer " + (opp+1) + " chose: " + choice);
            }
            else
                choice = input.nextInt();
            if(choice == 0) {
                attack = random.nextInt(5);
                System.out.println("Player " + (opp+1) + " hit a " + attack); 
                pHp -= attack;
            }
            else {
                System.out.println("Player " + (opp+1) + " runs away from the fight!");
                fighting=false;
            }
            
            if(pHp <= 0) {
                System.out.println("Player " + (opp+1) + " wins the fight!");
                System.out.println("Player " + (p+1) + " energy -2.");
                characters[p].energy -= 2;
                fighting = false;
            }
        }        
    }
    
    public static abstract class State {
        
        protected Scanner input = new Scanner(System.in);
        protected Random random = new Random();
        
        public State() {}
        
        public abstract void enter(int player);
        public abstract void exit(int player);
        public abstract int updateAndCheckTransitions(int player);
        
    }
    
    /** Resting State:
     *  Each Update:
     *   Energy + 2, Food & Drink - 1
     *  Choices:
     *   0 : Continue Resting
     *   1 : Wake Up
     *  Notes:
     *   Can only go to walking from resting.
     *   Every other state CAN go back to this
     *
     **/
    public static class Resting extends State {
        //int counter;
        @Override
        public void enter(int player) {
            characters[player].counter = 0;
            System.out.println("You fall asleep.");
        }
        @Override
        public void exit(int player) {
            System.out.println("You awake from your rest...");
        }
        @Override
        public int updateAndCheckTransitions(int player) {          
            int choice;
            System.out.println("You're fast asleep.  (Energy +1)");
            
            // gain 2 energy each time you are resting
            if(characters[player].energy < MAX_STAT)
                characters[player].energy+=1;
            else
                characters[player].energy = MAX_STAT;
            characters[player].counter++;
            
            // don't allow a player to rest more than 3 times
            if(characters[player].counter == 3) {
                System.out.println("Player " + (player+1) + " can't possibly sleep anymore");
                characters[player].food --;
                characters[player].water --;
                
                return 1;
            }
            
            System.out.print("Would you like to (0)Continue Resting or (1) Wake Up?: ");
            if(player>=NUM_PLAYERS) {
                choice = characters[player].updateSleeping();
                System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
            }
            else
                choice = input.nextInt();

            if(choice == 1) {
                // Wake up, food and drink deducted one to account for time spent resting
                System.out.println("You wake up.");
                
                characters[player].food--;
                characters[player].water--;
                
                return 1;
            }
            
            else {
                System.out.println("You continue to rest");
                
                return 0;
            }
        }
    }
        
    /** Walking State:
     *  Each Update:
     *   Energy, Hygiene, Food & Drink - 1 
     *   Chance to find Food OR Drink (Currently 50/50(Change depending on time & color)) --- Add chance for nothing and/or another cat??
     *  Action Choices:
     *   0: Continue Walking
     *   1: Rest
     *   2: Eat/Drink -- Depends on what was found
     *
     *  Notes:
     *   Current: 45%/45% for food & drink
     *              10% for finding nothing
     *   Transitions to both consumption states as well as resting
     */
    public static class Walking extends State {

        @Override
        public void enter(int player) {  System.out.println("You are walking.");  }

        @Override
        public void exit(int player) {  System.out.println("You stop walking.");  }

        @Override
        public int updateAndCheckTransitions(int player) {
            System.out.println("You're walking down the street...");
            double randomEvent = random.nextDouble();
            int choice;
            
            characters[player].energy--;
            characters[player].food--;
            characters[player].water--;
            
            if (randomEvent <= 0.45) {
                System.out.println("You find some food!");
                System.out.print("Would you like to (0)Continue Walking, (1)Rest, or (2)Eat?: ");
                if(player>=NUM_PLAYERS) {
                    choice=characters[player].updateWalking(true, false);
                    System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                }
                else
                    choice = input.nextInt();
                
                if (choice == 2) {
                    characters[player].food++;
                    return 2;
                }
            }
            
            else if(randomEvent >= 0.55) {
                System.out.println("You find some water!");
                System.out.print("Would you like to (0)Continue Walking, (1)Rest, or (2)Drink?: ");
                if(player>=NUM_PLAYERS) {
                    choice=characters[player].updateWalking(false, true);
                    System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                }
                else
                    choice = input.nextInt();
                if (choice == 2){
                    characters[player].water++;
                    return 3;
                }
            }
            
            else {
                System.out.println("You don't find anything!");
                System.out.print("Would you like to (0)Continue Walking or (1)Rest?: ");
                if(player>=NUM_PLAYERS) {
                    choice=characters[player].updateWalking(false, false);
                    System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                }
                else 
                    choice = input.nextInt();
            }
            
            if (choice == 0) {
                return 1;
            }
            
            else {
                return 0;
            }
        }
    }
    
   /**
     * Eating State:
     *  Each Update:
     *   Food  +1
     *   Energy & Drink  -1
     *   5% Chance of being attacked by another cat.
     *  Action Choices:
     *   (Fighting):
     *    0: Fight
     *    1: Walk Away
     *   (Food Left):
     *    0: Continue Eating
     *    1: Rest
     *    2: Walk
     *   (No Food Left):
     *    0: Rest
     *    1: Walk
     *  Notes:
     *   Goes to resting, walking, or fighting.  Comes back from fighting with same amount of food left.
    */
    public static class Eating extends State {
        int choice;
        int foodAmt;
        
        @Override
        public void enter(int player) {
            // Random int between 0 and 4
            // 1 is added later to make the actual range 1-5
            foodAmt = (random.nextInt(4));
            
            if (foodAmt < 0)
                foodAmt *= -1;
            
            foodAmt++;
            
            System.out.println("You start eating your catch.");
        }

        @Override
        public void exit(int player) {  System.out.println("You finish eating...");  }

        @Override
        public int updateAndCheckTransitions(int player) {
            double randomNumber = random.nextDouble();
            if(characters[player].food<MAX_STAT)
                characters[player].food++;
            foodAmt--;
            
            if(foodAmt <= 0) {
                System.out.println("You finish off your meal.  (Food +1)");
            }
            
            else {
                System.out.println("You're eating your catch...  (Food +1)");
                System.out.println("You have " + foodAmt + " portions left.");
            }

            
            //Possible (10%) chance of another cat attacking
            if(randomNumber <= FIGHT_CHANCE) {  // <= 0.05
                System.out.println("Another cat comes out of nowhere!");
                System.out.print("Would you like to (0)Fight or (1)Walk Away?: ");
                if(player>=NUM_PLAYERS) {
                    characters[player].updateEating(foodAmt, true);
                    System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                }
                else
                    choice = input.nextInt();
                
                if (choice == 0) {
                    System.out.println("Let's get ready to rumble!");
                    Fighting.eating = true;
                    return 4;
                }
                
                else {
                    System.out.println("You give up the rest of your food and walk away.");
                    
                    return 0;
                }
            }
            
            else {
                // Handles when there's still food left to eat
                if (foodAmt > 0) {
                    System.out.print("Would you like to (0)Keep Eating, (1)Rest, or (2)Walk Around?: ");
                    if(player >= NUM_PLAYERS) {
                        choice = characters[player].updateEating(foodAmt, false);
                        System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                    }
                    else
                        choice = input.nextInt();

                    switch(choice) {
                        case 0:
                            System.out.println("You continue eating your food.");
                            return 2;
                        case 1:
                            System.out.println("You decide to take a cat nap.");
                            return 0;
                        case 2: 
                            System.out.println("You continue on your adventure.");
                            return 1;
                        default:
                            System.out.println("Not an option, but you continue eating your food.");
                            return 2;
                    }
                }
                // When you finish off the meal
                else {
                    System.out.print("Would you like to (0)Rest or (1)Walk Around?: ");
                    if(player >= NUM_PLAYERS) {
                        choice = characters[player].updateEating(0, false);
                        System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                    }
                    else
                        choice = input.nextInt();

                    if(choice == 0) {
                        System.out.println("You decide to take a cat nap.");
                        return 0;
                    }
                    else {
                        System.out.println("You continue on your adventure.");
                        return 1;
                    }
                }
            }
        }
    }
    
   /**
     * Drinking State:
     *  Each Update:
     *   Water  +1
     *   Energy & Food  -1
     *   5% Chance of being attacked by another cat.
     *  Action Choices:
     *   (Fighting):
     *    0: Fight
     *    1: Walk Away
     *   (Water Left):
     *    0: Continue Drinking
     *    1: Rest
     *    2: Walk
     *   (No Water Left):
     *    0: Rest
     *    1: Walk
     *  Notes:
     *   Goes to resting, walking, or fighting.  Comes back from fighting with same amount of water left.
    */
    
    public static class Drinking extends State {
        int choice;
        int waterAmt;

        @Override
        public void enter(int player) {
            // Random int between 0 and 4
            // 1 is added later to make the actual range 1-5
            waterAmt = (random.nextInt(4));
            if (waterAmt < 0)
                waterAmt *= -1;
            // Add to get 1-5
            waterAmt++;
            
            System.out.println("You start drinking some water.");
        }

        @Override
        public void exit(int player) {  System.out.println("You finish up drinking.");  }

        @Override
        public int updateAndCheckTransitions(int player) {
            int opponent = player;
            while(opponent == player ) {
                opponent = random.nextInt(NUM_PLAYERS+NUM_NPCS);
                if(opponent < 0)
                    opponent *= -1;
            }
            double randomnumber = random.nextDouble();
            if(characters[player].water<MAX_STAT)
                characters[player].water++;
            waterAmt--;
            
            if (waterAmt == 0) {
                System.out.println("You finish the rest of the water.  (Water +1)");
            }
            else {
                System.out.println("You're drinking some water...  (Water +1)");
                System.out.println("You have " + waterAmt + " sips left.");
            }
            
            if(randomnumber <= FIGHT_CHANCE) {
                
                System.out.println("Another cat comes out of nowhere!");
                System.out.print("Would you like to (0)Fight or (1)Walk Away?: ");
                if(player>=NUM_PLAYERS) {
                    characters[player].updateDrinking(waterAmt, true);
                    System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                }
                else
                    choice = input.nextInt();
                
                if (choice == 0) {
                    Fighting.drinking = true;
                    System.out.println("Let's get ready to rumble!");
                    return 4;
                }
                else {
                    System.out.println("You give up the rest of your water and walk away.");
                    return 1;
                }
            }
             
            else {
            
                if (waterAmt > 0) {
                    System.out.print("Would you like to (0)Keep Drinking, (1)Rest, or (2)Walk Around?: ");
                    if(player>=NUM_PLAYERS) {
                        choice = characters[player].updateDrinking(waterAmt, false);
                        System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                    }
                    else
                        choice = input.nextInt();

                    switch(choice) {
                        case 0:
                            System.out.println("You continue drinking the water.");
                            return 3;
                        case 1:
                            System.out.println("You decide to take a cat nap.");
                            return 0;
                        case 2: 
                            System.out.println("You continue on your adventure.");
                            return 1;
                        default:
                            System.out.println("Not an option, but you continue drinking the water.");
                            return 3;
                    }
                }

                else {
                    System.out.print("Would you like to (0)Rest or (1)Walk Around?: ");
                    
                    if(player>=NUM_PLAYERS) {
                        choice = characters[player].updateDrinking(0, false);
                        System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                    }
                    else
                        choice = input.nextInt();

                    if(choice == 0) {
                        System.out.println("You decide to take a cat nap.");
                        return 0;
                    }
                    else {
                        System.out.println("You continue on your adventure.");
                        return 1;
                    }
                }
            }
        }
    }
    
   /**
     * Fighting State:
     *  Only goes through one update to account for the fight.
     *  Subtracts 2 energy.
     * Actions Out:
     *  0: Continue Eating/Drinking -- Depends on state you were in when attacked.
     *  1: Rest
     *  2: Walk Around
     * Notes:
     *  Not a necessary state, but provides random events to make it a little more interesting.
    */
    public static class Fighting extends State {

        public static boolean eating, drinking;
        
        @Override
        public void enter(int player) {
            System.out.println("The fight begins!");
        }

        @Override
        public void exit(int player) {
            System.out.println("The fight is finally over.");
            eating = false;
            drinking = false;
        }

        @Override
        public int updateAndCheckTransitions(int player) {
            int choice;
            
            int opponent;
            do {
                opponent = random.nextInt(NUM_PLAYERS+NUM_NPCS);
            }while(opponent==player);
            
            fight(player, opponent);
            
            
            
            if(eating) {
                System.out.print("Would you like to (0)Continue Eating, (1)Rest, or (2)Walk Around?: ");
                if(player >= NUM_PLAYERS) {
                    choice = characters[player].updateEating(1, false);
                    System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                }
                else
                    choice = input.nextInt();
                switch(choice) {
                    case 0:
                        System.out.println("You go back to eating your meal.");
                        return 2;
                    case 1:
                        System.out.println("You need a nap after the fight.");
                        return 0;
                    case 2:
                        System.out.println("You get back to your adventure.");
                        return 1;
                    default:
                        return 2;
                }
            }
            else if(drinking) {
                System.out.print("Would you like to (0)Continue Drinking, (1)Rest, or (2)Walk Around?: ");
                if(player >= NUM_PLAYERS) {
                    choice = characters[player].updateDrinking(1, false);
                    System.out.println("\nPlayer " + (player+1) + " chose: " + choice);
                }
                else
                    choice = input.nextInt();
                switch(choice) {
                    case 0:
                        System.out.println("You go back to drinking your water.");
                        return 3;
                    case 1:
                        System.out.println("You need a nap after the fight.");
                        return 0;
                    case 2:
                        System.out.println("You get back to your adventure.");
                        return 1;
                    default:
                        return 3;
                }
            }
            
            else {
                return 1;
            }
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int numberOfStates = 5;
        State[] states = new State[numberOfStates];
            
        for(int i=0; i<NUM_PLAYERS; i++) {
            characters[i] = new Computer();
        }
        for(int i=NUM_PLAYERS; i<NUM_PLAYERS+NUM_NPCS; i++) {
            characters[i] = new Computer();
        }
        
        System.out.println("Characters Created.");
        

        /* IMPORTANT! Modify this code to create each of your states ***/
        states[0] = new Resting();
        states[1] = new Walking();
        states[2] = new Eating();
        states[3] = new Drinking();
        states[4] = new Fighting();
 
        /**** End of code to modify ****/

        int currentState = 0;
        int nextState;  

        System.out.println("Welcome to your own cat adventure!");
        System.out.println("Make your ways to the street as you balance out your energy, food and water levels.");
        System.out.println("Don't let them get to 0 and watch out for other wild cats!");
        
        //Game start
        states[0].enter(0);
        while(true) {
            for(int i=0; i<(NUM_PLAYERS+NUM_NPCS); i++) {
            // Display stats before each turn.
            
                if(characters[i].energy < 0) {
                    System.out.println("Player " + (i+1) + " died of exhaustion! Game Over!");
                    return;
                    
                }
                if(characters[i].food < 0) {
                    System.out.println("Player " + (i+1) + " died of hunger! Game Over!");
                    return;
                }
                if(characters[i].water < 0) {
                    System.out.println("Player " + (i+1) + " died of thirst! Game Over!");
                   return;
                }
                System.out.println("Player " + (i+1) + ". Energy: " + characters[i].energy + ";  Food: " + characters[i].food + ";  Water: " + characters[i].water);            
            

            
            //Death Checks

                
                characters[i].nextState = states[characters[i].currentState].updateAndCheckTransitions(i);
            
                if (characters[i].nextState != characters[i].currentState) {
                    //Handles the fighting state
                    if((characters[i].nextState == 2 && characters[i].currentState == 4) ||  (characters[i].nextState == 3 && characters[i].currentState == 4)) {
                        states[currentState].exit(i);
                        characters[i].currentState = characters[i].nextState;
                    }
                    else{
                        states[characters[i].currentState].exit(i);
                        characters[i].currentState = characters[i].nextState;
                        states[characters[i].currentState].enter(i);
                    }
                }
            
                
            
            //Warnings before dying
                    if(characters[i].energy < 3)
                        System.out.println("Player " + (i+1) + " is dying of exhaustion! Get some rest!");
                    if(characters[i].food < 3)
                        System.out.println("Player " + (i+1) + " is dying of hunger! Find some food!");
                    if(characters[i].water < 3)
                        System.out.println("Player " + (i+1) + " is dying of thirst! Find some water!");
                    System.out.println("--------------------");
            }
        }   
    }
}

