package nsu.gaiduk.services;

import lombok.RequiredArgsConstructor;
import nsu.gaiduk.database.dto.NodeDto;
import nsu.gaiduk.database.entities.NodeEntity;
import nsu.gaiduk.database.mappers.NodeMapper;
import nsu.gaiduk.database.repositories.NodeRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NodeService {
    private final NodeRepository repository;

    public NodeDto get(BigInteger k) {
        return NodeMapper.INSTANCE.toDto(repository.findById(k).orElse(null));
    }

    public List<NodeDto> findInRadius(double lat, double lon, double radius) {
        List<NodeDto> result = new ArrayList<>();
        List<NodeEntity> entities = repository.findAll();

        for (NodeEntity e : entities) {
            double v1 = e.getLat() - lat;
            double v2 = e.getLon() - lon;
            double r2 = radius * radius;

            if(v1 * v1 + v2 * v2 > r2) {
                continue;
            }
            result.add(NodeMapper.INSTANCE.toDto(e));
        }
        return result;
    }

    public NodeDto create(NodeDto dto) {
        NodeEntity entity = NodeMapper.INSTANCE.toEntity(dto);
        return NodeMapper.INSTANCE.toDto(repository.save(entity));
    }

    public NodeDto update(NodeDto dto) {
        NodeEntity entity = NodeMapper.INSTANCE.toEntity(dto);
        return NodeMapper.INSTANCE.toDto(repository.save(entity));
    }

    public void delete(BigInteger k) {
        repository.deleteById(k);
    }
}
