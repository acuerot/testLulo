Feature: Operaciones empleados

  Scenario: Consulta empleados
    Given Angelica prepara la consulta
    When ella consulta empleados
    Then ella observa que la consulta de empleados es exitosa

  Scenario: Consulta empleado
    Given Angelica prepara la consulta
    When ella consulta un empleado
    Then ella observa que la consulta del empleado es exitosa

  Scenario: Crear empleado
    Given Angelica prepara la creación
    When ella crea un empleado
    Then ella observa que la creación del empleado es exitosa

  Scenario: Borrar empleado
    Given Angelica prepara la eliminación
    When ella borra un empleado
    Then ella observa que el borrado del empleado es exitosa