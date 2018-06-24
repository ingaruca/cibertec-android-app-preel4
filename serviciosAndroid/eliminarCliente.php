<?php

$id = $_REQUEST['id'];

$cn = new PDO("mysql:host=localhost:3306; dbname=CRUD_IBatis", "root", "mysql");

$query = "DELETE FROM Clientes WHERE IdCliente = '$id' ";

if ($cn->query($query) == true) {
  echo "Registro eliminado";
}

 ?>
