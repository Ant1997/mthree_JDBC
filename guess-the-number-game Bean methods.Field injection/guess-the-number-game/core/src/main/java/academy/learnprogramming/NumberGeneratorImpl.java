package academy.learnprogramming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

// when you want to name a bean, you put it in parentheses&use quotes
// Removing the annotation because they will be found automatically
// @Component("generator")
public class NumberGeneratorImpl implements NumberGenerator {
    // Fields
    private final Random random = new Random();

    @Autowired
    @MaxNumber
    private int maxNumber;

    @Autowired
    @MinNumber
    private int minNumber;

    // Public methods
    @Override
    public int next() {
        // Example min = 5 max = 20 max-min = 15 -> range 0-15 + min -> 5-20
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }
}
