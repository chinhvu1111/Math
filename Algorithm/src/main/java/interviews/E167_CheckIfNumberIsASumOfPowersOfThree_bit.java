package interviews;

public class E167_CheckIfNumberIsASumOfPowersOfThree_bit {

    public static boolean checkPowersOfThree(int n) {
        int number=n;

        while (number>=3&&(number%3==1||number%3==0)){
            number/=3;
        }
//        System.out.printf("%s %s, ", number, number%3);
        return number==1;
    }

    public static boolean checkPowersOfThreeRefactor1(int n) {
        while (n!=0){
            if(n%3==2){
                return false;
            }
            n/=3;
        }
        return true;
    }

    public static boolean checkPowersOfThreeRecursion(int n) {
        if(n==1){
            return true;
        }
        if(n%3==0){
            return checkPowersOfThreeRecursion(n/3);
        }
        if(n%3==1){
            return checkPowersOfThreeRecursion((n-1)/3);
        }
        return false;
    }

    public static void main(String[] args) {
//        int n=21;
        int n=91;
//        int n=12;
//        int n=11;
        System.out.println(checkPowersOfThree(n));
        //
        //** Đề bài như sau:
        //- Kiểm tra xem 1 số có phải là 101010 của 3 hay không
        //Input: n = 12
        //Output: true
        //Explanation: 12 = 31 + 32
        //
        //** Bài này tư duy như sau:
        //1,
        //Bài này vẫn là tư duy nhị phân
        //12 == (110) == 1 * 3 ^ 2 + 1 * 3 ^ 1 + 0 * 3 ^ 0
        //- Cách ta implement ở dạng:
        //1.1, Mục tiêu hướng đến là break ra được while mới kiểm tra kết quả.
        //--> number chia đến khi nào không chia được nữa ==>
        //- Tức là đều có thể xảy ra case (Đúng/Sai)
        //+ number>3 && (number%3 !=0/1) --> VD: 5%3==2 (Break) sẽ là case (Sai).
        //+ number>3 && (number%3 ==0/1) --> VD: 5%3==1 (Break) sẽ là case (Đúng).
        //1.2, Break ra while : Check kết quả number==1 return true <> false.
        //
        //2, Refactor
        //2.1, Break luôn trong while --> if(number%3==2) return false (Luôn)
        //Vì 3 là số nhỏ --> Chỉ có 2 nhỏ hơn nó (2!=0/1) nên ở đây ta xét luôn được case đó.
        //
        //3, Recursive
        //3.1, Ta chia như bình thường...
        //#Reference
        //- Divide Two Integers
        //- Reaching Points
        //- Minimum Time For K Virus Variants to Spread
        System.out.println(checkPowersOfThreeRefactor1(n));
        System.out.println(checkPowersOfThreeRecursion(n));
        System.out.println(checkPowersOfThreeRecursion(0));
    }
}
