package nsu.gaiduk.database.mappers;

import nsu.gaiduk.database.dto.NodeDto;
import nsu.gaiduk.database.entities.NodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NodeMapper {
    NodeMapper INSTANCE = Mappers.getMapper(NodeMapper.class);

    NodeDto toDto(NodeEntity nodeEntity);
    NodeEntity toEntity(NodeDto nodeDto);
}
