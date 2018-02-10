(defproject todomvc-re-frame "0.10.1"
  :dependencies [[org.clojure/clojure        "1.9.0"]
                 [org.clojure/clojurescript  "1.9.908"]
                 [reagent "0.8.0-alpha2" :exclusions [[cljsjs/react] [cljsjs/react-dom]]]
                 [cljsjs/react-dom "16.2.0-3"]
                 [re-frame "0.10.3" :exclusions [reagent]]
                 [binaryage/devtools "0.9.4"]
                 [secretary "1.2.3"]]


  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-figwheel "0.5.15-SNAPSHOT"]]

  :hooks [leiningen.cljsbuild]

  :profiles {:dev  {:dependencies [[day8.re-frame/trace "0.1.20-SNAPSHOT"]]
                    :cljsbuild
                    {:builds {:client {:compiler {:asset-path           "js"
                                                  :optimizations        :none
                                                  :source-map           true
                                                  :source-map-timestamp true
                                                  :main                 "todomvc.core"
                                                  :closure-defines      {"re_frame.trace.trace_enabled_QMARK_" true}
                                                  :preloads [day8.re-frame.trace.preload]}
                                       :figwheel {:on-jsload "todomvc.core/main"}}}}}

             :prod {:cljsbuild
                    {:builds {:client {:compiler {:optimizations :advanced
                                                  :elide-asserts true
                                                  :pretty-print  false}}}}}}

  :figwheel {:server-port 3450
             :repl        false}


  :clean-targets ^{:protect false} ["resources/public/js" "target"]

  :cljsbuild {:builds {:client {:source-paths ["src" #_ "../../src" "checkouts/re-frame-trace/src"]
                                :compile-paths ["src"]
                                :compiler     {:output-dir "resources/public/js"
                                               :output-to  "resources/public/js/client.js"}}
                       :dev    {:source-paths ["src" #_"../../src" "checkouts/re-frame-trace/src"]
                                :compiler     {:output-dir "resources/public/js1"
                                               :output-to  "resources/public/js1/client.js"}}}})
