package pt.ipp.isep.dei.esoft.project.mdisc;

import java.util.*;


//Minimal Spanning Tree
public class MST {

    public Graph mstKruskal(Graph g) {

        List<Edge> allEdges = new ArrayList<>(g.getEdgeList());


        // Ordeno as arestas em ordem crescente de custo
        allEdges.sort(Comparator.comparing(Edge::getWeight));

        // Crio um novo grafo para armazenar a MST
        Graph mst = new Graph();

        // Mapeia cada vértice para a sua componente conectada
        // Inicialmente, cada vértice está na sua própria componente
        Map<String, String> componentMap = new HashMap<>();
        for (Vertex vertex : g.getVertexList()) {
            componentMap.put(vertex.getId(), vertex.getId());
        }

        // Adiciono as arestas à MST até que todos os vértices estejam conectados
        for (Edge edge : allEdges) {
            String source = edge.getOrigin();
            String destination = edge.getDestination();

            // Para garantir que faço uma estrutura aciclica garanto que não são formados ciclos
            if (!getComponent(componentMap, source).equals(getComponent(componentMap, destination))) {
                mst.addEdge(new Edge(source, destination, edge.getWeight()));

                // Atualizo a componente conectada para todos os vértices da componente do destino
                String destComponent = getComponent(componentMap, destination);
                for (Vertex vertex : g.getVertexList()) {
                    if (getComponent(componentMap, vertex.getId()).equals(destComponent)) {
                        componentMap.put(vertex.getId(), getComponent(componentMap, source));
                    }
                }
            }
        }

        return mst;
    }

    public String getComponent(Map<String, String> componentMap, String vertex) {
        if (componentMap.get(vertex).equals(vertex)) {
            return vertex;
        } else {
            return getComponent(componentMap, componentMap.get(vertex));
        }
    }


}
