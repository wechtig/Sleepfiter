function initNames() {
    var url = "http://localhost:8080/monitoring/names"
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            let selectBox = '<select onchange=loadByName(this)><option>Station</option>';
            for (var i = 0; i < data.length; i++) {
                selectBox += "<option>"+data[i]+"</option>";
            }

            selectBox += "</select>";
            document.getElementById("names").innerHTML = selectBox;
        })
}

function loadByName(name) {
    warning = 0;
    var url = "http://localhost:8080/monitoring/name/" + name.value;
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            let table = "<table class=\"table\">";
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

                if(data[i].minutesSlept < 350) {
                    warning++;
                    table += "<tr style='background-color: #c93439'>";
                } else {
                    table += "<tr style='background-color: #2bba51'>";
                }
                table += "<td>" + data[i].patientName + "</td>"
                table += "<td>" + data[i].startTime + "</td>"
                table += "<td>" + data[i].endTime + "</td>"
                table += "<td>" + data[i].minutesSlept + "</td>"
                table += "<td>" + data[i].deepSleep + "</td>"
                table += "<td>" + data[i].lightSleep + "</td>"
                table += "</tr>";
            }

            var chBarClasses = document.getElementById("lineChart");

            var chart1 = new Chart(chBarClasses, {
                type: 'line',
                data: data[4],
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'Line Chart'
                        }
                    }
                },
            });

            document.getElementById("overviewSum").innerHTML = "Tage mit zu wenig Schlaf: " + warning;
            document.getElementById("testField").innerHTML = table;
        })
}