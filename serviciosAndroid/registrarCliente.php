<?php

$id = $_REQUEST['id'];
$apellidos = $_REQUEST['apellidos'];
$nombres = $_REQUEST['nombres'];
$edad = $_REQUEST['edad'];
$sexo = $_REQUEST['sexo'];

$cn = new PDO("mysql:host=localhost:3306; dbname=CRUD_IBatis", "root", "mysql");

// $res = $cn->query("INSERT INTO clientes(IdCliente, Apellidos, Nombres, Edad, Sexo) VALUES('$id', '$apellidos', '$nombres', $edad, '$sexo')");

$query = "INSERT INTO Clientes(IdCliente, Apellidos, Nombres, Edad, Sexo) VALUES('$id', '$apellidos', '$nombres', $edad, '$sexo')";

  if ( $cn->query($query) == true ) {
    echo "Registro exitoso";
  }else {
    echo "Error";
  }

?>
