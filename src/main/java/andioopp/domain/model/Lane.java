package andioopp.domain.model;

import andioopp.domain.model.enemy.Enemy;

import java.util.Collection;
import java.util.List;

public class Lane {
    private final List<Cell> cells;

    Lane(List<Cell> cells) {
        this.cells = cells;
    }

    public int getNumberOfCells() {
        return getCells().size();
    }

    public Cell getCell(int col) {
        return getCells().get(col);
    }

    private List<Cell> getCells() {
        return cells;
    }
}
