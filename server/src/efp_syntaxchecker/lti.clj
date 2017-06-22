(ns efp_syntaxchecker.lti
  (:import [org.imsglobal.pox IMSPOXRequest]
           [org.apache.http.impl.client DefaultHttpClient]
           [org.apache.commons.io IOUtils])
  (:use ring.util.response)
  (:require
    [efp_syntaxchecker.config :as config]))

(defn createRedirect [params headers]
  ; Send outcome request to Moodle and print response.
  (println
    (IOUtils/toString
      (.getContent
        (.getEntity
          (.execute
            (DefaultHttpClient.) 
            (IMSPOXRequest/buildReplaceResult
              (get params "lis_outcome_service_url")
              "efpss17"
              "efpss17"
              (get params "lis_result_sourcedid")
              "1.0"
              nil
              false))))))
  (redirect
    (str
      config/redirect-url-base
      "?lis_outcome_service_url=" (get params "lis_outcome_service_url") "&"
      "lis_result_sourcedid=" (get params "lis_result_sourcedid"))))
