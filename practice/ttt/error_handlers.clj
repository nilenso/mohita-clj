(ns ttt.error-handlers
  (:require
    [failjure.core :as f]))


(defn parse-int
  [s]
  (try
    (Integer/parseInt s)
    (catch Exception e
      (assoc (f/fail "Enter an Integer")
             :error e))))


(defn in-range
  [n start end]
  (if (<= start n end)
    n
    (f/fail (str "Enter a number between " start " - " end))))
