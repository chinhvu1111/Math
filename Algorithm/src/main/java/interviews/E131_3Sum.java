package interviews;

import java.util.*;

public class E131_3Sum {

    public static List<List<Integer>> threeSumConfuse(int[] nums) {
        int n = nums.length;
        List<List<Integer>> rs = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 1; i < n - 1; i++) {
            int currentValue = nums[i];

            if (hashSet.contains(currentValue)) {
                continue;
            }
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int valueSum = nums[left] + nums[right];

                if (valueSum > 0) {
                    if (valueSum * -1 < currentValue) {
                        right--;
                    } else if (valueSum * -1 > currentValue) {
                        left++;
                    } else {
                        List<Integer> currentRs = new ArrayList<>();
                        currentRs.add(nums[left]);
                        currentRs.add(nums[right]);
                        currentRs.add(currentValue);
                        hashSet.add(nums[left]);
                        hashSet.add(nums[right]);
                        System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
                        rs.add(currentRs);
                        break;
                    }
                } else if(valueSum < 0){
                    if (valueSum * -1 < currentValue) {
                        right--;
                    } else if (valueSum * -1 > currentValue) {
                        left++;
                    } else {
                        List<Integer> currentRs = new ArrayList<>();
                        currentRs.add(nums[left]);
                        currentRs.add(nums[right]);
                        currentRs.add(currentValue);
                        hashSet.add(nums[left]);
                        hashSet.add(nums[right]);
                        System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
                        rs.add(currentRs);
                        break;
                    }
                }else{
                    if (valueSum < currentValue) {
                        left++;
                    } else if (valueSum  > currentValue) {
                        right--;
                    } else {
                        List<Integer> currentRs = new ArrayList<>();
                        currentRs.add(nums[left]);
                        currentRs.add(nums[right]);
                        currentRs.add(currentValue);
                        hashSet.add(nums[left]);
                        hashSet.add(nums[right]);
                        System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
                        rs.add(currentRs);
                        break;
                    }
                }
//                if(nums[left]+nums[right]>currentValue){
//                    right--;
//                }else if(nums[left]+nums[right]<currentValue){
//                    left++;
//                }else{
//                    List<Integer> currentRs=new ArrayList<>();
//                    currentRs.add(nums[left]);
//                    currentRs.add(nums[right]);
//                    currentRs.add(currentValue);
//                    hashSet.add(nums[left]);
//                    hashSet.add(nums[right]);
//                    System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
//                    rs.add(currentRs);
//                    break;
//                }
            }
            hashSet.add(nums[i]);
        }
        return rs;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> rs = new ArrayList<>();
        //Ban đầu dùng int --> Thiếu case (-1),(-1) cạnh nhau.
        HashSet<String> hashSet = new HashSet<>();
        Arrays.sort(nums);
        Arrays.stream(nums).forEach((i)-> System.out.printf("%s,", i));
        System.out.println();

        for (int i = 1; i < n - 1; i++) {
            int currentValue = nums[i];
            int left = 0;
            int right = n - 1;

            while (left<i&&i<right) {
                int valueSum = nums[left] + nums[right];

                if(valueSum<=0){
                    if(currentValue>=0){
                        if(valueSum*-1>currentValue){
//                            right--;
                            left++;
                        }else if(valueSum*-1<currentValue){
//                            left++;
                            right--;
                        }else {
                            if(!hashSet.contains(nums[left]+","+currentValue+nums[right])){
                                List<Integer> currentRs = new ArrayList<>();
                                currentRs.add(nums[left]);
                                currentRs.add(currentValue);
                                currentRs.add(nums[right]);
                                System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
                                rs.add(currentRs);
                                hashSet.add(nums[left]+","+currentValue+nums[right]);
                            }
//                            hashSet.add(nums[left]);
//                            hashSet.add(nums[right]);
                            left++;
                            right--;
//                            break;
                        }
                    }else{
                        if(valueSum*-1>currentValue){
                            left++;
                        }else if(valueSum*-1<currentValue){
                            right--;
                        }else{
                            if(!hashSet.contains(nums[left]+","+currentValue+nums[right])){
                                List<Integer> currentRs = new ArrayList<>();
                                currentRs.add(nums[left]);
                                currentRs.add(currentValue);
                                currentRs.add(nums[right]);
                                System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
                                rs.add(currentRs);
                                hashSet.add(nums[left]+","+currentValue+nums[right]);
                            }
//                            hashSet.add(nums[left]);
//                            hashSet.add(nums[right]);
                            left++;
                            right--;
//                            break;
                        }
                    }
                }else{
                    if(currentValue<=0){
                        if(valueSum*-1>currentValue){
                            left++;
                        }else if(valueSum*-1<currentValue){
                            right--;
                        }else{
                            if(!hashSet.contains(nums[left]+","+currentValue+nums[right])){
                                List<Integer> currentRs = new ArrayList<>();
                                currentRs.add(nums[left]);
                                currentRs.add(currentValue);
                                currentRs.add(nums[right]);
                                System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
                                rs.add(currentRs);
                                hashSet.add(nums[left]+","+currentValue+nums[right]);
                            }
//                            hashSet.add(nums[left]);
//                            hashSet.add(nums[right]);
                            left++;
                            right--;
//                            break;
                        }
                    }else{
                        //(1) * -1 < 1 (Luôn nhỏ hơn)
//                        System.out.printf("");
                        right--;
                    }
                }
            }
        }
        return rs;
    }

    public static List<List<Integer>> threeSumCutCondition(int[] nums) {
        int n = nums.length;
        List<List<Integer>> rs = new ArrayList<>();
        //Ban đầu dùng int --> Thiếu case (-1),(-1) cạnh nhau.
        HashSet<String> hashSet = new HashSet<>();
        Arrays.sort(nums);
        Arrays.stream(nums).forEach((i)-> System.out.printf("%s,", i));
        System.out.println();

        for (int i = 1; i < n - 1; i++) {
            int currentValue = nums[i];
            int left = 0;
            int right = n - 1;

            while (left<i&&i<right) {
                int valueSum = nums[left] + nums[right];

                if(valueSum<=0){
                    if(currentValue>=0){
                        if(valueSum*-1>currentValue){
//                            right--;
                            left++;
                        }else if(valueSum*-1<currentValue){
//                            left++;
                            right--;
                        }else {
                            if(!hashSet.contains(nums[left]+","+currentValue+nums[right])){
                                List<Integer> currentRs = new ArrayList<>();
                                currentRs.add(nums[left]);
                                currentRs.add(currentValue);
                                currentRs.add(nums[right]);
                                System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
                                rs.add(currentRs);
                                hashSet.add(nums[left]+","+currentValue+nums[right]);
                            }
//                            hashSet.add(nums[left]);
//                            hashSet.add(nums[right]);
                            left++;
                            right--;
//                            break;
                        }
                    }else{
                        left++;
                    }
                }else{
                    if(currentValue<=0){
                        if(valueSum*-1>currentValue){
                            left++;
                        }else if(valueSum*-1<currentValue){
                            right--;
                        }else{
                            if(!hashSet.contains(nums[left]+","+currentValue+nums[right])){
                                List<Integer> currentRs = new ArrayList<>();
                                currentRs.add(nums[left]);
                                currentRs.add(currentValue);
                                currentRs.add(nums[right]);
                                System.out.printf("%s %s %s, ", nums[left], currentValue, nums[right]);
                                rs.add(currentRs);
                                hashSet.add(nums[left]+","+currentValue+nums[right]);
                            }
//                            hashSet.add(nums[left]);
//                            hashSet.add(nums[right]);
                            left++;
                            right--;
//                            break;
                        }
                    }else{
                        //(1) * -1 < 1 (Luôn nhỏ hơn)
//                        System.out.printf("");
                        right--;
                    }
                }
            }
        }
        return rs;
    }

    public static List<List<Integer>> threeSumReferCustom(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length<3){
            return result;
        }
        int n=nums.length-1;
        System.out.println("\n=================");
        HashSet<String> value=new HashSet<>();
        Arrays.sort(nums);

        for(int i=0;i<n-1;i++){
            if(nums[i]>0){
                break;
            }
            int j=i+1;
            int k=nums.length-1;

            while (j<k){
              int currentSum=nums[i]+nums[k];
                if(currentSum==-1*nums[j]&&!value.contains(nums[i]+"_"+nums[j]+"_"+nums[k])){
//                    System.out.printf("%s %s %s\n", nums[i], nums[j], nums[k]);
                    value.add(nums[i]+"_"+nums[j]+"_"+nums[k]);
                    List<Integer> currentRs = new ArrayList<>();
                    currentRs.add(nums[i]);
                    currentRs.add(nums[j]);
                    currentRs.add(nums[k]);
                    result.add(currentRs);
                }
//              System.out.printf("%s(%s) %s(%s) %s(%s) %s\n", i, nums[i], j, nums[j], k, nums[k], currentSum);

              if(nums[j]>0){
                  if(currentSum<0&&currentSum>-1*nums[j]){
                      k--;
                  }else if(currentSum<0&&currentSum<-1*nums[j]){
                      j++;
                  }else{
                      k--;
//                      j++;
                  }
              }else{
                  if(currentSum>=0&&currentSum>-1*nums[j]){
                      k--;
                  }else if(currentSum>=0&&currentSum<-1*nums[j]){
                      j++;
                  }else{
//                      k--;
                      j++;
                  }
              }
//                System.out.printf("==%s(%s) %s(%s) %s(%s) %s\n", i, nums[i], j, nums[j], k, nums[k], currentSum);
//              System.out.printf("%s %s %s\n", i, j, k);
            }

        }
        return result;
    }

    public static List<List<Integer>> threeSumReferRefactor(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length<3){
            return result;
        }
        int n=nums.length-1;
        Arrays.sort(nums);

        for(int i=0;i<n-1;i++){
            if((i>0&&nums[i]==nums[i-1])){
                continue;
            }
            if(nums[i]>0){
                break;
            }
            int j=i+1;
            int k=nums.length-1;

            while (j<k){
                int currentSum=nums[i]+nums[k];
                if(currentSum==-1*nums[j]){
                    List<Integer> currentRs = new ArrayList<>();
                    currentRs.add(nums[i]);
                    currentRs.add(nums[j]);
                    currentRs.add(nums[k]);
                    result.add(currentRs);
                }

                if(currentSum>-1*nums[j]){
                    do{
                        k--;
                    }while (j<k&&nums[k]==nums[k+1]);
                }else if(currentSum<-1*nums[j]){
                    do{
                        j++;
                    }while (j<k&&nums[j]==nums[j-1]);
                }else if(nums[j]>0){
                    do{
                        k--;
                    }while (j<k&&nums[k]==nums[k+1]);
                }else{
                    do{
                        j++;
                    }while (j<k&&nums[j]==nums[j-1]);
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        //Case 1: Case này bị thiếu trường hợp (-1, -1, 2)
        //--> Vì lúc nào cũng push(nums[i]) --> (-1) đã được push thì sau đó (-1) tiếp sẽ bị bỏ qua.
        //int arr[] = new int[]{-1, 0, 1, 2, -1, -4};
        // {-4, (-1), -1, 0, (1), 2}
        //
        //Case 2 : Case này liên quan đến tổng (left value + right value = 0)
        //int arr[] = new int[]{1,2,-2,-1};
        //int arr[] = new int[]{3,-2,1,0};
        //-3,0,1,2
        //
        //Case 3: Case này sai do chưa add (mid value)
        //int arr[]=new int[]{0,0,0};
        //int[] arr =new int[]{3,0,-2,-1,1,2};
        //{-2,-1,(0),1,2,3}
        //Output: [[-2,3,-1],[-2,2,0]]
        //Expected: [[-2,-1,3],[-2,0,2],[-1,0,1]]
        //
        //Case 3.1: Ngoài ra ta chỉ add mid value --> Vì nếu add (left value, right value)
        //==> Miss rất nhiều case (left/ right) là mid
        //Case 3.2: phải có thêm điều kiện (left<i<right) ==> nếu không thì (i) sẽ không phải là (mid) nữa
        //==> Tư duy sẽ bị sai.
        //
        //Case 4:
        //int[] arr =new int[]{3,0,-2,-1,1,2};
        //Output: [[-3,-1,4],[-2,-1,3]
        //Expect: [-3,-1,4], [-2,-1,3], [-1,-1,2]
        //==> Thiếu case (-1) ở giữa ==> (-1), (-1)
        //==> (-1) kết tiếp nên được xét tiếp
        //Problems : add mid value sẽ bị thiếu cases.
        //** Solution : Chuyển từ hashSet(mid) --> hashSet(String) : (valueLeft, currentValue, valueRight)
//        int[] arr =new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4};
//        int[] arr =new int[]{-1,0,1,2};
//        int[] arr =new int[]{-4,-1,1,2,3};
        int[] arr =new int[]{-1,0,1,2,-1,-4};
        //threeSumConfuse(arr);
        threeSum(arr);
        //** Đề bài :
        //- Tìm các array thuộc nums[] cho trước sao cho:
        //+ nums[i] + nums[j] + nums[k] == 0.
        //
        //** Bài này tư duy như sau:
        //Cách 1 : Brute force
        //1,
        //1.1, Tư duy khởi nguyên là dùng phương pháp 2 pointers:
        //- Ta đang tìm (a,b,c) sao cho a+c= -b
        //==> Ta chia ra tìm theo nhiều b thuộc (nums[i], i : 0...n-1)
        //1.2, Với mỗi b (nums[i]) ==> Ta sẽ tìm 2 phần tử (a(left),c(right))
        //===> Bằng phương pháp two pointers
        //1.3, Complexity of algorithm : O(n^2)
        //1.4, Nhưng bài nhiều cases như thế này cần phải nghĩ (Thật kỹ trước khi làm)
        //Các trường hợp phải nhớ như sau:
        //+
        //1.2 Vì logic của bài này không được quá rõ ràng:
        //- (leftVal + rightVal) * -1 = midVal
        //==> Ta cần chia cases ra 1 cách đầy đủ thì mới --> Đúng được.
        //
        //1.3, Danh sách các cases sau khi chia ra:
        //- Chú ý :
        //+ Để cover hết được các cases, các điều kiên phải bao gồm : (>0, <=0) / (>0, <=0)
        //
        //- valueSum<=0 && mid_value >=0
        //+ && valueSum *-1 > mid_value (4>3)<=>(valueSum=-4, mid =3) : left ++ (Vì ta cần tăng -4 ==> -3 (Vì ta cần so sánh vs 3
        // , left cần tăng) ==> Vì -4 < -3 (Tăng) ** (TƯ DUY CHỌN MỐC CUỐI CÙNG ĐỂ CHECK HƯỚNG)
        //+ && valueSum *-1 < mid_value (4<5)<=>(valueSum=-4, mid =5) : right--
        //VD: valueSum=0, mid_value=2
        //--> left++,
        //
        //- valueSum<=0 && mid_value <0
        //+ && valueSum *-1 > mid_value (4>-3) : left++ (-4 --> 3) (Tăng)
        //+ && valueSum *-1 < mid_value  : right-- ==> Không xảy ra case này (Do cùng âm (-))
        //==> Chỉ -0 mới thỏa mãn
        //
        //- valueSum>0 && value >=0
        //+ && valueSum *-1 > mid_value (4 > 3) : right--
        //+ && valueSum *-1 < mid_value (2 < 3) : left++
        //==> Không xảy ra case này do cùng dương (+).
        //==> Chỉ -0 mới thỏa mãn
        //==> Tuy là không xảy ra nhưng vẫn cần phải thay đổi (left, right)
        //
        //- valueSum>0 && mid_value <=0
        //+ && valueSum *-1 > mid_value (-3 > -4) : left++
        //+ && valueSum *-1 < mid_value (-3 < -2) : right--
        //
        //** KINH NGHIỆM TƯ DUY SO SÁNH KHOẢNG CÁCH:
        //- leftVal + rightVal = sumVal
        //VD: sumVal =-4, so sánh sumVal*-1 <> 3
        //--> cái ta cần là -4 --> -3 ===> Để bằng 3
        //* Tư duy chọn mốc cuối cùng (CHIỀU HƯỚNG TĂNG/GIẢM)
        //==> (Để xem nó tăng hay giảm) ==> (-4) --> (-3) (Muốn biến thành -3) ==> TĂNG.
        //
        //- Cut off đi các (điều kiện thừa thãi/ các điều kiện không thể xảy ra)
        threeSumCutCondition(arr);
        //Cách 2:
        //2,
        //2.0, Ta thấy a>0 ==> Sẽ không bh tìm được kết quả phù hợp --> Số đằng trước >0 ==> a>0 ==> Sai (BREAK)
        //2.1, Ta có biếu thức quy luật như sau:
        //+ a + b + c=0
        //+ a<=b<=c
        //a + c = -b
        //- a,b,c <0 ==> Không thể xảy ra vì a+c<-b
        //- a,b,c >0 ==> Không thể xảy ra vì a+b>0 !=-c
        //- a<0, b,c>0
        //VD: -7, 3, 4 ==> OK.
        //- a<0, b<0, c>0
        //VD: -4,-3, 7 ==> OK.
        //
        //2.2, Nếu ta không xét ở giữa nữa, quay ra xét a
        //- a chỉ có 1 case duy nhất là <0 ==> Sẽ bỏ qua khi a>=0
        //==> Mà array tăng dần nên ta sẽ loại bỏ được rất nhiều cases phía sau.
        //
        //2.3, Quy luật chạy biến
        //- i,j,k : (i) sẽ cố định
        //- Ta chỉ có 2 cases duy nhất là:
        //+ a<0, b,c>0
        //+ a<0, b<0, c>0
        //- Ta có các cases như sau:
        //** Với case 1: (a<0, b,c>0)
        //
        //- a + c >=0 <=> -b ==> Khác dấu
        //+ sum > -b (Do sum >0, b >0 (-b<0) ==> Case này sẽ là không thể.
        //
        //- a + c <0 <=> -b ==> Cùng dấu
        //
        //+ sum > -b
        //VD:
        //+ sum=-4
        //+ -b = -5
        //--> sum > -b ==> (-4 ==> -5) / ( -5 ==> -4)
        //+ Với (b) -5 ==> -4 : b phải giảm từ 5 --> 4 (Không được vì từ j left sang trường hợp đó ta đã xét rồi mà không thảo mãn)
        //+ Với (a+c) -4 ==> -5 ==> Giảm dần mà cố định a ==> c giảm ==> (k--).
        //
        //+ sum <-b
        //VD:
        //+ sum=-5
        //+ -b = -4
        //--> sum --> -b ==> (-5 ==> -4) / (-4 ==> -5)
        //+ Với (b) -4 ==> -5 : b phải tăng từ 4 --> 5 (j++)
        //+ Với (a+c) -5 ==> -4 ==> Mà c>0 --> c tăng ==> Không được (Vì từ ngoài đã xét rồi mà không thoả mãn).
        //
        //** Với case 2: (a<0, b<0, c>0)
        //
        //- a + c >=0 <=> -b dương (b<0) ==> Cùng dấu (Có thể xảy ra)
        //+ sum >= -b
        //VD:
        //sum=3
        //b=-2 ==> -b=2
        //
        //--> Sum --> -b ==> (3 ==> 2) / (2 ==> 3)
        //+ Với (b) (2 ==> 3) --> (-2 --> -3) ==> b giảm không được.
        //+ Với (a+c) (3 ==> 2) ==> c giảm ==> k--.
        //
        //+ sum < -b
        //VD:
        //sum=3
        //b=-4 ==> -b=4
        //
        //--> Sum --> -b ==> (3 ==> 4) / (4 ==> 3)
        //+ Với (b) (4 ==> 3) --> (-4 --> -3) ==> b tăng ==> j++.
        //+ Với (a+c) (3 ==> 4) ==> c tăng ==> Không thể xảy ra.
        //
        //- a + c <0 <=> -b dương (b<0) ==> Khác dấu (Không thể xảy ra)
        //VD: -7, -3, 2
        //
        //2.4, Các case ở biên/ đặc biệt:
        //
        //2.5, Tối ưu bằng cách reduce array trước khi xử lý:
        //- Tuy nhiên sẽ bị dính case (-1 -1 2) ==> Bỏ thành -1 sẽ lỗi ngay
        //- Với nums[j]==0 : Thì cần phải xem với case currentSum>=0/ currentSum<=0 ==> Mới có ý nghĩ nếu để nhầm sẽ bị miss cases
        //- Với case mà num[j]>0 (-b <0) và currentSum>0 ==> về cơ bản thì currentSum sẽ cần giảm để có thể âm (<0) ==> k--;
        //==> Nó là 1 cases else ở cuối cần chú ý
        //** Solution:
        //- nums[i]==nums[i-1] continue;
        //---> Mố mà ta đã xét trước đó thì có thể bỏ qua.
        //
        //2.6,Refactor:
        //- Sau khi gộp các điều kiện ta có công thức tổng quan như sau:
        //+ currentSum>-1*nums[j] : k--
        //+ currentSum<-1*nums[j] : j++
        //VD:
        //-4,-2(j),[-4],5,6,8
        //[-2+8]=6 >4 [-(-4)]
        //j++ ==> Không được vì *-1 (Tỉ lệ ngịch) ==> k--
        //- a*(-1) : a càng tăng thì (a*-1) càng giảm
        //--> Luôn là vậy bất kể (a>0/a<=0)
        //
        //** Kinh nghiệm làm bài dạng này :
        //- Thử 2,3 case để xác định là được.
        //- Sau đó thử với trường hợp == + nums[j] (>0 /<=0)
        //2.7, Để tránh duplicate thì khi k--/j++ ==> Các giá trị cạnh mà bằng nhau thì ta bỏ qua luôn.
        //2.8, Complexity
        //- Time complexity : O(N^2)
        //- Space complexity : ???
        //# Reference
        //16. 3Sum Closest
        //18. 4Sum
        //259. 3Sum Smaller
        int[] arr1 =new int[]{-1,0,1,2,-1,-4};
        List result= threeSumReferCustom(arr1);
        List result1= threeSumReferRefactor(arr1);
        System.out.println(result);
        System.out.println(result1);
    }
}
