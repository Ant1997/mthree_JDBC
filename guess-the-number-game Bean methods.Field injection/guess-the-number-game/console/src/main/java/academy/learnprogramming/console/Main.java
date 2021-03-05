package academy.learnprogramming.console;

import academy.learnprogramming.config.AppConfig;
import academy.learnprogramming.MessageGenerator;
import academy.learnprogramming.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    // Since we're not using beans.xml anymore
    // private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess the number game");

        // Create context first (container)
        // When using beans.xml, use this to configure the bean
        // ConfigurableApplicationContext context
        //        = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        // Create context (container) using annotations
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get numberGenerator bean from context (container)
        NumberGenerator numberGenerator
                = context.getBean(NumberGenerator.class);

        // Call method next() to get a random number
        int number = numberGenerator.next();

        // Log generated number
        log.info("number = {}", number);

        // Get the game bean from context (container)
        // Can retrieve bean in many ways, this one is overloaded
        // Game game = context.getBean(Game.class);

        // Get the game bean from context (container)
        // Get bean by type
        // Can retrieve bean in many ways, this one is overloaded
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        // Log some info
        log.info("getMainMessage = {}", messageGenerator.getMainMessage());
        log.info("getResultMessage = {}", messageGenerator.getResultMessage());

        // Call reset method
        // Initializes class itself, but we can configure it to call automatically (prone to errors)
        // add this to first bean area: init-method="reset"
        // game.reset();

        // Close context (container)
        // Prevent memory resource leaks
        // Singleton = single bean
        context.close();



    }
}
