public class Layouts {

    public void layoutsTelaDeIniciar(){//Aqui é a visualização para orientar o usuário.
        System.out.println("\n-------------------------");
        System.out.println("Banco MA");
        System.out.println("-------------------------");
        System.out.println("1- Cadastrar Cliente");
        System.out.println("2- Criar Conta");
        System.out.println("3- Criar Conta Poupanca");
        System.out.println("4- Depositar");
        System.out.println("5- Sacar");
        System.out.println("6- Transferir da Conta para a Poupanca");
        System.out.println("7- Transferir da Poupanca para a Conta");
        System.out.println("8- Mostrar Saldo");
        System.out.println("9- Mostrar Saldo da Poupanca");
        System.out.println("10- Sair do programa");
        System.out.println("-------------------------");
        System.out.print("Digite a opcao desejada: \n");
    }
    public void layoutsDaConta(String nome, String CPF, String numeroDaConta, double saldoDaConta){
        System.out.println("-----------Conta-----------");
        System.out.println("\nNome do Cliente: " + nome);
        System.out.println("CPF do Cliente: " + CPF);
        System.out.println("Numero da Conta: " + numeroDaConta);
        System.out.println("Saldo: " + saldoDaConta);
        System.out.println("---------------------------");
    }
    public void layoutsDaPoupanca(String nome, String CPF, String numeroDaPoupanca, double saldoDaPoupanca){
        System.out.println("-----------Poupanca-----------");
        System.out.println("\nNome do Cliente: " + nome);
        System.out.println("CPF do Cliente: " + CPF);
        System.out.println("Numero da Conta: " + numeroDaPoupanca);
        System.out.println("Saldo: " + saldoDaPoupanca);
        System.out.println("------------------------------");
    }
}
