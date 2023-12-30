public class Poupanca extends ContaAbstrata {
    public static final double TAXA = 0.01;
    private double saldoPou;

    public Poupanca(String numero, Cliente cliente, double saldo) {
        super(numero, cliente, saldo);
    }

    public double getSaldoPou() {
        return saldo;
    }
    public void setSaldoPou(double saldoPou) {
        this.saldo = saldoPou;
    }

    public void creditar(double saldoEmConta, double saldoCredi) {
        this.saldo = saldoEmConta + (saldoCredi+(saldoCredi * TAXA));// (saldoCredi + (saldoCredi * TAXA));
    }
    public void debitar(double saldoEmConta, double saldoDebi){
        this.saldo = saldoEmConta - saldoDebi;
    }
    public void resetSaldoPoupanca(double saldoDaConta){
        this.saldo = saldoDaConta;
    }
}

