package com.backend.chess_api.chess.pieces;

import java.util.HashSet;

import com.backend.chess_api.chess.Board;
import com.backend.chess_api.chess.Chess;
import com.backend.chess_api.chess.Square;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

public abstract class Piece {

    /**
     * Whether piece has been captured by another piece
     */
    public boolean isTaken;

    /**
     * Whether piece is white
     */
    public boolean isWhite;

    public boolean moved;
    /**
     * Square that piece resides on
     */
    public Square square;

    public int[][] valueTable;

    /**
     * 2-arg constructor for Piece
     * 
     * @param isWhite whether color of piece is white
     * @param square  square that piece is on
     */
    public Piece(boolean isWhite, Square square) {
        this.isTaken = false;
        this.moved = false;
        this.square = square;
        this.isWhite = isWhite;
        this.valueTable = new int[8][8];
    }

    /**
     * Default constructor for Piece
     */
    public Piece() {
        this(false, null);
    }

    /**
     * Checks if move made by user is valid according to rules of Piece being moved
     * 
     * @param start Starting square
     * @param end   Ending square
     * @param b     Board being played on
     * @return True if valid move, false otherwise
     */
    public abstract boolean isValidMove(Square start, Square end, Chess game);

    /**
     * Returns set of squares in between 2 specific squares
     * 
     * @param start Starting square
     * @param end   Ending square
     * @param b     Board being played on
     * @return HashSet of squares in between start and
     */
    public abstract HashSet<Square> squaresBetween(Square start, Square end, Board b);

    public abstract int pieceValue(Chess game);

    /**
     * Returns piece in String format
     * 
     * @return String representation of Piece
     */
    public abstract String toString();
}