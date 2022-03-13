package nsu.gaiduk.services;

import lombok.RequiredArgsConstructor;
import nsu.gaiduk.database.dto.NodeDto;
import nsu.gaiduk.database.entities.NodeEntity;
import nsu.gaiduk.database.mappers.NodeMapper;
import nsu.gaiduk.database.repositories.NodeRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class NodeService {
    private final NodeRepository repository;

    public NodeDto get(BigInteger k) {
        return NodeMapper.INSTANCE.toDto(repository.findById(k).orElse(null));
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
