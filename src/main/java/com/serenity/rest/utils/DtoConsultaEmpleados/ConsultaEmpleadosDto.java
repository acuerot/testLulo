package com.serenity.rest.utils.DtoConsultaEmpleados;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "data",
        "message"
})
public class ConsultaEmpleadosDto {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private List<ConsultaEmpleadoDetalleDto> data = null;
    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("data")
    public List<ConsultaEmpleadoDetalleDto> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<ConsultaEmpleadoDetalleDto> data) {
        this.data = data;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }
}
