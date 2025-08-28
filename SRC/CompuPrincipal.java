import java.io.IOException;
import java.net.*;

public class CompuPrincipal {
    private String ip;
    private int puerto;
    private String ipVecinoIzq;
    private int puertoVecinoIzq;
    private String ipVecinoDer;
    private int puertoVecinoDer;

    public CompuPrincipal(String ip, int puerto, String ipVecinoIzq, int puertoVecinoIzq, String ipVecinoDer, int puertoVecinoDer) {
        this.ip = ip;
        this.puerto = puerto;
        this.ipVecinoIzq = ipVecinoIzq;
        this.puertoVecinoIzq = puertoVecinoIzq;
        this.ipVecinoDer = ipVecinoDer;
        this.puertoVecinoDer = puertoVecinoDer;
    }

    public void enviarMensaje(String destinoIp, int destinoPuerto, String msg) throws IOException {
        // Formato: destinoIp:destinoPuerto|mensaje
        String mensajeCompleto = destinoIp + ":" + destinoPuerto + "|" + "" + "#" + msg;
        // Como origen siempre enviamos al vecino derecho si iniciamos
        if (ipVecinoDer != null) {
            reenviarMensaje(mensajeCompleto, ipVecinoDer, puertoVecinoDer);
        } else if (ipVecinoIzq != null) {
            reenviarMensaje(mensajeCompleto, ipVecinoIzq, puertoVecinoIzq);
        }
    }

    public void procesarMensaje(String data) throws IOException {
        String[] partes = data.split("\\|", 2);
        String[] destino = partes[0].split(":");
        String destinoIp = destino[0];
        int destinoPuerto = Integer.parseInt(destino[1]);

        // El segundo campo lo tratamos como "traza#mensaje"
        String[] resto = partes[1].split("#", 2);
        String traza = resto[0];
        String mensaje = resto.length > 1 ? resto[1] : resto[0];

        // Agregar esta compu a la traza
        if (!traza.isEmpty()) {
            traza += " -> ";
        }
        traza += "Compu" + (puerto - 4999); // Asume que 5000=Compu1, 5001=Compu2, etc.

        if (destinoIp.equals(this.ip) && destinoPuerto == this.puerto) {
            System.out.println("Recorrido: " + traza);
            System.out.println("Mensaje recibido: " + mensaje);
        } else {
            String nuevoData = partes[0] + "|" + traza + "#" + mensaje;
            if (destinoPuerto > this.puerto && ipVecinoDer != null) {
                reenviarMensaje(nuevoData, ipVecinoDer, puertoVecinoDer);
            } else if (ipVecinoIzq != null) {
                reenviarMensaje(nuevoData, ipVecinoIzq, puertoVecinoIzq);
            }
        }
    }

    private void reenviarMensaje(String data, String ipDestino, int puertoDestino) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ipReenvio = InetAddress.getByName(ipDestino);
        DatagramPacket dp = new DatagramPacket(data.getBytes(), data.length(), ipReenvio, puertoDestino);
        ds.send(dp);
        ds.close();
    }

    public int getPuerto() {
        return puerto;
    }

    public String getIp() {
        return ip;
    }
}
