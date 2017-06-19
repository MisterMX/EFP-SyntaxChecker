(ns efp_syntaxchecker.config
  (:require
      [efp_syntaxchecker.tasks.task1 :as task1]
      [efp_syntaxchecker.tasks.task2 :as task2]))

; Define the tasks that are offered by this webservice here:
(def task-map [
  {
    :id "exercise1"
    :name "Aufgabe 1"
    :triggers [
      {
        :id "exercise1.trigger.className"
        :displayName "Class name"
        :handler task1/triggerClassName
        :description "Class name must be 'FileServer'."
      }
      {
        :id "exercise1.trigger.packageName"
        :displayName "Package name"
        :handler task1/triggerPackageName
        :description "Class must be in package 'var' or in one of its subpackages."
      }
      {
        :id "exercise1.trigger.properties"
        :displayName "Package name"
        :handler task1/triggerProperties
        :description "Queue must be configured in file 'jndi.properties' with the key 'queue.files'."
      }
      {
        :id "exercise1.trigger.textMessage"
        :displayName "TextMessage"
        :handler task1/triggerTextMessage
        :description "Server must use the class 'TextMessage'"
      }
    ]
  }
  {
    :id "exercise2"
    :name "Aufgabe 2"
    :triggers [
      {
        :id "exercise2.trigger.chatclient"
        :displayName "ChatClient"
        :handler task2/triggerChatClient
        :description "Class 'ChatClient' must be in package 'var.rmi.chat'."
      }
      {
        :id "exercise2.trigger.chatserver"
        :displayName "ChatServer"
        :handler task2/triggerChatServer
        :description "Class 'ChatServer' must be in package 'var.rmi.chat'."
      }
      {
        :id "exercise2.trigger.conf"
        :displayName "Conf"
        :handler task2/triggerConf
        :description "Interface or class 'Conf' must contain the static final field 'CHATSERVICE'."
      }
      {
        :id "exercise2.trigger.useconf"
        :displayName "Use conf in ChatClient"
        :handler task2/triggerUseConf
        :description "'Conf.CHATSERVICE' must be used in class 'ChatClient'."
      }
    ]
  }])
