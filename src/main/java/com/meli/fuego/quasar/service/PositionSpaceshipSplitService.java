package com.meli.fuego.quasar.service;

import java.util.List;

import com.meli.fuego.quasar.entity.PositionSpaceShipSplit;
import com.meli.fuego.quasar.entity.Satellite;

public interface PositionSpaceshipSplitService {

	public List<Satellite> getSpacheshipSplit(PositionSpaceShipSplit informationSatellite, String nameSatellite);
	
	public Satellite getSatellite(String satelliteName);
}
