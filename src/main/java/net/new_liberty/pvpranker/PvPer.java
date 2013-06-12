package net.new_liberty.pvpranker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wrapper class to interact with the database in an object-oriented fashion.
 */
public class PvPer {
    private final PvPRanker plugin;

    private final String name;

    public PvPer(PvPRanker plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        ResultSet set = plugin.getDb().execute(
                "SELECT SUM(score) AS score "
                + "FROM pvpr_scores "
                + "WHERE player = `" + name + "`");

        int ret = 0;
        try {
            if (set.next()) {
                ret = set.getInt("score");
            }
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not get score from ResultSet!", ex);
        }

        return ret;
    }
}
