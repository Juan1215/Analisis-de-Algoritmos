/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication32;
import java.util.*;

/*
 * Proyecto: Árbol de Recubrimiento Mínimo usando el Algoritmo de Kruskal
 * Descripción: Este programa encuentra el ARM de un grafo no dirigido,
 * evitando ciclos mediante el uso de estructuras de conjuntos disjuntos (Union-Find).
 */

// Clase para representar una arista del grafo
class Arista implements Comparable<Arista> {
    int desde, hasta, peso;

    // Constructor que inicializa los valores de la arista
    public Arista(int desde, int hasta, int peso) {
        this.desde = desde;
        this.hasta = hasta;
        this.peso = peso;
    }

    // Método para poder ordenar las aristas por su peso (de menor a mayor)
    @Override
    public int compareTo(Arista otra) {
        return this.peso - otra.peso;
    }
}

// Estructura de datos Union-Find para detectar ciclos
class UnionFind {
    int[] padre;

    // Inicializa cada nodo como su propio "padre"
    public UnionFind(int n) {
        padre = new int[n];
        for (int i = 0; i < n; i++) {
            padre[i] = i;
        }
    }

    // Encuentra la raíz del conjunto al que pertenece x
    public int encontrar(int x) {
        if (padre[x] != x)
            padre[x] = encontrar(padre[x]); // Compresión de caminos
        return padre[x];
    }

    // Une los conjuntos de x e y
    public void unir(int x, int y) {
        int raizX = encontrar(x);
        int raizY = encontrar(y);
        padre[raizX] = raizY;
    }
}

// Clase principal que ejecuta el algoritmo de Kruskal
public class Kruskal {
    public static void main(String[] args) {
        // Número de vértices del grafo
        int numVertices = 6;

        // Lista de todas las aristas del grafo (grafo no dirigido)
        List<Arista> aristas = new ArrayList<>();
        aristas.add(new Arista(0, 1, 4));
        aristas.add(new Arista(0, 2, 4));
        aristas.add(new Arista(1, 2, 2));
        aristas.add(new Arista(1, 3, 5));
        aristas.add(new Arista(2, 3, 8));
        aristas.add(new Arista(2, 4, 10));
        aristas.add(new Arista(3, 4, 2));
        aristas.add(new Arista(3, 5, 6));
        aristas.add(new Arista(4, 5, 5));

        // Ordenamos las aristas por peso (menor primero)
        Collections.sort(aristas);

        // Inicializamos la estructura Union-Find
        UnionFind uf = new UnionFind(numVertices);

        // Lista para guardar el resultado del ARM
        List<Arista> resultado = new ArrayList<>();
        int totalPeso = 0;

        // Inicia la ejecución del algoritmo paso a paso
        System.out.println("PASOS DEL ALGORITMO DE KRUSKAL:");

        // Recorremos todas las aristas en orden de menor a mayor peso
        for (int i = 0; i < aristas.size(); i++) {
            Arista arista = aristas.get(i);
            int raizDesde = uf.encontrar(arista.desde);
            int raizHasta = uf.encontrar(arista.hasta);

            // Mostramos qué arista se está evaluando
            System.out.printf("Evaluando arista (%d - %d) con peso %d: ", arista.desde, arista.hasta, arista.peso);

            // Si los nodos pertenecen a diferentes conjuntos, se puede agregar sin formar ciclo
            if (raizDesde != raizHasta) {
                uf.unir(raizDesde, raizHasta); // Unimos los conjuntos
                resultado.add(arista);         // Añadimos al árbol
                totalPeso += arista.peso;      // Sumamos al peso total
                System.out.println(" Se añade al arbol.");
            } else {
                // Si ya están conectados, formarían un ciclo, así que se descarta
                System.out.println(" Forma un ciclo. Se descarta.");
            }
        }

        // Mostramos el árbol final resultante
        System.out.println("\n--- Arbol de Recubrimiento Minimo ---");
        for (Arista arista : resultado) {
            System.out.printf("(%d - %d) peso: %d\n", arista.desde, arista.hasta, arista.peso);
        }

        // Mostramos el peso total del ARM
        System.out.println("Peso total del ARM: " + totalPeso);
    }
}



  
