public abstract class ContaAbstrata {
    private String numero;
    private Cliente cliente;
    double saldo;
    public ContaAbstrata(String numero, Cliente cliente, double saldo){
        this.saldo = saldo;
        this.cliente = cliente;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
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

    public abstract void creditar(double saldoCred);
    public abstract void debitar(double saldoDebi);
}
