package solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {
        Queue<Integer> cookies = new PriorityQueue<>();
        for (Integer cookie : cookiesSweetness) {
            cookies.add(cookie);
        }

        int counter = 0;
        int currentMinSweetness = cookies.peek();
        while (currentMinSweetness < requiredSweetness && cookies.size() >= 2) {
            Integer leastSweetCookie = cookies.poll();
            Integer secondLeastSweetCookie = cookies.poll();
            Integer newCookie = leastSweetCookie + 2 * secondLeastSweetCookie;

            cookies.offer(newCookie);

            currentMinSweetness = cookies.peek();
            counter++;
        }

        return currentMinSweetness >= 7 ? counter : -1;
    }
}
