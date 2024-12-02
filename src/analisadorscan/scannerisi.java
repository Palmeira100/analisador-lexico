package analisadorscan;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class scannerisi {

    private char[] content;
    private int position;
    private List<Token> tokens;  // Lista para armazenar os tokens
    private int identifierCount = 0;
    private int numberCount = 0;
    private int operatorCount = 0;
    private int parenthesisCount = 0;
    private int bracketCount = 0;

    public scannerisi(String filename) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            String txtContent = new String(bytes, StandardCharsets.UTF_8);
            content = txtContent.toCharArray(); 
            position = 0;
            tokens = new ArrayList<>();  // Inicializa a lista de tokens
        } catch (Exception ex) {
            ex.printStackTrace(); 
        }
    }

    public Token nextToken() {
        if (isEndOfFile()) return null; 

        char currentChar = nextChar(); 

        // Ignora espaços em branco
        while (isSpace(currentChar) && !isEndOfFile()) {
            currentChar = nextChar();
        }

        // Se o caractere for um identificador (letras)
        if (isChar(currentChar)) {
            Token token = createIdentifierToken(); 
            tokens.add(token);  // Armazena o token na lista
            identifierCount++;  // Incrementa o contador de identificadores
            return token;
        } 
        // Se o caractere for um número
        else if (isNumber(currentChar)) {
            Token token = createNumberToken(); 
            tokens.add(token);  // Armazena o token na lista
            numberCount++;  // Incrementa o contador de números
            return token;
        } 
        // Se o caractere for um operador
        else if (isOperator(currentChar)) {
            Token token = createOperatorToken(); 
            tokens.add(token);  // Armazena o token na lista
            operatorCount++;  // Incrementa o contador de operadores
            return token;
        }
        // Se o caractere for um parêntese de abertura ou fechamento
        else if (currentChar == '(') {
            Token token = createParenthesisToken("(");
            tokens.add(token);  // Armazena o token na lista
            parenthesisCount++;  // Incrementa o contador de parênteses
            return token;
        } else if (currentChar == ')') {
            Token token = createParenthesisToken(")");
            tokens.add(token);  // Armazena o token na lista
            parenthesisCount++;  // Incrementa o contador de parênteses
            return token;
        }
        // Se o caractere for um colchete de abertura ou fechamento
        else if (currentChar == '[') {
            Token token = createBracketToken("[");
            tokens.add(token);  // Armazena o token na lista
            bracketCount++;  // Incrementa o contador de colchetes
            return token;
        } else if (currentChar == ']') {
            Token token = createBracketToken("]");
            tokens.add(token);  // Armazena o token na lista
            bracketCount++;  // Incrementa o contador de colchetes
            return token;
        }
        
        else if (currentChar == '{') {
            Token token = createBracketToken("{");
            tokens.add(token);  // Armazena o token na lista
            bracketCount++;  // Incrementa o contador de colchetes
            return token;
        } else if (currentChar == '}') {
            Token token = createBracketToken("}");
            tokens.add(token);  // Armazena o token na lista
            bracketCount++;  // Incrementa o contador de colchetes
            return token;
        }
        else if (currentChar == ',') {
            Token token = createBracketToken(",");
            tokens.add(token);  // Armazena o token na lista
            bracketCount++;  // Incrementa o contador de colchetes
            return token;
        } 
        else if (currentChar == ';') {
            Token token = createBracketToken(";");
            tokens.add(token);  // Armazena o token na lista
            bracketCount++;  // Incrementa o contador de colchetes
            return token;
        } 
        else if (currentChar == '*') {
            Token token = createBracketToken("*");
            tokens.add(token);  // Armazena o token na lista
            bracketCount++;  // Incrementa o contador de colchetes
            return token;
        } 
        else if (currentChar == '.') {
            Token token = createBracketToken(".");
            tokens.add(token);  // Armazena o token na lista
            bracketCount++;  // Incrementa o contador de colchetes
            return token;
        } 

        
        // Se o caractere não for reconhecido
        throw new IllegalArgumentException("Caractere inválido encontrado: " + currentChar);
    }

    private Token createBracketToken(String value) {
        Token token = new Token();
        token.setType(Token.TK_BRACKET);  // Defina um tipo adequado para o colchete
        token.setValue(value);
        return token;
    }

    private Token createParenthesisToken(String value) {
        Token token = new Token();
        token.setType(Token.TK_PARENTHESIS);  // Defina um tipo adequado para o parêntese
        token.setValue(value);
        return token;
    }

    private Token createIdentifierToken() {
        StringBuilder builder = new StringBuilder();
        char currentChar = content[position - 1];
        while (isChar(currentChar) || isNumber(currentChar)) {
            builder.append(currentChar);
            currentChar = nextChar();
        }
        back(); 

        Token token = new Token();
        token.setType(Token.TK_IDENTIFIER); 
        token.setValue(builder.toString()); 
        return token;
    }

    private Token createNumberToken() {
        StringBuilder builder = new StringBuilder();
        char currentChar = content[position - 1];
        while (isNumber(currentChar)) {
            builder.append(currentChar);
            currentChar = nextChar();
        }
        back(); 

        Token token = new Token();
        token.setType(Token.TK_NUMBER); 
        token.setValue(builder.toString());
        return token;
    }

    private Token createOperatorToken() {
        char currentChar = content[position - 1];
        Token token = new Token();
        token.setType(Token.TK_OPERATOR); 
        token.setValue(String.valueOf(currentChar));
        return token;
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isOperator(char c) {
        return c == '>' || c == '<' || c == '=' || c == '!' || c == '+' || c == '-';
    }

    private boolean isSpace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private char nextChar() {
        return content[position++];
    }

    private void back() {
        position--;
    }

    private boolean isEndOfFile() {
        return position == content.length;
    }

    // Método para imprimir todos os tokens encontrados
    public void printTokens() {
        for (Token token : tokens) {
            System.out.println("Tipo: " + token.getType() + " Valor: " + token.getValue());
        }
    }

    // Método para imprimir a tabela de símbolos (ou o número de tokens de cada tipo)
    public void printSymbolTable() {
        System.out.println("Identificadores: " + identifierCount);
        System.out.println("Números: " + numberCount);
        System.out.println("Operadores: " + operatorCount);
        System.out.println("Parênteses: " + parenthesisCount);
        System.out.println("Colchetes: " + bracketCount);
    }

    public void close() {
        // TODO: Implementar o fechamento, se necessário
    }
}