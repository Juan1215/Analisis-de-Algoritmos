/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package orden;

import java.util.Arrays;

/**
 *
 * @author HP
 */
public class Orden {

    public static void main(String[] args) {
        int[] array = {4, 8, 6, 2, 5, 7, 1};

        int p = 0;
        int q = ((array.length - 1) / 2) - 1;
        int r = array.length - 1;

        merge(array, p, q, r);

        // Mostrar el resultado
        System.out.println(Arrays.toString(array));
    }

    public static void merge(int[] A, int p, int q, int r) {
        int[] L = Arrays.copyOfRange(A, p, q + 1);
        int[] R = Arrays.copyOfRange(A, q + 1, r + 1);

        int i = 0, j = 0, k = p;

        while (i < L.length && j < R.length) {
            A[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }

        while (i < L.length) {
            A[k++] = L[i++];
        }

        while (j < R.length) {
            A[k++] = R[j++];
        }
    }
}
    
