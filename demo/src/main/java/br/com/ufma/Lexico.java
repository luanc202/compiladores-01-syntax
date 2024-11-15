package br.com.ufma;

import java.io.File;

public class Lexico {
	
	public static void geracaoLexica(File file) {
		
		if (file == null || !file.exists()) {
			System.err.println("Arquivo invalido");
			return;
		}
		
		try {
			System.out.println("Análise léxica para o arquivo: " + file.getAbsolutePath());
		} catch(Exception e) {
			System.err.println("Erro:" + e.getMessage());
		}
		
	}	

}
