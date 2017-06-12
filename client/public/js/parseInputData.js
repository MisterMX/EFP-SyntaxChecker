var jsonUpload = {
    taskName: "",
    files: []
};

function readTasks() {
    var list = document.getElementById("taskSelector");

    $.getJSON("http://localhost:8080/api/tasks", function (data) {
        for (var i = 0; i < data.length; i++) {
            var opt = data[i].name;
            var li = document.createElement("li");
            var link = document.createElement("a");
            var text = document.createTextNode(opt);

            link.appendChild(text);
            link.onclick = function () {
                createTriggerTable(opt);
                // reset the field
                jsonUpload.taskName = opt;
            };
            li.appendChild(link);
            list.appendChild(li);
        }
    })
}

function createTriggerTable(triggerName) {
    var body = "<table class='table table-hover' id='triggerTable'>";
    body += "<thead>";
    body += "<tr><th>Triggername</th><th>Message</th><th>Success</th></tr>";
    body += "</thead>";
    body += "<tbody>";
    // parse JSON with jQuery
    $.getJSON("http://localhost:8080/api/tasks", function (data) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].name == triggerName) {
                for (var j = 0; j < data[i].triggers.length; j++) {
                    body += "<tr>";
                    // TODO - triggername not working here
                    body += "<td>" + data[i].triggers[j] + "</td>";
                    body += "<td> - </td>";
                    body += "<td><img src='img/icons/working.png' width='50px' height='50px'</td>";
                    body += "</tr>";
                }
            }
        }

        body += "</tbody>";
        body += "</table>";
        document.getElementById("triggerTable").innerHTML = body;
    })
}

function readMultipleFiles(evt) {
    // reset the field
    jsonUpload.files = [];
    // retrieve the files from input
    var files = evt.target.files;
    if (files) {
        for (var i = 0, f; f = files[i]; i++) {
            var r = new FileReader();
            r.onload = (function (f) {

                return function (e) {
                    // create JSON object
                    jsonUpload.files.push({
                        "filename": f.name,
                        "content": e.target.result
                    });
                };
            })(f);
            r.readAsText(f);
        }
    } else {
        alert("Failed to load files");
    }
}

function uploadJSON() {
    // alert(JSON.stringify(jsonUpload));
    // upload data to server
    $.ajax({
        url: 'http://localhost:8080/api/execute',
        type: 'POST',
        data: JSON.stringify(jsonUpload),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (data) {

            // alert(JSON.stringify(data));
            // get the result from server in callback
            displayValidationResponse(data);
        },
    });
}

function displayValidationResponse(data) {
    var body = "<table class='table table-hover' id='triggerTable'>";
    body += "<thead>";
    body += "<tr><th>Triggername</th><th>Message</th><th>Success</th></tr>";
    body += "</thead>";
    body += "<tbody>";

    for (var i = 0; i < data.length; i++) {
        body += "<tr>";
        body += "<td>" + data[i].name + "</td>";
        body += "<td>" + data[i].message + "</td>";

        if (data[i]['success?']) {
            body += "<td><img src='img/icons/success.png' width='50px' height='50px'</td>";
        } else {
            body += "<td><img src='img/icons/failed.png' width='50px' height='50px'</td>";
        }

        body += "</tr>";
    }

    body += "</tbody>";
    body += "</table>";
    document.getElementById("triggerTable").innerHTML = body;
}
