package ServidorMultihilo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;

public class HiloHandler implements Runnable{
    
    private final Socket ch;
    private int id;
    public PrintWriter out;
    public BufferedReader in;


    // Creamos los flujos
    public HiloHandler(Socket ch, int id) throws IOException {
        // Recib eel socket para escuchar conexiones del cliente
        this.ch = ch;
        this.id = id;
        // Establece el stream de salida
        out = new PrintWriter(ch.getOutputStream(), true);
        // Establece el stream de entrada
        in = new BufferedReader(new InputStreamReader(ch.getInputStream()));

        System.out.println("Conexión recibida del cliente: " + ch.getInetAddress().getHostAddress());
        System.out.println("El servidor tiene " + id + " clientes conectados.");
    }

    @Override
    public void run() {
        try{

            out.println(id);

            String pathArchivo = Paths.get("ServidorMultihilo//archivote.csv").toAbsolutePath().toString();
            File file_in = new File(pathArchivo);

            // Pasar las lineas del archivo al cliente
            FileReader fr;
            fr = new FileReader(file_in);
            BufferedReader br = new BufferedReader(fr);

            String lineaLeida;
            while((lineaLeida = br.readLine()) != null) {
                out.println(lineaLeida);
            }

            out.println("EOF");
            br.close();
            fr.close();

            System.out.println("Cliente " + id + ": " + in.readLine());

            out.close();
            in.close();
            ch.close();

        } catch(IOException ex) {
            System.out.println(ex);
        }
    }

}



