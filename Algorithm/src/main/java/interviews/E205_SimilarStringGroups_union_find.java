package interviews;

import java.util.Arrays;
import java.util.HashSet;

public class E205_SimilarStringGroups_union_find {

    public static int findParent(int[] parent, int u){
        int currentNode=u;
        //1-->2-->3
        //==> Cần gán 2-->3
        while (parent[u]!=-1){
//            height[u]++;
            u=parent[u];
        }
        //Đoạn này chính là đoạn optimization
        if(currentNode!=u){
            parent[currentNode]=u;
        }
//        height[u]++;
        return u;
    }

    public static void union(int[] height,int[] parent,int x, int y){
        int parentX=findParent(parent,x);
        int parentY=findParent(parent,y);

        if(parentY==parentX){
//            parent[x]=parentX;
//            parent[y]=parentY;
            return;
        }
        parent[parentY]=parentX;
//        System.out.printf("Parent of %s is %s\n", parentY, parentX);
        height[parentX]=Math.max(height[parentX], height[parentY]+1);
    }

    public static boolean isSameGroup(String s, String s1){
        int n=s.length();
        int m=s1.length();

        if(n!=m){
            return false;
        }
        StringBuilder sRemain=new StringBuilder();
        StringBuilder s1Remain=new StringBuilder();
        int countDiff=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)!=s1.charAt(i)){
                countDiff++;
                if(countDiff>2){
                    return false;
                }
                sRemain.append(s.charAt(i));
                s1Remain.append(s1.charAt(i));
            }
        }
//        System.out.println(sRemain);
//        System.out.println(s1Remain);
        return s1Remain.toString().equals(sRemain.reverse().toString());
    }

    public static int numSimilarGroups(String[] strs) {
        int n=strs.length;
        int[] parent=new int[n];
        int[] height=new int[n];
        Arrays.fill(parent, -1);
        Arrays.fill(height, 1);

        for(int i=0;i<n;i++){
//            boolean isContain=false;

            for(int j=0;j<n;j++){
//                if(i==j||((parent[i]==parent[j]||parent[i]==j||parent[j]==i)&&(parent[i]!=-1||parent[j]!=-1))){
//                    continue;
//                }
                if(i==j){
                    continue;
                }
                if(isSameGroup(strs[i], strs[j])){
                    //Nếu tính ntn --> Các trường hợp rời rạc nó sẽ bị sai
//                    if(parent[i]==-1&&parent[j]==-1&&!isContain){
//                        rs++;
//                    }
                    if(height[i]>=height[j]){
                        union(height, parent, i, j);
//                        System.out.printf("Parent of %s is %s\n", j, i);
                    }else{
                        union(height, parent, j, i);
//                        System.out.printf("Parent of %s is %s\n", i, j);
                    }
//                    isContain=true;
                }
            }
//            if(!isContain&&parent[i]==-1){
//                rs++;
//            }
        }
        HashSet<Integer> hashSet=new HashSet<>();
        for(int i=0;i<n;i++){
            hashSet.add(findParent(parent, i));
        }
        return hashSet.size();
    }

    /*
    Viết kiểu này sẽ bị thiếu case
     */
    public static boolean isSameGroupRefactor(String s, String s1){
        int n = 0;
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) != s1.charAt(i) && ++n==3)
                return false;
        return true;
    }

    public static int findParentRefactor(int[] parent, int u){
        int currentNode=u;
        //1-->2-->3
        //==> Cần gán 2-->3
        while (parent[u]!=u){
            u=parent[u];
        }
        parent[currentNode]=u;
        return u;
    }

    public static void unionRefactor(int[] height,int[] parent,int x, int y){
        int parentX=findParentRefactor(parent,x);
        int parentY=findParentRefactor(parent,y);

        if(parentY==parentX){
//            parent[x]=parentX;
//            parent[y]=parentY;
            return;
        }
        rs--;
        parent[parentY]=parentX;
//        System.out.printf("Parent of %s is %s\n", parentY, parentX);
        height[parentX]=Math.max(height[parentX], height[parentY]+1);
    }

    public static int rs=0;

    public static int numSimilarGroupsRefactor(String[] strs) {
        int n=strs.length;
        int[] parent=new int[n];
        int[] height=new int[n];
        rs=n;

        for(int i=0;i<n;i++) parent[i]=i;
        Arrays.fill(height, 1);

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
//                if(i==j){
//                    continue;
//                }
                if(isSameGroupRefactor(strs[i], strs[j])){
                    if(height[i]>=height[j]){
                        unionRefactor(height, parent, i, j);
                    }else{
                        unionRefactor(height, parent, j, i);
                    }
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        String[] s=new String[]{"tars","rats","arts","star"};
//        String[] s=new String[]{"tars"};
//        String[] s=new String[]{"blw","bwl","wlb"};
        String[] s=new String[]{"ajdidocuyh","djdyaohuic","ddjyhuicoa","djdhaoyuic","ddjoiuycha","ddhoiuycja","ajdydocuih","ddjiouycha","ajdydohuic","ddjyouicha"};
//        System.out.println(numSimilarGroups(s));
        //
        //** Đề bài
        //- Trả lại số lượng group của các strings có trong input
        //Các rule như sau:
        //- 1 string A,B chung group khi:
        //+ Swap 2 ký tự bất ký của A/B --> Ra A/B
        //- A và B có thể swap ra, B và C có thể swap ra ==> {A, B, C} chung 1 group ==> Result = Result +1
        //- Nếu thừa chuỗi C nào đó --> Vẫn là 1 group riêng và nó đứng 1 mình.
        //
        //** Bài này tư duy như say:
        //Cách 1:
        //1, - Dùng union find:
        //1.1,
        //Cần nhớ 1 số quy tác của union find:
        //- Có 2 thao tác trong union find là:
        //+ find(u) : trả về gốc của thành phần liên thông chứa u
        //+ union(u, v): hợp nhất thành phần liên thông của u và v lại với nhau
        //- Cần nhớ rằng khi muốn hợp 2 thành phần liên thông lại với nhau:
        //VD: u và v ==> Cần tìm gốc của (u và v)
        //==> Hợp 2 gốc vào với nhau
        //==> union (find(u), find(v))
        //
        //- Union optimisation:
        //Ví dụ: union(0, 4). Đặt parent[0] = 4 hay parent[4] = 0?
        //height : là độ cao của u trong tree Union find, tức là nếu u là root --> Thì khoảng cách từ u ==> leaf node xa nhất chính
        // là height
        //          0
        //       1     2
        //    3
        //height[0] = 3, height[1]=2, height[3]=1
        //* Solution:
        //+ Nếu height[find[u]] < height[find[v]] thì đặt : parent[find[u]] = find[v]
        //+ Nên đặt parent[4] = 0, bởi sẽ tiết kiệm được 1 thao tác nếu sau này ta cần gọi find(3)
        //* Conclusion: gắn gốc của TPLT (union) nhỏ hơn vào TPLT lớn hơn
        //
        //- Find optimization
        //+ Ví dụ: parent[3] = 1, parent[1] = 0 → parent[3] = 0.
        //+ What if find(3) được gọi rất nhiều lần, và 3 nằm rất sâu trong cây?
        //* Solution: Khi tìm được find(3) = 0, set luôn parent[3] = 0
        //
        //1.2,
        //- Bài này cần check xem s, s1 có cùng 1 group hay không
        //- Cần phải xác định khi nào gắn (i và j) theo height như ở bên trên.
        //- Bài này gặp vấn đề giữ việc lặp lại gán (u với v) và (v với u)
        //==> Nếu gán giống nhau có thể gây lặp vô tận
        //- Hơi lúng túng phần gán (u và v) thành 1 thành phần liên thông
        //==> Nhớ là phải tìm node của cả 2 (u và v) rồi mới gán parent[v]=u chẳng hạn.
        //
        //- Height chỉ được + 1 khi parent[v]=u.
        //1.3,
        // Các cases đặc biệt có thể xảy ra:
        //- các tập có thể lặp lại nhưng --> Cùng 1 root ==> Nếu gán kiểu nối thêm dài ra + không tìm root của cả (u và v)
        //==> Các node trả lại sẽ không chung 1 root
        //--> Không thể tìm ra kết quả được.
        //
        //1.4, Không được để case parent[u]=u --> Xảy ra ==> Loop vô tận
        //1.5, Kết quả trả về --> count số root của tree
        //- hashSet : find(all (i))
        //
        //2, Refactor
        //2.1, Nếu đổi lại function isSameGroup ==> Ta có thể tối ưu từ 120ms --> 38ms
        //2.2,
        //- Dùng height --> faster
        //2.3,
        //- Ở đây ta có thể đổi 1 chút về việc:
        //- find()
        //+ while(parent[u]!=u] ==> Mục đích là để tính kết quả cho dễ
        //===> Tư duy này sai vẫn cần ==> Vì có 1 số case 1 số điểm chỉ được dùng 1 lần
        //2.4, Vì đang xét tính chất các tập hợp liên thông
        //--> for chỉ cần (i: 0 -> n-1) và (j : i+1 -> n-1)
        //2.5, Các root là gì đến cuối mới biết --> vì có thể nó sẽ:
        //- 1,3
        //- 2,4
        //- 5,6
        //- 1,6 ==> Sẽ gộp --> Mới biết được root thật sự.
        //
        //2.6, Cách tính result khác ==> Trừ đi số thành phần liên thông mà không còn liên thông nữa
        //VD: Số thành phần liên thông MAX=n
        //--> Ta sẽ trừ dần nếu có 2 node có root khác nhau
        //- find(u)!=find(v) ==> group--;
        //3,
        //- Time complexity: O(N^N*K)
        //- Space complexity : O(N)
        System.out.println(numSimilarGroups(s));
        System.out.println(numSimilarGroupsRefactor(s));
//        System.out.println(isSameGroup("tars", "rats"));
        //Bị sai case này --> Nếu viết kiểu này giảm được kha khá thời gian
        System.out.println(isSameGroupRefactor("abc","dba"));
    }
}
