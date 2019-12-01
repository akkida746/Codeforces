package com.example.cp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Akash Deep
 * @date 11/23/2019
 **/
// https://codeforces.com/contest/1254/problem/A
public class FeedingChicken {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        String symbols = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
        for(int i=0;i<tests+1;i++){
            String s = sc.nextLine();
            if(s.length() == 0)
                continue;
            String[] arr = s.split(" ");
            int row = Integer.valueOf(arr[0]);
            int col = Integer.valueOf(arr[1]);
            int chicken = Integer.valueOf(arr[2]);
            char[][] a = new char[row][col];
            int rice = 0;
            for(int r=0;r<row;r++){
                String str = sc.nextLine();
                for(int c=0;c<col;c++){
                    char ch = str.charAt(c);
                    if(ch == 'R')
                        ++rice;
                    a[r][c] = ch;
                }
            }
            int ricePerChicken = rice/chicken;
            int riceRemaining = rice%chicken;
            char[][] res = new char[row][col];
            int riceCount = riceRemaining;
            Cell cell = new Cell(0,0);
            for(int index=0;index<riceRemaining;index++){
                //System.out.println("Chicken Left = " + (riceRemaining-index));
                cell = print(a,res,ricePerChicken+1,symbols.charAt(index),cell.row,cell.col);
                if(cell.col == a[0].length){
                    cell.row = ++cell.row;
                    cell.col = 0;
                }
            }
            //System.out.println("**********************************************");
            cell = findSpace(res);
            char ch = 0;
            for(int index=riceRemaining;index<chicken;index++){
                //System.out.println("Chicken Left = " + (chicken-index));
                ch = symbols.charAt(index);
                cell = print(a,res,ricePerChicken,symbols.charAt(index),cell.row, cell.col);
                if(cell.col >= a[0].length-1){
                    cell.row = ++cell.row;
                    cell.col = 0;
                }
            }
            fillSpace(res,ch,0,0);
            printArray(res);
        }
    }

    static Cell print(char[][] a, char[][] res, int ricePerChicken, char c, int row, int col){
        for(int i=row;i<a.length;i++){
            for(int j=col;j<a[0].length;j++){
                if(a[i][j] == 'R' && res[i][j] == 0){
                    --ricePerChicken;
                    res[i][j] = c;
                    Queue<Cell> queue = new LinkedList<>();
                    queue.add(new Cell(i,j));
                    while(!queue.isEmpty()){
                        Cell cell = queue.poll();
                        if(cell.row<0 || cell.row>a.length-1|| cell.col <0 || cell.col>a[0].length-1)
                            continue;
                        if(a[cell.row][cell.col] == 'R' && res[cell.row][cell.col] == 0){
                            --ricePerChicken;
                            res[cell.row][cell.col] = c;
                        }
                        else if(a[cell.row][cell.col] != 'R')
                            res[cell.row][cell.col] = c;

                        if(ricePerChicken <= 0)
                            return new Cell(i,j);

                        queue.add(new Cell(cell.row,cell.col-1));
                        queue.add(new Cell(cell.row,cell.col+1));
                        queue.add(new Cell(cell.row+1,cell.col));
                        queue.add(new Cell(cell.row-1,cell.col));
                        //printArray(res);
                    }
                }
                else if(a[i][j] != 'R')
                    res[i][j] = c;
            }
        }
        return new Cell(a.length,a[0].length);
    }

    static void fillSpace(char[][] res, char ch, int row, int col){
        for(int i=row;i<res.length;i++){
            for(int j=0;j<res[0].length;j++){
                if(res[i][j] == 0)
                    res[i][j] = ch;
            }
        }
    }

    static Cell findSpace(char[][] res){
        for(int i=0;i<res.length;i++){
            for(int j=0;j<res[0].length;j++){
                if(res[i][j] == 0)
                    return new Cell(i,j);
            }
        }
        return null;
    }

    static class Cell{
        int row, col;
        public Cell(int row, int col){
            this.row = row;
            this.col =col;
        }
    }

    static void printArray(char[][] a){
        for(int i=0;i<a.length;i++){
            System.out.println();
            for(int j=0;j<a[0].length;j++){
                System.out.print(a[i][j]);
            }
        }
    }
}
