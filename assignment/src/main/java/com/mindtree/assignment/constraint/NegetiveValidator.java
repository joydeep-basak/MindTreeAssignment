package com.mindtree.assignment.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NegetiveValidator implements ConstraintValidator<Negetive, Integer> {
  @Override
  public boolean isValid(final Integer quantity, final ConstraintValidatorContext context) {
	  return (quantity >= 0);
  }
}