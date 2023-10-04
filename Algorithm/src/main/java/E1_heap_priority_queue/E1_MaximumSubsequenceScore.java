package E1_heap_priority_queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class E1_MaximumSubsequenceScore {

    public static class Node{
        int value;
        int score;
        int index;
        public Node(int index, int value, int score){
            this.index=index;
            this.value=value;
            this.score=score;
        }

        @Override
        public String toString() {
            return "Index: "+index+", value: "+value+", score: "+score+"\n";
        }
    }

    public static long maxScoreWrong(int[] nums1, int[] nums2, int k) {
        int n=nums1.length;
        Node[] nodes=new Node[n];
        Node[] nodeVals=new Node[n];

        for(int i=0;i<n;i++){
            nodes[i]=new Node(i, nums2[i], nums1[i]);
            nodeVals[i]=new Node(i, nums2[i], nums1[i]);
        }
        Arrays.sort(nodes, (a, b) -> a.value-b.value);
        //
        Arrays.sort(nodeVals, (a, b) -> b.score-a.score);
        long[] prefixSum=new long[n];
        HashMap<Integer, Integer> realIndexToSortIndex=new HashMap<>();
        long sum=0;

        for(int i=0;i<n;i++){
            sum+=nodeVals[i].score;
            prefixSum[i]=sum;
            realIndexToSortIndex.put(nodeVals[i].index, i);
        }
        System.out.println(realIndexToSortIndex);
        long rs=0;

        //
        for(int i=0;i<n;i++){
            Node currentNode=nodes[i];
            //0,1,2 (n=3)
            //k=2
            //n-1-k = 3-1-2=0
            int sortedIndex=realIndexToSortIndex.get(currentNode.index);
            int currentScore=currentNode.score;

            if(sortedIndex<k){
                System.out.printf("Get value : %s, min: %s\n", prefixSum[k-1], currentNode.value);
                rs=Math.max(rs, prefixSum[k-1]*currentNode.value);
            }else if(k>=2){
                System.out.printf("Get value : %s, min: %s\n", (prefixSum[k-2]+currentScore)*currentNode.value, currentNode.value);
                rs=Math.max(rs, (prefixSum[k-2]+currentScore)*currentNode.value);
            }
        }
        return rs;
    }

    public static long maxScore(int[] nums1, int[] nums2, int k) {
        int n=nums1.length;
        Node[] nodes=new Node[n];

        for(int i=0;i<n;i++){
            nodes[i]=new Node(i, nums2[i], nums1[i]);
        }
        Arrays.sort(nodes, (a, b) -> b.value-a.value);
        //- Vì cần remove từ MAX --> MIN ==> Ta cần phải sắp xếp từ max --> min (Giảm dần)
        PriorityQueue<Integer> queueIncreScore=new PriorityQueue<>((a, b) -> a-b);
        long rs=0;
        long curSum=0;
//        Arrays.sort(nums1);
//        for(int i=n-1;i>=0;i--){
//            queueIncreScore.add(nums1[i]);
//            curSum+=nums1[i];
//        }

        //nums2 ={4,3,2,1}
        //nums1 = [2,3,1,3]
        //queue= [3,3,2,1] (Decrease)
        //- Vì đi từ max --> min ==> Các phần tử ban đầu phải là full list vs (min= MAX của all elements)
        //+ min = 4 (all elements) khi ta chọn index=0
        //  + Ta chỉ có thể chọn 1 element được ==> queue = 2
        //+ min = 3 ==> Các element có min >=3
        //  + 2,3
        for(int i=0;i<n;i++){
            Node currentNode=nodes[i];
//            System.out.println(queueIncreScore);
//            System.out.printf("Sum= %s\n", curSum);

            if(queueIncreScore.isEmpty()){
                queueIncreScore.add(nums1[currentNode.index]);
                curSum+=nums1[currentNode.index];
            }else{
                if(queueIncreScore.size()==k){
                    if(queueIncreScore.peek()<nums1[currentNode.index]){
//                        System.out.printf("Remove : %s\n", queueIncreScore.peek());
                        curSum+=nums1[currentNode.index]-queueIncreScore.poll();
                        queueIncreScore.add(nums1[currentNode.index]);
//                        System.out.printf("Done %s\n", queueIncreScore);
                        rs=Math.max(rs, curSum*currentNode.value);
                    }
                }else{
                    curSum+=nums1[currentNode.index];
                    queueIncreScore.add(nums1[currentNode.index]);
                }
            }
            if(queueIncreScore.size()==k){
                rs=Math.max(rs, curSum*currentNode.value);
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.
        //+ Tức là ta có thể chọn sub indices bằng cách xoá đi 1 số elements or giữ nguyên
        //
        //The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
        //It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
        //* return maximum score that we can get.
        //Ex:
        //Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
        //Output: 12
        //Explanation:
        //The four possible subsequence scores are:
        //- We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
        //- We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
        //- We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
        //- We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
        //Therefore, we return the max score, which is 12.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == nums1.length == nums2.length
        //1 <= n <= 10^5
        //0 <= nums1[i], nums2[j] <= 105
        //1 <= k <= n
        //- Ta không thể giải theo kiểu bit mask được vì n khá lớn
        //--> Có thể giải trong O(n) / O(n*log(n))
        //
        //- Idea
        //- 2 array có index phụ thuộc nhau
        //- nums2 array ta có thể lấy min của all sub-indices bằng cách nào?
        //Ex:
        //nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
        //- Vì ta có thể chọn 1 sub-indices + delete các element 1 cách tự do.
        //+ Nên việc sort lại + chọn theo k y hệt với việc chọn nums2 trong ==> Miễn là ta có thể sorted chúng bằng nhau
        //- Ta sẽ sort nums2 mục đích là có thể tìm được min ngay thì vì phải xét min(nums2[i], nums2[i+1], nums2[i+3], nums2[i+4])
        //Ex:
        //nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
        //--> sort cùng nhau
        //nums1 = [3,1,3,2], nums2 = [1,2,3,4], k = 3
        //- Ta thấy rằng say khi sort
        //+ sum * min(range) ==> min lúc này sẽ là từng phần tử trong nums2 sau khi sort
        //Sẽ là:
        //+ min=1 : Nếu sum chứa nums1[0] + các số còn lại còn tuỳ chọn
        //+ min=2 : Nếu sum chứa nums1[1] + các số còn lại còn tuỳ chọn
        //+ min=3 : Nếu sum chứa nums1[1] + các số còn lại còn tuỳ chọn
        //
        //- Nếu xếp theo chiều giảm dần nums2
        //nums1 = [2,3,1,3], nums2 = [4,3,2,1], k = 3
        //
        //nums1 = [3,1,3,2], nums2 = [1,2,3,4], k = 3
        //+ index=0, min=1 ==> cố định 3 ==> Tìm k-1 các phần tử lớn nhất trong các phần tử còn lại
        //Ở đây ta sẽ sắp xếp nums1 trước:
        //+ 3,1,3,2 --> 1,2,3,3 ==> Ta sẽ search dựa trên k-1 phần tử khác index của min (Vì lấy value nó có thể trùng value)
        //Ex:
        //- Index min=0 ==> Ta có thể lấy từ max --> min được ==> Sum ta có thể tính trước được.
        //+ Nếu index>= n-1-k ==> Ta lấy sum( (k+1) của elements ) - min ==> Ta có thể lấy được sum.
        //==> Cần xem lại
        //nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
        //indexs =[0,1,2,3]
        //==>
        //nums1 =          [1,3,3,2]
        //Original indexs= [0,1,2,3]
        //sorted nums1=    [3,3,2,1] ==> Prefix sum : [3,6,8,9]
        //+ Real index=    [0,1,2,3] : Index sau khi sort
        //sorted indexs=   [1,2,3,0] : Index ban đầu
        //==> HashMap :
        // + {1:0, 2:1, 3:2, 0:3}
        //==> Tính cái này mới ra được index xem thuộc index hay không
        //
        //nums2 = {2,1,3,4};
        //nums2 = [1,2,3,4], k = 3
        //indexs= [1,0,2,3]
        //- 1 cái nữa là khi xét ==> Cần loại bỏ không được chọn giá trị của min trước đó
        //+ 1, index =
        //
        //- Làm sao để trừ được score của phần tử min đã xét trước đó
        //+ sorted nums2 ={1,2,3,4}
        //+ sorted nums1 ={3,2,1,1}
        //==> Khá khó để trừ đi
        //** Thế nên ta tư duy theo kiểu cộng lên liệu có được không?
        //- Nếu sort nums2 theo decrease thì sao?
        //+ sorted decrease nums2 ={4,3,2,1}
        //  + min = 4: ==> Vì là số lớn nhất nên ta sẽ chọn các element có nums2[i]==4
        //Ex:
        //Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
        //+ sorted decrease nums2 ={4,3,2,1}
        //+ Sau đó nums1 cũng sẽ được sorted theo score
        //+ nums2:
        //  + 2 -> 4
        //  + 1 -> 3
        //  + 3 -> 2
        //  + 4 -> 1
        //
        //nums1 = [2,3,1,3]
        //+ Ở đây ta sẽ dùng primary queue để chọn ra k thằng lớn nhất với từng score nums2[i] giảm dần (decrease)
        //+ Ta sẽ add phần tử vào
        //  + Nếu phần tử đó mà > first() ==> update (sum+=new_value-first) + add thêm vào
        //  + Nếu phần tử đó < first():
        //      + Nếu không đủ --> Vẫn add thêm sum+=value
        //      + Nếu đủ --> Không update sum
        //
//        int[] nums1 = {1,3,3,2};
//        int[] nums2 = {2,1,3,4};
//        int k = 3;

//        int[] nums1 = {1};
//        int[] nums2 = {2};
//        int k = 1;

//        int[] nums1 = {1,2};
//        int[] nums2 = {2,3};
//        int k = 1;

        int[] nums1 = {1};
        int[] nums2 = {2};
        int k = 2;
        System.out.println(maxScore(nums1, nums2, k));
        //#Reference:
        //502. IPO
        //857. Minimum Cost to Hire K Workers
    }
}
