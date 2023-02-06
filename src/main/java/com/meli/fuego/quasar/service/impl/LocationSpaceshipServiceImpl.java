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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.meli.fuego.quasar.entity.Position;
import com.meli.fuego.quasar.entity.PositionSpaceShip;
import com.meli.fuego.quasar.entity.Satellite;
import com.meli.fuego.quasar.entity.SatelliteSign;
import com.meli.fuego.quasar.service.LocationSpaceshipService;

/**
 * Clase que responde a los ejercicios del Nivel 1 y 2, conta de los metodos de localizacion de la nave (getSatellite) y mensaje compleo (getSpacheshipSplit)
 * @author Edilson
 *
 */
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
	private String getMessage(List<Satellite> messageDe) {
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
	 * NOTA : new TrilaterationFunction (Sirve como un metodo de ubicacion de elementos) libreria com.lemmingapex.trilateration
	 */
	private Position getLocation(List<Satellite> distances) {
		
		double[] range = new double[distances.size()];
		for(int i = 0 ; i < distances.size() ; i++ ) {
			range[i] = distances.get(i).getDistance();
		}
		logger.info( Arrays.toString(range));
        try {
            //Distancias entre Kenobi y Skywalker (-2x(1) + 2x(2))x + (-2y1 + 2y2)y = r^2(1)-r^2(2)-x^2(1)-x^2(1)-y^2(1)-y^2(1)
            double ksa = -2 * KENOBI.getX() + 2 * SKYWALKER.getX();
            double ksb = -2 * KENOBI.getY() + 2 * SKYWALKER.getY();
            double res_ks = pow(range[0], 2) - pow(range[1], 2) - pow(KENOBI.getX(), 2) + pow(SKYWALKER.getX(), 2) - pow(KENOBI.getY(), 2) + pow(SKYWALKER.getY(), 2);
            //Distancias entre Skywalker y Sato
            double sse = -2 * SKYWALKER.getX() + 2 * SATO.getX();
            double ssf = -2 * SKYWALKER.getY() + 2 * SATO.getY();
            double res_ss = pow(range[1], 2) - pow(range[2], 2) - pow(SKYWALKER.getX(), 2) + pow(SATO.getX(), 2) - pow(SKYWALKER.getY(), 2) + pow(SATO.getY(), 2);
            //Determinar ubicacion nave principal
            double d = ksa * ssf - ksb * sse;
            if (d == 0.0) {throw new IllegalArgumentException("no se encuentran las coordenadas");}
            double dx = res_ks * ssf - ksb * res_ss;
            double dy = ksa * res_ss - res_ks * sse;
            // Determinar x=Dx/D y=Dy/D
            float x = BigDecimal.valueOf(dx / d).setScale(2, 0).floatValue();
            float y = BigDecimal.valueOf(dy / d).setScale(2, 0).floatValue();
            return new Position(x, y);
        } catch(final Exception ex) {
        	logger.error("Error al calcular la ubicacion", Arrays.toString(range), ex);
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
	
}
