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
      [(- n i 1) i])))


(defn get-elements-of-matrix
  [matrix, coordinates]
  (vec (map #(get-in matrix %) coordinates)))


(defn get-all-possible-seqs
  [matrix])


(defn diag-winner
  [matrix]
  (->> matrix
       (primary-diag-coordinates)
       (get-elements-of-matrix matrix)
       (winner-of-collection)))


(defn winner-of-board
  [matrix]
  conj)


(defn diagonal-elements
  [matrix]
  (let [n                (count matrix)
        primary-coords   (for [i (range n)] [i i])
        secondary-coords (for [i (range n)] [i (- n i 1)])
        primary-diags    (vec (map #(get-in matrix %) primary-coords))
        secondary-diags  (vec (map #(get-in matrix %) secondary-coords))]
    (prn primary-coords)
    (conj [] primary-diags secondary-diags)))


