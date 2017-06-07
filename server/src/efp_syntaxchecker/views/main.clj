(ns efp_syntaxchecker.views.main
  (:use [hiccup core page]))

(defn index-page []
  (html5
    [:head
      [:title "EFP-SyntaxChecker"]]
    [:body
      [:h1 "EFP-SyntaxChecker"]
      [:div {:class "mainContent"}
        [:h2 "Menu:"]
        [:ul
          [:li [:a {:href "http://localhost:8080/api/upload"} "File Upload"]]
          [:li [:a {:href "http://localhost:8080/api/tasks"} "Tasks"]]]]]))