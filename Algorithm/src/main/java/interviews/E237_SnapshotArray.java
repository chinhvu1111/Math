package interviews;

import com.google.common.collect.BiMap;

import java.util.*;

public class E237_SnapshotArray {

    public static class SnapshotArray {

        Integer[] arr;
//        HashMap<Integer, HashMap<Integer, Integer>> snapshots;
        // index : <snapshot id, value>
        HashMap<Integer, HashMap<Integer, Integer>> indexLastestSnapshot;
        HashMap<Integer, TreeSet<Integer>> indexSnapshots;
        Integer snapshotId=0;

        public SnapshotArray(int length) {
            arr=new Integer[length];
//            snapshots=new HashMap<>();
            indexLastestSnapshot=new HashMap<>();
            indexSnapshots=new HashMap<>();
//            currentCacheValues=new TreeMap<>();
        }

        public void set(int index, int val) {
            HashMap<Integer, Integer> allSnapshotIndex=indexLastestSnapshot.get(index);
            TreeSet<Integer> listSnapshots=indexSnapshots.get(index);

            if(allSnapshotIndex==null){
                allSnapshotIndex=new HashMap<>();
            }
                allSnapshotIndex.put(snapshotId, val);
            if(listSnapshots==null){
                listSnapshots=new TreeSet<>();
            }
            listSnapshots.add(snapshotId);
            indexLastestSnapshot.put(index, allSnapshotIndex);
            indexSnapshots.put(index, listSnapshots);
        }

        public int snap() {
            return snapshotId++;
        }

        public int get(int index, int snap_id) {
            HashMap<Integer, Integer> snapshotIndex=indexLastestSnapshot.get(index);
            if(snapshotIndex==null){
                return 0;
            }
            TreeSet<Integer> listSnapshot=indexSnapshots.get(index);
            Integer versionLastest=listSnapshot.floor(snap_id);

            if(!snapshotIndex.containsKey(versionLastest)){
                return 0;
            }
//            snapshotIndex.
            return snapshotIndex.get(versionLastest);
        }
    }

    public static void main(String[] args) {
//        SnapshotArray snapshotArr = new SnapshotArray(3);
//        snapshotArr.set(0,5);  // Set array[0] = 5
//        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
//        snapshotArr.set(0,6);
//        System.out.println(snapshotArr.get(0, 0));

//        SnapshotArray snapshotArr = new SnapshotArray(4);
//        snapshotArr.snap();
//        snapshotArr.snap();
//        System.out.println(snapshotArr.get(3,1));
//        snapshotArr.set(2,4);
//        snapshotArr.snap();
//        snapshotArr.set(1,4);

        SnapshotArray snapshotArr = new SnapshotArray(1);
        snapshotArr.set(0, 15);
        snapshotArr.snap();
        snapshotArr.snap();
        snapshotArr.snap();
        System.out.println(snapshotArr.get(0,2));
        snapshotArr.snap();
        snapshotArr.snap();
        System.out.println(snapshotArr.get(0,0));
    }
}
