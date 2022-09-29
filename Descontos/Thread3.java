package Descontos;

import java.util.List;
import java.util.concurrent.Semaphore;

import Funcionarios.Funcionarios;

public class Thread3 extends Thread {

    private List<Funcionarios> parte1;
    private List<Funcionarios> parte2;
    private List<Funcionarios> parte3;
    private List<Funcionarios> parte4;
    private Semaphore sem1;
    private Semaphore sem2;
    private Semaphore sem3;
    private Semaphore sem4;
    private Semaphore mutexCont;
    private Semaphore semBarreira;
    private int[] cont;

    public Thread3(int[] cont, List<Funcionarios> parte1, List<Funcionarios> parte2, List<Funcionarios> parte3,
            List<Funcionarios> parte4,
            Semaphore sem1, Semaphore sem2, Semaphore sem3, Semaphore sem4, Semaphore mutexCont,
            Semaphore semBarreira) {
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.sem3 = sem3;
        this.sem4 = sem4;
        this.mutexCont = mutexCont;
        this.semBarreira = semBarreira;
        this.parte1 = parte1;
        this.parte2 = parte2;
        this.parte3 = parte3;
        this.parte4 = parte4;
        this.cont = cont;
    }

    @Override
    public void run() {
        try {

            sem3.acquire();
            calculoPrevidencia(parte3);
            sem3.release();
            sem4.acquire();
            calculoPrevidencia(parte4);
            sem4.release();
            sem1.acquire();
            calculoPrevidencia(parte1);
            sem1.release();
            sem2.acquire();
            calculoPrevidencia(parte2);
            sem2.release();

            mutexCont.acquire();
            cont[0]++;
            if (cont[0] == 4) {
                semBarreira.release();
            }
            mutexCont.release();

            semBarreira.acquire();
            semBarreira.release();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculoPrevidencia(List<Funcionarios> funcionarios) {
        for (Funcionarios func : funcionarios) {
            String valor = String.format("%.2f", func.getSalarioBruto() * 0.04);
            Double valorDesconto = Double.parseDouble(valor.replace(',', '.'));
            func.setDescontoPrevidencia(valorDesconto);

        }

    }

}
