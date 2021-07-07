package pacman.display;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pacman.game.GameWriter;
import pacman.game.PacmanGame;
import javafx.beans.property.StringProperty;
import pacman.hunter.Hunter;
import pacman.util.Direction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class MainViewModel {
    // the PacmanGame to link to the view.
    private PacmanGame model;
    // the name for saving the game.
    private String saveFilename;
    // the game over status
    private BooleanProperty gameOver;
    // the property associated with the pause state.
    private BooleanProperty pause;
    // the BoardViewModel created at initialisation.
    private BoardViewModel boardViewModel;
    // the the ScoreViewModel created at initialisation.
    private ScoreViewModel scoreViewModel;
    // the current tick.
    private int tickCount;

    /**
     * Creates a MainViewModel and updates the propertiesand the ScoreViewModel created.
     *  model the PacmanGame to link to the view.
     * @param model the PacmanGame to link to the view
     * @param saveFilename the name for saving the game
     */
    public MainViewModel(PacmanGame model, String saveFilename){
        this.model = model;
        this.saveFilename = saveFilename;
        this.boardViewModel = new BoardViewModel(model);
        this.scoreViewModel = new ScoreViewModel(model);
        gameOver = new SimpleBooleanProperty();
        pause = new SimpleBooleanProperty(true);
    }

    /**
     * Updates the title of the game window and the score view model.
     */
    public void update(){
        getTitle();
        scoreViewModel.update();
    }

    /**
     * Gets the title property of the Game Window.
     * @return the title property of the game
     */
    public StringProperty getTitle(){
        StringProperty title = new SimpleStringProperty(this.model.getTitle() + " by " + this.model.getAuthor());
        return title;
    }

    /**
     * Gets the property representing whether the game is over or not.
     * @return the game over status
     */
    public BooleanProperty isGameOver(){
        return gameOver;
    }

    /**
     * Saves the current state of the game to the file location given in the constructor.
     * An IOException should not cause the program to crash and should be ignored.
     */
    public void save(){
        try {
            FileWriter writer = new FileWriter(new File(saveFilename));
            GameWriter.write(writer,model);
            writer.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Tick is to be called by the view at around 60 times a second.
     * This method will pass these ticks down to the model at a reduced rate
     * depending on the level of the game.
     * The game starts with zero ticks.
     */
    public void tick(){
        if (! isPaused().get()) {
            switch (this.model.getLevel()) {
                case 0:
                case 1:
                    if (tickCount % 50 == 0){
                        this.model.tick();
                    }
                    break;
                case 2:
                case 3:
                    if (tickCount % 40 == 0){
                        this.model.tick();
                    }
                    break;
                case 4:
                case 5:
                    if (tickCount % 30 == 0){
                        this.model.tick();
                    }
                    break;
                case 6:
                case 7:
                case 8:
                    if (tickCount % 20 ==0){
                        this.model.tick();
                    }
                    break;
                default:
                    if (tickCount % 10 == 0){
                        this.model.tick();
                    }
            }
            tickCount++;
        }
        if (this.model.getLives() == 0){
            isGameOver().set(true);
        } else {
            isGameOver().set(false);
        }
    }

    /**
     * Accepts key input from the view and acts according to the key.
     * @return incoming input from the view.
     */
    public void accept(String input){
        if (isPaused().get()){
            switch (input){
                case "P":
                case "p":
                    isPaused().set(false);
                    break;
                case "R":
                case "r":
                    this.model.reset();
                    break;
            }
        } else {
            switch (input){
                case "P":
                case "p":
                    isPaused().set(true);
                    break;
                case "R":
                case "r":
                    this.model.reset();
                    break;
                case "A":
                case "a":
                    this.model.getHunter().setDirection(Direction.LEFT);
                    break;
                case "D":
                case "d":
                    this.model.getHunter().setDirection(Direction.RIGHT);
                    break;
                case "W":
                case "w":
                    this.model.getHunter().setDirection(Direction.UP);
                    break;
                case "S":
                case "s":
                    this.model.getHunter().setDirection(Direction.DOWN);
                    break;
                case "O":
                case "o":
                    this.model.getHunter().activateSpecial(Hunter.SPECIAL_DURATION);
                    break;
            }
        }
    }

    /**
     *Gets the paused property of the game.
     * @return the property associated with the pause state.
     */
    public BooleanProperty isPaused(){
        return pause;
    }

    /**
     * Gets the ScoreViewModel created at initialisation.
     * @return the ScoreViewModel associated wtih the game's scores
     */
    public ScoreViewModel getScoreVM(){
        return this.scoreViewModel;
    }

    /**
     * Gets the BoardViewModel created at initialisation.
     * @return the BoardViewModel associated with the game play.
     */
    public BoardViewModel getBoardVM(){
        return this.boardViewModel;
    }

}
