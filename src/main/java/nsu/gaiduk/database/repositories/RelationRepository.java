package nsu.gaiduk.database.repositories;

import nsu.gaiduk.database.entities.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RelationRepository extends JpaRepository<RelationEntity, BigInteger> {
}
