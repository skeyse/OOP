/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;
import java.util.Random;
/**
 *
 * @author Sean-
 */


public class Computer {
    //on every update
    //weight change?: options[i]= 1 + skill/10
    //choosing to walk should be weighted on the current skill's value vs the other values
    //current skill should get a good bias however, so they can raise it more than just the lowest level
    public int food = 6;
    public int water = 6;
    public int energy = 4;
    
    public int choice;
    public int counter;
    
    public int prevState = -1;
    public int currentState = 0;
    public int nextState;  // maybe
    
    public int fightChance = 0;
    
    public int hit=1;
    
    //Sleep, Eat, Drink
    private double options[] = {1.0, 1.0, 1.0};
    
    private int getMax() {
        Random random = new Random();

        int max = 0;
        
        if(options[1] >= options[max])
            max = 1;
        if(options[2] >= options[max])
            max = 2;
        if(max != 2) {
            if(options[0] == options[1])
                max = random.nextInt(2);
        }
        else {
            if(options[2] == options[0]) {
                if(options[2] == options[1])
                    max = random.nextInt(3);
                else {
                    if(random.nextBoolean())
                        max = 2;
                    else max = 0;
                }
            }
            else if(options[2] == options[1]) {
                if(random.nextBoolean())
                    max = 2;
                else
                    max = 1;
            }
        }
        
        return max;
    }
        //input: 0 eat, 1 rest, 2 walk
        //if foodAmt=0: 0 rest, 1 walk
    public int updateEating(int foodAmt, boolean fight) {
        //eating = 1.4 + 7-food/10  -- Eat until foodAmt == 0 or 4 more than the lowest stat
        //walk away = 1.0 + 7-stat/10   -- Get the highest value to compare to eating value
        
        if(fight) {
            if (energy > food)
                return 0;
            else 
                return 1;
        }
        options[0] = (1.0 + ((7.0-(double)energy)/10.0)); // sleep
        options[1] = (1.5 + ((7.0-(double)food)/10.0)); // eat
        options[2] = (1.0 + ((7.0-(double)water)/10.0)); // drink
        
        int maxStat = getMax();
        
        if(foodAmt <= 0) {
            if(maxStat == 0)
                return 0;
            else
                return 1;
        }
        
        if(maxStat == 1) // eat more
            return 0;
        if(maxStat == 0) // take nap
            return 1;
        else {           // walk
            return 2;
        }
        
    }
        //input: 0 drink, 1 rest, 2 walk
        //if waterAmt=0: 0 rest, 1 walk
    public int updateDrinking(int waterAmt, boolean fight) {
        //drinking = 1.4 + 7-water/10  -- Drink until waterAmt == 0 or 4 more than the lowest stat
        //walk away = 1.0 + 7-stat/10   -- Get the highest value to compare to eating value
        if(fight) {
            if (options[0] > options[1])
                return 0;
            else 
                return 1;
        }
        options[0] = (1.0 + ((7.0-(double)energy)/10.0)); // sleep
        options[1] = (1.0 + ((7.0-(double)food)/10.0)); // eat
        options[2] = (1.3 + ((7.0-(double)water)/10.0)); // drink
        
        int maxStat = getMax();
        
        if(waterAmt <= 0) {
            if(maxStat == 0)
                return 0;
            else
                return 1;
        }
        
        if(maxStat == 2) // eat more
            return 0;
        if(maxStat == 0) // take nap
            return 1;
        else {           // walk
            return 2;
        }
    }
        //input: 0 rest, 1 walk
    public int updateSleeping() {
        //sleeping = 1.3 + 7-energy/10  -- Eat until foodAmt == 0 or 2 more than the lowest stat
        //wake up = 1.0 + 7-stat/10   -- Get the highest value to compare to eating value
        options[0] = (1.0 + ((5.0-(double)energy)/10.0)); // sleep
        options[1] = (1.0 + ((7.0-(double)food)/10.0)); // eat
        options[2] = (1.0 + ((7.0-(double)water)/10.0)); // drink
        
        int maxStat = getMax();
        
        if(maxStat == 0)
            return 0;
        else
            return 1;
    }
        //food found: 0 walk, 1 rest, 2 eat
        //water found: 0 walk, 1 rest, 2 drink
        //else: 0 walk, 1 rest
    public int updateWalking(boolean eat, boolean drink) {
        //eat = 1.2 + 7-food/10
        //drink = 1.2 + 7-water/10
        //sleep = 1.0 + 7-energy/10
        options[0] = (1.0 + ((7.0-(double)energy)/10.0)); // sleep
        options[1] = (1.1 + ((7.0-(double)food)/10.0)); // eat
        options[2] = (1.1 + ((7.0-(double)water)/10.0)); // drink
        if(eat) {
            if(options[0] > options[1])
                return 1;
            else if(options[2] > options[1]+0.2)
                return 0;
            else
                return 2;
        }
        
        else if(drink) {
            if(options[0] > options[2])
                return 1;
            if(options[1] > options[2]+0.2)
                return 0;
            else
                return 2;
        }
        
        else {
            if(options[0] > options[1] && options[0] > options[2])
                return 1;
            else
                return 0;
        }
    }
    
    public int updateFighting(int hp, int oppHp,boolean start, boolean eating, boolean finished) {
        if(start)
            hit = 1;
        if(finished) {
            
        }
        // Choose whether to hit
        hit*=(food+hp-oppHp)/10;
        if(hit >= 0)
            return 0;
        else
            return 1;
        
        
    }
    
    public int updateOppFighting(int hp, int oppHp, boolean start) {
        // First choose to fight
        if(start) {
            if(options[1] >= 2 && options[2] >= 2 && 
                    options[0] >= options[1] && options[0] >= options[2]) {
                return 0;
            }
            else
                return 1;
        }
        //Handle rest of fight sequence.
        else {
            if(oppHp+energy>hp)
                return 0;
            else
                return 1;
        }
    }
    
}
