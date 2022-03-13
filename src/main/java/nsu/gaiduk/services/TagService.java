package nsu.gaiduk.services;

import lombok.RequiredArgsConstructor;
import nsu.gaiduk.database.dto.TagDto;
import nsu.gaiduk.database.entities.TagEntity;
import nsu.gaiduk.database.mappers.TagMapper;
import nsu.gaiduk.database.repositories.TagRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository repository;

    public TagDto get(String k) {
        return TagMapper.INSTANCE.toDto(repository.findById(k).orElse(null));
    }

    public TagDto create(TagDto dto) {
        TagEntity entity = TagEntity.builder()
                .k(dto.getK())
                .v(dto.getV())
                .build();
        return TagMapper.INSTANCE.toDto(repository.save(entity));
    }

    public TagDto update(TagDto dto) {
        TagEntity entity = TagEntity.builder()
                .k(dto.getK())
                .v(dto.getV())
                .build();
        return TagMapper.INSTANCE.toDto(repository.save(entity));
    }

    public void delete(String k) {
        repository.deleteById(k);
    }
}
