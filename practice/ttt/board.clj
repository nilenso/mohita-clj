(ns ttt.board
  (:require [clojure.set]
            [ttt.matrix-operations :as mo]))

(def empty-board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])

(def positions
  [[1 2 3]
   [4 5 6]
   [7 8 9]])

(def positions-string
  (mo/matrix->string positions))

(def valid-game-pieces-set #{:o :x :e})
(def game-pieces-set #{:o :x})

(defn to-string [board]
  (mo/matrix->string (mapv #(mapv name %) board)))

(defn empty-coordinate? [board coordinate]
  (= :e (get-in board coordinate)))

(defn valid? [board]
  (and (mo/square-matrix? board)
       (clojure.set/subset? (set (flatten board)) valid-game-pieces-set)))

(defn update [board coordinate game-piece]
  (when (valid? board)
    (assoc-in board coordinate game-piece)))

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

(defn winning-game-piece [board]
  (when (= 1 (count (winners-of-seqs board)))
    (first (winners-of-seqs board))))

(defn winner [board]
  (when (valid? board)
    (winning-game-piece board)))
