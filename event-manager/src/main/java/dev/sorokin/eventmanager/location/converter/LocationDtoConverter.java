package dev.sorokin.eventmanager.location.converter;

import dev.sorokin.eventmanager.location.dto.CreateLocationDto;
import dev.sorokin.eventmanager.location.dto.LocationDto;
import dev.sorokin.eventmanager.location.dto.UpdateLocationDto;
import dev.sorokin.eventmanager.location.entity.Location;
import io.lettuce.core.dynamic.annotation.Command;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoConverter {


    public LocationDto toDto(Location location) {
        return new LocationDto(
                location.id(),
                location.name(),
                location.address(),
                location.capacity(),
                location.description()
        );
    }

    public Location toDomain(CreateLocationDto createLocationDto) {
        return new Location(
                null,
                createLocationDto.name(),
                createLocationDto.address(),
                createLocationDto.capacity(),
                createLocationDto.description()
        );
    }

    public Location updateToDomain(UpdateLocationDto updateLocationDto) {
        return new Location(
                null,
                updateLocationDto.name(),
                updateLocationDto.address(),
                updateLocationDto.capacity(),
                updateLocationDto.description()
        );
    }
}
