(ns tic-tac-toe
  (:gen-class))


(defn winner-of-collection
  [coll]
  (let [unique-val (set coll)]
    (when (and
            (= 1 (count unique-val))
            (not (:e unique-val)))
      (first unique-val))))


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


(defn winners-of-seqs
  [matrix]
  (remove nil? (map winner-of-collection
                    (get-all-possible-seqs matrix))))


(defn winning-game-piece
  [board]
  (if (= 1 (count (winners-of-seqs board)))
    (first (winners-of-seqs board))
    "No Winner"))


(def valid-game-pieces-set #{:o :x :e})


(defn is-valid-collection?
  [coll]
  (clojure.set/subset? (set coll) valid-game-pieces-set))


(defn is-board-valid?
  [board]
  (every? true? (map is-valid-collection? board)))


(defn winner-of-board
  [board]
  (if (is-board-valid? board)
    (winning-game-piece board)
    "Invalid Board"))


(def test-board
  [[:x :o :x]
   [:e :o :x]
   [:o :e :x]])


(winner-of-board test-board)


;; board
;; -> board-is-valid
;; -> get-all-possible-seqs
;; -> winners-of-seqs
;; -> remove nil?
;; -> count and all
;;
;; (count and all (remove nil? (winning seqs (get-all-possible-seqs board ))))
