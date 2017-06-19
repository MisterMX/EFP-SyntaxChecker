(ns efp_syntaxchecker.task-execution
  (:require [efp_syntaxchecker.config-util :as config-util]))

(defn executeTrigger [trigger files]
  (if ((:handler trigger) files)
    {
      :name (:id trigger)
      :success? true
      :message (:description trigger)
    }
    {
      :name (:id trigger)
      :success? false
      :message (:description trigger)
    }))

(defn executeTask [task files]
  (map
    #(executeTrigger % files)
    (:triggers task)))

(defn executeTaskRequest [requestBody]
  (executeTask
    (config-util/getTask (:taskName requestBody))
    (:files requestBody)))
