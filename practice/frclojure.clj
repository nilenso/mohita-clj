(ns frclojure
  (:gen-class))

(= ( (fn [c p] (loop [coll c  pos p]
                      (if (> pos 0)
                        (recur (rest coll) (dec  pos))
                        (first coll )
                        )))
    '(4 5 6 7) 2) 6)


(= ( (fn [c] (loop [coll c acc 0]
                  (if (empty? coll)
                    acc
                    (recur (rest coll) (inc acc))
                    ))) '(1 2 3 3 1)) 5)

(= ((fn [c] (loop [coll c rev '()]
              (if (empty? coll)
                rev
                (recur (rest coll) (conj rev (first coll))))
              )) [1 2 3 4 5]) [5 4 3 2 1])

(= ((fn [coll] (reduce + coll)) [1 2 3]) 6)

(= (filter odd? #{1 2 3 4 5}) '(1 3 5))

;;bit hacky using a vector
;;26

(= ((fn [x] (loop [coll [1] curr x a 0 b 1]
              (if (= curr 1)
                coll
                (recur (conj coll (+ a b) ) (dec curr) b (+ a b)))
              )) 6) '(1 1 2 3 5 8))


;;try using stack maybe?
(true? ((fn [x]
           (= (vec x) (vec (reverse x)))) "racecar"))
