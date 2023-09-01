package com.example.sudoku.repository;

// import org.springframework.data.jdbc.repository.query.Modifying;
// import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.transaction.annotation.Transactional;

import com.example.sudoku.entity.Tile;


public interface SudokuRepository extends CrudRepository<Tile,Integer>{

    // @Transactional
	// @Modifying
	// @Query(value="update Tile set value=?1,sudokuRow=?2,sudokuCol=?3 where id=?4")
	// void updateTileById(String value,int sRow,int sCol,Integer id);
}
