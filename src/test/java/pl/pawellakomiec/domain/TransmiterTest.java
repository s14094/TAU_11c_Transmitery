package pl.pawellakomiec.domain;

import org.junit.Before;
import org.junit.Test;

import pl.pawellakomiec.domain.Transmiter;

import static org.junit.Assert.*;

public class TransmiterTest {

    private Transmiter transmiter;

    @Before
    public void buildTransmiter() {
        transmiter = new Transmiter();
    }

    @Test
    public void checkIfTransmiterExists() {
        assertNotNull(transmiter);
    }
}
