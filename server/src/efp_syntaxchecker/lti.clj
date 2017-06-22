(ns efp_syntaxchecker.lti
  (:import [org.imsglobal.pox IMSPOXRequest]
           [org.apache.http.impl.client DefaultHttpClient]
           [org.apache.commons.io IOUtils])
  (:use ring.util.response)
  (:require
    [efp_syntaxchecker.config :as config]))

(defn calculateGrade [executionResult]
  (format
    "%1$.1f"
    (/ 
      (float (count (filter #(:success? %) executionResult)))
      (float (count executionResult)))))

(defn sendOutcome [executionResult outcome-service-url result-sourcedid]
  ; Send outcome request to Moodle and print response.
  (println
    (IOUtils/toString
      (.getContent
        (.getEntity
          (.execute
            (DefaultHttpClient.) 
            (IMSPOXRequest/buildReplaceResult
              outcome-service-url
              "efpss17"
              "efpss17"
              result-sourcedid
              "1.0"
              nil
              false)))))))

(defn createRedirect [params headers]
  (redirect
    (str
      config/redirect-url-base
      "?lis_outcome_service_url=" (get params "lis_outcome_service_url") "&"
      "lis_result_sourcedid=" (get params "lis_result_sourcedid") "&"
      "lis_person_name_given=" (get params "lis_person_name_given") "&"
      "lis_person_name_family=" (get params "lis_person_name_family"))))
