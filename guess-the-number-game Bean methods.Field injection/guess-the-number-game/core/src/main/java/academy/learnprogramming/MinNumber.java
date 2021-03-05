package academy.learnprogramming;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// where it's applicable
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
// Indicates how long the annotations are to be retained @runtime
@Retention(RetentionPolicy.RUNTIME)
// Qualifier annotation to annotate custom annotations
@Qualifier
public @interface MinNumber {

}
