# efp-syntaxchecker

## Data Upload

The Data upload is handled by a HTML input form. The uploaded multipart/form-data will be wrapped in JSON format before processing in backend
```
function readMultipleFiles(evt)
```

## REST Interface

### GET /api/tasks

Used to read the tasks from the backend and create the trigger table from it.
```
function readTasks()
```
Creates the HTML Table from the selected Task.
```
function createTriggerTable(triggerName)
```

### POST /api/execute

Executes a given task.
```
function uploadJSON()
```

Uploads the Data in the following JSON format. Example:
```
{ "taskName" : "task2",
  "files" : [ 
    { "filename" : "project.clj",
      "content" : "(defproject my-website \"0.1.0-SNAPSHOT\"\n
        :description \"FIXME: write this!\"\n
        :dependencies [[org.clojure/clojure \"1.4.0\"]\n
        [noir \"1.3.0-beta3\"]]\n
        :main my-website.server)\n
        \n
        "}]}
```

Visualization of the response is done in
```
function displayValidationResponse(data)
```

## Bugs/Todo

- HTTPS Webserver for moodle integration

GET Method http://localhost:8080/api/tasks does not give the array of trigger and their names.
```
[{"name":"task1","triggers":["triggers"]},{"name":"task2","triggers":["triggers"]}]
```
