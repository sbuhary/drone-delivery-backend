package co.penny.dronedelivery.drones.api;

import co.penny.dronedelivery.common.dto.LocationDto;

public record DroneDto(String id, String name, String status, LocationDto location, String currentOrderId) {
}
