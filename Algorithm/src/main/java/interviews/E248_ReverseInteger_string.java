package interviews;

public class E248_ReverseInteger_string {

    public static int reverse(int x) {
        int number=0;

        while (x!=0){
            int value=x%10;

            if(number>Integer.MAX_VALUE/10){
                return 0;
            }else if(number<Integer.MIN_VALUE/10){
                return 0;
            }
            number=number*10+value;
            System.out.println(number);
            x=x/10;
        }
        return number;
    }

    public static void main(String[] args) {
//        int x=123;
//        int x=-123;
//        int x=120;
        int x=1534236469;
        System.out.println(reverse(x));
    }
}
