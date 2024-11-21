package br.com.ufma;

public class Parser {

    private final Scanner scanner;

    private char currentToken;

    private String[] operatorsList = {"+", "-"};

    public Parser(byte[] input) {
        scanner = new Scanner(input);
        currentToken = scanner.nextToken();
    }

    private void nextToken () {
        currentToken = scanner.nextToken();
    }

    private void match(char token) {
        if (currentToken == token) {
            nextToken();
        }else {
            throw new Error("syntax error: invalid token");
        }
    }

    void digit () {
        if (Character.isDigit(currentToken)) {
            System.out.println("push " + currentToken);
            match(currentToken);
        } else {
            throw new Error("syntax error");
        }
    }

    void operator() {
        if (currentToken == TokenType.PLUS.getChar()) {
            match(TokenType.PLUS.getChar());
            digit();
            System.out.println("add");
            operator();
        } else if (currentToken == TokenType.MINUS.getChar()) {
            match(TokenType.MINUS.getChar());
            digit();
            System.out.println("sub");
            operator();
        }
    }

    void expression() {
        digit();
        operator();
    }

    public void parse () {
        expression();
    }
}
