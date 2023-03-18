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
    }
}
