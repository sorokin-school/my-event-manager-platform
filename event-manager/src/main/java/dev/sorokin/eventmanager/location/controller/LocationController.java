package dev.sorokin.eventmanager.location.controller;

import dev.sorokin.eventmanager.location.converter.LocationDtoConverter;
import dev.sorokin.eventmanager.location.dto.CreateLocationDto;
import dev.sorokin.eventmanager.location.dto.LocationDto;
import dev.sorokin.eventmanager.location.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
