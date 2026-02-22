package util;

public class Pause {

    public static void pause(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
        }
    }
}
