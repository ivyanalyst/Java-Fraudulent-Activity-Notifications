import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */

    public static int activityNotifications(List<Integer> expenditure, int d) {
    // Write your code here
    
        int notic = 0;
        int[] cntArr = new int[201];
        
        for(int i = 0; i < d; i++) {
            cntArr[expenditure.get(i)]++;
        }
        
        for (int i = d; i < expenditure.size(); i++) {
            double median = findMedian(cntArr, d);
            
            if(2 * median <= expenditure.get(i)) {
                notic++;
            }
            
            cntArr[expenditure.get(i - d)]--;
            cntArr[expenditure.get(i)]++;
        }
        
        return notic;

    }
    
    private static double findMedian(int[] countArray, int d) {
        int count = 0;
        double median = 0;
        
        if (d % 2 == 0) {
            int first = -1;
            int second = -1;
            for (int i = 0; i < countArray.length; i++) {
                count += countArray[i];
                if (first == -1 && count >= d / 2) {
                    first = i;
                }
                
                if (second == -1 && count >= d / 2 + 1) {
                    second = i;
                    break;
                    }
                }
                
                median = (first + second) / 2.0;
        } else {
            for (int i = 0; i < countArray.length; i++) {
                count += countArray[i];
                if (count > d / 2) {
                    median = i;
                    break;
                }
            }
        }
        return median;
        
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

