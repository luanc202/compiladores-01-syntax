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

    public char nextToken () {
        char ch = peek();

        if (Character.isDigit(ch)) {
            advance();
            return ch;
        }

        return switch (ch) {
            case '+', '-' -> {
                advance();
                yield ch;
            }
            default -> '\0';
        };

    }

}