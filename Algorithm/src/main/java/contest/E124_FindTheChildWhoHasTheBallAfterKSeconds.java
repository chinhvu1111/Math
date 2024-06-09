package contest;

public class E124_FindTheChildWhoHasTheBallAfterKSeconds {

    public static int numberOfChild(int n, int k) {
        int index=0;
        boolean right=true;
        int count=0;

        while (count<k){
            if(right){
                index++;
                if(index==n-1){
                    right=false;
                }
            }else {
                index--;
                if(index==0){
                    right=true;
                }
            }
            count++;
        }
        return index;
    }

    public static void main(String[] args) {
        //2 <= n <= 50
        //1 <= k <= 50
//        int n = 3, k = 5;
        int n = 5, k = 6;
        System.out.println(numberOfChild(n, k));
    }
}
