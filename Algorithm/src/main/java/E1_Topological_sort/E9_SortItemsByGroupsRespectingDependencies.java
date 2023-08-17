package E1_Topological_sort;

import java.util.*;

public class E9_SortItemsByGroupsRespectingDependencies {

    public static List<Integer> topologicalSort(int m, List<Integer>[] inGroups, List<Integer>[] adjNodes,
                                       int[] inDegree, HashMap<Integer, Integer> itemToGroup){
        //Chuyển thành list item ==> Để tránh các item không có group
        Queue<Integer> queueGroups=new LinkedList<>();
        boolean[] visited=new boolean[adjNodes.length];
        boolean[] visitedGroup=new boolean[m];

        //Chắc chắn là sẽ không cùng group
        for(int i=0;i<m;i++){
//            System.out.println(inDegree[i]);
            if(inDegree[i]==0){
                queueGroups.addAll(inGroups[i]);
            }
        }
        System.out.printf("Init queue: %s\n", queueGroups);
        List<Integer> rs=new ArrayList<>();
        while (!queueGroups.isEmpty()){
            Integer currentItem= queueGroups.poll();
            if(itemToGroup.containsKey(currentItem)&&!visitedGroup[itemToGroup.get(currentItem)]){
                List<Integer> elementsInGroup=inGroups[itemToGroup.get(currentItem)];
                if(elementsInGroup!=null){
                    for(Integer e:elementsInGroup){
                        rs.add(e);
                        visited[e]=true;
                    }
                }
                visitedGroup[itemToGroup.get(currentItem)]=true;
            }
            List<Integer> adjItems=adjNodes[currentItem];
            System.out.printf("Adj: %s %s\n", currentItem, adjItems);

            if(adjItems==null){
                continue;
            }
            for(Integer curItem:adjItems){
                if(!itemToGroup.containsKey(curItem)){
                    rs.add(curItem);
                    visited[curItem]=true;
                    continue;
                }
                int value=--inDegree[itemToGroup.get(curItem)];

                if(value==0){
                    List<Integer> nextItem=inGroups[itemToGroup.get(curItem)];
                    queueGroups.addAll(nextItem);
                }
            }
        }
        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                rs.add(i);
            }
        }
        return rs;
    }

    public static List<Integer> subTopologicalSort(List<Integer> currentGroup, HashMap<Integer, Integer> indegreeTmp, HashMap<Integer, List<Integer>> adjItemTemp){
        Queue<Integer> queueItems=new LinkedList<>();
        List<Integer> currentRs=new ArrayList<>();

        for(Integer e: currentGroup){
            if(!indegreeTmp.containsKey(e)||indegreeTmp.get(e)==0){
                queueItems.add(e);
            }
        }
        while (!queueItems.isEmpty()){
            int currentItem=queueItems.poll();
            currentRs.add(currentItem);
            List<Integer> adjNodes=adjItemTemp.get(currentItem);
            if(adjNodes==null||adjNodes.size()==0){
                continue;
            }
            for(Integer temp: adjNodes){
                if(!indegreeTmp.containsKey(temp)){
                    continue;
                }
                int value=indegreeTmp.get(temp)-1;
                indegreeTmp.put(temp, value);
                if(value==0){
                    queueItems.add(temp);
                }
            }
        }
        return currentRs;
    }

    public static int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        List<Integer>[] inGroups=new ArrayList[m];
        HashMap<Integer, Integer> itemToGroup=new HashMap<>();
        int[] inDegree=new int[m];

        for(int i=0;i<n;i++){
            if(group[i]==-1){
//                itemToGroup.put(i, -1);
                continue;
            }
            if(inGroups[group[i]]==null){
                inGroups[group[i]]=new ArrayList<>();
            }
            inGroups[group[i]].add(i);
            itemToGroup.put(i, group[i]);
        }
        //Sort group
        for(int i=0;i<m;i++){
            List<Integer> currentGroup=inGroups[i];
            HashSet<Integer> checkExists=new HashSet<>(currentGroup);
            HashMap<Integer, Integer> indegreeTmp=new HashMap<>();
            HashMap<Integer, List<Integer>> adjItemTemp=new HashMap<>();

            for(Integer e:currentGroup){
                for(Integer beforeItem: beforeItems.get(e)){
                    if(checkExists.contains(beforeItem)){
                        indegreeTmp.put(e, indegreeTmp.getOrDefault(e, 0)+1);
                        List<Integer> oldNextItems=adjItemTemp.get(beforeItem);

                        if(oldNextItems==null){
                            oldNextItems=new ArrayList<>();
                        }
                        oldNextItems.add(e);
                        adjItemTemp.put(beforeItem, oldNextItems);
                    }
                }
            }
            System.out.printf("Old : %s\n", currentGroup);
            currentGroup=subTopologicalSort(currentGroup, indegreeTmp, adjItemTemp);
            System.out.printf("New : %s\n", currentGroup);
            inGroups[i]=currentGroup;
        }
//        System.out.println(inGroups.length);
//        for (int i = 0; i < inGroups.length; i++) {
//            System.out.println(inGroups[i]);
//        }
//        System.out.println(itemToGroup);
        List<Integer>[] adjNodes=new ArrayList[n];

        for(int i=0;i<n;i++){
//            if(!itemToGroup.containsKey(i)){
//                continue;
//            }
            List<Integer> beforeElements=beforeItems.get(i);

            for(Integer beforeElement:beforeElements){
                if(adjNodes[beforeElement]==null){
                    adjNodes[beforeElement]=new ArrayList<>();
                }
                if(!Objects.equals(itemToGroup.get(i), itemToGroup.get(beforeElement))||(itemToGroup.get(beforeElement)==null && itemToGroup.get(i)==null)){
//                    System.out.printf("Connection : %s %s\n", i, beforeElement);
                    if(itemToGroup.containsKey(i)){
                        inDegree[itemToGroup.get(i)]++;
                    }
                    adjNodes[beforeElement].add(i);
                }
            }
        }
//        for (int i = 0; i < m; i++) {
//            System.out.printf("%s %s\n", i, inDegree[i]);
//        }
        List<Integer> result=topologicalSort(m, inGroups, adjNodes, inDegree, itemToGroup);
        System.out.println(result);
        int[] rsArr=new int[result.size()];

        for(int i=0;i<result.size();i++){
            rsArr[i]=result.get(i);
        }
        return rsArr;
    }

    public static int[] sortItemsReference(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int groupId=m;
        for(int i=0;i<n;i++){
            if(group[i]==-1){
                group[i]=groupId;
                groupId++;
            }
        }
        HashMap<Integer, List<Integer>> adjNodes=new HashMap<>();
        HashMap<Integer, List<Integer>> groupGraph=new HashMap<>();
        int[] inDegree=new int[n];
        int[] groupInDegree=new int[groupId];

        for(int i=0;i<n;i++){
            adjNodes.put(i, new ArrayList<>());
        }
        for(int i=0;i<groupId;i++){
            groupGraph.put(i, new ArrayList<>());
        }

        for(int i=0;i<n;i++){
            List<Integer> currentBeforeItems=beforeItems.get(i);
            if(currentBeforeItems==null||currentBeforeItems.size()==0){
                continue;
            }
            for(Integer prevNode:currentBeforeItems){
                adjNodes.get(prevNode).add(i);
                inDegree[i]++;

                if(group[i]!=group[prevNode]){
                    groupInDegree[group[i]]++;
                    groupGraph.get(group[prevNode]).add(group[i]);
                }
            }
        }
        List<Integer> sortedNodes=topologicalSortRefer(inDegree, adjNodes);
        List<Integer> groupOrder=topologicalSortRefer(groupInDegree, groupGraph);

        if(sortedNodes.isEmpty() || groupOrder.isEmpty()){
            return new int[0];
        }
        HashMap<Integer, List<Integer>> orderedGroups=new HashMap<>();

        //Add group based on the order of all of elements
        for(Integer e: sortedNodes){
            if(!orderedGroups.containsKey(group[e])){
                orderedGroups.put(group[e], new ArrayList<>());
            }
            orderedGroups.get(group[e]).add(e);
        }
        List<Integer> result=new ArrayList<>();
        //This code means all of group has been sorted --> We prefer sorting by group
        for(int groupIndex: groupOrder){
            result.addAll(orderedGroups.getOrDefault(groupIndex, new ArrayList<>()));
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static List<Integer> topologicalSortRefer(int[] inDegree, HashMap<Integer, List<Integer>> adjNodes){
        Queue<Integer> nodes=new LinkedList<>();
        List<Integer> sortNodes=new ArrayList<>();
//        System.out.printf("Keys: %s\n", adjNodes.keySet());

        for(Integer key: adjNodes.keySet()){
            System.out.printf("Key : %s: adj: %s, indegree: %s\n", key, adjNodes.get(key), inDegree[key]);
            if(inDegree[key]==0){
                nodes.add(key);
            }
        }
//        System.out.printf("Nodes: %s\n", nodes);
        while (!nodes.isEmpty()){
            Integer currentNode=nodes.poll();
            sortNodes.add(currentNode);
            List<Integer> curAdjNodes=adjNodes.get(currentNode);

            if(curAdjNodes!=null){
                for(Integer nextNode: curAdjNodes){
                    int newValue=--inDegree[nextNode];
                    if(newValue==0){
                        nodes.add(nextNode);
                    }
                }
            }
        }
        System.out.printf("Sorted nodes: %s\n", sortNodes);
        return sortNodes.size()==adjNodes.size()?sortNodes:new ArrayList<>();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Sorted list --> list được sắp xếp theo bất kỳ rule nào chứ không phải mỗi (asc, desc)
        //- Cho:
        //+ items, groups, beforeItems:
        //+ Mỗi item có thể thuộc 1 group nào đó ith item sẽ thuộc ith group và có list (ith beforeItems) là danh sách các node đừng trước ith item
        //+ Các item thuộc chung 1 group --> sẽ cạnh nhau trong 1 result sorted list.
        //* return lại any result list có thể tổng hợp được từ items <> nếu không có return empty list
        //
        //** Idea
        //* Method-1:
        //1.
        //1.0,
        //- Constraint
        //1 <= m <= n <= 3 * 10^4
        //group.length == beforeItems.length == n
        //-1 <= group[i] <= m - 1
        //0 <= beforeItems[i].length <= n - 1
        //0 <= beforeItems[i][j] <= n - 1
        //i != beforeItems[i][j]
        //beforeItems[i] does not contain duplicates elements ==> Không duplicated
        //
        //- Brainstorm
        //VD:
        //n = 8, m = 2,
        // group = [-1,-1,1,0,0,1,0,-1],
        // beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
        //          0
        //       /  \   \
        //     3    4    6
        //
        //          1
        //        /   \
        //      2      5
        //- Group [0,1]
        //- Before
        // 6 --> 1
        // 5 --> 2
        // 6 --> 3
        // 3,6 --> 4
        //
        //          6 -- 3
        //        /  \  /
        //      1      4
        //(3,4,6), (2,5)
        // (6, 1, 3, 4) -> Do (3,4,6) cạnh nhau
        //--> Cần duyệt 3 trước.
        //
        //- Sẽ ưu tiên add những node cùng group trước ==> Những nodes còn lại add ở cuối.
        //VD:
        // 3,4,5,6 đều có indegree[i]==0 ==> Ta sẽ phải chuyển về (3,5,6,4)
        //==> Cái việc cùng chùng group ==> Và các before là cùng rule với nhau
        //Tức là cùng chung group ==> Chắc chắn các node phải (có connect lẫn nhau) <> return empty list.
        //VD:
        //          6 -- 3
        //        /  \  /
        //      1      4
        //+ 1 <-- 3
        //(3,4,6), (2,5)
        //
        //- Hint:
        //- Liệu ta có thể phân thành các group và traverse theo topological sort
        //VD:
        //        1      7
        //          \   /
        //          (2,5) - group 1 [inDegree[1]=2]
        //             \
        //          (3,4,6) - group 0 [inDegree[0]=1]
        //
        // 4 <-- 5
        // 2 <-- 1
        // 5 <-- 7
        //
        //     (3,4,6)
        //          \
        //           1
        //
        //     (2,5)
        //
//        int n = 8, m = 2;
//        int[] group = {-1,-1,1,0,0,1,0,-1};
//        int[][] beforeItems = {{},{6},{5},{6},{3,6},{},{},{}};
        //
        //2.0, Chữa
        //2.1,
        //- Nếu đi theo topological sort thì nếu số lượng node visited < số lượng nodes
        //==> Tức là graph có cycle
        //-
        //

        //int n = 8, m = 2;
        //int[] group = {-1,-1,1,0,0,1,0,-1};
        //0 : -1 :
        //1 : -1 : 6
        //2 :  1 : 5
        //3 :  0 : 6
        //4 :  0 : 3
        //5 :  1 :
        //6 :  0 : 4
        //7 : -1 :
        //(3,4,6), (2,5), 0,1,7
        //int[][] beforeItems = {{},{6},{5},{6},{3},{},{4},{}};

        //
        int n = 5, m = 5;
        int[] group = {2,0,-1,3,0};
        //0 :  2 : 2,1,3
        //1 :  0 : 2,4
        //2 : -1 :
        //3 :  3 :
        //4 :  0 :
        //(3,4,6), (2,5), 0,1,7
        int[][] beforeItems = {{2,1,3},{2,4},{},{},{}};
        List<List<Integer>> inputBeforeItems=new ArrayList<>();

        for(int i=0;i<beforeItems.length;i++){
            List<Integer> currentElements=new ArrayList<>();
            for(int j=0;j<beforeItems[i].length;j++){
                currentElements.add(beforeItems[i][j]);
            }
            inputBeforeItems.add(currentElements);
        }
//        sortItems(n, m, group, inputBeforeItems);
        int[] rs=sortItemsReference(n, m, group, inputBeforeItems);
        for (int r : rs) {
            System.out.printf("%s, ", r);
        }
        //#Reference:
        //1857. Largest Color Value in a Directed Graph
        //1791. Find Center of Star Graph
        //426. Convert Binary Search Tree to Sorted Doubly Linked List
        //2556. Disconnect Path in a Binary Matrix by at Most One Flip
    }
}
