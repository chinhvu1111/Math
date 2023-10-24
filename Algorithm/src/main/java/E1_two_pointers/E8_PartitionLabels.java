package E1_two_pointers;

import java.util.ArrayList;
import java.util.List;

public class E8_PartitionLabels {

    public static List<Integer> partitionLabels(String s) {
        int[] rightFurthest=new int[26];
        int n=s.length();

        for(int i=n-1;i>=0;i--){
            int c=s.charAt(i)-'a';
            if(rightFurthest[c]==0){
                rightFurthest[c]=i;
//                System.out.printf("%s %s\n",s.charAt(i), rightFurthest[c]);
            }
        }
        int high;
        List<Integer> rsList=new ArrayList<>();

        //Ex:
        //abcabde
        for(int i=0;i<n;i++){
            int low=i;
            high=i;
            while(low<=high){
                int c=s.charAt(low)-'a';
                if(rightFurthest[c]!=0){
                    high=Math.max(high, rightFurthest[c]);
                }
                low++;
            }
            rsList.add(high-i+1);
            i=low-1;
        }
        return rsList;
    }

    public static List<Integer> partitionLabelsRefactor(String s) {
        int[] rightFurthest=new int[26];
        int n=s.length();

        for(int i=n-1;i>=0;i--){
            int c=s.charAt(i)-'a';
            if(rightFurthest[c]==0){
                rightFurthest[c]=i;
//                System.out.printf("%s %s\n",s.charAt(i), rightFurthest[c]);
            }
        }
        int high;
        List<Integer> rsList=new ArrayList<>();

        //Ex:
        //abcabde
        for(int i=0;i<n;i++){
            high=i;
            int init=i;
            while(i<=high){
                int c=s.charAt(i)-'a';
                if(rightFurthest[c]!=0){
                    high=Math.max(high, rightFurthest[c]);
                }
                i++;
            }
            rsList.add(high-init+1);
            i--;
        }
        return rsList;
    }

    public static List<Integer> partitionLabelsOptimization(String s) {
        int[] rightFurthest=new int[26];
        int n=s.length();

        for(int i=n-1;i>=0;i--){
            int c=s.charAt(i)-'a';
            if(rightFurthest[c]==0){
                rightFurthest[c]=i;
//                System.out.printf("%s %s\n",s.charAt(i), rightFurthest[c]);
            }
        }
        int high = 0;
        int low=0;
        List<Integer> rsList=new ArrayList<>();

        //Ex:
        //abcabde
        for(int i=0;i<n;i++){
            high=Math.max(high, rightFurthest[s.charAt(i)-'a']);
            if(i==high){
                rsList.add(high-low+1);
                low=i+1;
            }
        }
        return rsList;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s.
        // We want to partition the string into as (many parts) as possible so that (each letter) appears in at most one part.
        //- Chia string thành các parts sao cho mỗi letter trong parts đó chỉ có tại part đó mà không thuộc part khác
        //* Return số parts lớn nhất có thẻ chia
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 500
        //s consists of lowercase English letters.
        //+ lowercase : 26 character
        //
        //- Brainstorm
        //Ex:
        //s=ababcbacadefegdehijhklij
        //
        //- Tư duy ở đây là tìm character xa nhất về phía right
        //+ Sau đó mỗi i thì ta chạy 2 pointers để chọn 1 vùng nhỏ nhất mà mỗi character sẽ không thuộc vùng khác
        //  + Vùng đó sẽ ít nhất là (s[low], index phía right xa nhất của character at low-th (rightMost[s[low]-a])
        //  + Trong khoảng đó có thể có các character có index phía right còn xa hơn ==> Ta cần traverse hết ==> nới high dần
        //  + Cuối cùng update i thành high ==> Sau khi loop tiếp i++ ==> high+1 ==> Sang new part.
        //
        //1.2, Kinh nghiệm
        //- Chủ ý với bài 2 poiters dạng này:
        //+ Ta cần lưu giá trị đầu của low ==> Check thay đổi của low trong thân ==> Nếu thay đổi i = low-1 <> để nguyên i như bình thường
        //==> Tính chất while hết loop ==> low= high+1
        //
        //1.3, Optimization
        //- Viết được ngắn lại tý bằng cách dùng i trực tiếp thôi ==> Nhưng vẫn cần lưu lại init của i ==> Để add(high-init+1) vào result
        //
        //1.4,
        //- Space : O(n)
        //- Time : O(n)
//        String s = "ababcbacadefegdehijhklij";
//        String s = "eccbbbbdec";
//        String s = "e";
//        String s = "";
        //
        //2, Greedy
        //2.0,
        //- Idea
        //+ Vẫn là idea đó ==> Chỉ là ta không cần chạy 2 pointers.
        //  + low=0 ban đầu
        //  + nếu i!=max(high, rightMost[s[i]-'a'])
        //      + rs.add(high-low+1)
        //      + update : low=i+1
        //
        //
        String s = "edc";
        System.out.println(partitionLabels(s));
        System.out.println(partitionLabelsRefactor(s));
        System.out.println(partitionLabelsOptimization(s));
        //#Reference:
        //2405. Optimal Partition of String
    }
}
