package pacman.display;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pacman.game.PacmanGame;

public class ScoreViewModel {
    // the stringproperty of how the player's scores are displayed.
    private StringProperty sortedBy;
    // the PacmanGame to link to the view.
    private PacmanGame model;
    // the StringProperty containing the current score for the player.
    private StringProperty currentScore;
    // a list containing all "Name : Value" score entries in the game's ScoreBoard,
    private ObservableList<String> scores;

    /**
     * Creates a new ScoreViewModel and updates its properties.
     * The default ordering of the scores should be by name.
     * @param model the PacmanGame to link to the view
     */
    public ScoreViewModel(PacmanGame model){
        this.model = model;
        sortedBy = new SimpleStringProperty("Sorted by Name");
        currentScore = new SimpleStringProperty();
        this.scores = FXCollections.observableList(this.model.getScores().getEntriesByName());
        this.update();
    }

    /**
     * Updates the properties containing the current score,
     * the sort order of the scoreboard and the list of sorted scores.
     */
    public void update(){
        currentScore.set("Score: " + this.model.getScores().getScore());
        if (getSortedBy().get().equals("Sorted by Name")){
            scores.setAll(this.model.getScores().getEntriesByName());
        } else if (getSortedBy().get().equals("Sorted by Score")){
            scores.setAll(this.model.getScores().getEntriesByScore());
        }
    }

    /**
     * Changes the order in which player's scores are displayed.
     */
    public void switchScoreOrder(){
       if (sortedBy.get().equals("Sorted by Name")){
                sortedBy.set("Sorted by Score");
        } else if (sortedBy.get().equals("Sorted by Score")){
           sortedBy.set("Sorted by Name");
       }
    }

    /**
     * Returns the StringProperty containing the current score for the player.
     * @return the property representing the current score
     */
    public StringProperty getCurrentScoreProperty(){
        return currentScore;
    }

    /**
     * Returns the StringProperty of how the player's scores are displayed.
     * @return StringProperty representing how the player's scores are displayed
     */
    public StringProperty getSortedBy(){
        return sortedBy;
    }

    /**
     * Returns a list containing all "Name : Value" score entries in the game's ScoreBoard,
     * sorted by the current sort order.
     * @return the list of sorted scores
     */
    public ObservableList<String> getScores(){
        return scores;
    }

    /**
     * Returns the overall current score for the game.
     * @return current score
     */
    public int getCurrentScore(){
        return this.model.getScores().getScore();
    }

    /**
     * Sets the given player's score to score.
     * @param player the player
     * @param score the new score
     */
    public void setPlayerScore(String player, int score){
        this.model.getScores().setScore(player,score);
    }



}
