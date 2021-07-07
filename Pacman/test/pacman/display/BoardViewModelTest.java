package pacman.display;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import pacman.board.PacmanBoard;
import pacman.game.PacmanGame;
import pacman.ghost.Ghost;
import pacman.ghost.Phase;
import pacman.hunter.Hunter;
import pacman.hunter.Speedy;
import pacman.util.Direction;
import pacman.util.Position;

import static org.junit.Assert.*;

public class BoardViewModelTest {
    private String title;
    private String author;
    private Hunter hunter;
    private PacmanBoard board;
    private PacmanGame testGame;
    private BoardViewModel model;

    @Before
    public void setUp() throws Exception {
        title = "csse7023 Pacman";
        author = "Lavinia";
        hunter = new Speedy();
        board = new PacmanBoard(5,5);
        testGame = new PacmanGame(title,author,hunter,board);
        model = new BoardViewModel(testGame);
    }

    @Test
    public void getLives() {
        testGame.setLives(2);
        assertEquals(2,model.getLives());
    }

    @Test
    public void getLevel() {
        testGame.setLevel(2);
        assertEquals(2,model.getLevel());
    }

    @Test
    public void getPacmanColour() {
        if (testGame.getHunter().isSpecialActive()){
            assertEquals("#CDC3FF",model.getPacmanColour());
        }
        else {
            assertEquals("#FFE709",model.getPacmanColour());
        }
    }

    @Test
    public void getPacmanMouthAngle() {
        testGame.getHunter().setDirection(Direction.UP);
        assertEquals(120,model.getPacmanMouthAngle());
        testGame.getHunter().setDirection(Direction.DOWN);
        assertEquals(300,model.getPacmanMouthAngle());
        testGame.getHunter().setDirection(Direction.LEFT);
        assertEquals(210,model.getPacmanMouthAngle());
        testGame.getHunter().setDirection(Direction.RIGHT);
        assertEquals(30,model.getPacmanMouthAngle());
    }

    @Test
    public void getPacmanPosition() {
        testGame.getHunter().setPosition(new Position(2,3));
        assertEquals((new Position(2,3)),model.getPacmanPosition());
    }

    @Test
    public void getBoard() {
        assertEquals(board,model.getBoard());
    }

    @Test
    public void getGhosts() {
        for (int i = 0; i <4; i++){
            Ghost ghost = testGame.getGhosts().get(i);
            Pair<Position, String> ghostPair;
            if (ghost.getPhase() == Phase.FRIGHTENED){
            }
            else {
                ghostPair = new Pair<>(ghost.getPosition(),ghost.getColour());
                assertEquals(ghostPair,model.getGhosts().get(i));
            }
        }
    }
}