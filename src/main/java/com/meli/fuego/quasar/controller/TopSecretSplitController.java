package com.meli.fuego.quasar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.fuego.quasar.entity.PositionSpaceShipSplit;
import com.meli.fuego.quasar.entity.Satellite;
import com.meli.fuego.quasar.service.PositionSpaceshipSplitService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "${api.path.topsecret.split}")
@CrossOrigin(origins = "*")
public class TopSecretSplitController {
	
	@Autowired
	private PositionSpaceshipSplitService positionSpaceshipSplitService;

	
	/**
	 * Registrar informacion del satelite
	 * @param informationSatellite
	 * @param satelliteName
	 * @return
	 */
	@RequestMapping(value = {"/", "/{satelliteName}"}, method = RequestMethod.POST)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal error server")
    })
    public ResponseEntity<List<Satellite>> locationSpaceship(@Valid @RequestBody PositionSpaceShipSplit informationSatellite, @PathVariable(required = false) String satelliteName){
        return new ResponseEntity<List<Satellite>>(positionSpaceshipSplitService.getSpacheshipSplit(informationSatellite, satelliteName), HttpStatus.OK);
    }
	
	/**
	 * Consultar informacion de los satelites registrado, estos quedan almacenados en una variable global, por tal razon al momento de bajar 
	 * el servicio se eliminara la informacion de los satelites previamente almacenadas.
	 * @param satelliteName
	 * @return
	 */
	@GetMapping("/{satelliteName}")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal error server")
	})
    public ResponseEntity<Satellite> informatioSatellite(@PathVariable String satelliteName){
        return new ResponseEntity<Satellite>(positionSpaceshipSplitService.getSatellite(satelliteName), HttpStatus.OK);
    }
	
}
