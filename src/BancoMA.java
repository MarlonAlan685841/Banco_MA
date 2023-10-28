import java.util.Scanner;

public class BancoMA {
    public Boolean inicializarBancoMA(){
        boolean controlador = true, respostaDoInicializador = true;
        Scanner leitor = new Scanner(System.in);
        Layouts layouts = new Layouts();
        Cliente cliente = null;
        Poupanca poupanca = null;
        Conta conta = null;

        try {
            while (controlador) {
                int opcao;
                //imprime a tela inicial do Banco
                layouts.layoutsTelaDeIniciar();

                //recebe o valor digitado pelo usuário
                opcao = leitor.nextInt();


                switch (opcao) {
                    case 1:
                        Scanner leitorCliente = new Scanner(System.in);
                        System.out.print("\nDigite o nome do cliente: ");
                        String nomeCliente = leitorCliente.nextLine();

                        System.out.print("\nDigite o cpf do cliente: ");
                        String cpfCliente = leitorCliente.nextLine();

                        if (!nomeCliente.equals(null) && !nomeCliente.equals("") && !cpfCliente.equals(null) && !cpfCliente.equals("")) {

                            if (Validacoes.validarCPF(cpfCliente)) {

                                cliente = new Cliente(nomeCliente, cpfCliente);

                                System.out.println("\nCliente cadastrado com sucesso!\n");

                            } else {
                                System.out.println("CPF Incorreto");
                            }
                        } else {
                            System.out.println("\nErro ao cadastrar cliente\n");
                            cliente = null;
                        }

                        break;
                    case 2:
                        if (cliente != null) {

                            String numeroConta = "MA" + cliente.getCpf().substring(0, 5);
                            conta = new Conta(numeroConta, cliente, 0.0);
                            if (conta.getNumero().equals(numeroConta)) {
                                System.out.println("\nConta criada com sucesso\n");
                            } else {
                                System.out.println("\nErro ao criar conta\n");
                            }
                        } else {
                            System.out.println("\nPara criar uma conta e necessario primeiro ser cliente do Banco MA\n");
                        }

                        break;

                    case 3:
                        if (conta != null) {

                            String numeroPoup = "MA" + cliente.getCpf().substring(0, 5);
                            poupanca = new Poupanca(numeroPoup, cliente, 0.0, 0.0);
                            if (poupanca.getNumero().equals(numeroPoup)) {
                                System.out.println("\nConta poupanca criada com sucesso\n");
                            } else {
                                System.out.println("\n“Erro ao criar conta poupanca\n");
                            }
                        } else {
                            System.out.println("\nPara criar uma conta poupanca e necessario primeiro criar uma conta no Banco MA\n");
                        }
                        break;


                    case 4:
                        if (conta != null) {

                            Scanner leitordepositar = new Scanner(System.in);
                            System.out.print("\nInformar o valor do deposito: ");
                            double valordep = leitordepositar.nextDouble();

                            double saldoanterior = conta.getSaldo();
                            conta.creditar(valordep);

                            if (conta.getSaldo() > saldoanterior) {
                                System.out.println("\nDeposito realizado com sucesso\n");
                            } else {
                                System.out.println("\nErro ao efetuar deposito\n");
                            }

                        } else {
                            System.out.println("\nE necessario criar uma conta para efetuar essa operacao!\n");
                        }

                        break;
                    case 5:
                        if (conta != null) {

                            Scanner leitorsaque = new Scanner(System.in);
                            System.out.print("\nInformar o valor do saque: ");
                            double valorsaque = leitorsaque.nextDouble();

                            double saldoanterior = conta.getSaldo();
                            conta.debitar(valorsaque);

                            if (conta.getSaldo() < saldoanterior) {
                                System.out.println("\nSaque realizado com sucesso\n");
                            } else {
                                System.out.println("\nErro ao efetuar saque\n");
                            }

                        } else {
                            System.out.println("\nE necessario criar uma conta para efetuar essa operacao!\n");
                        }
                        break;

                    case 6:
                        if (poupanca != null) {

                            Scanner leitortrnsf = new Scanner(System.in);
                            System.out.print("\nInformar o valor a ser tranferido : ");
                            double valortrans = leitortrnsf.nextDouble();

                            double saldoanterior = poupanca.getSaldoPou();

                            poupanca.creditar(valortrans);

                            conta.debitar(valortrans);


                            if (conta.saldo > saldoanterior || conta.saldo < saldoanterior) {
                                System.out.println("\nTransferencia realizada com sucesso\n");
                            } else {
                                System.out.println("\nErro ao efetuar transferencia\n");
                            }

                        } else {
                            System.out.println("\nE necessario criar uma conta poupanca para efetuar essa operacao!\n");
                        }

                        break;


                    case 7:

                        if (poupanca != null) {

                            Scanner leitortrnsf = new Scanner(System.in);
                            System.out.print("\nInformar o valor a ser transferido : ");
                            double valortrans = leitortrnsf.nextDouble();

                            double saldoanterior = poupanca.getSaldo();

                            conta.creditar(valortrans);

                            poupanca.debitar(valortrans);


                            if (poupanca.getSaldoPou() > saldoanterior || poupanca.getSaldoPou() < saldoanterior) {
                                System.out.println("\nTransferencia realizada com sucesso\n");
                            } else {
                                System.out.println("\nErro ao efetuar transferencia\n");
                            }

                        } else {
                            System.out.println("\nE necessario criar uma conta poupanca para efetuar essa operacao!\n");
                        }

                        break;

                    case 8:
                        if (conta != null) {
                            layouts.layoutsDaConta(cliente.getNome(), cliente.getCpf(), conta.getNumero(), conta.getSaldo());
                        } else {
                            System.out.println("\nE necessario criar uma conta para efetuar essa operacao!\n");
                        }
                        break;

                    case 9:
                        if (poupanca != null) {
                            layouts.layoutsDaPoupanca(cliente.getNome(), cliente.getCpf(), poupanca.getNumero(), poupanca.getSaldo());
                        } else {
                            System.out.println("\nE necessario criar uma conta para efetuar essa operacao!\n");
                        }
                        break;

                    case 10:
                        controlador = false;
                        respostaDoInicializador = false;
                        System.out.println("\nObrigado por utilizar o Banco MA\n");
                        break;
                    default:
                        System.out.println("\nOpcao Invalida!\n");

                }
            }
        }catch (Exception e){
            System.out.println("\nDigite somente Numeros");
        }
        return respostaDoInicializador;
    }
}
