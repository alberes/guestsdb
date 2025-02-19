package io.github.alberes.guestdb.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Guest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String legalEntityNumber;

    private String name;

    private LocalDate birthday;

    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @CreatedDate
    private LocalDateTime creationDate;
}
