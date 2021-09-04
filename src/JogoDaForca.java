package JogoDaForca;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Projeto1 de POO
 * Grupo de alunos: Diego Figueiredo, John Marques
 */

public class JogoDaForca {
    private int numeroPalavrasArquivo;  // Quantidade de palavras do arquivo (lido do arquivo)
    private int indice = 0;             // Posição (0 a numeroPalavrasArquivo-1) da palavra sorteada no array
    private int acertos = 0;            // Total de letras adivinhadas na palavra
    private int erros = 0;              // Total de tentativas erradas
    private String palavraSorteada;     // A palavra sorteada
    private String[] palavras;          // Um array com as numeroPalavrasArquivo palavras (lidas do arquivo)
    private String[] dicas;             // Um array com as numeroPalavrasArquivo dicas (lidas do arquivo)
    private String[] penalidades =      {"perna direita", "perna esqueda", "mão direita", "mão esquerda", "tronco", "cabeça"};
    private String[] letrasReveladas;   // Armazena um array com as letras reveladas
    private String[] letrasEscondidas;  // Armazena um array com as letras escondidas
    private String[] letrasDigitadas;   // Armazena um array com as letras digitadas pelo usuário

    public JogoDaForca(String nomearquivo) throws Exception {
        String linha;
        String[] linhaSep;
        File leitura;
        Scanner arquivo;

        try {
            leitura = new File(nomearquivo);
            arquivo = new Scanner(leitura);

            while (arquivo.hasNextLine()) {
                arquivo.nextLine();
                this.numeroPalavrasArquivo++;
            }
            arquivo.close();

            this.palavras = new String[this.numeroPalavrasArquivo];
            this.dicas = new String[this.numeroPalavrasArquivo];

            arquivo = new Scanner(leitura);

            for (int i = 0; i < numeroPalavrasArquivo; i++) {
                linha = arquivo.nextLine().toUpperCase();
                linhaSep = linha.split(";");
                this.palavras[i] = linhaSep[0];
                this.dicas[i] = linhaSep[1];
            }
            arquivo.close();

        } catch (FileNotFoundException e) {
            throw new Exception("Arquivo Inexistente");
        }
    }

    public void iniciar() {
        Random numeroAleatorio = new Random();
        this.indice = numeroAleatorio.nextInt(this.numeroPalavrasArquivo);
        this.palavraSorteada = this.palavras[this.indice];

        letrasReveladas = this.palavraSorteada.split("");
        letrasEscondidas = letrasReveladas.clone();
        for (int i = 0; i < letrasReveladas.length; i++) {
            letrasEscondidas[i] = "_";
        }
        letrasDigitadas = new String[penalidades.length];

    }

    public boolean adivinhou(String letra) throws Exception {
        letra = letra.toUpperCase();
        int contador = 0;

        if (Pattern.matches("[a-zA-Z]", letra)) {
            do {
                if (letrasDigitadas[contador] == null) {
                    letrasDigitadas[contador] = letra;
                    break;
                } else {
                    if (letrasDigitadas[contador].contains(letra)) {
                        throw new Exception("Você ja utilizou essa letra");
                    }
                }
                contador++;
            } while (contador < letrasDigitadas.length);

            if (this.palavraSorteada.contains(letra)) {
                for (int i = 0; i < this.letrasReveladas.length; i++) {
                    if (this.letrasReveladas[i].equals(letra)) {
                        this.letrasEscondidas[i] = this.letrasReveladas[i];
                        this.acertos++;
                    }
                }
            } else {
                this.erros++;
                return false;
            }
            return true;
        } else {
            throw new Exception("Por obséquio informe UMA LETRA, meu querido");
        }

    }

    public boolean terminou() {
        return this.erros == 6 || this.acertos == this.letrasReveladas.length;
    }

    public String getPalavra() {
        return String.join(" ", letrasEscondidas);
    }

    public String getDica() {
        return dicas[this.indice];
    }

    public String getPenalidade() {
        switch (this.erros) {
            case 1:
                return penalidades[0];
            case 2:
                return penalidades[1];
            case 3:
                return penalidades[2];
            case 4:
                return penalidades[3];
            case 5:
                return penalidades[4];
            case 6:
                return penalidades[5];
            default:
                return "";
        }
    }

    public int getAcertos() {
        return this.acertos;
    }

    public int getErros() {
        return this.erros;
    }

    public String getResultado() {
        if (this.acertos == this.letrasReveladas.length) {
            return "Venceu!";
        } else {
            return "Perdeu";
        }
    }

}
