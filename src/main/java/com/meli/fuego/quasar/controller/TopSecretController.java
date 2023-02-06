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

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "${api.path.topsecret}")
@CrossOrigin(origins = "*")
public class TopSecretController {
	
	@Autowired
	private LocationSpaceshipService locationSpaceshipService;

	@PostMapping("/")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal error server")
    })
    public ResponseEntity<PositionSpaceShip> locationSpaceship(@Valid @RequestBody SatelliteSign informationSpaceship){
        return new ResponseEntity<PositionSpaceShip>(locationSpaceshipService.getSpacheship(informationSpaceship), HttpStatus.OK);
    }
	
}
