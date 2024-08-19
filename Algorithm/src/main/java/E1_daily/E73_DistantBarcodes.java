package E1_daily;

import java.util.*;

public class E73_DistantBarcodes {

    public static int[] rearrangeBarcodesWrong(int[] barcodes) {
        //Space: O(n)
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int n=barcodes.length;

        for(int e: barcodes){
            mapCount.put(e, mapCount.getOrDefault(e, 0)+1);
        }
        //Space: O(n)
        PriorityQueue<int[]> sortCount=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
//        System.out.println(mapCount);

        //Time: O(n*log(n))
        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            sortCount.add(new int[]{e.getKey(), e.getValue()});
        }
        //Space: O(n)
        int[] rs=new int[n];
        int index=0;
        if(sortCount.isEmpty()){
            return rs;
        }
        int[] prevElement=sortCount.poll();

        if(sortCount.isEmpty()){
            rs[index]=prevElement[0];
        }
        boolean isRemaining=true;
        //Time: O(n*log(n))
        while(!sortCount.isEmpty()){
            int[] curElement = sortCount.poll();
            int curAddLen = Math.min(curElement[1], prevElement[1]);
//            System.out.printf("curAddLen: %s, index: %s\n", curAddLen, index);
//            System.out.printf("prevElement: %s, count: %s\n", prevElement[0], prevElement[1]);
//            System.out.printf("curElement: %s, count: %s\n", curElement[0], curElement[1]);

            for(int i=0;i<curAddLen;i++){
//                System.out.printf("%s index=%s\n", rs[index], index);
                rs[index]=prevElement[0];
                rs[++index]=curElement[0];
                index++;
            }
            if(curElement[1]>prevElement[1]){
                sortCount.add(new int[]{curElement[0], curElement[1]-prevElement[1]});
                prevElement = sortCount.poll();
            }else if(curElement[1]<prevElement[1]){
                sortCount.add(new int[]{prevElement[0], prevElement[1]-curElement[1]});
                prevElement = sortCount.poll();
            }else if(!sortCount.isEmpty()){
                prevElement=sortCount.poll();
            }else{
                isRemaining=false;
                break;
            }
        }
        if(isRemaining){
            rs[index]=prevElement[0];
        }
        //3(5),2(3)
        //==> 3,(2),3,(2),3,(2)
        //+ (5-3) ==> 3(2)
        for (int i = 0; i < n; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        return rs;
    }

    public static int[] rearrangeBarcodes(int[] barcodes) {
        //Space: O(n)
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int n=barcodes.length;

        for(int e: barcodes){
            mapCount.put(e, mapCount.getOrDefault(e, 0)+1);
        }
        //Space: O(n)
        //Time: O(n*log(n))
        PriorityQueue<int[]> sortCount=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
//        System.out.println(mapCount);

        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            sortCount.add(new int[]{e.getKey(), e.getValue()});
        }
        //Space: O(n)
        //Time: O(n)
        int[] rs=new int[n];
        int index=0;
        if(sortCount.isEmpty()){
            return rs;
        }
        int[] prevElement=sortCount.poll();

        if(sortCount.isEmpty()){
            rs[index]=prevElement[0];
        }
        boolean isRemaining=true;

        //Time: O(n*log(n))
        while(!sortCount.isEmpty()){
            int[] curElement = sortCount.poll();
            rs[index] = prevElement[0];
            rs[++index] = curElement[0];
            if(prevElement[1]>1){
                sortCount.add(new int[]{prevElement[0], prevElement[1]-1});
            }
            if(curElement[1]>1){
                sortCount.add(new int[]{curElement[0], curElement[1]-1});
            }
            if(sortCount.isEmpty()){
                isRemaining=false;
                break;
            }
            prevElement=sortCount.poll();
            index++;
        }
        if(isRemaining){
            rs[index]=prevElement[0];
        }
        //3(5),2(3)
        //==> 3,(2),3,(2),3,(2)
        //+ (5-3) ==> 3(2)
//        for (int i = 0; i < n; i++) {
//            System.out.printf("%s, ", rs[i]);
//        }
        return rs;
    }

    public static int[] rearrangeBarcodesRefer(int[] barcodes) {
        Map<Integer, Integer> cnt = new HashMap();
        for (int i : barcodes) cnt.put(i, cnt.getOrDefault(i, 0) + 1);

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(cnt.entrySet());
        Collections.sort(list, Map.Entry.<Integer, Integer>comparingByValue().reversed());
        int l = barcodes.length, i = 0;
        int[] res = new int[l];

        for (Map.Entry<Integer, Integer> e : list) {
            int count=e.getValue();

            while(count>0){
                count--;
                res[i]=e.getKey();
                i+=2;
                //Nếu đến biên thì không cần phải cách 1 unit
                if(i>=barcodes.length){
                    i=1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // Requirement
        //- In a warehouse, there is (a row of barcodes), where (the ith barcode) is barcodes[i].
        //* Rearrange the barcodes so that (no two adjacent barcodes) are equal.
        //- You may return (any answer), and it is guaranteed (an answer exists).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= barcodes.length <= 10000
        //1 <= barcodes[i] <= 10000
        //+ Length <= 10^4 không quá lớn
        //=> Time : O(n*k)
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: barcodes = [1,1,1,2,2,2]
        //Output: [2,1,2,1,2,1]
        //
        //- Không có 2 elements giống nhau
        //- Không brute force được do length quá lớn (10^4)
        //- Tư duy ngược lại:
        //  + Đặt sao cho các phần tử khác nhau ==> Không cạnh nhau là được
        //- Các element khác nhau nếu (đặt cạnh nhau or không đặt cạnh nhau) thì có ảnh hưởng đến kết quả không
        //Ex:
        //barcodes = [1,3,3,2]
        //rs = [1,(3),2,3]
        //rs = [1,(2),3,3]
        //  + Nếu đặt (2) trước ==> sau đó các phần tử giống nhau (3,3) / (vị trí còn lại 2) là bằng nhau ==> Không thể sắp xếp được
        //  + Nếu đặt (3) trước ==> thì số lượng các digits giống nhau không còn ==> Có thể sắp xếp bình thường
        //
        //- Khi nào thì không thể arrange được nữa?
        //  + Số lượng phần tử giống nhau ==> Chiếm >1/2 size còn lại??? ==> WRONG (Chưa đúng)
        //+ 2,3,2 ==> valid
        //  + rs = [2,3,2]
        //+ 2,3,2,2 ==> Invalid
        //  + rs = [2,3,(2,2)]
        //Ex:
        //barcodes = [1,3,3,2,2]
        //rs = [1,3,2,3,2]
        //
        //- Các phần tử nếu chia thành các unique values:
        //  + Vai trò của chúng là như nhau nếu (!= nhau)
        //- Ta sẽ ưu tiên các phần tử xuất hiện nhiều trước:
        //  + Sử dụng các phần tử khác để đan xen nó???
        //Ex:
        //barcodes = [1,3,3,3,3,3,2,2,2,5,6,7]
        //rs = [1,3,(2),3,(2),3,(2),3,5,3,6,7] ==> Valid
        //rs = [1,3,(5),3,(6),3,(7),3,2,3,2,2] ==> Invalid
        //==> Ta nên hướng việc sắp xếp xen kẽ nhưng element xuất hiện nhiều trước
        //  + Vì nếu chọn các phần tử khác nhau trước --> Về sau các phần tử giống nhau sẽ chưa được sử dụng
        //  ==> Nó có khả năng cao là invalid hơn
        //Ex:
        //barcodes = 3(5), 2(3), 1(2)
        //+ Nếu gộp:
        //2,1,2 ==> Điền 3,3,3,3,3 ==> Sai chắc
        //+ Nếu gộp:
        //3,(2),3,(2),3,(2),3,(1),3
        //
        //
//        int[] barcodes = {1,1,1,2,2,2};
//        int[] barcodes = {1,1,1,1,2,2,3,3};
//        int[] barcodes = {1};
//        int[] barcodes = {1};
//        int[] barcodes = {1,1,2};
        int[] barcodes = {7,7,7,8,5,7,5,5,5,8};
        //- Case count(5)==count(7) ==> thừa 2 con 8
        //==> Nếu xếp 5 và 7 + 8,8 sẽ invalid
        //- Nếu dùng count add 1 loạt ==> Sau đó trừ đi thì sẽ bị WRONG case trên
        //Ex:
        //rs = [7,5,7,5,7,5,7,5,(8,8)]
        //  ==> Invalid
        //* Solution:
        //- Ta cần add lần lượt pair of different digits vào
        //  + Giảm count của từng số đi 1 ==> add lại vào priority queue
        //  + Sau đó lấy 2 pair of digits có count lớn nhất ra xử lý tiếp
        //
//        rearrangeBarcodesWrong(barcodes);
        rearrangeBarcodes(barcodes);
        rearrangeBarcodesRefer(barcodes);
        //
        //1.1, Optimization
        //- Ta có thể sort trước + fill in giá trị khác đi
        //Ex:
        //barcodes = {7,7,7,8,5,7,5,5,5,8}
        //- Ta sẽ fill in trước các vị trí cách nhau cho các elements giống nhau:
        //Ex:
        //rs = {7,0,7,0,7,0,7,0,0,0}
        //- Nếu fill in 5 có count(5)==4 vào giữa 7 thì sẽ bị cases thừa 2 số 8
        //* Main point:
        //+ Ta sẽ fill sao cho 2 số fill in liên tiếp ==> Luôn cách nhau 1 unit
        //  + Do 2 số đó sẽ có thể bằng nhau ==> sẽ cách nhau 1 unit ==> sẽ luôn khác nhau
        //  + Các số >0 <=> Điền rồi (KHÔNG CẦN XÉT)
        //      + Skip là được
        //- Đến biên --> Không cần cách 1 unit:
        //  + i=1
        //* Important point:
        //- Theo lý thuyết thì không cần xét (rs[i]>0) <=> (đã điền chưa):
        //  + Để fill in đủ value ==> Chỉ cần scan 2 lần là (điền hết SOLE)
        //==> Điền ntn thì các số sẽ luôn khác nhau
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        //#Reference:
        //2918. Minimum Equal Sum of Two Arrays After Replacing Zeros
        //2335. Minimum Amount of Time to Fill Cups
        //3044. Most Frequent Prime
        //
    }
}
