package contest;

import java.util.Arrays;
import java.util.HashSet;

public class E16_MaximizeGreatnessOfAnArray {
    public static int maximizeGreatness(int[] nums) {
        int n=nums.length;
        int[] nums1=new int[n];

        System.arraycopy(nums, 0, nums1, 0, n);
        Arrays.sort(nums1);
        boolean[] visited=new boolean[n];
        int rs=0;

        for(int e:nums){
            int indexGreater=search(nums1, e, 0, n-1);
//            System.out.printf("%s : index %s, value ", e, indexGreater);
//            if(indexGreater!=-1){
//                System.out.print(nums1[indexGreater]);
//            }
//            System.out.println();
            if(indexGreater!=-1){
                if(!visited[indexGreater]){
                    rs++;
                    visited[indexGreater]=true;
                }else{
//                    System.out.printf("%s : index %s, value-1 \n", e, indexGreater);
                    int index=indexGreater;
                    while (indexGreater>=0&&visited[indexGreater]&&nums1[indexGreater]>e) indexGreater--;
//                    System.out.printf("%s : index %s, value-2 \n", e, indexGreater);
                    if(indexGreater!=-1&&nums1[indexGreater]>e){
                        rs++;
                        visited[indexGreater]=true;
                        continue;
                    }
                    while (index<n&&visited[index]) index++;
                    if(index!=n&&nums1[index]>e){
                        rs++;
                        visited[index]=true;
                    }
//                    System.out.printf("%s : index %s, value-3 \n", e, index);
                }
            }
        }
        return rs;
    }

    /*
    Tìm phần tử lớn > value nhưng nhỏ nhất
    low, mid, high
    1,2 ==> mid=1
     */
    public static int search(int[] nums, int value, int low, int high){
        if(low>=high){
            if(nums[low]>value){
                return low;
            }
            if(nums[high]>value){
                return high;
            }
            return -1;
        }
        int mid=low + (high-low)/2;
        int rs=-1;

        if(nums[mid]>value){
            rs=mid;
            high=mid-1;
        }else{
            low=mid+1;
        }
        int index=search(nums, value, low, high);
        if(index==-1){
            return rs;
        }
        return index;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,3,5,2,1,3,1};
        int[] arr=new int[]{1,2,3,4};
        //1,3,5,2,1,3,1
        //
        //1,1,1,2,3,3,5
        //1,3,5,2,1,3,1
        //2,5,-1,3,3,-1
        System.out.println(maximizeGreatness(arr));
    }
}
