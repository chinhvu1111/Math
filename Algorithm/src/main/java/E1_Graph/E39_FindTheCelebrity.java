package E1_Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E39_FindTheCelebrity {

    public static int graph[][];
    static boolean knows(int a, int b){
        return graph[a][b]==1;
    }

    public int findCelebrity(int n) {
        //Số edge trỏ vào i
        int[] inDegree=new int[n];
        //Số edge trỏ ra
        int[] outDegree=new int[n];

        //0,1
        //0,2
        //1,2
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j){
                    continue;
                }
                if(knows(i, j)){
                    inDegree[j]++;
                    outDegree[i]++;
                }
            }
        }
        for(int i=0;i<n;i++){
            // System.out.printf("Index: %s, inDegree[i]: %s, outDegree[i]: %s\n", i, inDegree[i], outDegree[i]);
            if(inDegree[i]==n-1&&outDegree[i]==0){
                return i;
            }
        }

        return -1;
    }

    public static int findCelebrityOptimization(int n) {
        //- 1 node có thể đến được node khác ==> Node đó không thể là celebrity
        //-
        boolean[] isClebrity=new boolean[n];
        boolean[] visited=new boolean[n];
        Arrays.fill(isClebrity, true);

        for(int j=0;j<n;j++){
            Queue<Integer> nodes=new LinkedList<>();
            if(!visited[j]){
                nodes.add(j);
            }else{
                continue;
            }
            while(!nodes.isEmpty()){
                Integer curNode=nodes.poll();
                System.out.printf("Cur-node : %s\n",curNode);

                for(int i=0;i<n;i++){
                    if(i==curNode||visited[i]){
                        continue;
                    }
                    System.out.printf("%s to %s, knows: %s, visited[%s]=%s\n", curNode, i, knows(curNode, i), i, visited[i]);
                    if(knows(curNode, i)){
                        //Nếu curNode -> node i ==> Nó không phải là celebrity
                        isClebrity[curNode]=false;
                        System.out.printf("Set %s=False\n",curNode);
                    }else{
                        continue;
                    }
                    //Nếu node-i --> node-j
                    if(!visited[i]){
                        nodes.add(i);
                        visited[i]=true;
                    }
                }
                visited[curNode]=true;
            }
        }
        int count=0;
        int rs=-1;

        for(int i=0;i<n;i++){
            if(isClebrity[i]){
                boolean isValid=true;
                for(int j=0;j<n;j++){
                    if(!knows(j, i)){
                        isValid=false;
                        break;
                    }
                }
                if(!isValid){
                    continue;
                }
                count++;
//                if(count==2){
//                    return -1;
//                }
                rs=i;
            }
        }
        return count==1?rs:-1;
    }

    public static int findCelebrityReference(int n) {
        //- 1 node có thể đến được node khác ==> Node đó không thể là celebrity
        //-
        int celebrityCandidate=0;
        for(int i=0;i<n;i++){
            if(knows(celebrityCandidate, i)){
                celebrityCandidate=i;
            }
        }
        return isCelebrity(celebrityCandidate, n)?celebrityCandidate:-1;
    }

    public static boolean isCelebrity(int i, int n){
        for(int j=0;j<n;j++){
            if(i==j) continue;
            if(knows(i, j)||!knows(j, i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Tìm xem ai là clebrity trong N người đã cho
        //- Clebrity là ngừoi không biết ai trong N-1 người còn lại
        //- Còn N-1 người còn lại ai cũng biết clebrity
        //- Given knows(i, j)=1 method thể hiện người (i) biết người (j)
        //* Return lại clebrity là ai.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == graph.length == graph[i].length
        //2 <= n <= 100
        //graph[i][j] is 0 or 1.
        //graph[i][i] == 1
        //--> Số nhỏ có thể xử lý trong O(n^2)
        //
        //- Brainstorm
        //- Ở đây nếu dùng tư duy inDegree[] và outDegree
        //==> Ta vẫn có thể pass được.
        //
        //- Nhưng ở đây cần minimize số lần call knows() function = O(N*3)
        //- Requirement:
        //- Cần tìm the celebrity mà thoả mãn:
        //  + Không biết các người khác
        //  + Các người khác đều biết nó
        //- Ta có thể dùng BFS bài này:
        //==> Nhưng sẽ bị slow
        //
        //- Ở đây ta chỉ cần tìm candidate mà có khả năng là celebrity xong sau đó
        //==> double check xem nó có phải celebrity không là được.
        //+ Knows(A, B)= true: A không phải clebrity (Do A biết B)
        //+ Knows(A, B)= false: B không phải clebrity (Do A không biết B)
        //- for i : 0 to n
        //  + Với mỗi lần call knows(i, j) : Ta sẽ biết chính xác i / j có phải clebrity hay không
        //  ==> Ta chỉ cần gán cho thằng có khả năng là clebrity ==> Run tiếp để check
        //  Cứ loop như thế đến cuối ta sẽ thu được i có thể là clebrity
        //
        //1.1, Optimization
        //- Caching khi call knows() ==> HashMap
        //1.2, Complexity
        //- N is the number of nodes.
        //- Space : O(1)
        //- Time : O(N)
        //
//        graph= new int[][]{{1,1,0},{0,1,0},{1,1,1}};
//        graph= new int[][]{{1,0,1},{1,1,0},{0,1,1}};
//        int n=3;
//        graph= new int[][]{{1,0},{0,1}};
        // 0 1
//        int n=2;
//        graph= new int[][]{{1,1,1},{1,1,0},{0,0,1}};
        // 0 -> 1 -> 0
        //  \
        //   v
        //    2
        //
//        int n=3;
        graph= new int[][]{{1,1},{1,1}};
        int n=2;
        // 0 -> 1
        // 1 -> 0
        System.out.println(findCelebrityOptimization(n));
        System.out.println(findCelebrityReference(n));
        //#Reference:
        //843. Guess the Word
        //1750. Minimum Length of String After Deleting Similar Ends
        //1147. Longest Chunked Palindrome Decomposition
        //2838. Maximum Coins Heroes Can Collect
        //2868. The Wording Game
        //1850. Minimum Adjacent Swaps to Reach the Kth Smallest Number
        //
    }
}
