package E1_two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E2_AdvantageShuffle {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        //Time : nlog(n)
        Arrays.sort(nums1);
        int n=nums2.length;
        //Time: O(n)
        //Space : O(n)
        boolean[] visited=new boolean[n];
        //Time: O(n)
        //Space : O(n)
        int[] result=new int[n];
        List<Integer> unvisitedNums2=new ArrayList<>();

        //Time: O(n)
        for(int i=0;i<n;i++){
            //Time : O(log(n)
            //Space : log(n)
            int index=findIndexGreaterElement(nums1, nums2[i], 0, n);
            if(index!=-1){
                int j=index;
                //Ex: Worst case O(n)
                //Time : O(k) ==> Sum (n)
                for(;j<n;j++){
                    if(!visited[j]){
                        // System.out.printf("Visited: i: %s, j: %s, %s\n", i, j, nums1[j]);
                        result[i]=nums1[j];
                        visited[j]=true;
                        break;
                    }
                }
                if(j==n){
                    unvisitedNums2.add(i);
                }
            }else{
                unvisitedNums2.add(i);
                // System.out.printf("Miss: %s\n", i);
            }
        }
        List<Integer> unvisitedNums1=new ArrayList<>();

        //Time : O(h)
        for(int i=0;i<n;i++){
            if(!visited[i]){
                unvisitedNums1.add(i);
            }
        }
        for(int i=0;i<unvisitedNums1.size();i++){
            result[unvisitedNums2.get(i)]=nums1[unvisitedNums1.get(i)];
        }
        // System.out.printf("%s\n%s\n", unvisitedNums1, unvisitedNums2);
        return result;
    }

    public static int findIndexGreaterElement(int[] nums1, int value, int low, int high){
        if(low>=high-1){
            if(low<nums1.length&&nums1[low]>value){
                return low;
            }
            if(high<nums1.length&&nums1[high]>value){
                return high;
            }
            return -1;
        }
        int mid=low + (high-low)/2;

        // System.out.printf("Finding-1 value: %s, low: %s, high: %s, mid_val: %s\n", value, low, high, nums1[mid]);
        if(nums1[mid]<=value){
            low=mid+1;
        }else{
            high=mid;
        }
        // System.out.printf("Finding-2 value: %s: %s %s %s\n", value, low, high, nums1[mid]);
        return findIndexGreaterElement(nums1, value, low, high);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given nums1 and nums2 --> Same length.
        //- The advantage of nums1 with respect to nums2 is (the number of indices i) for which nums1[i] > nums2[i]
        //Ex :
        // nums1 = [12,24,8,32],
        // nums2 = [13,25,32,11]
        //result= [24,32,8,12]
        //- Số lượng nums1[i] > nums2[i] lớn nhất = 3
        //
        //* Return (any permutation of nums1) that maximizes its advantage with respect to nums2
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= nums1.length <= 105
        //nums2.length == nums1.length
        //0 <= nums1[i], nums2[i] <= 109
        //
        //- Ta cần tìm cách tối ưu:
        //- Brainstorm
        //Ex :
        // nums1 = [12,24,8,32],
        // nums2 = [13,25,32,11]
        //- Với mỗi index của new array nums2[i] --> Cần tìm số nums1[j] > nums2[i] + (num1[j] chưa dùng + min)
        //
        //- Idea
        //- sort(nums1)
        //- Bài này sẽ tìm element có index của nums1 có value > nums2[i] + visited[i]==false (==> Tìm số nhỏ nhất)
        //+ Nếu không tìm thấy --> Chạy qua phải lấy đến khi nào value > nums2[i] thì thôi
        //- Các phần tử chưa được lấy có 2 case:
        //+ For --> right vẫn không tìm thấy : add to list
        //+ Index==-1 --> Tức là không có phần tử nums1[j] > nums[i] : add to list
        //
        //- Các phần tử chưa visit --> Ta sẽ fill 1 cách tự do vào.
        //
        //1.1, Optimization
        //- Xem reference
        //
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n)) --> Worst case O(n^2*log(n))
        //
        //- Special cases:
        //nums1=  [2,0,4,1,2] --> [0,1,2,2,4]
        //nums2=  [1,3,0,0,2]
        //result= [0,4,1,2,2] --> [2,4,1,2,-1]
        //Expect =[2,0,2,1,4]
        //
        //
        //#Reference:
        //2557. Maximum Number of Integers to Choose From a Range II
        //2410. Maximum Matching of Players With Trainers
        //2389. Longest Subsequence With Limited Sum
        //
    }
}
