package pl.pawellakomiec.repository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.pawellakomiec.domain.Transmiter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class TransmiterRepositoryMockTest {

    TransmiterRepository transmiterRepository;

    @Mock
    Connection connectionMock;

    @Mock
    TransmiterRepository transmiterRepositoryMock;

    @Mock
    PreparedStatement insertStatementMock;

    @Mock
    PreparedStatement selectStatementMock;

    @Mock
    PreparedStatement getTransmiterByIdStmt;


    @Before
    public void setupDatabase() throws SQLException {

        when(connectionMock.prepareStatement("INSERT INTO Transmiter (name, price, power, operationDate) VALUES (?, ?, ?, ?)")).thenReturn(insertStatementMock);
        when(connectionMock.prepareStatement("SELECT * FROM Transmiter")).thenReturn(selectStatementMock);
        when(connectionMock.prepareStatement("SELECT * FROM Transmiter WHERE id = ?")).thenReturn(getTransmiterByIdStmt);


        transmiterRepository = new TransmiterRepositoryImpl();
        transmiterRepositoryMock = mock(TransmiterRepositoryImpl.class);
        transmiterRepository.setConnection(connectionMock);

        verify(connectionMock).prepareStatement("INSERT INTO Transmiter (name, price, power, operationDate) VALUES (?, ?, ?, ?)");
        verify(connectionMock).prepareStatement("SELECT * FROM Transmiter");
        verify(connectionMock).prepareStatement("SELECT * FROM Transmiter WHERE id = ?");

    }

    @Test
    public void checkAdding() throws Exception {
        when(insertStatementMock.executeUpdate()).thenReturn(1);

        Transmiter transmiter = new Transmiter();
        transmiter.setName("name");
        transmiter.setPrice(1);
        transmiter.setPower(1);
        transmiterRepository.addTransmiter(transmiter);

        verify(insertStatementMock, times(1)).setString(1, "name");
        verify(insertStatementMock, times(1)).setInt(2, 1);
        verify(insertStatementMock, times(1)).setInt(3, 1);
        verify(insertStatementMock).executeUpdate();
    }

    abstract class AbstractResultSet implements ResultSet {
        int i = 0;

        @Override
        public boolean next() throws SQLException {
            if (i == 1)
                return false;
            i++;
            return true;
        }

        @Override
        public int getInt(String s) throws SQLException {
            return 1;
        }

        @Override
        public String getString(String columnLabel) throws SQLException {
            return "nameString";
        }
    }

    abstract class AbstractResultSetById implements ResultSet {
        int i = 0;

        @Override
        public int getInt(String s) throws SQLException {
            return 1;
        }

        @Override
        public String getString(String columnLabel) throws SQLException {
            return "nameString";
        }

        @Override
        public boolean next() throws SQLException {
            if (i == 1)
                return false;
            i++;
            return true;
        }
    }


    @Test
    public void getAllTransmiters() throws SQLException {

        AbstractResultSet mockedResultSet = mock(AbstractResultSet.class);
        when(mockedResultSet.next()).thenCallRealMethod();
        when(mockedResultSet.getInt("id")).thenCallRealMethod();
        when(mockedResultSet.getString("name")).thenCallRealMethod();
        when(selectStatementMock.executeQuery()).thenReturn(mockedResultSet);

        assertEquals(1, transmiterRepository.getAll().size());

        verify(selectStatementMock, times(1)).executeQuery();
        verify(mockedResultSet, times(1)).getInt("id");
        verify(mockedResultSet, times(1)).getString("name");
        verify(mockedResultSet, times(2)).next();

    }



    @Test(expected = IllegalStateException.class)
    public void checkExceptionWhenAddingNullAdding() throws Exception {
        when(insertStatementMock.executeUpdate()).thenThrow(new SQLException());
        Transmiter transmiter = new Transmiter();
        transmiter.setId(5);
        transmiter.setName(null);
        transmiter.setPrice(66);
        transmiter.setPower(77);

        transmiterRepository.addTransmiter(transmiter);
        assertNotNull(transmiterRepository.getById(transmiter.getId()));
    }

}