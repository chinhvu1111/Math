package interviews;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class E49_CountGoodMeals {

    public static int countPairs(int[] deliciousness) {
        int min= Arrays.stream(deliciousness).min().getAsInt();
        int max= Arrays.stream(deliciousness).max().getAsInt();
        int n=deliciousness.length;
        int numberMin= 0;
        if(min!=0){
            numberMin=(int) log2(min*2);
        }
        int numberMax= (int) log2(max*2);
        Arrays.sort(deliciousness);
        TreeSet<Integer> treeSet=new TreeSet<>();
        for(int i=0;i<n;i++){
            treeSet.add(deliciousness[i]);
        }
        int rs=0;
        int counts[]=new int[max+1];
        boolean visited[]=new boolean[max+1];

        for(int i=0;i<n;i++){
            counts[deliciousness[i]]++;
        }
        for(int i=0;i<n;i++){
            double currentNumber = log2(deliciousness[i]*2);
            int currentNumberInt = (int) log2(deliciousness[i]*2);

            if(currentNumberInt==currentNumber){
                rs+=binomialCoeff(counts[deliciousness[i]], 2);
            }
            i+=counts[deliciousness[i]]-1;
        }

        for(int h=numberMin;h<=numberMax;h++) {
            int currentNumber = (int) Math.pow(2, h);
            visited=new boolean[max+1];
            Iterator<Integer> iterator = treeSet.iterator();

            while (iterator.hasNext()){
                Integer number=iterator.next();

                if(currentNumber - number>=0
                        &&currentNumber-number<=max
                        &&currentNumber!=2*number
                        &&!visited[number]&&!visited[currentNumber-number]){
                    rs += counts[number]*counts[currentNumber - number];
                    visited[number]=true;
                    visited[currentNumber-number]=true;
                }
            }
//            for (int i = 0; i < n / 2; i++) {
//                if (currentNumber - deliciousness[i] >= 0&&!visited[deliciousness[i]]) {
//                    rs += counts[deliciousness[i]]*counts[currentNumber - deliciousness[i]];
//                    visited[deliciousness[i]]=true;
//                }
//            }
        }
        return rs;
    }

    public static double log2(double x){
        return Math.log(x)/Math.log(2);
    }

    static int binomialCoeff(int n, int k)
    {
        // Base Cases
        if (k > n)
            return 0;
        if (k == 0 || k == n)
            return 1;

        // Recur
        return binomialCoeff(n - 1, k - 1)
                + binomialCoeff(n - 1, k);
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,1,1,3,3,3,7};
//        int arr[]=new int[]{1,1,1};
//        int arr[]=new int[]{1,1,1,3};
//        int arr[]=new int[]{1,3,5,7,9};
        int arr[]=new int[]{149,107,1,63,0,1,6867,1325,5611,2581,39,89,46,18,12,20,22,234};
//        int nums[]=new int[1048577];
        System.out.println(countPairs(arr));
    }
}
