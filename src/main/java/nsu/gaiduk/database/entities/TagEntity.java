package nsu.gaiduk.database.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "tag")
public class TagEntity {

    @ManyToMany(mappedBy = "tags")
    @ToString.Exclude
    private List<NodeEntity> nodes;

    @Id
    private String k;
    private String v;
}
