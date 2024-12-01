package analisadormain;

import analisadorscan.scannerisi;
import analisadorscan.Token;

public class mainclass {  // Nome da classe com a primeira letra maiúscula
    public static void main(String[] args) {
        scannerisi sc = null;  // Declara a variável fora do try para que possa ser acessada no finally
        try {
            // Inicializa o scanner com o arquivo de entrada
            sc = new scannerisi("input");  
            Token token;

            // Percorre os tokens até não haver mais tokens
            do {
                token = sc.nextToken();  // Obtém o próximo token
                if (token != null) {
                    // Aqui você pode fazer algo com o token, como imprimi-lo
                    System.out.println(token);
                }
            } while (token != null);  // Continua até não haver mais tokens
        } catch (Exception e) {
            // Trata exceções que possam ocorrer
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Bloco finalmente sempre é executado, independente de erro
            if (sc != null) {
                try {
                    sc.close();  // Supondo que ScannerIsi tenha um método close() para liberar recursos
                    System.out.println("Scanner fechado.");
                } catch (Exception e) {
                    System.err.println("Erro ao fechar o scanner: " + e.getMessage());
                }
            }
        }
    }
}