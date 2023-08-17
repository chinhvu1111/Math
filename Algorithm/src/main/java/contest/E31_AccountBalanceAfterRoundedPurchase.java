package contest;

public class E31_AccountBalanceAfterRoundedPurchase {

    public static int accountBalanceAfterPurchase(int purchaseAmount) {
        if(purchaseAmount%10==0){
            if(purchaseAmount<=100){
                return 100-purchaseAmount;
            }else{
                return 100;
            }
        }
        //9
        int left=(purchaseAmount/10)*10;
        left= Math.max(left, 0);
        int right=(purchaseAmount/10+1)*10;
//        System.out.printf("%s %s, %s\n", left, right, purchaseAmount);

        if(Math.abs(right-purchaseAmount)<=Math.abs(left-purchaseAmount)){
            if(right<=100){
                return 100-right;
            }else if(left<=100){
                return 100-left;
            }else{
                return 0;
            }
        }else{
            if(left<=100){
                return 100-left;
            }else{
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Tìm bội số x gần nhất với purchaseAmount --> Chọn số lớn
        //* Return số dư 100-x
        //
        //** Idea
        //1.
        //1.1,
        //- Dữ kiện:
        //+ Constraints:
        //0 <= purchaseAmount <= 100
        //
        //- Brainstorm
        //- Tìm bội số gần nhất ==>
        //- purchaseAmount/10)
        System.out.println(accountBalanceAfterPurchase(0));
        System.out.println(accountBalanceAfterPurchase(9));
        System.out.println(accountBalanceAfterPurchase(15));
        //30
        //75/10 = 7 ==> 70, 80
        System.out.println(accountBalanceAfterPurchase(75));
        //10
        System.out.println(accountBalanceAfterPurchase(11));
    }
}
