package interviews;

import java.util.Stack;

public class E44_DailyTemperatures_stack {

    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<int[]> stack=new Stack<>();
        int length=temperatures.length;
        int dp[]=new int[length];

        for(int i=length-1;i>=0;i--){
            int count=0;

            while(!stack.isEmpty()&&stack.peek()[0]<=temperatures[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                count=stack.peek()[1]-i;
            }
            stack.add(new int[]{temperatures[i], i});
            dp[i]=count;
        }
        return dp;
    }

    public static int[] dailyTemperaturesOptimized(int[] temperatures) {
        Stack<Integer> stack=new Stack<>();
        int length=temperatures.length;
        int dp[]=new int[length];

        if(length==0 || length==1){
            return dp;
        }
        for(int i=length-1;i>=0;i--){
            int count=0;
            int value=temperatures[i];

            while(!stack.isEmpty()&&temperatures[stack.peek()]<=value){
                stack.pop();
            }
            if(!stack.isEmpty()){
                dp[i]=stack.peek()-i;
            }
            stack.add(i);
        }
        return dp;
    }

    public static int[] dailyTemperaturesOptimized99(int[] temperatures) {

        if(temperatures.length == 0)return new int[0];
        int[] answer = new int[temperatures.length];
        int hottest = 0;
        int days = 1;
        for(int i = temperatures.length-1; i>=0; i--){

            if(temperatures[i] >= hottest){
                hottest = temperatures[i];
                continue;
            }
            days = 1;
            while(temperatures[i+days] <= temperatures[i]){
                days = days+answer[i+days];
            }
            answer[i] = days;
        }
        return answer;

    }

    public static void main(String[] args) {
//        int temp[]=new int[]{73,74,75,71,69,72,76,73};
//        int temp[]=new int[]{30,40,50,60};
//        int temp[]=new int[]{30};
        int temp[]=new int[]{89,62,70,58,47,47,46,76,100,70};
        //Output :   [8,1,5,4,1,2,1,1,0,0]
        //Expected : [8,1,5,4,3,2,1,1,0,0]
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Dùng kiểu monostack --> Số phần tử sẽ bị pop đi sẽ không đủ --> Nên count số phần tử đứng sau sẽ SAI.
        //1.1, Thường với câc bài liên quan đến monostack ta thường lựa chọn add dạng int[]{value, index}
        //--> Khi add kiểu pair như thế này thường ta sẽ có thể lấy index kèm
        //1.2, Với bài này chỉ cần check khi xem temper[i] nó sẽ nhở hơn phần tử nào trong stack
        //VD: tìm được peek() --> Ta get index --> lấy index -i ==> Có được số count.
        //dp[i]=count.

        //Cách 2:
        //Thay vì lưu value --> Ta có thể lưu index
        //*** Ta lưu index để tận dụng tác dụng của index --> VD: Trong bài này dùng để tính số days.
        //==> Tăng tốc độ bằng cách giảm số không gian : init array.
        //LƯU Ý: Tốc độ duyệt từ đầu đến cuối --> Có thể khác nhau
        //--> Khi đọc arr[i] ==> Nên lưu lại vào thanh ghi (register) để tăng tốc độ.

        //NOTE**: 0 --> n-1 > nhanh hớn (n-1 --> 0)

        //Cách 3:
        //Dùng array để implement --> 100% speed

        dailyTemperatures(temp);
        dailyTemperaturesOptimized(temp);
        dailyTemperaturesOptimized99(temp);
    }
}
