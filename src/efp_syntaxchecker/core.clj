(ns efp_syntaxchecker.core
  (:use compojure.core
        ring.adapter.jetty)
  (:require [compojure.route :as route]))

(defn getText [request]
  (str request " test"))

(defroutes app-routes
  (POST "/api" [request] (getText [request]))
  (route/not-found "Page not found"))

(defn start-server [port]
  (run-jetty app-routes
    { :port port
      :ssl? true
      :ssl-port 8081
      :keystore "/opt/certificate/keystore"
      :key-password "efpss17"}))

(defn -main [& [port]]
  (if port
    (start-server (Integer/parseInt port))
    (start-server 8080)))
