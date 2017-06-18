; Task handlers for EFP "Augabe 1"

(ns efp_syntaxchecker.tasks.task1
  (:require
    [efp_syntaxchecker.tasks.taskutil :as taskutil]))

(defn taskClassName [files]
  (boolean
    (re-find
      #"class\s+FileServer"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "FileServer.java" files))))))

(defn packageName [files]
  (boolean
    (re-find
      #"package\s+var"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "FileServer.java" files))))))
