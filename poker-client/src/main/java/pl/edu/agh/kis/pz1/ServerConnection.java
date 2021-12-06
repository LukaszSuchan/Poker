/**
 * The ServerConnection class implements a client connection protocol
 * with server
 *
 * @author LukaszSuchan
 * @version 1.0
 */

package pl.edu.agh.kis.pz1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {
    /**
     * attributes
     */
    private BufferedReader in;
    private Socket socket;
    private Client client;

    private static Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(ServerConnection.class.getName());
    }

    /**
     * Constructor
     * @param client
     * @throws IOException
     */
    public ServerConnection(Client client) throws IOException {
        socket = client.getSocket();
        this.client = client;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Class run method
     */
    public void run() {
        String serverResponse = "";
        try {
            serverResponse = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        switch (serverResponse) {
            case "WAITING_FOR_CONNECTION":
                System.out.println("Wait for other players...");
                break;
            case "WAITING":
                System.out.println("Wait for your turn...");
                break;
            case "YOUR_TURN":
                System.out.println("Your turn for bet");
                break;
            case "DISPLAY_GAME":
                display_game();
                break;
            case "EXCHANGE_CARD":
                exchangeCardCommunicat();
                break;
            case "BET":
                bet();
                break;
            case "TO_CALL":
                toCallCommunication();
                break;
            case "YOU_WON":
                youWon();
                break;
            case "SOMEONE_WON":
                someoneWon();
                break;
            case "TIE":
                tie();
                break;
            case "wrong command":
                System.out.println("wrong command");
                break;

            default:
                System.out.println(serverResponse);
        }
    }

    /**
     * displays_game interface on console.
     * used in protocol
     */
    private void display_game(){
        printLine();
        System.out.print("your money: ");
        String money = null;
        try {
            money = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        System.out.println(money);
        System.out.print("pot: ");
        String pot = null;
        try {
            pot = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        System.out.println(pot);
        System.out.println("your hand: ");
        String hand = null;
        for(int i = 0; i < 6; i++) {
            try {
                hand = in.readLine();
            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage());
            }
            System.out.println(hand);
        }

    }

    /**
     * gives information about card exchanges
     * used in protocol
     */
    private void exchangeCardCommunicat() {
        System.out.println("which cards you want to exchange (insert [none] if none): ");
        client.sendRequest();
    }

    /**
     * gives information about calling
     * used in protocol
     */
    private void toCallCommunication(){
        System.out.print("money: ");
        String money = null;
        try {
            money = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        System.out.println(money);
        System.out.print("To call : ");
        String callAmount = null;
        try {
            callAmount = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        System.out.println(callAmount);
    }

    /**
     * gives information about betting
     * used in protocol
     */
    private void bet(){
        System.out.println("[call] to call, [amount_bet] to bet or raise, [fold] to fold");
        client.sendRequest();
    }

    /**
     * gives information about client win
     * used in protocol
     */
    private void youWon(){
        System.out.println("YOU WON!!!");
    }

    /**
     * gives information about others client win
     * used in protocol
     */
    private void someoneWon(){
        String winner = null;
        try {
            winner = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        System.out.println(winner + " won !!!");
    }

    /**
     * gives information of tie
     * used in protocol
     */
    private void tie(){
        System.out.println("TIE !!! between:");
        StringBuilder winners = new StringBuilder();
        String numberOfWinners = null;
        try {
            numberOfWinners = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        for(int i = 0; i < Integer.parseInt(numberOfWinners); i++){
            String winner  = null;
            try {
                winner = in.readLine();
            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage());
            }
            winners.append(winner);
            winners.append("\n");
        }
        System.out.println(winners);
    }

    /**
     * print line of '='
     */
    public static void printLine(){
        System.out.println("------------------------");
    }
}
