package interviews;

public class E18_PalindromeNumber {
    public static boolean isPalindrome(int x) {
        int hLength=0;
        int numberRight=x;
        int right=0;

        do{
            hLength++;
            right=right*10+numberRight%10;
            numberRight=numberRight/10;
        }while(Math.pow(10, hLength*2-1)<x);
//        int length=0;
//
        //3.1, Ở đây ta chia ra 2 trường hợp
        //- Chẵn
        //- Lẻ
        //VD:
        // 1,2,2,1 hLength=3
        // 1,2,1 hLength=2
        //left=1221/ (hLength-1)
        //left =121 / (hlengh)
        //3.2, right là đảo của 21 chỉ cần /10 là xong.
        int left= 0;
        if(x/Math.pow(10, 2*hLength-3)>=10){
            left=(int) (x/(Math.pow(10, hLength)));
        }else{
//            length=2*hLength-1;
            left=(int) (x/(Math.pow(10, hLength-1)));
        }

        if(right/10==left){
            return true;
        }
//        int count=0;
//        int numberInitLeft=x;
//        int numberInitRight=x;
//
//        while(count<=hLength){
//            int left= (int) (numberInitLeft/Math.pow(10, length-count-1));
//            numberInitLeft= (int) (numberInitLeft%Math.pow(10, length-count-1));
//            int right=numberInitRight%10;
//            numberInitRight=numberInitRight/10;
//
//            if(left!=right){
//                return false;
//            }
//            count++;
//        }
        return false;
    }

    public boolean isPalindromeSlow(int x) {
        int hLength=0;

        do{
            hLength++;
        }while(Math.pow(10, hLength*2-1)<x);
        int length=0;

        if(x/Math.pow(10, 2*hLength-3)<10){
            length=2*hLength-2;
        }else{
            length=2*hLength-1;
        }
        int count=0;
        int numberInitLeft=x;
        int numberInitRight=x;

        while(count<=hLength){
            int left= (int) (numberInitLeft/Math.pow(10, length-count-1));
            numberInitLeft= (int) (numberInitLeft%Math.pow(10, length-count-1));
            int right=numberInitRight%10;
            numberInitRight=numberInitRight/10;

            if(left!=right){
                return false;
            }
            count++;
        }
        return true;
    }

    public static boolean isPalindromeNormal(int x) {
        String s=String.valueOf(x);
        int n=s.length();

        for(int i=0;i<n/2;i++){
            if(s.charAt(i)!=s.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeOptimize(int x) {
        int hLength=0;
        int number=0;
        int initNumber=x;

        do{
            hLength++;
            int multiple= (int) Math.pow(10, hLength);

            initNumber=initNumber/10;
        }while(Math.pow(10, hLength*2-1)<x);
        return number==x;
    }

    public static boolean isPalindromeO(int x) {

        if(x<0) return false;
        if(x==0) return true;

        int num = x;
        int temp = 0;

        while(num>0){

            temp = 10*temp + num%10;
            num = num/10;
        }

        return temp == x;
    }

    public static void main(String[] args) {
//        int x=1231;
//        int x=121;
        int x=1;
//        int x=9999;
//        int x=1001;
        System.out.println(isPalindrome(x));
//        System.out.println(isPalindromeOptimize(x));
        //Bài này có các tư duy như sau:
        //Cách 1, Cast int to string
        //Cách 2,
        //2.1, Tìm điểm middle point bằng cách check khoảng cách reach lớn nhất cho cả chuỗi (chẵn/ lẻ):
        //Ex : 1,2,2,1 hLength=3
        //Ex : 1,2,1 hLength=2
        //2.2, Ta cần tìm length
        //Sau đó check điều kiện (Để có thể tìm được các điểm 1231 --> 1,2 (ở đầu):
        //- length=2*hLength-2;
        //- length=2*hLength-1;
        //2.3, Áp dụng quy tác check đối xứng --> Tìm chuỗi nhỏ hơn
        // 1=1,2=2,....
        //
        //Cách 3:
        //Dùng cách reverse string == so với chính nó.
        //Nếu bằng thì đối xứng <> false
        //**NOTE: Tư duy chính ở đây là reverse + SO SÁNH VỚI CHÍNH NÓ.
        //Cách 4:
        //Dùng (middle point) + kết hợp với (tư duy check đối xứng).
        //4.1, Ở đây ta chia ra 2 trường hợp
        //- Chẵn
        //- Lẻ
        //VD:
        // 1,2,2,1 hLength=3
        // 1,2,1 hLength=2
        //left=1221/ (hLength-1)
        //left =121 / (hlengh)
        //4.2, right là đảo của 21 chỉ cần /10 là xong.
        System.out.println(isPalindromeO(x));
    }
}
