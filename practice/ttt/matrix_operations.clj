(ns ttt.matrix-operations)


(defn transpose-matrix
  [matrix]
  (if (every? #(not (sequential? %)) matrix)
    matrix
    (vec (apply map vector matrix))))


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


(defn get-diagonals-of-matrix
  [matrix]
  (conj []
        (get-elements-of-matrix matrix (primary-diag-coordinates matrix))
        (get-elements-of-matrix matrix (secondary-diag-coordinates matrix))))


(defn get-all-possible-seqs
  [matrix]
  (concat
    matrix
    (transpose-matrix matrix)
    (get-diagonals-of-matrix matrix)))


