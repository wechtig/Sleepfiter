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
                "            <th scope=\"col\">REM Time</th>" +
                "            <th scope=\"col\">Waked Up</th>" +

                "        </tr>" +
                "    </thead>";

            var minutesSleptArray = [];
            var deepSleepArray = [];
            var day = [];
            var sumLight = 0;
            var sumDeep = 0;

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
                table += "<td>" + data[i].timeRem + "</td>"
                table += "<td>" + data[i].minutesWakedUp + "</td>"
                table += "</tr>";

                minutesSleptArray.push(data[i].minutesSlept)
                deepSleepArray.push(data[i].deepSleep)
                day.push(i)

                sumLight = sumLight + data[i].lightSleep;
                sumDeep = sumDeep + data[i].deepSleep;
            }

           var ctx = document.getElementById("linechart");
           var ctxPie = document.getElementById("piechart");

            const config = {
                data: {
                    labels: day,
                    datasets: [{
                        label: "Sleep Time",
                        data:minutesSleptArray,
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: "rgba(75,192,192,0.4)",
                        borderColor: "rgba(75,192,192,1)",
                        borderDash: [],
                        borderDashOffset: 0.0,
                        pointBorderColor: "rgba(75,192,192,1)",
                        pointBackgroundColor: "#fff",
                        pointBorderWidth: 1,
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "rgba(75,192,192,1)",
                        pointHoverBorderColor: "rgba(220,220,220,1)",
                        pointHoverBorderWidth: 2,
                        pointRadius: 5,
                        pointHitRadius: 10,

                    },{
                        label: "Deep Sleep Time",
                        data:deepSleepArray,
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: "red",
                        borderColor: "red",
                        borderDash: [],
                        borderDashOffset: 0.0,
                        pointBorderColor: "red",
                        pointBackgroundColor: "#fff",
                        pointBorderWidth: 1,
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "red",
                        pointHoverBorderColor: "red",
                        pointHoverBorderWidth: 2,
                        pointRadius: 5,
                        pointHitRadius: 10,
                    }]
                }
            };
            var barColors = [
                "#b91d47",
                "#00aba9",
            ];

            var configPie = {
                data: {
                    labels: ["Sumlight", "SumDeep"],
                    datasets: [{
                        backgroundColor: barColors,
                        data: [sumLight, sumDeep]
                    }]
                }
            }

            new Chart.Line(ctx, config);

            new Chart.Doughnut(ctxPie, configPie);


            document.getElementById("overviewSum").innerHTML = "Tage mit zu wenig Schlaf: " + warning;
            document.getElementById("testField").innerHTML = table;
        })
}