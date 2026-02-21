import entities.Player;
import entities.Sorteio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Listas
        List<Player> players = new ArrayList<>();

        ArrayList<Integer> baralho = Sorteio.criarBaralho(); // baralho

        ArrayList<Integer> playerCartas = new ArrayList<>(); // cartas que já saíram


        //Embaralhamento
        Collections.shuffle(baralho);
        System.out.println("BARALHO EMBARALHADO\n");
        System.out.println("Total de cartas: " + baralho.size());
        System.out.println("Primeiras 10 cartas:\n");
        for (int i = 0; i < 10; i++) {
            System.out.print(baralho.get(i) + " ");
        }
        System.out.println("\n");

        

        // Instanciação de objetos "player" na lista players
        for (int i = 0; i < 4; i++) { // limite será atualizado conforme a variavel de quantidade de jogadores
            if (i == 0) {
                players.add(new Player("playerNamePlaceholder"));
                continue;
            }
            players.add(new Player("Bot " + i));
        }

        //Distribuição de compra dos bots
          System.out.println("=== DISTRIBUIÇÃO INICIAL ===");
        for (Player player : players) {
            System.out.print(player.getName() + " recebeu: ");
            for (int j = 0; j < 2; j++) {
                int carta = Sorteio.sortearCarta(baralho, playerCartas);
                player.addPoint(carta);
                System.out.print(carta + " ");
            }
            System.out.println("→ Total: " + player.getPoints());
        }
        System.out.println();

        // Lógica de compra dos Bots
        System.out.println("Turno dos BOTS");
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                continue; // Pula o Player
            }


            Player bot = players.get(i);
            System.out.println("\n🤖 " + bot.getName() + " jogando... (Total atual: " + bot.getPoints() + ")");

            while (bot.getPoints() <= 17) {
                // USA O SORTEIO REAL (não é mais addPoint(1))
                int cartaSorteada = Sorteio.sortearCarta(baralho, playerCartas);
                bot.addPoint(cartaSorteada);
                
                System.out.println(" Comprou " + cartaSorteada + " → Total: " + bot.getPoints());

        

            if (bot.getPoints() > 21) {
                    System.out.println("  💥 ESTOUROU!");
                    break;
                } else if (bot.getPoints() == 21) {
                    System.out.println("  🎉 FEZ 21!");
                    break;
                }
            }
        }
    

        for (int i = 0; i < 4; i++) { // teste de funcionamento
            System.out.println(players.get(i));

        }

    
        }}


