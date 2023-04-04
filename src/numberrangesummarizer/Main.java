package numberrangesummarizer;
import java.util.Collection;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);

        // get input
        System.out.println("Sample Input:");
        String sampleInput = input.nextLine();
        ArrangeNumbers number = new ArrangeNumbers();
        Collection<Integer> output = number.collect(sampleInput);

        //output
        System.out.println("Result:");
        String s = number.summarizeCollection(output);
        System.out.println(s);
    }
}