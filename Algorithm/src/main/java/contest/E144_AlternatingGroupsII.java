package contest;

public class E144_AlternatingGroupsII {

    public static int numberOfAlternatingGroups(int[] colors, int k) {
        int n=colors.length;
        int rs=0;
        int curDiff=0;

        if(n<k){
            return 0;
        }
        //1,2,1,1,5,6
        //(1,2) : curRs++
        //(2,1) : curRs++
        //==> x=n ==> max(curRs=x)
        //4
        for(int i=0;i<k;i++){
            int leftIndex=i>0?i-1:-1;
//            int rightIndex=i<n-1?i+1:-1;
            int left=leftIndex!=-1?colors[leftIndex]:-1;
//            int right=rightIndex!=-1?colors[rightIndex]:-1;

            if(colors[i]!=left){
                curDiff++;
            }
        }
        if(curDiff==k){
            rs++;
        }
        int l;
        for(l=0;l<n-1;l++){
            int leftIndex=l>0?l-1:-1;
            int rightIndex=l+1;
            int left=leftIndex!=-1?colors[leftIndex]:-1;
            int right=colors[rightIndex];

            //1,1,3
            if(colors[l]!=right){
                curDiff--;
            }
            int r = (l+k)%n;
            //[0,1,0,0,[1],0,1]
            //n=7
            //l=4
            //==> r = 3
            //==> (4+6)%n : WRONG
            //4+6-1= 9 ==> 3 ntn
            //9%6 = (l+k-1)%(n-1)
            if(r==l+1){
                break;
            }
//            System.out.printf("l:%s, r:%s\n", l, r);
            leftIndex=r>0?r-1:n-1;
            rightIndex=r<n-1?r+1:-1;
            left=leftIndex!=-1?colors[leftIndex]:-1;
            right=rightIndex!=-1?colors[rightIndex]:-1;

            if(colors[r]!=left){
                curDiff++;
            }
            if(curDiff==k){
                rs++;
//                System.out.printf("l:%s, r:%s\n", l+1, r);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is a circle of red and blue tiles. You are given an array of integers colors and an integer k.
        // The color of tile i is represented by colors[i]:
        //  + colors[i] == 0 means that tile i is red.
        //  + colors[i] == 1 means that tile i is blue.
        //- An alternating group is (every k contiguous tiles) in the circle with alternating colors
        // (each tile in the group except the first and last one has a different color from its left and right tiles).
        //* Return the number of alternating groups.
        //- Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //3 <= colors.length <= 10^5
        //0 <= colors[i] <= 1
        //3 <= k <= colors.length
        //==> Time: O(n)
        //
        //- Brainstorm
        //Example 2:
        //Input: colors = [0,1,0,0,1,0,1], k = 6
        //Output: 2
        //a,b,c,d
        //
        //
        int[] colors = {0,1,0,1,0};
        int k = 3;
//        int[] colors = {0,1,0,0,1,0,1};
//        int k = 6;
        System.out.println(numberOfAlternatingGroups(colors, k));
    }
}
