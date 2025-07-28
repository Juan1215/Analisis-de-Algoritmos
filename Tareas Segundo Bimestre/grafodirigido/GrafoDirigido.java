/*
 * Proyecto: Algoritmo de Dijkstra en un Grafo Dirigido
 * Descripción: Calcula la distancia más corta desde un nodo origen hacia los demás nodos del grafo.
 */
package grafodirigido;

import java.util.*;

public class GrafoDirigido {

    // Representa "infinito", usado para inicializar distancias
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int n = 5; // Número de nodos
        int[][] graph = new int[n][n]; // Matriz de adyacencia

        // Inicializamos todas las distancias como INF
        for (int[] row : graph)
            Arrays.fill(row, INF);

        // Definimos las aristas y sus pesos
        graph[0][1] = 10;
        graph[0][3] = 30;
        graph[0][4] = 100;
        graph[1][2] = 50;
        graph[3][2] = 20;
        graph[3][4] = 60;
        graph[2][4] = 10;

        // Llamamos al algoritmo de Dijkstra desde el nodo 0 (nodo 1 real)
        dijkstra(graph, 0);
    }

    // Implementación del algoritmo de Dijkstra
    static void dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] dist = new int[n]; // distancias mínimas desde el nodo origen
        boolean[] visited = new boolean[n]; // nodos que ya fueron visitados

        Arrays.fill(dist, INF); // Todas las distancias son infinitas inicialmente
        dist[source] = 0; // La distancia desde el nodo origen hacia sí mismo es 0

        System.out.println("Nodo inicial: " + (source + 1));
        System.out.println("---------------------------");

        // Se repite n veces para asegurar visitar todos los nodos
        for (int step = 0; step < n; step++) {
            int u = minDistance(dist, visited); // Obtenemos el nodo con menor distancia no visitado

            // Si no hay más nodos alcanzables, se rompe el ciclo
            if (u == -1) break;

            visited[u] = true; // Marcamos el nodo como visitado
            System.out.println("Paso " + (step + 1) + ": Nodo seleccionado = " + (u + 1));
            System.out.println("Distancias actuales: " + distanciasAString(dist));
            System.out.println("Nodos visitados: " + nodosVisitadosString(visited));
            System.out.println();

            // Revisamos todos los vecinos del nodo u
            for (int v = 0; v < n; v++) {
                // Si no se ha visitado y existe arista entre u y v
                if (!visited[v] && graph[u][v] != INF) {
                    int nuevaDist = dist[u] + graph[u][v];
                    // Si encontramos un camino más corto, actualizamos la distancia
                    if (nuevaDist < dist[v]) {
                        dist[v] = nuevaDist;
                        System.out.println("Actualizando distancia de nodo " + (v + 1) + " a " + nuevaDist);
                    }
                }
            }
            System.out.println();
        }

        // Mostramos el resultado final con todas las distancias mínimas
        System.out.println("Resultado final:");
        for (int i = 0; i < n; i++) {
            System.out.println("Distancia desde nodo " + (source + 1) + " a nodo " + (i + 1) + ": " + (dist[i] == INF ? "∞" : dist[i]));
        }
    }

    // Función auxiliar para encontrar el nodo con menor distancia no visitado
    static int minDistance(int[] dist, boolean[] visited) {
        int min = INF, idx = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                idx = i;
            }
        }
        return idx;
    }

    // Devuelve un string con las distancias actuales en formato legible
    static String distanciasAString(int[] dist) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dist.length; i++) {
            sb.append("[").append(i + 1).append(": ").append(dist[i] == INF ? "INF" : dist[i]).append("] ");
        }
        return sb.toString();
    }

    // Devuelve un string con los nodos que ya han sido visitados
    static String nodosVisitadosString(boolean[] visited) {
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) lista.add(i + 1); // Se suma 1 para mostrar nodos desde 1, no desde 0
        }
        return lista.toString();
    }
}
