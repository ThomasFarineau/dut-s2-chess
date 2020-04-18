package fr.iut;

public class ChessMain {

    public String getHelloMessage() {
        return "Welcome to chest game.";
    }

    public static void main(String[] args) {
        final ChessMain chessMain = new ChessMain();
        System.out.println(chessMain.getHelloMessage());
        System.out.println("salut");
    }
}
