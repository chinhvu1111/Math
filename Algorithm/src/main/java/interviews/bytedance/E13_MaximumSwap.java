package interviews.bytedance;

public class E13_MaximumSwap {

    public static int maximumSwap(int num) {
        int l=getLength(num);
        int[] dp=new int[l];
        int[] digits=new int[l];
        int currentMax=Integer.MIN_VALUE;
        int currentIndex=0;

        for(int i=l-1;i>=0;i--){
            dp[i]=i;
//            System.out.println(currentIndex);
            if(num%10>currentMax){
                currentMax=num%10;
                currentIndex=i;
            }
            dp[i]=currentIndex;
            currentMax=Math.max(currentMax, num%10);
            digits[i]=num%10;
            num/=10;
        }
        int rs=0;
        boolean isSwap=false;

        for(int i=0;i<l;i++){
            if(dp[i]!=i&&digits[i]!=digits[dp[i]]&&!isSwap){
                int temp=digits[i];
                digits[i]=digits[dp[i]];
                digits[dp[i]]=temp;
                isSwap=true;
            }
            rs=rs*10+digits[i];
        }
        return rs;
    }

    public static int getLength(int num){
        int l=0;

        while (num!=0){
            l++;
            num/=10;
        }
        return l;
    }

    public static void main(String[] args) {
//        int num=2736;
//        int num=2;
        int num=98368;
        System.out.println(maximumSwap(num));
        //
        //#Reference:
        //671. Second Minimum Node In a Binary Tree
        //321. Create Maximum Number
    }
}
