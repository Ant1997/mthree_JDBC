package academy.learnprogramming;

// No annotation so that it's not coupled with Spring dependence
public interface NumberGenerator {
    int next();

    int getMaxNumber();

    int getMinNumber();
}
