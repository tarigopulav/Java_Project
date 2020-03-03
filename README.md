# Java_Project
Graph Coloring Sudoku Project
Graph coloring problem is to assign colors to certain elements of a graph subject to certain constraints.
The basic idea of Greedy algorithm implemented for the graph is :
1. To Select the first vertex and check if a color was assigned.
a. If there is already a color, move to next adjacent vertex.
b. If not, Assign an available color.
2. For the remaining Adjacent vertices, check the colors and select the one that has not been assigned any color of them. If there is no available color, add new color.
