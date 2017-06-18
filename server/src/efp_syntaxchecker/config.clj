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
        :handler task1/taskClassName
        :errorMsg "Class name must be 'FileServer'."}
      :packageName {
        :handler task1/packageName
        :errorMsg "Class must be in package 'var'."}}
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
