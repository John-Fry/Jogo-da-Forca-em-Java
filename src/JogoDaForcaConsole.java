/*
 * IFPB-POO-PROJETO1
 * Aplicação console para testar a classe JogoDaForca
 * 
 */
import java.util.Arrays;
import java.util.Scanner;

public class JogoDaForcaConsole {
	public static void main(String[] args) {
//		JogoDaForca jogo = new JogoDaForca("jogo.txt");
//		jogo.iniciar();
//		System.out.println(jogo.getPalavras());
//		System.out.println(jogo.getDicas());
//		String letra;
//		Scanner teclado = new Scanner (System.in);
//		letra = teclado.nextLine();
//		jogo.adivinhou(letra);
//		System.out.println(jogo.getPalavras());

		JogoDaForca jogo = new JogoDaForca("palavras.txt");
		jogo.iniciar();
		String letra;
		Scanner teclado = new Scanner (System.in);
		do {
			System.out.println("palavra=" + jogo.getPalavras());
			System.out.println("dica=" + jogo.getDicas());

			System.out.println("digite uma letra da palavra ");
			letra = teclado.nextLine();

			if (jogo.adivinhou(letra)) {
				System.out.println("voce acertou a letra "+ letra);
				System.out.println("total de acertos="+jogo.getAcertos());
			}
			else {
				System.out.println("Voce errou a letra "+ letra);
				System.out.println("total de erros="+jogo.getErros());
				System.out.println("Penalidade: "+jogo.getPenalidade());
			}
		}
		while(!jogo.terminou());

		teclado.close();

		System.out.println("game over");
		System.out.println("resultado final="+jogo.getResultado() );
	}
}
