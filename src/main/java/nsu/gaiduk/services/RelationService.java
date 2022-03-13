package nsu.gaiduk.services;

import lombok.RequiredArgsConstructor;
import nsu.gaiduk.database.dto.RelationDto;
import nsu.gaiduk.database.entities.RelationEntity;
import nsu.gaiduk.database.mappers.RelationMapper;
import nsu.gaiduk.database.repositories.RelationRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class RelationService {
    private final RelationRepository repository;

    public RelationDto get(BigInteger k) {
        return RelationMapper.INSTANCE.toDto(repository.findById(k).orElse(null));
    }

    public RelationDto create(RelationDto dto) {
        RelationEntity entity = RelationMapper.INSTANCE.toEntity(dto);
        return RelationMapper.INSTANCE.toDto(repository.save(entity));
    }

    public RelationDto update(RelationDto dto) {
        RelationEntity entity = RelationMapper.INSTANCE.toEntity(dto);
        return RelationMapper.INSTANCE.toDto(repository.save(entity));
    }

    public void delete(BigInteger k) {
        repository.deleteById(k);
    }
}
