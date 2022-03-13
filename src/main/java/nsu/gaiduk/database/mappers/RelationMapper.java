package nsu.gaiduk.database.mappers;

import nsu.gaiduk.database.dto.RelationDto;
import nsu.gaiduk.database.entities.RelationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RelationMapper {
    RelationMapper INSTANCE = Mappers.getMapper(RelationMapper.class);

    RelationDto toDto(RelationEntity relationEntity);
    RelationEntity toEntity(RelationDto relationDto);
}
