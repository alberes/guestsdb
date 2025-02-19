package io.github.alberes.guestdb.repositories;

import io.github.alberes.guestdb.domains.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {
}
