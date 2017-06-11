var jsonUpload = {
    tasks: [],
    files: []
};

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

function createTasks() {
    var body = "<table class='table table-hover' id='tasksTable'>";
    body += "<thead>";
    body += "<tr><th>Taskname</th><th>Info</th><th>Success</th><th>Validate</th></tr>";
    body += "</thead>";

    body += "<tbody>";
    // parse JSON with jQuery
    $.getJSON("http://localhost:8080/api/tasks", function(data) {
        for(var i = 0; i < data.length; i++) {
            body += "<tr>";
            body += "<td>" + data[i].name + "</td>";
            body += "<td>" + data[i].triggers[0].name + "</td>";
            body += "<td><img src='img/icons/working.png' width='50px' height='50px'</td>";
            body += "<td><input type='checkbox' name='task' value='" + data[i].name + "'></td>";
            body += "</tr>";
        }
        
        body += "</tbody>";
        document.getElementById("tasksTable").innerHTML = body;
   })
}

function uploadJSON() {
    // reset the field
    jsonUpload.tasks = [];
    // check which tasks should be executed and add them to JSON
    var checkboxes = document.getElementsByName('task');
    var selected = [];
    
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selected.push(checkboxes[i].value);
        }
    }
    jsonUpload.tasks.push({ 
                        "taskName" : selected
                    });
    
    alert(JSON.stringify(jsonUpload));
    
    // upload data to server
    // TODO - gives 500 taskname not found
    $.ajax({
            url: 'http://localhost:8080/api/execute',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                
                // TODO - get the result from server in callback
                
                alert(http.responseText);
            },
            data: jsonUpload
    });
}