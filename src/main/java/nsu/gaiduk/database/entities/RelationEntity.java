package nsu.gaiduk.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "relation")
public class RelationEntity {
    @Id
    private BigInteger id;
    private String username;
    private BigInteger uid;
    private Boolean visible;
    private BigInteger version;
    private BigInteger changeset;
    private LocalDate timestamp;
}
