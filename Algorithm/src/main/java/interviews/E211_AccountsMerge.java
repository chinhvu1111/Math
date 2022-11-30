package interviews;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class E211_AccountsMerge {

    public static int findParent(int[] parent, int u){
        while (u!=parent[u]){
            u=parent[u];
        }
        return u;
    }

    //u : child
    //v : parent
    public static void union(int[] parent, int[] height, int u, int v){
        int parentU=findParent(parent, u);
        parent[u]=parentU;
        int parentV=findParent(parent, v);
        parent[v]=parentV;

        if(parentU==parentV){
            return;
        }
        if(height[parentU]>height[parentV]){
            parent[parentV]=parentU;
            height[parentU]=Math.max(height[parentV]+1, height[parentU]);
        }else{
            parent[parentU]=parentV;
            height[parentV]=Math.max(height[parentU]+1, height[parentV]);
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, Integer> emailToIndex=new HashMap<>();
        HashMap<Integer, TreeSet<String>> indexToEmails=new HashMap<>();
        int n=accounts.size();
        int[] parent=new int[n];
        int[] height=new int[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
            height[i]=1;
        }

        //- N time
        for(int i=0;i<n;i++){
            for(int j=1;j<accounts.get(i).size();j++){
                String email=accounts.get(i).get(j);

                if(!emailToIndex.containsKey(email)){
                    emailToIndex.put(email, i);
                }else{
                    union(parent, height, i, emailToIndex.get(email));
                }
            }
        }
        //- N*Log(N) time
        for(int i=0;i<n;i++){
            int parentOfCurrentIndex=findParent(parent, i);
            TreeSet<String> currentTreeSet;

            if(indexToEmails.containsKey(parentOfCurrentIndex)){
                currentTreeSet=indexToEmails.get(parentOfCurrentIndex);
            }else{
                currentTreeSet=new TreeSet<>();
            }
            for(int j=1;j<accounts.get(i).size();j++){
                currentTreeSet.add(accounts.get(i).get(j));
            }
            indexToEmails.put(parentOfCurrentIndex, currentTreeSet);
        }
        Set<Map.Entry<Integer, TreeSet<String>>> entries = indexToEmails.entrySet();
        List<List<String>> result=new ArrayList<>();

        //- N time
        for(Map.Entry<Integer, TreeSet<String>> e: entries){
            String name=accounts.get(e.getKey()).get(0);
            List<String> currentList=new ArrayList<>();

            currentList.add(name);
            currentList.addAll(e.getValue());
            result.add(currentList);
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        String[][] s=new String[][]{
//                {"John","johnsmith@mail.com","john_newyork@mail.com"},
//                {"John","johnsmith@mail.com","john00@mail.com"},
//                {"Mary","mary@mail.com"},{"John","johnnybravo@mail.com"}};
//        String[][] s=new String[][]{
//                {"Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"},{"Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"},{"Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"},{"Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"},{"Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"}};
//        String[][] s=new String[][]{
//                {"Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"},{"Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"},{"Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"},{"Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"},{"Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"}};
        //Case liên quan đến hash check contains không được trùng name
//        String[][] s=new String[][]{
//                {"Kevin","Kevin1@m.co","Kevin5@m.co","Kevin2@m.co"},
//                {"Bob","Bob3@m.co","Bob1@m.co","Bob2@m.co"},
//                {"Lily","Lily3@m.co","Lily2@m.co","Lily0@m.co"},
//                {"Gabe","Gabe2@m.co","Gabe0@m.co","Gabe2@m.co"},
//                {"Kevin","Kevin4@m.co","Kevin3@m.co","Kevin3@m.co"}};
        //Case liên quan đến put lại string --> index mới
//        String[][] s=new String[][]{
//                {"David","David0@m.co","David4@m.co","David3@m.co"},
//                {"David","David5@m.co","David5@m.co","David0@m.co"},
//                {"David","David1@m.co","David4@m.co","David0@m.co"},
//                {"David","David0@m.co","David1@m.co","David3@m.co"},
//                {"David","David4@m.co","David1@m.co","David3@m.co"}};
        String[][] s=new String[][]{
                {"David","David0@m.co","David1@m.co"},
                {"David","David3@m.co","David4@m.co"},
                {"David","David4@m.co","David5@m.co"},
                {"David","David2@m.co","David3@m.co"},
                {"David","David1@m.co","David2@m.co"}};
        File file = new File("/home/chinhvu/project-intel/Math/Algorithm/src/main/java/interviews/E211_input.txt");
        Scanner scanner=new Scanner(file);
        List<List<String>> inputFile=new ArrayList<>();

        while (scanner.hasNext()){
            String inputString=scanner.nextLine();
            int length=inputString.length();
            String newString=inputString.substring(2, length-2).replaceAll("\"","");
            String[] elements=newString.split("\\],\\,\\[");
            List<String> currentList=new ArrayList();

            for(String str:elements){
                String[] eachElements=str.split(",");
                currentList.addAll(Arrays.asList(eachElements));
                inputFile.add(currentList);
            }
        }
        List<List<String>> list=new ArrayList<>();

        for(int i=0;i<s.length;i++){

            List<String> currentList = new ArrayList<>(Arrays.asList(s[i]));
            list.add(currentList);
        }
        System.out.println(accountsMerge(inputFile));
        //
        //** Đề bài:
        //- Bài này dùng để merge các mail của cùng 1 người lại
        //- Rule :
        //+ 1 email không thể thuộc cùng 2 người có tên khác nhau --> 1 mail chỉ thuộc 1 tên
        //+ Nếu 2 user có cùng name mà phát hiện giao nhau 1 số email nào đó --> Xác định cùng 1 người ==> Merge
        //VD:
        //A : B, C, D
        //A : B, E
        //
        //** Bài này tư duy như sau:
        //Cách 1: TLE
        //1,
        //1.1,
        //- Travese từng index, cũng dùng dạng check haspMap<Email, Index>
        //+ Nếu email có rồi thì sẽ luôn luôn gắn thằng mới là parent --> add all elements của thằng cũ vào thằng mới
        //+ Tất nhiên là có thể tìm được rất nhiều chlildren --> add all emails của children vào list emails của index hiện tại
        //+ Loop list email của index (i) --> new list mới + add dần dần
        //
        //- Chú ý :
        //+ Không dùng dạng Map(Index, HashSet<>(Email hiện tại)) ==> Xong loop email cũ (check từng phần tử trong email cũ xem tồn tại trong
        // list emails của index (i) mới hay không ) --> mới add vào
        //===> Slow --> Dùng luôn TreeSet = HashSet + Order
        //==> Không cần lưu thêm Map + Faster.
        //1.2,
        //- Time complexity : O(N + N * N (Do có đoạn check + gom phần tử) * log(N : sẽ có case gom hết) + N )
        //+ N là kích thước accounts
        //+ M là kích thước email <=10 --> Bỏ qua
        // = O(N + Nlog(N : sẽ có case gom hết))
        //- Space complexity : O(N)
        //
        //Cách 2:
        //2,
        //2.1,
        //- Ta sẽ map Email to index (Tương ứng với index của list)
        //VD:
        //- Email to Index
        //A : B, C, D
        //+ (B -> 0), (C -> 0), (D ->0)
        //A : B, E
        //+ (B -> 1) : [0 --> 1] (Parent), (E -> 1)
        //** ==> Hướng đến việc mỗi email --> Có 1 parent index riêng
        //- Nếu có email dạng lặp lại (child --> parent) --> phân cấp theo (index / tương ứng với cả cái group đó luôn)
        //==> Vì name không phân cấp được do giống nhau --> Ta dùng index
        //
        //2.2, Map từ index --> email : HashMap<Index, Tree<Email>>
        //- Mỗi index --> Ta sẽ có parent của nó --> Ta chỉ cần push all email vào (index parent) đó là được
        //===> findParent mỗi lần traverse index mới --> sau đó push TreeSet (Để order email)
        //
        //2.3,--> Map còn lại.
        //2.4,
        //- Time complexity : O(N + Nlog(N : sẽ có case gom hết) + N)
        // = Nlog(N)
        //- Space complexity : O(N)
    }
}
