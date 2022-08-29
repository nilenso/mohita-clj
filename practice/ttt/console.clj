(ns ttt.console
  (:gen-class)
  (:require
    [failjure.core :as f]
    [ttt.matrix-operations :as mo]
    [ttt.tic-tac-toe :as ttt]))


(def initial-board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])


;; (def position-board
;;  [[1 2 3]
;;   [4 5 6]
;;   [7 8 9]])

(def position-to-coordinate
  {1 [0 0], 2 [0 1], 3 [0 2],
   4 [1 0], 5 [1 1], 6 [1 2],
   7 [2 0], 8 [2 1], 9 [2 2]})


(def player-order
  (take 9 (cycle ttt/game-pieces-set)))


(defn update-board
  [coordinate game-piece board]
  (assoc-in board coordinate game-piece))


(defn place-move-on-board
  [coordinate game-piece board]
  (update-board coordinate game-piece board))


(defn move-valid?
  [coordinate board]
  (= :e (get-in board coordinate)))


(defn position-valid?
  [pos]
  (if (and (Integer/parseInt pos)
           (<= 1 (Integer/parseInt pos) 9))
    pos
    (f/fail "Enter a number between 1-9")))

(defn validate-position
  [position]
  (f/attempt #(str "Error: " (f/message %))
             (position-valid? position)))

;(defn read-position
;  [game-piece board]
;  (prn (str "Choose position for " game-piece))
;  (if-let [position (Integer/parseInt (read-line))
;           coord    (get position-to-coordinate position)]
;    (if (move-valid? coord board)
;      (place-move-on-board coord game-piece board)
;      (do (prn "Invalid Coordinate. Try again")
;          (recur game-piece board)))))


;;
;;
;; (defn play-game
;;  [board]
;;  (loop [board           board
;;         player-sequence player-order]
;;    (println "Current board:" (mo/print-matrix board))
;;    (cond
;;      (ttt/game-over? board) (ttt/winner-of-board board)
;;      (ttt/draw? board) (str "It's a draw")
;;      :else
;;      (recur
;;        (read-position (first player-sequence) board)
;;        (rest player-sequence)))))
;;
;;
;; (defn -main
;;  []
;;  (play-game initial-board))

