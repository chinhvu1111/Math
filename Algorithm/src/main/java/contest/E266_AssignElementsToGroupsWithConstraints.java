package contest;

import java.util.HashMap;

public class E266_AssignElementsToGroupsWithConstraints {

    public static int[] assignElements(int[] groups, int[] elements) {
        int n=groups.length;
        int m=elements.length;

        HashMap<Integer, Integer> elementToIndex=new HashMap<>();
        for(int i=m-1;i>=0;i--){
            elementToIndex.put(elements[i], i);
        }
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            int size=groups[i];
            rs[i]=-1;
            int minIndex=Integer.MAX_VALUE;

            for(int j=1;j*j<=size;j++){
                if(size%j==0){
                    if(elementToIndex.containsKey(j)){
                        minIndex=Math.min(minIndex, elementToIndex.get(j));
//                        break;
                    }
                    int expectedElement=size/j;
                    if(elementToIndex.containsKey(expectedElement)){
                        minIndex=Math.min(minIndex, elementToIndex.get(expectedElement));
                    }
                }
            }
            if(minIndex==Integer.MAX_VALUE){
                minIndex=-1;
            }
            rs[i]=minIndex;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array groups), where groups[i] represents (the size of the ith group).
        //- You are also given an integer array elements.
        //- Your task is to assign (one element) to (each group) based on the following rules:
        //  + An (element j) can be assigned to (a group i)
        //      + if groups[i] is divisible by elements[j].
        //  + If there are (multiple elements) that can be assigned,
        //      + assign (the element with the smallest index j).
        //  + If no element satisfies the condition for a group,
        //      + assign -1 to that group.
        //* Return (an integer array assigned), where assigned[i] is (the index of the element) chosen for (group i),
        //  + or -1 if (no suitable element) exists.
        //- Note: An element may be assigned to (more than one group).
        //
        //Example 1:
        //Input: groups = [8,4,3,2,4], elements = [4,2]
        //Output: [0,0,-1,1,0]
        //Explanation:
        //elements[0] = 4 is assigned to groups 0, 1, and 4.
        //elements[1] = 2 is assigned to group 3.
        //Group 2 cannot be assigned any element.
        //
        //* Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= groups.length <= 10^5
        //1 <= elements.length <= 10^5
        //1 <= groups[i] <= 10^5
        //1 <= elements[i] <= 10^5
        //  + Length<=10^5 => Time: O(n*k)
        //  + val <= 10^5 => Big
        //
        //- Brainstorm
        //- For each element, how to get (the group % element==0):
        //  +
        //
//        int[] groups = {8,4,3,2,4}, elements = {4,2};
        int[] groups = {2,3,5,7}, elements = {5,3,3};
        int[] rs= assignElements(groups, elements);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
    }
}
