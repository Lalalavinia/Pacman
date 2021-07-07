package pacman.display;

import org.junit.Before;
import org.junit.Test;
import pacman.board.PacmanBoard;
import pacman.game.PacmanGame;
import pacman.hunter.Hunter;
import pacman.hunter.Speedy;
import pacman.score.ScoreBoard;

import static org.junit.Assert.*;

public class ScoreViewModelTest {
    private ScoreViewModel model;
    private String title;
    private String author;
    private Hunter hunter;
    private PacmanBoard board;
    private PacmanGame testGame;

    @Before
    public void setUp() throws Exception {
        title = "csse7023 Pacman";
        author = "Lavinia";
        hunter = new Speedy();
        board = new PacmanBoard(5,5);
        //scoreBoard = new ScoreBoard();
        testGame = new PacmanGame(title,author,hunter,board);
        model = new ScoreViewModel(testGame);

    }

    @Test
    public void update() {
    }

    @Test
    public void switchScoreOrder() {
        assertEquals("Sorted by Name",this.model.getSortedBy().get());
        model.switchScoreOrder();
        model.update();
        assertEquals("Sorted by Score",this.model.getSortedBy().get());
    }

    @Test
    public void getCurrentScoreProperty() {
        assertEquals("Score: " +testGame.getScores().getScore(),model.getCurrentScoreProperty().get());
    }

    @Test
    public void getScores() {
        for (int i = 0; i < testGame.getScores().getEntriesByName().size();i++){
            assertEquals(testGame.getScores().getEntriesByName().get(i),model.getScores().get(i));
        }
        this.model.switchScoreOrder();
        update();
        for (int i = 0; i < testGame.getScores().getEntriesByScore().size();i++){
            assertEquals(testGame.getScores().getEntriesByScore().get(i),model.getScores().get(i));
        }
    }
    @Test
    public void getCurrentScore() {
        assertEquals(testGame.getScores().getScore(),model.getCurrentScore());
    }
    @Test
    public void setPlayerScore() {
        this.model.setPlayerScore("Lavinia",20000);
        this.model.update();
        assertEquals(model.getScores().get(0),"Lavinia : 20000");
        this.model.setPlayerScore("Lavinia",200);
        this.model.update();
        assertEquals(model.getScores().get(0),"Lavinia : 200");
    }
}