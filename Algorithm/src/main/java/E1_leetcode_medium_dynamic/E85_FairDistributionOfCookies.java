package E1_leetcode_medium_dynamic;

import java.util.HashMap;
import java.util.Map;

public class E85_FairDistributionOfCookies {

    public static void getAllCases(HashMap<Integer, Integer> values, int index,
                                   int currentPos, int currentSum, int[] nums){
        if(index==-1){
            if(currentSum!=0){
                values.put(currentPos, currentSum);
            }
            return;
        }
        //1000000
        getAllCases(values,index-1, currentPos|(1<<(index)), currentSum+nums[nums.length-1-index], nums);
        getAllCases(values,index-1, currentPos, currentSum, nums);
    }

    public static int distributeCookies(int[] cookies, int k) {
        int n=cookies.length;
        int size=1<<n;

        int[][] dp=new int[k+1][size];
        HashMap<Integer, Integer> hashMap=new HashMap<>();
        getAllCases(hashMap, n-1, 0, 0, cookies);

        for(int i=1;i<=size-1;i++){
            if(hashMap.containsKey(i)){
                dp[1][i]=hashMap.get(i);
            }
        }
        System.out.println(hashMap.entrySet());
        for(int i=2;i<=k;i++){
            for(int j=1;j<=size-1;j++){
//                int min=Integer.MAX_VALUE;
//                int h=0;

                for(Map.Entry<Integer, Integer> entry:hashMap.entrySet()){
                    if((j&entry.getKey())==0){
                        int val=j|entry.getKey();
//                        System.out.printf("%s %s %s\n", Integer.toBinaryString(j), Integer.toBinaryString(entry.getKey()), entry.getValue());
//                        min=Math.min(min, entry.getValue());
//                        if(min>=entry.getValue()){
//                            min=entry.getValue();
//                            h=entry.getKey();
//                        }
                        if(dp[i][val]==0){
                            dp[i][val]=Integer.MAX_VALUE;
                        }
                        dp[i][val]=Math.min(dp[i][val], Math.max(hashMap.get(j), dp[i-1][entry.getKey()]));
//                        System.out.printf("level: %s, %s %s %s %s %s %s %s\n",
//                                i, dp[i-1][entry.getKey()], Integer.toBinaryString(j),
//                                hashMap.get(j), dp[i][val], Integer.toBinaryString(entry.getKey()), dp[i-1][entry.getKey()], Integer.toBinaryString(val));
                        System.out.printf("level: %s, %s %s %s %s\n",
                                i, Integer.toBinaryString(j), Integer.toBinaryString(entry.getKey()), dp[i-1][entry.getKey()], Integer.toBinaryString(val));
                    }
                }
//                if(dp[j]==0){
//                    dp[j|h]=Integer.MAX_VALUE;
//                }
//                if(min!=Integer.MAX_VALUE){
//                    dp[j|h]=Math.max(hashMap.get(j), min);
//                }
//                System.out.printf("level: %s, %s %s %s\n",i, Integer.toBinaryString(j), min, dp[j|h]);
            }
        }
        return dp[k][size-1];
    }

    public static void main(String[] args) {
//        int[] cookies = {8,15,10,20,8};
//        int k = 2;
//        int[] cookies = {6,1,3,2,2,4,1,2};
//        int k = 3;
        int[] cookies = {1,8,16,5,6,14};
        int k = 6;
        //[6,1], [3,2,2], and [4,1,2]
        System.out.println(distributeCookies(cookies, k));
        //** Đề bài:
        //- Chia N túi bánh cho M người
        //- Chia sao người nhận bánh nhiều nhất là x bánh ==> Chia sao để x nhỏ nhất.
        //VD:
        //int[] cookies = {8,15,10,20,8};
        //int k = 2;
        //Return 31 là min. (8,15,8),(10,20)
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- Để người nhận nhiều nhất MIN thì không được chia lệch.
        //VD:
        //1,4,5 k=2
        //(1,4),(5)
        //
        //VD: 10,20,40 k=2
        //(40)(10,20)
        //
        //Input: cookies = [6,1,3,2,2,4,1,2], k = 3
        //Output: 7
        //21 bánh /3 ==> Mỗi người ~7 bánh.
        //
        //1,1,2,2,2,3,4,6
        //
        //- k người mỗi người có 2^n khả năng nhận bánh
    }
}
