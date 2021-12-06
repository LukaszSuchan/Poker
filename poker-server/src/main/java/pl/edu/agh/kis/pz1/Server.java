/**
 * The Server class
 *
 * @author LukaszSuchan
 * @version 1.0
 */
package pl.edu.agh.kis.pz1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    /**
     * attributes
     */
    private static final int PORT = 4999;
    private final ServerSocket serverSocket;
    private static int numberOfClients;
    private static ArrayList<ClientHandler> clients;
    public static Game game;

    /**
     * Constructor
     * @param number
     * @throws IOException
     */
    public Server(int number) throws IOException {
        serverSocket = new ServerSocket(PORT);
        numberOfClients = number;
        clients = new ArrayList<>();
    }

    /**
     * starts a Server
     * @throws IOException
     */
    public void startServer() throws IOException {
        try {
            while (clients.size() != numberOfClients) {
                Socket client = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                clients.add(clientHandler);
            }

            while (true) {
                startGame();
                exchangeCardRound();
                betRound();
                while (everyCallOrFold()) {
                    betRound();
                }
                resultRound();
                emptyHands();
                if(clients.size() != numberOfClients) break;
            }
        } finally {
           closeServerSocket();
        }
    }

    /**
     * starts a game on server
     */
    private static void startGame(){
        game = new Game(numberOfClients);
        for(ClientHandler client : clients){
            game.addPlayer(client.getPlayer());
        }
        for(ClientHandler client : clients){
            client.startGame();
        }
    }

    /**
     * exchanges card on server
     */
    private void exchangeCardRound(){
        for(ClientHandler client : clients){
            client.exchangeCard();
        }
    }

    /**
     * proceeds a bet round
     */
    private void betRound(){
        for(ClientHandler client : clients){
            client.bet();
        }
    }

    /**
     * check whether all players have called
     * @return
     */
    private boolean everyCallOrFold(){
        int pivot = 0;
        for(ClientHandler client : ClientHandler.clientHandlers){
            if(client.getPlayer().fold) continue;
            pivot = client.getPlayer().getPlacedBet();
        }
        for(ClientHandler client : ClientHandler.clientHandlers){
            if(client.getPlayer().fold) continue;
            if(client.getPlayer().getPlacedBet() != pivot) return true;
        }

        return false;
    }

    /**
     * send a message (server side)
     * @param name
     * @param winner
     */
    private void messageOfSomeoneWin(String name,Player winner){
        for(ClientHandler client : ClientHandler.clientHandlers){
            if(!client.getPlayer().equals(winner)){
                client.messageOfSomeoneWin(name);
            }
        }
    }

    /**
     * send a message (server side)
     * @param winners
     * @param names
     */
    private void messageForTie(ArrayList<Player> winners, ArrayList<String> names){
        for(ClientHandler client : ClientHandler.clientHandlers){
            for(Player winner : winners){
                if(!client.getPlayer().equals(winner)){
                    client.messageForTie(names);
                }
            }
        }
    }

    /**
     * send a message (server side)
     * @param winners
     * @return
     */
    private ArrayList<String> messageOfWinForTie(ArrayList<Player> winners){
        ArrayList<String> names = new ArrayList<>();
        for(ClientHandler clientHandler : ClientHandler.clientHandlers){
            for(Player winner : winners){
                if(clientHandler.getPlayer().equals(winner)){
                    names.add(clientHandler.getNick());
                    clientHandler.messageOfWin();
                }
            }
        }
        return names;
    }

    /**
     * refreshes players
     */
    private void addRefreshedPlayers(){
        for(ClientHandler client : ClientHandler.clientHandlers){
            game.getPlayers().add(client.getPlayer());
        }
    }

    /**
     * proceeds a result round
     */
    private void resultRound(){
        addRefreshedPlayers();
        ArrayList<Player> winners = game.getWinner();
        if(winners.size() == 1){
            Player winner = winners.get(0);
            String name = null;
            for(ClientHandler client : ClientHandler.clientHandlers){
                if(client.getPlayer().equals(winner)){
                    name = client.getNick();
                    game.givePotToWinner(client.getPlayer());
                    client.messageOfWin();
                    break;
                }
            }
            messageOfSomeoneWin(name,winner);

        } else {
            ArrayList<String> names = messageOfWinForTie(winners);
            messageForTie(winners,names);
            game.splitPot(winners);
        }
    }

    /**
     * clears players hands
     */
    private void emptyHands(){
        for(ClientHandler clientHandler : ClientHandler.clientHandlers){
            clientHandler.getPlayer().emptyHand();
        }
    }

    /**
     * closes server socket and client sockets
     * @throws IOException
     */
    private void closeServerSocket() throws IOException {
        for(ClientHandler clientHandler : clients){
            clientHandler.closeEverything();
        }
        serverSocket.close();
    }

}
