package duda;

// Eduarda Vargas dos Santos - Prova GB
// Classe Terminal

import java.io.IOException;

public class Terminal {
    // Método para limpar terminal windows e linux
    public static void clearTerminal() throws InterruptedException, IOException {
        // Verifica o sistema operacional para limpar o terminal
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }

    // Método para exibir mensagem por determinado tempo, e após, limpar o terminal
    public static void printMessageForTime(String message, int time) {
        // Exibe mensagem
        System.out.println(message);
        try {
            // Timeout para limpar a tela
            Thread.sleep(time);
            // Limpa a tela
            Terminal.clearTerminal();
        } catch (Exception e) {
        }
    }

}