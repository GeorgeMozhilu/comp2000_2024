import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  List<Actor> actors;
  List<Cell> cellOverlay;
  Optional<Actor> actorInAction;
  GameState currentState;

  public Stage() {
    grid = new Grid();
    actors = new ArrayList<Actor>();
    cellOverlay = new ArrayList<Cell>();
    actorInAction = Optional.empty();
    currentState = new ChoosingActorState(this);
  }

  public void paint(Graphics g, Point mouseLoc) {
    currentState.paint(g, mouseLoc);

    grid.paint(g, mouseLoc);
    grid.paintOverlay(g, cellOverlay, new Color(0f, 0f, 1f, 0.5f));
    for (Actor a : actors) {
      a.paint(g);
    }

    // Rest of the painting code (information area, etc.) remains the same
  }

  public void mouseClicked(int x, int y) {
    currentState.mouseClicked(x, y);
  }

  public List<Cell> getClearRadius(Cell from, int size) {
    List<Cell> init = grid.getRadius(from, size);
    for (Actor a : actors) {
      init.remove(a.loc);
    }
    return init;
  }

  // Getters and setters
  public Grid getGrid() {
    return grid;
  }

  public List<Actor> getActors() {
    return actors;
  }

  public List<Cell> getCellOverlay() {
    return cellOverlay;
  }

  public Optional<Actor> getActorInAction() {
    return actorInAction;
  }

  public void setActorInAction(Optional<Actor> actor) {
    this.actorInAction = actor;
  }

  public void setCellOverlay(List<Cell> overlay) {
    this.cellOverlay = overlay;
  }

  public void setCurrentState(GameState state) {
    this.currentState = state;
  }
}