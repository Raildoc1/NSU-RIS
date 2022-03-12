package database.loader;

import database.dao.NodeDao;
import database.dao.TagDao;

import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Tag;

import java.util.List;

public class NodeLoader implements IDataLoader<Node>{

    private NodeDao nodeDao;
    private TagDao tagDao;

    public NodeLoader(NodeDao nodeDao, TagDao tagDao) {
        this.nodeDao = nodeDao;
        this.tagDao = tagDao;
    }

    @Override
    public void load(List<Node> nodes) {
        nodeDao.insertNodes(nodes);
        for (Node node : nodes) {
            List<Tag> tags = node.getTag();
            tagDao.insertTags(tags);
            tagDao.insertTagsToNode(tags, node.getId());
        }
    }
}
