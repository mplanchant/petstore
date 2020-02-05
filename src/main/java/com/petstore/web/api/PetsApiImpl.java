package com.petstore.web.api;

import com.petstore.web.dto.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PetsApiImpl implements PetsApi {

    @Override
    public ResponseEntity<Void> createPets() {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(@Valid Integer limit) {
        var fido = new Pet();
        fido.setName("Fido");
        fido.setId(1L);
        fido.setAge(3);
        fido.setTag("dog");

        var tiddles = new Pet();
        tiddles.setName("Tiddles");
        tiddles.setTag("cat");
        tiddles.setAge(6);
        tiddles.setId(2L);

        return ResponseEntity.ok(List.of(fido, tiddles));
    }

    @Override
    public ResponseEntity<Pet> showPetById(String petId) {
        var fido = new Pet();
        fido.setName("Fido");
        fido.setId(1L);
        fido.setAge(3);
        fido.setTag("dog");

        var tiddles = new Pet();
        tiddles.setName("Tiddles");
        tiddles.setTag("cat");
        tiddles.setAge(6);
        tiddles.setId(2L);

        if (petId.equals("1")) {
            return ResponseEntity.ok(fido);
        } else if (petId.equals("2")) {
            return ResponseEntity.ok(tiddles);
        }
        return ResponseEntity.notFound().build();
    }
}