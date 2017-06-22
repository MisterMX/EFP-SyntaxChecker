(ns efp_syntaxchecker.config-util
  (:require [efp_syntaxchecker.config :as config]))

(defn getTaskList []
  (map
    (fn [task] {
      :name (:id task)
      :triggers (map 
        (fn [trigger] {
          :name (:id trigger)
          :description (:description trigger)
        })
        (:triggers task))
    })
    config/task-map))

(defn getTask [name]
  ((fn [task]
    (if (nil? task)
      (throw (Exception. (str "No task with name " name)))
      task))
    (some #(when (= name (:id %)) %) config/task-map)))
