/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singletonproject;

/**
 *
 * @author Sean-
 */
public class Settings {
    private static Settings instance;
    
    private int volume;
    
    private Settings() {}
    
    public static Settings getInstance() {
        
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }
    
    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    
}
