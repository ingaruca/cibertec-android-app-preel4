<?php

$id = $_REQUEST['id'];
$apellidos = $_REQUEST['apellidos'];
$nombres = $_REQUEST['nombres'];
$edad = $_REQUEST['edad'];
$sexo = $_REQUEST['sexo'];

$cn = new PDO("mysql:host=localhost:3306; dbname=CRUD_IBatis", "root", "mysql");

$query = "UPDATE Clientes SET Apellidos='$apellidos', Nombres='$nombres', Edad=$edad, Sexo='$sexo' WHERE IdCliente='$id'";

if ($cn->query($query) == true) {
  echo "Se han grabados los cambios para el cliente con ID $id";
}else {
  echo "Error";
}

 ?>
