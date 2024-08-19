package E1_daily;

import java.util.HashMap;

public class E72_LemonadeChange {

    public static boolean lemonadeChange(int[] bills) {
        int n=bills.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for(int i=0;i<n;i++){
            if(bills[i]==10){
                if(!mapCount.containsKey(5)){
                    return false;
                }
                if(mapCount.get(5)==1){
                    mapCount.remove(5);
                }else{
                    mapCount.put(5, mapCount.get(5)-1);
                }
                mapCount.put(10, mapCount.getOrDefault(10, 0)+1);
            }else if(bills[i]==5){
                mapCount.put(5, mapCount.getOrDefault(5, 0)+1);
            }else{
                int count10 = mapCount.getOrDefault(10, 0);
                int count5 = mapCount.getOrDefault(5, 0);

                if(count10>=1&&count5>=1){
                    if(count10==1){
                        mapCount.remove(10);
                    }else{
                        mapCount.put(10, count10-1);
                    }
                    if(count5==1){
                        mapCount.remove(5);
                    }else{
                        mapCount.put(5, count5-1);
                    }
                }else if(count5>=3){
                    if(count5==3){
                        mapCount.remove(5);
                    }else{
                        mapCount.put(5, count5-3);
                    }
                }else{
                    return false;
                }
                mapCount.put(20, mapCount.getOrDefault(20, 0)+1);
            }
        }
        return true;
    }

    public static boolean lemonadeChangeOptimization(int[] bills) {
        int n=bills.length;
        int[] count=new int[11];

        for(int i=0;i<n;i++){
            if(bills[i]==10){
                if(count[5]<=0){
                    return false;
                }
                count[5]--;
                count[10]++;
            }else if(bills[i]==5){
                count[5]++;
            }else{
                if(count[10]>=1&&count[5]>=1){
                    count[5]--;
                    count[10]--;
                }else if(count[5]>=3){
                    count[5]-=3;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean lemonadeChangeRefer(int[] bills) {
        // Count of $5 and $10 bills in hand
        int fiveDollarBills = 0;
        int tenDollarBills = 0;

        // Iterate through each customer's bill
        for (int customerBill : bills) {
            if (customerBill == 5) {
                // Just add it to our count
                fiveDollarBills++;
            } else if (customerBill == 10) {
                // We need to give $5 change
                if (fiveDollarBills > 0) {
                    fiveDollarBills--;
                    tenDollarBills++;
                } else {
                    // Can't provide change, return false
                    return false;
                }
            } else { // customerBill == 20
                // We need to give $15 change
                if (tenDollarBills > 0 && fiveDollarBills > 0) {
                    // Give change as one $10 and one $5
                    fiveDollarBills--;
                    tenDollarBills--;
                } else if (fiveDollarBills >= 3) {
                    // Give change as three $5
                    fiveDollarBills -= 3;
                } else {
                    // Can't provide change, return false
                    return false;
                }
            }
        }
        // If we've made it through all customers, return true
        return true;
    }

    public static void main(String[] args) {
        // Requirement
        //- At a lemonade stand, (each lemonade costs) $5.
        //- Customers are standing in a queue to buy from you and order (one at a time) (in the order specified by bills).
        //- Each customer will only buy (one lemonade) and
        //  + pay with either a ($5, $10, or $20) bill.
        //- You must provide (the correct change) to (each customer) so that (the net transaction) is that the customer pays $5.
        //* Note that you (do not have any change) in hand at first.
        //- Given an integer array bills where bills[i] is (the bill the ith customer pays),
        //* return true if you can provide (every customer) with (the correct change), or false otherwise.
        //- Đại loại là mua bán:
        //  + Giá mỗi quả cam là 5 ==> bill là tiền khách hàng sẽ trả
        //      + Ta cần thu tiền + trả lại tiền cho khác hàng
        //* return true nếu có thể trả lại được
        //
        //Example 1:
        //
        //Input: bills = [5,5,5,10,20]
        //Output: true
        //Explanation:
        //From the first 3 customers, we collect three $5 bills in order.
        //From the fourth customer, we collect a $10 bill and give back a $5.
        //From the fifth customer, we give a $10 bill and a $5 bill.
        //Since all customers got correct change, we output true.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= bills.length <= 10^5
        //bills[i] is either 5, 10, or 20.
        //+ bill[i] chỉ có thể là 5/10/20
        //  ==> Chỉ cần có thể trả 5,15=(10+5) là được
        //
        //- Brainstorm
        //- count số lượng 5,10,20
        //  ==> Giảm dần là được.
        //- 10 - 5 = 5
        //- 20 - 5 = 10+5 = 5+5+5
        //  + Ưu tiên từ trái qua phải
        //  ==> Trả tiền chẵn tốt hơn.
        //
        //1.1, Optimization
        //- Tối ưu ==> Dùng array thay vì map
        //- Có 2 giá trị có thể dùng để change:
        //  + 5,10 ==> dùng 2 variables cũng được thay vì dùng array
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n)
        //
        int[] bills={5,5,5,10,20};
        System.out.println(lemonadeChange(bills));
        System.out.println(lemonadeChangeOptimization(bills));
        System.out.println(lemonadeChangeRefer(bills));
        //#Reference:
        //373. Find K Pairs with Smallest Sums
        //2012. Sum of Beauty in the Array
        //2740. Find the Value of the Partition
    }
}
