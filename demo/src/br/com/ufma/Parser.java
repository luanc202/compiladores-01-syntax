package br.com.ufma;

public class Parser {

    private final Scanner scan;

    private char currentToken;

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
    }

    private void nextToken () {
        currentToken = scan.nextToken();
    }

    private void match(char token) {
        if (currentToken == token) {
            nextToken();
        }else {
            throw new Error("syntax error: invalid token");
        }
    }

}
