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
        int[] arr =new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4};
        //threeSumConfuse(arr);
        threeSum(arr);
        //Bài này tư duy như sau:
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
    }
}
