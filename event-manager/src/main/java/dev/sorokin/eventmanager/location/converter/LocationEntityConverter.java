package dev.sorokin.eventmanager.location.converter;

import dev.sorokin.eventmanager.location.entity.Location;
import dev.sorokin.eventmanager.location.entity.LocationEntity;
import org.springframework.stereotype.Component;

@Component
public class LocationEntityConverter {

    public LocationEntity toEntity(Location location) {
        return new LocationEntity(
                location.id(),
                location.name(),
                location.address(),
                location.capacity(),
                location.description()
        );
    }

    public Location toDomain(LocationEntity entity) {
        return new Location(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getCapacity(),
                entity.getDescription()
        );
    }
}
