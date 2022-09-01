package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.cmp = c;
    }


    /**
     * @return the maximum element in the deque as governed by the previously given Comparator
     */
    public T max() {
        return max(cmp);
    }

    /**
     * @param c
     * @return the maximum element in the deque as governed by the parameter Comparator c.
     */
    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }


        int maxValueIndex = 0;
        for (int i = 1; i < size(); i++) {
            if (c.compare(get(maxValueIndex), get(i)) < 0) {
                maxValueIndex = i;
            }
        }

        return get(maxValueIndex);
    }

    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static Comparator<Integer> getIntComparator() {
        return new IntComparator();
    }

    private static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static Comparator<String> getStringComparator() {
        return new StringComparator();
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    public static Comparator<String> getStringLengthComparator() {
        return new StringLengthComparator();
    }


}
