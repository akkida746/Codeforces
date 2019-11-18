package com.example.cp;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Akash Deep
 * @date 11/18/2019
 **/

// https://codeforces.com/contest/1144/problem/B
public class ParityAlternatedDeletions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Comparator<Integer> comparator = (Integer i1, Integer i2) -> i2 - i1;
        PriorityQueue<Integer> evenQueue = new PriorityQueue<>(comparator);
        PriorityQueue<Integer> oddQueue = new PriorityQueue<>(comparator);
        int value = 0;

        for(int i=0;i<n;i++){
            value = sc.nextInt();
            if(value %2 == 0)
                evenQueue.add(value);
            else
                oddQueue.add(value);
        }

        while(!evenQueue.isEmpty() && !oddQueue.isEmpty()){
            evenQueue.poll();
            oddQueue.poll();
        }

        int evenQueueSize = evenQueue.size();
        int oddQueueSize = oddQueue.size();

        if(evenQueueSize > 0 && evenQueueSize > oddQueueSize)
            evenQueue.poll();
        else if(oddQueueSize > 0 && oddQueueSize > evenQueueSize)
            oddQueue.poll();
        else if(evenQueueSize > 0 && oddQueueSize > 0){
            if(evenQueue.peek() > oddQueue.peek())
                evenQueue.poll();
            else
                oddQueue.poll();
        }

        evenQueueSize = evenQueue.size();
        oddQueueSize = oddQueue.size();

        int sum = 0;
        while(!evenQueue.isEmpty() || !oddQueue.isEmpty()){
            if(!evenQueue.isEmpty())
                sum += evenQueue.poll();
            if(!oddQueue.isEmpty())
                sum += oddQueue.poll();
        }

        System.out.println(sum);
    }
}
