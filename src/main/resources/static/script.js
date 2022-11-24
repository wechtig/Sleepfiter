function getData()
{
    var url = "http://localhost:8080/monitoring/data"

    /*
    console.log("check for data")
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    var responseTestText = xmlHttp.json();
    console.log(responseTestText)
    document.getElementById("testField").innerText = responseTestText;
    return responseTestText;
*/

    fetch(url)
        .then(function(response) {
            var res = response.json();
            console.log(res);
        })
        .then(function(jsonResponse) {
            // do something with jsonResponse
        });
}