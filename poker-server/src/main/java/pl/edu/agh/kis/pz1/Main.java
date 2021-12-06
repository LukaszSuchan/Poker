/**
 * The Main class
 *
 * @author LukaszSuchan
 * @version 1.0
 */
package pl.edu.agh.kis.pz1;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    /**
     * main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("------------------------------------");
        System.out.println("|              POKER               |");
        System.out.println("------------------------------------");

        Scanner sc = new Scanner(System.in);
        System.out.print("set number of players [2-4]: ");
        String numberOfPlayers = sc.next();
        int number = Integer.parseInt(numberOfPlayers);

        Server server = new Server(number);
        System.out.println("Game has been created for " + numberOfPlayers + " players.");
        server.startServer();

    }
}
