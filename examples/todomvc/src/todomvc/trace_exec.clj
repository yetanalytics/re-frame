(ns todomvc.trace-exec
  (:require [re-frame.trace :as trace :include-macros true]))


;;




(defmacro trace-exec
  "Evaluates exprs in a context in which *print-fn* is bound to .append
  on a fresh StringBuffer.  Returns the string created by any nested
  printing calls."
  ;; From with-out-str
  [& body]
  `(let [sb# (js/goog.string.StringBuffer.)]
     (try
       (binding [cljs.core/*print-newline* true
                 cljs.core/*print-fn*      (fn [x#] (.append sb# x#))]
         ~@body)
       (finally
         (let [code# (get-in trace/*current-trace* [:tags :code-lines] [])]
           (trace/merge-trace! {:tags {:code-lines (conj code# (cljs.core/str sb#))}}))))))

;; pprint-code
;; fipp - code
;; cljfmt
;; parinfer?
