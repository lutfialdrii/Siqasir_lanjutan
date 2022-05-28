<?php
require_once 'db_connection.php';
header("Content-Type: application/json; charset=UTF-8");

$username = $_POST['username'];
$password = $_POST['password'];

$query = "SELECT * FROM users WHERE `username`='$username' AND `password` = '$password'";

$execute = mysqli_query($dbConnection,$query);
$response =[];
$row = mysqli_fetch_assoc($execute);

if (count($row)>0) {
   $response['status']='succcess';
   $response['message']='User ditemukan';
   $response['data']=$row;
}
else {
   $response['status']='failed';
   $response['message']='User tidak ditemukan';
   $response['data']=$row;

}
$json = json_encode($response,JSON_PRETTY_PRINT);
echo $json;



