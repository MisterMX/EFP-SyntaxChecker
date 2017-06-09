(ns efp_syntaxchecker.task-execution
  (:require [efp_syntaxchecker.config-util :as config-util]))

(defn executeTrigger [trigger triggerName files]
  (if ((:handler trigger) files)
    {
      :name triggerName
      :success? true
    }
    {
      :name triggerName
      :success? false
      :message (:errorMsg trigger)
    }))

(defn executeTask [task files]
  (map
    (fn [[triggerName trigger]] (executeTrigger trigger triggerName files))
    (:triggers task)))

(defn executeTaskRequest [requestBody]
  (executeTask
    (config-util/getTask (:taskName requestBody))
    (:files requestBody)))
