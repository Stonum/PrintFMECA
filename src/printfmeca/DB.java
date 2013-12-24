package printfmeca;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Stonum
 */
public class DB {

    Connection con = null;
    Statement stat = null;
    ResultSet res = null;

    public DB() {
        try {
            Driver d = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            String url = "jdbc:sqlite:FMECA.db";
            con = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Log.print(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Vector getDevelopName() {
        try {
            String sql = "SELECT Name FROM Developers";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            Vector names = new Vector();
            while (res.next()) {
                names.add(res.getString("Name"));
            }
            return names;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addDevelopName(String name) {
        try {
            String sql = "INSERT INTO Developers (Name) VALUES (\"" + name + "\")";
            stat = con.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delDevelopName(String name) {
        try {
            String sql = "DELETE FROM Developers WHERE Name=(\"" + name + "\")";
            stat = con.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Vector getPotencialName() {
        try {
            String sql = "SELECT Name FROM Potencial";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            Vector names = new Vector();
            while (res.next()) {
                names.add(res.getString("Name"));
            }
            return names;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addPotencialName(String name) {
        try {
            String sql = "INSERT INTO Potencial (Name) VALUES (\"" + name + "\")";
            stat = con.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delPotencialname(String name) {
        try {
            String sql = "DELETE FROM Potencial WHERE Name=(\"" + name + "\")";
            stat = con.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
        public Vector getRootName() {
        try {
            String sql = "SELECT Name FROM Root";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            Vector names = new Vector();
            while (res.next()) {
                names.add(res.getString("Name"));
            }
            return names;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addRootName(String name) {
        try {
            String sql = "INSERT INTO Root (Name) VALUES (\"" + name + "\")";
            stat = con.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delRootName(String name) {
        try {
            String sql = "DELETE FROM Root WHERE Name=(\"" + name + "\")";
            stat = con.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            if (res != null) {
                res.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
