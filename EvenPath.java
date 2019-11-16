package com.example.cp;

import java.util.Scanner;

/**
 * @author Akash Deep
 * @date 11/16/2019
 **/

// https://codeforces.com/problemset/problem/1252/C
public class EvenPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        int arr_size = Integer.valueOf(arr[0]);
        int test_count = Integer.valueOf(arr[1]);

        String[] row = sc.nextLine().split(" ");
        String[] col = sc.nextLine().split(" ");

        int[][] a = new int[arr_size+1][arr_size+1];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(i==0 && j==0)
                    continue;
                if(i==0 && j>0){
                    a[i][j] = Integer.valueOf(col[j-1]);
                }
                else if(i>0 && j==0){
                    a[i][j] = Integer.valueOf(row[i-1]);
                }
                else{
                    a[i][j] = Integer.valueOf(row[i-1]) + Integer.valueOf(col[j-1]);
                }
            }
        }
//        printArray(a);
//        System.out.println();

        // Running queries
        for(int i=0;i<test_count;i++){
            String[] arr1 = sc.nextLine().split(" ");
            int[] points = new int[arr1.length];
            for(int k=0;k<arr1.length;k++){
                points[k] = Integer.valueOf(arr1[k]);
            }
            boolean flag = evenPath(a, points);

            if(flag == true)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    static boolean evenPath(int[][] a, int[] points){
        for(int i=0;i<a.length;i++){
            int cell1_value = a[points[0]][points[1]];
            int cell2_value = a[points[2]][points[3]];

            if(cell1_value%2 != 0 || cell2_value%2 != 0)
                return false;

            boolean[][] visited = new boolean[a.length][a[0].length];
            return dfsUtil(visited, a, points[0], points[1], points);
        }
        return false;
    }

    static boolean dfsUtil(boolean[][] visited, int[][] a, int r, int c, int[] points){

        if(r < 0 || r > a.length-1)
            return false;
        if(c < 0 || c > a[0].length-1)
            return false;

        if(visited[r][c] == true)
            return false;

        //System.out.println("index: " + r + " " + c);
        visited[r][c] = true;

        if(a[r][c]%2 != 0)
            return false;
        // Destination reached
        if(r == points[2] && c == points[3])
            return true;

        // Visit all neighbours
        return dfsUtil(visited, a, r-1, c, points) ||
        dfsUtil(visited, a, r+1, c, points) ||
        dfsUtil(visited, a, r, c-1, points) ||
        dfsUtil(visited, a, r, c+1, points);
    }

//    static void printArray(int[][] a){
//        System.out.println();
//        for(int i=0;i<a.length;i++){
//            System.out.println();
//            for(int j=0;j<a[0].length;j++){
//                System.out.print(a[i][j] + " ");
//            }
//        }
//    }
}
