package interviews;

import java.util.*;

public class E278_CountSpecialQuadruplets {

    public static int countQuadrupletsMisleading(int[] nums) {
        int n=nums.length;
        int rs=0;
        Integer[] nums1=new Integer[n];
        int[] exists=new int[101];

        for(int i=0;i<n;i++){
            nums1[i]=nums[i];
            exists[nums1[i]]++;
        }
        Arrays.sort(nums1, Collections.reverseOrder());
        println(nums1);

        //a(n-4)=b(n-3)+c(n-2)+d(n-1)
        for(int i=0;i<n-3;i++){
            //5
            exists[nums1[i]]--;
            for(int j=i+1;j<n-2;j++){
                //3
                exists[nums1[j]]--;
                for(int h=j+1;h<n-1;h++){
                    int remainElement=nums1[i]-nums1[j]-nums1[h];
                    if(remainElement<0){
//                        System.out.printf("Negative: %s %s %s %s\n",remainElement, nums1[i], nums1[j], nums1[h]);
                        continue;
                    }
                    exists[nums1[h]]--;
                    if(exists[remainElement]!=0&&nums1[h+1]>=remainElement){
                        System.out.printf("Negative: %s %s (%s)%s (%s)%s (%s)%s\n",
                                remainElement, exists[remainElement], i, nums1[i], j, nums1[j], h, nums1[h]);
                        System.out.printf("%s %s\n", remainElement, exists[remainElement]);
                        rs+=exists[remainElement];
                    }
                }
                for(int h=j+1;h<n-1;h++){
                    int remainElement=nums1[i]-nums1[j]-nums1[h];
                    if(remainElement<0){
                        continue;
                    }
                    exists[nums1[h]]++;
                }
                exists[nums1[j]]++;
            }
//            exists[nums1[i]]++;
        }
        return rs;
    }

    public static int countQuadruplets(int[] nums) {
        int n=nums.length;
        int[] exists=new int[101];

        for(int i=0;i<n;i++){
            exists[nums[i]]++;
        }
        int rs=0;

        //a(n-3)+b(n-2)+c(n-1)=d(n)
        //a(0),b(1),c(2),d(3)
        for(int i=n-1;i>=3;i--){
            exists[nums[i]]--;
//            System.out.printf("Start: %s \n", exists[nums[i]]);
            for(int j=i-1;j>=2;j--){
                exists[nums[j]]--;
                for(int h=j-1;h>=1;h--){
                    int remainElement=nums[i]-nums[j]-nums[h];
                    if(remainElement<0){
//                        System.out.printf("Negative: %s %s %s %s\n",remainElement, nums1[i], nums1[j], nums1[h]);
                        continue;
                    }
                    exists[nums[h]]--;
                    if(exists[remainElement]!=0){
//                        System.out.printf("Negative: %s %s (%s)%s (%s)%s (%s)%s\n",
//                                remainElement, exists[remainElement], i, nums[i], j, nums[j], h, nums[h]);
//                        System.out.printf("%s %s\n", remainElement, exists[remainElement]);
                        rs+=exists[remainElement];
                    }
                }
                for(int h=j-1;h>=1;h--){
                    int remainElement=nums[i]-nums[j]-nums[h];
                    if(remainElement<0){
//                        System.out.printf("Negative: %s %s %s %s\n",remainElement, nums1[i], nums1[j], nums1[h]);
                        continue;
                    }
                    exists[nums[h]]++;
//                    System.out.printf("%s %s\n",nums[h], exists[nums[h]]);
                }
            }
            for(int j=i-1;j>=2;j--){
                exists[nums[j]]++;
            }
//            System.out.printf("End: %s \n", exists[nums[i]]);
//            for(int j=0;j<n;j++){
//                System.out.printf("I(%s) E(%s) V(%s), ", j, nums[j], exists[nums[j]]);
//            }
//            System.out.println();
        }
        return rs;
    }

    public static void println(Integer[] arr){
        for(int i=0;i< arr.length;i++){
            System.out.printf("%s,", arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //5(0), 3(1), 1(2), 1(3), 1(4)
        //+ 5,3,1(2) ==> Có 2 vị trí đằng sau để chọn
        //+ 5,3,1(3) ==> Có 1 vị trí đằng sau để chọn
        //
        int[] arr=new int[]{1,1,1,3,5};
        //39,27,16,9,3,2
        //+ 27,16,9,2
        //+ 39,27,9,3
//        int[] arr=new int[]{2,16,9,27,3,39};
        //90,85,49,37,28,20,8,8,
        //+ 85,49,28,8
        //+ 85,37,28,20
//        int[] arr=new int[]{28,8,49,85,37,90,20,8};
//        System.out.println(countQuadrupletsMisleading(arr));
        System.out.println(countQuadruplets(arr));
        //#Reference:
        //1996. The Number of Weak Characters in the Game
        //1534. Count Good Triplets
        //2552. Count Increasing Quadruplets
    }
}
