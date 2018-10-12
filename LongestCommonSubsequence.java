/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic;
import java.util.*;
/**
 *
 * @author Gaurav Joshi
 */
public class LongestCommonSubsequence {
    
    public static void main(String args[]) {
        
        String str1, str2;
        Scanner scan = new Scanner(System.in);
        
        //System.out.println("Enter the 2 strings");
        //str1 = scan.next();
        //str2 = scan.next();
        
        str1 = "president";
        str2 = "providence";
        lcs(str1, str2);
        updated_lcs(str1, str2);
        
    }
    
    //T(n)=O(mn) and S(n)=O(mn)
    public static void lcs(String str1, String str2) {
    
        int L[][] = new int[str1.length()][str2.length()];
        int i=0 ; int j=0;
        
        //Initialize the first row and first column of the Matrix
        //First Row
        while(j<str2.length()) {
            if(str1.charAt(i) == str2.charAt(j)) {
                
                while(j<str2.length()) {
                    L[0][j] = 1;
                    j++;
                }
                break;
            }
            j++;
        }
        
        //First Column
        j=0;
        while(j<str1.length()) {
            
            if(str2.charAt(i) == str1.charAt(j)) {
                
                while(j<str1.length()) {
                    L[j][0] = 1;
                    j++;
                }
                break;
            }
            j++;
        }
        
        //Filling out the rest of Table
        for(i=1; i<str1.length(); i++) {
 
            for(j=1; j<str2.length(); j++) {
            
                if(str1.charAt(i) == str2.charAt(j)) 
                    L[i][j] = 1 + L[i-1][j-1];
                else 
                    L[i][j] = max(L[i-1][j], L[i][j-1]);
                
            }
        }
        
        displayMatrix(L);
        System.out.println();
        System.out.println("Length of LCS is " + L[str1.length()-1][str2.length()-1]);
        backtracking(L, str1, str2);
    }
    
    //Does not take the entire 2D matrix
    //Can't be used to show what the subsequence is
    //T=O(mn) and S(n)=O(n)
    public static void updated_lcs(String str1, String str2) {
    
        int L[][] = new int[2][str2.length()];
        
        int i=0,j=0;
        
        //Initialize the First Row of L Matrix
        while(j<str2.length()) {
            if(str1.charAt(i) == str2.charAt(j)) {
                
                while(j<str2.length()) {
                    L[0][j] = 1;
                    j++;
                }
                break;
            }
            j++;
        }
        
        //Initilaizing the first entry in the second row
        if(str2.charAt(0) == str1.charAt(1)) {
            L[1][0]=1;
        }
            
        //Filling out the rest of Table
        for(i=1; i<str1.length(); i++) {
 
            for(j=1; j<str2.length(); j++) {
            
                if(str1.charAt(i) == str2.charAt(j)) {
                    L[1][j] = 1 + L[0][j-1];
                } else {
                    L[1][j] = max(L[0][j], L[1][j-1]);
                }
                    
            }
            
            if(i == str1.length()-1)
                break;
            
            //Assigning the values of row1 to row0
            for(int k=0; k<str2.length(); k++) {
                L[0][k] = L[1][k];
            }
            
            //Initilaizing the first entry in the second row
            if(str2.charAt(0) == str1.charAt(i+1)) {
                L[1][0]=1;
            }
            
        }
        for(i=0; i<L[1].length; i++)
            System.out.print(L[1][i] + " ");
        
        System.out.println();
        System.out.println("The length of LCS is " + L[1][str2.length()-1]);
        
    }
    
    //T(n)=O(m+n) and S(n)=O(mn)
    public static void backtracking(int L[][], String str1, String str2) {
    
        ArrayList<Character> str = new ArrayList<Character>();
        int i = str1.length()-1;
        int j = str2.length()-1;
        
        while(i>0 && j>0) {
            if(str1.charAt(i)==str2.charAt(j)) {
                
                str.add(str1.charAt(i));
                i = i-1;
                j = j-1;
            } else {
                if(L[i-1][j] < L[i][j-1])
                   j--;
                else
                   i--;
                
            }
        }
        
        //Checking for 1s outside loop
        if(i==0) {
            while(j>=0 && L[i][j]==1) {
                str.add(str2.charAt(j));
                j--;
            }
        } else {
            while(i>=0 && L[i][j]==1) {
                str.add(str2.charAt(i));
                i--;
            }
        }
        
        //Printing string in reverse order
        i = str.size()-1;
        System.out.print("The LCS is ");
        while(i>=0) {
            System.out.print(str.get(i));
            i--;
        }
        System.out.println("\n");
        
    }
    
    //Displays the L-Matrix content
    public static void displayMatrix(int L[][]) {
        
        for(int i=0; i<L.length; i++) {
            
            for(int j=0; j<L[0].length; j++) {
                
                System.out.print(L[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    
    //Gives max of two numbers
    public static int max(int a, int b) {
        
        return (a>=b?a:b);
    }
    
    //Gives minimum of two numbers
    public static int min(int a, int b) {
        return (a<=b?a:b);
    }
    
}
