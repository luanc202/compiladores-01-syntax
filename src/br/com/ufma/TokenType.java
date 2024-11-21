package br.com.ufma;

public enum TokenType {

    PLUS("+"),
    MINUS("-"),
    IDENT("\t"),
    NUMBER("0123456789"),
    EQUAL("="),
    BLANK(" "),
    NEWLINE("\n"),
    SEMICOLON(";"),
    LET("let"),
    EOF("\0");

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public char getChar() {
        return value.charAt(0);
    }
}