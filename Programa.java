import java.util.concurrent.Semaphore;

import Funcionarios.Thread0;

public class Programa {
    public static void main(String[] args) throws Exception {

        Semaphore sem0 = new Semaphore(1);
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);
        Semaphore sem3 = new Semaphore(1);
        Semaphore sem4 = new Semaphore(1);
        Semaphore mutexCont = new Semaphore(1);
        Semaphore semBarreira = new Semaphore(0);

        int cont[] = new int[1];
        cont[0] = 0;
        Thread0 t0 = new Thread0(4,cont, sem0, sem1, sem2, sem3, sem4,mutexCont,semBarreira);
               

        t0.start();

        try {

            t0.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
