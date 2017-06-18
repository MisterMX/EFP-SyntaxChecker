(ns efp_syntaxchecker.tasks.taskutil)

(defn removeJavaComments [text]
  (clojure.string/replace text #"(\/\*((?!\*\/)[\S\s])*\*\/)|(\/\/.*)" ""))

(defn getFileByName [fileName files]
  (some #(= fileName %) files))
