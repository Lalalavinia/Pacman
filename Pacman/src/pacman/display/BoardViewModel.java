package pacman.display;

import javafx.util.Pair;
import pacman.board.PacmanBoard;
import pacman.game.PacmanGame;
import pacman.ghost.Ghost;
import pacman.ghost.Phase;
import pacman.util.Position;
import java.util.ArrayList;
import java.util.List;


public class BoardViewModel {
    // a new BoardViewModel to model the given PacmanGame.
    private PacmanGame model;

    /**
     * Constructs a new BoardViewModel to model the given PacmanGame.
     * @param model the game to be played
     */
    public BoardViewModel(PacmanGame model){
        this.model = model;
    }

    /**
     * Returns the number of lives left for the player in the game.
     * @return the number of lives
     */
    public int getLives(){
        return model.getLives();

    }

    /**
     * Returns the current level that the player is on.
     * @return the current level of the game
     */
    public int getLevel(){
        return model.getLevel();
    }

    /**
     * Returns a colour string to represent how the hunter should be displayed.
     * If game's hunter special is active it should return "#CDC3FF", otherwise return "#FFE709".
     * @return the colour associated with the game's hunter
     */
    public String getPacmanColour(){
        if (model.getHunter().isSpecialActive()){
            return "#CDC3FF";
        }
        else {
            return "#FFE709";
        }

    }

    /**
     * Returns the starting angle of the mouth arc of the pacman depending on its direction. If the hunter's direction is RIGHT, return 30.
     * If the hunter's direction if UP, return 120.
     * If the hunter's direction is LEFT, return 210.
     * If the hunter's direction is DOWN, return 300.
     * @return the angle based on the direction of the game's hunter
     */
    public int getPacmanMouthAngle(){
        switch (model.getHunter().getDirection()){
            case RIGHT:
                return 30;
            case UP:
                return 120;
            case LEFT:
                return 210;
            case DOWN:
                return 300;
            default:
                return 0;
        }
    }

    /**
     * Returns the current position of the game's hunter.
     * @return the position of the hunter
     */
    public Position getPacmanPosition(){
        return model.getHunter().getPosition();
    }

    /**
     * Returns the board associated with the game.
     * @return the game board
     */
    public PacmanBoard getBoard(){
        return model.getBoard();
    }

    /**
     * Returns the positions and colours of the ghosts in the game.
     * Each ghost should be represented as a Pair(position, colour),
     * where position is their current position on the board, and colour is their colour.
     * The ghost's colour should be given be by Ghost.getColour().
     * However, if the ghost's phase is FRIGHTENED, this colour should instead be "#0000FF".
     * @return a list of Pair<position, colour > representing the ghosts in the game,
     * in no particular order
     */
    public List<Pair<Position,String >> getGhosts() {
        List<Pair<Position, String>> ghostsInfo = new ArrayList<Pair<Position, String>>();
        Pair<Position, String> ghostPair;
        for (Ghost ghost : model.getGhosts()) {
            if (ghost.getPhase() == Phase.FRIGHTENED) {
                ghostPair = new Pair<>(ghost.getPosition(), "#0000FF");
            } else {
                ghostPair = new Pair<>(ghost.getPosition(), ghost.getColour());
            }
            ghostsInfo.add(ghostPair);
        }
        return ghostsInfo;
    }
}
