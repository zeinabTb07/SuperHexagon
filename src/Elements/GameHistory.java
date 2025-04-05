package Elements;
public class GameHistory {
    private String playerName;
    private String playTime;
    private double score;

    public GameHistory(String playerName, String playTime, double score) {
        this.playerName = playerName;
        this.playTime = playTime;
        this.score = score;
    }

    public String getPlayerName() { return playerName; }
    public String getPlayTime() { return playTime; }
    public double getScore() { return score; }

    @Override
    public String toString() {
        return playerName + " | Time: " + playTime + " | Score: " + score;
    }
}
