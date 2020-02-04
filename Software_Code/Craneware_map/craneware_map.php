<?php
// skeleton code taken from google maps developers documentation
$server = 'silva.computing.dundee.ac.uk';
$username = '19agileteam1';
$password = '8760.at1.0678';
$database = '19agileteam1db';

function parseToXML($htmlStr)
{
$xmlStr=str_replace('<','&lt;',$htmlStr);
$xmlStr=str_replace('>','&gt;',$xmlStr);
$xmlStr=str_replace('"','&quot;',$xmlStr);
$xmlStr=str_replace("'",'&#39;',$xmlStr);
$xmlStr=str_replace("&",'&amp;',$xmlStr);
//$xmlStr=str_replace("$",'&amp;',$xmlStr);
return $xmlStr;
}

//Opens a connection to a MySQL server
$conn = mysqli_connect ($server, $username, $password);
//check the connection
if (!$conn) {
  die('Connection failed: ' . $conn->connect_error);
}

echo 'Connected successfully';

// Set the active MySQL database
$db_selected = mysqli_select_db($conn, $database);
if (!$db_selected) {
  die ('Can\'t use db: ' . mysqli_error($conn));
}

// Select all the rows in the provider_map_id_id table
$query = "SELECT * FROM provider_map_id WHERE 1";
$result = mysqli_query($conn, $query);
if (!$result) {
  die('Invalid query: ' . mysqli_error());
}

//header("Content-type: text/xml");

// Start XML file, echo parent node
echo "<?xml version='1.0' ?>";
echo '<provider_map_id>';
$ind=0;
// Iterate through the rows, printing XML nodes for each
while ($row = @mysqli_fetch_assoc($result)){
  // Add to XML document node
  echo '<placeHolder ';
  echo 'id="' . $row['provider_map_id_id'] . '" ';
  echo 'name="' . parseToXML($row['Provider_name']) . '" ';
  echo 'address="' . parseToXML($row['Full_address']) . '" ';
  echo 'price="' . parseToXML($row['Average_Medicare_Payments']) . '" ';
  echo 'lat="' . $row['Latitude'] . '" ';
  echo 'lng="' . $row['Longitude'] . '" ';
  //echo 'type="' . $row['type'] . '" ';
  echo '/>';
  $ind = $ind + 1;
}

// End XML file
echo '</provider_map_id>';

?>
