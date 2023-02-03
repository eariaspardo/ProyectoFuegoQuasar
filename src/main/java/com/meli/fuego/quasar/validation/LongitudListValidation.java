package com.meli.fuego.quasar.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
//@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { LongitudListConstraint.class })
public @interface LongitudListValidation {
	
	String message() default "La informacion de la nave debe provenir de 3 satelites";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
