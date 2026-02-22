package util;

public class FaceCards {
    public static String numberToFace(int number){
        switch (number){
            case 11:
                return "J";

            case 12:
                return "Q";

            case 13:
                return "K";

            default:
                return "Invalid number";
        }

    }
}
