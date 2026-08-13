package dev.sorokin.eventmanager.locations;

import dev.sorokin.eventmanager.AbstractTest;
import dev.sorokin.eventmanager.location.entity.Location;
import dev.sorokin.eventmanager.location.repository.LocationRepository;
import dev.sorokin.eventmanager.location.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LocationsControllerTest extends AbstractTest {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;


    @Test
    void shouldSuccessCreateLocation() throws Exception {

    }



//    private Location createDummyLocation() {
//        return locationService.createLocation(new Location(
//                null,
//
//        ))
//    }
}
