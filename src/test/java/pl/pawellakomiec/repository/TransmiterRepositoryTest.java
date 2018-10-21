package pl.pawellakomiec.repository;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.pawellakomiec.domain.Transmiter;

import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TransmiterRepositoryTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    TransmiterRepository transmiterRepository;

    @Before
    public void initRepository() throws SQLException {

        transmiterRepository = TransmiterRepositoryFactory.getInstance();
        Transmiter transmiter1 = new Transmiter();
        Transmiter transmiter2 = new Transmiter();
        Transmiter transmiter3 = new Transmiter();
        Transmiter transmiter4 = new Transmiter();

        transmiter1.setId(1);
        transmiter1.setName("transmiter1");
        transmiter1.setPrice(100);
        transmiter1.setPower(40);
        transmiter1.setOperationDate("initRepository" + transmiterRepository.getActuallTime());

        transmiter2.setId(2);
        transmiter2.setName("transmiter2");
        transmiter2.setPrice(200);
        transmiter2.setPower(40);
        transmiter2.setOperationDate("initRepository" + transmiterRepository.getActuallTime());


        transmiter3.setId(3);
        transmiter3.setName("transmiter3");
        transmiter3.setPrice(300);
        transmiter3.setPower(40);
        transmiter3.setOperationDate("initRepository" + transmiterRepository.getActuallTime());


        transmiter4.setId(4);
        transmiter4.setName("transmiter4");
        transmiter4.setPrice(400);
        transmiter4.setPower(40);
        transmiter4.setOperationDate("initRepository" + transmiterRepository.getActuallTime());


        transmiterRepository.addTransmiter(transmiter1);
        transmiterRepository.addTransmiter(transmiter2);
        transmiterRepository.addTransmiter(transmiter3);
        transmiterRepository.addTransmiter(transmiter4);
    }


    @Test
    public void get_all() throws SQLException {
        assertNotNull(transmiterRepository.getAll());
    }

    @Test
    public void get_specific_transmiter() throws SQLException{
        Transmiter transmiter2 = transmiterRepository.getById(3);

        String string = "get_specific_transmiter" + transmiterRepository.getActuallTime();
        transmiterRepository.updateAction(3, string);

        if (transmiter2 == null) {
            exception.expect(ClassNotFoundException.class);
        }
    }

    @Test
    public void create_transmiter() throws SQLException {

        Transmiter transmiter5 = new Transmiter();
        transmiter5.setName("createtransmiter5");
        transmiter5.setPrice(50);
        transmiter5.setPower(30);
        transmiter5.setOperationDate("create_transmiter" + transmiterRepository.getActuallTime());
        transmiterRepository.addTransmiter(transmiter5);
        assertNotNull(transmiterRepository.getById(transmiter5.getId()));
    }

    @Test
    public void delete_transmiter() throws SQLException {
        Transmiter transmiter3 = transmiterRepository.getById(2);
        transmiterRepository.deleteTransmiter(transmiter3);

        if (transmiterRepository.getAll().size() > 0) {
            assertNotNull(transmiterRepository.getAll());
            assertNull(transmiterRepository.getById(transmiter3.getId()));
        }
    }

    @Test
    public void search_transmiter() throws SQLException {
        int idToFind = 1;
        String string = "search_transmiter" + transmiterRepository.getActuallTime();
        transmiterRepository.updateAction(1, string);
        assertNotNull(transmiterRepository.getById(idToFind));
    }

    @Test
    public void update_transmiter() throws SQLException {

        Transmiter transmiter2 = TransmiterRepositoryFactory.getInstance().getById(2);
        if (transmiter2 == null) {
            exception.expect(ClassNotFoundException.class);
        }

        int updateId = 2;
        transmiter2.setId(22);
        transmiter2.setName("transmiter222");
        transmiter2.setPrice(222);
        transmiter2.setOperationDate("ds");
        transmiterRepository.updateTransmiter(updateId, transmiter2);

        String string = "update_transmiter" + transmiterRepository.getActuallTime();
        transmiterRepository.updateAction(updateId, string);

        assertEquals(transmiterRepository.getById(updateId).getName(), transmiter2.getName());

        for (Transmiter transmiter1 : transmiterRepository.getAll()) {
            assertNotNull(transmiter1.getName(), transmiter2.getName());
        }
    }

    @After
    public void dropTable() throws SQLException {
        //transmiterRepository.dropDatatable();
    }
}


