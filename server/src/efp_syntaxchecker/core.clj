(ns efp_syntaxchecker.core
  (:use compojure.core
        ring.adapter.jetty
        ring.util.response)
  (:require 
    [efp_syntaxchecker.lti :as lti]
    [efp_syntaxchecker.config :as config]
    [efp_syntaxchecker.config-util :as config-util]
    [efp_syntaxchecker.task-execution :as task-execution]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.json :refer :all]
    [ring.middleware.params :refer :all]
    [ring.util.json-response :refer :all]
    [ring.middleware.cors :refer [wrap-cors]]))

(defroutes app-routes
  (GET "/api/tasks" [] (response (config-util/getTaskList)))
  (POST "/api/execute" {body :body params :params} (response (task-execution/executeTaskRequest body params)))
  ; Moodle request is application/urlencoded -> data is in :params.
  ; Use 'get' to get an entry from params.
  (POST "/lti/launch" {params :params headers :headers} (lti/createRedirect params headers))
  (route/not-found "Page not found"))

;---
;Startup
;---



(def app
  (-> app-routes
    (wrap-json-body {:keywords? true :bigdecimals? true})
    (wrap-params)
    (wrap-json-response)
    (wrap-cors app-routes #".*")
    (wrap-cors app-routes identity)))

(defn start-server []
  (run-jetty app
    { :port 8080
      :ssl? true
      :ssl-port 8081
      :keystore "/opt/certificate/keystore"
      :key-password "efpss17"}))

(defn -main []
  (start-server))
