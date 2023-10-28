
public class InicializarPrograma {

    public static void main(String[] args) {
        BancoMA bancoMA = new BancoMA();
        boolean controlador = true;
        while (controlador) {
            controlador = bancoMA.inicializarBancoMA();
        }
    }
}