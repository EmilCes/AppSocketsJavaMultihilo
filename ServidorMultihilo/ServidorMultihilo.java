package ServidorMultihilo;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorMultihilo{

    public static void main(String[] args) {
        
        int puerto = 8080;
        int cont = 0;

        // Crea un socket de servidor
        try (ServerSocket ss = new ServerSocket(puerto)) {

            System.out.println("Servidor escuchando en el puerto: " + puerto + "...");

            // El servidor va a escuchar conexiones hasta presionar Ctrl + C
            // Cada cliente se manda a un nuevo hilo por lo que siempre
            // estara dispuesto a recibir nuevos clientes
            while (true) {
                cont++;
                // El servdiror envía una rchivo grande a cada cliente
                HiloHandler cliente = new HiloHandler(ss.accept(), cont);
                Thread h1 = new Thread(cliente);
                h1.start();
            }

        } catch(IOException ex) {
            System.out.println(ex);
        }

    }

}


