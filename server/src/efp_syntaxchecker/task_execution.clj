(ns efp_syntaxchecker.task-execution
  (:require [efp_syntaxchecker.config-util :as config-util]))

(defn executeTrigger [trigger triggerName files]
  {
    :name triggerName
    :success? true
  })

(defn executeTask [task files]
  (map
    (fn [[triggerName trigger]] (executeTrigger trigger triggerName files))
    (:triggers task)))

(defn executeTaskRequest [requestBody]
  (executeTask
    (config-util/getTask (:taskName requestBody))
    (:files requestBody)))
