package Data;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import Funcionarios.Funcionarios;

public class GeraArquivo {

    public GeraArquivo() {
    }

    public static void salvaArquivo(List<Funcionarios> partes, String caminho) {

        try {
            FileWriter arquivo = new FileWriter(caminho);

            for (Funcionarios func : partes) {
                arquivo.write("Codigo: " + func.getCodigo() + "\n" +
                        "Salario Bruto: " + func.getSalarioBruto() + "\n" +
                        "Desconto IR: " + func.getDescontoIR() + "\n" +
                        "Desconto INSS: " + func.getDescontoINSS() + "\n" +
                        "Desconto Plano: " + func.getDescontoPlanoSaude() + "\n" +
                        "Desconto Previdencia: " + func.getDescontoPrevidencia() + "\n" +
                        "Total de Descontos: " + func.getTotalDescontos() + "\n" +
                        "Salario Liquido: " + func.getSalarioLiquido() + "\n" + "\n");

            }
            arquivo.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
