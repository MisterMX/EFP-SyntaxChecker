# efp-syntaxchecker

## Webservice Client

### GET /api/tasks

Used to create a Table for all possible Tasks and their triggers.
```
function createTasks()
```

### POST /api/execute

Executes a given task.
```
function uploadJSON()
```

## TODO/Bugs

/api/execute request Body needs two arrays. Otherwise the File Upload needs to be done for every task.

Request body format:
```
{
    tasks: [
        <taskName>: <taskName>,
        ...
    files: [
        <filename>: <content>,
        ...
    ]
}