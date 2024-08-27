import java.util.List;

public interface MoveStrategy {
    Cell getNextMove(List<Cell> possibleLocs);
}