<?php

$cn = new PDO("mysql:host=localhost:3306; dbname=CRUD_IBatis", "root", "mysql");

$res = $cn->query("SELECT * FROM Clientes");

$datos = array();

foreach ($res as $row) {
    $datos[] = $row;
}

echo json_encode($datos);

 ?>
