package E1_PrefixSum;

public class E5_FindTheHighestAltitude {

    public int largestAltitude(int[] gain) {
        int sum=0;
        int n=gain.length;
        int rs=0;

        for(int i=0;i<n;i++){
            sum+=gain[i];
            rs=Math.max(rs, sum);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- prefix sum demo --> Tổng lần tăng toạ độ max
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //
    }
}
