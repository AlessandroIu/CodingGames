import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OrderSystemInput {

    public static void main(String... argv) {

        Scanner in = new Scanner(System.in);
        List<List<String>> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            lines.add(Arrays.asList(line.split(" ")));
        }
        System.out.print(lines);
    }
}
