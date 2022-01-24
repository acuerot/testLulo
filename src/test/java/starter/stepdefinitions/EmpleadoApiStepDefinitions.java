package starter.stepdefinitions;

import com.serenity.rest.models.data.EmpleadoData;
import com.serenity.rest.models.empleado.NuevoEmpleado;
import com.serenity.rest.tasks.rest.GetRequest;
import com.serenity.rest.tasks.rest.PostRequest;
import com.serenity.rest.utils.DtoConsultaEmpleados.ConsultaEmpleadoDetalleDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import java.util.HashMap;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class EmpleadoApiStepDefinitions {

    Actor actor = Actor.named("Angie");
    EnvironmentVariables environmentVariables;
    RequestSpecification request;
    Response response;

    @Before
    public void setup() {
        actor.whoCan(CallAnApi.at(environmentVariables.getProperty("baseUrl")));
        RestAssured.baseURI = environmentVariables.getProperty("baseUrl");
    }

    @When("un tester consulta empleados")
    public void un_tester_consulta_empleados() {
        actor.attemptsTo(GetRequest.withResource(environmentVariables.getProperty("consultaEmpleados")));
    }

    @Then("puede recuperar la información de los empleados")
    public void puede_recuperar_la_información_de_los_empleados() {
        actor.should(seeThatResponse("Ver el código de respuesta",
                response -> response.statusCode(200)));
        ConsultaEmpleadoDetalleDto[] listaEmpleados = SerenityRest.lastResponse()
                .jsonPath().getObject("data", ConsultaEmpleadoDetalleDto[].class);
        /**
         * Se buscan empleados mayores de 30 años y se imprimen
         * con el nombre
         */
        HashMap<String, Integer> mapEmpleados = new HashMap <> ();
        for (int i = 0; i < listaEmpleados.length; i++) {
            assertThat(listaEmpleados[i]).hasNoNullFieldsOrProperties();
            mapEmpleados.put(listaEmpleados[i].getEmployeeName(),
                    listaEmpleados[i].getEmployeeAge());
        }
        System.out.println("Empleados mayores de 30 años: " + mapEmpleados);
    }

    @When("un tester consulta un empleado")
    public void un_tester_consulta_un_empleado() {
        actor.attemptsTo(GetRequest.withResource(environmentVariables.getProperty("consultaEmpleado")));
    }

    @Then("puede recuperar la información del empleado")
    public void puede_recuperar_la_información_del_empleado() {
        actor.should(seeThatResponse("Ver el código de respuesta",
                response -> response.statusCode(200)));
    }

    @When("un tester crea un empleado")
    public void un_tester_crea_un_empleado() {
        actor.attemptsTo(PostRequest.withData(environmentVariables.getProperty("crearEmpleado"),
                EmpleadoData.getNuevoEmpleado()));
    }

    @Then("se crea el empleado exitosamente")
    public void se_crea_el_empleado_exitosamente() {
        actor.should(seeThatResponse("ver el código de respuesta",
                response -> response.statusCode(200)));
        NuevoEmpleado user = SerenityRest.lastResponse()
                .jsonPath().getObject("", NuevoEmpleado.class);
        assertThat(user).hasNoNullFieldsOrProperties();
    }

    @When("un tester borra un empleado")
    public void un_tester_borra_un_empleado() {
        request = RestAssured.given();
        response = request.delete(environmentVariables.getProperty("borrarEmpleado").concat("1"));
        System.out.println("Response: " + response.asString());
    }

    @Then("se borra el empleado exitosamente")
    public void se_borra_el_empleado_exitosamente() {
        response.then().assertThat().statusCode(200).
                and().body("message",containsString("Record has been deleted"));
    }
}
