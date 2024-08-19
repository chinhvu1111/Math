package contest;

public class E169_CountSubstringsThatSatisfyKConstraintI {

    public static int countKConstraintSubstrings(String s, int k) {
        int rs=0;
        int n=s.length();
        int[] prefixSum=new int[n+1];

        for (int i = 1; i <= n; i++) {
            if(s.charAt(i-1)=='1'){
                prefixSum[i]=prefixSum[i-1]+1;
            }else{
                prefixSum[i]=prefixSum[i-1];
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=0;j+i-1<n;j++){
                int h=i+j-1;
                int countOne = prefixSum[h+1]-prefixSum[j];
                if(countOne<=k||i-countOne<=k){
//                    System.out.println(s.substring(j, h+1));
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        String s = "10101";
        int k = 1;
        System.out.println(countKConstraintSubstrings(s, k));
    }
}
