package com.exemplo.biblioteca;
public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConnection.getConnection();
            DatabaseConnection.createTables(); 
            System.out.println("Conexão bem-sucedida e estrutura do banco garantida!");
        } catch (Exception e) {
            System.err.println("Erro na inicialização: " + e.getMessage());
        }
    }
}



