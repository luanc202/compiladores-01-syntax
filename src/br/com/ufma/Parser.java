package br.com.ufma;

public class Parser {

    private final Scanner scanner;

    private Token currentToken;

    public Parser(byte[] input) {
        scanner = new Scanner(input);
        currentToken = scanner.nextToken();
    }

    private void nextToken() {
        currentToken = scanner.nextToken();
    }

    private void match(TokenType token) {
        if (currentToken.type == token) {
            nextToken();
        } else {
            throw new Error("syntax error: invalid token");
        }
    }

    void number() {
        System.out.println("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    void operator() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            number();
            System.out.println("add");
            operator();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            number();
            System.out.println("sub");
            operator();
        }
    }

    void expression() {
        number();
        operator();
    }

    public void parse() {
        expression();
    }
}
