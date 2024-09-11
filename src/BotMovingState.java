import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class BotMovingState implements GameState {
    private Stage stage;

    public BotMovingState(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void paint(Graphics g, Point mouseLoc) {
        for (Actor a : stage.getActors()) {
            if (!a.isHuman()) {
                List<Cell> possibleLocs = stage.getClearRadius(a.loc, a.moves);
                Cell nextLoc = a.strat.chooseNextLoc(possibleLocs);
                a.setLocation(nextLoc);
            }
        }
        stage.setCurrentState(new ChoosingActorState(stage));
        for (Actor a : stage.getActors()) {
            a.turns = 1;
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        // No action for mouse clicks in this state
    }
}