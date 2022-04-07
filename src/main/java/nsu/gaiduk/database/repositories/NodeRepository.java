package nsu.gaiduk.database.repositories;

import nsu.gaiduk.database.entities.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, BigInteger> {
    @Query(
            value = "SELECT * FROM node"
                    + " WHERE gc_to_sec(earth_distance(ll_to_earth(?1, ?2), ll_to_earth(node.lat, node.lon))) < ?3"
                    + " ORDER BY gc_to_sec(earth_distance(ll_to_earth(?1, ?2), ll_to_earth(node.lat, node.lon))) ASC",
            nativeQuery = true
    )
    List<NodeEntity> findAllNodesInRadius(Double latitude, Double longitude, Double radius);
}
