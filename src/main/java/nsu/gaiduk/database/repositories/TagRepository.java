package nsu.gaiduk.database.repositories;

import nsu.gaiduk.database.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, String> {
}
