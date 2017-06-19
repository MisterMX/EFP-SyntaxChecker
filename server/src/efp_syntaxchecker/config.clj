(ns efp_syntaxchecker.config
  (:require
      [efp_syntaxchecker.tasks.task1 :as task1]
      [efp_syntaxchecker.tasks.task2 :as task2]))

; Define the tasks that are offered by this webservice here:
(def task-map {
  :exercise1 {
    :name "Aufgabe 1"
    :triggers {
      :taskClassName {
        :handler task1/triggerClassName
        :errorMsg "Class name must be 'FileServer'."}
      :packageName {
        :handler task1/triggerPackageName
        :errorMsg "Class must be in package 'var' or in one of its subpackages."}
      :properties {
        :handler task1/triggerProperties
        :errorMsg "Queue must be configured in file 'jndi.properties' with the key 'queue.files'."}
      :textMessage {
        :handler task1/triggerTextMessage
        :errorMsg "Server must use the class 'TextMessage'"}
      }
  }
  :task2 {
    :name "task2"
    :triggers {
      :trigger1 {
        :handler task2/task1Handler
        :errorMsg "Error"}
      :trigger2 {
        :handler task2/task2Handler
        :errorMsg "Error"}}
  }})
