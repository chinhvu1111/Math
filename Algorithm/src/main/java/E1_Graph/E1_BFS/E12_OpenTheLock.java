package E1_Graph.E1_BFS;

import java.util.*;

public class E12_OpenTheLock {

    //0101
    //->
    //1101
    //9101
    public static void getStringPermutation(int index, String s, List<String> listStr){
        if(index==4){
            listStr.add(s);
            return;
        }
        for(int i=index;i<4;i++){
            StringBuilder curS=new StringBuilder(s);
            int curNum=curS.charAt(i)-'0';
            int left=curNum==0?9:curNum;
            int right=curNum==9?0:curNum+1;
            curS.setCharAt(i, (char)(right+'0'));
            getStringPermutation(index+1, curS.toString(), listStr);
            curS.setCharAt(i, (char)(curNum+'0'));
            curS.setCharAt(i, (char)(left+'0'));
            getStringPermutation(index+1, curS.toString(), listStr);
        }
    }

    public static int openLock(String[] deadends, String target) {
        HashSet<String> block = new HashSet<>(Arrays.asList(deadends));
        if(block.contains("0000")){
            return -1;
        }
        if("0000".equals(target)){
            return 0;
        }
        HashSet<String> visited=new HashSet<>();

        Queue<String> nodes=new LinkedList<>();
        HashMap<String, Integer> depthStr=new HashMap<>();
        nodes.add("0000");
        depthStr.put("0000", 0);
        visited.add("0000");

        while(!nodes.isEmpty()){
            String currentStr=nodes.poll();
            System.out.println(currentStr);
            for(int i=0;i<4;i++){
                StringBuilder curS=new StringBuilder(currentStr);
                int curNum=curS.charAt(i)-'0';
                int left=curNum==0?9:curNum-1;
                int right=curNum==9?0:curNum+1;
                curS.setCharAt(i, (char)(right+'0'));
                String curCase=curS.toString();
                if(!visited.contains(curCase)&&!block.contains(curCase)){
                    if(curCase.equals(target)){
                        return depthStr.get(currentStr)+1;
                    }
                    depthStr.put(curCase, depthStr.get(currentStr)+1);
                    visited.add(curCase);
                    nodes.add(curCase);
                    System.out.printf("Next string: %s\n", curCase);
                }
                curS.setCharAt(i, (char)(curNum+'0'));
                curS.setCharAt(i, (char)(left+'0'));
                curCase=curS.toString();
                if(!visited.contains(curCase)&&!block.contains(curCase)){
                    if(curCase.equals(target)){
                        return depthStr.get(currentStr)+1;
                    }
                    depthStr.put(curCase, depthStr.get(currentStr)+1);
                    visited.add(curCase);
                    nodes.add(curCase);
                    System.out.printf("Next string: %s\n", curCase);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- You have a lock in front of you with 4 circular wheels.
        // Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
        // The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
        // Each move consists of turning one wheel one slot.
        //The lock initially starts at '0000', a string representing the state of the 4 wheels.
        //You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
        //Given a target representing the value of the wheels that will unlock the lock,
        //* Return the minimum total number of turns required to open the lock, or -1 if it is impossible.
        //- Tức là cho 1 danh sách các circular wheels
        //  + Nếu dừng ở đây thì không đi tiếp được
        //* Return minimum total number of turns để có thể reach đến target.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= deadends.length <= 500
        //deadends[i].length == 4
        //target.length == 4
        //target will not be in the list deadends.
        //target and deadends[i] consist of digits only.
        //- Chiều dài chuỗi không lớn --> String thường được.
        //
        //- Brainstorm
        //- Bài này dùng BFS + hashtable là được.
        //  + BFS đường đi ngắn nhất.
        //  + Hashtable để lưu thông tin check.
        //
//        List<String> testList= new ArrayList<>();
//        getStringPermutation(0, "0201", testList);
//        System.out.println(testList);
        //1.1, Optimization
        //1.2, Complexity
        //
        //- Space: O(V+E)
        //  + O(9999)
        //- Time: O(V+E)
        //  + O(9999)
        //
        //#Reference:
        //2368. Reachable Nodes With Restrictions
        //
        String[] deadends={"0201","0101","0102","1212","2002"};
        String target="0202";
//        String[] deadends={"0000"};
//        String target="8888";
        System.out.println(openLock(deadends, target));
    }
}
