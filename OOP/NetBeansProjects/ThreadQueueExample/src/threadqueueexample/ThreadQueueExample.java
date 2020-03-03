package threadqueueexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class ThreadQueueExample {
    
    
    private final static int THREAD_COUNT = 2;

    public static void main(String[] args) throws IOException, InterruptedException {
        
        new ThreadQueueExample().start();
        
    }
    
    public void start() throws IOException, InterruptedException {
        
        File file = new File("C:/Users/Sean-/Documents/people.json");
        ObjectMapper objectMapper = new ObjectMapper();

            List<Person> people = 
                    objectMapper.readValue(file, 
                            objectMapper.getTypeFactory()
                                    .constructCollectionType(List.class, Person.class));
        
        
        BlockingQueue<Person> queue = new LinkedBlockingQueue();
        
        people = people.stream()
                .filter(person -> person.getFirstName().toLowerCase().contains("ad"))
                .sorted(Person::getFirstName)
                .collect(Collectors.toList());
        
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        executor.execute(new PersonProducer(queue, people.subList(0, 49)));
        executor.execute(new PersonProducer(queue, people.subList(50, 99)));
        
        long time = System.currentTimeMillis();
        
        int size = people.size();
        
        
        /*int remainingSize = people.size();
        while(remainingSize > 0) {
            int temp = remainingSize;
            if(remainingSize > THREAD_COUNT) {
                temp /= THREAD_COUNT;
                
            }
            
            people.subList()
        }*/
        
        
        //Thread threadA = new Thread(new PersonProducer(queue, people));
        //threadA.start();
        
        Thread.sleep(500);
        
        Thread threadB = new Thread(new PersonConsumer(queue));
        threadB.start();
        
        Thread threadC = new Thread(
                ()->System.out.println("Hello World")
        );
        threadC.start();
    }
}
