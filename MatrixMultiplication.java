package dynamic;
import static dynamic.LongestCommonSubsequence.*;
import java.util.*;

/**
 *
 * @author Gaurav Joshi
 */
public class MatrixMultiplication {
    
    public static void main(String args[]) {
        
        //Given order of various matrices
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(1);
        A.add(10);
        A.add(5);
        A.add(2);
        A.add(25);
        A.add(4);
        calculate_computations(A);
    }
    
    //Computes the entire table
    //The table stores minimum number of computations needed to multiply matrices in some range
    //T(n)=O(n^3) and S(n)=O(n^2)
    public static void calculate_computations(ArrayList<Integer> A) {
        
        int L[][] = new int[A.size()][A.size()];
        
        //Initializing the primary and the secondary diagnol
        for(int i=1; i<A.size(); i++) {
            L[i][i] = 0;
            if(i != A.size()-1) {
                L[i][i+1] = A.get(i-1) * A.get(i) * A.get(i+1);
                L[i+1][i] = i;
            }
        }
        
        //Filling the rest of the table
        //Remember we are filling the table diagnolly
        for(int l=3; l<A.size(); l++) {
            
            for(int i=1; i<=A.size()-l; i++) {
                
                int j=i+l-1;
                
                L[i][j] = L[i][j-1] + (A.get(i-1)*A.get(j-1)*A.get(j));
                L[j][i] = j-1;
                for(int k=i; k<j-1; k++) {
                    
                    int temp = L[i][k] + L[k+1][j] + (A.get(i-1)*A.get(k)*A.get(j));
                    if(L[i][j] > temp) {
                        L[i][j] = temp;
                        L[j][i] = k;
                    }
                }
            }
        }
        
        displayMatrix(L);
        System.out.println("\nThe minimum number of matrix multiplications needed are " + L[1][A.size()-1]);
        System.out.print("\nThe values of k should be as follows : ");
        matrix_backtracking(L, 1, L[0].length-1);
        System.out.println();
        
    }
    
    //Backtracking through the values of k
    //Gives what values of k should be picked
    //T(n)=O(n) and S(n)=O(n^2)
    public static void matrix_backtracking(int L[][], int i, int j) {
        
        if(j>0 && i!=j) {
            
            int k = L[j][i]; 
            System.out.print(k + " ");
            matrix_backtracking(L, i, k);
            matrix_backtracking(L, k+1, j);
        }
        
    }
}
