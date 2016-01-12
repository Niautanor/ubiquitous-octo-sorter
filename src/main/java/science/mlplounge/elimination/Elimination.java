package science.mlplounge.elimination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;

public class Elimination {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            usage();
            return;
        }
        Random random = new Random();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        ArrayList<String> candidates = new ArrayList<>();
        Deque<String> eliminated = new ArrayDeque<>();
        String line;

        while ((line = br.readLine()) != null) {
            candidates.add(line);
        }

        br.close();

        if (candidates.size() < 1) {
            usage();
            return;
        }

        Scanner in = new Scanner(System.in);

        System.out.println("Starting Elimination with " + candidates.size() + " contestants");

        int round = 1, numRounds = candidates.size()-1, c;
        int[] pick = new int[2];
        while (candidates.size() > 1) {
            pick[0] = random.nextInt(candidates.size());
            do {
                pick[1] = random.nextInt(candidates.size());
            } while (pick[1] == pick[0]);
            do {
                System.out.println("Round " + round + "/" + numRounds + ":");
                System.out.println("[1] - " + candidates.get(pick[0]));
                System.out.println("[2] - " + candidates.get(pick[1]));
                System.out.println("");
                c = in.nextInt();
            } while (c != 1 && c != 2);
            int remove = (c == 1) ? 1 : 0;
            eliminated.addFirst(candidates.remove(pick[remove]));
        }

        eliminated.addFirst(candidates.remove(0));

        int i = 0;
        for (String s : eliminated) {
            System.out.println((++i) + " - " + s);
        }

        System.out.println("Press enter to continue");
        in.nextLine();
        in.close();
    }

    public static void usage() {
        System.out.println("Usage: java -jar elimination-sorter.jar filename");
        System.out.println("");
        System.out.println("filename should be a file containing the things that you want to choose from. One thing per line. It should also have at least one line and no empty lines");
        System.out.println("If you are on windows, you might be able to just drag your file into the jar");
    }
}
