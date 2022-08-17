(ns tic-tac-toe
  (:gen-class))

(defn winner-of-collection [coll]
  (let [unique-val (set coll)]
    (when (and (= 1 (count unique-val))
            (not (:e unique-val)))
      (first unique-val)
      )
    )
  )


(defn transpose-matrix [matrix]
  (vec (apply map vector matrix)))

(defn get-diagonal-elements [matrix]
  (let [n (count matrix)
        primary-coords (for [i (range n)] [i i])
        secondary-coords (for [i (range n)] [i (- n i 1)])

        primary-diags (vec (map #(get-in matrix %) primary-coords))
        secondary-diags (vec (map #(get-in matrix %) secondary-coords))]

    (conj [] primary-diags secondary-diags)))

