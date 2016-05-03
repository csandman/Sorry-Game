import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stats {
    // Connect to the database
    private Connection con = null;
    private PreparedStatement pst = null;

    private String url = "jdbc:mysql://webdb.uvm.edu/PDAMORA_sorry";
    private String user = "pdamora_admin";
    private String password = "7GapsNhuzuBZ";

    public Stats() {

    }

    public void add(String playerName, String time, String color, String result) {
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("INSERT INTO Stats(player_name, time, color, result) VALUES(?, ?, ?, ?)");
            pst.setString(1, playerName);
            pst.setString(2, time);
            pst.setString(3, color);
            pst.setString(4, result);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Stats.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Stats.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }


}
