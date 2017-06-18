(ns efp_syntaxchecker.tasks.taskutil)

(defn removeJavaComments [text]
  (clojure.string/replace text #"(\/\*((?!\*\/)[\S\s])*\*\/)|(\/\/.*)" "d"))

(defn getFileByName [fileName files]
  (some #(when (= fileName (:filename %)) %) files))

(def not-nil? (complement nil?))
