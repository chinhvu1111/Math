package interviews;

import java.util.Arrays;
import java.util.Comparator;

public class E139_RussianDollEnvelopes {

    public static int maxEnvelopesTLE(int[][] envelopes) {
        Arrays.sort(envelopes, (t1, t2) -> {
            if(t1[0]>t2[0]){
                return 1;
            }else if(t1[0]<t2[0]){
                return -1;
            }else if(t1[1]>t2[1]){
                return -1;
            }else{
                return 1;
            }
        });
//        Arrays.stream(envelopes).forEach(ints -> {
//            System.out.printf("%s %s,", ints[0], ints[1]);
//        });
        int n=envelopes.length;
        int dp[]=new int[n];
        int rs=0;

        if(n!=0){
            dp[0]=1;
            rs=1;
        }

        for(int i=1;i<n;i++){
            int currentValue=0;
            int height=envelopes[i][1];

            for(int j=i-1;j>=0;j--){
                if(height>envelopes[j][1]){
                    currentValue=Math.max(currentValue, dp[j]);
                }
            }
            dp[i]=currentValue+1;
            rs=Math.max(rs, dp[i]);
        }
        return rs;
    }

    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (t1, t2) -> {
            if(t1[0]>t2[0]){
                return 1;
            }else if(t1[0]<t2[0]){
                return -1;
            }else if(t1[1]>t2[1]){
                return -1;
            }else{
                return 1;
            }
        });
//        Arrays.stream(envelopes).forEach(ints -> {
//            System.out.printf("%s %s,", ints[0], ints[1]);
//        });
        int n=envelopes.length;
        int[] arr =new int[n];
        int top=0;

        if(n!=0){
            arr[0]=envelopes[0][1];
        }

        for(int i=1;i<n;i++){
            if(arr[top]<envelopes[i][1]){
                arr[++top]=envelopes[i][1];
            }else{
                int index=biSecLeft(arr, envelopes[i][1], top);
//                Arrays.stream(arr).forEach(i1 -> System.out.print(i1+" "));
//                System.out.println();

//                System.out.printf("%s %s,", envelopes[i][1], index);
                arr[index]=envelopes[i][1];
            }
        }

//        Arrays.stream(arr).forEach(i -> System.out.print(i+" "));
        return top+1;
    }

    public static int biSecLeft(int[] envelopesHeight, int target, int n){
        int left=0;
        int right=n;

        while (left<=right){
            int mid=left +(right-left)/2;

            if(envelopesHeight[mid]<target){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        //
        //** Đề bài:
        //- envelopes[i] = [wi, hi] đại diện cho (chiều rộng, chiều dài) của 1 bức thư
        //- 1 envelope khớp với 1 cái khi khi cả 2 (width và height) > 1 cái đã có
        //- Tìm số lượng envelopes nối  nhau dài nhất.
        //VD:
        //[2,3] => [5,4] => [6,7]
        //- Chý ý rằng width < heigh bình thường.
        //
        //
        //** Bài này tư duy như sau:
        //
        //
        //** reference
        //- The Number of Weak Characters in the Game
//        int[][] arr=new int[][]{{5,4},{6,4},{6,7},{2,3}};
//        int[][] arr=new int[][]{{1,1},{1,1}, {1,1}};
        int[][] arr=new int[][]{{1,1}};
        System.out.println(maxEnvelopesTLE(arr));
        System.out.println(maxEnvelopes(arr));
        //int[] a =new int[]{1,2,3,3,5};
        //System.out.println(biSecLeft(a, 3, 4));
        //
    }
}
