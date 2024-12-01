package analisadorscan;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class scannerisi {

    private char[] content;
    private int position;    

    public scannerisi(String filename) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            String txtContent = new String(bytes, StandardCharsets.UTF_8);
            content = txtContent.toCharArray(); 
            position = 0; 
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
            return createIdentifierToken(); 
        } else if (isNumber(currentChar)) {
            return createNumberToken(); 
        } else if (isOperator(currentChar)) {
            return createOperatorToken(); 
        }

        throw new IllegalArgumentException("Caractere inválido encontrado: " + currentChar);
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

	public void close() {
		// TODO Auto-generated method stub
		
	}   
	    
}

