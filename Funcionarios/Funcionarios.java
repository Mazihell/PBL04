package Funcionarios;

public class Funcionarios {
    private int codigo;
    private double salarioBruto;
    private double descontoIR;
    private double descontoINSS;
    private double descontoPrevidencia;
    private double descontoPlanoSaude;
    private double totalDescontos;
    private double salarioLiquido;

    public Funcionarios() {

    }

    public Funcionarios(int codigo, double salarioBruto, double salarioLiquido, double descontoIR, double descontoINSS,
            double descontoPrevidencia, double descontoPlanoSaude, double totalDescontos) {
        this.codigo = codigo;
        this.salarioBruto = salarioBruto;
        this.descontoIR = descontoIR;
        this.descontoINSS = descontoINSS;
        this.descontoPrevidencia = descontoPrevidencia;
        this.descontoPlanoSaude = descontoPlanoSaude;
        this.totalDescontos = totalDescontos;
        this.salarioLiquido = salarioLiquido;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getSalarioLiquido() {
        return salarioLiquido = getSalarioBruto() - getTotalDescontos();
    }

    public double getDescontoIR() {
        return descontoIR;
    }

    public void setDescontoIR(double descontoIR) {
        this.descontoIR = descontoIR;
    }

    public double getDescontoINSS() {
        return descontoINSS;
    }

    public void setDescontoINSS(double descontoINSS) {
        this.descontoINSS = descontoINSS;
    }

    public double getDescontoPrevidencia() {
        return descontoPrevidencia;
    }

    public void setDescontoPrevidencia(double descontoPrevidencia) {
        this.descontoPrevidencia = descontoPrevidencia;
    }

    public double getDescontoPlanoSaude() {
        return descontoPlanoSaude;
    }

    public void setDescontoPlanoSaude(double descontoPlanoSaude) {
        this.descontoPlanoSaude = descontoPlanoSaude;
    }

    public double getTotalDescontos() {
        totalDescontos = getDescontoIR() + getDescontoINSS() + getDescontoPrevidencia() + getDescontoPlanoSaude();
        return totalDescontos;
    }

}
