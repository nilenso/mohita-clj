(ns ttt.console
  (:gen-class)
  (:require
    [failjure.core :as f]
    [ttt.error-handlers :as eh]
    [ttt.matrix-operations :as mo]
    [ttt.tic-tac-toe :as ttt]))


(def initial-board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])


(def position-board
  [[1 2 3]
   [4 5 6]
   [7 8 9]])


(def position-to-coordinate
  {1 [0 0], 2 [0 1], 3 [0 2],
   4 [1 0], 5 [1 1], 6 [1 2],
   7 [2 0], 8 [2 1], 9 [2 2]})


(def player-order
  (take 9 (cycle ttt/game-pieces-set)))


(defn update-board
  [coordinate game-piece board]
  (assoc-in board coordinate game-piece))


(defn move-valid?
  [coordinate board]
  (= :e (get-in board coordinate)))


(defn place-move-on-board
  [coordinate game-piece board]
  (update-board coordinate game-piece board))


(defn user-input-position
  [game-piece board]
  (println  "Choose position for" game-piece)
  (f/attempt-all [input (eh/valid-parse-int (read-line))
                  pos (eh/in-valid-range input 1 9)
                  coord (get position-to-coordinate pos)]

                 (if (move-valid? coord board)
                   (place-move-on-board coord game-piece board))

                 (f/when-failed [e]
                                (do (println (f/message e))
                                    (user-input-position game-piece board)))))


(defn user-input
  []
  (println "Press 1 to view position board \nPress 2 to make move")
  (f/attempt-all [input (eh/valid-parse-int (read-line))
                  option (eh/in-valid-range  input 1 2)]
                 option
                 (f/when-failed [e]
                                (do (println (f/message e)))
                                (user-input))))


(defn print-winner
  [board player-sequence]
  (if (empty? player-sequence)
    (println "It's a draw")
    (println "Winner is: " (ttt/winner-of-board board))))


(defn play-game
  [board]
  (loop [board           board
         player-sequence player-order]
    (println "Current board:" (mo/matrix->string board))
    (cond
      (ttt/game-over? board player-sequence) (print-winner board player-sequence)
      :else
      (case (user-input)
        1 (do
            (println "\nPosition board" (mo/matrix->string position-board) "\n")
            (recur board player-sequence))
        2 (recur
            (user-input-position (first player-sequence) board)
            (rest player-sequence))))))


(defn -main
  []
  (play-game initial-board))

