package co.penny.dronedelivery.drones.controller;

import co.penny.dronedelivery.common.dto.LocationDto;
import co.penny.dronedelivery.drones.api.DroneDto;
import co.penny.dronedelivery.drones.repository.DroneRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drones")
public class DroneController {

    private final DroneRepository repo;

    public DroneController(DroneRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<DroneDto>> list() {
        return ResponseEntity.ok(repo.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DroneDto> get(@PathVariable("id") String id) {
        DroneDto d = repo.findById(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @PostMapping("/{id}/location")
    public ResponseEntity<Void> location(@PathVariable("id") String id, @RequestBody LocationDto loc) {
        repo.updateLocation(id, loc);
        return ResponseEntity.ok().build();
    }
}
