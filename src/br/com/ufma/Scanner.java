package br.com.ufma;

public class Scanner {

    private final byte[] input;
    private int current;

    public Scanner (byte[] input) {
        this.input = input;
    }

    private char peek () {
        if (current < input.length)
            return (char)input[current];
        return '\0';
    }

    private void advance()  {
        char ch = peek();
        if (ch != '\0') {
            current++;
        }
    }

    public Token nextToken () {
        char ch = peek();

        removeWhitespaces();

        if (isAlpha(ch)) {
            return identifier();
        }

        if (ch == '0') {
            advance();
            return new Token(TokenType.NUMBER, Character.toString(ch));
        }  else if (Character.isDigit(ch))
            return number();

        return switch (ch) {
            case '+' -> {
                advance();
                yield new Token(TokenType.PLUS, TokenType.PLUS.getValue());
            }
            case '-' -> {
                advance();
                yield new Token(TokenType.MINUS, TokenType.MINUS.getValue());
            }
            case '\0' -> {
                advance();
                yield new Token(TokenType.EOF, TokenType.EOF.getValue());
            }
            default -> throw new Error("lexical error: no op token found when expected");
        };

    }

    private Token number() {
        int start = current ;
        while (Character.isDigit(peek())) {
            advance();
        }

        String n = new String(input, start, current-start)  ;
        return new Token(TokenType.NUMBER, n);
    }

    private void removeWhitespaces() {
        String ch = Character.toString(peek());
        while (ch.equals(" ") || ch.equals("\r") || ch.equals("\t") || ch.equals("\n")) {
            advance();
            ch = Character.toString(peek());
        }
    }

    private Boolean isAlpha(char c) {
        return (c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || c == '_';
    }

    private Boolean isAlphanumeric(char c) {
        return isAlpha(c) || Character.isDigit(c);
    }

    private Token identifier() {
        int start = current;

        while (isAlphanumeric(peek())) {
            advance();
        }

        String id = new String(input, start, current - start);
        return new Token(TokenType.IDENT, id);
    }

}