# efp-syntaxchecker

## Webservice API

### /api/tasks

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
