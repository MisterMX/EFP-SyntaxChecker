(ns efp_syntaxchecker.core
  (:use compojure.core
        ring.adapter.jetty
        ring.util.response)
  (:require 
    [efp_syntaxchecker.config :as config]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.json :refer :all]
    [ring.middleware.content-type :refer :all]
    [ring.middleware.params :refer :all]
    [ring.util.json-response :refer :all]))

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
  ; Moodle request is application/urlencoded -> data is in :params.
  (POST "/api" {params :params {userId :user_id} :body} (response {:user_id userId :params params}))
  (route/not-found "Page not found"))

;---
;Startup
;---

(def app
  (-> app-routes
    ; (wrap-content-type {:mime-types ["application/json"]})
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
