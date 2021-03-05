package academy.learnprogramming.config;

import academy.learnprogramming.GuessCount;
import academy.learnprogramming.MaxNumber;
import academy.learnprogramming.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
public class GameConfig {
    // Fields
    // When not using game.properties file, this can be done
    // @Value("${game.maxNumber:20}")
    @Value("${game.maxNumber:20}")
    private int maxNumber;
    // @Value("${game.guessCount:5}")
    @Value("${game.guessCount:5}")
    private int guessCount;
    // @Value("${game.minNumber:5}")
    @Value("${game.minNumber:5}")
    private int minNumber;

    // Bean methods
    @Bean
    // With qualifier the method names can be changed and still work
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    // Create a special qualifier annotation (fine-tuning annotation based)
    @Bean
    // With qualifier the method names can be changed and still work
    @GuessCount
    public int guessCount() {
        return guessCount;
    }

    // Create a special qualifier annotation (fine-tuning annotation based)
    @Bean
    // With qualifier the method names can be changed and still work
    @MinNumber
    public int minNumber() {
        return minNumber;
    }
}
