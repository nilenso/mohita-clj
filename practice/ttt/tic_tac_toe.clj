(ns ttt.tic-tac-toe
  (:gen-class)
  (:require
    [ttt.matrix-operations :as mo]))


(def valid-game-pieces-set #{:o :x :e})


(defn winner-of-collection
  [coll]
  (let [unique-val (set coll)]
    (when (and
            (= 1 (count unique-val))
            (not (:e unique-val)))
      (first unique-val))))


(defn winners-of-seqs
  [matrix]
  (remove nil? (map winner-of-collection
                    (mo/get-all-possible-seqs matrix))))


(defn winning-game-piece
  [board]
  (if (= 1 (count (winners-of-seqs board)))
    (first (winners-of-seqs board))
    "No Winner"))


(defn valid-board?
  [board]
  (clojure.set/subset? (set (flatten board)) valid-game-pieces-set))


(defn winner-of-board
  [board]
  (if (valid-board? board)
    (winning-game-piece board)
    "Invalid Board"))


(def test-board
  [[:e :x :e]
   [:o :o :o]
   [:x :e :x]])


(winner-of-board test-board)


;; board
;; -> board-is-valid
;; -> get-all-possible-seqs
;; -> winners-of-seqs
;; -> remove nil?
;; -> count and all
;;
;; (count and all (remove nil? (winning seqs (get-all-possible-seqs board ))))
