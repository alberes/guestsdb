package io.github.alberes.guestdb.controller;

import io.github.alberes.guestdb.controller.dto.GuestDto;
import io.github.alberes.guestdb.controller.mappers.GuestMapper;
import io.github.alberes.guestdb.domains.Guest;
import io.github.alberes.guestdb.services.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guests")
@RequiredArgsConstructor
public class GuestController implements GenericController {

    private final GuestService service;

    private final GuestMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid GuestDto dto){
        Guest guest = this.mapper.toEntity(dto);
        guest = this.service.save(guest);
        return ResponseEntity.created(
                this.createURI("/{id}", guest.getLegalEntityNumber()))
                .build();
    }

    @GetMapping("/{legalEntityNumber}")
    public ResponseEntity<GuestDto> find(@PathVariable String legalEntityNumber){
        Guest guest = this.service.find(legalEntityNumber);
        if(guest == null){
            return ResponseEntity.notFound().build();
        }
        GuestDto dto = this.mapper.toDTO(guest);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{legalEntityNumber}")
    public ResponseEntity<Void> update(@PathVariable String legalEntityNumber, @RequestBody GuestDto dto){
        Guest guest = this.mapper.toEntity(dto);
        guest.setLegalEntityNumber(legalEntityNumber);
        this.service.update(guest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{legalEntityNumber}")
    public ResponseEntity<Void> delete(@PathVariable String legalEntityNumber){
        this.service.delete(legalEntityNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<GuestDto>> page(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){

        Page<Guest> pageGuest = this.service.findPage(page, linesPerPage, orderBy, direction);

        if(pageGuest.getTotalElements() == 0){
            return ResponseEntity.noContent().build();
        }

        Page<GuestDto> pageDto = pageGuest.map(this.mapper::toDTO);

        return ResponseEntity.ok(pageDto);
    }
}
