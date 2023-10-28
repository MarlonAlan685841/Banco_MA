public class Conta extends ContaAbstrata {
    public Conta(String numero, Cliente cliente, double saldo){
        super(numero, cliente, saldo);
    }

    public void creditar(double saldoCred) {
        this.saldo = saldo + saldoCred;
    }
    public void debitar(double saldoDebi){
        this.saldo = saldo - saldoDebi;
    }
}
