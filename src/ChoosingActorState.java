import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;

public class ChoosingActorState implements GameState {
    private Stage stage;

    public ChoosingActorState(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void paint(Graphics g, Point mouseLoc) {
        // No specific painting for this state
    }

    @Override
    public void mouseClicked(int x, int y) {
        stage.setActorInAction(Optional.empty());
        for (Actor a : stage.getActors()) {
            if (a.loc.contains(x, y) && a.isHuman()) {
                stage.setCellOverlay(stage.getGrid().getRadius(a.loc, a.moves));
                stage.setActorInAction(Optional.of(a));
                stage.setCurrentState(new SelectingNewLocationState(stage));
                break;
            }
        }
    }
}