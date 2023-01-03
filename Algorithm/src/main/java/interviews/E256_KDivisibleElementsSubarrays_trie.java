package interviews;

import java.util.Arrays;
import java.util.HashSet;

public class E256_KDivisibleElementsSubarrays_trie {

    public static int countDistinct(int[] nums, int k, int p) {
        int n=nums.length;
        int[][] dp =new int[n][n];

        for(int i=0;i<n;i++){
            if(nums[i]%p==0){
                dp[i][i]=1;
            }
        }
        int rs=0;
        for(int i=2;i<=n;i++){
            for(int j=0;i+j-1<n;j++){
                if (nums[i+j-1]%p==0) {
                    dp[j][i+j-1]=dp[j][i+j-2]+1;
                }else{
                    dp[j][i+j-1]=dp[j][i+j-2];
                }
            }
        }
        //Distinct subarrays
        for(int i=1;i<=n;i++){
            HashSet<Integer> hashSet=new HashSet<>();
            int hashValue=0;
            int power=1;

            for(int j=0;j<i;j++){
                hashValue=hashValue*2 + nums[j];
                if(j!=0){
                    power=power*2;
                }
            }
            if(dp[0][i-1]<=k){
                rs++;
                hashSet.add(hashValue);
            }
//            System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, i)));

            //Step 1:
            //length 2,3
            //- : 0 .. 1 <2
            //-0,1,(2),3,4
            for(int j=i;j<n;j++){
//                System.out.println(hashValue);
//                System.out.println(Arrays.toString(Arrays.copyOfRange(nums, j - i+1, j+1)));
                hashValue-=power*nums[j-i];
                hashValue=hashValue*2 + nums[j];
                //- Có thể có 2 string khác nhau nhưng chung hash
                //- Nếu khác hash thì chắc chắn không thuộc
                if(!hashSet.contains(hashValue)){
                    if(dp[j-i+1][j]<=k){
                        rs++;
                        hashSet.add(hashValue);
                    }
//                    System.out.println(Arrays.toString(Arrays.copyOfRange(nums, j - i+1, j+1)));
//                    System.out.println(hashValue);
                }else if(checkIfNotMoreThanTwoTimeExist(Arrays.copyOfRange(nums, j - i+1, j+1), nums, j)){
                    if(dp[j-i+1][j]<=k){
                        rs++;
                        hashSet.add(hashValue);
                    }
//                    System.out.println(Arrays.toString(Arrays.copyOfRange(nums, j - i+1, j+1)));
//                    System.out.println(hashValue);
                }
//                if(checkIfNotMoreThanTwoTimeExist(Arrays.copyOfRange(nums, j - i+1, j+1), nums)){
//                    if(dp[j-i+1][j]<=k){
//                        rs++;
//                        hashSet.add(hashValue);
//                    }
////                    System.out.println(Arrays.toString(Arrays.copyOfRange(nums, j - i+1, j+1)));
////                    System.out.println(hashValue);
//                }
            }
            hashSet.clear();
        }

        return rs;
    }

    public static boolean checkIfNotMoreThanTwoTimeExist(int[] arr, int[] nums, int start){
//        int n=nums.length;
        int count=0;
        //Tức là method này sẽ kiểm tra xem liệu arr đã xuất hiện trước đó hay chưa

        for(int i=0;i+arr.length-1<=start;i++){
            if(arr[0]==nums[i]){
                int j=0;
                int k=i;
                while (j < arr.length && arr[j] == nums[k]){
                    j++;
                    k++;
                }
                if(j==arr.length){
                    count++;
                    if(count>=2){
                        return false;
                    }
                }
            }
        }
        return count==1;
    }

    public static class TrieNode{
        TrieNode[] childrend;
        boolean finished;

        public TrieNode() {
            childrend=new TrieNode[201];
        }
    }

    public static int countDistinctTrie(int[] nums, int k, int p) {
        int n=nums.length;
        TrieNode root=new TrieNode();
        TrieNode node=root;
        int rs=0;

        for(int i=0;i<n;i++){
            node=root;
            int count=0;
            int j=i;
            while (j<n&&count<=k){
                int ch=nums[j++];

                if(ch%p==0){
                    count++;
                    if(count>k){
                        break;
                    }
                }
                TrieNode chilrend=node.childrend[ch];

                if(chilrend==null){
                    rs++;
                    chilrend=new TrieNode();
                    node.childrend[ch]=chilrend;
                }
                node=chilrend;
            }
        }
        return rs;
    }
    public static final int MAX_P=200;
    public static int base=MAX_P+1;
    public static long mod=45887423068929227L;

    public static int countDistinctRabinKarpModify(int[] nums, int k, int p) {
        int n= nums.length;
        HashSet<Long> hashSet=new HashSet<>();

        for(int i=0;i<n;i++){
            long hash=0;
            int count=0;

            for(int j=i;count<=k&&j<n;j++){
                if(nums[j]%p==0){
                    count++;
                    if(count>k){
                        break;
                    }
                }
                hash=(hash*base+ nums[j])%mod;
                hashSet.add(hash);
            }
        }
        return hashSet.size();
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{2,3,3,2,2};
//        System.out.println(countDistinct(arr, 2,2));
//        int[] arr=new int[]{1,2,3,4};
//        System.out.println(countDistinct(arr, 4,1));
        //[14,19,9]
//        int[] arr=new int[]{14,19,9};
//        System.out.println(countDistinct(arr, 1,11));
//        int[] arr=new int[]{16,17,4,12,3};
//        System.out.println(countDistinct(arr, 4,1));
//        int[] arr=new int[]{8,14,5,14,11,19,6,12,6};
//        System.out.println(countDistinct(arr, 9,1));
//        /*
        int[] arr=new int[]{79,88,50,63,93,15,86,94,13,79,74,2,91,46,68,27,46,
                17,88,41,7,13,18,32,7,49,94,37,39,51,3,1,78,61,79,1,56,22,37,
                5,10,68,55,73,97,40,46,49,45,79,86,19,27,95,68,81,55,59,67,96,
                90,42,18,18,71,37,89,40,38,55,36,75,18,37,78,18,29,77,28,61,96,
                58,73,20,24,41,14,58,72,82,93,70,8,32,85,81,42,16,60,61,61,44,
                83,2,26,35,98,38,79,70,45,74,60,83,2,87,52,94,44,93,87,22,61,44,
                77,66,55,62,47,21,74,73,30,68,56,22,57,52,70,5,27,30,59,90,56,
                72,37,21,69,17,39,15,96,29,38,21,10,74,56,7,93,95,9,73,35,11,68,
                57,97,14,86,19,87,13,2,90,47,78,27,25,24,2,54,95,73,13,23};
//        */
        System.out.println(countDistinct(arr, 131,86));
        //
        //** Đề bài:
        //- Cho 2 số k, p
        //- Tính số tổng các array khác nhau sao cho trong đó số các số %p=0 <=k (Nhiều nhất là k)
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Cần áp dụng quy hoạch động
        //- dp[i][j] để lưu số lượng chữ số % p==0 nằm bên trong Array đó.
        //==> Có thể là 0,1,... ==> Lưu ý chỉ cần <=k là được nên 0 vẫn được tính count++.
        //- Tính phần này ta dùng đến length để tính :
        //+ dp[j][i+j-1]=dp[j][i+j-2]+1 : Trong trường hợp số tiếp theo % p==0
        //+ dp[j][i+j-1]=dp[j][i+j-2] : %p !=0
        //
        //1.2, Lúc này ta sẽ dùng thuật toán rabin-karp để tìm distinct array
        //- Ta sẽ chia ra thành length của array để tính dần dần từ 1 --> n
        //- Với mỗi length ta sẽ tính hash từ đầu đến cuối:
        //VD: length=3
        //+ Đoạn đầu ta sẽ tính : 0 --> length-1 (Trong bài này thì là i-1)
        //+ Ta tính hash khởi đầu bằng công thức : hash=hash*2+nums[i]
        //+ Đoạn sau ta sẽ bắt đầu với j=length (Trong bài này thì là i)
        //Có 2 format code như sau:
        //- Nếu xét từ length(i) : Vào for là bắt đầu dich luôn (Tính lại hash luôn) (Trừ left + right)
        //--> Thường nếu tính như thế này thì coi như là có 1 value luôn
        //+ nums[i-j] + nums[i]
        //- Còn nếu xét từ length-1 : Ta sẽ đặt phần dịch sau (Tính lại hash sau)
        //--> Tính như thế này thì vào for mới nhận value mới.
        //
        //- Mỗi length ta sẽ tạo 1 hashset để check tồn tại:
        //+ Với mỗi kết quả dp[i][j]<=k : thì ta sẽ rs++ và add hash value của array đó vào hashset
        //--> Chỉ thoả mãn mới add vào hashset (Tư duy hashset tương tự dạng check chuỗi start index của s1 trong s2)
        //- Với dạng check hash value sẽ có 2 trường hợp như sau:
        //+ Có thể có 2 string khác nhau nhưng chung hash
        //+ Nếu khác hash thì chắc chắn không thuộc (Tức là array mới)
        //
        //1.3, Nếu hashValue mà nó đã có rồi thì ta vẫn cần check 1 bước mới chắc là có loại nó không:
        //- Check xem các array đó đã đưọc xét rồi thật chưa <=> có giống với kết quả tính hash value không
        //==> Nếu mà khác + dp[i][j]<=k thì lấy thêm nó
        //- Thiết kế method check:
        //+ Truyền end index vào : Vị trí mà ta đã scan đến (Với cùng length + index cho đến khi check method này)
        //+ Lúc đó ta sẽ xét 0 --> end index + length của array được copy từ (i-j, i+1) ==> Hàm này chỉ xét trong khoảng
        //(i-j,i) mà thôi (i+1) để mốc break (bỏ đi)
        //+ Count số lần xuất hiện nếu count>=2 : return false <> đến cuối thì return true.
        //
        //1.4,
        //- Time complexity: O(N^2)
        //- Space complexity: O(N^2)
        //
        //Cách 2:
        //2.
        //2.1,
        //
//        System.out.println(countDistinctTrie(arr, 131, 86));
//        System.out.println(countDistinctTrie(arr, 2, 2));
        //
        //Cách 1 + modify:
        //
        System.out.println(countDistinctRabinKarpModify(arr, 131, 86));
//        System.out.println(countDistinctRabinKarpModify(arr, 2, 2));
        //#Reference:
        //2262. Total Appeal of A String
        //992. Subarrays with K Different Integers
        //1248. Count Number of Nice Subarrays
        //2334. Subarray With Elements Greater Than Varying Threshold
    }
}
