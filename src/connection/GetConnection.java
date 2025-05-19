package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnection {

    public static Connection createConnection() {
        try {
            Properties prop = getProperties();
            final String URL = prop.getProperty("bd.url");
            final String LOGIN = prop.getProperty("bd.login");
            final String PASSWORD = prop.getProperty("bd.password");
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Properties getProperties() {
        try {
            Properties prop = new Properties();
            String directory = "/home/bitter/Documentos/conexao.properties";
            FileInputStream patch = new FileInputStream(directory);
            prop.load(patch);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
