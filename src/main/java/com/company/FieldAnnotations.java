package com.company;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class FieldAnnotations {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface HackerAttack {}

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Container { }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface FilePath {
        String in() default "";
        String out() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Transformation {}
}
