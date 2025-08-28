import java.util.Scanner;

public class Compu3 {
    public static void main(String[] args) throws Exception {
        CompuPrincipal pc = new CompuPrincipal("127.0.0.1", 5002, "127.0.0.1", 5001, "127.0.0.1", 5003);
        new Hilo(pc).start();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Destino IP:");
            String ipDestino = sc.nextLine();
            System.out.println("Destino Puerto:");
            int puertoDestino = Integer.parseInt(sc.nextLine());
            System.out.println("Mensaje:");
            String mensaje = sc.nextLine();

            pc.enviarMensaje(ipDestino, puertoDestino, mensaje);
        }
    }
}
