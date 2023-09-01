package com.example.sudoku.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sudoku.SudokuForm;
import com.example.sudoku.entity.Tile;
import com.example.sudoku.repository.SudokuRepository;

@Controller
@RequestMapping("/sudoku")
public class SudokuController {

    @Autowired
    private SudokuRepository sudokuRepository;

    @GetMapping
    public String displaySudoku(Model model) {
        List<Tile> tiles = new ArrayList<>();
        sudokuRepository.findAll().forEach(tile -> tiles.add(tile));
        System.out.println(tiles.size());
        SudokuForm form = new SudokuForm();
        form.setTiles(tiles);
        model.addAttribute("sudokuForm", form);
        return "sudoku";
    }

    @PostMapping
    public void setSudoku(@ModelAttribute("sudokuForm") SudokuForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println ("binding problems");
        }
        Tile[][] newTiles = new Tile[9][9];
        System.out.println(form.getTiles().size());
        for(int row =0;row< 9; row++){
            for(int col=0;col<9;col++){
                newTiles[row][col] = form.getTiles().get(col+row*9);
            }
        }
        for (Tile tile : form.getTiles()) {
            System.out.println(String.format("Trying to save: %s %s", tile.getSudokuRow(), tile.getSudokuCol()));
                if(SudokuForm.isValidSudoku(newTiles))
                    sudokuRepository.save(tile);
                else
                {
                    if(tile.getValue().equals(" "))
                        sudokuRepository.save(tile);
                    else{
                        System.out.println("Wrong input");
                        break;
                    }
                }
        }

    }
    @GetMapping("/init")
    public String initializeSudoku(Model model) {
        // Create and store empty tiles in the sudoku matrix
        List<Tile> emptyTiles = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Tile emptyTile = new Tile();
                emptyTile.setValue(" ");
                emptyTile.setSudokuRow(row);
                emptyTile.setSudokuCol(col);
                emptyTiles.add(emptyTile);
            }
        }
        sudokuRepository.saveAll(emptyTiles); // Save empty tiles to the database
        return "redirect:/sudoku";
    }

}
