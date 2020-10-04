(defproject c "0.0.1-SNAPSHOT"
  :description "Small cli tool for personal ordinary tasks"
  :url "http://github.com/caioguedes/c"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clj-commons/clj-yaml "0.7.0"]
                 [clojure-term-colors "0.1.0"]]
  :main ^:skip-aot c.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
