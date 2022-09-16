(ns ttt.game
  (:require
    [failjure.core :as f]
    [ttt.board :as board]
    [ttt.user-input :as user-input]))


(def player-order
  (take 9 (cycle board/game-player-set)))


(defn over?
  [board player-sequence]
  (or (board/winner board)
      (empty? player-sequence)))


(defn process-parsed-command
  [parsed-command board player-sequence]
  (case parsed-command
    "h" (do
          (println "\nPosition board" board/positions-string "\n")
          [board player-sequence])

    "q" [nil player-sequence]

    (let [player (first player-sequence)]
      (if (board/empty-coordinate? board parsed-command)
        [(board/update board parsed-command player) (rest player-sequence)]
        (do (println "Enter an empty position")
            [board player-sequence])))))


(defn process-command
  [command board player-sequence]
  (f/attempt-all [valid-command (user-input/valid-command? command)
                  parsed-command (user-input/parse-command valid-command)]
                 (process-parsed-command parsed-command board player-sequence)
                 (f/when-failed [e]
                                (println (f/message e))
                                [board player-sequence])))


(defn play
  [board]
  (println "Enter a number between 1-9 to make a move \nEnter h to view position board \nEnter q to quit")
  (loop [board           board
         player-sequence player-order]
    (cond
      (or (nil? board))
      (println "Bye bye!")

      (over? board player-sequence)
      (if-let [winner (board/winner board)]
        (println "Winner is" (name winner))
        (println "It's a draw"))

      :else
      (do (println "Current board:" (board/to-string board)
                   "\nPlayer is" (name (first player-sequence)))
          (let [command (read-line)
                [new-board new-player-sequence] (process-command command board player-sequence)]
            (recur new-board new-player-sequence))))))
