package E1_daily;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class E101_MinimumTimeDifference {

    public static int getMinutesDiff(String time1, String time2) {
        String[] times1 = time1.split(":");
        String[] times2 = time2.split(":");
        //00-23
        //  + if else
        //23-00
        int diff;
        if (times1[0].compareTo(times2[0]) < 0) {
            String[] tmp = times1;
            times1 = times2;
            times2 = tmp;
        }
        int hour1 = Integer.parseInt(times1[0]);
        int minute1 = Integer.parseInt(times1[1]);
        int hour2 = Integer.parseInt(times2[0]);
        int minute2 = Integer.parseInt(times2[1]);
        diff = (hour1 - hour2) * 60;
        //03:12 - 06:20
        //03:50 - 06:10
        int diffMinutes = minute1 - minute2;
        if (diff > 0) {
            diff += diffMinutes;
        } else {
            diff += Math.abs(diffMinutes);
        }
        int nextDiff = (24 - hour1 + hour2) * 60;
        nextDiff += (minute2 - minute1);
        diff = Math.min(nextDiff, diff);
        return diff;
    }

    public static int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        int n = timePoints.size();
        int rs = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int prevIndex = (i + n - 1) % n;
//            System.out.printf("%s %s\n", i, prevIndex);
            rs = Math.min(rs, getMinutesDiff(timePoints.get(i), timePoints.get(prevIndex)));
        }
        return rs;
    }

    public static int findMinDifferenceSortMinutes(List<String> timePoints) {
        int n = timePoints.size();
        int[] minutes = new int[n];

        for (int i = 0; i < n; i++) {
            String[] s = timePoints.get(i).split(":");
            minutes[i] = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
        }
        Arrays.sort(minutes);
        int rs = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int prevIndex = (i + n - 1) % n;
            rs = Math.min(rs, minutes[i] - minutes[prevIndex]);
        }
        return Math.min(rs, minutes[0] + 1440 - minutes[n - 1]);
    }

    public static int findMinDifferenceOptimization(List<String> timePoints) {
        // create buckets array for the times converted to minutes
        boolean[] minutes = new boolean[24 * 60];
        for (String time : timePoints) {
            int min =
                    Integer.parseInt(time.substring(0, 2)) * 60 +
                            Integer.parseInt(time.substring(3));
            if (minutes[min]) return 0;
            minutes[min] = true;
        }
        int prevIndex = Integer.MAX_VALUE;
        int firstIndex = Integer.MAX_VALUE;
        int lastIndex = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        // find differences between adjacent elements in sorted array
        for (int i = 0; i < 24 * 60; i++) {
            if (minutes[i]) {
                if (prevIndex != Integer.MAX_VALUE) {
                    ans = Math.min(ans, i - prevIndex);
                }
                prevIndex = i;
                if (firstIndex == Integer.MAX_VALUE) {
                    firstIndex = i;
                }
                lastIndex = i;
            }
        }

        return Math.min(ans, 24 * 60 - lastIndex + firstIndex);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a list of 24-hour clock time points in "HH:MM" format,
        //* Return (the minimum minutes difference) between (any two time-points) in the list.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= timePoints.length <= 2 * 10^4
        //timePoints[i] is in the format "HH:MM".
        //
        //- Brainstorm
        //- Sort list:
        //  + Sau đó xét từng đôi 1
        //- Nhớ là cần xét diff giữa (head vs last)
        //
        //
//        String[] timePoints = {"00:00","23:59","00:00"};
        //rs = 0
//        String[] timePoints = {"01:00","23:59","00:00"};
        //rs = 1
//        String[] timePoints = {"01:20","23:20","00:00"};
        //expected rs = 40
//        String[] timePoints = {"01:20","23:20"};
        //{00:00,01:20,23:20}
        //rs = 1
        System.out.println(getMinutesDiff("01:20", "23:20"));
        //expected: 120
        //  00:00 -> 01:20: 80
        //  23:20 -> 00:00: 40
        //- Special case:
        //- Với các times cùng hour:
        //  + The difference between minutes sẽ quyết định:
        //      + Nếu diff minutes <0, >=0: ==> | diff |
        //- Time range với hour smaller or bigger:
        //  + 00-23
        //  + 23-00
        //  ==> Cần swap để đẩy thẳng bigger hour lên trước
        //- Tính như sau:
        //Ex:
        //  00:00 -> 01:20: 80 (small time - 00:00)
        //  23:20 -> 00:00: 40 (00:00 - bigger time)
        //      + hour + 24 - bigger hour
        //      + +(00 - bigger minutes time)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(log(n))
        //- Time: O(n*log(n))
        //
        //2. Sort minutes
        //- Cũng là sort nhưng sort minutes bằng cách tính trực tiếp thôi
        //- Bản chất việc sort theo (hour:minute) là sort theo (minutes) được tính ra từ nó:
        //Ex:
        //["01:20","23:20"]
        //-> sort: [80, 1400]
        //  + Case này tính 2 đầu là được:
        //      + 80 + 24*60 -1400 = 120
        //* MAIN POINT:
        //  - Vì xét min diff của all of pairs nên ta không cần:
        //      + Tính min của từng pair:
        //  Ex:
        //  (a, b) phải xét:
        //      + c1=b-a
        //      + c2=a+1440-b
        //  cur = min(c1,c2)
        //==> Ta chỉ cần xét 2 đầu vì:
        //  + c2=a+1440-b
        //  * Hiệu dạng này 2 đầu sẽ ("LUÔN MIN nhất")
        //      + Ta chỉ cần xét (a[i]-a[i-1]) thôi
        //      + Xét thêm case |head+1440-last| là đủ
        //
        //1.1, Optimization
        //- Ở đây ta có thể dùng counting sort vì:
        //  + Số minutest trong 1 day ==> Fixed
        //* KINH NGHIỆM:
        //  + Dùng counting sort:
        //      + Fix size or liên tục
        //      + Size không quá lớn
        //- Solution:
        //  + Tạo 1 array boolean exists
        //      ==> Dùng để counting sort luôn
        //  + Sau đó sẽ dùng prev_index và current index ==> If else là được.
        //
        //1.2, Complexity
        //- Space: O(log(n))
        //- Time: O(n*log(n))
        //
        String[] timePoints = {"12:12", "12:13"};
        System.out.println(findMinDifference(Arrays.asList(timePoints)));
        System.out.println(findMinDifferenceSortMinutes(Arrays.asList(timePoints)));
        System.out.println(findMinDifferenceOptimization(Arrays.asList(timePoints)));
        //#Reference:
        //2162. Minimum Cost to Set Cooking Time
        //
    }
}
