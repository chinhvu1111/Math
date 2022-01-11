/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 *
 * @author chinhvu
 */
public class Ugly_Number_13 {

//    public static int nthUglyNumber(int n) {
//        int indexNumberTwo=0;
//        int indexNumberThree=0;
//        int indexNumberFive=0;
//        int currentNumber=0;
//        int before=0;
//        
//        for(int i=0;i<n;i++){
//            currentNumber=(int) (Math.pow(2, indexNumberTwo)
//                    *Math.pow(3, indexNumberThree)
//                    *Math.pow(5, indexNumberFive));
//            double min=Math.min(Math.pow(2, indexNumberTwo+1), 
//                    Math.min(Math.pow(3, indexNumberThree+1), 
//                            Math.pow(5, indexNumberFive+1)));
//            if(min%2==0){
//                if(before==3){
//                    indexNumberThree--;
//                }
//                if(before==5){
//                    indexNumberFive--;
//                }
//                indexNumberTwo++;
//                before=2;
//            }
//            if(min%3==0){
//                if(before==2){
//                    indexNumberTwo--;
//                }
//                if(before==5){
//                    indexNumberFive--;
//                }
//                indexNumberThree++;
//                before=3;
//            }
//            if(min%5==0){
//                if(before==3){
//                    indexNumberThree--;
//                }
//                if(before==2){
//                    indexNumberTwo--;
//                }
//                indexNumberFive++;
//                before=5;
//            }
//        }
//        return currentNumber;
//    }
    //Key ở đây là việc (root node) + chia index
    public static int nthUglyNumber(int n) {
        int ugly[] = new int[n];
        int index1 = 0, index2 = 0, index3 = 0;

        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            ugly[i] = Math.min(ugly[index1] * 2,
                    Math.min(ugly[index2] * 3, ugly[index3] * 5));
            if (ugly[i] == ugly[index1] * 2) {
                index1++;
            }
            if (ugly[i] == ugly[index2] * 3) {
                index2++;
            }
            if (ugly[i] == ugly[index3] * 5) {
                index3++;
            }
        }
        return ugly[n - 1];
    }

    //Key ơ đây là việc add all values
    //Bỏ values đã cho vào rồi
    //VD: 2: (2*2), (2*3), (2*5) --> Đã được cho vào + sắp xếp --> Poll nó đi
    //--> Get first next value (min)
    public static int treeSet(int n) {
        TreeSet<Long> set = new TreeSet();
        HashMap<Long, Boolean> map = new HashMap<>();
        set.add(1l);
        map.put(1l, Boolean.TRUE);

        for (int i = 0; i < n - 1; i++) {
            Long v = set.pollFirst();

            if (map.get(v * 2) == null) {
                set.add(v * 2);
                map.put(v * 2, Boolean.TRUE);
//                continue;
            }
            if (map.get(v * 3) == null) {
                set.add(v * 3);
                map.put(v * 3, Boolean.TRUE);
//                continue;
            }
            if (map.get(v * 5) == null) {
                set.add(v * 5);
                map.put(v * 5, Boolean.TRUE);
//                continue;
            }
        }
        return set.first().intValue();
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(7));
        System.out.println(treeSet(7));
    }
}
