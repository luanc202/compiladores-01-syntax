package br.com.ufma;

import java.util.HashMap;
import java.util.Map;

public class Scanner {

    private final byte[] input;
    private int current;

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("let",    TokenType.LET);
    }

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

        if (isAlpha(ch)) {
            return identifier();
        }

        removeWhitespaces();

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
            case '=' -> {
                advance();
                yield new Token(TokenType.EQUAL, TokenType.EQUAL.getValue());
            }
            case ';' -> {
                advance();
                yield new Token(TokenType.SEMICOLON, TokenType.SEMICOLON.getValue());
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
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
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
        TokenType type = keywords.get(id);
        if (type == null) type = TokenType.IDENT;
        return new Token(type, id);
    }

}