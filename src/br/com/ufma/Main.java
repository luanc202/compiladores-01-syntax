package br.com.ufma;


public class Main {

    public static void main(String[] args) {
        String input = "1+2-3+4";
        Parser p = new Parser (input.getBytes());
        p.parse();

    }

}