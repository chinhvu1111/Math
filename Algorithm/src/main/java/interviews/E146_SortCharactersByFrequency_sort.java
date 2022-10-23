package interviews;

import java.util.*;

public class E146_SortCharactersByFrequency_sort {

    public static String frequencySortNormallly(String s) {
        int[] count=new int[123];
        int n=s.length();
        List<Character> uniqueElements=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(count[s.charAt(i)]==0){
                uniqueElements.add(s.charAt(i));
            }
            count[s.charAt(i)]++;
        }
        Collections.sort(uniqueElements, (o1, o2) -> {
            if(count[o1]>count[o2]){
                return -1;
            }else if(count[o1]<count[o2]){
                return 1;
            }
            return 0;
        });
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<uniqueElements.size();i++){
            for(int j=1;j<=count[uniqueElements.get(i)];j++){
                rs.append(uniqueElements.get(i));
            }
        }
        return rs.toString();
    }

    public static String frequencySortApplyCountingSort(String s) {
        int[] count=new int[123];
        int n=s.length();
        List<Character> uniqueElements=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(count[s.charAt(i)]==0){
                uniqueElements.add(s.charAt(i));
            }
            count[s.charAt(i)]++;
        }
        uniqueElements.sort((o1, o2) -> {
            if (count[o1] > count[o2]) {
                return -1;
            } else if (count[o1] < count[o2]) {
                return 1;
            }
            return 0;
        });
//        System.out.println(uniqueElements);
        for(int i=1;i<uniqueElements.size();i++){
           count[uniqueElements.get(i)]=count[uniqueElements.get(i)]+ count[uniqueElements.get(i-1)];
//        System.out.printf("%s %s, ", uniqueElements.get(i), count[uniqueElements.get(i)]);
        }
        Character[] arr=new Character[s.length()];

        for(int i=0;i<s.length();i++){
            arr[count[s.charAt(i)]-1]=s.charAt(i);
//         System.out.printf("%s %s, ", uniqueElements.get(i), arr[count[uniqueElements.get(i)]-1]);
            count[s.charAt(i)]--;
        }
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<arr.length;i++){
            if(arr[i]!=null){
                rs.append(arr[i]);
            }
        }
        return rs.toString();
    }

    public static String frequencySortApplyBucketSort(String s) {
        int n=s.length();
        HashMap<Character, Integer> hashMapCount=new HashMap<>();

        for(int i=0;i<n;i++){
            hashMapCount.put(s.charAt(i),hashMapCount.getOrDefault(s.charAt(i),0)+1);
        }
        List<Character>[] bucket=new ArrayList[s.length()+1];

        for(char key: hashMapCount.keySet()){
            int frequency=hashMapCount.get(key);

            if(bucket[frequency]==null){
                bucket[frequency]=new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        StringBuilder rs=new StringBuilder();

        for(int i=bucket.length-1;i>=0;i--){
            if(bucket[i]!=null){
//                System.out.println(bucket[i]);
                for(Character c: bucket[i]){
                    for(int h=0;h<i;h++){
                        rs.append(c);
                    }
                }
            }
        }

        return rs.toString();
    }

    public static void main(String[] args) {
//        String s="tree";
        String s="eeeee";
//        String s="loveleetcode";
        System.out.println((int)'a');
        System.out.println((int)'z');
        System.out.println((int)'A');
        System.out.println((int)'Z');
        System.out.println(frequencySortNormallly(s));
        System.out.println(frequencySortApplyCountingSort(s));
        System.out.println(frequencySortApplyBucketSort(s));
        //
        //** Đề bài
        //-
        //
        //** Bài này tư duy như sau:
        //0, Nhớ lại
        //- Counting sort --> Cộng dồng count[i]=count[i-1] + count[i]
        //==> Mục đích để tính các khoảng giữa
        //VD: 1,2,4,5,1,2 ==> count[1]=2
        //==> cộng count[2]=count[1] + count[2] ==> 2 sẽ bỏ quả khoảng cách count[1] =2 (Đã được điền số 1)
        //Cách 1:
        //Sắp xếp thông thường:
        //1,
        //1,1, Count số lượng phần tử của mỗi element trong elements
        //1.2, Distinct các elements ==> list
        //1.3, Sắp xếp các phần tử distict sao cho count[iư giảm dần
        //1.4, for --> Dùng StringBuilder để lấy kết quả
        //
        //## Time complexity:
        //- O(N log N) : N length of array, k is number of character
        //## Space
        //- O(N)
        //
        //Cách 2: Áp dụng counting sort
        //2,
        //2.1, count bình thường
        //2.2, Distinct các elements ==> list
        //2.3, Traverse Unique list --> Tính theo công thức như sau:
        //CT:
        //- count[uniqueElements.get(i)]=count[uniqueElements.get(i)]+ count[uniqueElements.get(i-1)];
        //==> C[i] = C[i]+ C[i-1]
        //==> Mục đích ở đây là chuyển thành dãy dạng
        //VD:
        //[e,r,t]
        //[2,1,1]
        //==>
        //e sẽ được điền lần lượt vòa (0,1), r sẽ được điền lần lượt vào (2)
        //==> Ta cần cộng dồn
        //[e,r,t]
        //[2,1,1]
        //[2,3,4]
        //
        //2.4, Sau đó --> Traverse all nums[]
        //- Gán arr[ count[nums[ch]]-1 ] = nums[ch]
        //===> Điền từng vị trí (Key ở đây là đoạn sort)
        //- Trừ dần count[nums[i]]--
        //
        //## Time complexity:
        //- O(N) + K log (k) : N length of array, k is number of character
        //## Space
        //- O(N)
        //
        //Cách 3:
        //- bucket sort
        //3,
        //3.1, ở đây ta sẽ xắp xếp array bằng cách chia ra thành từng bucket 1
        //- Tận dụng index của array đã được sắp xếp trước
        //- Bucket dạng List[] buckets
        //--> bucket[i] = new ArrayList() : Là danh sách phần tử có số lần xuất hiện (frequency = i)
        //VD: eertt
        //- VD: bucket[2] = List {e,t}
        //3.2, Sau đó ta sẽ traverse từ (n-1 ==> 0) --> + số ký tự trong bucket[i] (list) + (h=0 --> i : số lần xuất hiện)
        //==> s.append(c) ==> result
        //
        //##Time complexity:
        //- O(N)
        //## Space:
        //- O(N)
        //##Reference
        //First Unique Character in a String : easy
        //Sort Array by Increasing Frequency : easy
        //Percentage of Letter in String : easy
        //Maximum Number of Pairs in Array : easy
        //Node With Highest Edge Score :medium
    }
}
