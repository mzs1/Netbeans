/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author MZS
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        int a=0;
//        int [] array=new int[5];
//        for(int i=0;i<5;i++){
//            array[i]=i*5;
//        }
//        for(int i=0;i<5;i++){
//            System.out.println(array[i]);
//        }
boolean [][]G = new boolean[12][12];
        G[0][1] = G[1][0] = true; // notice that for each edge G[i][j] == G[j][i]
        G[0][8] = G[8][0] = true; // means the graph is undirected
        G[0][4] = G[4][0] = true;
        G[1][2] = G[2][1] = true;
        G[1][3] = G[3][1] = true;
        G[2][6] = G[6][2] = true;
        G[3][4] = G[4][3] = true;
        G[3][5] = G[5][3] = true;
        G[6][7] = G[7][6] = true;
        G[8][9] = G[9][8] = true;
        G[9][10] = G[10][9] = true;
        G[10][11] = G[11][10] = true;
        for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                if(G[i][j]==true){
                 System.out.print(" |"+G[i][j]+" | ");

                }
                else
            System.out.print(" |"+G[i][j]+"| ");
        }
                     System.out.println();   
        }
    }
    
}
