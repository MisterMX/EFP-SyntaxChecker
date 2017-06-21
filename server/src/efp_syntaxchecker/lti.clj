(ns efp_syntaxchecker.lti
  (:use ring.util.response)
  (:require
    [efp_syntaxchecker.config :as config]))

(defn createRedirect [params headers]
  (println headers)
  (redirect
    (str
      config/redirect-url-base
      "?lis_outcome_service_url=" (get params "lis_outcome_service_url") "&"
      "lis_result_sourcedid=" (get params "lis_result_sourcedid") "&"
      "lis_person_sourcedid=" (get params "list_person_sourcedid") "&"
      "oauth_consumer_key=" (get params "oauth_consumer_key") "&"
      "oauth_signature_method=" (get params "oauth_signature_method") "&"
      "oauth_signature=" (get params "oauth_signature") "&"
      "oauth_timestamp=" (get params "oauth_timestamp") "&"
      "oauth_nonce=" (get params "oauth_nonce") "&"
      "oauth_version=" (get params "oauth_version") "&"
      "oauth_callback=" (get params "oauth_callback"))))
