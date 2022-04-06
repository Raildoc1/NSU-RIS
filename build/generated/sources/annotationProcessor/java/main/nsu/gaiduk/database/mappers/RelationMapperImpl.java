package nsu.gaiduk.database.mappers;

import javax.annotation.processing.Generated;
import nsu.gaiduk.database.dto.RelationDto;
import nsu.gaiduk.database.dto.RelationDto.RelationDtoBuilder;
import nsu.gaiduk.database.entities.RelationEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-06T21:09:42+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.12 (Microsoft)"
)
public class RelationMapperImpl implements RelationMapper {

    @Override
    public RelationDto toDto(RelationEntity relationEntity) {
        if ( relationEntity == null ) {
            return null;
        }

        RelationDtoBuilder relationDto = RelationDto.builder();

        relationDto.id( relationEntity.getId() );
        relationDto.username( relationEntity.getUsername() );
        relationDto.uid( relationEntity.getUid() );
        relationDto.visible( relationEntity.getVisible() );
        relationDto.version( relationEntity.getVersion() );
        relationDto.changeset( relationEntity.getChangeset() );
        relationDto.timestamp( relationEntity.getTimestamp() );

        return relationDto.build();
    }

    @Override
    public RelationEntity toEntity(RelationDto relationDto) {
        if ( relationDto == null ) {
            return null;
        }

        RelationEntity relationEntity = new RelationEntity();

        relationEntity.setId( relationDto.getId() );
        relationEntity.setUsername( relationDto.getUsername() );
        relationEntity.setUid( relationDto.getUid() );
        relationEntity.setVisible( relationDto.getVisible() );
        relationEntity.setVersion( relationDto.getVersion() );
        relationEntity.setChangeset( relationDto.getChangeset() );
        relationEntity.setTimestamp( relationDto.getTimestamp() );

        return relationEntity;
    }
}
