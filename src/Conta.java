public class Conta extends ContaAbstrata {
    public Conta(String numero, Cliente cliente, double saldo){
        super(numero, cliente, saldo);
    }

    public void creditar(double saldoEmConta, double saldoCred) {
        this.saldo = saldoEmConta + saldoCred;
    }
    public void debitar(double saldoEmConta, double saldoDebi){
        this.saldo = saldoEmConta - saldoDebi;
    }
    public void resetSaldoConta(double saldoDaConta){
        this.saldo = saldoDaConta;
    }
}
