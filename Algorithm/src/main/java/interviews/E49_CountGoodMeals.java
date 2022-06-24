package interviews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class E49_CountGoodMeals {

    public static int countPairs(int[] deliciousness) {
//        int min= Arrays.stream(deliciousness).min().getAsInt();
        int max= Arrays.stream(deliciousness).max().getAsInt();
        int n=deliciousness.length;
//        int numberMin= 0;
//        if(min!=0){
//            numberMin=(int) log2(min*2);
//        }
//        int numberMax= (int) log2(max*2);
//        Arrays.sort(deliciousness);
        TreeSet<Integer> treeSet=new TreeSet<>();
        int counts[]=new int[max+1];
        for(int i=0;i<n;i++){
            treeSet.add(deliciousness[i]);
            counts[deliciousness[i]]++;
        }

        int rs=0;
        boolean visited[]=new boolean[max+1];
        int countDistinct=0;
        Iterator<Integer> iterator1 = treeSet.iterator();

        while (iterator1.hasNext()){
            Integer number=iterator1.next();

            double currentNumber = log2(number*2);
            int currentNumberInt = (int) log2(number*2);

            if(currentNumberInt==currentNumber){
                rs+=binomialCoeff(counts[number], 2);
                countDistinct++;
            }
        }

        if(treeSet.size()==countDistinct){
            return rs;
        }

        for(int h=0;h<=20;h++) {
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

    public static int countPairsOptimized(int[] deliciousness) {
        int rs=0;
        int n=deliciousness.length;
        HashMap<Integer, Integer> mapValues=new HashMap<>();

        for(int i=0;i<n;i++){
            int currentValue=1;
            int val=deliciousness[i];

            for(int j=0;j<=21;j++){
                Integer sub=mapValues.get(currentValue-val);

//                if(currentValue-val<0){
//                    continue;
//                }
                if(sub==null){
                    sub=0;
                }
                rs=(rs%1_000_000_007+sub)%1_000_000_007;
                currentValue=currentValue*2;
            }
            Integer prevValue=mapValues.get(val);

            if(prevValue==null){
                prevValue=0;
            }
            mapValues.put(val, prevValue+1);
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,1,1,3,3,3,7};
//        int arr[]=new int[]{1,1,1};
//        int arr[]=new int[]{1,1,1,3};
//        int arr[]=new int[]{1,3,5,7,9};
        int arr[]=new int[]{149,107,1,63,0,1,6867,1325,5611,2581,39,89,46,18,12,20,22,234};
//        int arr[]=new int[]{149,107,1,63,0,1,6867,1325,5611,2581,39,89,46,18,12,20,22,234};
//        int arr[]=new int[]{32,32};
//        int nums[]=new int[1048577];
        System.out.println(countPairs(arr));
        System.out.println(countPairsOptimized(arr));
    }
}
