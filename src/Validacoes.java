public class Validacoes {
    public static boolean validarCPF(String cpf) {
        if (cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma1 += digito * (10 - i);
        }

        int resto1 = soma1 % 11;
        int digitoVerificador1 = (resto1 < 2) ? 0 : 11 - resto1;

        int soma2 = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma2 += digito * (11 - i);
        }

        soma2 += digitoVerificador1 * 2;
        int resto2 = soma2 % 11;
        int digitoVerificador2 = (resto2 < 2) ? 0 : 11 - resto2;

        int d1 = Character.getNumericValue(cpf.charAt(9));
        int d2 = Character.getNumericValue(cpf.charAt(10));

        return d1 == digitoVerificador1 && d2 == digitoVerificador2;
    }

    public static boolean verificarDeVazio(String nome, String cpf) {
        boolean resposta = false;
        if (nome != null && nome != "" && cpf != null && cpf != ""){
            resposta = true;
        }
        return resposta;
    }
}
