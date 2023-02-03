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

@RestController
@RequestMapping(path = "${api.path.topsecret.split}")
@CrossOrigin(origins = "*")
public class TopSecretSplitController {
	
	@Autowired
	private PositionSpaceshipSplitService positionSpaceshipSplitService;

	@RequestMapping(value = {"/", "/{satelliteName}"}, method = RequestMethod.POST)
    public ResponseEntity<List<Satellite>> locationSpaceship(@Valid @RequestBody PositionSpaceShipSplit informationSatellite, @PathVariable(required = false) String satelliteName){
        return new ResponseEntity<List<Satellite>>(positionSpaceshipSplitService.getSpacheshipSplit(informationSatellite, satelliteName), HttpStatus.OK);
    }
	
	@GetMapping("/{satelliteName}")
    public ResponseEntity<Satellite> informatioSatellite(@PathVariable String satelliteName){
        return new ResponseEntity<Satellite>(positionSpaceshipSplitService.getSatellite(satelliteName), HttpStatus.OK);
    }
	
}
