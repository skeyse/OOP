package threadqueueexample;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class PersonProducer implements Runnable {

    private BlockingQueue<Person> queue;
    private List<Person> people;
    
    public PersonProducer(BlockingQueue<Person> queue, List<Person> people) {
        this.queue = queue;
        this.people = people;
    }
    
    @Override
    public void run() {
        System.out.println("Producer Running");
        
        /*try {
        File file = new File("C:/Users/Sean-/Documents/people.json");
        ObjectMapper objectMapper = new ObjectMapper();

            List<Person> people = 
                    objectMapper.readValue(file, 
                            objectMapper.getTypeFactory()
                                    .constructCollectionType(List.class, Person.class));
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(PersonProducer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        for(Person person: people) {
                queue.add(person);
                System.out.println(toString());
            }
    }
    
}