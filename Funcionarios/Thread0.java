package Funcionarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import Descontos.Thread1;
import Descontos.Thread2;
import Descontos.Thread3;
import Descontos.Thread4;

public class Thread0 extends Thread {

    private List<Funcionarios> funcionarios;
    private List<Funcionarios> parte1;
    private List<Funcionarios> parte2;
    private List<Funcionarios> parte3;
    private List<Funcionarios> parte4;
    private Semaphore sem0;
    private Semaphore sem1;
    private Semaphore sem2;
    private Semaphore sem3;
    private Semaphore sem4;
    private Semaphore mutexCont;
    private Semaphore semBarreira;
    private Thread1 th1;
    private Thread2 th2;
    private Thread3 th3;
    private Thread4 th4;
    private int cont[];

    private int n;
    private Random geraAleatorio = new Random();

    public Thread0(int n, int cont[], Semaphore sem0, Semaphore sem1, Semaphore sem2, Semaphore sem3,
            Semaphore sem4, Semaphore mutexCont, Semaphore semBarreira) {
        this.n = n * 4;
        this.sem0 = sem0;
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.sem3 = sem3;
        this.sem4 = sem4;
        this.mutexCont = mutexCont;
        this.semBarreira = semBarreira;
        this.funcionarios = new ArrayList<Funcionarios>(this.n);
        this.parte1 = new ArrayList<Funcionarios>();
        this.parte2 = new ArrayList<Funcionarios>();
        this.parte3 = new ArrayList<Funcionarios>();
        this.parte4 = new ArrayList<Funcionarios>();
        this.cont = cont;

    }

    @Override
    public void run() {
        try {
            // Cria uma lista de n funcionários, onde n é um múltiplo de 4
            criaListaFuncionarios();
            // Divide a lista em quatro partes de igual tamanho, denominadas parte 1, parte
            // 2, parte 3 e parte 4.
            divideLista(funcionarios);


            th1 = new Thread1(cont, parte1, parte2, parte3, parte4, sem1, sem2, sem3, sem4, mutexCont, semBarreira);
            th2 = new Thread2(cont, parte1, parte2, parte3, parte4, sem1, sem2, sem3, sem4, mutexCont, semBarreira);
            th3 = new Thread3(cont, parte1, parte2, parte3, parte4, sem1, sem2, sem3, sem4, mutexCont, semBarreira);
            th4 = new Thread4(cont, parte1, parte2, parte3, parte4, sem1, sem2, sem3, sem4, mutexCont, semBarreira);
            th1.start();
            th2.start();
            th3.start();
            th4.start();


            imprimeLista();

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }

    public void criaListaFuncionarios() {
        while (funcionarios.size() < this.n) {
            int codigoFuncionario = geraAleatorio.nextInt(5000 - 1 + 1) + 1;
            String valor = String.format("%.2f", geraAleatorio.nextDouble(5000 - 1000 + 1) + 1000);
            Double salarioBruto = Double.parseDouble(valor.replace(',', '.'));
            Funcionarios funcionario = new Funcionarios(codigoFuncionario, salarioBruto, 0.0, 0.0, 0.0, 0.0,
                    0.0, 0.0);
            funcionarios.add(funcionario);
        }

    }

    public void divideLista(List<Funcionarios> funcionarios) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (parte1.size() < (funcionarios.size() / 4)) {
                parte1.add(funcionarios.get(i));
            } else if (parte2.size() < (funcionarios.size() / 4)) {
                parte2.add(funcionarios.get(i));
            } else if (parte3.size() < (funcionarios.size() / 4)) {
                parte3.add(funcionarios.get(i));
            } else {
                parte4.add(funcionarios.get(i));
            }
        }
    }

    public void imprimeLista() {
        for (Funcionarios func : parte1) {
            System.out.println("Codigo: " + func.getCodigo() + "\n" +
                    "Salario Bruto: " + func.getSalarioBruto() + "\n" +
                    "Desconto IR: " + func.getDescontoIR() + "\n" +
                    "Desconto INSS: " + func.getDescontoINSS() + "\n" +
                    "Desconto Plano: " + func.getDescontoPlanoSaude() + "\n" +
                    "Desconto Previdencia: " + func.getDescontoPrevidencia() + "\n" +
                    "Total de Descontos: " + func.getTotalDescontos() + "\n" +
                    "Salario Liquido: " + func.getSalarioLiquido() + "\n");
        }
    }

}
