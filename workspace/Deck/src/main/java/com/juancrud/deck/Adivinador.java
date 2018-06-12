package com.juancrud.deck;

import java.util.ArrayList;
import java.util.List;

public class Adivinador {
	private final List<Card> cartas;
	private int grupoAnterior;
	private boolean concluido;

	public Adivinador(List<Card> cartas) {
		this.cartas = cartas;
	}

	public List<Card> getCartas() {
		return cartas;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public List<List<Card>> distribuir() {
		int cantidadGrupos = 3;

		List<List<Card>> result = new ArrayList<List<Card>>();
		for (int i = 0; i < cantidadGrupos; i++) {
			result.add(new ArrayList<Card>());
		}

		for (int i = 0; i < cartas.size(); i++) {
			List<Card> subLista = result.get(i % cantidadGrupos);
			subLista.add(cartas.get(i));
		}

		return result;
	}

	public void redistribuir(int grupo) {
		List<List<Card>> subListas = distribuir();

		cartas.clear();
		if (grupo == 1) {
			cartas.addAll(subListas.get(2));
			cartas.addAll(subListas.get(0));
			cartas.addAll(subListas.get(1));
		} else if (grupo == 2) {
			cartas.addAll(subListas.get(0));
			cartas.addAll(subListas.get(1));
			cartas.addAll(subListas.get(2));
		} else {
			cartas.addAll(subListas.get(1));
			cartas.addAll(subListas.get(2));
			cartas.addAll(subListas.get(0));
		}

		concluido = grupo == 2 && grupoAnterior == 2;
		grupoAnterior = grupo;
	}

	public Card getRespuesta() {
		return isConcluido() ? cartas.get(10) : null;
	}
}
