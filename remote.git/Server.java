/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echozone;

/**
 *
 * @author MeijiTenno
 */
import java.io.*;
import java.net.*;
import java.lang.Thread;

public class Server {

    static boolean finito = false;
    static int i = 0;
    static PrintStream os;

    public static void main(String args[]) {

        ServerSocket echoServer = null;
        String line;
        BufferedReader is;

        String chiudi[] = {"Bye", "Bye", "Arrivederci"};
        //String chiudi = "bye";
        Socket clientSocket = null;
        try {
            echoServer = new ServerSocket(9999);
            System.out.println("Server");
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            clientSocket = echoServer.accept();
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());

            while (true) {

                line = is.readLine();
                System.out.println(line);
                System.out.println(line.equals(chiudi[i]));
                if (line.equals(chiudi[i]) && !finito) {
                    checkTheCombo(line, chiudi);
                } else {
                    i = 0;
                }
                os.println(i + " " + "ECHO " + line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void checkTheCombo(String l, String c[]) {
        if (l.equals(c[i])) {
            i++;
        }
        if (i == 3) {
            finito = true;
            os.println("Chiudi");
        }
    }

}
