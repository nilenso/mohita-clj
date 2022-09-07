(ns ttt.user-input
  (:require
    [failjure.core :as f]))

(def position-to-coordinate
  {1 [0 0], 2 [0 1], 3 [0 2],
   4 [1 0], 5 [1 1], 6 [1 2],
   7 [2 0], 8 [2 1], 9 [2 2]})

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

(defn position [game-piece]
  (println  "Choose position for" game-piece)
  (f/attempt-all [input (parse-int (read-line))
                  pos (in-range input 1 9)]
                 (get position-to-coordinate pos)

                 (f/when-failed [e]
                                (do (println (f/message e))
                                    (position game-piece)))))

(defn command []
  (println "Press 1 to view position board \nPress 2 to make move \nPress 3 to quit")
  (f/attempt-all [input (parse-int (read-line))
                  option (in-range input 1 3)]
                 option
                 (f/when-failed [e]
                                (do (println (f/message e)))
                                (command))))
