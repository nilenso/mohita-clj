(ns laziness)

(take 5 ((fn iterate-v2 [f x]
           (lazy-seq (conj
                       (iterate-v2 f (f x))
                       x)))
         #(* 1 %) 1))