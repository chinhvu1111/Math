package leetcode_medium;

import java.util.LinkedList;

public class CheapestFlightsWithinKStops_51 {

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int dp[][]=new int[n][k+2];
        int arr[][]=new int[n][n];
        boolean visited[][]=new boolean[n][n];
//        int depth[]=new int[n];
        LinkedList<Integer> queue=new LinkedList<>();
        queue.add(src);

        for(int i=0;i<flights.length;i++){
            arr[flights[i][0]][flights[i][1]]=flights[i][2];
        }
        //Chỗ này phải giảm tăng k+1 --> k+2 vì:
        //1, Ban đầu xuất phái từ (0 -> 1) sẽ được phép cộng thêm 1
        //--> Nếu không cộng thì ct:
        //dp[i][depth[i]]=dp[p][depth[p]]+arr[p][i]; --> Sẽ bị sai (Không theo rule qua các vòng lặp khác nhau)
        //2, Nếu giảm depth[src]=-1; --> Sẻ bị âm công thức bên trên
//        depth[src]=0;
        queue.add(src);

        while (!queue.isEmpty()){
            int p=queue.pop();

            for(int i=0;i<n;i++){
                if(arr[p][i]!=0&&!visited[p][i]){
//                    int temp=dp[i][depth[p]+1];
////                    dp[i][depth[i]]=dp[p][depth[p]]+arr[p][i];
//                    dp[i][depth[p]+1]=Math.min(dp[i][depth[p]+1], dp[p][depth[p]]+arr[p][i]);
//                    if(temp==dp[i][depth[p]+1]){
//                        depth[i]=depth[p]+1;
//                    }
                    for(int j=0;j<=k;j++){
                        dp[i][j+1]=dp[p][j]+arr[p][i];
                    }
                    queue.add(i);
                    visited[p][i]=true;
                }
            }
        }

        int rs=Integer.MAX_VALUE;
        for(int i=0;i<=k+1;i++){
            if(dp[dst][i]!=0){
                rs=Math.min(dp[dst][i], rs);
            }
        }
        return (rs==Integer.MAX_VALUE)?-1:rs;
    }

    public static void main(String[] args) {
//        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
//        int n=3;
//        int src = 0, dst = 2, k = 1;
        int[][] flights = {{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},{1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1},{8,7,3},{1,2,6},{4,1,5},{5,2,4},{1,9,1},{7,8,10},{0,4,2},{7,2,8}};
        int n=10;
        int src = 6, dst = 0, k = 7;
        System.out.println(findCheapestPrice(n, flights, src, dst, k));
    }
}
