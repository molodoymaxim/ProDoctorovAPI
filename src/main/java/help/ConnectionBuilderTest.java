package help;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionBuilderTest implements ConnectionBuilder {

    private static final Logger logger = Logger.getLogger(ConnectionBuilderPostgres.class.getName());

    public Connection getConnection() {
        var props = new Properties();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            props.load(is);
        } catch (IOException e) {
            logger.log(Level.INFO, "No such properties", e);
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.log(Level.INFO, "No db connection", e);
        }

        return connection;
    }
}
