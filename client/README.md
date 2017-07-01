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

Uploads the Data in the following JSON format
```
{
    taskName: <taskName>,
    files: [
        <filename>: <content>,
        <content> : <filecontent>
        ...
    ]
}
```

Visualization of response
```
function displayValidationResponse(data)
```