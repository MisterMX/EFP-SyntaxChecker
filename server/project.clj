(defproject compojure "0.1.0-SNAPSHOT"
  :description "A concise routing library for Ring"
  :url "https://github.com/weavejester/compojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.macro "0.1.5"]
                 [clout "2.1.2"]
                 [medley "1.0.0"]
                 [ring "1.6.1"]
                 [ring/ring-codec "1.0.1"]
                 [ring/ring-jetty-adapter "1.6.1"]
                 [compojure "1.6.0"]]
  :plugins [[lein-codox "0.10.3"]
            [lein-ring "0.12.0"]
            [lein-run "1.0.0"]
            [lein-ring-jetty "0.1.0-SNAPSHOT"]]
  :main efp_syntaxchecker.core
  :run-aliases {:server [efp_syntaxchecker.core -main]}
  :codox
  {:output-path "codox"
   :metadata {:doc/format :markdown}
   :source-uri "http://github.com/weavejester/compojure/blob/{version}/{filepath}#L{line}"})
