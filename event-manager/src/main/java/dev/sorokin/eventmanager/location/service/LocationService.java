package dev.sorokin.eventmanager.location.service;

import dev.sorokin.eventmanager.location.converter.LocationEntityConverter;
import dev.sorokin.eventmanager.location.dto.CreateLocationDto;
import dev.sorokin.eventmanager.location.entity.Location;
import dev.sorokin.eventmanager.location.exceptions.LocationNameIsAlreadyExist;
import dev.sorokin.eventmanager.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationEntityConverter entityConverter;

    public Location createLocation(Location location) {

        if (locationRepository.existsByName(location.name())) {
            throw new LocationNameIsAlreadyExist(
                    "Location with name " + location.name() + " already exists"
            );
        }


        var entity = locationRepository.save(entityConverter.toEntity(location));

        System.out.println(entity);

        return entityConverter.toDomain(entity);
    }
}
