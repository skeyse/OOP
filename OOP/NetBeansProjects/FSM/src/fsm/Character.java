/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsm;

/**
 *
 * @author Sean-
 */

public class Character {
    public int food = 6;
    public int water = 6;
    public int energy = 3;
    
    public int choice;
    public int counter;
    
    public int prevState = -1;
    public int currentState = 0;
    public int nextState;  // maybe
    
    public int fightChance = 0;
}
