function testRequest()
{
    var url = "http://localhost:8080/monitoring/data"
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    var responseTestText = xmlHttp.responseText;
    document.getElementById("testField").innerText = responseTestText;
    return responseTestText;
}