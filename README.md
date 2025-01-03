# Delivery Route Optimization System

## Overview

The **Delivery Route Optimization System** is a command-line application designed to optimize delivery routes using graph algorithms. This system implements **Kruskal's Algorithm** to find the **Minimum Spanning Tree (MST)** for optimizing delivery networks. It supports dynamic updates, user interaction, and efficient route management.

## Features

- **Graph Representation**: Delivery locations and routes are modeled as a graph, with locations as nodes and routes as edges.
- **Kruskal's Algorithm**: Finds the MST for optimized delivery paths.
- **Union-Find Structure**: Used to detect cycles and ensure efficient edge additions to the MST.
- **Dynamic Updates**: The system allows adding new delivery locations and routes dynamically.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your system.
- Command-line interface (CLI) for running the program.

### Setup

1. Clone the repository or download the source code.
2. Navigate to the project directory in your terminal.

### Compiling and Running

To compile the Java files:

```bash
javac *.java
```

To run the application:

```bash
java DeliverySystem
```

### Example

1. Add new locations and delivery routes.
2. Use the system to optimize the delivery route using Kruskal's algorithm.

## File Structure

- **Graph.java**: Defines the graph structure with nodes (locations) and edges (routes).
- **KruskalAlgorithm.java**: Implements Kruskal's algorithm to find the MST for optimized delivery routes.
- **DeliverySystem.java**: The main class to run the system and interact with the user.
