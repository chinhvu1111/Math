package leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.Comparator;

public class E42_MaximumLengthOfPairChain {
    public static int findLongestChain(int[][] pairs) {
//        Arrays.sort(pairs, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] ints, int[] t1) {
//                return ints[0]-t1[0];
//            }
//        });
        Arrays.sort(pairs, Comparator.comparingInt(ints -> ints[0]));
        int rs=1;

        int dp[]=new int[pairs.length];
        int maxPos[]=new int[pairs.length];
        dp[0]=1;

        for(int i=1;i<pairs.length;i++){
            int currentMax=0;
            int value=pairs[i][0];

            for(int j=i-1;j>=0;j--){
                if(value<=pairs[j][1]){
                    continue;
                }
                currentMax=Math.max(currentMax, dp[j]);
                if(dp[j]==maxPos[j]||rs>=dp[j]){
                    break;
                }
            }
            dp[i]=currentMax+1;
            maxPos[i]=Math.max(dp[i], maxPos[i]);
            rs=Math.max(rs, currentMax+1);
        }
        return rs;
    }

    public static int findLongestChain1(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(ints -> ints[0]));
        int rs=1;
         int currentMax=0;

        for(int i=0;i<pairs.length-1;i++){
            if(pairs[i+1][0]>pairs[i][1]){
                currentMax++;
                rs=Math.max(rs, currentMax);
            }else{
                currentMax=0;
            }
        }
        return rs;
    }

    //Sắp xếp theo pair[i][1]
    public static int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int count = 0, i = 0, n = pairs.length;

        while (i < n) {
            count++;
            int curEnd = pairs[i][1];
            while (i < n && pairs[i][0] <= curEnd) i++;
        }
        return count;
    }

    //Sắp xếp theo pair[i][0]
    public static int findLongestChain3(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int count=1;
        int n=pairs.length;
        int currentRight=pairs[0][1];

        for(int i=0;i<n-1;i++){
            if(currentRight<pairs[i+1][0]){
                currentRight=pairs[i+1][1];
                count++;
                continue;
            }
            //Điều kiện chỗ này không hẳn là == thì mới update
            //Vì ta đăng xếp theo tăng dần sẵn rồi --> Chỉ cần >= là được
            //--> Nó phải là điều kiện đảo ngược của việc ( < ) --> Phải diễn ra sau việc ( < )
            //--> Nhớ chỗ này sẽ là (i+1)
            currentRight=Math.min(currentRight, pairs[i+1][1]);
        }
        return count;
    }

    public static void main(String[] args) {
//        int arr[][]={{1,2},{2,3},{3,4}};
//        int arr[][]={{1,2},{7,8},{4,5}};
//        int arr[][]={{1,3},{1,5}};
//        int arr[][]={{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
//        int arr[][]={{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};
        int arr[][]={{9,10},{9,10},{4,5},{-9,-3},{-9,1},{0,3},{6,10},{-5,-4},{-7,-6}};
//        int arr[][]={{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};
        System.out.println(findLongestChain(arr));
//        System.out.println(findLongestChain1(arr));
        //Tư duy giải pháp này như sau:
        //Ta có:
        //Sắp xếp ở đây ta sẽ sắp xếp theo chiều tăng dần của pair[i][1] để:
        //1, (1,3), (1,5), (2,6) --> Nếu ta chọn thì sẽ chỉ chọn một mình (1,3)
        //Thỏa mãn 2 tiêu chí:
        //1.1, Nếu thêm (1,3) thì length++ --> Tức là tât cả các giá trị khác đều chỉ cộng 1
        //--> Ta sẽ đánh giá dựa trên part[i][1] --> Tỉ lệ giao (tập hợp hiện tại) với (các phần tử đưng sau)
        //2, Nếu vậy thì ta chỉ cần lấy phần tử gần nhất + lấy pair[i][1] của nó so sánh với pair[j][0] các phần tử sau
        //2.1, ở đây ta chỉ quan tâm các trường hợp loại, arr[i][1]< arr[i][0] --> next (i++)
        //2.2,  --> Nếu tìm thành công thì ta cọi như lấy phần tử hiện tại (i) làm mốc mới
        //--> Để mang đi so sánh với các phần tử sau đó nữa
        //** Chú ý: Mấu chốt vấn đề ở đây là pair[i][1] --> Có ý nghĩa rất lớn
        //* Với tư duy như thế này: --> Ta hoàn toàn có thể phát triển được theo hướng (sắp xếp theo) arr[i][0]

        //Tư duy nếu sắp xếp phần tử đầu như sau:
        //1, Ta phải kiểm tra điều kiện thỏa mãn trước: --> Update count++
        //2, Nếu không thảo mãn: --> Ta ưu tiên update phía bên phải (right)
        //Điều kiện chỗ này không hẳn là == thì mới update
        //Vì ta đang xếp theo tăng dần sẵn rồi --> Chỉ cần >= là được
        //--> Nó phải là điều kiện đảo ngược của việc ( < ) --> Phải diễn ra sau việc ( < )
        //--> Nhớ chỗ này sẽ là (i+1)
        //currentRight=Math.min(currentRight, pairs[i+1][1]); --> Tức là luôn ưu tiên phần tử bên phải
//        System.out.println(findLongestChain2(arr));
        System.out.println(findLongestChain3(arr));
    }
}
