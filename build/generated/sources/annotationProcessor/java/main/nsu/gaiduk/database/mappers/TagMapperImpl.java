package nsu.gaiduk.database.mappers;

import javax.annotation.processing.Generated;
import nsu.gaiduk.database.dto.TagDto;
import nsu.gaiduk.database.dto.TagDto.TagDtoBuilder;
import nsu.gaiduk.database.entities.TagEntity;
import nsu.gaiduk.database.entities.TagEntity.TagEntityBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-13T17:48:14+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.12 (Microsoft)"
)
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDto toDto(TagEntity tagEntity) {
        if ( tagEntity == null ) {
            return null;
        }

        TagDtoBuilder tagDto = TagDto.builder();

        tagDto.k( tagEntity.getK() );
        tagDto.v( tagEntity.getV() );

        return tagDto.build();
    }

    @Override
    public TagEntity toEntity(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        TagEntityBuilder tagEntity = TagEntity.builder();

        tagEntity.k( tagDto.getK() );
        tagEntity.v( tagDto.getV() );

        return tagEntity.build();
    }
}
