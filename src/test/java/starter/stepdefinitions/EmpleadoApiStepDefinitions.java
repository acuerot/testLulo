package starter.stepdefinitions;

import com.github.javafaker.Faker;
import com.serenity.rest.utils.DtoConsultaEmpleados.ConsultaEmpleadoDetalleDto;
import com.serenity.rest.utils.DtoConsultaEmpleados.ConsultaEmpleadosDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class EmpleadoApiStepDefinitions {

    final String BASE_URI = "http://dummy.restapiexample.com/api/v1/";
    final int edadMinima = 18;
    final int edadMaxima = 85;
    final int salarioMinimo = 1200000;
    final int salarioMaximo = 8000000;
    Faker faker = new Faker();
    RequestSpecification request;

    @Before
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Given("Angelica prepara la consulta")
    public void angelica_prepara_la_consulta() {
    }

    @When("ella consulta empleados")
    public void ella_consulta_empleados() {
        request = RestAssured.given();
        Response response = request.get("employees");
        System.out.println("Response: " + response.asString());
        response.then().assertThat().statusCode(200);
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<ConsultaEmpleadoDetalleDto> listaEmpleados = jsonPathEvaluator.getList("data",ConsultaEmpleadoDetalleDto.class);
        /**
         * Se buscan empleados mayores de 30 años y se imprimen
         * con el nombre
         */
        HashMap<String, Integer> mapEmpleados = new HashMap <> ();
        System.out.println("Prueba::" + listaEmpleados.get(0).getEmployeeName());
        for(int i=0;i<listaEmpleados.size();i++){
            if(listaEmpleados.get(i).getEmployeeAge()>30){
                mapEmpleados.put(listaEmpleados.get(i).getEmployeeName(),
                        listaEmpleados.get(i).getEmployeeAge());
            }
        }
        System.out.println("Empleados mayores de 30 años: " + mapEmpleados);
    }

    @Then("ella observa que la consulta de empleados es exitosa")
    public void ella_observa_que_la_consulta_de_empleados_es_exitosa() {

    }

    @When("ella consulta un empleado")
    public void ella_consulta_un_empleado() {
        request = RestAssured.given();
        Response response = request.get("employee/1");
        System.out.println("Response: " + response.asString());
        response.then().assertThat().statusCode(200).
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
        request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name",faker.name().firstName());
        requestParams.put("salary",faker.number().numberBetween(salarioMinimo,salarioMaximo));
        requestParams.put("age",faker.number().numberBetween(edadMinima,edadMaxima));
        request.body(requestParams.toString());
        Response response = request.post("create");
        System.out.println("Response: " + response.asString());
        response.then().assertThat().statusCode(200).
                and().body("message",containsString("Record has been added."));
    }

    @Then("ella observa que la creación del empleado es exitosa")
    public void ella_observa_que_la_creación_del_empleado_es_exitosa() {

    }

    @Given("Angelica prepara la eliminación")
    public void angelica_prepara_la_eliminación() {

    }

    @When("ella borra un empleado")
    public void ella_borra_un_empleado() {
        RequestSpecification request = RestAssured.given();
        Response response = request.delete("delete/1");
        System.out.println("Response: " + response.asString());
        response.then().assertThat().statusCode(200).
                and().body("message",containsString("Record has been deleted"));
    }

    @Then("ella observa que el borrado del empleado es exitosa")
    public void ella_observa_que_el_borrado_del_empleado_es_exitosa() {

    }
}
