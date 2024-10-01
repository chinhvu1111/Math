package E1_weekly;

import java.util.*;

public class E15_SmallestCommonRegion {

    public static String commonParent;
    public static boolean[] solution(String curRegion, HashMap<String, HashSet<String>> graph, String region1, String region2){
        HashSet<String> children = graph.get(curRegion);
        boolean[] curRs={curRegion.equals(region1), curRegion.equals(region2)};
        if(curRs[0]&&curRs[1]){
            commonParent=curRegion;
            return curRs;
        }
        if(children==null){
            return curRs;
        }
        if(children.contains(region1)&&children.contains(region2)){
            if(commonParent==null){
                commonParent=curRegion;
            }
            return new boolean[]{true,true};
        }
        boolean isValid=false;
        for (String child: children){
            boolean[] tmpRs = solution(child, graph, region1, region2);
            curRs=new boolean[]{curRs[0]|tmpRs[0], curRs[1]|tmpRs[1]};
            if(curRs[0]&&curRs[1]){
                isValid=true;
                break;
            }
        }
        if(isValid&&commonParent==null){
            commonParent=curRegion;
        }
        return curRs;
    }

    public static String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        commonParent=null;
        HashMap<String, HashSet<String>> graph=new HashMap<>();

        for(List<String> r: regions){
            String parentRegion=r.get(0);
            HashSet<String> adj=new HashSet<>();
            int m=r.size();
            for(int i=1;i<m;i++){
                adj.add(r.get(i));
            }
            graph.put(parentRegion, adj);
        }
//        System.out.println(graph);
        solution(regions.get(0).get(0), graph, region1, region2);
        return commonParent;
    }

    public static String solutionOptimization(String curRegion, HashMap<String, List<String>> graph, String region1, String region2){
        if(curRegion==null||curRegion.equals(region1)||curRegion.equals(region2)){
            return curRegion;
        }
        List<String> adj=graph.get(curRegion);
        if(adj==null){
            return null;
        }
        int count=0;
        String matchRegion = null;
        for(String nextRegion: adj){
            String tmpRegion = solutionOptimization(nextRegion, graph, region1, region2);
            if(tmpRegion!=null){
                matchRegion=tmpRegion;
                count++;
            }
            if(count==2){
                break;
            }
        }
        if(count==2){
            return curRegion;
        }
        return matchRegion;
    }

    public static String findSmallestRegionClassic(List<List<String>> regions, String region1, String region2){
        HashMap<String, List<String>> graph=new HashMap<>();

        for(List<String> r: regions){
            String parentRegion=r.get(0);
            List<String> adj=new ArrayList<>();
            int m=r.size();
            for(int i=1;i<m;i++){
                adj.add(r.get(i));
            }
            graph.put(parentRegion, adj);
        }
//        System.out.println(graph);
        return solutionOptimization(regions.get(0).get(0), graph, region1, region2);
    }

    private static List<String> fetchPathForRegion(
            String currNode,
            Map<String, String> childParentMap
    ) {
        List<String> path = new ArrayList<>();
        // Start by adding the current node to the path.
        path.add(currNode);

        // Traverse upwards through the tree by finding the parent of the
        // current node. Continue until the root node is reached.
        while (childParentMap.containsKey(currNode)) {
            String parentNode = childParentMap.get(currNode);
            path.add(parentNode);
            currNode = parentNode;
        }
        // Reverse the path so that it starts from the root and
        // ends at the given current node.
        Collections.reverse(path);
        return path;
    }

    public static String findSmallestRegionRefer(
            List<List<String>> regions,
            String region1,
            String region2
    ) {
        // Map to store (child -> parent) relationships for each region.
        Map<String, String> childParentMap = new HashMap<>();

        // Populate the 'childParentMap' using the provided 'regions' list.
        for (List<String> regionArray : regions) {
            String parentNode = regionArray.get(0);
            for (int i = 1; i < regionArray.size(); i++) {
                childParentMap.put(regionArray.get(i), parentNode);
            }
        }

        // Store the paths from the root node to 'region1' and 'region2'
        // nodes in their respective lists.
        List<String> path1 = fetchPathForRegion(region1, childParentMap);
        List<String> path2 = fetchPathForRegion(region2, childParentMap);

        int i = 0, j = 0;
        String lowestCommonAncestor = "";
        // Traverse both paths simultaneously until the paths diverge.
        // The last common node is the lowest common ancestor.
        while (
                i < path1.size() &&
                        j < path2.size() &&
                        path1.get(i).equals(path2.get(j))
        ) {
            lowestCommonAncestor = path1.get(i);
            i++;
            j++;
        }

        // Return the lowest common ancestor of 'region1' and 'region2'.
        return lowestCommonAncestor;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are (given some lists of regions) where (the first region) of (each list) includes (all other regions) in that list.
        //- Naturally, if (a region x) contains (another region y) then (x is bigger than y).
        //  + Also, by definition, (a region x contains itself).
        //Given two regions: region1 and region2,
        //* return (the smallest region) that (contains both of them).
        //- If you are given regions r1, r2, and r3 such that (r1 includes r3),
        //  + it is guaranteed there is (no r2) such that (r2 includes r3).
        //- It is guaranteed (the smallest region) exists.
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= regions.length <= 10^4
        //2 <= regions[i].length <= 20
        //1 <= regions[i][j].length, region1.length, region2.length <= 20
        //region1 != region2
        //regions[i][j], region1, and region2 consist of English letters.
        //
        //- Brainstorm
        //Example 1:
        //Input:
        //regions = [["Earth","North America","South America"],
        //["North America","United States","Canada"],
        //["United States","New York","Boston"],
        //["Canada","Ontario","Quebec"],
        //["South America","Brazil"]],
        //region1 = "Quebec",
        //region2 = "New York"
        //Output: "North America"
        //- Earth includes North America, South America
        //  + "North America" includes ("United States","Canada")
        //  ==> Tiếp thế đến hết
        //
        //- Smallest region include all of regions:
        //  + It means the common parent of both of regions
        //Ex:
        //        4
        //      /
        //    1
        //  /  \
        // 2    3
        //- Nearest parent = 1
        //- Preorder traverse để tìm common parent.
        //- Để dễ thì ta sẽ dùng DFS trước:
        //  + Graph này là tree
        //  + Mỗi node có n children
        //- Method for each node will return boolean[]{a,b}
        //  + a==true: region1 exist in this node
        //  + b==true: region1 exist in this node
        //
        //- assign result only one time ==> static or input value.
        //1.1, Optimization
        //- Nếu dùng recursive approach thì sẽ phải traverse back về ==> không break được luôn
        //  + Sẽ take time
        //- Ta có thể dùng BFS được không
        //
        //* Thực ra dạng này classic rồi:
        //  + Cần sửa lại cách làm ngay
        //  - Return lại chính node thoả mãn:
        //      + node==region1
        //      + node==region2
        //  - Sau đó check count = 2 là break
        //
        //* KINH NGHIỆM:
        //  + Tìm path từ (leaf ==> root)
        //- 1 cách khác là tìm path traverse từ (region1, region2 ==> root)
        //- Lấy 2 paths xong dùng 2 pointers:
        //  + Tìm path ==> List add liên tục:
        //  + Đến cuối reverse + return
        //
        //1.2, Complexity
        //- Size of list regions
        //- m is average size of the adj regions
        //- Space: O(n)
        //- Time: O(n*m)
        //
        String[][] regions = {{"Earth","North America","South America"},
                {"North America","United States","Canada"},
                {"United States","New York","Boston"},
                {"Canada","Ontario","Quebec"},
                {"South America","Brazil"}};
        String region1 = "Quebec";
        String region2 = "New York";
        List<List<String>> listRegion=new ArrayList<>();
        for(String[] r: regions){
            listRegion.add(Arrays.asList(r));
        }
        System.out.println(findSmallestRegion(listRegion, region1, region2));
        System.out.println(findSmallestRegionClassic(listRegion, region1, region2));
        System.out.println(findSmallestRegionRefer(listRegion, region1, region2));
    }
}
