package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class EmpleadoApiStepDefinitions {

    private String baseUrl = "http://dummy.restapiexample.com/api/v1/";
    private Response response;

    @Given("Angelica prepara la consulta")
    public void angelica_prepara_la_consulta() {


    }

    @When("ella consulta empleados")
    public void ella_consulta_empleados() {
    }

    @Then("ella observa que la consulta de empleados es exitosa")
    public void ella_observa_que_la_consulta_de_empleados_es_exitosa() {

    }

    @When("ella consulta un empleado")
    public void ella_consulta_un_empleado() {
        RestAssured.
                when().get(baseUrl + "employee/1").
                then().assertThat().statusCode(200).
                and().body("data.id", equalTo(1),"data.employee_name",is("Tiger Nixon"));
    }

    @Then("ella observa que la consulta del empleado es exitosa")
    public void ella_observa_que_la_consulta_del_empleado_es_exitosa() {

    }

    @Given("Angelica prepara la creación")
    public void angelica_prepara_la_creación() {

    }

    @When("ella crea un empleado")
    public void ella_crea_un_empleado() {

    }

    @Then("ella observa que la creación del empleado es exitosa")
    public void ella_observa_que_la_creación_del_empleado_es_exitosa() {

    }

    @Given("Angelica prepara la eliminación")
    public void angelica_prepara_la_eliminación() {

    }

    @When("ella borra un empleado")
    public void ella_borra_un_empleado() {
        RestAssured.
                when().delete(baseUrl + "delete/1").
                then().assertThat().statusCode(200).
                and().body("message",containsString("Record has been deleted"));
    }

    @Then("ella observa que el borrado del empleado es exitosa")
    public void ella_observa_que_el_borrado_del_empleado_es_exitosa() {

    }
}
