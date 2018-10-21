package pl.pawellakomiec.repository;

import pl.pawellakomiec.domain.Transmiter;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

public interface TransmiterRepository {

    void setConnection(Connection connection) throws SQLException;

    Connection getConnection();

    List<Transmiter> getAll() throws SQLException;

    Transmiter getById(int id) throws SQLException;

    Transmiter getByName(String name) throws SQLException;

    void addTransmiter(Transmiter transmiter);

    void deleteTransmiter(Transmiter transmiter) throws SQLException;

    void updateTransmiter(int id, Transmiter newTransmiter) throws SQLException;

    void dropDatatable() throws SQLException;
}