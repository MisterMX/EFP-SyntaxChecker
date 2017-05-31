(ns efp_syntaxchecker.core
  (:use compojure.core
        ring.adapter.jetty)
  (:require [[compojure.route :as route]
             [ring.middleware.json :refer [wrap-json-response]]]))

(defn getText [request]
  (str request " test"))

(defroutes app-routes
  (POST "/api" {body :body} (slurp body))
  (route/not-found "Page not found"))

(def app
  (-> app-routes
    wrap-json-params))

(defn start-server []
  (run-jetty app-routes
    { :port 8080
      :ssl? true
      :ssl-port 8081
      :keystore "/opt/certificate/keystore"
      :key-password "efpss17"}))

(defn -main []
  (start-server))
