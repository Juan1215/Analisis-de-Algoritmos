/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sucesionfibonacci;

/**
 *
 * @author HP
 */
public class SucesionFibonacci {

    
    public static void main(String[] args) {
        int n = 10; // Número de términos a mostrar
        System.out.println("Sucesión de Fibonacci hasta " + n + " términos:");
        
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    // Método recursivo para obtener el enésimo término de Fibonacci
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}

