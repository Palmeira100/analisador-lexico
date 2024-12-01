package analisadorscan;

public class Token {

    public static final int TK_IDENTIFIER = 0; 
    public static final int TK_NUMBER = 1; 
    public static final int TK_OPERATOR = 2; 
    public static final int TK_PONCTUATION = 3; 
    public static final int TK_ASSIGN = 4; 
    public static final int TK_UNKNOWN = 5; 

    private int type; 
    private String text;
  
    public Token(int type, String text) { 
        this.type = type; 
        this.text = text; 
    }
    
    public Token() {
        this.type = TK_UNKNOWN; 
        this.text = ""; 
    }
    
    public int getType() { 
        return type;
    }
    
    public void setType(int type) { 
        this.type = type;
    }
    
    public String getText() { 
        return text;
    }
    
    public void setText(String text) { 
        this.text = text;
    }

    public boolean isIdentifier() {
        return this.type == TK_IDENTIFIER;
    }
    
    public boolean isNumber() {
        return this.type == TK_NUMBER;
    }

    public boolean isOperator() {
        return this.type == TK_OPERATOR;
    }

    public boolean isAssign() {
        return this.type == TK_ASSIGN;
    }
   
    public void setValue(String valueOf) {

    }
   
    }