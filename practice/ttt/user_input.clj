(ns ttt.user-input
  (:require
    [failjure.core :as f]
    [ttt.board :as board]))


(def extra-commands #{"q" "h"})


(def valid-commands
  (->> board/valid-positions
       (map str)
       (concat extra-commands)
       set))


(defn valid-command?
  [command]
  (if (contains? valid-commands command)
    command
    (f/fail "Please enter a valid input")))


(defn parse-command
  [command]
  (if (contains? extra-commands command)
    command
    (get board/position-to-coordinate (Integer/parseInt command))))


