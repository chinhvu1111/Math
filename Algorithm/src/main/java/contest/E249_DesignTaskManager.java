package contest;

import java.util.*;

public class E249_DesignTaskManager {

    static class TaskManager {

        public class Node{
            int taskId;
            int userId;
            int priority;
            public Node(int taskId, int userId, int priority){
                this.taskId=taskId;
                this.userId=userId;
                this.priority=priority;
            }
        }

        HashMap<Integer, Node> mapTaskIds;
        TreeSet<Node> listTasks;

        public TaskManager(List<List<Integer>> tasks) {
            mapTaskIds=new HashMap<>();
            listTasks=new TreeSet<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if(o1.priority!=o2.priority){
                        return o1.priority-o2.priority;
                    }
                    if(o1.taskId!=o2.taskId){
                        return o1.taskId-o2.taskId;
                    }
                    return o1.userId-o2.userId;
                }
            });

            for (List<Integer> e: tasks){
                Node curNode = new Node(e.get(1), e.get(0), e.get(2));
                listTasks.add(curNode);
                mapTaskIds.put(curNode.taskId, curNode);
            }
        }

        public void add(int userId, int taskId, int priority) {
            Node newNode = new Node(taskId, userId, priority);
            listTasks.add(newNode);
            mapTaskIds.put(taskId, newNode);
        }

        public void edit(int taskId, int newPriority) {
            Node oldNode = mapTaskIds.get(taskId);
            listTasks.remove(oldNode);
            oldNode.priority=newPriority;
            listTasks.add(oldNode);
//            Node newNode = new Node(oldNode.taskId, oldNode.userId, newPriority);
        }

        public void rmv(int taskId) {
            Node oldNode = mapTaskIds.get(taskId);
            listTasks.remove(oldNode);
            mapTaskIds.remove(taskId);
        }

        public int execTop() {
            if(listTasks.isEmpty()){
                return -1;
            }
            Node highestTask = listTasks.pollLast();
            mapTaskIds.remove(highestTask.taskId);
            return highestTask.userId;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a task management system that allows users to manage their tasks, each associated with (a priority).
        //- The system should efficiently handle adding, modifying, executing, and removing tasks.
        //Implement the TaskManager class:
        //
        //TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples.
        // Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified user with the given priority.
        //- void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user with userId.
        //  + It is guaranteed that (taskId does not exist in the system).
        //- void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority.
        //  + It is guaranteed that taskId exists in the system.
        //- void rmv(int taskId) removes the task identified by taskId from the system.
        //  + It is guaranteed that taskId exists in the system.
        //- int execTop() executes the task with the highest priority across all users.
        //  + If there are multiple tasks with the same highest priority, execute the one with the highest taskId.
        // After executing, the taskId is removed from the system. Return the userId associated with the executed task.
        //  + If no tasks are available, return -1.
        //Note that a user may be assigned multiple tasks.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //
        //
        //- Brainstorm
        //- Sort by priority for all of users
        //- Edit the priority for the task with the task_id
        //- remove the task_id
        //- Execute the highest priority task
        //
        List<List<Integer>> tasks=new ArrayList<>();
        Integer[][] taskArr=new Integer[][]{{1,101,10},{2,102,20},{3,103,15}};
        for(Integer[] e: taskArr){
            tasks.add(Arrays.asList(e));
        }
        TaskManager t = new TaskManager(tasks);
        //
        t.add(4,104,5);
        t.edit(102, 8);
        System.out.println(t.execTop());
    }
}
