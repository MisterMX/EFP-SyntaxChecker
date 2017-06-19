(ns efp_syntaxchecker.config
  (:require
      [efp_syntaxchecker.tasks.task1 :as task1]
      [efp_syntaxchecker.tasks.task2 :as task2]))

; Define the tasks that are offered by this webservice here:
(def task-map [
  {
    :id "exercise1"
    :name "Aufgabe 1"
    :triggers [
      {
        :id "exercise1.trigger.className"
        :displayName "Class name"
        :handler task1/triggerClassName
        :description "Class name must be 'FileServer'."
      }
      {
        :id "exercise1.trigger.packageName"
        :displayName "Package name"
        :handler task1/triggerPackageName
        :description "Class must be in package 'var' or in one of its subpackages."
      }
      {
        :id "exercise1.trigger.properties"
        :displayName "Package name"
        :handler task1/triggerProperties
        :description "Queue must be configured in file 'jndi.properties' with the key 'queue.files'."
      }
      {
        :id "exercise1.trigger.textMessage"
        :displayName "TextMessage"
        :handler task1/triggerTextMessage
        :description "Server must use the class 'TextMessage'"
      }
    ]
  }
  {
    :id "exercise2"
    :name "Aufgabe 2"
    :triggers [
      {
        :id "exercise2.trigger.textMessage"
        :displayName "TextMessage"
        :handler task2/task1Handler
        :errorMsg "Error"
      }
      {
        :id "exercise2.trigger.textMessage"
        :displayName "TextMessage"
        :handler task2/task2Handler
        :errorMsg "Error"
      }
    ]
  }])
