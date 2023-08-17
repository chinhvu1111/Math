package E1_Topological_sort;

public class E6_CountSubtreesWithMaxDistanceBetweenCities {
    public static int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        return null;
    }

    public static void main(String[] args) {
        //** Requirement
        //- n cities
        //- We have a tree of cities
        //
        //- There are (n cities) numbered from 1 to n. You are given an array edges of size n-1,
        // where edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi.
        //- A subtree is a subset of cities where every city is reachable from every other city in the subset,
        // where the path between each pair passes through only the cities from the subset.
        //- Two subtrees are different if there is a city in one subtree that is not present in the other.
        //
        //      1
        //    /   \
        //   4    5
        //          3
        //        /
        //      1
        //    /   \
        //   4    5
        //- 2 cái này cùng 1 tree nhưng 3 không có trong tree 1 ==> 2 subtree
        //- Return each d from 1 to (n-1):
        //  - (Maximum distance) between any (two cities) in the subtree is equal to (d)
        //  - Distance : the number of edges between them.
        //* return array of size (n-1) where d-th is the number of trees having (the maximum depth == d)
        //
        //** Idea
        //*
        //1.
        //1.0,
        //- Constraint
        //2 <= n <= 15
        //edges.length == n-1
        //edges[i].length == 2
        //1 <= ui, vi <= n
        //All pairs (ui, vi) are distinct.
        //
        //- Brainstorm
        //- 1 to n-1
        //- How to find all subtrees :
        //Example:
        //          1
        //          |
        //          2
        //        /   \
        //      3      4
        //d = 1 : (1,2), (2,3), (2,4)
        //d = 2 : (1,2,3), (1,2,4), (2,3,4), (1,2,3,4)
        //d = 3 : 0
        //
        //- Max distance between two nodes
        //- Tính khoảng cách giữa 2 leaf node bất kỳ trong tree
        //  + Bỏ qua các node không phải là leaf node? Không vì ta cần phải xét all subtrees thay vì chỉ 1 tree như trước
        //
        //- Tìm all subtrees + (max distance giữa 2 cities) = d
        //- Làm thế nào để (nhận diện/ cố định) được 1 tree ==> Cách ta chọn root
        //+ Vì distance giữa các cities sẽ không đổi ==> Ta sẽ chọn được root.
        //
        //Example:
        //               1
        //             /     \
        //          2          5
        //        /   \     /   \   \
        //      3      4   8     7   6
        //                        \
        //                         9
        //d=1 : 1-2, 2-3, 2-4, 1-15, 5-8, 5-7, 7-9
        //- Để có 1 subtrees --> Ta sẽ chọn root ==> Sau đó quét các độ sâu phù hợp
        //
        //- Mỗi root --> Có thể có rất subtrees
        //VD: root=5 có nhiều subtree có root=5:
        //{5,8}, {5,7}, {5,8,7}, {5,8,7,9}
        //* Có vẻ liên quan khoảng cách d ==> Có thể dynamic programming được không?
        //Example:
        //root=5 : dmax=3
        //+ dp[5][1]= 4
        //+ dp[5][2]= 3
        //+ dp[5][3]= 2
        //==> Không có pattern gì
        //
        //- Chiều dài max giữa 2 điểm trong 1 subtrees
        //==> Có thể chuyển thành bài toán chọn root ==> (Tìm all subtree có root=x + depth=d)
        //Example:
        //- root=5 :
        //+ d=1 : {5,8}, {5,7}, {5,6}
        //+ d=2 : {5,7,9},{5,8,7,9}, {5,7,9,6}, {5,7,9,8,6}
        //--> Max depth = 2
        //
        //- root=8 :
        //+ d=1 : {8,5}
        //+ d=2 : {8,5,7}, {8,5,1}, {8,5,6}
        //+ d=3 : {8,5,1,2}, {8,5,7,9}
        //
        //- Làm sao để lưu tree đã đi rồi :
        //+ Bit mask: 1 --> n ==> Có thể lưu danh sách các node đã dùng
        // 0000... --> 1111...
        //
        //** Kinh nghiệm:
        //- Khi muốn lưu trace 1 collection : ==> bit mask to cache
        //
        //- Ct truy hồi:
        //+ Tại root : dp[root][d]= sum(dp[subnode][d-1])???
        //+ Lưu cache
        //
        //- Câu hỏi là nếu chọn 8 là root --> root back to 5 (root cũ thì giá trị sẽ tính như thế nào nếu dùng dynamic programming)
        //* ==> Tư duy chọn nhiều root sai vì có thể nó sẽ quay ngược lại được.
        //
        //* Qyy luật:
        //- Tư root ta có :
        //+ 2 branch có độ sâu = 2
        //+ 3 branch có độ sâu = 1
        //==> Ta có (2*3=6) đường kết nối giữa 2 cities có độ dài = 2+3=5
        //+ dp[r][d] : là số nhánh có (độ sâu = d) với (root=r)
        //+ result[d] : là kết quả
        //
        //- Special test case:
        //Ex:
        //n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
        //0 : -1
        //1 : -1 : 6
        //2 : 1 : 5
        //3 : 0 : 6
        //4 : 0 : 3
        //5 : 1 :
        //6 : 0 : 4
        //7 : -1 : 0
        //
        // (3,4,6), (2,5), 0, 1, 7
        //              3
        //                \
        //                  4
        //                    \
        //                     6
        //      0   1   7
        //          2
        //            \
        //              5
        // 3 --> 6 : return false (Cycle)
        //
        //1.1. Implementation
        //- CT:
        //+ dp[root][d]= sum(dp[subnode][d-1])
        //
    }
}
