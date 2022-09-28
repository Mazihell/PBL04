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

    public Thread3(List<Funcionarios> parte1,List<Funcionarios> parte2,List<Funcionarios> parte3,List<Funcionarios> parte4,
    Semaphore sem1,Semaphore sem2,Semaphore sem3,Semaphore sem4) {
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.sem3 = sem3;
        this.sem4 = sem4;
        this.parte1 = parte1;
        this.parte2 = parte2;
        this.parte3 = parte3;
        this.parte4 = parte4;
    }

    @Override
    public void run() {
        try {

            sem3.acquire();     
            
            calculoPrevidencia(parte3);

            sem4.release();
            calculoPrevidencia(parte4);
            calculoPrevidencia(parte1);
            calculoPrevidencia(parte2);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculoPrevidencia(List<Funcionarios> funcionarios) {
        for (Funcionarios func : funcionarios) {
            func.setDescontoPrevidencia(func.getSalarioBruto() * 0.04);

        }

    }

}
