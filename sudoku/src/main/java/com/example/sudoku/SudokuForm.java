package com.example.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.sudoku.entity.Tile;

public class SudokuForm {

    public SudokuForm() {
        this.tiles = new ArrayList<>();
    }

    private List<Tile> tiles;

    public List<Tile> getTiles() {
        return tiles;
    }

    public static boolean isValidSudoku(Tile[][] board) {
        // Check rows
        for (int row = 0; row < 9; row++) {
            if (!isValidRow(board, row)) {
                return false;
            }
        }

        // Check columns
        for (int col = 0; col < 9; col++) {
            if (!isValidColumn(board, col)) {
                return false;
            }
        }

        // Check 3x3 subgrids
        for (int startRow = 0; startRow < 9; startRow += 3) {
            for (int startCol = 0; startCol < 9; startCol += 3) {
                if (!isValidSubgrid(board, startRow, startCol)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValidRow(Tile[][] board, int row) {
        boolean[] used = new boolean[9];
        for (int col = 0; col < 9; col++) {
            String value = board[row][col].getValue();
            if (!value.equals(" ")) {
                int num = Integer.parseInt(value.trim());
                if (used[num - 1]) {
                    return false; // Number is already used in this row
                }
                used[num - 1] = true;
            }
        }
        return true;
    }

    private static boolean isValidColumn(Tile[][] board, int col) {
        boolean[] used = new boolean[9];
        for (int row = 0; row < 9; row++) {
            String value = board[row][col].getValue();
            if (!value.equals(" ")) {
                int num = Integer.parseInt(value.trim());
                if (used[num - 1]) {
                    return false; // Number is already used in this column
                }
                used[num - 1] = true;
            }
        }
        return true;
    }

    private static boolean isValidSubgrid(Tile[][] board, int startRow, int startCol) {
        boolean[] used = new boolean[9];
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                String value = board[row][col].getValue();
                if (!value.equals(" ")) {
                    int num = Integer.parseInt(value.trim());
                    if (used[num - 1]) {
                        return false; // Number is already used in this 3x3 subgrid
                    }
                    used[num - 1] = true;
                }
            }
        }
        return true;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }
}

