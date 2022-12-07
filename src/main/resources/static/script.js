function initNames() {
    var url = "http://localhost:8080/monitoring/names"
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            let selectBox = '<select onchange=loadByName(this)>';
            for (var i = 0; i < data.length; i++) {
                selectBox += "<option>"+data[i]+"</option>";
            }

            selectBox += "</select>";
            document.getElementById("names").innerHTML = selectBox;
        })
}

function loadByName(name) {

    var url = "http://localhost:8080/monitoring/name/" + name.value;
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            let table = "<table>";
            table += "<thead>" +
                "        <tr>" +
                "            <th scope=\"col\">Patient Name</th>" +
                "            <th scope=\"col\">Start Time</th>" +
                "            <th scope=\"col\">End Time</th>" +
                "            <th scope=\"col\">Minutes Slept</th>" +
                "            <th scope=\"col\">Deep Sleep Time</th>" +
                "            <th scope=\"col\">Light Sleep Time</th>" +

                "        </tr>" +
                "    </thead>";

            for (var i = 0; i < data.length; i++) {

                table += "<tr>";
                table += "<td>" + data[i].patientName + "</td>"
                table += "<td>" + data[i].startTime + "</td>"
                table += "<td>" + data[i].endTime + "</td>"
                table += "<td>" + data[i].minutesSlept + "</td>"
                table += "<td>" + data[i].deepSleep + "</td>"
                table += "<td>" + data[i].lightSleep + "</td>"
                table += "</tr>";
            }

            document.getElementById("testField").innerHTML = table;
        })
}

function getData()
{
    var url = "http://localhost:8080/monitoring/data"
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            let table = "<table>";
            table += "<thead>" +
                "        <tr>" +
                "            <th scope=\"col\">Name</th>" +
                "            <th scope=\"col\">Start Time</th>" +
                "            <th scope=\"col\">End Time</th>" +
                "            <th scope=\"col\">Minutes Slept</th>" +
                "            <th scope=\"col\">Deep Sleep Time</th>" +
                "            <th scope=\"col\">Light Sleep Time</th>" +

                "        </tr>" +
                "    </thead>";

            for (var i = 0; i < data.length; i++) {

                table += "<tr>";
                table += "<td>" + data[i].patientName + "</td>"
                table += "<td>" + data[i].startTime + "</td>"
                    table += "<td>" + data[i].endTime + "</td>"
                    table += "<td>" + data[i].minutesSlept + "</td>"
                    table += "<td>" + data[i].deepSleep + "</td>"
                    table += "<td>" + data[i].lightSleep + "</td>"
                table += "</tr>";
            }

            document.getElementById("testField").innerHTML = table;
        })
}