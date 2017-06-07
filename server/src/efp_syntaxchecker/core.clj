(ns efp_syntaxchecker.core
  (:use [compojure.core]
        [ring.middleware.params]
        [ring.middleware.multipart-params]
        [ring.adapter.jetty]
        [ring.util.response]
        [clojure.java.io]
        [efp_syntaxchecker.views.upload]
        [efp_syntaxchecker.views.tasks]
        [efp_syntaxchecker.views.main])

  (:require 
    [efp_syntaxchecker.config-util :as config-util]
    [efp_syntaxchecker.task-execution :as task-execution]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.json :refer :all]
    [ring.middleware.params :refer :all]
    [ring.middleware.multipart-params :refer :all]
    [ring.util.json-response :refer :all]
    [hiccup.middleware :only (wrap-base-url)]))


(defroutes app-routes
  (GET "/" [] (index-page))
  (GET "/api/upload" [] (upload-page))
  (GET "/api/tasks" [] (tasks-page))
  
  (POST "/api/execute" {body :body} (response (task-execution/executeTaskRequest body)))
  ; Moodle request is application/urlencoded -> data is in :params.
  (POST "/api" {params :params {userId :user_id} :body} (response {:user_id userId :params params}))

  ; HTTP-Form upload is multipart/form-data
  ; params enthält Map des Uploads - Key = "file" -> 
  ; {file [{:filename Customer.java, :content-type text/x-java, :tempfile #object[java.io.File 0x25641dd9 /tmp/ring-multipart-8681951062682279797.tmp], :size 1567}]}
  (POST "/api/upload" {params :params}
    (println params)
    (let [file (get params "file")]
      (response (get file :tempfile))))

  (route/not-found "Page not found"))
;---
;Startup
;---

(def app
  (-> app-routes
    wrap-params
    wrap-multipart-params
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
