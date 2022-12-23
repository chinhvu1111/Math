package interviews;

import java.util.*;

public class E237_SnapshotArray_hash_binary_search {

    public static class SnapshotArray {

//        HashMap<Integer, HashMap<Integer, Integer>> snapshots;
        // index : <snapshot id, value>
        HashMap<Integer, HashMap<Integer, Integer>> indexLastestSnapshot;
        HashMap<Integer, TreeSet<Integer>> indexSnapshots;
        Integer snapshotId=0;

        public SnapshotArray(int length) {
            indexLastestSnapshot=new HashMap<>();
            indexSnapshots=new HashMap<>();
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
        //Case 1:
        //- Case cơ bản.
//        SnapshotArray snapshotArr = new SnapshotArray(3);
//        snapshotArr.set(0,5);  // Set array[0] = 5
//        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
//        snapshotArr.set(0,6);
//        System.out.println(snapshotArr.get(0, 0));

        //Case 2:
        //- Thực hiện snapshot nhiều lần --> mà chưa từng thực hiện việc set
        //--> Cần thực hiện việc search snapshot_id gần nhất
//        SnapshotArray snapshotArr = new SnapshotArray(4);
//        snapshotArr.snap();
//        snapshotArr.snap();
//        System.out.println(snapshotArr.get(3,1));
//        snapshotArr.set(2,4);
//        snapshotArr.snap();
//        snapshotArr.set(1,4);

        //Case 3:
        //- Liên quan đến việc index không được set của snapshot_id nào
        //--> Cần return 0 (default value ban đầu của arr)
        SnapshotArray snapshotArr = new SnapshotArray(1);
        snapshotArr.set(0, 15);
        snapshotArr.snap();
        snapshotArr.snap();
        snapshotArr.snap();
        System.out.println(snapshotArr.get(0,2));
        snapshotArr.snap();
        snapshotArr.snap();
        System.out.println(snapshotArr.get(0,0));
        //
        //** Đề bài:
        //- Cần phải lấy thông tin dựa trên <Index, SnapshotId>:
        //+ Snapshot id : id của lần snapshot
        //+ index là số thứ tự của arr[index]
        //
        //** Bài này tư duy như sau:
        //1,
        //Cần lưu ý những điều sau đây:
        //- Số lượng phần tử lớn --> Không thể mỗi lần snapshot ghi lại all values arr[n] tại snapshot
        //- Số lần snapshot lớn : Không thể cứ tốn ram để lưu n*n phần tử 5*10^4 ^2
        //- Lấy value của index a của lần snapshot số i
        //1.1,
        //Snapshot chỉ lưu thay đổi
        //2 —> 4
        //==> Lưu các index thay đổi vào hash sét của mỗi lần snapshot
        //- Nếu muốn lấy 1 thay đổi của snapshot i
        //—> Cần phải đọc thay đổi của all version trước đó
        //
        //2.1, Nó chỉ cần lấy value của index trong array
        //Case 1:
        //1,2,3
        //1,3,3
        //snap : 2 —> 3
        //1,4,3
        //Snap : 3 —> 4
        //
        //2.2,
        //Method:
        //- get, set, snap
        //
        //- get thì lấy value của (snapshot id, index)
        //cần tìm xem snapshot - id có index đó không
        //+ Có thì return
        //+ Không có thì cần tìm xem index đó được snapshot bởi snapshot id nào < current snapshot id.
        //
        //VD:
        //Index <Snapshot_id, value>
        //2.3, Tư duy như sau:
        //- Không thể lưu mỗi snapshot id all index được
        // --> Vì nếu get(index) --> Cần phải tìm xem index đó được snapshot bới snapshot_id nào <> thì lấy =0
        //--> Chính là giá trị default value ban đầu của arr[index] đó
        //
        //- Lưu mỗi index --> Danh sách các snapshot id đã snapshot index đó
        //+ Format : <index, <snapshot_id, value>> : thể hiện index tại snapshot_id này thì value =?
        //
        //2.4,
        //- Lấy (index, snapshot_id) tức là lấy value của arr[index] tại lần snapshot_id nếu:
        //+ index không được snapshot_id set thì --> Nó sẽ lấy snapshop_id_lastest < snapshot_id
        //==> Tức là snapshot_id gần nhất.
        //- Ý tưởng:
        //+ <index, <snapshot_id, value>> : Để lưu thông tin các snapshot_id đã thực hiện việc (set) trên index
        //==> Có thể nó không có ===> Cần phải tìm cái gần nhất.
        //+ <index, TreeSet<Snapshot_id>> : Với index thì lưu danh sách snapshot_id tương ứng + tối ưu cho việc tìm kiếm
        // snapshot_id gần nhất (binary search)
        //--> Tìm điểm snapshot_id_lastest gần nhất với snapshot_id : treeset.floor(snapshot_id) : snapshot_id-x <= snapshot_id
        //** Note: TreeSet : Lưu thông tin snapshot id sắp xếp theo tăng dần
    }
}
