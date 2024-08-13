package contest;

import java.util.Arrays;

public class E160_FindTheCountOfNumbersWhichAreNotSpecial {

    public static boolean isSpecial(int x){
        int count=1;

        if(x%2==0){
            return false;
        }
        if(x%3==0){
            return false;
        }
        //2*x
        //3*x
        //
        //int index=0
        //Chỉ check số lẻ (odd thôi)
        //val <= (x-1)/2
        double limit1 = Math.sqrt(x);
        double limit2 = (x-1)/2;
        for(int i=1;2*i+1<=limit1&&i<=limit2;i++){
            int curVal=i*2+1;
            if(x%curVal==0){
                count++;
                if(x/curVal!=curVal){
                    return false;
                }
            }
            if(count>2){
                return false;
            }
        }
        return count==2;
    }

//    public static boolean isSpecial(int num){
//        if (num <= 1) {
//            return false;
//        }
//        if (num <= 3) {
//            return true;
//        }
//        if (num % 2 == 0 || num % 3 == 0) {
//            return false;
//        }
//        for (int i = 5; i * i <= num; i += 6) {
//            if (num % i == 0 || num % (i + 2) == 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static int nonSpecialCountWrong(int l, int r) {
        int rs=0;
        int index=(l-1)/2;
        //3 ==> 1
        //4 ==> 1
        int i = 0;

        //3
        //5
        //7
        //9
        //13
        //15 ==> 1,3,5
//        HashSet<Integer> setValid=new HashSet<>();
        for(;i<=r;index++){
            i=index*2+1;
//            System.out.println(i);
            if(i<=2){
                rs++;
                continue;
            }
//            if(!setValid.contains(i)&&!isSpecial(i)){
//                rs++;
//                for (int j = i * i; j <= r; j += i * 2){
//                    setValid.add(j / 2);
//                    rs++;
//                }
//            }
            if(!isSpecial(i)){
                rs++;
//                for (int j = i * i; j <= r; j += i * 2){
//                    setValid.add(j / 2);
//                    rs++;
//                }
            }
        }
        return rs;
    }

    public static int nonSpecialCountWrong1(int l, int r) {
        boolean[] numbers = new boolean[r+1];
        //- Đoạn này là loop các elements từ (2 --> r)
        //  + 1, 2 bị loại bỏ
        //
        for (int p = 2; p <= (int) Math.sqrt(r); ++p) {
            if (numbers[p] == false) {
                //- j = 2*2 = 4
                //- j = 3*3 = 4
                //- Ở đây ta tìm not special chứ không phải là prime:
                //  + p*p ==> Không phải là (prime) nhưng ta vẫn tính là (non special)
                for (int j = p * p; j <= r; j += p) {
                    numbers[j] = true;
                }
            }
        }
        int numberOfPrimes = 0;
        int numberOfPrimesBeforeL = 0;

        for (int i = 2; i <= r; i++) {
//            if(i<l&&numbers[i] == false){
//                System.out.printf("Before l: %s\n", i);
//                numberOfPrimesBeforeL++;
//            }
//            if (numbers[i] == false) {
//                System.out.printf("All %s\n", i);
//                ++numberOfPrimes;
//            }
            //- numbers[i] == False:
            //  + Là prime ==> rs++
            //- numbers[i] == True:
            //  + Là non prime
            //      + even: rs++
            //      + odd: chia hết cho 2 số trở lên: rs++
            //      + odd: chia hết cho 2 số : rs is not changed.
            //
            if(i<l&&numbers[i]){
                System.out.printf("Before l: %s\n", i);
                numberOfPrimesBeforeL++;
            }
            if (numbers[i]) {
                System.out.printf("All %s\n", i);
                ++numberOfPrimes;
            }
        }
//        System.out.println(numberOfPrimes-numberOfPrimesBeforeR-countEven);

        return numberOfPrimes-numberOfPrimesBeforeL;
    }

    public static int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] numbers = new boolean[n];
        for (int p = 2; p <= (int) Math.sqrt(n); ++p) {
            if (numbers[p] == false) {
                for (int j = p * p; j < n; j += p) {
                    numbers[j] = true;
                }
            }
        }

        int numberOfPrimes = 0;
        for (int i = 2; i < n; i++) {
            if (numbers[i] == false) {
                ++numberOfPrimes;
            }
        }
        //5,7
        //5,7 --> prime
        //Prime + even
        //100,1001
        //
        return numberOfPrimes;
    }

    public static int nonSpecialCount(int l, int r) {
        boolean[] numbers = new boolean[r+1];
        boolean[] isPrimes = new boolean[r+1];

        for (int p = 2; p <= (int) Math.sqrt(r); ++p) {
            if (numbers[p] == false) {
                //Mark non special
                //4: Là special
                //  + Chia hết cho 1,2
                //9: Là special
                //  + Chia hết cho 1,3
                //
                //- Nếu viết ntn:
                //  + Check prime
                for (int j = p * p; j <= r; j += p) {
                    //- Làm ntn để ignore case:
                    //- 81= 9*9 = 3*27
                    //==> Tính ra 3*27 ==> Nhận là non special luôn
                    //- Còn 9*9 ==> Chưa đủ nhận dạng vì:
                    //4=2*2 ==> Specials
                    if(j!=p*p){
                        numbers[j] = true;
                    }
                    isPrimes[j]=true;
                }
            }
        }
        int numberOfPrimes = 0;
        int numberOfPrimesBeforeL = 0;

        for (int i = 1; i <= r; i++) {
            if(i<l&&(numbers[i]|| !isPrimes[i])){
//                System.out.printf("Before l: %s\n", i);
                numberOfPrimesBeforeL++;
            }
            if (numbers[i]|| !isPrimes[i]) {
//                System.out.printf("All %s\n", i);
                ++numberOfPrimes;
            }
        }
//        System.out.println(numberOfPrimes-numberOfPrimesBeforeR-countEven);

        return numberOfPrimes-numberOfPrimesBeforeL;
    }

    public static int nonSpecialCountSpaceOptimization(int l, int r) {
        boolean[] numbers = new boolean[r+1];

        for (int p = 2; p <= (int) Math.sqrt(r); ++p) {
            if (!numbers[p]) {
                //Mark non special
                //4: Là special
                //  + Chia hết cho 1,2
                //9: Là special
                //  + Chia hết cho 1,3
                //
                //- Nếu viết ntn:
                //  + Check prime
                for (int j = p * (p+1); j <= r; j += p) {
                    //- Làm ntn để ignore case:
                    //- 81= 9*9 = 3*27
                    //==> Tính ra 3*27 ==> Nhận là non special luôn
                    //- Còn 9*9 ==> Chưa đủ nhận dạng vì:
                    //4=2*2 ==> Specials
//                    if(j!=p*p){
//                        numbers[j] = true;
//                    }else{
//                        System.out.printf("Ignore: %s\n", j);
//                    }
                    numbers[j]=true;
                }
            }
        }
        int numberOfPrimes = 0;
        int numberOfPrimesBeforeL = 0;
        numbers[1]=true;

        for (int i = 1; i <= r; i++) {
            int curSqrt = (int) Math.sqrt(i);
            if(i<l&&(numbers[i]||(i!=curSqrt*curSqrt))){
//                System.out.printf("Before l: %s\n", i);
                numberOfPrimesBeforeL++;
            }
            if (numbers[i]||(i!=curSqrt*curSqrt)) {
//                System.out.printf("All %s\n", i);
                ++numberOfPrimes;
            }
        }
//        System.out.println(numberOfPrimes-numberOfPrimesBeforeR-countEven);

        return numberOfPrimes-numberOfPrimesBeforeL;
    }

    public static int nonSpecialCountSpaceOptimization2(int l, int r) {
        int limit = (int) Math.sqrt(r);
        boolean[] numbers = new boolean[limit+1];
        Arrays.fill(numbers, true);
        numbers[1]=false;
        numbers[0]=false;

        for (int p = 2; p <= limit; ++p) {
            if (numbers[p]) {
                //Mark non special
                //4: Là special
                //  + Chia hết cho 1,2
                //9: Là special
                //  + Chia hết cho 1,3
                //
                //- Nếu viết ntn:
                //  + Check prime
                for (int j = p * p; j <= limit; j += p) {
                    //- Làm ntn để ignore case:
                    //- 81= 9*9 = 3*27
                    //==> Tính ra 3*27 ==> Nhận là non special luôn
                    //- Còn 9*9 ==> Chưa đủ nhận dạng vì:
                    //4=2*2 ==> Specials
//                    if(j!=p*p){
//                        numbers[j] = true;
//                    }else{
//                        System.out.printf("Ignore: %s\n", j);
//                    }
                    numbers[j]=false;
                }
            }
        }
//        int numberOfPrimes = 0;
//        int numberOfPrimesBeforeL = 0;
        int specialNum=0;

        for (int i = 2; i <=limit; i++) {
            if(numbers[i]){
                int square=i*i;
                if(square>=l&&square<=r){
                    specialNum++;
                }
            }
        }
//        for (int i = 1; i <= r; i++) {
//            int curSqrt = (int) Math.sqrt(i);
//            if(i<l&&(numbers[i]||(i!=curSqrt*curSqrt))){
////                System.out.printf("Before l: %s\n", i);
//                numberOfPrimesBeforeL++;
//            }
//            if (numbers[i]||(i!=curSqrt*curSqrt)) {
////                System.out.printf("All %s\n", i);
//                ++numberOfPrimes;
//            }
//        }
//        System.out.println(numberOfPrimes-numberOfPrimesBeforeR-countEven);

//        return numberOfPrimes-numberOfPrimesBeforeL;
        return r-l+1-specialNum;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given 2 positive integers l and r.
        // For any number x, all (positive divisors) of x (except x) are called the proper (divisors of x).
        //- A number is called special if it has exactly 2 proper divisors. For example:
        //  + The number 4 is special because it has proper divisors 1 and 2.
        //  + The number 6 is not special because it has proper divisors 1, 2, and 3.
        //* Return the count of numbers in the range [l, r] that are not special.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= l <= r <= 10^9
        //  + Range khá lớn
        //
        //** Brainstorm
        //- Tìm các số not special in range (Khá lớn)
        //- Special nếu có 2 ước duy nhất:
        //  + Chắc chắn là có thể có ước là 1
        //  + Số còn lại có thể là bất kỳ
        //  Ex:
        //  25 = 5*5
        //  35 = 5*7
        //
        //- Tìm số có exact 2 ntn:
        //  + Nếu loop --> Time complexity: O(1_000_000_000)
        //
        //5 -> 7
        //5: 1
        //
        //- Loại bỏ tất cả các số chẵn đi
        //  ==> >=2 ==> Chia chắc chắn sẽ là not special
        //-
//        int  l = 5, r = 7;
//        int l = 4, r = 16;
//        int l = 1, r = 2;
        //(4),5,(6),7,(8),9,(10),11,(12),13,(14),15,(16)
        //  + Bỏ 4 đi
        //  + 4,5,(6),7,(8),9,(10),11,(12),13,(14),15,(16)
        //
//        int l = 1, r = 4;
        //1,2,3,4
//        int l = 1, r = 1;
//        int l = 1, r = 2;
//        int l = 1, r = 3;
        //10086764
        //96508040
//        int l = 10086764, r = 96508040;
        int l = 3602634, r = 440140577;
        //
        //- Loại bỏ những số chẵn
        //- Loại bỏ những số lẻ:
        //  + Mà chia hết cho 2 số trở lên
        //      + Số lẻ chia hết cho 1 số --> Tức là ("KHÔNG PHẢI") prime.
        //
        //- Số non special:
        //  + Là số không phải prime là được.
        //==> Range - số lượng prime là được.
        //
        //- Bài này cần nhớ cơ chế đánh dấu (Dựa trên prime algorithm)?
        //  + i = 2 ==> tất cả các bội của 2 sẽ không phải prime
        //      + ==> 1 số (x) là prime khi nó cần chia hết cho (1 or x)
        //- Ta sẽ dùng nums[n+1]:
        //  + Để đánh dấu 1 số có phải prime hay không
        //
        //- numbers[i] == False:
        //  + Là prime ==> rs++
        //- numbers[i] == True:
        //  + Là non prime
        //  * Chắc chắn là nó phân tích được thành : i = a*b
        //  - 1 số phân tích thành:
        //      + i = x*x (2 số giống nhau) thì có thể phân tích thành:
        //      + i = a*b (được không)
        //      Ex:
        //      81 = 9 * 9
        //      81 = 3 * 27
        //      ==> TÁCH ĐƯỢC.
        //
        //      + even: rs++
        //      + odd: chia hết cho 2 số trở lên: rs++
        //      + odd: chia hết cho 2 số : rs is not changed.
        //- Ta có thể tạo 2 array:
        //  + 1 cái là prime
        //  + 1 cái là non special
        //==> Nếu dùng 2 cái thì sẽ bị memory limit
        //
        //- Nếu dùng 1 cái --> Vẫn bị memory limit như (nonSpecialCountSpaceOptimization)
        //  + Do số lượng phần tử (<=10^9) : Quá lớn nên sẽ bị (memory limit)
        //- Nên ta phải giảm số lượng phần tử lưu ở array (nonSpecialCountSpaceOptimization2)
        //  + Giảm từ O(r) --> O(sqrt(r))
        //- Nums[p] sẽ thể hiện việc special:
        //  + Vì p <= sqrt(r): Không đủ length
        //  ==> Nó sẽ cần 1 method để MAPPING.
        //
        //- Vì ta đang cần tìm special number trong khoảng (l, r):
        //- Nếu mình tư duy dựa trên tính chất primes --> Ok vẫn được nhưng bị memory limit
        //- Thử tư duy khác đi xem sao:
        //  + Là số sẽ chỉ có 1 dạng x=a*a
        //  + Không có dạng khác x = c*d
        //- Bản chất việc dùng primes[i] là để:
        //  + Tìm nhưng điểm không bh được traversed ==> Tức là không bh chia hết được cho cái gì
        //  + x=a*b ==> Ta sẽ range value của element ==> (1 -> sqrt(r)) để tìm số còn lại thôi.
        //
        //- Look back to this problem, ta sẽ gặp vấn đề tương tự nhưng chỉ là:
        //  + x = a*a ==> Nếu có 1 dạng khác x=c*d thì ta sẽ đánh nó false (Không phải special number)
        //- numbers[i] sẽ thể hiện cho việc đánh dấu bội số
        //  + Nếu numsbers[i]==true:
        //      + Tức là (i) chưa là bội số của số nào ==> không thể phân tích thành (i == a*b)
        //          + Nếu phân tich được thì các bội số của (i) chắc chắn không phải special: vì nó chia hết cho (i or a or b)
        //      + Lúc này ta sẽ đánh dấu các bội số của (i)==true && (i*k<=l):
        //          + Là không phải special luôn.
        //** Point chính ở đây là:
        //  + Tìm các số primes mà : x*x<=r
        //      + Vì ta đang cần tìm số special chỉ chia hết cho 2 số:
        //          + 2 số đó là 1 và sqrt(x) (Sqrt của chính nó)
        //- Sau đó ta chỉ còn các số prime thôi:
        //- Giả sử ta range cho a (1 -> sqrt(x))
        //  + Thì với a --> Ta chỉ cần check a*a
        //  + Nếu l<=a*a<=r: ==> Ta sẽ nhận là special number
        //
        //** KINH NGHIỆM:
        //- Bài này thuộc dạng classic cho việc tìm prime
        //
        //1.1, Optimization
        //
        //
        //1.2, Complexity
        //- Space: O(sqrt(n))
        //- Time: O(n+n*loglog(n))
        //
//        System.out.println(nonSpecialCount(l, r));
//        System.out.println(nonSpecialCountWrong1(l, r));
//        System.out.println(nonSpecialCount(l, r));
//        System.out.println(countPrimes(r));
        System.out.println(nonSpecialCountSpaceOptimization(l, r));
        System.out.println(nonSpecialCountSpaceOptimization2(l, r));
    }
}
