package nsu.gaiduk.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.gaiduk.database.entities.NodeEntity;
import nsu.gaiduk.database.repositories.NodeRepository;
import nsu.gaiduk.database.repositories.TagRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NodeLoaderService {
    private final NodeRepository nodeRepository;
    private final TagRepository tagRepository;

    public void loadToDatabase(NodeEntity nodeEntity) {
        tagRepository.saveAll(nodeEntity.getTags());
        nodeRepository.save(nodeEntity);
    }
}
