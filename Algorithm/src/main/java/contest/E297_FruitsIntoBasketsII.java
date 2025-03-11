package contest;

public class E297_FruitsIntoBasketsII {

    public static int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n=fruits.length;
        int m=baskets.length;
        boolean[] visited=new boolean[m];
        int rs=0;

        for(int i=0;i<n;i++){
            int j=0;
            boolean isValid=false;
            for(;j<m;j++){
                if(baskets[j]>=fruits[i]&&!visited[j]){
                    visited[j]=true;
                    isValid=true;
                    break;
                }
            }
            if(!isValid){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i] represents
        // the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
        //From left to right, place the fruits according to these rules:
        //  + Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the quantity of that fruit type.
        //  + Each basket can hold only one type of fruit.
        //  + If a fruit type cannot be placed in any basket, it remains unplaced.
        //Return the number of fruit types that remain unplaced after all possible allocations are made.
        int[] fruits = {3,6,1}, baskets = {6,4,7};
    }
}
