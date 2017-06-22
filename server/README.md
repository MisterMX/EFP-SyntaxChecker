# efp-syntaxchecker

## Webservice API

### GET /api/tasks

Gets a list of all task and there triggers.

No request body is required.

Response body format:
```
[
    {
        "name": <taskname>,
        "triggers": [
            {
                "name": <triggername>
            },
            ...
        ]
    },
    ...
]
```

### POST /api/execute

Execute a given task.

Request body format:
```
{
    taskName: <taskName>,
    files: [
        <filename>: <content>,
        ...
    ]
}
```

Response body format:
```
[
    {
        "name": <triggername>,
        "success": <true|false>,
        "message": <string>
    },
    ...
]
```
