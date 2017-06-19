; Triggers for EFP "Augabe 2"

(ns efp_syntaxchecker.tasks.task2
  (:require
    [efp_syntaxchecker.tasks.taskutil :as taskutil]))

(defn triggerChatClient [files]
  (boolean
    (re-find
      #"package\s+var\.rmi\.chat[\s\S]+class\s+ChatClient"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "ChatClient.java" files))))))

(defn triggerChatServer [files]
  (boolean
    (re-find
      #"package\s+var\.rmi\.chat[\s\S]+class\s+ChatServer"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "ChatServer.java" files))))))

(defn triggerConf [files]
  (boolean
    (re-find
      #"public\s+static\s+final\s+String\s+CHATSERVICE"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "Conf.java" files))))))

(defn triggerUseConf [files]
  (boolean
    (re-find
      #"Conf\.CHATSERVICE"
      (taskutil/removeJavaComments 
        (:content (taskutil/getFileByName "ChatClient.java" files))))))
