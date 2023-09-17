package E1_bitmanipulation;

public class E1_MaximumNumberOfAchievableTransferRequests {

    public static int maximumRequests(int n, int[][] requests) {
        int m=requests.length;
        int rs=0;
        int maxMask=1<<m;

        //Time: O(2^m)
        for(int i=0;i<maxMask;i++){
            //Time: O(n)
            int[] degree=new int[n];
            //0001000
            //Time : O(m)
            for(int j=0;j<m;j++){
                if(((i>>j)&1)==1){
                    int u=requests[j][0];
                    int v=requests[j][1];
                    degree[u]--;
                    degree[v]++;
                }
            }
            boolean isAdapt=true;

            //Time: O(n)
            for(int j=0;j<n;j++){
                if(degree[j]!=0){
                    isAdapt=false;
                    break;
                }
            }
            if(isAdapt){
                rs=Math.max(rs, Integer.bitCount(i));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Given n buildings
        //- There are some requests to tranfer
        //+ Request ith [fromi, toi]
        //+ Present the user ith want to transfer from building (from-ith) to (to-ith)
        //* Return the maximum number of achievable requests.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //1 <= n <= 20
        //1 <= requests.length <= 16
        //requests[i].length == 2
        //0 <= fromi, toi < n
        //- Có giới hạn --> Có thể thử hết các cases
        //
        //- Brainstorm
        //- Ý tưởng là dùn bitmask để list ra all cases
        //- Cần kiểm tra xem số lượng in và out của 1 collection là như nhau như thế nào?
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time: O(2^m*(n+m))
        //- Space : O(n)
        //
        //#Reference:
        //3. UTF-8 Validation
        //1441. Build an Array With Stack Operations
        //943. Find the Shortest Superstring
    }
}
