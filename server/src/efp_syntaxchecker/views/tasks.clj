(ns efp_syntaxchecker.views.tasks
  (:use [hiccup core page])
  
  (:require [efp_syntaxchecker.config-util :as config-util]))

(defn tasks-page []
  (html5
    [:head
      [:title "EFP-SyntaxChecker - Tasks"]]
    [:body
      [:h1 "EFP-SyntaxChecker - Tasks"]
      [:div {:class "mainContent"}
      [:p (config-util/getTaskList)]]]))