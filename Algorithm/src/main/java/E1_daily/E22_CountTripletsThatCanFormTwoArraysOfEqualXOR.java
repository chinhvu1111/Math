package E1_daily;

import java.util.HashMap;

public class E22_CountTripletsThatCanFormTwoArraysOfEqualXOR {

    public static int countTriplets(int[] arr) {
        int n=arr.length;
        int[] prefix=new int[n+1];

        for(int i=1;i<=n;i++){
            prefix[i]=prefix[i-1]^arr[i-1];
        }
        int rs=0;

        // k
        for(int k=2;k<=n;k++){
            //j
            for(int j=k;j>=2;j--){
                //i,j,k
                //i
                //prefix[i]^prefix[k] = (k+1,i)
                for(int i=j-1;i>=1;i--){
                    //prefix[2]^prefix[1] == prefix[0]^prefix[0]
                    if((prefix[j-1]^prefix[k])==(prefix[i-1]^prefix[j-1])){
//                        System.out.printf("%s %s %s\n", i-1, j-1, k-1);
                        rs++;
                    }
                }
            }
        }
        return rs;
    }

    public static int countTriplets1(int[] arr) {
        int n=arr.length;
        int[] prefix=new int[n+1];

        for(int i=1;i<=n;i++){
            prefix[i]=prefix[i-1]^arr[i-1];
        }
        int rs=0;

        //j>i
        for(int i=0;i<=n-1;i++){
            int init=Math.max(i+1, 2);
            for(int j=init;j<=n;j++){
                //(j+1,i)
                if(prefix[i]==prefix[j]){
//                    System.out.printf("%s %s\n", i+1, j);
                    //j-(i+1)
                    rs+=j-i-1;
                }
            }
        }
        return rs;
    }

    public static int countTripletsHashMap(int[] arr) {
        int n=arr.length;
        int[] prefix=new int[n+1];

        for(int i=1;i<=n;i++){
            prefix[i]=prefix[i-1]^arr[i-1];
        }
        int rs=0;
        HashMap<Integer, Integer> prefixCount=new HashMap<>();
        HashMap<Integer, Integer> totalIndexPrefix=new HashMap<>();
//        prefixCount.put(0, 1);
//        prefixCount.put(prefix[1], prefixCount.getOrDefault(prefix[1], 0)+1);
//        totalIndexPrefix.put(prefix[1], 2);

        //i=1
        for(int i=0;i<=n;i++){
//            if(prefixCount.containsKey(prefix[i])){
//                rs+=prefixCount.get(prefix[i])*i - totalIndexPrefix.getOrDefault(prefix[i], 0);
//            }
            if(i>=2){
                rs+=prefixCount.getOrDefault(prefix[i], 0)*i - totalIndexPrefix.getOrDefault(prefix[i], 0);
            }
            totalIndexPrefix.put(prefix[i], totalIndexPrefix.getOrDefault(prefix[i], 0)+i+1);
            prefixCount.put(prefix[i], prefixCount.getOrDefault(prefix[i], 0)+1);
        }
        return rs;
    }

    public static int countTripletsHashMap1(int[] arr) {
        int n=arr.length;
        int rs=0;
        int xorVal=0;
        HashMap<Integer, Integer> prefixCount=new HashMap<>();
        HashMap<Integer, Integer> totalIndexPrefix=new HashMap<>();

        //i=1
        for(int i=0;i<=n;i++){
            if(i>=1){
                xorVal=xorVal^arr[i-1];
            }
            if(i>=2){
                rs+=prefixCount.getOrDefault(xorVal, 0)*i - totalIndexPrefix.getOrDefault(xorVal, 0);
            }
            totalIndexPrefix.put(xorVal, totalIndexPrefix.getOrDefault(xorVal, 0)+i+1);
            prefixCount.put(xorVal, prefixCount.getOrDefault(xorVal, 0)+1);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //Given an array of integers arr.
        //We want to select three indices (i, j and k) where
        // (0 <= i < j <= k < arr.length).
        //Let's define a and b as follows:
        //  + a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
        //  + b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
        //Note that ^ denotes the bitwise-xor operation.
        //* Return (the number of triplets (i, j and k)) Where a == b.
        //
        //** Idea
        //1. Brute force
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= arr.length <= 300
        //1 <= arr[i] <= 10^8
        //
        //- Brainstorm
        // i < j <= k
        //+ k: 1 -> n-1
        //  + 2 -> n
        //+ j : 1 -> k
        //  + 2 -> k
        //+ i : 0 -> j-1
        //  + 1 -> j-1
        //  + a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
        //  + b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
        //  a = prefix[j-1] ^ prefix[i-1]
        //  b = prefix[k] ^ prefix[j-1]
        //
        //* Chú ý:
        //- Prefix sum thì không liên quan đến việc (index-1)
        //  ==> Chỉ khi dùng với nums thì ta mới quan tâm đến (index-1)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n^3)
        //
        //2. Vẫn là prefix + trick
        //- Có cách nào caching giảm đi 1 loop không?
        //  + (i,j,k)
        //- Nếu chỉ dùng 1 prefix --> chỉ có 1 cách O(n^3)
        //  + a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
        //  + b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
        //
        //- Ta đang so sánh XOR của 2 collection:
        //  XOR(j,k)==XOR(i,j-1)
        //* trick:
        //  XOR(j,k) xor XOR(i,j-1) ==0
        //==> Ta chỉ cần đếm count các case (i,k) khác nhau là được.
        //  <=> XOR(i) == XOR(k) là được.
        //  ==> Ta sẽ lấy được đoạn (i+1,k)
        //- Mỗi (i,k) sẽ cho ta:
        //Ex: i=1, j=4
        //a,b,c,d
        //+ a,b,d
        //+ a,c,d
        //+ a,d,d
        //==> 3
        // => rs += j-i
        //
        //3. HashMap + trick
        //3.0,
        //- Với idea bên trên thì ta có thể caching count số prefix = x
        //
        //
        //- Nếu ta tìm được N prefix bằng nhau
        //==> Xét từng pair một vì:
        //  + Các cases được cố định bởi (i,k) => (count j)
        //
        //a,(b),c,(d),e,(f)
        //0, 1, 2, 3, 4, 5
        //1,3 : 5-3-1
        //1,5 : 5-1-1
        //3,5 : 5-3-1
        //
        //- Nếu làm bình thường để xét các cases ntn:
        //  + Ta cần loop lại các prefix cũ + vào
        //==> Bước cộng trên liệu có thể tính cộng luôn vào được không?
        //
        //- Ta có thể cộng dồn (i+1) vào
        //- count các prefix ta cũng có thể lưu lại
        //  + rs+= i*cur_count - (total (i+1))
        //
        //- Nhớ chỉ tính cho i>=2
        //  + count cho (val==0) ==> cũng cần cả total nữa.
        //
        //3.1, Optimization
        //- Bỏ phần array có size==n đi
        //3.2, Complexity
        //- Space: O(n)
        //- Time : O(n)
        //
//        System.out.println(3^1);
        System.out.println(2^3);
        //0,1,2,3,4,5
        //  2,3,1,6,7
        int[] arr = {2,3,1,6,7};
//        int[] arr = {1,1,1,1,1};
        System.out.println(countTriplets(arr));
        System.out.println(countTriplets1(arr));
        System.out.println(countTripletsHashMap(arr));
        System.out.println(countTripletsHashMap1(arr));
    }
}
