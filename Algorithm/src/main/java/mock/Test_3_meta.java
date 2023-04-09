package mock;

public class Test_3_meta {

    //1.
    //1.1, Idea
    //- Dữ kiện:
    //+ 0 <= n <= 10^9
    //+ Vỡi mỗi lần toggle: Chuyển từ (tắt --> bật)/ (bật --> tắt)
    //- VD:
    //1: 1 1 1 1 1
    //2: 1 0 1 0 1
    //3: 1 0 0 1 1
    //4: 1 0 0 1 1
    //5: 1 0 0 1 0
    //* every là đếm lần lượt:
    //Số chữ số 0 = i - 1
    //Số chữ số 1 = n- i +1 = 5 - 4 +1 = 2 ===> tư duy sai.
    //
    //- n=4 --> expect=2
    //1: 1 1 1 1
    //2: 1 0 1 0
    //3: 1 0 0 0
    //4: 1 0 0 1
    //+ 2 --> tạo ra all số 0 --> 4,6,8,10 ==> Sẽ lần lượt bù vào
    //
    //2: 1 0 1 0 1 0 1 0 : 4
    //3: 1 0 1 0 1 1 1 0 : 5
    //4: 1 0 1 1 1 1 1 1 : 7 : (2 vị trí trùng nhau của 4 và 2)
    //8: 1 0 1 1 1 1 1 0 : 6 : (Vị trí trùng nhau của 4 và 8)
    public static int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
    public static void main(String[] args) {

    }
}
