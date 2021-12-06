/**
 * The Main class of Client module
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
        System.out.print("Insert your nickname: ");
        String nick = sc.next();


        Client client = new Client(nick);
        client.start();
    }

}
