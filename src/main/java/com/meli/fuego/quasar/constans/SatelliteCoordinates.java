package com.meli.fuego.quasar.constans;

public enum SatelliteCoordinates {
	
	KENOBI(-500, -200),
    SKYWALKER(100, -100),
    SATO(500, 100);

    float x;
    float y;
    
    private SatelliteCoordinates (float x, float y){
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
