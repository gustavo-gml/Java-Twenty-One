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



        //--------------------Nome do Jogador e quantidade de bots----------------------
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o seu nome: ");
        String nome = sc.nextLine();

        System.out.print("\nDigite a quantidade de bots (min. 1 e max. 6): ");

        int qntBots = Integer.parseInt(sc.nextLine());

        while (qntBots < 1 || qntBots > 6) {
            System.out.println("Valor inválido. Digite um número entre 1 e 6:");
            qntBots = Integer.parseInt(sc.nextLine());
        }



        //------------------Instanciação de objetos "player" na lista players-----------
        for (int i = 0; i <= qntBots; i++) {
            if (i == 0) {
                players.add(new Player(nome));
                continue;
            }
            players.add(new Player("Bot " + i));
        }

        boolean jogando = true;
        int rodada = 1;


           /* ===== LOOP DE RODADAS ===== */
        while (jogando) {

            // reset pontos
            for (Player p : players) {
                p.resetPoints();
            }
        //------------------------Embaralhamento---------------------------------------

            Sorteio.reiniciarBaralho(baralho, playerCartas);
            Collections.shuffle(baralho);
            System.out.println("\n===== RODADA " + rodada + " =====");
            System.out.println("BARALHO EMBARALHADO\n");
            Pause.pause(2000);

            /*System.out.println("Total de cartas: " + baralho.size()); -------------------testes
            System.out.println("Primeiras 10 cartas:\n");
            for (int i = 0; i < 10; i++) {
                System.out.print(baralho.get(i) + " ");
            }*/



        //-------------------------Distribuição de cartas iniciais-----------------------------
            System.out.println("=== DISTRIBUIÇÃO INICIAL ===");
            for (Player player : players) {
                System.out.print(player.getName() + " recebeu: ");
                for (int j = 0; j < 2; j++) {
                    int carta = Sorteio.sortearCarta(baralho, playerCartas);
                    if(carta <= 10){
                        player.addPoint(carta);

                        System.out.print(carta);

                    }else{
                        player.addPoint(10);
                        System.out.print(FaceCards.numberToFace(carta));
                    }
                    if(j == 0){
                        System.out.print(" e ");

                    }
                }
                System.out.println(" Total: " + player.getPoints());
            }
            System.out.println();
            System.out.println("Pressione a tecla 'Enter' para continuar");
            sc.nextLine();
            sc.reset();



        //-------------------------Lógica de compra do player--------------------------


            Player player = players.get(0);

            System.out.println("\n=== SUA VEZ "+ nome +"! ===");

            while (true) {
                System.out.println("Total atual: " + player.getPoints());
                System.out.println("Digite 'S' ou 'N': ");
                System.out.println("S - Comprar carta \nN - Parar");
                String opcao = sc.nextLine();

                if (opcao.equalsIgnoreCase("S")) {

                    int carta = Sorteio.sortearCarta(baralho, playerCartas);

                    if (carta <= 10) {
                        player.addPoint(carta);
                        System.out.println("Você comprou: " + carta +"\n");
                        Pause.pause(1500); //qisso
                    } else {
                        player.addPoint(10);
                        System.out.println("Você comprou: " + FaceCards.numberToFace(carta));
                        Pause.pause(1500);
                    }

                } else if (opcao.equalsIgnoreCase("N")) {
                    System.out.println("  Parou!\n");
                    Pause.pause(2000);
                    break;
                } else {
                    System.out.println("Opção inválida.");
                }

                if (player.getPoints() == 21){
                    System.out.println("  FEZ 21! :)\n");
                    Pause.pause(2000);
                    break;
                }
                else if (player.getPoints() > 21) {
                    System.out.println(" ESTOUROU! :(\n");
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
                System.out.println("\n" + bot.getName() + " jogando... (Total atual: " + bot.getPoints() + ")");


                while (bot.getPoints() <= 17) {
                    int cartaSorteada = Sorteio.sortearCarta(baralho, playerCartas);
                    if(cartaSorteada <= 10){
                        bot.addPoint(cartaSorteada);
                        System.out.println(" Comprou " + cartaSorteada + " \n  Total: " + bot.getPoints());
                    }else{
                        bot.addPoint(10);
                        System.out.println(" Comprou " + FaceCards.numberToFace(cartaSorteada) + "  \nTotal: " + bot.getPoints());
                    }
                }


                if(bot.getPoints() > 21){
                    System.out.println("  ESTOUROU! :(");
                } else if (bot.getPoints() == 21) {
                    System.out.println("  FEZ 21! :)");
                } else {
                    System.out.println("  Parou!");
                }
            }



        //------------------Lógica de Declaração de Vitórias--------------------
            Player winner = null;
            System.out.println("\n=== RESULTADO DA RODADA ===");

            int maiorPontuacao = 0;
            Player vencedorRodada = null;
            boolean empate = false;

            for (Player p : players) {

                if (p.getPoints() <= 21) {

                    if (p.getPoints() > maiorPontuacao) {
                        maiorPontuacao = p.getPoints();
                        vencedorRodada = p;
                        empate = false;

                    } else if (p.getPoints() == maiorPontuacao) {
                        empate = true;
                    }
                }
            }

            if (vencedorRodada == null) {
                System.out.println("Todos estouraram. Sem vencedor.");

            } else if (empate) {
                System.out.println("Empate na rodada.");

            } else {
                vencedorRodada.addRoundWon();
                System.out.println(vencedorRodada.getName() + " venceu a rodada!");
                if(vencedorRodada.getRoundsWon() == 3){
                    winner = vencedorRodada;
                }
            }
            Pause.pause(2000);

            System.out.println("\n=== PONTUAÇÃO DA RODADA ===");

            for (Player p : players) {
                System.out.println(
                    p.getName() +
                    " | Rodadas vencidas: " + p.getRoundsWon()
                );
            }

            System.out.println("\nPressione a tecla 'Enter' para continuar");
            sc.nextLine();
            sc.reset();
            System.out.println("\n\n\n\n\n");

        /* -------- Verificar vitória da PARTIDA -------- */



            if (winner != null) {

            winner.addMatchWon();
            System.out.println("\n" + winner.getName() + " GANHOU A PARTIDA!");

            System.out.print("Deseja jogar outra partida? (S/N): ");
            String resp = sc.nextLine();

            if (resp.equalsIgnoreCase("N")) {
                jogando = false;
                sc.close();
            } else {
                // reset para nova partida
                for (Player r : players) {
                    r.resetRoundsWon();
                }
                rodada = 1;
                continue;
            }


            }

            Pause.pause(2000);
        rodada++;

        }
    }
}

