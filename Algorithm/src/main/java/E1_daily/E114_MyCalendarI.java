package E1_daily;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.TreeSet;

public class E114_MyCalendarI {

    public TreeSet<Pair<Integer, Integer>> events;

    public E114_MyCalendarI() {
        events=new TreeSet<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey()-o2.getKey();
            }
        });
    }

    public boolean book(int start, int end) {
        //[12,20],[24,40]
        //[21,30] ==> false: 30 thuộc [24,40]
        //[21,22] ==> true
        //+ Search bình thường trước
        //+ Search ceil để check
        //+ Seach floor để check tiếp
        Pair<Integer, Integer> curEvent=new Pair<>(start, end);
        if(events.isEmpty()){
            events.add(curEvent);
            return true;
        }
        if(events.contains(curEvent)){
            return false;
        }
        Pair<Integer, Integer> ceilEvent = events.ceiling(curEvent);
        if(ceilEvent!=null&&ceilEvent.getKey()<curEvent.getValue()){
            return false;
        }
        Pair<Integer, Integer> floorEvent = events.floor(curEvent);
        if(floorEvent!=null&&floorEvent.getValue()>curEvent.getKey()){
            return false;
        }
        events.add(curEvent);
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are implementing a program to use as your calendar. We can add (a new event) if adding the event will not cause (a double booking).
        //- (A double booking) happens when:
        //  + two events have some (non-empty intersection) (i.e., some moment is common to both events.).
        //- The event can be represented as (a pair of integers) (start and end) that represents a booking on the half-open interval [start, end),
        // the range of real numbers x such that (start <= x < end).
        //- Implement the MyCalendar class:
        //  + MyCalendar() Initializes the calendar object.
        //  + boolean book(int start, int end)
        //      + Returns true if the event can be added to the calendar successfully without causing a double booking.
        //      + Tức là return true nếu event có thể được add vào mà không xảy ra (double booking)
        //- Otherwise, return false and do not add the event to the calendar.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //0 <= start < end <= 10^9
        //At most 1000 calls will be made to book.
        //
        //- Brainstorm
        //-
        //1.1, Optimization
        //- Bài này ta có thể dùng tree map để xử lý cũng được
        //
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(log(n))
        E114_MyCalendarI e=new E114_MyCalendarI();
        System.out.println(e.book(10, 20));
        System.out.println(e.book(15, 25));
        System.out.println(e.book(20, 30));
        //#Reference:
        //731. My Calendar II
        //732. My Calendar III
        //2446. Determine if Two Events Have Conflict
        //
    }
}
