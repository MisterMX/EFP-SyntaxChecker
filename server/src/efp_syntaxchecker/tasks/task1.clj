; Task handlers for EFP "Augabe 1"

(ns efp_syntaxchecker.tasks.task1
  (:require
    [efp_syntaxchecker.tasks.taskutil :as taskutil]))

(defn triggerClassName [files]
  (boolean
    (re-find
      #"class\s+FileServer"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "FileServer.java" files))))))

(defn triggerPackageName [files]
  (boolean
    (re-find
      #"package\s+var"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "FileServer.java" files))))))

(defn triggerProperties [files]
  (boolean
    (re-find
      #"queue.files[ \t]+=[ \t]+\w+"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "jndi.properties" files))))))

(defn triggerTextMessage [files]
  (boolean
    (re-find
      #"TextMessage"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "FileServer.java" files))))))
