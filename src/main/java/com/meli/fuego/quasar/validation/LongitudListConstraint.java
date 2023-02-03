package com.meli.fuego.quasar.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.meli.fuego.quasar.entity.Satellite;

public class LongitudListConstraint implements ConstraintValidator<LongitudListValidation, List<Satellite>>{

	@Override
	public boolean isValid(List<Satellite> value, ConstraintValidatorContext context) {
		return value.size() > 2;
	}
	
}
