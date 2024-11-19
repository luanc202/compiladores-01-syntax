package br.com.ufma;

import java.io.File;

import javax.swing.JFileChooser;


public class Main {
    
    
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("digite o path do arquivo que voce deseja fazer analise lexica");
        
        JFileChooser fileCarrega = new JFileChooser();
        fileCarrega.setDialogTitle("qual o arquivo?");
        
        int userSelection = fileCarrega.showOpenDialog(null);
        
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileCarrega.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            System.out.println("Arquivo selecionado: " + path);
            
            Lexico.geracaoLexica(selectedFile);
            
        } else {
        	System.out.println("nao foi possivel selecionar o arquivo ");
        }
        
        
    }
}