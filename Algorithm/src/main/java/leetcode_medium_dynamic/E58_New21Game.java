package leetcode_medium_dynamic;

public class E58_New21Game {
    public static double new21Game(int n, int k, int maxPts) {
        int dp[]=new int[k];
        dp[0]=1;

        for(int i=1;i<=maxPts;i++){
            
        }

        return 1;
    }
    public static void main(String[] args) {
        int n=6, k=1, maxPts=10;
        //Giải thích đề bài:
        //Tức là giới hạn số điểm là k
        //Tính tỉ lệ ta có được số điểm <=n
        //- Tức là ta vẫn có thể lấy sao (số điểm <k)
        //n và k khác nhau như thế nào
        //+ k là số điểm mà khi >=k --> Ngừng vẽ
        //--> Tức là lần cuổi có thể >k (vẫn tính nếu <n)
        //+ n là số điểm biên thực sự <n mới tính tỷ lệ
        System.out.println(new21Game(n, k, maxPts));
    }
}
