package leetcode_medium;

import java.util.*;

public class SumOfSubarrayMinimums_65 {

    public static int sumSubarrayMinsTimeOut(int[] arr) {
        int rs=0;
        int n=arr.length;
//        List<Integer> results=new ArrayList<>();
        PriorityQueue<Integer>  currentRs;
        PriorityQueue<Integer> beforeRs=new PriorityQueue<Integer>();
//        results.add(arr[0]);
        beforeRs.add(arr[0]);
        rs+=arr[0];

        for(int i=1;i<n;i++){
            currentRs=new PriorityQueue();
            currentRs.add(arr[i]);
            rs+=arr[i];

//            for(Integer e: beforeRs){
//                if(arr[i]<e){
//                    currentRs.add(arr[i]);
//                }else {
//                    currentRs.add(e);
//                }
//            }

            while (!beforeRs.isEmpty()){
                int e=beforeRs.peek();

                if(e>arr[i]){
                    List<Integer> appendList=Collections.nCopies(beforeRs.size(), arr[i]);
                    rs+=arr[i]*beforeRs.size()%1_000_000_007;
                    rs%=1_000_000_007;
                    currentRs.addAll(appendList);
                    break;
                }else{
                    currentRs.add(e);
                    rs+=e;
                    rs%=1_000_000_007;
                }
                beforeRs.poll();
            }
//            results.addAll(currentRs);
            beforeRs.clear();
            beforeRs.addAll(currentRs);
//            beforeRs=new PriorityQueue<>(currentRs);
        }
//        for(Integer e:results){
//            rs+=e;
//            rs%=1_000_000_007;
//        }
        return rs;
    }

    public static int sumSubarrayMinsWrong(int[] arr) {
        int rs=0;
        int n=arr.length;
//        List<Integer> results=new ArrayList<>();
        TreeSet<Integer> currentRs;
        TreeSet<Integer> beforeRs=new TreeSet();
//        results.add(arr[0]);
        beforeRs.add(arr[0]);
        rs+=arr[0];
        int max=Arrays.stream(arr).max().getAsInt();
        int count[]=new int[max+1];

        for (int i=0;i<n;i++){
            count[arr[i]]=1;
        }
        for(int i=1;i<n;i++){
            currentRs=new TreeSet();
            currentRs.add(arr[i]);
            rs+=arr[i];

//            for(Integer e: beforeRs){
//                if(arr[i]<e){
//                    currentRs.add(arr[i]);
//                }else {
//                    currentRs.add(e);
//                }
//            }
            int tempCount=count[arr[i]];

            while (!beforeRs.isEmpty()){
                Integer e=beforeRs.first();

                if(e>=arr[i]){
                    int c=count[e];
                    tempCount+=count[e];

                    if(e>arr[i]){
                        count[e]=1;
                    }
                    //Viết kiểu này nếu arr[i]==e --> Sai khi update cả 2
//                    rs+=arr[i]*count[e]%1_000_000_007;
                    rs+=arr[i]*c%1_000_000_007;
                    rs%=1_000_000_007;
                    currentRs.add(arr[i]);
//                    break;
                }else{
                    currentRs.add(e);
                    rs+=e*count[e]%1_000_000_007;
                    rs%=1_000_000_007;
                }
                beforeRs.pollFirst();
            }
            if(tempCount!=0){
                count[arr[i]]=tempCount;
            }
//            results.addAll(currentRs);
            beforeRs.addAll(currentRs);
//            beforeRs=new PriorityQueue<>(currentRs);
        }
//        for(Integer e:results){
//            rs+=e;
//            rs%=1_000_000_007;
//        }
        return rs;
    }

    public static int sumSubarrayMins(int[] arr) {
        long rs=0;
        int n=arr.length;
        long lefts[]=new long[n];
        long rights[]=new long[n];
        lefts[0]=1;
        rights[n-1]=1;
        int left=1;
        int right=1;

        for(int i=1;i<n;i++){
            left=i;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    break;
                }
                left=j;
            }
            lefts[i]=i-left+1;

            int before=n-i-1;
            right=before;

            for(int j=before+1;j<n;j++){
                if(arr[before]>=arr[j]){
                    break;
                }
                right=j;
            }
            rights[before]=right-before+1;
        }

        for(int i=0;i<n;i++){
            rs=(rs+(arr[i]*lefts[i]*rights[i])%1_000_000_007)%1_000_000_007;
        }
        return (int)rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{3,1,2,4};
//        int arr[]=new int[]{11,81,94,43,3};
//        int arr[]=new int[]{19,19,62,66};
//        int arr[]=new int[]{19,19};
//        int arr[]=new int[]{96,36,29,36,95,64,63,72,39,42};
        //Khi count[36]=2;
        //Sau khi tính 29 xong --> reset 36 -->1 vì 29 đã update toàn bộ 36 trước đó
        //Dùng 36 tiếp --> Sẽ gây sai
        //Cases này sai do mình đang cố counting các số + treeset để giảm số lượng các số xuất hiện --> Giảm thời gian xử lý
//        int arr[]=new int[]{96,36,29,36,95};
//        int arr[]=new int[]{59,1,16,34,79,72,34,63,5,17,99,29,40,55,30,48,24,5,18,53};
        int arr[]=new int[]{59,1,16,34,79,72,34,63,5,17,99,29,40,55,30,48,24,5,18,53};
        System.out.println(sumSubarrayMinsTimeOut(arr));
        System.out.println(sumSubarrayMinsWrong(arr));
        System.out.println(sumSubarrayMins(arr));
    }
}
