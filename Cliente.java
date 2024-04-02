import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        
        int puerto = 8080;

        try {
            
            // Cuando un cliente se conecta se abre el puerto a escuchar conexiones de cliente
            // Si el servidor responde, AQUI SE CREA EL SOCKET EN EL SO
            Socket cs = new Socket("localhost", puerto);

            // Establecer el stream de salida
            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);

            // Establece el stream de entrada
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            String linearecibida;

            // Leemos la linea por linea hasta recibir "EOF"
            while (!(linearecibida = in.readLine()).equalsIgnoreCase("EOF")) {
                System.out.println("Servidor: " + linearecibida);
            }

            // Envía la despedida al servidor
            out.println("Recepción de datos correcta...");

            out.close();
            in.close();
            cs.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
