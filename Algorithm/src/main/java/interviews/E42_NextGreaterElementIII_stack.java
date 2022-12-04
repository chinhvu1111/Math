package interviews;

import java.util.*;

public class E42_NextGreaterElementIII_stack {

    public static int nextGreaterElement(int n) {
        String s=String.valueOf(n);
        Stack<Character> stack=new Stack<>();
        int length=s.length();
        stack.add(s.charAt(length-1));
        TreeSet<int[]> treeSet=new TreeSet<>((a, b) -> a[0]-b[0]);
        List<int[]> suffix=new ArrayList<>();
        if(s.length()>=1){
            treeSet.add(new int[]{s.charAt(length-1), length-1});
        }
        int indexX=-1;
        int indexY=-1;

        for(int i=length-2;i>=0;i--){
//            if(treeSet.contains(new int[]{s.charAt(i),i})){
//                continue;
//            }
            int[] value=treeSet.higher(new int[]{s.charAt(i),i});

            if(value!=null){
                indexX=i;
                indexY=value[1];
                break;
            }
            treeSet.add(new int[]{s.charAt(i),i});
        }
        if(indexX==-1){
            return -1;
        }
        for(int i=indexX+1;i<length;i++){
            if(indexY!=-1&&i==indexY){
                suffix.add(new int[]{s.charAt(indexX),indexY});
                continue;
            }
            suffix.add(new int[]{s.charAt(i),i});
        }
        Collections.sort(suffix, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0]-t2[0];
            }
        });
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<length;i++){
            if(indexX!=-1&&i==indexX){
                rs.append(s.charAt(indexY));
                break;
            }
            rs.append(s.charAt(i));
        }
        for(int[] c: suffix){
            rs.append((char)c[0]);
        }
//        while (!treeSet.isEmpty()){
//            rs.append((char)treeSet.pollFirst()[0]);
//        }
        if(Long.parseLong(rs.toString())>Integer.MAX_VALUE){
            return -1;
        }
        return Integer.parseInt(rs.toString());
    }

    public static int nextGreaterElementOptimized(int n){
        String s=String.valueOf(n);
        char arr[]=s.toCharArray();
        int index=s.length()-2;

        while(index>=0&&arr[index]>=arr[index+1]){
            index--;
        }
        if(index<0){
            return -1;
        }
        int j=s.length()-1;
        //Nếu đi từ cuối tức là:
        //Start --> End: Được sắp xếp theo thứ tự giảm dần
        //--> Ta sẽ lấy value nhỏ nhất từ end --> start ==> Để swap
        //arr[index]>arr[j]
        //VD : 12222 --> Lấy 2 ở cuối vì (theo ct check đến 2 ở cuối là stop)
        while (j>=0&&arr[index]>=arr[j]){
            j--;
        }
        StringBuilder rs=new StringBuilder();
        char temp=arr[index];
        arr[index]=arr[j];
        arr[j]=temp;

        for(int i=0;i<=index;i++){
            rs.append(arr[i]);
        }
        for(int i=s.length()-1;i>index;i--){
            rs.append(arr[i]);
        }
        long rsL=Long.parseLong(rs.toString());
        if(rsL>Integer.MAX_VALUE){
            return -1;
        }
        return (int) rsL;
    }

    public static void main(String[] args) {
        //Case 1: replace số ở cuối
//        int n=1042;
        //Case 2: replace 2 số trùng nhau
        //TreeSet có 2 phương thức :
        //- ceil: Tìm element >= s.chartAt(i)
        //- higher : Tìm element > s.chartAt(i)
        //==> Ở đây muốn không phải check contains --> Dùng higher

        //Bài này ta tư duy như sau:
        //CÁCH 1:
        //1, Vì là tìm số nhỏ nhất > lớn hơn số đã cho
        //Ta sẽ tìm vị trí gần thỏa mãn điều kiện gần bên phải nhất ==> Tức là sự thay đối ít nhất có thể
        //VD: 1342 ==> 2134
        //Ở đây ta đã thay thế 1 bằng số 2 (Nhỏ nhất trong các số đứng sau 1 (min) + >1)
        //1.1, Case đặc biệt:
        //VD : 1222
        //Đối với case này ta sẽ chọn số 2 gần right nhất ==> Replace sẽ cho số MIN
        //--> Ta sẽ chỉ lưu 1 số 2 vào collection có thể dùng:
        //- Queue + check contains.
        //- Set.
        //1,2 Ở đây có 1 số lỗi + hiểu lầm khi xử lý bài này như:
        //- Khi ta replace (i) bằng (j) trong chuỗi s
        //==> Để tìm được chuỗi min + > s --> Ta cần phải sắp xếp tất cả các số đằng sau (i)
        //** Tư duy replace không sẽ không thể min được
        //VD : 1342 --> replace 1432 ==> Số này vẫn >   1423 (Chưa min)
        //1.3, Kinh nghiệm làm bài kiểu find số lớn hớn số hiện tại/ nhỏ hơn số hiện tại:
        //TreeSet có 2 phương thức :
        //- ceil: Tìm element >= s.chartAt(i)
        //- higher : Tìm element > s.chartAt(i)
        //==> Ở đây muốn không phải check contains --> Dùng higher.
        //** Nếu không dùng treeset --> Phải xét tồn tại.
        //1.4, Ở đây vì là replace nên ta phải gắn index đi kèm để:
        //Có thể replace theo index của 2 số cần replace.
        //2, Vì dùng (treeset + remove  + replace) không đủ số phần tử --> Các phần tử có thể trùng nhau
        // --> Cần tạo ra list để Sort các số ở cuối
        // --> Sau đó prefix -> indexX + suffix List --> Ra kết quả.

        //CÁCH 2:
        //1, Cách suy nghĩ theo kiểu stack/queue cổ điển (Classic):
        //VD : 1,3,4,2,5
        //** TƯ DUY NGƯỢC : Ta muốn tìm 2,5 tức là 2<5 (Đầu tiên từ bên phải)
        //** Thay vì check arr[i]<arr[j] + break; (CHỈ TÌM THẤY LẦN ĐẦU TIÊN)
        // ---> Thì ta có thể check ngược lại nếu
        //CODE:
        //while(arr[i]>arr[j]) i--;
        //==> Nếu chỉ 1 lần arr[i]<=arr[j] ==> Kết thúc xử lý luôn.
        //Khi dùng while() i-- ==> Ta chỉ
        //2, Tư duy chuỗi tăng dần arr[i]> arr[j]
        //Nếu while(arr[i]>arr[j]) i--;
        //Các số cho đến i sẽ tằng dần từ (end --> i)
        //VD: 1,3(i),5,2,1
        //Thế nên nếu reverse từ (end --> start) ==> Ta không cần sắp xếp (sorted) lại các phần tử ở cuối.
        //2.1, Các câu hỏi đặt ra là :
        //- Tìm phần tử nhỏ nhất lớn hơn arr[i] + gần left nhất (THAY THẾ NHỎ BẰNG LỚN --> Gần left thì càng tốt hơn) + (replace)
        // + Cần lấy ở cuối arr[j]:
        // while(arr[i]>arr[j]) j-- ; ==> Vẫn áp dụng tư duy ngược.
        // ---> Vì cần gần left + >=
        // while(arr[i]>arr[j]) j--
        //- Cần SẮP XẾP các phần tử cuối --> MIN.
        //==> Tư duy cổ điền arr[i]> arr[j] : i-- ==> MẢNG GIẢM DẦN
        //==> Chỉ cần đọc từ (end --> i) là OK.
//        int n=21;
//        int n=1234;
//        int n=1342;
//        int n=1222;
//        int n=1;
//        int n=230241;
//        int n=21;
//        int n=14325;
        int n=12443322;
        //Output : 12223344
        //Expected : 13222344
        //--> Case này bị lỗi khi nums[i]=2 --> replace cho nums[j]=2
        // Cần ignore >= --> while(>=)
        System.out.println(nextGreaterElement(n));
        System.out.println(nextGreaterElementOptimized(n));
    }
}
