import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("A filename must be provided");

        StringBuilder input = new StringBuilder();

        try {
            Scanner myReader = new Scanner(new File(args[0]));
            while (myReader.hasNextLine()) {
                input.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Analyzer analyzer = new Analyzer(input.toString());
        analyzer.PrintAnalysis(3);
    }
}