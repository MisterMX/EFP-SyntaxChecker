(ns efp_syntaxchecker.lti
  (:use ring.util.response)
  (:require
    [efp_syntaxchecker.config :as config]))

(defn createRedirect [params]
  (redirect (str
    config/redirect-url-base
    "?lis_outcome_service_url=" (get params "lis_outcome_service_url") "&"
    "lis_result_sourcedid=" (get params "lis_result_sourcedid") "&"
    "lis_person_sourcedid=" (get params "list_person_sourcedid"))))
