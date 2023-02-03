package com.meli.fuego.quasar.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.meli.fuego.quasar.entity.PositionSpaceShipSplit;
import com.meli.fuego.quasar.entity.Satellite;

public class PositionSpaceshipSplitServiceImplTest {
	
	@InjectMocks
	private PositionSpaceshipSplitServiceImpl positionSpaceshipSplitServiceImpl;
	
	public static List<Satellite> LISTA_SATELITES = new ArrayList<>();
	public static final String NOFINDSTELLITE = "No se encontro el Satelite";
	public static final String NAMESATELLITE = "El nombre del satelite es requerido";
	
	@Mock
	private RestTemplate restTemplate = new RestTemplate();
	
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		//LISTA_SATELITES.add(new Satellite(200.4, "kenobi", Arrays.asList("this", "", "", "message", "")));
		//LISTA_SATELITES.add(new Satellite(150.5, "skywalker", Arrays.asList("", "is", "", "", "secret")));
		//LISTA_SATELITES.add(new Satellite(182.8, "sato", Arrays.asList("this", "", "a", "", "")));
		
	}

	@AfterEach
	void tearDown() {
		LISTA_SATELITES = new ArrayList<>();
	}

	@Test
	void testGetSpacheshipSplit() {
		LISTA_SATELITES = new ArrayList<>();
		LISTA_SATELITES.add(new Satellite(95.5, "SATELLITETRY", Arrays.asList("try", "", "this", "message", "")));
		assertNotNull(positionSpaceshipSplitServiceImpl.getSpacheshipSplit(positionSpaceShipSplitObjeto(), "SATELLITETRYTWO"));
	}
	
	@Test
	void testGetSpacheshipSplitThrow() {
		Exception myException2 = null;
		try {
			positionSpaceshipSplitServiceImpl.getSpacheshipSplit(positionSpaceShipSplitObjeto(), "");
	    } catch (Exception e) {
	    	myException2 = e;
	    }
		assertEquals(myException2.getMessage(), NAMESATELLITE);
	}
	
	@Test
	void testGetSatelliteThrow() {
		Exception myException = null;
		try {
			LISTA_SATELITES = new ArrayList<>();
			LISTA_SATELITES.add(new Satellite(95.5, "SATELLITETRY", Arrays.asList("try", "", "this", "message", "")));
			positionSpaceshipSplitServiceImpl.getSatellite("SATELLITETRY");
	    } catch (Exception e) {
	        myException = e;
	    }
		assertEquals(myException.getMessage(), NOFINDSTELLITE);
	}
	
	private PositionSpaceShipSplit positionSpaceShipSplitObjeto() {
		PositionSpaceShipSplit objeto = new PositionSpaceShipSplit();
		objeto.setDistance(95.5);
		objeto.setMessage(Arrays.asList("try", "", "this", "message", ""));
		return objeto;
	}
	
}
