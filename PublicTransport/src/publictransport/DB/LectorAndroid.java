package publictransport.DB;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LectorAndroid {
    public static void main(String[] args) {
        try {
            /* Establece la conexion */
            Socket socket = new Socket("10.0.0.4", 8888);
            /* Stream para enviar datos */
            OutputStream os = socket.getOutputStream();
            /* Stream para recibir datos */
            InputStream is = socket.getInputStream();
            /* Leemos el mensage del servidor mientras exista conexion y no llegue el caracter '.' */
            int c;
            while( (c = is.read()) != -1  && !String.valueOf((char)c).equals("."))
                System.out.print((char)c);
            /* Enviamos un mensaje al servidor */
            String x = "Hola Servidor...";
            os.write(x.getBytes());
            os.flush();
            /* Cerramos los streams y el socket */
            os.close();
            is.close();
            socket.close();
        } catch(Exception ex) {
        }
    }
}
