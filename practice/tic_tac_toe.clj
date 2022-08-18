(ns tic-tac-toe
  (:gen-class))


(defn winner-of-collection
  [coll]
  (let [unique-val (set coll)]
    (when (and (= 1 (count unique-val))
               (not (:e unique-val)))
      (first unique-val))))


(defn transpose-matrix
  [matrix]
  (vec (apply map vector matrix)))


(defn primary-diag-coordinates
  [matrix]
  (for [i (range (count matrix))]
    [i i]))


(defn secondary-diag-coordinates
  [matrix]
  (let [n (count matrix)]
    (for [i (range n)]
      [i (- n i 1)])))


(defn get-elements-of-matrix
  [matrix, coordinates]
  (vec (map #(get-in matrix %) coordinates)))


(defn get-diagonals
  [matrix]
  (conj []
        (get-elements-of-matrix matrix (primary-diag-coordinates matrix))
        (get-elements-of-matrix matrix (secondary-diag-coordinates matrix))))


(defn get-all-possible-seqs
  [matrix]
  (concat
    matrix
    (transpose-matrix matrix)
    (get-diagonals matrix)))


(defn winning-seqs
  [matrix]
  (filter winner-of-collection
          (get-all-possible-seqs matrix)))


(defn winner-of-board
  [matrix]
  (if (= 1 (count (winning-seqs matrix)))
    (first (first (winning-seqs matrix)))
    "no winner"))


(def board
  [[:x :e :e]
   [:x :e :e]
   [:x :e :o]])


(winner-of-board board)
