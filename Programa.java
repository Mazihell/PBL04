import java.util.concurrent.Semaphore;

import Funcionarios.Thread0;

public class Programa {
    public static void main(String[] args) throws Exception {

        Semaphore sem0 = new Semaphore(1);
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);
        Semaphore sem3 = new Semaphore(1);
        Semaphore sem4 = new Semaphore(1);
        int vert[] = new int[1];
        Thread0 t0 = new Thread0(4, vert, sem0, sem1, sem2, sem3, sem4);
               

        t0.start();

        try {

            t0.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
