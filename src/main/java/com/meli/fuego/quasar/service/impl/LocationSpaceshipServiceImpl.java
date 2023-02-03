package com.meli.fuego.quasar.service.impl;

import static com.meli.fuego.quasar.constans.SatelliteCoordinates.KENOBI;
import static com.meli.fuego.quasar.constans.SatelliteCoordinates.SATO;
import static com.meli.fuego.quasar.constans.SatelliteCoordinates.SKYWALKER;
import static java.lang.Math.pow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meli.fuego.quasar.entity.Position;
import com.meli.fuego.quasar.entity.PositionSpaceShip;
import com.meli.fuego.quasar.entity.Satellite;
import com.meli.fuego.quasar.entity.SatelliteSign;
import com.meli.fuego.quasar.exception.PositionNotFoundException;
import com.meli.fuego.quasar.service.LocationSpaceshipService;

@Service
public class LocationSpaceshipServiceImpl implements LocationSpaceshipService{
	
	private static Logger logger = LoggerFactory.getLogger(LocationSpaceshipServiceImpl.class);

	public PositionSpaceShip getSpacheship(SatelliteSign informationSpaceship) {
		return new PositionSpaceShip(getLocation(informationSpaceship.getSatellites()), getMessage(informationSpaceship.getSatellites()));
	}
	
	/**
	 * Extraer el mensaje Codificado
	 * @param messageDe (Array con la informacion de los mensajes parciales de los satelites)
	 * @return
	 */
	public String getMessage(List<Satellite> messageDe) {
		// Extraer listado de mensaje
		List<List<String>> messages = new ArrayList<List<String>>();
		messageDe.stream().forEach(s -> {
        	messages.add(s.getMessage());
        });
		
		
		List<String> fullMessage = Arrays.asList(new String[messages.get(0).size()]);
		for (List<String> msg : messages) {
			IntStream.range(0, msg.size())
			.filter(i -> !msg.get(i).isEmpty())
			.mapToObj(i -> fullMessage.set(i, msg.get(i)))
			.collect(Collectors.toList());
		}
		
		//Convertir Array en String
		//return fullMessage.toString().replaceAll("\\[|\\]", "").replaceAll(", "," ");
		return String.join(" ", fullMessage);
		
	}
	
	/**
	 * Obtener la Ubicacion
	 * @param distances (Array con la informacion de los satelites)
	 * @return
	 */
	public Position getLocation(List<Satellite> distances) {
		
		double[] range = new double[distances.size()];
		for(int i = 0 ; i < distances.size() ; i++ ) {
			range[i] = distances.get(i).getDistance();
		}
		
		logger.info("Calculate trilateration status=START distances={}", Arrays.toString(range));
        try {
            //Distance equation Kenobi - Skywalker
            double a = -2 * KENOBI.getX() + 2 * SKYWALKER.getX();
            double b = -2 * KENOBI.getY() + 2 * SKYWALKER.getY();
            double c = pow(range[0], 2) - pow(range[1], 2) - pow(KENOBI.getX(), 2) + pow(SKYWALKER.getX(), 2) - pow(KENOBI.getY(), 2) + pow(SKYWALKER.getY(), 2);
            //Distance equation Skywalker - Sato
            double e = -2 * SKYWALKER.getX() + 2 * SATO.getX();
            double f = -2 * SKYWALKER.getY() + 2 * SATO.getY();
            double g = pow(range[1], 2) - pow(range[2], 2) - pow(SKYWALKER.getX(), 2) + pow(SATO.getX(), 2) - pow(SKYWALKER.getY(), 2) + pow(SATO.getY(), 2);
            //Finding Cramer's rule determinants
            double d = a * f - b * e;
            if (d == 0.0)
                throw new IllegalArgumentException("could.not.find.spaceship.coordinates");
            double dx = c * f - b * g;
            double dy = a * g - c * e;
            float x = BigDecimal.valueOf(dx / d).setScale(2, 0).floatValue();
            float y = BigDecimal.valueOf(dy / d).setScale(2, 0).floatValue();
            return new Position(x, y);
        } catch(final Exception ex) {
        	logger.error("Calculate trilateration status=ERROR distances={}", Arrays.toString(range), ex);
            throw new PositionNotFoundException(ex.getMessage());
        }
    }

}
