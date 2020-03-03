/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innerloops;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sean-
 */
public class Innerloops {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(CardValue.ACE.getValue() > CardValue.FIVE.getValue()) {
            System.out.println("A > 5");
        }
        else
            System.out.println("A < 5");
        
        //List<Card> list = new ArrayList<>();
        /*
        for (int i = 0; i < 13; i++) {
        Card card = new Card(i + 1, CardSuit.DIAMOND);
        System.out.println(card.toString());
        }
        for (int i = 0; i < 13; i++) {
        Card card = new Card(i + 1, CardSuit.HEART);
        //list.add(card);
        
        System.out.println(card.toString());
        }
        for (int i = 0; i < 13; i++) {
        Card card = new Card(i + 1, CardSuit.CLUB);
        //list.add(card);
        System.out.println(card.toString());
        }
        for (int i = 0; i < 13; i++) {
        Card card = new Card(i + 1, CardSuit.SPADE);
        //list.add(card);
        System.out.println(card.toString());
        }
        */
        /* SWITCHES
        int number = 2;
        switch(number) {
        case 0:
        System.out.println("Zero");
        break;
        case 5:
        System.out.println("Hi");
        break;
        default:
        System.out.println("Not 0 or 5 you bum");
        break;
        }
         */ /* INNER LOOPSS
        for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 5; j++) {
        System.out.println("i = " + i + " j = " + j);
        }
        } */
    }
    
}
