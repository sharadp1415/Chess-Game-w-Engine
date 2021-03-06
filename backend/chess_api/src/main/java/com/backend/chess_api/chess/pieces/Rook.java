package com.backend.chess_api.chess.pieces;

import java.util.HashSet;

import com.backend.chess_api.chess.Board;
import com.backend.chess_api.chess.Chess;
import com.backend.chess_api.chess.Square;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

public class Rook extends Piece {

    /**
     * 2-arg constructor for Rook class
     * 
     * @param isWhite If piece is white or black
     * @param square  Square piece resides on
     */
    public Rook(boolean isWhite, Square square) {
        super(isWhite, square);
        if (isWhite) {
            valueTable = new int[][] {
                    { 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 5, 10, 10, 10, 10, 10, 10, 5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { 0, 0, 0, 5, 5, 0, 0, 0 } };
        } else {
            valueTable = new int[][] {
                    { 0, 0, 0, 5, 5, 0, 0, 0 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { -5, 0, 0, 0, 0, 0, 0, -5 },
                    { 5, 10, 10, 10, 10, 10, 10, 5 },
                    { 0, 0, 0, 0, 0, 0, 0, 0 } };
        }
    }

    public boolean isValidMove(Square start, Square end, Chess game) {
        Board b = game.board;

        // check if piece moved at all
        if (start.equals(end))
            return false;

        // check if piece moved in straight line
        if ((start.rowpos - end.rowpos) * (start.colpos - end.colpos) != 0) {
            return false;
        }

        // check if move goes through pieces
        if (start.rowpos - end.rowpos == 0) {
            for (int i = 1; i < Math.abs(start.colpos - end.colpos); i++) {
                if (start.colpos < end.colpos) {
                    if (b.board[start.rowpos][start.colpos + i].piece != null)
                        return false;
                } else {
                    if (b.board[start.rowpos][start.colpos - i].piece != null)
                        return false;
                }
            }
        } else {
            for (int i = 1; i < Math.abs(start.rowpos - end.rowpos); i++) {
                if (start.rowpos < end.rowpos) {
                    if (b.board[start.rowpos + i][start.colpos].piece != null)
                        return false;
                } else {
                    if (b.board[start.rowpos - i][start.colpos].piece != null)
                        return false;
                }
            }
        }

        // check if piece lands on same color piece
        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        // check if piece is pinned (perform the move and check if the king is in check
        // and revert back)
        boolean output;
        Piece piece = start.piece;
        Piece capturedPiece = end.piece;
        if (capturedPiece != null) {
            if (capturedPiece.isWhite) {
                b.whitePieces.remove(capturedPiece);
            } else {
                b.blackPieces.remove(capturedPiece);
            }
        }
        start.piece = null;
        end.piece = piece;
        piece.square = end;
        // capturedPiece.square = null;
        if (b.inCheck(piece.isWhite)) {
            output = false;
        } else {
            output = true;
        }

        start.piece = piece;
        piece.square = start;
        end.piece = capturedPiece;
        // capturedPiece.square = end;
        if (capturedPiece != null) {
            if (capturedPiece.isWhite) {
                b.whitePieces.add(capturedPiece);
            } else {
                b.blackPieces.add(capturedPiece);
            }
        }

        return output;
    }

    public HashSet<Square> squaresBetween(Square start, Square end, Board b) {
        HashSet<Square> squares = new HashSet<>();

        if (start.rowpos - end.rowpos == 0) {
            for (int i = 1; i < Math.abs(start.colpos - end.colpos); i++) {
                if (start.colpos < end.colpos) {
                    squares.add(b.board[start.rowpos][start.colpos + i]);
                } else {
                    squares.add(b.board[start.rowpos][start.colpos - i]);
                }
            }
        } else {
            for (int i = 1; i < Math.abs(start.rowpos - end.rowpos); i++) {
                if (start.rowpos < end.rowpos) {
                    squares.add(b.board[start.rowpos + i][start.colpos]);
                } else {
                    squares.add(b.board[start.rowpos - i][start.colpos]);
                }
            }
        }

        return squares;
    }

    public int pieceValue(Chess game) {

        return 500 + valueTable[square.rowpos][square.colpos];
    }

    public String toString() {
        return isWhite ? "wR " : "bR ";
    }
}
