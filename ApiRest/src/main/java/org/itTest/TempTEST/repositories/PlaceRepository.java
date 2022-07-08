package org.itTest.TempTEST.repositories;

import org.itTest.TempTEST.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, String> {
}
