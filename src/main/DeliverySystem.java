package main;

import java.util.*;

public class DeliverySystem {
    private static Graph graph;
    private static AStarAlgorithm aStarAlgorithm;
    private static KruskalAlgorithm kruskalAlgorithm;

    public static void main(String[] args) {
        graph = new Graph();
        aStarAlgorithm = new AStarAlgorithm(graph);
        kruskalAlgorithm = new KruskalAlgorithm(graph);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addLocationOrRoad(scanner);
                    break;
                case 2:
                    viewCurrentNetwork();
                    break;
                case 3:
                    findShortestRoute(scanner);
                    break;
                case 4:
                    optimizeDeliveryNetwork();
                    break;
                case 5:
                    simulateTrafficUpdate(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Welcome to the Delivery Route Optimization System");
        System.out.println("1. Add Location or Road");
        System.out.println("2. View Current Road Network");
        System.out.println("3. Find Shortest Route");
        System.out.println("4. Optimize Delivery Network");
        System.out.println("5. Simulate Traffic Update");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addLocationOrRoad(Scanner scanner) {
        System.out.println("Enter 1 to add location, 2 to add road:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.print("Enter location name: ");
            String location = scanner.nextLine();
            graph.addNode(location);
            System.out.println("Location added: " + location);
        } else if (choice == 2) {
            System.out.print("Enter starting location, ending location, and distance: ");
            String from = scanner.next();
            String to = scanner.next();
            double distance = scanner.nextDouble();
            graph.addEdge(from, to, distance);
            System.out.println("Road added from " + from + " to " + to + " with distance " + distance);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void viewCurrentNetwork() {
        graph.displayGraph();
    }

    private static void findShortestRoute(Scanner scanner) {
        System.out.print("Enter start location: ");
        String start = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        
        // Example heuristic (straight-line distance in reality you would use actual distances)
        Map<String, Double> heuristic = new HashMap<>();
        heuristic.put(destination, 0.0);

        List<String> path = aStarAlgorithm.findShortestPath(start, destination, heuristic);
        System.out.println("Shortest Path: " + path);
    }

    private static void optimizeDeliveryNetwork() {
        List<Graph.Edge> mst = kruskalAlgorithm.optimizeNetwork();
        System.out.println("Optimized Delivery Network:");
        for (Graph.Edge edge : mst) {
            // Use the getter methods to access the fields
            System.out.println(edge.getFrom() + " - " + edge.getTo() + " (" + edge.getWeight() + ")");
        }
    }
    

    private static void simulateTrafficUpdate(Scanner scanner) {
        System.out.print("Enter road to update (from to): ");
        String from = scanner.next();
        String to = scanner.next();
        System.out.print("Enter new distance: ");
        double newDistance = scanner.nextDouble();
        graph.updateEdgeDistance(from, to, newDistance);
        System.out.println("Road distance updated.");
    }
}
