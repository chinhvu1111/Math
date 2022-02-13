package leetcode_medium;

import java.util.Arrays;

public class BitwiseORsOfSubarrays_64{
    public static int result[];
    //1, Không dùng left, right dp[][] vì độ dài của array: 5 * 104
    public static int mapping[];

//    public static int solution(int arr[]){
//
//    }

    public static int subarrayBitwiseORs(int[] arr) {
        int n=arr.length;
        result=new int[1 << 15+1];
        mapping=new int[1 << 15+1];
        int arrConst=1<<15;
        Arrays.fill(result, -1);

        for(int i=2;i<=n;i++){
            for(int j=0;i+j<n;j++){
                int k=i+j-1;

                int left=arrConst>>(j+1);
                int right=arrConst << (n-k-2);
                int currentArr=arrConst | left | right;
                int leftBefore=arrConst>>(j+2);
//                int rightBefore=arrConst << (n-k-1);
                int keyLeftBefore=arrConst | leftBefore | right;
//                int keyRightBefore=arrConst | left | rightBefore;
                int valueLeftBefore=mapping[keyLeftBefore];

                mapping[currentArr]=valueLeftBefore | arr[j];
                result[valueLeftBefore | arr[j]]=1;
            }
        }
        int rs=0;

        for(int i=0;i<=arrConst;i++){
            if(result[i]!=-1){
                rs++;
            }
        }
        return rs;

    }

    public static void main(String[] args) {
        System.out.println(1 << 15);
        int arr[]=new int[]{1,1,2};
        System.out.println(subarrayBitwiseORs(arr));
    }
}
