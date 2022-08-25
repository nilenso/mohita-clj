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
;; h-> 2^1, 2^2, 2^2^2 ...


;; ex 1.11
;; fn - n n<3
;; f (n-1) + 2f(n-2) + 3(fn-3) n>= 3
;; recusive process

(def f-recursive
  [n]
  (if (< n 3)
    n
    (+ (f-recursive (- n 1))
       (* 2 (f-recursive (- n 2)))
       (* 3 (f-recursive (- n 3))))))


;; iterative

;; f(0) = 0
;; f(1) = 1
;; f(2) = 2
;; f(3) = f(2) + f(1) + f(0)
;; f(4) = f(3) + f(2) + f(1)
;; f(5) = f(4) + f(3) + f(2)

(defn f-iterative
  [a b c n count]
  (if (< n count)
    a
    (f-iterative (+ a (* 2 b) (* 3 c)) a b n (inc count))))


(defn f-i
  [n]
  (f-iterative 2 1 0 n 0))


(define (f n)
  (f-iter 1 0 0 n))


(define (f-iter a b c count)
  (if (= count 0)
    (+ b c)
    (f-iter (+ a (* b 2) (* c 3)) a b (- count 1))))
