package contest;

public class E142_AlternatingGroupsI {

    public static int numberOfAlternatingGroups(int[] colors) {
        int n= colors.length;
        int rs=0;

        for(int i=0;i<n;i++){
            int leftIndex=i>0?i-1:n-1;
            int rightIndex=i<n-1?i+1:0;
            int left=colors[leftIndex];
            int right=colors[rightIndex];

            if(colors[i]!=left&&colors[i]!=right){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is a circle of red and blue tiles. You are given an array of integers colors. The color of tile i is represented by colors[i]:
        //  + colors[i] == 0 means that tile i is red.
        //  + colors[i] == 1 means that tile i is blue.
        //- Every 3 contiguous tiles in the circle with alternating colors (
        //  + the (middle tile) has a different color from its (left and right tiles))
        // is called an alternating group.
        //* Return the number of alternating groups.
        //Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
        //
        //
        int[] colors={};
        System.out.println(numberOfAlternatingGroups(colors));
    }
}
