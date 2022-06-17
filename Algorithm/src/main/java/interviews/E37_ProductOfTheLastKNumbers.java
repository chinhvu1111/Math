package interviews;

import java.util.ArrayList;
import java.util.List;

public class E37_ProductOfTheLastKNumbers {

    List<List<Integer>> numbers;
    int currentIndex=0;

    public E37_ProductOfTheLastKNumbers() {
        numbers=new ArrayList<>();
        List<Integer> integers=new ArrayList<>();
        numbers.add(integers);
    }

    public void add(int num) {
        int size=numbers.get(currentIndex).size();
        int prevProduct=1;

        if(size>0){
            prevProduct=numbers.get(currentIndex).get(size-1);
        }
        if(num==0){
            List<Integer> integers=new ArrayList<>();
            numbers.add(integers);
            currentIndex++;
        }else{
            numbers.get(currentIndex).add(num*prevProduct);
        }
    }

    public int getProduct(int k) {
        int lastIndex=numbers.get(currentIndex).size()-1;
        int prevProduct=lastIndex-k;

        if(k>numbers.get(currentIndex).size()){
            return 0;
        }
        int division=1;
        if(prevProduct>=0&&numbers.get(currentIndex).get(prevProduct)>0){
            division=numbers.get(currentIndex).get(prevProduct);
        }
        return numbers.get(currentIndex).get(lastIndex)/division;
    }

    public static void main(String[] args) {
        E37_ProductOfTheLastKNumbers productOfNumbers = new E37_ProductOfTheLastKNumbers();
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
    }
}
