(ns efp_syntaxchecker.views.main
  (:use [hiccup core page]))

(defn index-page []
  (html5
    [:head
      [:title "EFP-SyntaxChecker"]]
    [:body
      [:h1 "EFP-SyntaxChecker"]
      [:form {:action "http://localhost:8080/upload" :method "post" :enctype "multipart/form-data"}
        [:input {:name "file" :type "file"}]
        [:input {:type "submit" :name "submit" :value "submit"}]]]))