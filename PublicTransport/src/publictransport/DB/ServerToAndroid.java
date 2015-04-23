package publictransport.DB;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerToAndroid {
    public static void main(String[] args) {
        try {
            /* Levantamos el servidor que escucha en el puerto 8888 */
            ServerSocket serverSocket = new ServerSocket(8888);

            /* Espera solicitud de conexion */
            Socket socket = serverSocket.accept();

            /* Stream para enviar datos */
            OutputStream os = socket.getOutputStream();

            /* Stream para recibir datos */
            InputStream is = socket.getInputStream();

            /* Enviamos un mensage al cliente */
            String x = "Bienvenido...";
            os.write(x.getBytes());
            os.flush();

            /* Leemos los caracteres enviados por el cliente mientras exista conexion */
            int c;
            while( (c = is.read()) != -1 )
                System.out.print((char)c);

            /* Cerramos los streams y el socket */
            os.close();
            is.close();
            socket.close();

        } catch(Exception ex) {
        }
    }
}
