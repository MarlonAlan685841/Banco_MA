public class Poupanca extends ContaAbstrata {
    public static final double TAXA = 0.01;
    private double saldoPou;

    public Poupanca(String numero, Cliente cliente, double saldo, double saldoPou) {
        super(numero, cliente, saldo);
        this.saldoPou = saldoPou;
    }

    public double getSaldoPou() {
        return saldoPou;
    }
    public void setSaldoPou(double saldoPou) {
        this.saldoPou = saldoPou;
    }

    public void creditar(double saldoCredi) {
        this.saldoPou = saldoPou + saldoCredi;// (saldoCredi + (saldoCredi * TAXA));
    }
    public void debitar(double saldoDebi){
        this.saldoPou = saldoPou - saldoDebi;
    }
    public void resetSaldoPoupanca(double saldoDaConta){
        this.saldo = saldoDaConta;
    }
}

