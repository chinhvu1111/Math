package contest;

public class E112_FindTheNumberOfGoodPairsI {

    public static int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int n=nums1.length;
        int m= nums2.length;
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(nums1[i]%(nums2[j]*k)==0){
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
    }
}
