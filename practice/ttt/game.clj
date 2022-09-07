(ns ttt.game
  (:require [failjure.core :as f]
            [ttt.board :as board]
            [ttt.user-input :as user-input]))

(def player-order
  (take 9 (cycle board/game-pieces-set)))

(defn over?
  [board player-sequence]
  (or (empty? player-sequence)
      (board/winner board)))

(defn process-command [command board player-sequence]
  (case command
    1 (do
        (println "\nPosition board" board/positions-string "\n")
        [board player-sequence])
    2 (let [player      (first player-sequence)
            input-coord (user-input/position player)]
        (if (board/empty-coordinate? board input-coord)
          [(board/update board input-coord player) (rest player-sequence)]
          [board player-sequence]))
    3 [nil player-sequence]))


(defn play [board]
  (loop [board           board
         player-sequence player-order]
    (println "Current board:" (board/to-string board))
    (cond
      (or (nil? board))
      (println "Bye bye!")

      (over? board player-sequence)
      (if-let [winner (board/winner board)]
        (println "Winner is " winner)
        (println "It's a draw"))

      :else
      (let [[new-board new-player-sequence] (process-command (user-input/command) board player-sequence)]
        (recur new-board new-player-sequence)))))
