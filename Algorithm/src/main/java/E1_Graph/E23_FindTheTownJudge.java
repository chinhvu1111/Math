package E1_Graph;

import java.util.HashMap;
import java.util.HashSet;

public class E23_FindTheTownJudge {

    public static int findJudge(int n, int[][] trust) {
        if(n==1&&trust.length==0){
            return 1;
        }
        HashMap<Integer, HashSet<Integer>> personTrustByPeople=new HashMap<>();
        HashMap<Integer, Integer> trustMap=new HashMap<>();

        for (int[] currentTrust : trust) {
            trustMap.put(currentTrust[0], currentTrust[1]);
        }

        for (int[] currentTrust : trust) {
            HashSet<Integer> people = null;
            if (!personTrustByPeople.containsKey(currentTrust[1])) {
                people = new HashSet<>();
            } else {
                people = personTrustByPeople.get(currentTrust[1]);
            }
            people.add(currentTrust[0]);
            personTrustByPeople.put(currentTrust[1], people);
            if (people.size() == n-1&&!trustMap.containsKey(currentTrust[1])) {
                return currentTrust[1];
            }
        }
//        System.out.println(personTrustByPeople);
        return -1;
    }

    public static int findJudgeMethod2(int n, int[][] trust) {
        if(trust.length<n-1){
            return -1;
        }
        int[] countOutEdge=new int[n+1];
        int[] countInEdge=new int[n+1];

        for(int[] currentTrust:trust){
            countOutEdge[currentTrust[1]]++;
            countInEdge[currentTrust[0]]++;
        }
        for(int i=1;i<=n;i++){
            if(countOutEdge[i]==n-1&&countInEdge[i]==0){
                return i;
            }
        }
        return -1;
    }

    public static int findJudgeMethod2Refactor(int n, int[][] trust) {
        if(trust.length<n-1){
            return -1;
        }
        int[] countOutEdge=new int[n+1];

        for(int[] currentTrust:trust){
            countOutEdge[currentTrust[0]]--;
            countOutEdge[currentTrust[1]]++;
        }
        for(int i=1;i<=n;i++){
            if(countOutEdge[i]==n-1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Có 1 nhóm người, trust thể hiện niềm tin giữa 2 người, có 1 town judge:
        //+ town judge không tin ai
        //+ Mọi người đều tin town judge
        //+ town judge không tin town judge
        //* Tìm town judge
        //+ Chỉ có 1 người duy nhất thoả mãn 2 đk trên
        //
        //** Idea
        //1.
        //1.0, Brainstorm
        //- Constraints:
        //+ 1 <= n <= 1000
        //+ 0 <= trust.length <= 10^4
        //+ trust[i].length == 2
        //+ n people --> distinct
        //
        //- Brainstorm
        //- Các people có thể trust lẫn nhau thoải mái
        //(1,3), (2,3), (1,2)
        //
        //- Tạo hash ngược dạng:
        //+ <3, hash<Int>>
        //- Tạo thêm hash check xem người judge town có đang trust ai nữa không
        //+ --> Nếu trust ai thì cần bỏ đi vì judge town không trust ai cả.
        //
        //1.1, Complexity:
        //- Time complexity : O(N)
        //+ N là số trust
        //
        //- Space complexity : O(N^2)
        //+ MapPeople:
        //+ Mỗi người có thể tin bởi (N-k) người còn lại
        //+ Ta có N người --> (N-k)*N = O(N^2)
        //+ Trust map:
        //+ N trustMap : O(N)
        //* Space = O(N^2) + O(N) = O(N^2)
        //
        //* Method 2:
        //2.
        //2.0, Idea
        //- Dùng phương pháp counting để đếm outedge và inedge
        //+ Chỉ là count outedge[i]++ --> Để check danh sách trust của person (ith)
        //+ count indege[i]+} --> Xem person ith có trust ai không
        //
        //2.1, Optimization
        //- Tận dụng 1 chút tính chất của outEdge[i]-- ==> Để tính cho cả đoạn check inEdge[i]==0
        //+ Nếu trừ đi thì nó sẽ không bao h đến được count=n-1 nếu có trust thêm ai.
        //
        //2.2, Complexity:
        //
        //- Time complexity : O(N)
        //+ time = max(N, M) = N
        //+ Best case : N<M : O(1)
        //+ Worst case : N>M : O(M)
        //+ N là length of trust array
        //
        //- Space complexity : O(M)
        //+ M is the number of people
//        int[][] trust={{1,3},{2,3}};
        int[][] trust={{1,3},{2,3},{3,1}};
        System.out.println(findJudge(3, trust));
        System.out.println(findJudgeMethod2(3, trust));
        System.out.println(findJudgeMethod2Refactor(3, trust));
        //#Reference:
        //1557. Minimum Number of Vertices to Reach All Nodes
        //277. Find the Celebrity
    }
}
