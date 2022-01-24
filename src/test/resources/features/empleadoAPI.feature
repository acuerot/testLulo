Feature: Operaciones empleados

  Scenario: Consulta empleados
    When un tester consulta empleados
    Then puede recuperar la información de los empleados

  Scenario: Consulta empleado
    When un tester consulta un empleado
    Then puede recuperar la información del empleado

  Scenario: Crear empleado
    When un tester crea un empleado
    Then se crea el empleado exitosamente

  Scenario: Borrar empleado
    When un tester borra un empleado
    Then se borra el empleado exitosamente