package interviews;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class E211_AccountsMerge_TLE {

    public static int findParent(int[] parent, int u){
        while (u!=parent[u]){
            u=parent[u];
        }
        return u;
    }

    //u : child
    //v : parent
    public static void union(int[] parent,
                             TreeSet<String>[] accounts, TreeSet<String> treeEmail, int u, int v){
        int parentU=findParent(parent, u);
        if(parentU==v){
            return;
        }
        TreeSet<String> currentNonDuplicateEmail=accounts[v];

        Iterator<String> iterator = accounts[parentU].iterator();
        while (iterator.hasNext()){
            String currentString=iterator.next();

            if(!currentNonDuplicateEmail.contains(currentString)){
                treeEmail.add(currentString);
                currentNonDuplicateEmail.add(currentString);
            }
        }

        parent[parentU]=v;
        accounts[parentU]=new TreeSet<>();
        parent[u]=v;
        accounts[u]=new TreeSet();
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();
        //Example:
        //- johnsmith@mail.com --> i=0
        HashMap<String, Integer> hashMailIndex=new HashMap<>();
//        HashMap<Integer, TreeSet<String>> hashIndexNonDuplicateMail=new HashMap<>();
        int n=accounts.size();
        TreeSet<String>[] accountsArr=new TreeSet[n];
        int[] parent =new int[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        for(int i=0;i<n;i++){
//            String name=accounts.get(i).get(0);
            //Removing name
//            List<String> listWithoutName=accounts.get(i).subList(1, accounts.get(i).size());
            //Hashset distinct email without name
            TreeSet currentEmails = new TreeSet(accounts.get(i));
//            TreeSet<String> currentEmailsName = new TreeSet(accounts.get(i));
//            currentEmailsName.add(name);

//            hashIndexNonDuplicateMail.put(i, currentEmailsName);
            accountsArr[i]=currentEmails;
        }
        endTime=System.currentTimeMillis();
        System.out.println("Start run in :"+(endTime-startTime)+ " millies seconds");

        for(int i=0;i<n;i++){
            TreeSet<String> treeEmail=accountsArr[i];
//            List<String> newListEmail=new LinkedList<>(treeEmail);

//            Iterator<String> interator = treeEmail.iterator();
            //Traverse to ith position
            for (int j=0;j<accounts.get(i).size();j++){
                String currentString=accounts.get(i).get(j);

                if(hashMailIndex.containsKey(currentString)&&hashMailIndex.get(currentString)==i){
                    continue;
                }
                if(!hashMailIndex.containsKey(currentString)){
                    hashMailIndex.put(currentString, i);
                    continue;
                }else{
                    //- Find parent =i
                    //-
                    union(parent, accountsArr, treeEmail, hashMailIndex.get(currentString), i);
//                    accountsArr[hashMailIndex.get(currentString)]=new ArrayList<>();
                }
                hashMailIndex.put(currentString, i);
            }
            accountsArr[i]=treeEmail;
//            if(i%1000==0){
//                endTime=System.currentTimeMillis();
//                System.out.println("Run in :"+(endTime-startTime)+ " millies seconds");
//            }
//            System.out.println(newListEmail);
        }
        endTime=System.currentTimeMillis();
        System.out.println("Run in :"+(endTime-startTime)+ " millies seconds");
        startTime=System.currentTimeMillis();
        List<List<String>> result=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(accountsArr[i].size()!=0){
                List<String> currentList=new ArrayList<>();
//                currentList.add(accounts.get(i).get(0));
                Iterator<String> iterator = accountsArr[i].iterator();
                while (iterator.hasNext()){
                    currentList.add(iterator.next());
                }
                result.add(currentList);
            }
        }
        endTime=System.currentTimeMillis();
        System.out.println("Run in :"+(endTime-startTime)+ " millies seconds");

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
    }
}
