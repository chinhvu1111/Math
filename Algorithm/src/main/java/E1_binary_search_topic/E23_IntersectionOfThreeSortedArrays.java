package E1_binary_search_topic;

import java.util.ArrayList;
import java.util.List;

public class E23_IntersectionOfThreeSortedArrays {

    public static List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int p1=0,p2=0,p3=0;
        int n=arr1.length;
        int m=arr2.length;
        int l=arr3.length;
        List<Integer> ans=new ArrayList<>();

        while(p1<n&&p2<m&&p3<l){
            if(arr1[p1]==arr2[p2]&&arr2[p2]==arr3[p3]){
                ans.add(arr1[p1]);
                p1++;
                p2++;
                p3++;
            }else {
                if(arr1[p1]<arr2[p2]){
                    p1++;
                }else if(arr2[p2]<arr3[p3]){
                    p2++;
                }else{
                    p3++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //#Reference:
        //2248. Intersection of Multiple Arrays
    }
}
