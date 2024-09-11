import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Optional;

public class SelectingNewLocationState implements GameState {
    private Stage stage;

    public SelectingNewLocationState(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void paint(Graphics g, Point mouseLoc) {
        // No specific painting for this state
    }

    @Override
    public void mouseClicked(int x, int y) {
        Optional<Cell> clicked = Optional.empty();
        for (Cell c : stage.getCellOverlay()) {
            if (c.contains(x, y)) {
                clicked = Optional.of(c);
            }
        }
        stage.setCellOverlay(new ArrayList<Cell>());
        if (clicked.isPresent() && stage.getActorInAction().isPresent()) {
            stage.getActorInAction().get().setLocation(clicked.get());
            stage.getActorInAction().get().turns--;
            int humansWithMovesLeft = 0;
            for (Actor a : stage.getActors()) {
                if (a.isHuman() && a.turns > 0) {
                    humansWithMovesLeft++;
                }
            }
            if (humansWithMovesLeft > 0) {
                stage.setCurrentState(new ChoosingActorState(stage));
            } else {
                stage.setCurrentState(new BotMovingState(stage));
            }
        }
    }
}