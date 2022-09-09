(ns ttt.board
  (:refer-clojure :exclude [update])
  (:require
    [clojure.set]
    [ttt.matrix-operations :as mo]))


(def size 3)


(def position-to-coordinate
  (->> (for [x (range size)
             y (range size)]
         [x y])
       (map-indexed (fn [i coord] [(inc i) coord]))
       (into {})))


(def empty-board
  (mapv vec (partition size (repeat (* size size) :e))))


(def valid-positions
  (keys position-to-coordinate))


(def positions-string
  (mo/matrix->string (partition size (sort valid-positions))))


(def valid-game-pieces-set #{:o :x :e})

(def game-pieces-set #{:o :x})


(defn to-string
  [board]
  (mo/matrix->string (mapv #(mapv name %) board)))


(defn empty-coordinate?
  [board coordinate]
  (= :e (get-in board coordinate)))


(defn valid?
  [board]
  (and (mo/square-matrix? board)
       (clojure.set/subset? (set (flatten board)) valid-game-pieces-set)))


(defn update
  [board coordinate game-piece]
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


(defn winning-game-piece
  [board]
  (when (= 1 (count (winners-of-seqs board)))
    (first (winners-of-seqs board))))


(defn winner
  [board]
  (when (valid? board)
    (winning-game-piece board)))
