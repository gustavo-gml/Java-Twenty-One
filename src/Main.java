import entities.Player;

import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args){

        //Listas
        List<Player> players = new ArrayList<>();

        //Instanciação de objetos "player" na lista players
        for(int i = 0; i < 4; i++) { //limite será atualizado conforme a variavel de quantidade de jogadores
            if (i == 0) {
                players.add(new Player("playerNamePlaceholder"));
                continue;
            }
            players.add(new Player("Bot " + i));
        }


        //Lógica de compra dos Bots
        for(int i = 0; i < 4; i++){ //limite será atualizado conforme a variavel de quantidade de jogadores
            if(i == 0){
                continue;
            }
            while(players.get(i).getPoints() <= 17){
                players.get(i).addPoint(1);//será modificado pela lógica do sorteio
            }


        }

        for(int i = 0; i < 4; i++){ //teste de funcionamento
            System.out.println(players.get(i));

        }

    }
}