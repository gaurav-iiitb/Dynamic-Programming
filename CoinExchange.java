/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic;
import static dynamic.LongestCommonSubsequence.*;
import java.util.*;
/**
 *
 * @author Gaurav Joshi
 */
public class CoinExchange {
    
    public static void main(String args[]) {
        
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> denomination = new ArrayList<Integer>();
        denomination.add(1);
        denomination.add(7);
        denomination.add(10);
        int w = 15;
        //min_coin(denomination, w);
        updated_min_coin(denomination, w);
    }
    
    
    //T(n)=O(nw) and S(n)=O(nw)
    public static void min_coin(ArrayList<Integer> deno, int w) {
    
        int L[][] = new int[w+1][deno.size()];
        
        //Initializing the firt column
        for(int i=0; i<w+1; i++) {
            L[i][0] = i;
        }
        //Initializing the first row
        for(int i=0; i<deno.size(); i++) {
            L[0][i] = 0;
            L[1][i] = 1;
        }
        
        //Filling the rest of the table
        for(int i=1; i<w+1; i++) {
            
            for(int j=1; j<deno.size(); j++) {
                
                    
                if(i>=deno.get(j))
                    L[i][j] = min(L[i][j-1], (1 + L[i-deno.get(j)][j]));
                else 
                    L[i][j] = L[i][j-1];
                
            }
            
        }
        
        displayMatrix(L);
        System.out.println();
        System.out.println("Minimum number of coins needed are " + L[w][deno.size()-1]);
        System.out.println();
        backtracking(L, w, deno);
        
    }
    
    //T(n)=O(nw) and S(n)=O(w)
    public static void updated_min_coin(ArrayList<Integer> deno, int w) {
        
        int L[] = new int[w+1];
        
        //Initilaing for the first time
        for(int i=0; i<=w; i++) {
            L[i] = i;
        }
        
        //Filling the rst counterparts one column at a time
        for(int i=1; i<=w; i++) {
            
            for(int j=1; j<deno.size(); j++) {
                
                if(i>=deno.get(j)) {
                    if(L[i] > (1 + L[i-deno.get(j)])) {
                        L[i] = 1 + L[i-deno.get(j)];
                    }
                }
            }
        }
        
        //Displaying the content of L
        for(int i=0; i<=w; i++) {
            System.out.println(L[i]);
        }
        
        System.out.println("\nThe min coins needed are " + L[w]);
        updated_backtracking(L, deno);
        
    }

    //T(n)=O(n+w) and S(n)=O(nw)
    public static void backtracking(int L[][], int w, ArrayList<Integer> deno) {
        
        int i = w;
        int j = L[0].length - 1;
        int sum = 0;
        System.out.print("The coins used are : ");
        while(j>0) {
            
            if(i>=deno.get(j)) {
                
                if(L[i][j-1] > (1 + L[i-deno.get(j)][j])) {
                    System.out.print(deno.get(j) + " ");
                    i = i - deno.get(j);
                    sum += deno.get(j);
                } else {
                
                    j--;
                }
            } else {
                j--;
            }
            
        }
        
        //Remaining amount would be paid by series of ones
        w -= sum;
        while(w>0) {
            System.out.print(deno.get(0) + " ");
            w--;
        }
        System.out.println();
        
    }
    
    //T(n)=O(w+n) and S(n)=O(w)
    public static void updated_backtracking(int L[], ArrayList<Integer> deno) {
        
        int i = L.length-1;
        int j = deno.size()-1;
        System.out.print("\nThe coins used are : ");
        while(i>0) {
            
            if(i>=deno.get(j)) {
                
                if(L[i]>=(1+L[i-deno.get(j)])) {
                    System.out.print(deno.get(j) + " ");
                    i = i-deno.get(j);
                } else {
                    j--;
                }
                
            } else {
                j--;
            }
        }
        System.out.println();
    }
}
