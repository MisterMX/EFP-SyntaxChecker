# Dokumentation für EFP-SyntaxChecker

Philipp Minges \
Maximilian Blatt 1423983

## Struktur

Das Projekt ist aufgeteilt in einen Webservice (Clojure) und einen Webclient (JavaScript) aufgeteilt. Es ist eine integration in Moodle möglich, beide können jedoch unabhängig davon betrieben werden.

### Client

Der Client wird über einen NodeJs Webserver ausgeliefert. Da die Details seiner Implementierung nicht Bestandteil der Aufgabe ist, wird dieser auch nicht erläutert.

Um den Client vorzubereiten benötigt man ein installiertes NodeJs und Bower mit denen die einzelnen Packages installiert werden. Der Server wird dann gestartet mit
```
npm start
```

### Webservice

Der in Clojure progammierte Webservice ist die zentrale Komponente des Projekts. Hier folgt eine genauere Erläuterung seiner Funktionen.

#### Start

Der Start des Webservice erfolgt über die Kommandozeile mit 

```
lein run
```

#### Webservice API

Dieser Abschnitt enthält die Dokumentation der REST-Schnittstelle des Webservice.

##### GET ```/api/tasks```

Gets a list of all task and there triggers.

Liefert eine Liste aller Aufgaben und ihrer Fehlerklassen, die vom Webservice angeboten werden.

Response Format:
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

##### POST ```/api/execute?lis_outcome_service_url=<value>&lis_result_sourcedid=<value>```

Führt einen gegeben Task aus.

Die URL-Parameter sind optional. Bei ihrem Vorhandensein wird die Bewertung (Prozent der abgeschlossenen Aufgaben) zusätzlich an Moodle übermittelt.

Request Format:
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

##### POST ```/lti/launch```

Ziel URL für den Launch Request von Moodle.

Die Antwort enthält lediglich eine Weiterleitung an den Client.

#### Fehlerklassen

Dieser Abschnitt definiert die einzelnen Tasks und die Fehlerklassen, die vom Webservice angeboten und in der Datei ```config.clj``` definiert werden. Alle Fehlerklassen werden mit Hilfe von Regex überprüft.

##### Aufgabe 1

* exercise1.trigger.className: \
    Der Klassenname muss ```FileServer``` heißen.

* exercise1.trigger.packageName \
    Die Klasse muss im Paket ```var``` oder einem Unterpaket liegen.

* exercise1.trigger.properties \
    Die Datei ```jndi.properties``` mit dem Eintrag ```queue.files``` muss existieren.

* exercise1.trigger.textMessage \
    In der Klasse ```FileServer``` muss die Klasse ```TextMessage``` verwendet werden.

##### Aufgabe 2

* exercise2.trigger.chatclient \
    Die Klasse ```ChatClient``` muss im Paket ```var.rmi.chat``` liegen.

* exercise2.trigger.chatserver \
    Die Klasse ```ChatServer``` muss im Paket ```var.rmi.chat``` liegen.

* exercise2.trigger.conf \
    Die Klasse/Interface  ```Conf``` muss das Feld  ```static final CHATSERVICE``` besitzen.

* exercise2.trigger.useconf \
    ```Conf.CHATSERVICE``` muss in der Klasse  ```ChatClient``` verwendet werden.

