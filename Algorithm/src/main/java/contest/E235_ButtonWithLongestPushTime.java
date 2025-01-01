package contest;

public class E235_ButtonWithLongestPushTime {

    public static int buttonWithLongestTime(int[][] events) {
        int n=events.length;
        int prev=0;
        int maxTime=0;
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            int durationTime = events[i][1]-prev;
            if(maxTime<=durationTime){
                if(maxTime==durationTime){
                    if(rs>events[i][0]){
                        rs=events[i][0];
                    }
                }else{
                    rs=events[i][0];
                }
                maxTime=durationTime;
            }
            prev=events[i][1];
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given a 2D array events which represents a sequence of events where a child pushes a series of buttons on a keyboard.
        //- Each events[i] = [indexi, timei] indicates that the button at index indexi was pressed at time timei.
        //  + The array is sorted in increasing order of time.
        //  + The time taken to press a button is the difference in time between consecutive button presses.
        //  The time for the first button is simply the time at which it was pressed.
        //* Return the index of the button that took the longest time to push.
        // If multiple buttons have the same longest time, return the button with the smallest index.
//        int[][] events = {{1,2},{2,5},{3,9},{1,15}};
        int[][] events = {{10,5},{1,7}};
        System.out.println(buttonWithLongestTime(events));
    }
}
