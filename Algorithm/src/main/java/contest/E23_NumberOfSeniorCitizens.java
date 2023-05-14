package contest;

public class E23_NumberOfSeniorCitizens {

    public static int countSeniors(String[] details) {
        int rs=0;
        int n=details.length;

        for(int i=0;i<n;i++){
            int currentAge= Integer.parseInt(details[i].substring(11, 13));
            if(currentAge>60){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirment:
        //-
        //** Idea
        //1.
        //1.1,
        //- Filter bình thường
    }
}
