package help;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionBuilderPostgres implements ConnectionBuilder {

    private static final Logger logger = Logger.getLogger(ConnectionBuilderPostgres.class.getName());

    public Connection getConnection() {

        var props = new Properties();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("connection.properties")) {
            props.load(is);
        } catch (IOException e) {
            logger.log(Level.INFO, "load properties problem", e);
        }

        String data = props.getProperty("datasource");

        Connection connection = null;

        try {
            Context envContext = new InitialContext();
            DataSource dataSource = (DataSource) envContext.lookup(data);

            if (dataSource == null) {
                logger.log(Level.INFO, "datasource not found");
            }
            assert dataSource != null;
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            logger.log(Level.INFO, "No db connection", e);
        }
        return connection;
    }

}
