package Descontos;

import java.util.List;
import java.util.concurrent.Semaphore;

import Data.GeraArquivo;
import Funcionarios.Funcionarios;

public class Thread2 extends Thread {

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
    private String caminho;
    private GeraArquivo geraArquivo;

    public Thread2(int[] cont, List<Funcionarios> parte1, List<Funcionarios> parte2, List<Funcionarios> parte3,
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
        caminho = "Data/parte2.txt";
        this.geraArquivo = new GeraArquivo();
    }

    @Override
    public void run() {
        try {

            sem2.acquire();
            calculoINSS(parte2);
            sem2.release();
            sem3.acquire();
            calculoINSS(parte3);
            sem3.release();
            sem4.acquire();
            calculoINSS(parte4);
            sem4.release();
            sem1.acquire();
            calculoINSS(parte1);
            sem1.release();

            mutexCont.acquire();
            cont[0]++;
            if (cont[0] == 4) {
                semBarreira.release();
            }
            mutexCont.release();

            semBarreira.acquire();
            geraArquivo.salvaArquivo(parte2, caminho);
            semBarreira.release();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculoINSS(List<Funcionarios> funcionarios) {
        for (Funcionarios func : funcionarios) {
            String valor = String.format("%.2f", func.getSalarioBruto() * 0.08);
            Double valorDesconto = Double.parseDouble(valor.replace(',', '.'));
            func.setDescontoINSS(valorDesconto);

        }

    }

}
