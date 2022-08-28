package interviews;

public class E120_ValidPerfectSquare_binary {

    public static boolean isPerfectSquare(int num) {
        if(num==1){
            return true;
        }
        int left=1;
        int right=num/2;

        while (left<=right){
            int mid=left+(right-left)/2;

            if(mid<num/mid){
                left=mid+1;
            }else if(mid>num/mid){
                right=mid-1;
            }else{
                if(mid*mid==num){
                    return true;
                }else {
                    break;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        int num=16;
//        int num=14;
        int num=5;
        System.out.println(isPerfectSquare(num));
    }
}
