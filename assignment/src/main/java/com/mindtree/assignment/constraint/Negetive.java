package com.mindtree.assignment.constraint;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NegetiveValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RUNTIME)
@Documented
public @interface Negetive {
  String message() default "Negetive value not allowed";
  Class <?> [] groups() default {};
  Class <? extends Payload> [] payload() default {};
 }