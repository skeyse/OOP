/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean-
 */


import java.awt.event.KeyEvent;

public enum Directions {
    RIGHT(KeyEvent.VK_RIGHT), LEFT(KeyEvent.VK_LEFT), UP(KeyEvent.VK_UP), DOWN(KeyEvent.VK_DOWN);
    
    private int key;
    
    private Directions(int key) {
        this.key = key;
    }
    
    public int getDirection() {
        return key;
    }
    
}
