import java.util.Scanner;

public class Compu1 {
    public static void main(String[] args) throws Exception {
        CompuPrincipal pc = new CompuPrincipal("127.0.0.1", 5000, null, -1, "127.0.0.1", 5001);
        new Hilo(pc).start();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Destino IP:");
            String ipDestino = sc.nextLine();

            // Si escriben "salir" en vez de la IP, termina el programa
            if (ipDestino.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break; // sale del while
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
        sc.close(); // cerrar el scanner
        System.exit(0); // termina el programa y mata los threads
    }
}
