package nsu.gaiduk.services;

import lombok.RequiredArgsConstructor;
import nsu.gaiduk.database.dto.WayDto;
import nsu.gaiduk.database.entities.WayEntity;
import nsu.gaiduk.database.mappers.WayMapper;
import nsu.gaiduk.database.repositories.WayRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class WayService {
    private final WayRepository repository;

    public WayDto get(BigInteger k) {
        return WayMapper.INSTANCE.toDto(repository.findById(k).orElse(null));
    }

    public WayDto create(WayDto dto) {
        WayEntity entity = WayMapper.INSTANCE.toEntity(dto);
        return WayMapper.INSTANCE.toDto(repository.save(entity));
    }

    public WayDto update(WayDto dto) {
        WayEntity entity = WayMapper.INSTANCE.toEntity(dto);
        return WayMapper.INSTANCE.toDto(repository.save(entity));
    }

    public void delete(BigInteger k) {
        repository.deleteById(k);
    }
}
