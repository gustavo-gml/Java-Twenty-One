package entities;


public class Player {
   //**Atributos
   private String name;
   private int points;
   private int roundsWon;
   private int matchesWon;

   //**Getters e Setters

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    //**Construtor

    public Player(String name){
        this.name = name;
        points = 0;
        roundsWon = 0;
        matchesWon = 0;
    }

    //**Métodos
   public void addPoint(int points){
       this.points += points;
   }
   public void resetPoints(){
       this.points = 0;
   }

    public void addRoundWon(){
        roundsWon++;
    }

    public void addMatchWon(){
        matchesWon++;
    }

    @Override
    public String toString() {
        return "player " +
                 name +
                ", points=" + points +
                ", roundsWon=" + roundsWon +
                ", matchesWon=" + matchesWon;
    }

    //serve para zerar a quantidade de rodadas vencidas por um jogador quando um jogador vence a partida, para que a contagem de rodadas seja reiniciada para a próxima partida.
    public void resetRoundsWon() { 
    this.roundsWon = 0;
}
}
