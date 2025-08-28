import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Hilo extends Thread {
    private CompuPrincipal compu;

    public Hilo(CompuPrincipal compu) {
        this.compu = compu;
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(compu.getPuerto())) {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);
                String data = new String(paquete.getData(), 0, paquete.getLength());
                compu.procesarMensaje(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
