package E1_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E37_KillProcess {

    public static void getSubProcess(HashMap<Integer, HashSet<Integer>> adjNodes, int pid, List<Integer> rs){
        rs.add(pid);
        HashSet<Integer> curSubProcesses=adjNodes.get(pid);

        if(curSubProcesses==null){
            return;
        }
        for(Integer p:curSubProcesses){
            getSubProcess(adjNodes, p, rs);
        }
    }

    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, HashSet<Integer>> adjNodes=new HashMap<>();
        int n=pid.size();

        for(int i=0;i<n;i++){
            if(ppid.get(i)==0){
                continue;
            }
            int curPid=ppid.get(i);
            HashSet<Integer> adj=adjNodes.get(curPid);

            if(!adjNodes.containsKey(curPid)){
                adj=new HashSet<>();
                adjNodes.put(curPid, adj);
            }
            adj.add(pid.get(i));
        }
        List<Integer> rs=new ArrayList<>();
        getSubProcess(adjNodes, kill, rs);

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two integer arrays (pid) and (ppid),
        // where
        //+ pid[i] is the ID of the (ith) process
        //+ ppid[i] is the ID of the (ith) process's (parent process).
        //- Rules:
        //+ Each process has only one (parent process) but may have (multiple) (children processes).
        //+ Only one process has ppid[i] = 0,
        // which means this process has no (parent process) (the "root" of the tree).
        //* Given an integer kill representing the ID of a process you want to kill,
        // return (a list of the IDs) of (the processes) that will be killed.
        //- You may return the answer in (any order).
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == pid.length
        //n == ppid.length
        //1 <= n <= 5 * 10^4
        //1 <= pid[i] <= 5 * 10^4
        //0 <= ppid[i] <= 5 * 10^4
        //+ Only one process has no parent.
        //+ All the values of pid are unique.
        //+ kill is guaranteed to be in pid.
        //--> Pid là unique thế nên ta có thể dùng hashmap để xử lý
        //
        //- Brainstorm
        //- Dùng HashMap để lưu lại danh sách kề của pid
        //==> Sau đó dùng DFS để
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of processes.
        //- H is the height of tree
        //
        //- Space : O(N+H)
        //- Time : O(N)
        //#Reference:
        //2569. Handling Sum Queries After Update
        //110. Balanced Binary Tree
        //1293. Shortest Path in a Grid with Obstacles Elimination
    }
}
