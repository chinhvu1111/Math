package E1_daily;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E84_MostStonesRemovedWithSameRowOrColumn {

    public static boolean dfs(
            Pair<Integer, Integer> node,
            HashMap<Integer, List<Pair<Integer, Integer>>> colAdj,
            HashMap<Integer, List<Pair<Integer, Integer>>> rowAdj,
            HashSet<Pair<Integer, Integer>> visited,
            int[] rs){
        visited.add(node);
        List<Pair<Integer, Integer>> listNodeInCol = colAdj.get(node.getValue());
        List<Pair<Integer, Integer>> listNodeInRow = rowAdj.get(node.getKey());
        boolean isRemoved=false;

        for(Pair<Integer, Integer> p: listNodeInCol){
            if(!visited.contains(p)){
                boolean nextVal = dfs(p, colAdj, rowAdj, visited, rs);
                isRemoved = true;
            }
        }
        for(Pair<Integer, Integer> p: listNodeInRow){
            if(!visited.contains(p)){
                boolean nextVal = dfs(p, colAdj, rowAdj, visited, rs);
                isRemoved = true;
            }
        }
        if(isRemoved){
            System.out.printf("%s %s\n", node.getKey(), node.getValue());
//            rs[0]++;
        }
        rs[0]++;
        return isRemoved;
    }

    public static void dfsOptimization(
            Pair<Integer, Integer> node,
            HashMap<Integer, List<Pair<Integer, Integer>>> colAdj,
            HashMap<Integer, List<Pair<Integer, Integer>>> rowAdj,
            HashSet<Pair<Integer, Integer>> visited,
            int[] rs){
        visited.add(node);
        List<Pair<Integer, Integer>> listNodeInCol = colAdj.get(node.getValue());
        List<Pair<Integer, Integer>> listNodeInRow = rowAdj.get(node.getKey());

        for(Pair<Integer, Integer> p: listNodeInCol){
            if(!visited.contains(p)){
                dfsOptimization(p, colAdj, rowAdj, visited, rs);
            }
        }
        for(Pair<Integer, Integer> p: listNodeInRow){
            if(!visited.contains(p)){
                dfsOptimization(p, colAdj, rowAdj, visited, rs);
            }
        }
        rs[0]++;
    }

    public static int removeStones(int[][] stones) {
        HashMap<Integer, List<Pair<Integer, Integer>>> colAdj=new HashMap<>();
        HashMap<Integer, List<Pair<Integer, Integer>>> rowAdj=new HashMap<>();

        for(int[] r:stones){
            List<Pair<Integer, Integer>> nodeInCol= colAdj.getOrDefault(r[1], new ArrayList<>());
            nodeInCol.add(new Pair<>(r[0], r[1]));
            colAdj.put(r[1], nodeInCol);
            List<Pair<Integer, Integer>> nodeInRow= rowAdj.getOrDefault(r[0], new ArrayList<>());
            nodeInRow.add(new Pair<>(r[0], r[1]));
            rowAdj.put(r[0], nodeInRow);
        }
//        System.out.println(colAdj);
//        System.out.println(rowAdj);
        HashSet<Pair<Integer, Integer>> visited=new HashSet<>();
        int[] rs=new int[1];

        for(int[] r:stones){
            if(!visited.contains(new Pair<>(r[0], r[1]))){
                int[] temp=new int[1];
                dfsOptimization(new Pair<>(r[0], r[1]), colAdj, rowAdj, visited, temp);
                rs[0]+=temp[0]-1;
            }
        }
        return rs[0];
    }

    public static void main(String[] args) {
        //** Requirement
        //- On a 2D plane, we place (n stones) at some integer (coordinate points).
        //- (Each coordinate point) may have ("at most") (one stone).
        //- A stone can be (removed) if it shares either (the same row) or (the same column) as another stone that (has not been removed).
        //- Given an array stones of length n where stones[i] = [xi, yi] represents (the location of the ith stone),
        //* return (the largest possible number of stones) that can be removed.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= stones.length <= 1000
        //0 <= xi, yi <= 10^4
        //No two stones are at the same coordinate point.
        //  + Số lượng stones không nhiều ==> Time: O(n^2)
        //
        //- Brainstorm
        //Example 1:
        //Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
        //Output: 5
        //Explanation: One way to remove 5 stones is as follows:
        //1. Remove stone [2,2] because it shares the same row as [2,1].
        //2. Remove stone [2,1] because it shares the same column as [0,1].
        //3. Remove stone [1,2] because it shares the same row as [1,0].
        //4. Remove stone [1,0] because it shares the same column as [0,0].
        //5. Remove stone [0,1] because it shares the same row as [0,0].
        //Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
        //
        //rs = [
        //  1 1 0
        //  1 0 1
        //  0 1 1
        //]
        //==>
        //rs = [
        //  1 0 0
        //  0 0 0
        //  0 0 0
        //]
        //- Ta sẽ chọn xoá các nodes:
        //  + Sao cho mỗi col và row ==> Có ít nhất 1 node
        //  ==> Hiểu sai đề
        //* Đề:
        //- Ta sẽ tìm cách remove nhiều nhất có thể:
        //  + Node được remove phải:
        //      + Chung (row or col) với node nào đó chưa bị removed
        //
        //- col: 0,1,2
        //  + count = [2,2,2]
        //- row: 0,1,2
        //  + count = [2,2,2]
        //
        //- Không chọn remove node mà:
        //  + Không thể traverse qua node khác.
        //Ex:
        //rs = [
        //  1 1 0
        //  1 0 1
        //  0 1 1
        //]
        //==>
        //rs = [
        //  0 0 0
        //  1 0 0
        //  0 0 0
        //]
        //- Bản chất 1 collection
        //  + Removed được hết các node có connect được với nhau
        //      + Để lại được 1 node cuối cùng
        //- Khi nào thì connect:
        //  ==> Cùng col, list các trong 1 row/ col:
        //  + traverse dần dần là được.
        //- DFS là được
        //- Remove bằng cách mark =0 là được
        //
        //- Special case:
        //stones = [[0,1],[1,0]]
        //0 1
        //1 0
        //
        //0 0 0 0
        //1 0 1 1
        //0 1 0 0
        //0 1 0 1
        //
        //0 0 0 0
        //0 0 0 0
        //0 1 0 0
        //0 0 0 0
        //
        //0 0 0 0
        //1 0 0 0
        //0 1 0 0
        //0 0 0 0
        //+ Bị case :
        //  + 1 node không connect đến được với nhau nằm ở 2 đầu của collection:
        //      + Do không connect đến bất kỳ điểm nào ==> Không thể removed được
        //          + Mặc dù (0,1) có thể remove được nếu được xét trước:
        //          Ex: [0,1] => [1,2] => [1,3]
        //
        //- (Số lượng node trong collection) - 1 chính là số node có thể removed được
        //  ==> DFS all là xong.
        //
//        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
//        int[][] stones = {{0,1},{1,0}};
        int[][] stones = {{1,2},{1,3},{3,3},{3,1},{2,1},{1,0}};
        //
        //A -> B -> C
        //- Đứng ở A (B xuất hiện)
        //  + Remove A
        //- Đứng ở B (C xuất hiện)
        //  + Remove B
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        System.out.println(removeStones(stones));
        //#Reference:
        //2347. Best Poker Hand
        //117. Populating Next Right Pointers in Each Node II
        //2831. Find the Longest Equal Subarray
        //
    }
}
