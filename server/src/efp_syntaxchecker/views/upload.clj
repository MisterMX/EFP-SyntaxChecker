(ns efp_syntaxchecker.views.upload
  (:use [hiccup core page]))

(defn upload-page []
  (html5
    [:head
      [:title "EFP-SyntaxChecker - Upload"]]
    [:body
      [:h1 "EFP-SyntaxChecker - Upload"]
      [:div {:class "mainContent"}
      [:h2 "Please upload File(s)"]
      [:form {:action "http://localhost:8080/api/upload" :method "post" :enctype "multipart/form-data"}
        [:input {:name "file" :type "file" :multiple "multiple"}]
        [:input {:type "submit" :name "submit" :value "submit"}]]]]))