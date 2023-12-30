public abstract class ContaAbstrata {
    private String numeroConta;
    private Cliente cliente;
    double saldo;
    public ContaAbstrata(String numeroConta, Cliente cliente, double saldo){
        this.saldo = saldo;
        this.cliente = cliente;
        this.numeroConta = numeroConta;
    }

    public String getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract void creditar(double saldoEmConta, double saldoCred);
    public abstract void debitar(double saldoEmConta, double saldoDebi);
}
