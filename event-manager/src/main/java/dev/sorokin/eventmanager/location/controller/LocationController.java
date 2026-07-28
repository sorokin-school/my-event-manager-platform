package dev.sorokin.eventmanager.location.controller;

import dev.sorokin.eventmanager.location.converter.LocationDtoConverter;
import dev.sorokin.eventmanager.location.dto.CreateLocationDto;
import dev.sorokin.eventmanager.location.dto.LocationDto;
import dev.sorokin.eventmanager.location.dto.UpdateLocationDto;
import dev.sorokin.eventmanager.location.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(name = "/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final LocationDtoConverter locationDtoConverter;



    @PostMapping
    public ResponseEntity<LocationDto> createLocation(
                @Valid @RequestBody CreateLocationDto createLocationDto
        ) {

        log.info("Got request for create location");


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationDtoConverter.toDto(
                        locationService.createLocation(
                                locationDtoConverter.toDomain(createLocationDto)
                        )
                ));
    }

    @GetMapping
    public List<LocationDto> getLocations() {

        log.info("Got request for get locations list");

        return locationService.getAllLocations().stream()
                .map(locationDtoConverter::toDto)
                .toList();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LocationDto> updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody UpdateLocationDto updateLocationDto
            ) {

        log.info("Got request for update location");

        return ResponseEntity
                .ok(
                        locationDtoConverter.toDto(
                                locationService.updateLocation(
                                        id,
                                        locationDtoConverter.updateToDomain(updateLocationDto)
                                )
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Long id) {

        log.info("Got request for get location by id: {}", id);


        return ResponseEntity
                .ok(
                        locationDtoConverter.toDto(
                                locationService.getLocationById(id)
                        )
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocationById(@PathVariable Long id) {

        log.info("Got request for delete location by id: {}", id);

        locationService.deleteLocationById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
