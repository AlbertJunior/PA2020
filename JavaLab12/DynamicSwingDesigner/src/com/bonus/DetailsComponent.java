package com.bonus;

import java.lang.annotation.*;

/**
 * This annotation represents some basic details for a component
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.LOCAL_VARIABLE,
        ElementType.FIELD})
@Inherited
public @interface DetailsComponent {

    int Width() default 100;

    int Height() default 100;

    String Text() default "Hello World!";

    int FontSize() default 10;
}
