package dynamic;
import static dynamic.LongestCommonSubsequence.displayMatrix;
import java.util.*;
/**
 *
 * @author Gaurav Joshi
 */
public class RodCutting {
    
    public static void main(String args[]) {
        
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(0);
        A.add(75);
        A.add(90);
        A.add(95);
        A.add(100);
        apt_cut(A);
    }
    
    //T(n)=O(n^3) and S(n)=O(n^2)
    public static void apt_cut(ArrayList<Integer> A) {
        
        int n = A.size();
        int L[][] = new int[n][n];
        int minimum = 0;
        int min_index = 0;
        
        //Initializing the leading diagnol
        //And the diagnol to the immediate right of the leading diagnol
        for(int i=0; i<n; i++){
            L[i][i] = -1;
            if(i != n-1)
                L[i][i+1] = 0;
        }
        
        //Initialize the minimum variable
        for(int i=0; i<n; i++){
            minimum += A.get(i);
        }
        int max = minimum;
        
        //Filling the rest of the table
        for(int h=2; h<n; h++) {
            
            int i=0;
            for(int j=h; j<n; j++) {

                if(j-i>2) {
                    minimum = max;
                    for(int k=i+1; k<j ; k++) {
                        int temp = L[i][k] + L[k][j];
                        if(temp<minimum) {
                            minimum = temp;
                            min_index = k;
                        }
                    }
                    L[i][j] = A.get(j) - A.get(i) + minimum;
                    L[j][i] = min_index;
                } else {
                    L[i][j] = A.get(j) - A.get(i);
                    L[j][i] = i+1;
                }
                i++;
            }
            
        }
        
        displayMatrix(L);
        System.out.println("The minimum cost of rod cutting is " + L[0][n-1]);
        
        System.out.print("The cuts have to be made at ");
        backtracking(A, L, 0, L.length-1);
        System.out.println("\n");
    }
    
    //T(n)=O(n) and S(n)=O(n^2)
    public static void backtracking(ArrayList<Integer> A, int L[][], int i, int j) {
        
        if(L[j][i]!=0)
            System.out.print(A.get(L[j][i]) + " ");
        if(j-i>1) {
            int k = L[j][i];
            backtracking(A, L, i, k);
            backtracking(A, L, k, j);
        }
    }
}