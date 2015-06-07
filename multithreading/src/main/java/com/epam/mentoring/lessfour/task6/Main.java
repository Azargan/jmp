package com.epam.mentoring.lessfour.task6;

import com.epam.mentoring.lessfour.task6.Controller.SimpleController;
import com.epam.mentoring.lessfour.task6.consumer.SimpleConsumer;
import com.epam.mentoring.lessfour.task6.producer.SimpleProducer;
import static com.epam.mentoring.lessfour.task6.util.Sleeper.sleep;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
@Slf4j
public class Main {
    
    private static final String FIRST_TOPIC = "First topic";
    private static final String SECOND_TOPIC = "Second topic";
    
    public static void main(String[] args) throws Exception {
        final SimpleController controller = new SimpleController(10);
        final List<Thread> threads = Arrays.asList(
                new Thread(new SimpleProducer(controller, FIRST_TOPIC)),
                new Thread(new SimpleProducer(controller, FIRST_TOPIC)),
                new Thread(new SimpleProducer(controller, SECOND_TOPIC)),
                new Thread(new SimpleProducer(controller, SECOND_TOPIC)),
                new Thread(new SimpleConsumer(controller, FIRST_TOPIC)),
                new Thread(new SimpleConsumer(controller, SECOND_TOPIC)));
        
        threads.forEach(t -> t.start());
        sleep(10000L);
        System.exit(0);
    }
}
