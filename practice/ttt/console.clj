(ns ttt.console)

(def initial-board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])

(def player-order
  (take 9 (cycle [:x :o])))

(defn print-matrix [matrix]
  (for [row matrix]
    (str
     (apply str
            (interpose " " row)))))


(def test-board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])
