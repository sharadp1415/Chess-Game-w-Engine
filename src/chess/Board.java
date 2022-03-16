package chess;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class Board {
    public Square[][] board;
    // might add HashSets for set of black pieces and set of white pieces

    public Board() {
        board = new Square[8][8];
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, null, count % 2 == 1 ? false : true);
                count++;
            }
            count--;
        }

        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            board[1][i].piece = new Pawn(false);

            board[6][i].piece = new Pawn(true);
        }

        board[0][0].piece = new Rook(false);
        board[0][7].piece = new Rook(false);
        board[0][1].piece = new Knight(false);
        board[0][6].piece = new Knight(false);
        board[0][2].piece = new Bishop(false);
        board[0][5].piece = new Bishop(false);
        board[0][3].piece = new Queen(false);
        board[0][4].piece = new King(false);

        board[7][0].piece = new Rook(true);
        board[7][7].piece = new Rook(true);
        board[7][1].piece = new Knight(true);
        board[7][6].piece = new Knight(true);
        board[7][2].piece = new Bishop(true);
        board[7][5].piece = new Bishop(true);
        board[7][3].piece = new Queen(true);
        board[7][4].piece = new King(true);
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].piece != null) {
                    System.out.print(board[i][j].piece);
                } else if (board[i][j].isLightSquare) {
                    System.out.print("   ");
                } else {
                    System.out.print("## ");
                }
            }
            System.out.println(8 - i);
        }

        System.out.print(" ");
        for (int i = 0; i < board.length; i++) {
            char letter = (char) (i + 97);
            System.out.print(letter + "  ");
        }
    }

    // check whether it is checkmate for either white or black (wip)
    public void isCheckmate(boolean isWhite) {
        if (isWhite) {

        } else {

        }
    }
}
