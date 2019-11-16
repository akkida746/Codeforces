package com.example.cp;

import java.util.HashMap;
import java.util.Map;
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

        String[] rArr = new String[row.length+1];
        String[] cArr = new String[row.length+1];

        rArr[0] = "-1";
        cArr[0] = "-1";
        for(int i=1;i<rArr.length;i++){
            rArr[i] = row[i-1];
            cArr[i] = col[i-1];
        }

        for(int i=0;i<test_count;i++){
            String[] points = sc.nextLine().split(" ");
            boolean flag = evenPath(rArr, cArr, points);

            if(flag == true)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    static boolean evenPath(String[] row, String[] col, String[] points){
        for(int i=0;i<row.length;i++){
            Map<String,Boolean> map = new HashMap<>();
            return dfsUtil(map, row, col, points[0], points[1], points);
        }
        return false;
    }

    static boolean dfsUtil(Map<String,Boolean> map, String[] row, String[] col, String r, String c, String[] points){

        int rr = Integer.valueOf(r);
        int cc = Integer.valueOf(c);

        if(rr < 0 || rr > row.length-1)
            return false;
        if(cc < 0 || cc > col.length-1)
            return false;

        String key = r + c;
        if(map.containsKey(key)){
            if(map.get(key) == true)
                return true;
            return false;
        }
        map.put(key,false);

        int value = Integer.valueOf(row[rr]) + Integer.valueOf(col[cc]);

        if(value%2 != 0)
            return false;
        // Destination reached
        if(r.equals(points[2]) && c.equals(points[3])){
            map.put(key,true);
            return true;
        }

        return dfsUtil(map, row, col, String.valueOf(rr-1), c, points) ||
                dfsUtil(map, row, col,String.valueOf(rr+1), c, points) ||
                dfsUtil(map, row, col,r, String.valueOf(cc-1), points) ||
                dfsUtil(map, row, col,r, String.valueOf(cc+1), points);
    }

}
