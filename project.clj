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
            [lein-ring-jetty "0.1.0-SNAPSHOT"]]
  :ring {:handler efp_syntaxchecker.core/app}
  :codox
  {:output-path "codox"
   :metadata {:doc/format :markdown}
   :source-uri "http://github.com/weavejester/compojure/blob/{version}/{filepath}#L{line}"}
  :aliases
  {"test-all" ["with-profile" "default:+1.8" "test"]}
  :profiles
  {
    :dev {
      :jvm-opts ^:replace []
      :dependencies [[ring/ring-mock "0.3.0"]
                    [criterium "0.4.4"]
                    [javax.servlet/servlet-api "2.5"]]}
    :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
    :debug-repl {
      :resource-paths [#=(eval (System/getenv "PATH_TO_TOOLS_JAR"))]
      :repl-options {:nrepl-middleware [debug-middleware.core/debug-middleware]}
      :dependencies [[debug-middleware #=(eval (System/getenv "DEBUG_MIDDLEWARE_VERSION"))]]}})
