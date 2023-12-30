import java.util.Scanner;

public class BancoMA {
    public Boolean inicializarBancoMA() {
        boolean controlador = true, respostaDoInicializador = true;
        Scanner leitor = new Scanner(System.in);
        Layouts layouts = new Layouts();
        Cliente cliente = null;
        Poupanca poupanca = null;
        Conta conta = null;


        try { // Esse try catch é para o caso do usuário digitar algo diferente de números inteiros

            while (controlador) {
                int opcao;

                //imprime a tela inicial do Banco
                layouts.layoutsTelaDeIniciar();

                //recebe o valor digitado pelo usuario
                opcao = leitor.nextInt();

                switch (opcao) {
                    case 1:
                        // cadastramos um cliente com os dados pedidos, caso ocorra algum erro ele mostra uma mensagem de erro e reinicia o processo
                        Scanner leitorCliente = new Scanner(System.in);
                        System.out.print("\nDigite o nome do cliente: ");
                        String nomeCliente = leitorCliente.nextLine();

                        System.out.print("\nDigite o cpf do cliente: ");
                        String cpfCliente = leitorCliente.nextLine();

                        // verificamos se as variáveis foram deixadas em vazio ou como nulas
                        if (Validacoes.verificadorDeVazio(nomeCliente, cpfCliente)) {

                            // validamos se o CPF é existente
                            if (Validacoes.validarCPF(cpfCliente)) {

                                // criamos um cliente com os dados recebidos e mostramos uma mensagem após
                                cliente = new Cliente(nomeCliente, cpfCliente);

                                System.out.println("\nCliente cadastrado com sucesso!\n");

                            } else {
                                System.out.println("CPF Incorreto");
                            }
                        } else {
                            // caso ocorra algum erro as variáveis serão zeradas e retornará para a menu inicial
                            System.out.println("\nErro ao cadastrar cliente\n");
                            cliente = null;
                        }

                        break;
                    case 2:
                        // caso o usuario não tenha se tornado um cliente, o programa retornará à página inicial
                        if (cliente != null) {

                            // desenvolve o número da conta com MA mais os cinco primeiros números do seu CPF
                            String numeroConta = "MAC" + cliente.getCpf().substring(0, 6);

                            // criação da conta com o saldo incial de 0,0
                            conta = new Conta(numeroConta, cliente, 0.0);

                            // verificando se os dados foram passados corretamente caso false retorna uma mensagem de erro
                            if (conta.getNumeroConta().equals(numeroConta)) {
                                System.out.println("\nConta criada com sucesso\n");
                            } else {
                                System.out.println("\nErro ao criar conta\n");
                                conta = null;
                            }
                        } else {
                            System.out.println("\nPara criar uma conta e necessario primeiro ser cliente do Banco MA\n");
                        }

                        break;

                    case 3:
                        // caso o usuario não tenha criado uma conta, o programa retornará à página inicial
                        if (conta != null) {
                            try {
                                //cria o número da poupança com MA + os seis primeiros números do CPF do cliente
                                String numeroPoup = "MAP" + cliente.getCpf().substring(0, 6);

                                //cria uma conta poupança com os dados do cliente
                                poupanca = new Poupanca(numeroPoup, cliente, 0.0);

                                // verificando se os dados foram passados corretamente caso false retorna uma mensagem de erro
                                if (poupanca.getNumeroConta().equals(numeroPoup)) {
                                    System.out.println("\nConta poupanca criada com sucesso\n");
                                } else {
                                    System.out.println("\n“Erro ao criar conta poupanca\n");
                                }
                            }catch (Exception e){
                                System.out.println("\nErro ao criar a conta poupanca");
                            }
                        } else {
                            System.out.println("\nPara criar uma conta poupanca e necessario primeiro criar uma conta no Banco MA\n");
                        }
                        break;


                    case 4:
                        // caso o usuario não tenha criado uma conta, o programa retornará à página inicial
                        if (conta != null) {
                            Scanner leitordepositar = new Scanner(System.in);
                            System.out.print("\nInformar o valor do deposito: ");
                            double valordep = leitordepositar.nextDouble();
                            // Aqui salvamos o saldo anterior para que caso aconteça um erro na tranferencia o saldo irá continuar inalterado
                            double saldoanterior = conta.getSaldo();
                            conta.creditar(conta.getSaldo(), valordep);

                            if (conta.getSaldo() > saldoanterior) {
                                System.out.println("\nDeposito realizado com sucesso\n");
                            } else {
                                conta.resetSaldoConta(saldoanterior);
                                System.out.println("\nErro ao efetuar deposito\n");
                            }

                        } else {
                            System.out.println("\nE necessario criar uma conta para efetuar essa operacao!\n");
                        }

                        break;
                    case 5:
                        // caso o usuario não tenha criado uma conta, o programa retornará à página inicial
                        if (conta != null) {
                            Scanner leitorsaque = new Scanner(System.in);
                            System.out.print("\nInformar o valor do saque: ");
                            double valorsaque = leitorsaque.nextDouble();

                            // Aqui salvamos o saldo anterior para que caso aconteça um erro na tranferencia o saldo irá continuar inalterado
                            double saldoanterior = conta.getSaldo();
                            conta.debitar(conta.getSaldo(), valorsaque);

                            if (conta.getSaldo() < saldoanterior) {
                                System.out.println("\nSaque realizado com sucesso\n");
                            } else {
                                conta.resetSaldoConta(saldoanterior);
                                System.out.println("\nErro ao efetuar saque\n");
                            }

                        } else {
                            System.out.println("\nE necessario criar uma conta para efetuar essa operacao!\n");
                        }
                        break;

                    case 6:// Tranferência da conta para a conta poupança
                        // caso o usuario não tenha criado uma conta poupança, o programa retornará à página inicial
                        if (poupanca != null) {
                            Scanner leitortransf = new Scanner(System.in);
                            double saldoAnteriorCon = conta.getSaldo(), saldoAnteriorPou = poupanca.getSaldoPou();

                            try {
                                System.out.print("\nInformar o valor a ser tranferido : ");
                                double valortrans = leitortransf.nextDouble();

                                conta.debitar(conta.getSaldo(), valortrans);
                                poupanca.creditar(poupanca.getSaldo(), valortrans);

                                if (conta.saldo != saldoAnteriorCon && conta.saldo == (saldoAnteriorCon - valortrans) &&
                                        poupanca.saldo != saldoAnteriorPou && poupanca.saldo == (saldoAnteriorPou + (valortrans+(valortrans * Poupanca.TAXA)))) {
                                    System.out.println("\nTransferencia realizada com sucesso\n");
                                } else {
                                    poupanca.resetSaldoPoupanca(saldoAnteriorPou);
                                    conta.resetSaldoConta(saldoAnteriorCon);
                                    System.out.println("\nErro ao efetuar transferencia\n");
                                }
                            } catch (Exception e) {
                                poupanca.setSaldo(saldoAnteriorPou);
                                conta.setSaldo(saldoAnteriorCon);
                                System.out.println("\nErro ao efetuar transferencia\n");
                            }

                        } else {
                            System.out.println("\nE necessario criar uma conta poupanca para efetuar essa operacao!\n");
                        }

                        break;

                    case 7:// Tranferência da poupanca para a conta conta
                        // caso o usuario não tenha criado uma conta poupança, o programa retornará à página inicial
                        if (poupanca != null) {

                            Scanner leitortrnsf = new Scanner(System.in);
                            System.out.print("\nInformar o valor a ser transferido : ");
                            double valortrans = leitortrnsf.nextDouble();

                            double saldoAnteriorPoupanca = poupanca.getSaldo();
                            double saldoAnteriorCon = conta.saldo;

                            poupanca.debitar(poupanca.getSaldo(), valortrans);
                            conta.creditar(conta.getSaldo(), valortrans);

                            if (poupanca.getSaldoPou() > saldoAnteriorPoupanca || poupanca.getSaldoPou() < saldoAnteriorPoupanca) {
                                System.out.println("\nTransferencia realizada com sucesso\n");
                            } else {
                                poupanca.resetSaldoPoupanca(saldoAnteriorPoupanca);
                                conta.resetSaldoConta(saldoAnteriorCon);
                                System.out.println("\nErro ao efetuar transferencia\n");
                            }

                        } else {
                            System.out.println("\nE necessario criar uma conta poupanca para efetuar essa operacao!\n");
                        }

                        break;

                    case 8:
                        //Aqui será impresso na tela o saldo da conta
                        //caso não tenha sido criado a conta retornará uma mensagem
                        if (conta != null) {
                            layouts.layoutsDaConta(cliente.getNome(), cliente.getCpf(), conta.getNumeroConta(), conta.getSaldo());
                        } else {
                            System.out.println("\nE necessario criar uma conta para efetuar essa operacao!\n");
                        }
                        break;

                    case 9:
                        //Aqui será impresso na tela o saldo da conta poupança
                        //caso não tenha sido criado a conta poupança retornará uma mensagem
                        if (poupanca != null) {
                            layouts.layoutsDaPoupanca(cliente.getNome(), cliente.getCpf(), poupanca.getNumeroConta(), poupanca.getSaldo());
                        } else {
                            System.out.println("\nE necessario criar uma conta poupanca para efetuar essa operacao!\n");
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
        } catch (Exception e) {
            System.out.println("\nDigite somente Numeros");
        }
        return respostaDoInicializador;
    }
}
