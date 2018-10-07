package pl.pawellakomiec.repository;

import javax.activation.DataSource;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransmiterRepositoryFactory {

    public static TransmiterRepository getInstance() {

        try {
            String url = "jdbc:hsqldb:hsql://localhost/workdb";
            return new TransmiterRepositoryImpl(DriverManager.getConnection(url));
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
