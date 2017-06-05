(ns efp_syntaxchecker.core
  (:use compojure.core
        ring.adapter.jetty
        ring.util.response)
  (:require 
    [efp_syntaxchecker.config-util :as config-util]
    [efp_syntaxchecker.task-execution :as task-execution]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.json :refer :all]
    [ring.middleware.params :refer :all]
    [ring.util.json-response :refer :all]))

(defroutes app-routes
  (GET "/api/tasks" [] (response (config-util/getTaskList)))
  (POST "/api/execute" {body :body} (response (task-execution/executeTaskRequest body)))
  ; Moodle request is application/urlencoded -> data is in :params.
  (POST "/api" {params :params {userId :user_id} :body} (response {:user_id userId :params params}))
  (route/not-found "Page not found"))

;---
;Startup
;---

(def app
  (-> app-routes
    (wrap-json-body {:keywords? true :bigdecimals? true})
    (wrap-params)
    (wrap-json-response)))

(defn start-server []
  (run-jetty app
    { :port 8080
      :ssl? true
      :ssl-port 8081
      :keystore "/opt/certificate/keystore"
      :key-password "efpss17"}))

(defn -main []
  (start-server))
