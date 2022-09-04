package interviews;

public class E126_LongerContiguousSegmentsOfOnesThanZeros_slice {

    public static boolean checkZeroOnes(String s) {
        int maxCountOne=0, maxCountZero=0;
        int startOne=-1, startZero=-1;
        int n=s.length();
        int i;

        for(i=0;i<n;i++){
            if(s.charAt(i)=='0'){
                maxCountOne=Math.max(maxCountOne, i-startOne-1);
                startOne=i;
            }else{
                maxCountZero=Math.max(maxCountZero, i-startZero-1);
                startZero=i;
            }
        }
        maxCountOne=Math.max(maxCountOne, i-startOne-1);
        maxCountZero=Math.max(maxCountZero, i-startZero-1);
        return maxCountOne>maxCountZero;
    }

    public static void main(String[] args) {
//        String s="1101";
//        String s="111000";
        String s="110100010";
        //Bài này tư duy như sau:
        //- Với dạng bài này ta tìm consecutive của cả (0 và 1)
        //--> Nên sẽ cần dùng đến count
        System.out.println(checkZeroOnes(s));
        //1, Bài này đơn giản ta sẽ duplicate variable lên thôi.
    }
}
