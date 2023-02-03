package com.meli.fuego.quasar.entity;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionSpaceShipSplit {
	
	@DecimalMin(value = "0.1", inclusive = false)
	private double distance;
	
	@NotNull
	@NotEmpty(message = "Los mensajes no pueden estar vacios")
	private List<String> message;

}
