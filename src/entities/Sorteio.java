package entities;

import java.util.ArrayList;
import java.util.Random;

public class Sorteio {
    
    // Função que retorna um número de 1 a 13
    public static int sortearCarta(ArrayList<Integer> baralho, ArrayList<Integer> cartasUsadas) {
        
        if (baralho.isEmpty()) {
            System.out.println("Reembaralhando as cartas...");
            baralho.addAll(cartasUsadas);
            cartasUsadas.clear();
            java.util.Collections.shuffle(baralho);
        }
        
        Random random = new Random();
        int indiceSorteado = random.nextInt(baralho.size());
        
        int cartaSorteada = baralho.get(indiceSorteado);
        baralho.remove(indiceSorteado);
        cartasUsadas.add(cartaSorteada);
        
        return cartaSorteada;
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
}