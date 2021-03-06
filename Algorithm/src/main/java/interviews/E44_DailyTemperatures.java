package interviews;

import java.util.Stack;

public class E44_DailyTemperatures {

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
        //B??i n??y t?? duy nh?? sau:
        //C??ch 1:
        //1, D??ng ki???u monostack --> S??? ph???n t??? s??? b??? pop ??i s??? kh??ng ????? --> N??n count s??? ph???n t??? ?????ng sau s??? SAI.
        //1.1, Th?????ng v???i c??c b??i li??n quan ?????n monostack ta th?????ng l???a ch???n add d???ng int[]{value, index}
        //--> Khi add ki???u pair nh?? th??? n??y th?????ng ta s??? c?? th??? l???y index k??m
        //1.2, V???i b??i n??y ch??? c???n check khi xem temper[i] n?? s??? nh??? h??n ph???n t??? n??o trong stack
        //VD: t??m ???????c peek() --> Ta get index --> l???y index -i ==> C?? ???????c s??? count.
        //dp[i]=count.

        //C??ch 2:
        //Thay v?? l??u value --> Ta c?? th??? l??u index
        //*** Ta l??u index ????? t???n d???ng t??c d???ng c???a index --> VD: Trong b??i n??y d??ng ????? t??nh s??? days.
        //==> T??ng t???c ????? b???ng c??ch gi???m s??? kh??ng gian : init array.
        //L??U ??: T???c ????? duy???t t??? ?????u ?????n cu???i --> C?? th??? kh??c nhau
        //--> Khi ?????c arr[i] ==> N??n l??u l???i v??o thanh ghi (register) ????? t??ng t???c ?????.

        //NOTE**: 0 --> n-1 > nhanh h???n (n-1 --> 0)

        //C??ch 3:
        //D??ng array ????? implement --> 100% speed

        dailyTemperatures(temp);
        dailyTemperaturesOptimized(temp);
        dailyTemperaturesOptimized99(temp);
    }
}
