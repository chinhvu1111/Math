package algorithm;

import java.util.*;

public class NearestNeighboringCity {

    public static class Node{
        String x;
        int y;

        public Node(String x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<String> closestStraightCity(List<String> c, List<Integer> x, List<Integer> y, List<String> q) {
        // Write your code here
//        Collections.sort(c, new Comparator<String>() {
//            @Override
//            public int compare(String s, String s1) {
//                return compareString(s, s1);
//            }
//        });
        int n=c.size();
        HashMap<Integer, String> hashMapXName=new HashMap<>();
        HashMap<Integer, Integer> hashMapXIndex=new HashMap<>();
        int rsX[]=new int[n];
        int rsY[]=new int[n];
        List<String> result=new ArrayList<>();

        HashMap<Integer, String> hashMapYName=new HashMap<>();
        HashMap<Integer, Integer> hashMapYIndex=new HashMap<>();

        Arrays.fill(rsX, -1);
        Arrays.fill(rsY, -1);

        for(int i=0;i<n;i++){
            int currentValueX=x.get(i);
            String prevNearNameX=hashMapXName.get(currentValueX);
            Integer preNearIndexX=hashMapXIndex.get(currentValueX);

            if(prevNearNameX!=null){
                int compareString=compareString(prevNearNameX, c.get(i));

                if(compareString>=1){
                    hashMapXIndex.put(currentValueX, i);
                    hashMapXName.put(currentValueX, c.get(i));

                    if(preNearIndexX!=null){
                        rsX[preNearIndexX]=i;
                        rsX[i]=preNearIndexX;
                    }
                }else if(preNearIndexX==null){
                    hashMapXIndex.put(currentValueX, i);
                }else {
                    rsX[i]=preNearIndexX;
                    if(rsX[preNearIndexX]==-1){
                        rsX[preNearIndexX]=i;
                    }
                }
            }else{
                hashMapXName.put(currentValueX, c.get(i));
                hashMapXIndex.put(currentValueX, i);
            }
            //
            int currentValueY=y.get(i);
            String prevNearNameY=hashMapYName.get(currentValueY);
            Integer preNearIndexY=hashMapYIndex.get(currentValueY);

            if(prevNearNameY!=null){
                int compareString=compareString(prevNearNameY, c.get(i));

                if(compareString>=1){
                    hashMapYIndex.put(currentValueY, i);
                    hashMapYName.put(currentValueY, c.get(i));

                    if(preNearIndexY!=null){
                        rsY[preNearIndexY]=i;
                        rsY[i]=preNearIndexY;
                    }
                }else if(preNearIndexY==null){
                    hashMapYIndex.put(currentValueY, i);
                }else {
                    rsY[i]=preNearIndexY;
                    if(rsY[preNearIndexY]==-1){
                        rsY[preNearIndexY]=i;
                    }
                }
            }else{
                hashMapYName.put(currentValueY, c.get(i));
                hashMapYIndex.put(currentValueY, i);
            }
        }
        HashMap<String, String> hashRs=new HashMap<>();
        for(int i=0;i<n;i++){
            if(rsX[i]!=-1&&hashMapYIndex.get(x.get(i))!=i){
                hashRs.put(c.get(i), c.get(rsX[i]));
                continue;
            }
            if(rsY[i]!=-1){
                hashRs.put(c.get(i), c.get(rsY[i]));
            }
        }
        int m=q.size();


        for(int i=0;i<m;i++){
            String value=hashRs.get(q.get(i));

            if(value!=null){
                result.add(value);
            }else{
                result.add("NONE");
            }
        }
        return result;
    }

    public static int compareString(String s, String s1){
        if(s.length()>s1.length()){
            return 1;
        }else if(s.length()<s1.length()){
            return -1;
        }
        int n=s.length();
        boolean isEqual=false;
        isEqual=s.equals(s1);

        if(isEqual){
            return 0;
        }
        for(int i=0;i<n;i++){
            if(s.charAt(i)>s1.charAt(i)){
                return 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int n=3;
//        String c[]=new String[]{"c1","c2","c3"};
////        int x[]=new int[]{3,2,3,3};
//        int x[]=new int[]{3,2,1};
////        int y[]=new int[]{3,3,2,3};
//        int y[]=new int[]{3,2,3};
//        String q[]=new String[]{"c1","c2","c3",};

//        int n=3;
//        String c[]=new String[]{"c1"};
////        int x[]=new int[]{3,2,3,3};
//        int x[]=new int[]{3};
////        int y[]=new int[]{3,3,2,3};
//        int y[]=new int[]{3};
//        String q[]=new String[]{"c1"};

        int n=3;
        String c[]=new String[]{"fastcity","bigbanana","xyz"};
//        int x[]=new int[]{3,2,3,3};
        int x[]=new int[]{23,23,23};
//        int y[]=new int[]{3,3,2,3};
        int y[]=new int[]{1,10,20};
        String q[]=new String[]{"fastcity","bigbanana","xyz"};

        List<String> cList=new ArrayList<>();
        List<Integer> xList=new ArrayList<>();
        List<Integer> yList=new ArrayList<>();
        List<String> qList=new ArrayList<>();

        for(int i=0;i<c.length;i++){
            cList.add(c[i]);
        }
        for(int i=0;i<x.length;i++){
            xList.add(x[i]);
        }
        for(int i=0;i<x.length;i++){
            yList.add(y[i]);
        }
        for(int i=0;i<x.length;i++){
            qList.add(q[i]);
        }
        System.out.println(closestStraightCity(cList, xList, yList, qList));
    }
}
