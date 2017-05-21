(ns efp_syntaxchecker.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

(defn getText
  [request]
  (str request "test"));

(defroutes app
  (GET "/" [request] (getText [request]))
  (route/not-found "<h1>Page not found</h1>"))
