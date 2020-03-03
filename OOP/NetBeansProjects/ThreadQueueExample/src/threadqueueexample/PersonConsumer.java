package threadqueueexample;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonConsumer implements Runnable {

    private BlockingQueue<Person> queue;
    private boolean run = true;

    
    public PersonConsumer(BlockingQueue<Person> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        System.out.println("Consumer Running");
        PeopleAnalytics peopleAnalytics = PeopleAnalytics.getInstance();
        while(run) {
            try {
                if (queue.isEmpty())
                    run = false;
                Person person = queue.take();
                
                peopleAnalytics.addToFirstNameCount(person.getFirstName().length());
                peopleAnalytics.addToLastNameCount(person.getLastName().length());
                
                System.out.println(peopleAnalytics.toString());
                
                //if (queue.isEmpty()) {
                //    run = false;
                //}
                
            } catch (InterruptedException ex) {
                Logger.getLogger(PersonConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
}
