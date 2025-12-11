package co.penny.dronedelivery.drones.adapter;

import co.penny.dronedelivery.common.dto.LocationDto;
import co.penny.dronedelivery.drones.api.DroneDto;
import co.penny.dronedelivery.drones.repository.DroneRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class InMemoryDroneRepository implements DroneRepository {

    private final List<DroneDto> drones = new ArrayList<>();

    public InMemoryDroneRepository() {
        drones.add(new DroneDto("drone-1", "drone-1", "available", new LocationDto(0.0,0.0,null), null));
    }

    @Override
    public List<DroneDto> listAll() {
        return List.copyOf(drones);
    }

    @Override
    public DroneDto findById(String id) {
        return drones.stream().filter(d -> d.id().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void updateLocation(String id, LocationDto loc) {
        for (int i = 0; i < drones.size(); i++) {
            DroneDto d = drones.get(i);
            if (d.id().equals(id)) {
                drones.set(i, new DroneDto(d.id(), d.name(), d.status(), loc, d.currentOrderId()));
                return;
            }
        }
        drones.add(new DroneDto(id, id, "available", loc, null));
    }
}
