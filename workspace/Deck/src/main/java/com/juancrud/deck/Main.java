package com.juancrud.deck;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		
		Adivinador adivinador = new Adivinador(deck.getCards(21));
		Scanner scanner = new Scanner(System.in);

		// Paso 1
		System.out
				.println("Seleccione una carta de este grupo, memorizela y dele \"Enter\"");
		for (Card card : adivinador.getCartas()) {
			System.out.println(card);
		}
		scanner.nextLine();

		// Paso 2
		while (!adivinador.isConcluido()) {
			List<List<Card>> listas = adivinador.distribuir();
			for (int i = 0; i < listas.size(); i++) {
				System.out.printf("Grupo %s:\n", i + 1);
				List<Card> subLista = listas.get(i);
				for (int j = 0; j < subLista.size(); j++) {
					System.out.println(subLista.get(j));
				}
				System.out.println();
			}

			System.out.print("Digite el grupo en que esta su carta: ");
			int grupo = scanner.nextInt();
			adivinador.redistribuir(grupo);
		}

		// Paso 3
		Card carta = adivinador.getRespuesta();
		System.out.println("Su carta es: " + carta);

		scanner.close();
	}

}
