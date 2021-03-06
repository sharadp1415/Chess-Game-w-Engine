package com.backend.chess_api.chess.pieces;

import java.util.HashSet;

import com.backend.chess_api.chess.Board;
import com.backend.chess_api.chess.Chess;
import com.backend.chess_api.chess.Move;
import com.backend.chess_api.chess.Square;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

public class Knight extends Piece {

    /**
     * 2-arg constructor for Knight class
     * 
     * @param isWhite If piece is white or black
     * @param square  Square piece resides on
     */
    public Knight(boolean isWhite, Square square) {
        super(isWhite, square);
        if (isWhite) {
            valueTable = new int[][] {
                    { -50, -40, -30, -30, -30, -30, -40, -50 },
                    { -40, -20, 0, 0, 0, 0, -20, -40 },
                    { -30, 0, 10, 15, 15, 10, 0, -30 },
                    { -30, 5, 15, 20, 20, 15, 5, -30 },
                    { -30, 0, 15, 20, 20, 15, 0, -30 },
                    { -30, 5, 10, 15, 15, 10, 5, -30 },
                    { -40, -20, 0, 5, 5, 0, -20, -40 },
                    { -50, -40, -30, -30, -30, -30, -40, -50 } };
        } else {
            valueTable = new int[][] {
                    { -50, -40, -30, -30, -30, -30, -40, -50 },
                    { -40, -20, 0, 5, 5, 0, -20, -40 },
                    { -30, 5, 10, 15, 15, 10, 5, -30 },
                    { -30, 0, 15, 20, 20, 15, 0, -30 },
                    { -30, 5, 15, 20, 20, 15, 5, -30 },
                    { -30, 0, 10, 15, 15, 10, 0, -30 },
                    { -40, -20, 0, 0, 0, 0, -20, -40 },
                    { -50, -40, -30, -30, -30, -30, -40, -50 } };
        }
    }

    public boolean isValidMove(Square start, Square end, Chess game) {
        Board b = game.board;

        if (Math.abs(start.rowpos - end.rowpos) * Math.abs(start.colpos - end.colpos) != 2) {
            return false;
        }

        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        // check if piece is pinned (perform the move and check if the king is in check
        // and revert back)
        boolean output;
        Piece piece = start.piece;
        // Piece capturedPiece = end.piece;
        // if (capturedPiece != null) {
        // if (capturedPiece.isWhite) {
        // b.whitePieces.remove(capturedPiece);
        // } else {
        // b.blackPieces.remove(capturedPiece);
        // }
        // }
        // start.piece = null;
        // end.piece = piece;
        // piece.square = end;
        // capturedPiece.square = null;
        Move potentialMove = new Move(start, end, piece.isWhite);
        game.performMove(potentialMove);
        if (b.inCheck(piece.isWhite)) {
            output = false;
        } else {
            output = true;
        }

        game.revertMove(potentialMove);

        // start.piece = piece;
        // piece.square = start;
        // end.piece = capturedPiece;
        // // capturedPiece.square = end;
        // if (capturedPiece != null) {
        // if (capturedPiece.isWhite) {
        // b.whitePieces.add(capturedPiece);
        // } else {
        // b.blackPieces.add(capturedPiece);
        // }
        // }

        return output;
    }

    public HashSet<Square> squaresBetween(Square start, Square end, Board b) {
        return new HashSet<>();
    }

    public int pieceValue(Chess game) {

        return 320 + valueTable[square.rowpos][square.colpos];
    }

    public String toString() {
        return isWhite ? "wN " : "bN ";
    }
}