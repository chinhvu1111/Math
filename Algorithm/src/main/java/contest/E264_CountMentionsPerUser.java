package contest;

import java.util.*;

public class E264_CountMentionsPerUser {

    public static int[] countMentions(int numberOfUsers, List<List<String>> events) {
        Collections.sort(events, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                if(!o1.get(1).equals(o2.get(1))){
                    return Integer.parseInt(o1.get(1))-Integer.parseInt(o2.get(1));
                }
                if(!o1.get(0).equals("MESSAGE")){
                    return -1;
                }
                return 1;
            }
        });
        int[] rs=new int[numberOfUsers];
        int[] lastSleepTime =new int[numberOfUsers];
        int size=events.size();
        TreeSet<int[]> sleepTime=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o2[1]-o1[1];
            }
        });
        HashSet<Integer> onlineUsers=new HashSet<>();
        for (int i = 0; i < numberOfUsers; i++) {
            onlineUsers.add(i);
        }

        for(int i=0;i<size;i++){
            List<String> curInfo=events.get(i);
            String curMsg=curInfo.get(0);
            int curTimestamp=Integer.parseInt(curInfo.get(1));
            String notificationInfo = curInfo.get(2);
            if(curMsg.equals("MESSAGE")){
                while(!sleepTime.isEmpty()&&sleepTime.first()[0]+60<=curTimestamp){
                    int[] curUser = sleepTime.pollFirst();
                    onlineUsers.add(curUser[1]);
                }
                if(notificationInfo.equals("HERE")){
                    for (Integer e: onlineUsers) {
                        rs[e]++;
                    }
                }
                else if(notificationInfo.equals("ALL")){
                    for (int j = 0; j < numberOfUsers; j++) {
//onlineUsers.add(j);
                        rs[j]++;
                    }
                }else{
                    String[] listId = curInfo.get(2).replace("id", "").split(" ");
//                    HashSet<String> setUser = new HashSet<>(Arrays.asList(listId));
                    for(String s: listId){
                        int curId=Integer.valueOf(s);
//onlineUsers.add(curId);
                        rs[curId]++;
                    }
                }
            }else{
                Integer curOfflineId = Integer.parseInt(notificationInfo);
                onlineUsers.remove(curOfflineId);
                sleepTime.remove(new int[]{lastSleepTime[curOfflineId], curOfflineId});
                sleepTime.add(new int[]{curTimestamp, curOfflineId});
                lastSleepTime[curOfflineId]=curTimestamp;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer numberOfUsers representing the total number of users and an array events of size n x 3.
        //- Each events[i] can be either of the following two types:
        //- Message Event: ["MESSAGE", "timestampi", "mentions_stringi"]
        //  + This event indicates that a set of users was mentioned in a message at timestampi.
        //  + The mentions_stringi string can contain one of the following tokens:
        //      + id<number>: where <number> is an integer in range [0,numberOfUsers - 1].
        //      There can be multiple ids separated by a single whitespace and may contain duplicates.
        //      This can mention even the offline users.
        //      + ALL: mentions all users.
        //      + HERE: mentions all online users.
        //- Offline Event: ["OFFLINE", "timestampi", "idi"]
        //  + This event indicates that the user idi had become offline at timestampi for 60 time units.
        //  The user will automatically be online again at time timestampi + 60.
        //* Return an array mentions where mentions[i] represents (the number of mentions) the user
        // with id i has across (all MESSAGE events).
        //
        //- All users are initially online, and if a user goes offline or comes back online,
        // their status change is processed before handling any message event that occurs at the same timestamp.
        //- Note that a user can be mentioned multiple times in a single message event,
        // and each mention should be counted separately.
        //
        //Example 1:
        //
        //Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]
        //Output: [2,2]
        //Explanation:
        //
        //Initially, all users are online.
        //  + At timestamp 10, id1 and id0 are mentioned. mentions = [1,1]
        //  + At timestamp 11, id0 goes offline.
        //  + At timestamp 71, id0 comes back online and "HERE" is mentioned. mentions = [2,2]
        //
        //Example 2:
        //
        //Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","12","ALL"]]
        //Output: [2,2]
        //Explanation:
        //Initially, all users are online.
        //  + At timestamp 10, id1 and id0 are mentioned. mentions = [1,1]
        //  + At timestamp 11, id0 goes offline.
        //  + At timestamp 12, "ALL" is mentioned.
        //  This includes offline users, so both id0 and id1 are mentioned. mentions = [2,2]
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= numberOfUsers <= 100
        //1 <= events.length <= 100
        //events[i].length == 3
        //events[i][0] will be one of MESSAGE or OFFLINE.
        //1 <= int(events[i][1]) <= 105
        //The number of id<number> mentions in any "MESSAGE" event is between 1 and 100.
        //0 <= <number> <= numberOfUsers - 1
        //It is guaranteed that the user id referenced in the OFFLINE event is online at the time the event occurs.
        //  + numberOfUsers, events.length <= 100
        //      + Time: O((n*m)^2)
        //
        //- Brainstorm
        //- a user is offline:
        //  + Sleeping time
        //  ==> Priority queue
        //
//        int numberOfUsers = 2;
//        String[][] events = {{"MESSAGE","10","id1 id0"},{"OFFLINE","11","0"},{"MESSAGE","71","HERE"}};
//        int numberOfUsers = 2;
//        String[][] events = {{"MESSAGE","10","id1 id0"},{"OFFLINE","11","0"},{"MESSAGE","70","HERE"}};
//        int numberOfUsers = 2;
//        String[][] events = {{"MESSAGE","10","id1 id0"},{"OFFLINE","11","0"},{"MESSAGE","70","HERE"}, {"MESSAGE","72","HERE"}};
//        int numberOfUsers = 2;
//        String[][] events = {{"MESSAGE","10","id1 id0"},{"OFFLINE","11","0"},{"MESSAGE","70","ALL"}};
//        int numberOfUsers = 2;
//        String[][] events = {{"OFFLINE","10","0"},{"MESSAGE","12","HERE"}};
//        int numberOfUsers =3;
//        String[][] events = {{"MESSAGE","2","HERE"},{"OFFLINE","2","1"},{"OFFLINE","1","0"},{"MESSAGE","61","HERE"}};
        // {"OFFLINE","1","0"}
        //[1,2]
        // {"OFFLINE","2","1"}
        //[2]
        //{{"MESSAGE","2","HERE"}
        //[2]
        //rs = [0,0,1]
        // {"MESSAGE","61","HERE"}}
        //[2]
        //Output:
        //[2,1,2]
        //Expected:
        //[1,0,2]
//        int numberOfUsers =3;
//        String[][] events = {{"MESSAGE","1","ALL"},{"OFFLINE","66","1"},{"MESSAGE","66","HERE"},{"OFFLINE","5","1"}};
//        int numberOfUsers = 3;
//        String[][] events = {
//                {"MESSAGE","5","id0 id1"},
//                {"OFFLINE","10","0"},
//                {"OFFLINE","15","1"},
//                {"MESSAGE","20","ALL"},
//                {"OFFLINE","30","2"},
//                {"MESSAGE","40","HERE"}};
        //{{"MESSAGE","1","ALL"}
        //online = [0,1,2]
        //[1,1,1]
        //{"OFFLINE","5","1"}}
        //online = [0,2]
        //[1,1,1]
        //{"OFFLINE","66","1"}
        //online = [0,2]
        //
        //{"MESSAGE","66","HERE"}
        //[2,1,2]
        //Output: [2,2,2]
        //Expected: [2,1,2]
        //
        int numberOfUsers = 5;
        String[][] events =
                {
                        {"OFFLINE","28","1"},
                        {"OFFLINE","14","2"},
                        {"MESSAGE","2","ALL"},
                        {"MESSAGE","28","HERE"},
                        {"OFFLINE","66","0"},
                        {"MESSAGE","34","id2"},
                        {"MESSAGE","83","HERE"},
                        {"MESSAGE","40","id3 id3 id2 id4 id4"}
                };
        //{"MESSAGE","2","ALL"},
        //  + online = [0,1,2,3,4]
        //  + sleep = [0,0,0,0,0]
        //  + rs = [1,1,1,1,1]
        //{"OFFLINE","14","2"},
        //  + online = [0,1,3,4]
        //  + sleep = [0,0,14,0,0]
        //  + rs = [1,1,1,1,1]
        //{"OFFLINE","28","1"},
        //  + online = [0,3,4]
        //  + sleep = [0,28,14,0,0]
        //  + rs = [1,1,1,1,1]
        //{"MESSAGE","28","HERE"},
        //  + online = [0,3,4]
        //  + sleep = [0,28,14,0,0]
        //  + rs = [2,1,1,2,2]
        //{"MESSAGE","34","id2"},
        //  + online = [0,3,4]
        //  + sleep = [0,28,14,0,0]
        //  + rs = [2,1,2,2,2]
        //{"MESSAGE","40","id3 id3 id2 id4 id4"}
        //  + online = [0,3,4]
        //  + sleep = [0,28,14,0,0]
        //  + rs = [2,1,3,3,3]
        //{"OFFLINE","66","0"},
        //  + online = [0,1,2,3,4]
        //  + sleep = [0,28,14,0,0]
        //  + rs = [2,1,3,3,3]
        //{"MESSAGE","83","HERE"},
        //  + online = [0,1,2,3,4]
        //  + sleep = [0,28,14,0,0]
        //  + rs = [2,1,4,4,4]
        //Output: [2,1,4,4,4]
        //Expected: [2,1,4,5,5]
        List<List<String>> eventList=new ArrayList<>();

        for(String[] s: events){
            eventList.add(Arrays.asList(s));
        }
        int[] rs=countMentions(numberOfUsers, eventList);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s ", rs[i]);
        }
    }
}
