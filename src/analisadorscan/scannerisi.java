package analisadorscan;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class scannerisi {

    private char[] content;
    private int position;
    private List<Token> tokens;  
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
            tokens = new ArrayList<>();  
        } catch (Exception ex) {
            ex.printStackTrace(); 
        }
    }

    public Token nextToken() {
        if (isEndOfFile()) return null; 

        char currentChar = nextChar(); 

 
        while (isSpace(currentChar) && !isEndOfFile()) {
            currentChar = nextChar();
        }
        if (isChar(currentChar)) {
            Token token = createIdentifierToken(); 
            tokens.add(token);  
            identifierCount++; 
            return token;
        }
        else if (isNumber(currentChar)) {
            Token token = createNumberToken(); 
            tokens.add(token);  
            numberCount++;  
            return token;
        } 
        else if (isOperator(currentChar)) {
            Token token = createOperatorToken(); 
            tokens.add(token);  
            operatorCount++;  
            return token;
        }
    
        else if (currentChar == '(') {
            Token token = createParenthesisToken("(");
            tokens.add(token); 
            parenthesisCount++;  
            return token;
        } else if (currentChar == ')') {
            Token token = createParenthesisToken(")");
            tokens.add(token);  
            parenthesisCount++;  
            return token;
        }
       
        else if (currentChar == '[') {
            Token token = createBracketToken("[");
            tokens.add(token);  
            bracketCount++;  
            return token;
        } else if (currentChar == ']') {
            Token token = createBracketToken("]");
            tokens.add(token);  
            bracketCount++;  
            return token;
        }
        
        else if (currentChar == '{') {
            Token token = createBracketToken("{");
            tokens.add(token); 
            bracketCount++;  
            return token;
        } else if (currentChar == '}') {
            Token token = createBracketToken("}");
            tokens.add(token);  
            bracketCount++;  
            return token;
        }
        else if (currentChar == ',') {
            Token token = createBracketToken(",");
            tokens.add(token);  
            bracketCount++; 
            return token;
        } 
        else if (currentChar == ';') {
            Token token = createBracketToken(";");
            tokens.add(token);  
            bracketCount++;  
            return token;
        } 
        else if (currentChar == '*') {
            Token token = createBracketToken("*");
            tokens.add(token);  
            bracketCount++;  
            return token;
        } 
        else if (currentChar == '.') {
            Token token = createBracketToken(".");
            tokens.add(token); 
            bracketCount++;  
            return token;
        } 

        throw new IllegalArgumentException("Caractere inválido encontrado: " + currentChar);
    }

    private Token createBracketToken(String value) {
        Token token = new Token();
        token.setType(Token.TK_BRACKET);  
        token.setValue(value);
        return token;
    }

    private Token createParenthesisToken(String value) {
        Token token = new Token();
        token.setType(Token.TK_PARENTHESIS);  
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

    
    public void printTokens() {
        for (Token token : tokens) {
            System.out.println("Tipo: " + token.getType() + " Valor: " + token.getType());
        }
    }

   
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