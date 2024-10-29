package cgame.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameGraph  {

    public interface INode {
        String getId();
        GameData getData();
    }

    private class Node implements INode {
        private String id;
        private GameData data;
        private Node(INode values){
            this.id = values.getId();
            this.data = values.getData();
        }

        public String getId() {
            return id;
        }

        public GameData getData(){
            return data;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + hashCode();
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (!equals(other))
                return false;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }     

    }

    private Map<Node,List<Node>> graph;

    public GameGraph() {
            graph = new HashMap<>();
    }

    public boolean hasNeiqhbours(INode node){
        return !this.graph.get(new Node(node)).isEmpty();
    }

    public boolean addNode(INode newNode){
       var node = this.graph.putIfAbsent(new Node(newNode), new ArrayList<Node>());
       return node != null;
    }

    public boolean removeNode(INode nodeValues){
        var node = new Node(nodeValues);
        this.graph.values().stream().forEach((edge) -> edge.remove(node));
        var removedNode = this.graph.remove(new Node(nodeValues));
        return removedNode != null;
    }

    public boolean addEdge(INode node1,INode node2){
        var startNode = new Node(node1);
        var endNode = new Node(node2);
        if(!this.graph.containsKey(startNode)) {
            this.graph.put(startNode, new ArrayList<Node>());
        }
        return this.graph.get(startNode).add(endNode);
    }

    public void removeEdge(INode node1,INode node2){
        var startNode = new Node(node1);
        var endNode = new Node(node2);
        List<Node> eV1 = graph.get(startNode);
        List<Node> eV2 = graph.get(endNode);
        if (eV1 != null)
            eV1.remove(startNode);
        if (eV2 != null)
            eV2.remove(endNode);
    }


    public List<Node> getNeighbours(INode node) {
        return this.graph.get(new Node(node));
    }

    public List<GameData> getNeigbourValues(INode node) {
        return this.graph.get(new Node(node)).stream().map(value -> value.getData()).toList();
    }

    public static INode CreateNode(GameData data,String id){
        return new INode() {

            @Override
            public String getId() {
                return id;
            }

            @Override
            public GameData getData() {
               return data;
            }
            
        };
    }

}
