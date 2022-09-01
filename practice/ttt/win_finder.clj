(ns ttt.win-finder
  (:gen-class)
  (:require
    [clojure.set]
    [ttt.matrix-operations :as mo]))


(def valid-game-pieces-set #{:o :x :e})
(def game-pieces-set #{:o :x})


(defn winner-of-collection
  [coll]
  (let [unique-val (set coll)]
    (when (and
            (= 1 (count unique-val))
            (not (:e unique-val)))
      (first unique-val))))


(defn winners-of-seqs
  [matrix]
  (->> matrix
       (mo/get-all-possible-seqs)
       (map winner-of-collection)
       (remove nil?)))


(defn winning-game-piece
  [board]
  (if (= 1 (count (winners-of-seqs board)))
    (first (winners-of-seqs board))))


(defn valid-board?
  [board]
  (clojure.set/subset? (set (flatten board)) valid-game-pieces-set))


(defn winner-of-board
  [board]
  (if (and
        (mo/square-matrix? board)
        (valid-board? board))
    (winning-game-piece board)
    "Invalid Board"))


(defn game-over?
  [board player-sequence]
  (or (empty? player-sequence) (= 1 (count (winners-of-seqs board)))))


(def test-board
  [[:x :e]])


;;
;; (defn -main
;;  []
;;  (prn (winner-of-board test-board)))


;; board
;; -> board-is-valid
;; -> get-all-possible-seqs
;; -> winners-of-seqs
;; -> remove nil?
;; -> count and all
;;
;; (count and all (remove nil? (winning seqs (get-all-possible-seqs board ))))
