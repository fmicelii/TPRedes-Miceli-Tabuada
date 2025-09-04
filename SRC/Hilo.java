public class Hilo extends Thread {
    private CompuPrincipal pc;

    public Hilo(CompuPrincipal pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        pc.escuchar();
    }
}
