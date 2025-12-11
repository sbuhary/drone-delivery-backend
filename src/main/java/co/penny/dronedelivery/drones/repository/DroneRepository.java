package co.penny.dronedelivery.drones.repository;

import co.penny.dronedelivery.drones.api.DroneDto;
import co.penny.dronedelivery.common.dto.LocationDto;
import java.util.List;

public interface DroneRepository {
    List<DroneDto> listAll();
    DroneDto findById(String id);
    void updateLocation(String id, LocationDto loc);
}
