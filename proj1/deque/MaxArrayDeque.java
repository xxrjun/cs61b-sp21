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
}
