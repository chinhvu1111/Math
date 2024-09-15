package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.List;

public class E171_NumberOfPeopleAwareOfASecret {

    public static class SecretPersonInfo{
        int delay;
        int forget;
        public  SecretPersonInfo(int delay, int forget){
            this.delay=delay;
            this.forget=forget;
        }
    }

//    public static int peopleAwareOfSecret(int n, int delay, int forget) {
//        List<List<SecretPersonInfo>> info=new ArrayList<>();
//        List<SecretPersonInfo> curList=new ArrayList<>();
//        curList.add(new SecretPersonInfo(delay, forget));
//        info.add(curList);
//        int numValidPeople=0;
//
//        for(int i=1;i<n;i++){
//            List<SecretPersonInfo> prevList=info.get(i-1);
//            List<SecretPersonInfo> nextList= new ArrayList<>();
//            for(SecretPersonInfo person: prevList){
//                //Forget
//                if(person.forget==1){
//                    continue;
//                }
//                if(person.delay<=1){
//                    numValidPeople++;
//                }
//                nextList.add(new SecretPersonInfo(person.delay-1, person.forget-1));
//            }
//            prevList=nextList;
//        }
//        return 1;
//    }

    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        int mod = 1_000_000_007;
        long[] info=new long[n+1];
        //- Day 1
        info[1]=1;
//        int[] prevInfo=null;
        //infor[1][x] ==> lấy
        long rs=0;

        for(int i=2;i<n;i++){
            //Check all days:
            //  + Thằng nào delays được > delays day rồi
            //  + Next < forget day thì ta lấy ==> Đi share x people khác
            long curSum=0;
            for(int j=delay;j<forget;j++){
                curSum = (curSum + info[j])%mod;
            }
//            rs=(rs+curSum)%mod;
            //remove forget people
//            long minus=0;
            for (int j=forget;j<=n;j++){
//                rs=rs-info[j];
                info[j]=0;
            }
//            System.out.printf("On %s day, Share with %s people\n", i, rs);
            //- Khi next day
            //  + Tất cả các thằng
            //      + days x -shift-> days (x+1)
//            System.out.println("======= current");
//            for (int j = 0; j <= n; j++) {
//                System.out.printf("i: %s, val: %s\n", j, info[j]);
//            }
            //Ex:
            //[1,2,3,5,6]
            //[0,1,2,3,5]
            for(int j=n;j>=1;j--){
                info[j]=info[j-1];
            }
            info[1]=0;
            info[1]=(info[1]+curSum)%mod;
//            System.out.println("======= new");
//            for (int j = 0; j <= n; j++) {
//                System.out.printf("i: %s, val: %s\n", j, info[j]);
//            }
        }
        long nextVal=0;
        for(int i=1;i<forget;i++){
            if(i>=delay){
                nextVal=(nextVal+info[i])%mod;
            }
            rs=(rs+info[i])%mod;
        }
        rs=(rs+nextVal)%mod;
        return (int) rs;
    }

    public static int peopleAwareOfSecretSlideWindow(int n, int delay, int forget) {
        int mod = 1_000_000_007;
        long[] dp=new long[n+1];
        //- Day 1
        dp[1]=1;
//        int[] prevInfo=null;
        //infor[1][x] ==> lấy
        long rs=0;
        long numPeople=0;

        for(int i=2;i<n;i++){
            long numNewPeople = dp[Math.max(i-delay, 0)];
            long numPeopleForget = dp[Math.max(i-forget, 0)];
//            long numPeople = numNewPeople - numPeopleForget;
            numPeople = (numPeople + (numNewPeople - numPeopleForget) + mod)%mod;
            dp[i]=numPeople;
        }
        for(int i=n-forget+1;i<=n;i++) {
            rs = (rs + dp[i]) % mod;
        }
        return (int) rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- On (day 1), one person (discovers a secret).
        //- You are given (an integer delay), which means that (each person) will share the secret with
        // (a new person) every day, starting from (delay days) after (discovering the secret).
        //- You are also given (an integer forget), which means that (each person) will forget (the secret forget days)
        // (after discovering it).
        //  + Tức là có khoảng thời gian delay days trước khi share với new person
        //  + Có khoảng thời gian forget days sau khi person có thông tin.
        //- A person cannot share (the secret) on (the same day) they forgot it, or on (any day) afterwards.
        //  + 1 người không thể share the secret cùng 1 ngày hoặc các ngày say đó khi mà nó đã forgot.
        //- Given an integer n,
        //* Return (the number of people) who know the secret (at the end of day n).
        //+ Since the answer may be very large, return it modulo 10^9 + 7.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1: Dynamic programming
        //- Constraint:
        //2 <= n <= 1000
        //1 <= delay < forget <= n
        //  + n không quá lớn: Time: O(n^2)
        //  + delay>1:
        //      + Tức là phải biết ít nhất 1 ngày (Ngày đầu tiên) mới được share.
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: n = 6, delay = 2, forget = 4
        //Output: 5
        //Explanation:
        //Day 1: Suppose the first person is named A. (1 person)
        //Day 2: A is the only person who knows the secret. (1 person)
        //Day 3: A shares the secret with a new person, B. (2 people)
        //Day 4: A shares the secret with a new person, C. (3 people)
        //Day 5: A forgets the secret, and B shares the secret with a new person, D. (3 people)
        //Day 6: B shares the secret with E, and C shares the secret with F. (5 people)
        //
        //- 1 lúc có thể có nhiều people --> Share secret with other people
        //- Mỗi person sẽ có mốc delay/forget riêng
        //- On the first day, 1 người sẽ biết bí mật
        //  + Delay days sẽ tính cả ngày đó
        //  ==> Sau delays days mới được share the secret.
        //- Số lượng người có thể share không giới hạn
        //- Người nào forget:
        //  + Số lượng người biết tin = k - 1
        //- Rule:
        //+ A share with B
        //+ A,B share with C,D
        //+ A,B,C,D share with E,F,G,H
        //- Tức là mỗi ta có thể dp[i] cho mỗi day
        //Ex:
        //- On x day, A delay 3 days, B forget after 2 days, C share được:
        //  + Với kết quả ntn thì nó sẽ ảnh hưởng đến các (next days)
        //- Với mỗi days ta sẽ lưu:
        //  + Thông tin của mỗi person ta có: (delay, forget) days.
        //- Kết quả thì mỗi lần:
        //  + rs+= the number of people can share
        //- Sau khi lấy kết quả:
        //  + Ta cần update sự ảnh hưởng việc next day lên các people:
        //
        //- Số lượng người có thể rất lớn:
        //  + >=10^9+7
        //  + Ta không thể lưu all thông tin của từng person vào list được.
        //
        //- Xét vi trí (i):
        //  + số lượng valid tại (i) sẽ được tính theo (i-1):
        //      + Không delay
        //      + Chưa forget
        //- Người mới được shared vào thì:
        //  + Delay = original delay
        //  + Forget = original forget
        //
        //- dp[i][delay][forget] là được:
        //  - Mỗi lần i sẽ loop để trừ đi là được?
//        int n = 6, delay = 2, forget = 4;
        int n = 289, delay = 7, forget = 23;
        System.out.println(peopleAwareOfSecret(n, delay, forget));
//        int[] arr=new int[]{1,2,3,4,5};
//        for(int j=arr.length-1;j>=1;j--){
//            arr[j]=arr[j-1];
//        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.printf("%s, ", arr[i]);
//        }
//        System.out.println();
        //
        //- Case liên quan đến forget:
        //  + <forget mới lấy:
        //      + Không cho việc share + forget cùng ngày được.
        //
        //** Kinh nghiệm làm mấy bài dạng mod kiểu này:
        //  + Không nên tính subtraction
        //      + Hiệu 2 mod ==> Nó sẽ có thể return x<0 mặc dù:
        //          + y>x ==> (y%mod)-(x%mod)<0 vì:
        //              + y sẽ lớn dạng cycle ==> Quay lại 0 sớm hơn x
        //              ==> Có thể có case này
        //- Nên tính result lúc cuối cùng:
        //  + Không nên tính rs luôn.
        //
        //1.1, Optimization
        //- Bỏ đoạn reset các day>=forget
        //1.2, Complexity
        //+ N is the number of days
        //- Space: O(n)
        //- Time: O(n*n)
        //
        //
        //2.0, Sliding window
        //* Method-2:
        //- Cái này tư duy 1 phần giống bên trên:
        //
        //- CT:
        //  + i --> i + delay (Acting as Spreader)
        //  + i --> i + forget (Forgotting the Secret)
        //  + i --> dp[i - delay] (noOfnewPeopleSharingSecretOnIthDay)
        //      + means the number of people who found the secret on ith day
        //  + i --> dp[i - forget] (noOfPeopleForgetSecretOnIthDay)
        //- noOfPeopleSharingSecret = (noOfPeopleSharingSecret + noOfnewPeopleSharingSecretOnIthDay - noOfPeopleForgetSecretOnIthDay)
        //
        //- dp[i]:
        //  + Số lượng người lấy thêm vào ngày thứ (i)
        //1,2,3,[4],5,6
        //i=3
        //- Dùng slide window, vào ngày thứ (i) thì:
        //==> Lấy thêm ngày thứ (i)
        //  + i-delay: chính là ngày mà ta có thể lấy thêm vì:
        //      + shift 1 day sang right
        //  + Tăng i tức là cần loại bỏ số người sẽ forget vào ngày thứ (i)
        //      + Tức là những người đã được add vào (i-forget)
        //  ==> Do các ngày san sát liên tục nhau:
        //      + Lúc trừ đi ta đã trừ đi các ngày trước rồi:
        //      ==> Không cần tính lại nữa.
        //
        //** Kinh nghiệm:
        //- Tư duy slide window:
        //  + Xét đến vị trí (i) cần:
        //      + Thêm gì
        //          + Thêm thì thêm all
        //      + Bỏ gì
        //          + Bỏ thì thêm all
        //      Ex:
        //      + Delay và forget thì (add + delete) ntn
        //
        //2.1, Optimization
        //2.2, Complexity:
        //- N is the number of days
        //- Space: O(n)
        //- Time: O(n)
        //
        System.out.println(peopleAwareOfSecretSlideWindow(n, delay, forget));
        //
        //#Reference:
        //2430. Maximum Deletions on a String
        //2712. Minimum Cost to Make All Characters Equal
        //2945. Find Maximum Non-decreasing Array Length
    }
}
