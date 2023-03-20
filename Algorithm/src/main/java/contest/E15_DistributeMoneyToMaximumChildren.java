package contest;

import interviews.book.E3_AdditiveNumber;

public class E15_DistributeMoneyToMaximumChildren {

    public static int distMoney(int money, int children) {
        if(money<children){
            return -1;
        }
        if(money==4&&children==1){
            return -1;
        }
        int result=0;

        while (money!=0){
            if(children>2){
                if(money>8&&money-8>=children-1){
                    money-=8;
                    result++;
                    children--;
                }else if(money<=8){
                    return result;
                }else{
                    if(money>=children){
                        return result;
                    }else{
                        return 0;
                    }
                }
            }else if(children==2){
                if(money>8&&money!=12){
                    money-=8;
                    result++;
                    children--;
                }else{
                    return result;
                }
            }else{
                if(money==8){
                    return result+1;
                }else{
                    return result;
                }
            }
        }
        return 1;
    }

//    public static int distMoneyDynamic(int money, int children) {
//        int v1=distMoneyDynamic(money-8, children-1);
//        int v2=distMoneyDynamic(money)
//    }

    public static void main(String[] args) {
        int money = 16, children = 2;
//        System.out.println(distMoney(money, children));
//        money = 20;
//        children = 3;
//        System.out.println(distMoney(money, children));
//        money = 1;
//        children = 2;
//        System.out.println(distMoney(money, children));
//        money = 8;
//        children = 3;
//        System.out.println(distMoney(money, children));
//        money = 9;
//        children = 2;
//        System.out.println(distMoney(money, children));
//        money = 9;
//        children = 3;
//        System.out.println(distMoney(money, children));
        money = 17;
        children = 4;
        System.out.println(distMoney(money, children));
        //** Đề bài
        //- Chia số lượng money cho children sao cho :
        //+ Tiền được chia hết
        //+ children nào cũng nhận tiền ít nhất 1 và không = 4
        //- Trả lại số lần nhiều nhất để children có thể nhận được 8 tiền
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- Bài này đơn thuần là ta dùng while để chia 8 money nhiều nhất có thể.
        //1.2, Tránh các trường hợp đặc biệt:
        //1.2.1,
        //money = 20;
        //children = 3;
        //==> Trường hợp này thì chia bình thường
        //1.2.2,
        //money = 1;
        //children = 2;
        //==> Trường hợp không chia được -1
        //1.2.3,
        //money = 8;
        //children = 3;
        //==> Trường hợp chia số lần nhận 8 money = 0 khi money = 8 ==> mà còn tận 2 childrent sau khi chia 8 lần 1
        //1.2.4,
        //money = 9;
        //children = 2;
        //==> Trường hợp chia được duy nhất 1 lần nhận 8 money
        //1.2.5,
        //money = 9;
        //children = 3;
        //==> Trường hợp hợp chia xong 8 thì còn tận 2 children --> Không chia 8 được ==> nhận 0 nếu (money >= children)
        //1.2.6,
        //money = 17;
        //children = 4;
        //==> 8,(money=9, child= 4) ==> Chỉ có thể nhận được 1 lần money khi (money=9, child= 4) không chia được nhận 8 money nào
        //
    }
}
