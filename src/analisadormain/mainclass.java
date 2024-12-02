package analisadormain;

import analisadorscan.scannerisi;
import analisadorscan.Token;

public class mainclass {  
    public static void main(String[] args) {
        scannerisi sc = null; 
        try {
          
            sc = new scannerisi("input");  
            Token token;

            
            do {
                token = sc.nextToken();  
                if (token != null) {
                  
                    System.out.println(token);
                }
            } while (token != null);  
            
            System.out.println("\n--- TOKENS ---");
            sc.printTokens();

            System.out.println("\n---  SÍMBOLOS ---");
            sc.printSymbolTable();
        } 
        
        
        catch (Exception e) {
            
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
           
            if (sc != null) {
                try {
                    sc.close(); 
                    System.out.println("Scanner fechado.");
                } catch (Exception e) {
                    System.err.println("Erro ao fechar o scanner: " + e.getMessage());
                }
            }
        }
    }
}