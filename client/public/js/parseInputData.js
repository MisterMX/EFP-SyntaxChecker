var jsonUpload = {
    taskName: "",
    files: []
};

function readTasks() {
    var list = document.getElementById("taskSelector"); 
    
    $.getJSON("http://localhost:8080/api/tasks", function(data) {
        for(var i = 0; i < data.length; i++) {
            var opt = data[i].name;
            var li = document.createElement("li");
            var link = document.createElement("a");
            var text = document.createTextNode(opt);
            
            link.appendChild(text);
            link.href = "#";
            
            link.onclick = function() {
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
    body += "<tr><th>Trigger</th><th>Info</th><th>Success</th></tr>";
    body += "</thead>";
    body += "<tbody>";
    // parse JSON with jQuery
    $.getJSON("http://localhost:8080/api/tasks", function(data) {
        for(var i = 0; i < data.length; i++) {
            if(data[i].name == triggerName) {
                for(var j = 0; j < data[i].triggers.length; j++) {
                    body += "<tr>";
                    body += "<td>" + j+1 + "</td>";
                    body += "<td>" + data[i].triggers[j] + "</td>";
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
        for (var i=0, f; f=files[i]; i++) {
            var r = new FileReader();
            r.onload = (function(f) {
                
                return function(e) {
                    // create JSON object
                    jsonUpload.files.push({ 
                        "filename" : f.name,
                        "content"  : e.target.result 
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
    alert(JSON.stringify(jsonUpload));
    // upload data to server
    $.ajax({
            url: 'http://localhost:8080/api/execute',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                
                // TODO - get the result from server in callback
            },
            data: jsonUpload
    });
}