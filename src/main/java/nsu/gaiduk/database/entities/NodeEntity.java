package nsu.gaiduk.database.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "node")
public class NodeEntity {

    @ManyToMany
    @JoinTable(name = "node_tag", joinColumns = @JoinColumn(name = "node_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private List<TagEntity> tags;

    @Id
    private BigInteger id;
    private Double lat;
    private Double lon;
    private String username;
    private BigInteger uid;
    private Boolean visible;
    private BigInteger version;
    private BigInteger changeset;
    private LocalDate timestamp;
}
