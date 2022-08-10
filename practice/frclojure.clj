(ns frclojure
  (:gen-class))
;nth
(= ((fn [c p] (loop [coll c pos p]
                (if-not (> pos 0)
                  (first coll)
                  (recur (rest coll) (dec pos))

                  )))
    '(4 5 6 7) 2) 6)

(loop [x [1 2 3]
       acc 0]
  (if (empty? x)
    acc
    (recur (rest x) (+ (first x) acc))))
;count
(= ((fn [c] (loop [coll c acc 0]
              (if (empty? coll)
                acc
                (recur (rest coll) (inc acc))
                ))) '(1 2 3 3 1)) 5)
;reverse
(= ((fn [c] (loop [coll c rev '()]
              (if (empty? coll)
                rev
                (recur (rest coll) (conj rev (first coll))))
              )) [1 2 3 4 5]) [5 4 3 2 1])
;sum
(= ((fn [coll] (reduce + coll)) [1 2 3]) 6)
(= (reduce + [1 2 3]) 6)

;odd nos
(= (filter odd? #{1 2 3 4 5}) '(1 3 5))

;;bit hacky using a vector
;;fib seq

(= ((fn [x] (loop [coll [1] curr x a 0 b 1]
              (if (= curr 1)
                coll
                (recur (conj coll (+ a b)) (dec curr) b (+ a b)))
              )) 6) '(1 1 2 3 5 8))


;;try using stack maybe? palindrome
(true? ((fn [x]
          (= (vec x) (vec (reverse x)))) [:foo :bar :foo]))
;;won't work with keywords
(true? ((fn [x]
          (= x (apply str (reverse x)))) [:foo :bar :foo]))

;flatten
(= ((fn [c]
      (loop [coll c result []]
        (if (empty? coll)
          result
          (if (coll? (first coll))
            (recur (concat (first coll) (rest coll)) result)
            (recur (rest coll) (conj result (first coll))))))) '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))

;problem 29
;caps
;char/isuppercase not found
(= ((fn [x] (apply str (filter
                         (fn [a] (Character/isUpperCase a)) x)))
    "HeLlO, WoRlD!") "HLOWRD")

(= ((fn [x] (apply str (filter #(Character/isUpperCase %) x)))
    "HeLlO, WoRlD!") "HLOWRD")

(empty? ((fn [x] (apply str (filter #(Character/isUpperCase %) x))) "nothing"))

(= ((fn [x] (apply str (filter
                         (fn [a] (Character/isUpperCase a)) x))) "$#A(*&987Zf") "AZ")

(= ((fn [s] (apply str (re-seq #"[A-Z]+" s)))
    "HeLlO, WoRlD!") "HLOWRD")

;compress

(= (reduce (fn [a b] (if (= b (last a)) a (conj a b))) [] [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))

;31
;packing seq
(= ((fn [c] (loop [c c
                   curr []
                   result []]

              (if (empty? c)
                (conj result curr)

                (if (or (= (first c) (last curr)) (empty? curr))
                  (recur (rest c) (conj curr (first c)) result)
                  (recur (rest c) (conj [] (first c)) (conj result curr))))
              )) [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))


(= ((fn [coll] (reverse
                 (reduce (fn [a b]
                           (if (= (first (first a)) b)
                             (conj (rest a) (conj (first a) b))
                             (conj a (list b)))) [] coll))) [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))


;32
;dupe
(= ((fn [c] (loop [c c
                   result []]
              (if (empty? c)
                result
                (recur (rest c) (conj result (first c) (first c)))))) [1 2 3]) '(1 1 2 2 3 3))

(= ((fn [coll]
      (reverse (reduce (fn [result val]
                         (conj result val val))
                 () coll))) [1 2 3]) '(1 1 2 2 3 3))

;33
(= ((fn [c n]
      (for [c c _ (range n)]
        c)) [1 2 3] 2) '(1 1 2 2 3 3))


;34

((fn [start end]
   (loop [curr start
          acc []]
     (if (= curr end)
       acc
       (recur (inc curr) (conj acc curr))))) 1 4)
;38

(= ((fn [& vals]
      (reduce (fn [max curr]
                (if (> max curr) max curr)) vals))
    1 8 3 4) 8)

;39

(= (mapcat (fn [a b] (conj [] a b)) [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))

;40

(= ((fn [x y] (rest (flatten (for [x (list x)
                                   y y] (conj [] x y))))) 0 [1 2 3]) [1 0 2 0 3])

;41
(= ((fn [x n] (for [pos (range (count x))
                    :when (> (mod (inc pos) n) 0)]
                (nth x pos))) [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])

;42 fact
(= ((fn [n] (loop [curr n
                   fact 1]
              (if (<= curr 1)
                fact
                (recur (dec n) (* n fact)))
              )) 5) 120)

((fn [n]
   (reduce * (range 1 (inc n)))) 3)

;43
(= ((fn [c n]
      (map (fn [x] (take-nth n (drop x c)))
        (range n))) [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))

;;44
(= ((fn [n coll] (let [c (count coll)
                       n (mod n c)]
                   (if (< n 0)
                     (concat (take-last (+ n c) coll) (take n coll))
                     (concat (take-last (- c n) coll) (take n coll)))
                   )) -2 [1 2 3 4 5]) '(4 5 1 2 3))

;46
(= 3 (((fn [f] (fn [x y] (f y x))) nth) 2 [1 2 3 4 5]))

;47
(contains? [1 1 1 1 1 1] 4)

;49
(= ((fn [n c] (conj [] (take n c) (drop n c))) 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])

;50
(= (set ((fn [c] (vals (group-by class c))) [:a "foo" "bar" :b])) #{[:a :b] ["foo" "bar"]})

;51
(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))

;52
(= [2 4] (let [[a b c d e f g] (range)] [c e]))

;54
((fn [n xs]
   (take
     (quot (count xs) n) (map #(take n %)
                           (iterate #(drop n %) xs)))) 3 (range 8))
;55
(= ((fn [c]
      (into {} (map (fn [[k v]] {(first v) (count v)}) (group-by identity c)))) '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})

;56
(= ((fn [coll] (reduce (fn [result x]
                         (if (some #{x} result)
                           result
                           (conj result x))) [] coll)) [1 2 1 3 1 2 4]) [1 2 3 4])
