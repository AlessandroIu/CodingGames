import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumPositiveIntegerNotInArray {

    public static void main(String... args) {
        MinimumPositiveIntegerNotInArray minimumPositiveIntegerNotInArray = new MinimumPositiveIntegerNotInArray();
        minimumPositiveIntegerNotInArray.verifySolution(new int[]{1, 2, 3, 875, 2}, 4);
        minimumPositiveIntegerNotInArray.verifySolution(new int[]{2, 3, 875, 2}, 1);
        minimumPositiveIntegerNotInArray.verifySolution(new int[]{0, 4, 2, 5, 1, 6, 3}, 7);
    }

    public void verifySolution(int[] inputArray, int expectedResult) {
        int result = solution(inputArray);
        if (result != expectedResult) {
            System.err.println("Result wrong for array " + Arrays.toString(inputArray));
            System.err.println("Result is " + result + " but expected is " + expectedResult);
        } else {
            System.out.println("Result correct of " + expectedResult + " for array " + Arrays.toString(inputArray));
        }
        System.out.println();
    }

    public int solution(int[] A) {
        int N = A.length;
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            if (a > 0) {
                set.add(a);
            }
        }
        for (int i = 1; i <= N + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }
}
