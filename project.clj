(defproject kwrooijen/stdlib "0.0.1-SNAPSHOT"
  :description "Babashka compatible helper functions"
  :url "https://github.com/kwrooijen/stdlib"
  :license {:name "MIT"}
  :dependencies []
  :plugins [[lein-cloverage "1.1.2"]
            [lein-codox "0.10.7"]
            [lein-ancient "0.6.15"]]

  :repositories [["public-github" {:url "git://github.com"}]
                 ["private-github" {:url "git://github.com" :protocol :ssh}]]

  :codox {:doc-files ["README.md"]
          :output-path "docs/"
          :html {:namespace-list :nested}
          :metadata {:doc/format :markdown}
          :themes [:rdash]}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.1"]
                                  [orchestra "2020.09.18-1"]
                                  [codox-theme-rdash "0.1.2"]]}
             :test {:dependencies [[orchestra "2020.09.18-1"]]}}
  :deploy-repositories [["releases" :clojars]])
