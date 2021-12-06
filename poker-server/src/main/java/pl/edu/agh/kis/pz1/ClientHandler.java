/**
 * The ClientHandler class implements a clienthandler
 *
 * @author LukaszSuchan
 * @version 1.0
 */
package pl.edu.agh.kis.pz1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler {
    /**
     * attributes
     */
    protected static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Player player;
    private String nick;

    private static Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(ClientHandler.class.getName());
    }

    /**
     * Constructor
     * @param client
     * @throws IOException
     */
    public ClientHandler(Socket client) throws IOException {
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
        this.socket = client;
        this.player = new Player();
        nick = in.readLine();
        clientHandlers.add(this);
        System.out.println("[SERVER] " + nick + " has connected");
        out.println("WAITING_FOR_CONNECTION");
    }

    /**
     * starts a game
     */
    public void startGame(){
        out.println("DISPLAY_GAME");
        String money = Integer.toString(player.money);
        out.println(money);
        String pot = Integer.toString(Server.game.getPot());
        out.println(pot);
        ArrayList<String> hand = player.showCards();
        for(String row : hand){
            out.println(row);
        }
        out.println("WAITING");
    }

    /**
     * exchanges card of a player hand
     */
    public void exchangeCard(){
        out.println("EXCHANGE_CARD");
        String exchange = "";
        try {
            exchange = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        if(!exchange.equals("none")){
            String[] exchangeNums = exchange.split(" ");
            for(int i = 0; i < exchangeNums.length; i++){
                player.exchangeCard(Integer.parseInt(exchangeNums[i]),Server.game.getDeck());
            }
        }
        startGame();
    }

    /**
     * bet what player insert
     */
    public void bet(){
        out.println("YOUR_TURN");
        out.println("TO_CALL");
        out.println(player.money);
        int maxBet = 0;
        for(ClientHandler clientHandler : clientHandlers){
            int bet = clientHandler.getPlayer().getPlacedBet();
            if(maxBet < bet){
                maxBet = bet;
            }
        }
        int toCall = 0;
        if(maxBet !=0) {
            toCall = maxBet - player.getPlacedBet();
        }
        out.println(Integer.toString(toCall));
        out.println("BET");
        String choice = "";
        try {
            choice = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        if(choice.equals("call")){
            player.placeBet(toCall);
        } else if(choice.equals("fold")){
            player.fold = true;
        }
        else{
            int amount = Integer.parseInt(choice);
            Server.game.addToPot(player.placeBet(amount));
        }
        out.println("WAITING");
    }

    /**
     * send a protocol to client
     */
    public void messageOfWin(){
        out.println("YOU_WIN");
    }

    /**
     * send a protocol to client
     * @param winner
     */
    public void messageOfSomeoneWin(String winner){
        out.println("SOMEONE_WON");
        out.println(winner);
    }

    /**
     * send a protocol to client
     * @param winners
     */
    public void messageForTie(ArrayList<String> winners){
        out.println("TIE");
        out.println(winners.size());
        for(String winner : winners){
            out.println(winner);
        }
    }

    /**
     * close socket and input,output
     * @throws IOException
     */
    public void closeEverything() throws IOException {
        socket.close();
        in.close();
        out.close();
    }

    public Player getPlayer() {
        return player;
    }

    public String getNick() {
        return nick;
    }
}
