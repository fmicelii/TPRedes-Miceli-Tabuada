import java.util.Scanner;

public class Compu2 {
    public static void main(String[] args) throws Exception {
        CompuPrincipal pc = new CompuPrincipal("127.0.0.1", 5001, "127.0.0.1", 5000, "127.0.0.1", 5002);
        new Hilo(pc).start();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Destino IP:");
            String ipDestino = sc.nextLine();

            if (ipDestino.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            System.out.println("Destino Puerto:");
            String puertoInput = sc.nextLine();
            if (puertoInput.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            }
            int puertoDestino = Integer.parseInt(puertoInput);

            System.out.println("Mensaje (escribe 'salir' para terminar):");
            String mensaje = sc.nextLine();
            if (mensaje.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            pc.enviarMensaje(ipDestino, puertoDestino, mensaje);
        }
        sc.close();
        System.exit(0); // Cierra todo el programa, incluyendo el hilo
    }
}
