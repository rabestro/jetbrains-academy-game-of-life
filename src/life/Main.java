package life;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final var sc = new Scanner(System.in);
        final int size = sc.nextInt();
        final int steps = 11;
        sc.close();

        final var universe = new Universe(size).initialise();

        for (int i = 0; i < steps; ++i) {
            System.out.printf("Generation #%d%nAlive: %d%n", universe.getGeneration(), universe.getAliveCount());
            universe.printMap();
            universe.nextGeneration();
            Thread.sleep(1000);
            clearScreen();
        }
    }

    static void clearScreenOld() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
