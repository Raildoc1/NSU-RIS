package nsu.gaiduk.controllers;

import lombok.RequiredArgsConstructor;
import nsu.gaiduk.database.dto.NodeDto;
import nsu.gaiduk.database.dto.RelationDto;
import nsu.gaiduk.database.dto.TagDto;
import nsu.gaiduk.database.dto.WayDto;
import nsu.gaiduk.database.entities.NodeEntity;
import nsu.gaiduk.services.NodeService;
import nsu.gaiduk.services.RelationService;
import nsu.gaiduk.services.TagService;
import nsu.gaiduk.services.WayService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final TagService tagService;
    private final RelationService relationService;
    private final WayService wayService;
    private final NodeService nodeService;

//    @RequestMapping("/")
//    public String home(){
//        return "Hello World!";
//    }

    @PostMapping(value = "/tag")
    public TagDto createTag(@RequestBody TagDto tagDto) {
        return tagService.create(tagDto);
    }

    @GetMapping(value = "/tag/{id}")
    public TagDto getTag(@PathVariable String id) {
        return tagService.get(id);
    }

    @PutMapping(value = "/tag")
    public TagDto updateTag(@RequestBody TagDto tagDto) {
        return tagService.update(tagDto);
    }

    @DeleteMapping(value = "/tag/{id}")
    public void deleteTag(@PathVariable String id) {
        tagService.delete(id);
    }

    @PostMapping(value = "/relation")
    public RelationDto createRelation(@RequestBody RelationDto relationDto) {
        return relationService.create(relationDto);
    }

    @GetMapping(value = "/relation/{id}")
    public RelationDto getRelation(@PathVariable BigInteger id) {
        return relationService.get(id);
    }

    @PutMapping(value = "/relation")
    public RelationDto updateRelation(@RequestBody RelationDto relationDto) {
        return relationService.update(relationDto);
    }

    @DeleteMapping(value = "/relation/{id}")
    public void deleteRelation(@PathVariable BigInteger id) {
        relationService.delete(id);
    }

    @PostMapping(value = "/way")
    public WayDto createWay(@RequestBody WayDto wayDto) {
        return wayService.create(wayDto);
    }

    @GetMapping(value = "/way/{id}")
    public WayDto getWay(@PathVariable BigInteger id) {
        return wayService.get(id);
    }

    @PutMapping(value = "/way")
    public WayDto updateWay(@RequestBody WayDto wayDto) {
        return wayService.update(wayDto);
    }

    @DeleteMapping(value = "/way/{id}")
    public void deleteWay(@PathVariable BigInteger id) {
        wayService.delete(id);
    }

    @PostMapping(value = "/node")
    public NodeDto createNode(@RequestBody NodeDto nodeDto) {
        return nodeService.create(nodeDto);
    }

    @GetMapping(value = "/node/{id}")
    public NodeDto getNode(@PathVariable BigInteger id) {
        return nodeService.get(id);
    }

    @GetMapping(value = "/node/radius")
    public List<NodeEntity> getNode(@RequestParam("r") Double radius, @RequestParam("lon") Double lon, @RequestParam("lat") Double lat) {
        return nodeService.findInRadius(lat, lon, radius);
    }

    @PutMapping(value = "/node")
    public NodeDto updateNode(@RequestBody NodeDto nodeDto) {
        return nodeService.update(nodeDto);
    }

    @DeleteMapping(value = "/node/{id}")
    public void deleteNode(@PathVariable BigInteger id) {
        nodeService.delete(id);
    }
}

