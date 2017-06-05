(ns efp_syntaxchecker.config
  (:require
      [efp_syntaxchecker.tasks.task1 :as task1]
      [efp_syntaxchecker.tasks.task2 :as task2]))

(def task-map {
  :task1 {
    :name "task1"
    :triggers {
      :trigger1 {:handler task1/task1Handler}
      :trigger2 {:handler task1/task2Handler}}
  }
  :task2 {
    :name "task2"
    :triggers {
      :trigger1 {:handler task2/task1Handler}
      :trigger2 {:handler task2/task2Handler}}
  }})
