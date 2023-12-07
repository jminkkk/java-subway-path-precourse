package subway.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.SectionInfo;
import subway.domain.SectionInfoRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class PathController {
    private final SectionInfoRepository sectionInfoRepository;
    private final StationRepository stationRepository;

    public PathController(SectionInfoRepository sectionInfoRepository,
                          StationRepository stationRepository) {
        this.stationRepository = stationRepository;
        this.sectionInfoRepository = sectionInfoRepository;
    }

    public DijkstraShortestPath getGraph(String pathType) {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        stationRepository.stations().forEach(station -> graph.addVertex(station));

        sectionInfoRepository.sectionInfos()
                .forEach(sectionInfo ->
                        graph.setEdgeWeight(
                                graph.addEdge(
                                        sectionInfo.getSection().getStartStation(),
                                        sectionInfo.getSection().getEndStation()),
                                sectionInfo.getWeight(pathType)));

        return new DijkstraShortestPath(graph);
    }

    public List<Station> getMinimumPath(String pathType, String start, String end) {
        Station startStation = stationRepository.findByName(start);
        Station endStation = stationRepository.findByName(end);

        return getGraph(pathType).getPath(startStation, endStation).getVertexList();
    }

    public String getTotalValueByType(List<Station> shortestPaths, String type) {
        int totalDistance = 0;

        for (int i = 0; i < shortestPaths.size() - 1; i++) {
            Station start = shortestPaths.get(i);
            Station end = shortestPaths.get(i + 1);

            List<SectionInfo> sectionInfos = sectionInfoRepository.sectionInfos().stream()
                    .filter(sectionInfo -> sectionInfo.getSection().isSameSection(start, end))
                    .collect(Collectors.toList());

            totalDistance += sectionInfos.stream()
                    .min(Comparator.comparing(sectionInfo -> sectionInfo.getValue(type)))
                    .orElseThrow()
                    .getValue(type);
        }

        return String.valueOf(totalDistance);
    }
}
