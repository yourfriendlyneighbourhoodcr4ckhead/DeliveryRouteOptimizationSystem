package main;

import java.io.*;
import java.util.*;
import org.json.*;
import java.nio.file.Files;
import java.nio.file.Paths;  // This is the missing import
import java.nio.charset.StandardCharsets;



public class Utils {

    // Method to read CSV file for locations (delivery points)
    public static List<String> readCSV(String filePath) {
        List<String> locations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                locations.add(line);  // Add each line (location) to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locations;
    }

    // Method to read the graph from a JSON file
    public static Graph readGraphFromJson(String filePath) {
        Graph graph = new Graph();
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonContent);
            Iterator<String> nodes = jsonObject.keys();
            while (nodes.hasNext()) {
                String node = nodes.next();
                JSONArray edges = jsonObject.getJSONArray(node);
                for (int i = 0; i < edges.length(); i++) {
                    JSONObject edge = edges.getJSONObject(i);
                    String toNode = edge.getString("to");
                    double weight = edge.getDouble("weight");
                    graph.addEdge(node, toNode, weight);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return graph;
    }

    // Method to save the graph to a JSON file
    public static void saveGraphToJson(Graph graph, String filePath) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, List<Graph.Edge>> entry : graph.getAdjacencyList().entrySet()) {
            JSONArray edges = new JSONArray();
            for (Graph.Edge edge : entry.getValue()) {
                JSONObject edgeObject = new JSONObject();
                edgeObject.put("to", edge.getTo());
                edgeObject.put("weight", edge.getWeight());
                edges.put(edgeObject);
            }
            jsonObject.put(entry.getKey(), edges);
        }
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
