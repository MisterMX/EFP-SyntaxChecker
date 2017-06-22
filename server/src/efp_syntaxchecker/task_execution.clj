(ns efp_syntaxchecker.task-execution
  (:require [efp_syntaxchecker.config-util :as config-util]
            [efp_syntaxchecker.lti :as lti]))

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

(defn handleExecutionResult [requestParams result]
  (if (get requestParams "lis_outcome_service_url")
    (lti/sendOutcome
      result
      (get requestParams "lis_outcome_service_url")
      (get requestParams "lis_result_sourcedid")))
  result)

(defn executeTaskRequest [requestBody requestParams]
  (handleExecutionResult
    requestParams
    (executeTask
      (config-util/getTask (:taskName requestBody))
      (:files requestBody))))
