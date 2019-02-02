package pl.pawellakomiec.bdd;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import pl.pawellakomiec.domain.Transmiter;
import pl.pawellakomiec.repository.TransmiterRepositoryImpl;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class TransmiterBddSteps {


    private Transmiter transmiter;
    private TransmiterRepositoryImpl service;

    @Given("a transmiter")
    public void transmiterSetup() throws SQLException {
        transmiter = new Transmiter();
        service = new TransmiterRepositoryImpl();
    }

    @When("set arguments like name: $a, model: $b, price: $c")
    public void createNewTransmiter(String a, Integer b, Integer c) {
        transmiter.setName(a);
        transmiter.setPower(b);
        transmiter.setPrice(c);
    }

    @When("add it to arraylist")
    public void addToArrayList() {
        service.addTransmiter(transmiter);
    }

    @Then("destroy item with code $code")
    public void setNewNameForTransmiter(String code) throws SQLException {
        Transmiter transmiter1 = service.getByName(code);
        service.deleteTransmiter(transmiter1);
    }

    @Then("adding should return $name for Transmiter object with code $code")
    public void shouldAdd(String name, String code) throws SQLException {
        Transmiter transmiter = service.getByName(name);
        assertEquals(name, transmiter.getName());
    }

    @Then("check if data has been updated for transmiter with code $code and new name $name")
    public void checkIfUpdated(Integer code, String name) throws SQLException {
        Transmiter transmiter = service.getById(code);
        assertEquals(name, transmiter.getName());
    }

}
