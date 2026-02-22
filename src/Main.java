import entities.Player;
import util.Pause;
import util.Sorteio;
import util.FaceCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //---------------------------Listas--------------------------------------------
        List<Player> players = new ArrayList<>();
        ArrayList<Integer> baralho = Sorteio.criarBaralho(); // baralho
        ArrayList<Integer> playerCartas = new ArrayList<>(); // cartas que já saíram



        //------------------------Embaralhamento---------------------------------------
        Collections.shuffle(baralho);
        System.out.println("BARALHO EMBARALHADO\n");
        System.out.println("Total de cartas: " + baralho.size());
        System.out.println("Primeiras 10 cartas:\n");
        for (int i = 0; i < 10; i++) {
            System.out.print(baralho.get(i) + " ");
        }
        System.out.println("\n");

        //--------------------Nome do Jogador e quantidade de bots----------------------
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o seu nome: ");
        String nome = sc.nextLine();


        System.out.print("Digite a quantidade de bots (min. 1 e max. 6): ");

        int qntBots = Integer.parseInt(sc.nextLine());

        while (qntBots < 1 || qntBots > 6) {
            System.out.println("Valor inválido. Digite um número entre 1 e 6:");
            qntBots = Integer.parseInt(sc.nextLine());
        }












        //------------------Instanciação de objetos "player" na lista players-----------
        for (int i = 0; i <= qntBots; i++) { // limite será atualizado conforme a variavel de quantidade de jogadores
            if (i == 0) {
                players.add(new Player(nome));
                continue;
            }
            players.add(new Player("Bot " + i));
        }



        //-------------------------Distribuição de cartas iniciais-----------------------------
          System.out.println("=== DISTRIBUIÇÃO INICIAL ===");
        for (Player player : players) {
            System.out.print(player.getName() + " recebeu: ");
            for (int j = 0; j < 2; j++) {
                int carta = Sorteio.sortearCarta(baralho, playerCartas);
                if(carta <= 10){
                    player.addPoint(carta);
                    System.out.print(carta + " ");
                }else{
                    player.addPoint(10);
                    System.out.print(FaceCards.numberToFace(carta) + " ");
                }
            }
            System.out.println("→ Total: " + player.getPoints());
        }
        System.out.println();



        //-------------------------Lógica de compra do player--------------------------


        Player player = players.get(0);

        System.out.println("\n=== SUA VEZ "+ nome +"! ===");

        while (true) {
            System.out.println("Total atual: " + player.getPoints());
            System.out.println("Digite 'a' ou 'b': ");
            System.out.println("a - Comprar carta \nb - Parar");
            String opcao = sc.nextLine();

            if (opcao.equalsIgnoreCase("a")) {

                int carta = Sorteio.sortearCarta(baralho, playerCartas);

                if (carta <= 10) {
                    player.addPoint(carta);
                    System.out.println("Você comprou: " + carta +"\n");
                    Pause.pause(2000);
                } else {
                    player.addPoint(10);
                    System.out.println("Você comprou: " + FaceCards.numberToFace(carta));
                    Pause.pause(2000);
                }

            } else if (opcao.equalsIgnoreCase("b")) {
                System.out.println("  🙅‍♂️ Parou!\n");
                Pause.pause(2000);
                break;
            } else {
                System.out.println("Opção inválida.");
            }

            if (player.getPoints() == 21){
                System.out.println("  🎉 FEZ 21!\n");
                Pause.pause(2000);
                break;
            }
            else if (player.getPoints() > 21) {
                System.out.println("  💥 ESTOUROU!\n");
                Pause.pause(2000);
                break;
            }
        }





        //-------------------------Lógica de compra dos Bots---------------------------
        System.out.println("Turno dos BOTS");

        for (int i = 0; i <= qntBots; i++) {
            if (i == 0) {
                continue; // Pula o Player
            }


            Player bot = players.get(i);
            System.out.println("\n🤖 " + bot.getName() + " jogando... (Total atual: " + bot.getPoints() + ")");


            while (bot.getPoints() <= 17) {
                int cartaSorteada = Sorteio.sortearCarta(baralho, playerCartas);
                if(cartaSorteada <= 10){
                    bot.addPoint(cartaSorteada);
                    System.out.println(" Comprou " + cartaSorteada + " → Total: " + bot.getPoints());
                }else{
                    bot.addPoint(10);
                    System.out.println(" Comprou " + FaceCards.numberToFace(cartaSorteada) + " → Total: " + bot.getPoints());
                }
            }


            if(bot.getPoints() > 21){
                System.out.println("  💥 ESTOUROU!");
            } else if (bot.getPoints() == 21) {
                System.out.println("  🎉 FEZ 21!");
            } else {
                System.out.println("  🙅‍♂️ Parou!");
            }
        }



        //TODO------------------Lógica de Declaração de Vitórias--------------------







        sc.close();
    }
}


