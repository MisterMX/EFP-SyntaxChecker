(ns efp_syntaxchecker.core
  (:use compojure.core
        ring.adapter.jetty
        ring.util.response)
  (:require 
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.json :refer :all]
    [efp_syntaxchecker.config :as config]))

(defn getText [request]
  (str request " test"))

(defn getTaskList []
  (map 
    (fn [[taskName task]] 
      {
        :name taskName
        :triggers (map (fn [[triggerName trigger]] triggerName ) (get task :triggers))
      })
    config/task-map))

(defroutes app-routes
  (GET "/api/tasks" [] (response (getTaskList)))
  (POST "/api" {body :body} (slurp body))
  (route/not-found "Page not found"))

;---
;Startup
;---

(def app
  (-> app-routes
    wrap-json-params
    wrap-json-body
    wrap-json-response))

(defn start-server []
  (run-jetty app
    { :port 8080
      :ssl? true
      :ssl-port 8081
      :keystore "/opt/certificate/keystore"
      :key-password "efpss17"}))

(defn -main []
  (start-server))
