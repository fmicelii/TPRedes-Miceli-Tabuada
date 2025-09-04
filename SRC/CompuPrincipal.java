import java.io.*;
import java.net.*;

public class CompuPrincipal {
    private String ip;
    private int puerto;
    private String ipIzq;
    private int puertoIzq;
    private String ipDer;
    private int puertoDer;

    public CompuPrincipal(String ip, int puerto, String ipIzq, int puertoIzq, String ipDer, int puertoDer) {
        this.ip = ip;
        this.puerto = puerto;
        this.ipIzq = ipIzq;
        this.puertoIzq = puertoIzq;
        this.ipDer = ipDer;
        this.puertoDer = puertoDer;
    }

    public int getPuerto() {
        return puerto;
    }

    public String getIp() {
        return ip;
    }

    public void enviarMensaje(String ipDestino, int puertoDestino, String mensaje) {
        try {
            // Si el destino soy yo
            if (ip.equals(ipDestino) && puerto == puertoDestino) {
                System.out.println("Soy el destino " + ip + ":" + puerto + " -> Mensaje recibido: " + mensaje);
                return;
            }

            // Decidir hacia dónde reenviar
            if (puertoDestino < puerto && ipIzq != null) {
                reenviar(ipIzq, puertoIzq, ipDestino, puertoDestino, mensaje);
            } else if (puertoDestino > puerto && ipDer != null) {
                reenviar(ipDer, puertoDer, ipDestino, puertoDestino, mensaje);
            } else {
                System.out.println("No existe la computadora con IP " + ipDestino + " y puerto " + puertoDestino);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reenviar(String ipVecino, int puertoVecino, String ipDestino, int puertoDestino, String mensaje) {
        try {
            Socket socket = new Socket(ipVecino, puertoVecino);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(ipDestino);
            out.println(puertoDestino);
            out.println(mensaje);
            out.println(ip + ":" + puerto); // para mostrar el recorrido
            socket.close();

            System.out.println("[" + ip + ":" + puerto + "] -> [" + ipVecino + ":" + puertoVecino + "]: reenviando...");
        } catch (Exception e) {
            System.out.println("Error reenviando a " + ipVecino + ":" + puertoVecino);
        }
    }

    public void escuchar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String ipDestino = in.readLine();
                int puertoDestino = Integer.parseInt(in.readLine());
                String mensaje = in.readLine();
                String recorrido = in.readLine();

                System.out.println("Mensaje recibido en " + ip + ":" + puerto + " (recorrido hasta ahora: " + recorrido + ")");

                if (ip.equals(ipDestino) && puerto == puertoDestino) {
                    System.out.println("Llego a " + ip + ":" + puerto + " -> " + mensaje);
                } else {
                    // reenviar
                    enviarMensaje(ipDestino, puertoDestino, mensaje);
                }
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
