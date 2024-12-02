package analisadorscan;

public class Token {

	public static final int TK_IDENTIFIER = 0; 
	public static final int TK_NUMBER = 1; 
	public static final int TK_OPERATOR = 2; 
	public static final int TK_PONCTUATION = 3; 
	public static final int TK_RESERVED = 5;
	public static final int TK_STRING = 6;
	public static final int TK_DELIMITER = 7;
	public static final int TK_ARITHMETIC_OPERATOR = 8; 
	public static final int TK_COMPARISON_OPERATOR = 9; 
	public static final int TK_LOGICAL_OPERATOR = 10;   
	public static final int TK_ASSIGNMENT_OPERATOR = 11; 
	public static final int TK_INCREMENT_OPERATOR = 12;  
	public static final int TK_DECREMENT_OPERATOR = 13;
	 public static final int TK_ASSIGN = 14; 
	    public static final int TK_UNKNOWN = 15;
		public static final int TK_PARENTHESIS = 16;
		public static final int TK_BRACKET = 17; 

    private int type; 
    private String text;
	private Token[] tokens;
    
  
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


    public String getValue() {
		// TODO Auto-generated method stub
		return text;
   
    }
}