package com.meli.fuego.quasar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.fuego.quasar.entity.PositionSpaceShip;
import com.meli.fuego.quasar.entity.SatelliteSign;
import com.meli.fuego.quasar.service.LocationSpaceshipService;

@RestController
@RequestMapping(path = "${api.path.topsecret}")
@CrossOrigin(origins = "*")
public class TopSecretController {
	
	@Autowired
	private LocationSpaceshipService locationSpaceshipService;

	@PostMapping("/")
    public ResponseEntity<PositionSpaceShip> locationSpaceship(@Valid @RequestBody SatelliteSign informationSpaceship){
        return new ResponseEntity<PositionSpaceShip>(locationSpaceshipService.getSpacheship(informationSpaceship), HttpStatus.OK);
    }
	
}
