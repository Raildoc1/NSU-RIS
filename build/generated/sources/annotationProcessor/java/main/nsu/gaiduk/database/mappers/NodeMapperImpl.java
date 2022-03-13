package nsu.gaiduk.database.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import nsu.gaiduk.database.dto.NodeDto;
import nsu.gaiduk.database.dto.NodeDto.NodeDtoBuilder;
import nsu.gaiduk.database.dto.TagDto;
import nsu.gaiduk.database.dto.TagDto.TagDtoBuilder;
import nsu.gaiduk.database.entities.NodeEntity;
import nsu.gaiduk.database.entities.NodeEntity.NodeEntityBuilder;
import nsu.gaiduk.database.entities.TagEntity;
import nsu.gaiduk.database.entities.TagEntity.TagEntityBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-13T17:48:13+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.12 (Microsoft)"
)
public class NodeMapperImpl implements NodeMapper {

    @Override
    public NodeDto toDto(NodeEntity nodeEntity) {
        if ( nodeEntity == null ) {
            return null;
        }

        NodeDtoBuilder nodeDto = NodeDto.builder();

        nodeDto.tags( tagEntityListToTagDtoList( nodeEntity.getTags() ) );
        nodeDto.id( nodeEntity.getId() );
        nodeDto.lat( nodeEntity.getLat() );
        nodeDto.lon( nodeEntity.getLon() );
        nodeDto.username( nodeEntity.getUsername() );
        nodeDto.uid( nodeEntity.getUid() );
        nodeDto.visible( nodeEntity.getVisible() );
        nodeDto.version( nodeEntity.getVersion() );
        nodeDto.changeset( nodeEntity.getChangeset() );
        nodeDto.timestamp( nodeEntity.getTimestamp() );

        return nodeDto.build();
    }

    @Override
    public NodeEntity toEntity(NodeDto nodeDto) {
        if ( nodeDto == null ) {
            return null;
        }

        NodeEntityBuilder nodeEntity = NodeEntity.builder();

        nodeEntity.tags( tagDtoListToTagEntityList( nodeDto.getTags() ) );
        nodeEntity.id( nodeDto.getId() );
        nodeEntity.lat( nodeDto.getLat() );
        nodeEntity.lon( nodeDto.getLon() );
        nodeEntity.username( nodeDto.getUsername() );
        nodeEntity.uid( nodeDto.getUid() );
        nodeEntity.visible( nodeDto.getVisible() );
        nodeEntity.version( nodeDto.getVersion() );
        nodeEntity.changeset( nodeDto.getChangeset() );
        nodeEntity.timestamp( nodeDto.getTimestamp() );

        return nodeEntity.build();
    }

    protected TagDto tagEntityToTagDto(TagEntity tagEntity) {
        if ( tagEntity == null ) {
            return null;
        }

        TagDtoBuilder tagDto = TagDto.builder();

        tagDto.k( tagEntity.getK() );
        tagDto.v( tagEntity.getV() );

        return tagDto.build();
    }

    protected List<TagDto> tagEntityListToTagDtoList(List<TagEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<TagDto> list1 = new ArrayList<TagDto>( list.size() );
        for ( TagEntity tagEntity : list ) {
            list1.add( tagEntityToTagDto( tagEntity ) );
        }

        return list1;
    }

    protected TagEntity tagDtoToTagEntity(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        TagEntityBuilder tagEntity = TagEntity.builder();

        tagEntity.k( tagDto.getK() );
        tagEntity.v( tagDto.getV() );

        return tagEntity.build();
    }

    protected List<TagEntity> tagDtoListToTagEntityList(List<TagDto> list) {
        if ( list == null ) {
            return null;
        }

        List<TagEntity> list1 = new ArrayList<TagEntity>( list.size() );
        for ( TagDto tagDto : list ) {
            list1.add( tagDtoToTagEntity( tagDto ) );
        }

        return list1;
    }
}
