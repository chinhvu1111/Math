package leetcode_medium_dynamic;

public class E83_CampusBikesII {

    public static int distance(int[] p1, int[] p2){
        return Math.abs(p1[0]-p2[0])+ Math.abs(p1[1]-p2[1]);
    }

    public static int rs=Integer.MAX_VALUE;
    public static int[][] dp;
    public static boolean [] visited;

    public static int topDownDp(int index, int[][] workers, int[][] bikes, int prevPos, int prevVal){
        if(index>=workers.length){
            return prevVal;
        }
        if(dp[index][prevPos]!=0){
            return dp[index][prevPos];
        }
        int currentMin=Integer.MAX_VALUE;

        for(int i=0;i<bikes.length;i++){
            if(visited[i]){
                continue;
            }
            int value=distance(workers[index], bikes[i]);
            visited[i]=true;
            int currentValue=topDownDp(index+1, workers, bikes, prevPos|(1<<i), value);
            visited[i]=false;
            currentMin=Math.min(currentMin, currentValue);
        }
        dp[index][prevPos]=currentMin+prevVal;
//        System.out.printf("%s %s %s\n", index, prevPos, currentMin+prevVal);
        return currentMin+prevVal;
    }

    public static int topDownDpRefactor(int index, int[][] workers, int[][] bikes, int prevPos, int prevVal){
        if(index>=workers.length){
            return prevVal;
        }
        if(dp[index][prevPos]!=0){
            return dp[index][prevPos];
        }
        int currentMin=Integer.MAX_VALUE;

        for(int i=0;i<bikes.length;i++){
            if((prevPos>>i&1)==1){
                continue;
            }
            int value=distance(workers[index], bikes[i]);
            int currentValue=topDownDpRefactor(index+1, workers, bikes, prevPos|(1<<i), value);
            currentMin=Math.min(currentMin, currentValue);
        }
        dp[index][prevPos]=currentMin+prevVal;
//        System.out.printf("%s %s %s\n", index, prevPos, currentMin+prevVal);
        return currentMin+prevVal;
    }

    public static int assignBikes(int[][] workers, int[][] bikes) {
        int n=workers.length;
        int m=bikes.length;
        int mem= (int) Math.pow(2, m);
        //111
        dp=new int[n][mem];
        visited=new boolean[n];
        int rs=topDownDpRefactor(0, workers, bikes, 0, 0);
//        System.out.println(rs);
        return rs;
    }

    public static void main(String[] args) {
//        int[][] worker={{0,0},{1,1},{2,0}};
//        int[][] bikes={{1,0},{2,2},{2,1}};
//        int[][] worker={{0,0},{2,1}};
//        int[][] bikes={{1,2},{3,3}};
        int[][] worker={{0,0},{1,0},{2,0},{3,0},{4,0}};
        int[][] bikes={{0,999},{1,999},{2,999},{3,999},{4,999}};
        //
        assignBikes(worker, bikes);
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //int[][] arr={{0,0},{1,1},{2,0}};
        //int[][] worker={{1,0},{2,2},{2,1}};
        //- Nói chung bài này xuay quanh việc đệ quy có nhớ thôi:
        //
        //a,b,c
        //d,e,f,h
        //
        //					                a
        //                    /    \            \            \
        //                  d       e            f             h
        //                 /	     \            \
        //                b(e,f,h)    b(d,f,h)    b(d,e,h)     b(d,e,f) ===> Có b(de), b(h,f) trùng ==> Có thể dùng lại được :
        //              /   \
        //             e     f
        //            /       \
        //           c         c
        //          /           \
        //         f             e
        //
        //- a -> d | b -> e | c ->f
        //- a -> d | b -> f | c ->e
        //- a -> e | b -> d | c ->f
        //- a -> e | b -> f | c ->d
        //- a -> f | b -> d | c ->e
        //- a -> f | b -> e | c ->d
        //0111
        //
        //dp[i][1000]
        //
        //Để lưu theo dạng ntn
        //A : 000
        //B : 010
        //
        //- Ta sẽ lưu lại thành dp[index][1000] --> Là giá trị xe đang xét thứ (i) và các cases còn lại có thể xét tiếp
        //1.2,
        //- Các worker sẽ được chỉ định lần lượt index : 0 --> n-1
        //- Còn các bike thì sẽ được chọn ngẫu nhiên:
        //+ Để có thể bỏ visited[i] ==>
        //==> Ta có thể check bitmask (value >>i  & 1==0) thì ta sẽ được phép xét
        //1.3, Complexity
        //- Time complextity : O(N * 2^M * M)
        //+ N là số worker --> Mỗi worker tướng ứng số lượng state của bitmask
        //- Space complexity : O(N*2^M)
        //
    }
}
