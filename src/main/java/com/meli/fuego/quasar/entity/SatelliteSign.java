package com.meli.fuego.quasar.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.meli.fuego.quasar.validation.LongitudListValidation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteSign{

	@NotNull(message = "La informacion de la nave no puede estar vacia")
	@NotEmpty(message = "La informacion de la nave no puede estar vacia")
	@Valid
	@LongitudListValidation
	private List<Satellite> satellites;
	
}
