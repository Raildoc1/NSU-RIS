package nsu.gaiduk.database.mappers;

import javax.annotation.processing.Generated;
import nsu.gaiduk.database.dto.WayDto;
import nsu.gaiduk.database.dto.WayDto.WayDtoBuilder;
import nsu.gaiduk.database.entities.WayEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-13T16:53:24+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.12 (Microsoft)"
)
public class WayMapperImpl implements WayMapper {

    @Override
    public WayDto toDto(WayEntity wayEntity) {
        if ( wayEntity == null ) {
            return null;
        }

        WayDtoBuilder wayDto = WayDto.builder();

        wayDto.id( wayEntity.getId() );
        wayDto.username( wayEntity.getUsername() );
        wayDto.uid( wayEntity.getUid() );
        wayDto.visible( wayEntity.getVisible() );
        wayDto.version( wayEntity.getVersion() );
        wayDto.changeset( wayEntity.getChangeset() );
        wayDto.timestamp( wayEntity.getTimestamp() );

        return wayDto.build();
    }

    @Override
    public WayEntity toEntity(WayDto wayDto) {
        if ( wayDto == null ) {
            return null;
        }

        WayEntity wayEntity = new WayEntity();

        wayEntity.setId( wayDto.getId() );
        wayEntity.setUsername( wayDto.getUsername() );
        wayEntity.setUid( wayDto.getUid() );
        wayEntity.setVisible( wayDto.getVisible() );
        wayEntity.setVersion( wayDto.getVersion() );
        wayEntity.setChangeset( wayDto.getChangeset() );
        wayEntity.setTimestamp( wayDto.getTimestamp() );

        return wayEntity;
    }
}
