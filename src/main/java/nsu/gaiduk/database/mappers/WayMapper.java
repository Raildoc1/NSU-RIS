package nsu.gaiduk.database.mappers;

import nsu.gaiduk.database.dto.WayDto;
import nsu.gaiduk.database.entities.WayEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WayMapper {
    WayMapper INSTANCE = Mappers.getMapper(WayMapper.class);

    WayDto toDto(WayEntity wayEntity);
    WayEntity toEntity(WayDto wayDto);
}
