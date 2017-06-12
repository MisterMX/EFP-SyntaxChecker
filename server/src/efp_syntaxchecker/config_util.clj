(ns efp_syntaxchecker.config-util
  (:require [efp_syntaxchecker.config :as config]))

(defn getTaskList []
  (map 
    (fn [[taskName task]]
      {
        :name taskName
        :triggers (map (fn [[triggerName trigger]] triggerName) (:triggers task))
      })
    config/task-map))

(defn getTask [name]
  ; (def task (some #(= name %) config/task-map))
  ; (if (nil? task))
  ;   (throw (Exception. (str "No task with name " name)))
  ;   (config/task-map name)))
  (if (nil? (config/task-map (keyword name)))
    (throw (Exception. (str "No task with name " name)))
    (config/task-map (keyword name))))
