package contest;

import java.util.ArrayList;
import java.util.List;

public class E181_FindIndicesOfStableMountains {

    public static List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> rs=new ArrayList<>();
        int n=height.length;

        for(int i=1;i<n;i++){
            if(height[i-1]>threshold){
                rs.add(i);
            }
        }
        return rs;
    }

    public static void main(String[] args) {

    }
}
