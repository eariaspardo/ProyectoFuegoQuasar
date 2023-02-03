package com.meli.fuego.quasar.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.meli.fuego.quasar.entity.Satellite;
import com.meli.fuego.quasar.entity.SatelliteSign;

public class LocationSpaceshipServiceImplTest {
	
	@InjectMocks
	private LocationSpaceshipServiceImpl locationSpaceshipServiceImpl;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testGetSpacheship() {
		assertNotNull(locationSpaceshipServiceImpl.getSpacheship(SatelliteSignObjeto()));
	}
	
	private SatelliteSign SatelliteSignObjeto() {
		SatelliteSign objeto = new SatelliteSign();
		objeto.setSatellites(Arrays.asList(SatelliteObjeto(), SatelliteTwoObjeto(), SatelliteThreeObjeto()));
		return objeto;
	}
	
	private Satellite SatelliteObjeto() {
		Satellite objeto = new Satellite();
		objeto.setDistance(95.5);
		objeto.setName("SATELLITEONE");
		objeto.setMessage(Arrays.asList("try", "", "this", "message", ""));
		return objeto;
	}
	
	private Satellite SatelliteTwoObjeto() {
		Satellite objeto = new Satellite();
		objeto.setDistance(95.5);
		objeto.setName("SATELLITETWO");
		objeto.setMessage(Arrays.asList("", "a", "this", "message", "again"));
		return objeto;
	}
	
	private Satellite SatelliteThreeObjeto() {
		Satellite objeto = new Satellite();
		objeto.setDistance(95.5);
		objeto.setName("SATELLITETHREE");
		objeto.setMessage(Arrays.asList("try", "", "a", "", ""));
		return objeto;
	}
	
}
