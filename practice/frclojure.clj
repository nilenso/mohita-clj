(ns frclojure
  (:gen-class)
  (:import
    (clojure.lang
      ISeq)))


;; nth
(= ((fn [c p]
      (loop [coll c pos p]
        (if-not (> pos 0)
          (first coll)
          (recur (rest coll) (dec pos)))))
    '(4 5 6 7) 2) 6)


(loop [x   [1 2 3]
       acc 0]
  (if (empty? x)
    acc
    (recur (rest x) (+ (first x) acc))))


;; count
(= ((fn [c]
      (loop [coll c acc 0]
        (if (empty? coll)
          acc
          (recur (rest coll) (inc acc))))) '(1 2 3 3 1)) 5)


;; reverse
(= ((fn [c]
      (loop [coll c rev '()]
        (if (empty? coll)
          rev
          (recur (rest coll) (conj rev (first coll)))))) [1 2 3 4 5]) [5 4 3 2 1])


;; sum
(= ((fn [coll] (reduce + coll)) [1 2 3]) 6)
(= (reduce + [1 2 3]) 6)


;; odd nos
(= (filter odd? #{1 2 3 4 5}) '(1 3 5))


;; bit hacky using a vector
;; fib seq

(= ((fn [x]
      (loop [coll [1] curr x a 0 b 1]
        (if (= curr 1)
          coll
          (recur (conj coll (+ a b)) (dec curr) b (+ a b))))) 6) '(1 1 2 3 5 8))


;; try using stack maybe? palindrome
(true? ((fn [x]
          (= (vec x) (vec (reverse x)))) [:foo :bar :foo]))


;; won't work with keywords
(true? ((fn [x]
          (= x (apply str (reverse x)))) [:foo :bar :foo]))


;; flatten
(= ((fn [c]
      (loop [coll c result []]
        (if (empty? coll)
          result
          (if (coll? (first coll))
            (recur (concat (first coll) (rest coll)) result)
            (recur (rest coll) (conj result (first coll))))))) '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))


;; problem 29
;; caps
;; char/isuppercase not found
(= ((fn [x]
      (apply str (filter
                   (fn [a] (Character/isUpperCase a)) x)))
    "HeLlO, WoRlD!") "HLOWRD")


(= ((fn [x] (apply str (filter #(Character/isUpperCase %) x)))
    "HeLlO, WoRlD!") "HLOWRD")


(empty? ((fn [x] (apply str (filter #(Character/isUpperCase %) x))) "nothing"))


(= ((fn [x]
      (apply str (filter
                   (fn [a] (Character/isUpperCase a)) x))) "$#A(*&987Zf") "AZ")


(= ((fn [s] (apply str (re-seq #"[A-Z]+" s)))
    "HeLlO, WoRlD!") "HLOWRD")


;; compress

(= (reduce (fn [a b] (if (= b (last a)) a (conj a b))) [] [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))


;; 31
;; packing seq
(= ((fn [c]
      (loop [c      c
             curr   []
             result []]

        (if (empty? c)
          (conj result curr)

          (if (or (= (first c) (last curr)) (empty? curr))
            (recur (rest c) (conj curr (first c)) result)
            (recur (rest c) (conj [] (first c)) (conj result curr)))))) [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))


(= ((fn [coll]
      (reverse
        (reduce (fn [a b]
                  (if (= (first (first a)) b)
                    (conj (rest a) (conj (first a) b))
                    (conj a (list b)))) [] coll))) [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))


;; 32
;; dupe
(= ((fn [c]
      (loop [c      c
             result []]
        (if (empty? c)
          result
          (recur (rest c) (conj result (first c) (first c)))))) [1 2 3]) '(1 1 2 2 3 3))


(= ((fn [coll]
      (reverse (reduce (fn [result val]
                         (conj result val val))
                       () coll))) [1 2 3]) '(1 1 2 2 3 3))


;; 33
(= ((fn [c n]
      (for [c c _ (range n)]
        c)) [1 2 3] 2) '(1 1 2 2 3 3))


;; 34

((fn [start end]
   (loop [curr start
          acc  []]
     (if (= curr end)
       acc
       (recur (inc curr) (conj acc curr))))) 1 4)


;; 38

(= ((fn [& vals]
      (reduce (fn [max curr]
                (if (> max curr) max curr)) vals))
    1 8 3 4) 8)


;; 39

(= (mapcat (fn [a b] (conj [] a b)) [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))


;; 40

(= ((fn [x y]
      (rest (flatten (for [x (list x)
                           y y] (conj [] x y))))) 0 [1 2 3]) [1 0 2 0 3])


;; 41
(= ((fn [x n]
      (for [pos (range (count x))
            :when (> (mod (inc pos) n) 0)]
        (nth x pos))) [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])


;; 42 fact
(= ((fn [n]
      (loop [curr n
             fact 1]
        (if (<= curr 1)
          fact
          (recur (dec n) (* n fact))))) 2) 2)


((fn [n]
   (reduce * (range 1 (inc n)))) 3)


;; 43
(= ((fn [c n]
      (map (fn [x] (take-nth n (drop x c)))
           (range n))) [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))


;; 44
(= ((fn [n coll]
      (let [c (count coll)
            n (mod n c)]
        (if (< n 0)
          (concat (take-last (+ n c) coll) (take n coll))
          (concat (take-last (- c n) coll) (take n coll))))) -2 [1 2 3 4 5]) '(4 5 1 2 3))


;; 46
(= 3 (((fn [f] (fn [x y] (f y x))) nth) 2 [1 2 3 4 5]))


;; 47
(contains? [1 1 1 1 1 1] 4)


;; 49
(= ((fn [n c] (conj [] (take n c) (drop n c))) 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])


;; 50
(= (set ((fn [c] (vals (group-by class c))) [:a "foo" "bar" :b])) #{[:a :b] ["foo" "bar"]})


;; 51
(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))


;; 52
(= [2 4] (let [[a b c d e f g] (range)] [c e]))


;; 55
(= ((fn [c]
      (into {} (map (fn [[k v]] {(first v) (count v)}) (group-by identity c)))) '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})


;; 56
(= ((fn [coll]
      (reduce (fn [result x]
                (if (some #{x} result)
                  result
                  (conj result x))) [] coll)) [1 2 1 3 1 2 4]) [1 2 3 4])


;; 58

(= [3 2 1] (((fn [& fs]
               (fn [& args]
                 (loop [fs   (reverse fs)
                        args args]
                   (if (empty? fs)
                     args
                     (recur (rest fs)
                            (list (apply (first fs) args))))))) rest reverse identity) [1 2 3 4]))


(((fn underscore
    [& fs]
    (fn inner-fn
      [& args]
      (first
        (reduce
          #(list (apply %2 %1))
          args
          (reverse fs)))))
  rest reverse) [1 2 3 4])


(((fn our-comp
    [& fs]
    (letfn [(eval-fn-with-args [args f] (list (apply f args)))
            (comped-fn
              [& args]
              (first (reduce eval-fn-with-args
                             args
                             (reverse fs))))]
      comped-fn))
  rest reverse)
 [1 2 3 4])


;; 59

(= [21 6 1] (((fn my-juxt
                [& fs]
                (fn juxt-fn
                  [& args]
                  (map
                    #(apply % args)
                    fs))) + max min) 2 3 5 1 6 4))


;; 60

(fn my-reduction

  ([f x] (my-reduction f (first x) (rest x)))

  ([f x y]
   (lazy-seq
     (if (empty? y)
       [x]
       (let [result (f x (first y))]
         (cons
           x
           (my-reduction f result (rest y))))))))


(= (take 5 ((fn my-reduction

              ([f x] (my-reduction f (first x) (rest x)))

              ([f x y] (lazy-seq
                         (if (empty? y)
                           [x]
                           (let [result (f x (first y))]
                             (cons
                               x
                               (my-reduction f result (rest y)))))))) + (range))) [0 1 3 6 10])


;; 61

(= ((fn [x y]
      (into {} (map hash-map x y))) [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})


;; 62

(= (take 5 ((fn iterate-v2
              [f x]
              (lazy-seq (cons
                          x
                          (iterate-v2 f (f x)))))
            #(* 2 %) 1)) [1 2 4 8 16])


(take 5 ((fn iterate-v2
           [f x]
           (lazy-seq (conj
                       (iterate-v2 f (f x))
                       x)))
         #(* 1 %) 1))


(defn iterate-v2
  [f x]
  (lazy-seq (conj
              (iterate-v2 f (f x))
              x)))


;; 63
((fn grouping
   [f coll]
   (reduce #(merge-with concat %1 %2)
           (map #(hash-map (f %) [%]) coll)))
 #(> % 5) #{1 3 6 8})


;; 65

((fn get-coll
   [coll]
   (let [emptycoll (empty coll)]
     (cond
       (= {} emptycoll) :map
       (= #{} emptycoll) :set
       (= 1
          (first (conj emptycoll 1 2))) :vector
       :else :list))) [])


;; 66

(fn gcd
  [x y]
  (if (zero? y)
    x
    (recur y (mod x y))))


;; 67

(defn is-prime?
  [n]
  (and
    (< 1 n)
    (empty? (filter #(= 0 (rem n %))
                    (range 2 n)))))


((fn is-prime?
   [n]
   (empty? (->> n
                (range 2)
                (filter #(= 0 (rem n %)))))) 0)


(fn n-prime-numbers
  [n]
  (take n
        (filter
          (is-prime? n)
          (range))))


(->>
  (range)
  (filter is-prime?)
  (take 5))


;; 69

(fn merge-with-v2
  [resolve-fn & maps]
  (letfn [(set-kv-with-fn
            [acc [k v]]
            (let [new-val (if (contains? acc k)
                            (resolve-fn (k acc) v)
                            v)]
              (assoc acc k new-val)))

          (merge-two-maps
            [acc b]
            (reduce set-kv-with-fn acc b))]
    (reduce merge-two-maps {} maps)))


;; 70

#(sort-by clojure.string/lower-case (re-seq #"\w+" %))


(defn sort-words
  [str]
  (->> str
       (re-seq #"\w+")
       (sort-by clojure.string/lower-case)))


;; 73

;; tried again in practice/tic-tac-toe
((fn tic-tac-toe
   [board]
   (letfn [(winning-row
             [player]
             (some #(apply = player %) board))

           (win-primary-diag
             [player]
             (apply = player (map #(get-in board [% %]) (range 3))))

           (win-secondary-diag
             [player]
             (apply = player (map #(get-in board [% (- 2 %)]) (range 3))))

           (transpose
             []
             (apply map vector board))]


     (cond
       (winning-row :o) :o
       (winning-row :x) :x
       (winning-row :o (transpose)) :o
       (winning-row :x (transpose)) :x
       (win-primary-diag :o) :o
       (win-primary-diag :x) :x
       (win-secondary-diag :o) :o
       (win-secondary-diag :x) :x
       :else
       nil)))

 [[:x :x :x]
  [:x :x :x]
  [:o :e :x]])


;; 74

(defn is-perfect-square?
  [n]
  (let [approx-sqrt (Math/sqrt n)]
    (= approx-sqrt
       (Math/floor approx-sqrt))))


(defn filter-perfect-squares
  [s]
  (->>
    (clojure.string/split s #",")
    (map #(Integer/parseInt %))
    (filter is-perfect-square?)
    (clojure.string/join ",")))


(defn filter-perfect-squares-v2
  [s]
  (->>
    (clojure.string/split s #",")
    (map read-string)
    (filter is-perfect-square?)
    (interpose \,)
    (apply str)))


(filter-perfect-squares-v2 "15,16,25,36,37")

(= (filter-perfect-squares "4,5,6,7,8,9") "4,9")

(= (filter-perfect-squares-v2 "15,16,25,36,37") "16,25,36")


;; 75

(defn gcd
  [x y]
  (if (zero? y)
    x
    (recur y (mod x y))))


(defn is-coprime?
  [x y]
  (= 1 (gcd x y)))


(defn totient-function
  [n]
  (if (= 1 n)
    n
    (count (filter
             #(is-coprime? n %)
             (range 1 n)))))


;; for 4clj
(defn totient-function-v2
  [n]
  (if (= 1 n)
    n
    (count (filter
             #((fn is-coprime?
                 [x y]
                 (= 1 ((fn gcd
                         [x y]
                         (if (zero? y)
                           x
                           (recur y (mod x y)))) x y))) n %)
             (range 1 n)))))


;; 76
