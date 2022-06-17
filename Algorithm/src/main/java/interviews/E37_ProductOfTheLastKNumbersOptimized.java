package interviews;

import java.util.ArrayList;
import java.util.List;

public class E37_ProductOfTheLastKNumbersOptimized {

    List<Integer> prefixNumbers;

    public E37_ProductOfTheLastKNumbersOptimized() {
        prefixNumbers=new ArrayList<>();
    }

    public void add(int num) {
        if(num==0){
            prefixNumbers.clear();
        }else{
            int size=prefixNumbers.size();
            int prev=1;

            if(size!=0){
                prev=prefixNumbers.get(size-1);
            }
            prefixNumbers.add(num*prev);
        }
    }

    public int getProduct(int k) {
        if(k>prefixNumbers.size()){
            return 0;
        }
        int size=prefixNumbers.size();
        int division=1;

        if(size-k-1>=0){
            division=prefixNumbers.get(size-k-1);
        }
        return prefixNumbers.get(size-1)/division;
    }

    public static void main(String[] args) {
        //Ta tư duy như sau:
        //Mấu chốt ở đây là gặp 0 --> Clear list
        //Nếu product k> prefix.length() ==> Return 0 luôn.
        E37_ProductOfTheLastKNumbersOptimized productOfNumbers = new E37_ProductOfTheLastKNumbersOptimized();
        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        System.out.println(productOfNumbers.getProduct(2)); // return 20. The product of the last 2 numbers is 5 * 4 = 20
        System.out.println(productOfNumbers.getProduct(3)); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        System.out.println(productOfNumbers.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        System.out.println(productOfNumbers.getProduct(2)); // return 32. The product of the last 2 numbers is 4 * 8 = 32

        //Next challenges:
        //Word Search II
        //Max Value of Equation
        //Removing Minimum and Maximum From Array

        //Minimum Absolute Sum Difference
        //Longest Common Subsequence Between Sorted Arrays
        //Count Number of Maximum Bitwise-OR Subsets
    }
}
