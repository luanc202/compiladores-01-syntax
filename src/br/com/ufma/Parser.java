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
            term();
            System.out.println("add");
            operator();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            System.out.println("sub");
            operator();
        }
    }

    void expression() {
        term();
        operator();
    }

    public void parse() {
        letStatement();
    }

    private void term() {
        if (currentToken.type == TokenType.NUMBER) {
            number();
        } else if (currentToken.type == TokenType.IDENT) {
            System.out.println("push " + currentToken.lexeme);
            match(TokenType.IDENT);
        } else {
            throw new Error("syntax error: expected ident or number");
        }
    }

    void letStatement () {
        match(TokenType.LET);
        var id = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQUAL);
        expression();
        System.out.println("pop "+id);
        match(TokenType.SEMICOLON);
    }


}
