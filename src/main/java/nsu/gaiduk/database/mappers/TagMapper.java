package nsu.gaiduk.database.mappers;

import nsu.gaiduk.database.dto.TagDto;
import nsu.gaiduk.database.entities.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDto toDto(TagEntity tagEntity);
    TagEntity toEntity(TagDto tagDto);
}
