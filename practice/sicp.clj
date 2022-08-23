(ns sicp
  (:gen-class))


(defn A
  [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (A (- x 1) (A x (- y 1)))))


;; (def (f n) (A 0 n))
;; (def (g n) (A 1 n))
;; (def (h n) (A 2 n))
;; (def (k n) (* 5 n n))
;;
;; f-> 2*n
;; g-> 2^n
;; h->
