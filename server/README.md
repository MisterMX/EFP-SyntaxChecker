# efp-syntaxchecker

## Webservice API

### GET

#### /

Displays the Menu of the Webapp

#### /api/upload

Provides a Interface for uploading Files to the Syntax Checker

#### /api/tasks

Gets a list of all task and there triggers.

Format:
`
{
    name: <taskname>,
    triggers: {
        name: <triggername>
    }
}
`

### POST

#### /api/upload
