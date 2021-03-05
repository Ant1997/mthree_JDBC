package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// Remove because it is found automatically
// @Component
public class GameImpl implements Game {
    // Constants
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // Create fields
    @Autowired
    private NumberGenerator numberGenerator;

    @Autowired
    @GuessCount
    private int guessCount;

    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // Init Auto-Called
    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("The number is {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game pre-destroy()");
    }

    // Constructor to use constructor bean dependency
    // FOR BEAN CONSTRUCTOR DEPENDENCY USE:
    // <bean id="game" class="academy.learnprogramming.GameImpl">
    //        <constructor-arg ref="numberGenerator"/>
    //    </bean>
    // Initializes numberGenerator
    // public GameImpl(NumberGenerator numberGenerator) {
    //    this.numberGenerator = numberGenerator;
    // }

    // Public methods
    // Setter-based injection dependency
    // ADD THIS TAG FOR SETTER INJECTION DEPENDENCY
    // name = represents property/field of class, ref = reference to other bean
    // <property name="numberGenerator" ref="numberGenerator"/>
    // public void setNumberGenerator(NumberGenerator numberGenerator) {
    //    this.numberGenerator = numberGenerator;
    // }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    @Override
    public void check() {
        // Check range
        checkValidNumberRange();
        // If it's valid, check the guesses
        if (validNumberRange) {
            if (guess > number) {
                biggest = guess - 1;
            }
            if (guess < number) {
                smallest = guess + 1;
            }
        }
        // Deaccumulate guess amount
        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // Private methods
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
