package com.petstore.web.api;

import com.petstore.web.dto.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PetsApiImpl implements PetsApi {

    public static final Pet FIDO = new Pet().id(1L).name("Fido").tag("dog").age(3);
    public static final Pet TIDDLES = new Pet().id(2L).name("Tiddles").tag("cat").age(6);

    @Override
    public ResponseEntity<Void> createPets() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(@Valid Integer limit) {
        return ResponseEntity.ok(List.of(FIDO, TIDDLES));
    }

    @Override
    public ResponseEntity<Pet> showPetById(String petId) {
        if (petId.equals("1")) {
            return ResponseEntity.ok(FIDO);
        } else if (petId.equals("2")) {
            return ResponseEntity.ok(TIDDLES);
        }
        return ResponseEntity.notFound().build();
    }
}