package io.github.alberes.guestdb.services;

import io.github.alberes.guestdb.domains.Guest;
import io.github.alberes.guestdb.repositories.GuestRepository;
import io.github.alberes.guestdb.services.exception.DuplicateRecordException;
import io.github.alberes.guestdb.services.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository repository;

    @Modifying
    @Transactional
    public Guest save(Guest guest){
        Optional<Guest> guestDB = this.repository.findById(guest.getLegalEntityNumber());
        if(guestDB.isPresent()){
            throw new DuplicateRecordException("Registration with " + guest.getLegalEntityNumber() + " has already been registered!");
        }
        return this.repository.save(guest);
    }

    @Transactional(readOnly = true)
    public Guest find(String legalEntityNumber){
        Optional<Guest> guest = this.repository.findById(legalEntityNumber);
        return guest.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + legalEntityNumber + ", Type: " + Guest.class.getName()
        ));
    }

    @Modifying
    @Transactional
    public Guest update(Guest guest){
        Guest guestDB = this.find(guest.getLegalEntityNumber());
        guestDB.setName(guest.getName());
        guestDB.setBirthday(guest.getBirthday());
        return this.repository.save(guestDB);
    }

    @Modifying
    @Transactional
    public void delete(String legalEntityNumber){
        Guest guest = this.find(legalEntityNumber);
        this.repository.deleteById(legalEntityNumber);
    }

    @Transactional(readOnly = true)
    public Page<Guest> findPage(Integer page, Integer linesPerPage,
                                String orderBy, String direction){
        return this.repository.findAll(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }
}
