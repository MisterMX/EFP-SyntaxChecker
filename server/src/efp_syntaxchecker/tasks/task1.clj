; Task handlers for EFP "Augabe 1"

(ns efp_syntaxchecker.tasks.task1
  (:require
    [efp_syntaxchecker.tasks.taskutil :as taskutil]))

(defn taskClassName [files]
   (re-matches
    (taskutil/removeJavaComments 
      (taskutil/getFileByName "FileServer.java" files))
    #"class\s+FileServer"))

(defn packageName [files]
   (re-matches
    (taskutil/removeJavaComments 
      (taskutil/getFileByName "FileServer.java" files))
    #"package\s+var"))
