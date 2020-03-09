package com.petstore.web.api;

import com.petstore.web.dto.Pet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
public class PetsApiImpl implements PetsApi {

    private final String RESOURCE = "/pet";

    private static final Pet FIDO = new Pet().id(1L).name("Fido").tag("dog").age(3);
    private static final Pet TIDDLES = new Pet().id(2L).name("Tiddles").tag("cat").age(6);

    private static List<Pet> pets = Stream.of(FIDO, TIDDLES).collect(Collectors.toList());

    @Override
    public ResponseEntity<Void> createPets(@Valid Pet pet) {
        // Don't persist the new pet
        log.info("Creating new pet {}", pet);
        return ResponseEntity.created(buildUri(pet.getId())).build();
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(@Valid Integer limit) {
        log.info("Responding with all pets");
        return ResponseEntity.ok(pets);
    }

    @Override
    public ResponseEntity<Pet> showPetById(Long petId) {
        return pets.stream()
                .filter(p -> p.getId().equals(petId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private URI buildUri(long petId) {
        return UriComponentsBuilder
                .fromPath(RESOURCE)
                .path("/{pet-id}")
                .buildAndExpand(petId)
                .toUri();
    }
}