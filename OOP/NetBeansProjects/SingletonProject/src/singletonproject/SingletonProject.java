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
public class SingletonProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PersonFactory personFactory = PersonFactory.getInstance();
        
        Person person = personFactory.getPerson(true);
        System.out.println(person.getFirstName() + " " + person.getLastName());
        
        Person person2 = personFactory.getPerson(false);
        System.out.println(person2.getFirstName() + " " + person2.getLastName());

        
        /*StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello").append(" ").append("World");
        
        SettingsBuilder settings = new SettingsBuilder();
        
        settings.increaseVolume(5).increaseVolume(10);
        
        System.out.println(settings.getVolume());
        */
        // TODO code application logic here
        /*Settings settings = Settings.getInstance();
        
        settings.setVolume(100);
        
        System.out.println("The Volume Is: " + settings.getVolume());
        
        Settings.destroy();
        settings = settings.getInstance();
        
        System.out.println("The Volume Is: " + settings.getVolume());

     */   
    }
    
}
