package nl.jft.logic.participant;

import java.io.Serializable;

/**
 * @author Oscar de Leeuw.
 */

public class LocationJft implements Serializable {

    public double longitude;
    public double latitude;

    public LocationJft(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationJft() {
    }
}
