/**
 * The Client class implements a client of a server
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    /**
     * attributes
     */
    private static String host = "localhost";
    private static int PORT = 4999;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ServerConnection serverConnection;

    private static Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        logger = Logger.getLogger(Client.class.getName());
    }

    /**
     * Constructor
     * @param nick
     * @throws IOException
     */
    public Client(String nick) throws IOException {
        socket = new Socket(host,PORT);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(nick);
    }

    /**
     * send a request from client command line
     */
    public void sendRequest (){
        String command = null;
        try {
            command = in.readLine();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        out.println(command);
    }

    /**
     * starts server connection
     * @throws IOException
     */
    public void start() throws IOException {
        serverConnection = new ServerConnection(this);
            while (true){
                serverConnection.run();
                if(!socket.isConnected()) break;
            }
    }

    /**
     * disconnects with server
     * @throws IOException
     */
    public void disconnect() throws IOException {
        socket.close();
        in.close();
        out.close();
    }

    public Socket getSocket() {
        return socket;
    }
}
