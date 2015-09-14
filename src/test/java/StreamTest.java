import org.junit.Test;

import java.util.Arrays;
import java.util.OptionalDouble;

/**
 * @author chanwook
 */
public class StreamTest {

    @Test
    public void tryFirst() throws Exception {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // sum
        final int sum1 = Arrays.stream(arr).sum();
        assert sum1 == 55;

        // average
        final OptionalDouble sum2 = Arrays.stream(arr).average();
        assert sum2.getAsDouble() == 5.5;

        // sum even num
        final int sum3 = Arrays.stream(arr).filter(i -> i % 2 == 0).sum();
        assert sum3 == 30;

        // foreach
        Arrays.stream(arr).forEach(i -> System.out.println("loop value is " + i)); // forEach() return type is void...@@

        // collect odd num
//        List<Integer> list = Arrays.stream(arr).filter(i -> i % 2 != 0).collect(Collectors.toList());

        // map

        // reduce

        // parallel

        // min/max
    }
}
