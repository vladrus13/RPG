package ru.vladrus13.rpg.saves;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SaveConstante {
    String name();

    int constructor() default -1;

    String setNameFunction() default "";
}
