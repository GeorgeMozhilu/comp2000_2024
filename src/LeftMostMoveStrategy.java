import java.util.List;

public class LeftMostMoveStrategy implements MoveStrategy {
    @Override
    public Cell getNextMove(List<Cell> possibleLocs) {
        Cell leftMost = possibleLocs.get(0);
        for (Cell c : possibleLocs) {
            if (c.col < leftMost.col) {
                leftMost = c;
            }
        }
        return leftMost;
    }
}