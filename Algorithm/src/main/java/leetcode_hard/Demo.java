package leetcode_hard;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static long maximumProfit(List<Integer> price) {
        // Write your code here
        int n=price.size();
        int sum=0;
        // int leftIncre[]=new int[n];
        int rightIncre[]=new int[n];

        // leftIncre[n-1]=price.get(0);
        // for(int i=1;i<n;i++){
        //     leftIncre[i]=Math.max(leftIncre[i-1], leftIncre[i]);
        //     System.out.println(leftIncre[i]);
        // }
        rightIncre[n-1]=price.get(n-1);
        int max=0;

        for(int i=n-1;i>=0;i--){
            max=Math.max(max, price.get(i));
            rightIncre[i]=max;
            System.out.println("Max "+rightIncre[i]);
        }
        long rs=0;
        int index=0;

        for(int i=0;i<n;i++){
            if(price.get(i)==rightIncre[i]){
                rs+=price.get(i)*(i-index)-sum;
                sum=0;
                index=i+1;
                continue;
            }
            sum+=price.get(i);
        }
        //      3,5,4,(6),2,(3),2
        //left: 3,5,5,6,6,6,6
        //right: 6,6,6,(6),3,(3),2
        return rs;
    }

    public static void main(String[] args) {
        List<Integer> price=new ArrayList<>();
//        price.add(3);
//        price.add(4);
//        price.add(5);
//        price.add(3);
//        price.add(5);
//        price.add(2);

        price.add(1);
        price.add(2);
        price.add(100);
        //0
        //197
        //3
        System.out.println(maximumProfit(price));
    }
}
