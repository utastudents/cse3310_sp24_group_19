package wordsearch;

import java.util.List;

public class LeaderBoard {
    public static String generateLeaderBoard(List<Player> players) {
        StringBuilder leaderboardString = new StringBuilder();

        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (players.get(i).getScore() < players.get(j).getScore()) {
                    // Swap players
                    Player temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            leaderboardString.append((i + 1) + ". " + player.getNick() + " - " + player.getScore() + "\n");
        }

        return leaderboardString.toString();
    }
}
