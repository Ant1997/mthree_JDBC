package academy.learnprogramming.console;

import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

// Add an annotation
@Component
public class ConsoleNumberGuess {
    // Constants
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    // Fields
    @Autowired
    private Game game;

    @Autowired
    private MessageGenerator messageGenerator;


    // Events

    // Generate -> Implement methods
    // One way to listen to events
    // use this in class: implements ApplicationListener<ContextRefreshedEvent>
    // @Override
    // public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    //    log.info("Container ready for use");
    // }

    // Another way to add Event Listener
    @EventListener // Can pass class like this (ContextRefreshedEvent.class) next to the annotation
    // Like this ^^ we would delete this one vv
    public void start(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("start() -- > Container ready for use");

        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Endless loop
        while (true) {
            // Retrieve main message and print
            System.out.println(messageGenerator.getMainMessage());
            // Retrieve result message and print
            System.out.println(messageGenerator.getResultMessage());

            // Accept next guess
            int guess = scanner.nextInt();

            // Go to next line in scanner
            scanner.nextLine();

            // Set the guess
            game.setGuess(guess);

            // Check if the guess is correct
            game.check();

            // If it's won or lost after 10 guesses
            if (game.isGameWon() || game.isGameLost()) {
                // Print result message
                System.out.println(messageGenerator.getResultMessage());
                // Ask user if they want to play again
                System.out.println("Play again? y/n?");

                // Accept new user input
                String playAgainString = scanner.nextLine().trim();
                // If it is y, break out of the code block & reset game
                if (!playAgainString.equalsIgnoreCase("y")) {
                    break;
                }

                // Reset game
                game.reset();
            }
        }
    }
}
