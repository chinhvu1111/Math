package leetcode_medium_dynamic;

public class FlipStringToMonotoneIncreasing_67 {

    public static int minFlipsMonoIncr(String s) {
        int n=s.length();
        int r=0;
        int countZeros=0;
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='0'){
                countZeros++;
            }
        }

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                r++;
            }
            int remainingZero=countZeros-(i+1)+r;
            rs=Math.min(rs, r+remainingZero);
        }

//        for(int i=0;i<n-1;i++){
//            rs=Math.min(rs, ones[i]+zeros[i+1]);
//        }
//        rs=Math.min(rs, Math.min(ones[n-1], zeros[0]));
        rs=Math.min(rs, countZeros);
        return rs;
    }

    public static int minFlipsMonoIncrOptimize(String s) {
        int flips = 0;
        // this variables denotes the number of 1s we have seen so far
        int count_ones = 0;

        for(int i = 0; i < s.length(); i++) {
            // calculating the number of flips required to make the string[0:i] monotone increasing
            if(s.charAt(i) == '1') {
                // 1 is ok, if the last digit is 1, it will make a monotonic increasing array
                ++count_ones;
            } else {
                // 0 is problem. We have 2 choices here.
                // Either flip this this 0 to 1, in this case our num flips will increase by 1
                // Or we can let this 0 be as is, but then we have to flip all the previous 1s to 0, this is why we need the count_one variable
                flips = Math.min(flips + 1, count_ones);
            }
        }
        return flips;
    }

    public static void main(String[] args) {
//        String s="00110";
        String s="11011";
        System.out.println(minFlipsMonoIncr(s));
        System.out.println(minFlipsMonoIncrOptimize(s));
        //Bài này tư duy như sau:
        //Ngoài cách đánh dấu:
        //1,
        //(0--> i)  ones[i]: Là số chữ số (1) cho đến vị trí (i)
        //Sau đó lấy ones[i] + (số chữ số 0 còn lại vì nếu ta chuyển thì max nhất là chuyển remaining zeros)
        //--> Set all range ==> Result
        //2, Ta sẽ count lần lượt các chữ số 1 ( 0 --> n-1)
        //- Ta sẽ so sánh việc:
        //+ flíp all chữ số 1.
        //+ Khi gặp chữ số 0 --> Ta sẽ flip nó thành 1.
        //--> Với mỗi (i) ta có 2 cases lựa chọn"
        //+ Flip --> thành (All 0)
        //+ Flip vị trí hiện tại thành (1)
        //** Hoặc có thể tư duy (TỐI ƯU NHƯ SAU):
        //+ Vị trí hiện tại =0 --> Full đằng trươc =0
        //+ Vị trí hiện tại (0--> Chuyển thành 1) --> Thì update số lần flips
    }
}
