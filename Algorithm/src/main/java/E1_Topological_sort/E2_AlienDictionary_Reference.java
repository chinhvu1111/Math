package E1_Topological_sort;

import java.util.*;

public class E2_AlienDictionary_Reference {

    public static HashMap<Character, List<Character>> adjList;
    public static HashMap<Character, Integer> counts;

    public static String bfs(){
        StringBuilder rs=new StringBuilder();
        Queue<Character> queue=new LinkedList<>();

        for(Character c: counts.keySet()){
            if(counts.get(c)==0){
                queue.add(c);
            }
        }
        //Time : O(E)
        while (!queue.isEmpty()){
            Character currentChar=queue.poll();
            rs.append(currentChar);

            for(char c: adjList.get(currentChar)){
                counts.put(c, counts.get(c)-1);
                if(counts.get(c)==0){
                    queue.add(c);
                }
            }
        }
        if(rs.length()<counts.size()){
            return "";
        }
        return rs.toString();
    }

    public static String alienOrderBFS(String[] words) {
        adjList=new HashMap<>();
        counts=new HashMap<>();

        //Time : O(N)
        for(String word:words){
            //Time : O(M)
            for(char c:word.toCharArray()){
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }
        int n=words.length;

        //Time : O(N)
        for(int i=0;i<n-1;i++){
            String word1=words[i];
            String word2=words[i+1];

            if(word1.length()>word2.length()&&word1.startsWith(word2)){
                return "";
            }
            int minLength=Math.min(word1.length(), word2.length());

            //Time : O(M)
            for(int j=0;j<minLength;j++){
                if(word1.charAt(j)!=word2.charAt(j)){
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j))+1);
                    break;
                }
            }
        }
        //Time : O(E)
        return bfs();
    }

    //Tìm cycle:
    //* Dùng stack ta có 2 cách check cycle:
    //- Check all node phaỉ all nodes không có cycle
    //* Cách 1:
    //+ Với mỗi node traverse : stack[node] --> Dùng ghi lại quá trình traverse
    //+ Với all nodes traverse : visited[] --> Dùng để cache lại quá trình run đến từng nodes ==> Để 1 số nodes đã được xét trước đó (for tiếp sẽ return luôn)
    // --> Không cần re-traverse.
    //
    //* Cách 2:
    //- Dùng HashMap<Character, Boolean> hashMap:
    //* Value trong hashmap sẽ có 3 trạng thái :
    //+ exist : Đã xét (Trong hashMap) --> Continue
    //+ value_entry(i)= true : Đã đi qua --> Tức là đang xét dở chưa xong
    //+ value_entry(i)= false : Đã xét qua node đó --> Traverse hết các neighbors của node (i)
    //
    //- Tính chất của DFS:
    //+ Nếu ta add value sau for() [ Dùng để traverse neighbors]
    //==> Nó sẽ là add các leaf nodes --> Theo thứ tự thì ta chỉ cần reverse lại là được.
    //+ Vì tính chất leaf nodes nên ta có traverse ở bất cứ nodes nào each time.
    public static HashMap<Character, Boolean> visited;
    public static boolean isCycle(Character currentNode, StringBuilder reverseRs){
        if(visited.containsKey(currentNode)){
            return visited.get(currentNode);
        }
        visited.put(currentNode, true);

        for(char c: adjList.get(currentNode)){
            boolean nextResult=isCycle(c, reverseRs);
            if(nextResult){
                return true;
            }
        }
        reverseRs.append(currentNode);
        visited.put(currentNode, false);
        return false;
    }

    public String alienOrderDFS(String[] words) {
        //Space :
        adjList=new HashMap<>();
        //Space : O(26)
        visited=new HashMap<>();
        for(String word:words){
            for(char c:word.toCharArray()){
                adjList.put(c, new ArrayList<>());
            }
        }

        int n=words.length;

        for(int i=0;i<n-1;i++){
            String word1=words[i];
            String word2=words[i+1];

            if(word1.length()>word2.length()&&word1.startsWith(word2)){
                return "";
            }
            int minLength=Math.min(word1.length(), word2.length());

            for(int j=0;j<minLength;j++){
                if(word1.charAt(j)!=word2.charAt(j)){
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    break;
                }
            }
        }
        StringBuilder reverseRs=new StringBuilder();

        for(char c:adjList.keySet()){
            if(visited.containsKey(c)){
                continue;
            }
            boolean isCycle=isCycle(c, reverseRs);
            if(isCycle){
                return "";
            }
        }

        return reverseRs.reverse().toString();
    }

    public static void main(String[] args) {
        //
        //* Method-1: BFS
        //1.
        //1.0, Idea
        //- Các step traverse như sau:
        //+ Ở đây ta sẽ traverse node(i) + giảm indegree của các neighbors nodes của node(i)
        //+ Các nodes nào có inDegree==0 ==> Mới được add vào queue để xét tiếp
        //* Ở đây check cycle bằng cách check length của các nodes được traverse hết:
        //- Nếu result.length() < the number of nodes ==> Tức là có cycle (Có stopped ở đâu đó)
        //--> return ""
        //
        //1.1, Complexity:
        //- Time complexity : O(M*N+V+E)
        //+ M*N ở đây có thể rút gọn thành :
        //+ Let C be the (total length of all the words) in the input list, added together.
        //
        //+ Phân tích O(V+E)
        // + Ta có N words, do kết nối pair of words thì sẽ được 1 edges ==> Ta sẽ có nhiều nhất N-1 edges
        // + Ta có U nodes --> Nhiều nhất U^2 edge
        // + V = U
        //==> Time = O( C + U + min(U^2, N-1) )
        //+ N < C : Mỗi word bao gồm ít nhất 1 letter(char)
        //+ U < C : Unique letters lúc nào cũng ít nhất
        //
        //+ Nếu U^2 < N : min(U^2, N)= U^2, U^2<N, N<C ==> U^2 <C
        //+ Nếu U^2 > N : min(U^2, N)= N + N<C ==> <C
        //==> Time = O(C)
        //
        //- Space complexity : O(N*M)
        //+ adjList=new HashMap<>();
        //+ Space = O(V+E)
        //** Với cái hashmap dạng này space luôn theo edges vì cơ bản chính là số lượng connections
        //- V is the number of unique letters
        //- E is the number of edges
        //* More detail tính số lượng edges:
        //Ta có:
        //- U là số lượng nodes
        //- N là số lượng words
        //=> E = min(U^2, N)
        //+ counts:
        //  + space = O(V)
        //==> Space = O(V) + min(U^2, N)
        //
        //* Method-2: DFS
        //2.0, Idea
        //** Tìm cycle:
        //* Dùng stack ta có 2 cách check cycle:
        //- Check all node phaỉ all nodes không có cycle
        //* Cách 1:
        //+ Với mỗi node traverse : stack[node] --> Dùng ghi lại quá trình traverse
        //+ Với all nodes traverse : visited[] --> Dùng để cache lại quá trình run đến từng nodes ==> Để 1 số nodes đã được xét trước đó (for tiếp sẽ return luôn)
        // --> Không cần re-traverse.
        //
        //- Dùng HashMap<Character, Boolean> hashMap:
        //* Value trong hashmap sẽ có 3 trạng thái :
        //+ exist : Đã xét (Trong hashMap) --> Continue
        //+ value_entry(i)= true : Đã đi qua --> Tức là đang xét dở chưa xong
        //+ value_entry(i)= false : Đã xét qua node đó --> Traverse hết các neighbors của node (i)
        //
        //- Tính chất của DFS:
        //+ Nếu ta add value sau for() [ Dùng để traverse neighbors]
        //==> Nó sẽ là add các leaf nodes --> Theo thứ tự thì ta chỉ cần reverse lại là được.
        //+ Vì tính chất leaf nodes nên ta có traverse ở bất cứ nodes nào each time.
        //
        //2.1, Complexity:
        //- Time complexity : O(C)
        //- Space complexity : O(U + min(U^2, N))
        //
        //#Reference:
        //1203. Sort Items by Groups Respecting Dependencies
        //853. Car Fleet
        //472. Concatenated Words
        //2477. Minimum Fuel Cost to Report to the Capital
        //649. Dota2 Senate
        //2223. Sum of Scores of Built Strings
        //2279. Maximum Bags With Full Capacity of Rocks
        //2778. Sum of Squares of Special Elements
        //1293. Shortest Path in a Grid with Obstacles Elimination
        //1448. Count Good Nodes in Binary Tree
    }
}
