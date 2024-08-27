import java.util.List;
import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy {
    @Override
    public Cell getNextMove(List<Cell> possibleLocs) {
        int moveBotChooses = (new Random()).nextInt(possibleLocs.size());
        return possibleLocs.get(moveBotChooses);
    }
}