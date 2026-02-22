package util;

import java.util.ArrayList;
import java.util.Random;

public class Sorteio {
    
    // Função que retorna um número de 1 a 13
    public static int sortearCarta(ArrayList<Integer> baralho, ArrayList<Integer> cartasUsadas) {
        Integer pilha = baralho.getFirst();

        cartasUsadas.add(baralho.getFirst());
        baralho.remove(baralho.getFirst());
        
        return pilha;
    }
    
    // Cria baralho com números de 1 a 13 (4 de cada)
    public static ArrayList<Integer> criarBaralho() {

        ArrayList<Integer> baralho = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                baralho.add(i);
            }
        }
        return baralho;
    }

    public static void reiniciarBaralho(ArrayList<Integer> baralho, ArrayList<Integer> cartasUsadas) {
        baralho.addAll(cartasUsadas);
        cartasUsadas.clear();
    }
}