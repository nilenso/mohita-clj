(ns ttt.error-handlers
  (:require [failjure.core :as f]))

(defn valid-parse-int
  [s]
  (try
    (Integer/parseInt s)
    (catch Exception e
      (assoc
       (f/fail (str e))
       :error e))))

(defn valid-range
  [n start end]
  ())