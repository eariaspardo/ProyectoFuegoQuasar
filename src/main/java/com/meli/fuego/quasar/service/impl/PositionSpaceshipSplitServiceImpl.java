package com.meli.fuego.quasar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meli.fuego.quasar.entity.PositionSpaceShipSplit;
import com.meli.fuego.quasar.entity.Satellite;
import com.meli.fuego.quasar.exception.PositionNotFoundException;
import com.meli.fuego.quasar.service.PositionSpaceshipSplitService;


/**
 * Clase que responde a los ejercicios del Nivel 3, conta de los metodos de registro de un satelite y la busque de la informacion del salite
 * @author Edilson
 *
 */
@Service
public class PositionSpaceshipSplitServiceImpl implements PositionSpaceshipSplitService{

	public static List<Satellite> LISTA_SATELITES = new ArrayList<>();
	public static final String NOFINDSTELLITE = "No se encontro el Satelite";
	public static final String NAMESATELLITE = "El nombre del satelite es requerido";
	
	/**
	 * Almacenar la informacion del Satelite en memoria temporal
	 */
	public List<Satellite> getSpacheshipSplit(PositionSpaceShipSplit informationSatellite, String nameSatellite) {
		if (nameSatellite == null || nameSatellite.isBlank()) {
			throw new PositionNotFoundException(NAMESATELLITE);
		} else {
			LISTA_SATELITES.add(new Satellite(informationSatellite.getDistance(), nameSatellite.toUpperCase(), informationSatellite.getMessage()));
		}
		return LISTA_SATELITES;
	}

	/**
	 * Buscar el satelite en el listado
	 */
	public Satellite getSatellite(String satelliteName) {
		Satellite res = LISTA_SATELITES.stream().filter(name -> name.getName().equals(satelliteName.toUpperCase())).findFirst().orElse(null);
		if(res == null) {throw new PositionNotFoundException(NOFINDSTELLITE);}
		return res;
	}

}
