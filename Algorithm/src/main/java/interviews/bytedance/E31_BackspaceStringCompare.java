package interviews.bytedance;

public class E31_BackspaceStringCompare {

    public static boolean backspaceCompare(String s, String t) {
        int n=s.length();
        int m=t.length();
        int index=n-1, index1=m-1;

        while (index>=0||index1>=0){
            if(index>=0&&s.charAt(index)=='#'){
                int numberBackspace=0;

                while (index>=0&&s.charAt(index)=='#'){
                    while (index>=0&&s.charAt(index)=='#'){
                        numberBackspace++;
                        index--;
                    }
                    while (index>=0&&numberBackspace>0&&s.charAt(index)!='#'){
                        index--;
                        numberBackspace--;
                    }
                }
                index-=numberBackspace;
            }
            if(index1>=0&&t.charAt(index1)=='#'){
                int numberBackspace=0;

                while (index1>=0&&t.charAt(index1)=='#'){
                    while (index1>=0&&t.charAt(index1)=='#'){
                        numberBackspace++;
                        index1--;
                    }
                    while (index1>=0&&numberBackspace>0&&t.charAt(index1)!='#'){
                        index1--;
                        numberBackspace--;
                    }
                }
                index1-=numberBackspace;
            }
            if(index<0&&index1>=0){
                return false;
            }
            if(index>=0&&index1<0){
                return false;
            }
            if(index >= 0 && s.charAt(index) != t.charAt(index1)){
                return false;
            }
            index--;
            index1--;
        }

        return true;
    }

    public static boolean backspaceCompareRefactor(String s, String t) {
        int n=s.length();
        int m=t.length();
        int index=n-1, index1=m-1;

        while (index>=0||index1>=0){
            int numberBackspace=0;

            while (index>=0&&(s.charAt(index)=='#'|| numberBackspace>0)){
                if(s.charAt(index)=='#'){
                    numberBackspace++;
                }else{
                    numberBackspace--;
                }
                index--;
            }
            numberBackspace=0;
            while (index1>=0&&(t.charAt(index1)=='#'|| numberBackspace>0)){
                if(t.charAt(index1)=='#'){
                    numberBackspace++;
                }else{
                    numberBackspace--;
                }
                index1--;
            }
            if(index<0&&index1>=0){
                return false;
            }
            if(index>=0&&index1<0){
                return false;
            }
            if(index >= 0 && s.charAt(index) != t.charAt(index1)){
                return false;
            }
            index--;
            index1--;
        }

        return true;
    }

    public static void main(String[] args) {
//        String s="ab#c", s1="ad#c";
//        String s="ab##", s1="c#d#";
//        String s="xywrrmp", s1="xywrrmu#p";
        //Case 2:
        //- Sẽ xảy ra khi số lượng # nhiều hơn số lượng ký tự đằng sau
        //VD: bbbbb#a####
        //==> Thì ta cần lưu dồn lại
        String s="bxj##tw", s1="bxo#j##tw";
        System.out.println(backspaceCompare(s, s1));
        //#Reference
        //845. Longest Mountain in Array
        //1598. Crawler Log Folder
        //2390. Removing Stars From a String
        //
        //** Đề bài:
        //- Bài này để có thể xác định 2 chuỗi nếu bao gồm cả backspace có giống nhau hay không
        //
        //** Ta tư duy như sau:
        //1,
        //Các vấn đề cẩn xử lý:
        //- Nhiều backspace thì so sánh chuỗi tương ứng như thế nào
        //VD:
        //ab## --> ""
        //1.1,
        //- Vấn đề nhiều backspace ta có thể giải quyết bằng cách:
        //+ Chỉ so sánh các phần tử Valid (Tức là không bị xoá bởi backspace)
        //Sẽ xảy ra cases đặc biệt như :
        //- Sẽ xảy ra khi số lượng # nhiều hơn số lượng ký tự đằng sau:
        //VD: bbbbb#a####
        //==> Thì ta cần lưu dồn lại
        //- Ta sẽ xoá, nhưng xoá theo rule sau:
        //+ Nếu gặp # thì ta lưu số lượng cần xoá cho đến khi ta gặp ký tự (a<= ch, z>=ch)
        //+ Sau đó nếu là ký tự a<=ch<=z thì ta mới xoá còn nếu ==# thì ta sẽ lưu lại
        //+ Đến cuối cùng sau khi xoá hết thì numberBackspace sẽ được trừ đi vào (index)
        //
        //- Sau khi tìm được ký tự valid (Không bị xoá bởi backspace) thì ta sẽ đi so sánh 2 chars của 2 string (Từ last --> start)
        //1.2, Complexity
        //- Time complexity : O(n)
        //- Space complexity : O(1)
        //1.3, Refactor
        //- Ta gộp các if và while giống nhau thành 1
        //- Tối ưu thêm đoạn while (+ backspace) + thêm (- backspace) nữa
        //==> Tận dụng việc số # đằng sau bao nhiêu --> Đằng trước 1 là thêm #/ có ký tự để xoá
        //==> Ta sẽ dùng while(numBackspace>0 || s[index]=='#') là được.
        System.out.println(backspaceCompareRefactor(s, s1));
    }
}
