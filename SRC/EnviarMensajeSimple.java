import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class EnviarMensajeSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Datos del remitente
            System.out.print("IP origen: ");
            String ipOrigen = sc.nextLine();

            System.out.print("Puerto origen: ");
            int puertoOrigen = Integer.parseInt(sc.nextLine());

            // Datos del receptor
            System.out.print("IP destino: ");
            String ipDestino = sc.nextLine();

            System.out.print("Puerto destino: ");
            int puertoDestino = Integer.parseInt(sc.nextLine());

            // Mensaje
            System.out.print("Mensaje: ");
            String mensaje = sc.nextLine();

            // Crear socket UDP con puerto de origen
            DatagramSocket socket = new DatagramSocket(puertoOrigen, InetAddress.getByName(ipOrigen));
            InetAddress address = InetAddress.getByName(ipDestino);

            byte[] buffer = mensaje.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, puertoDestino);

            // Enviar mensaje
            socket.send(packet);
            socket.close();

            // Mostrar confirmación
            System.out.println("Mensaje enviado hacia el bus: "
                    + "[" + ipOrigen + ":" + puertoOrigen + "] -> ["
                    + ipDestino + ":" + puertoDestino + "] con contenido: " + mensaje);
            System.out.println("⚠️  La ruta real la verás en el destino (traza generada por las Compus intermedias).");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
