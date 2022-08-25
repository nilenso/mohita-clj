(ns joc
  (:gen-class))


;; java array can be modified
(def ds (into-array [:a :b :c]))
(aset ds 1 :c)
(seq ds)


(def ds2 [:a :b :c])


;; aset only work on java structures
;; (aset ds2 1 :c)
(clojure.repl/source aset)
(replace {:b :d} ds2)
ds2


;; true: both are sequential and have same values in order
(= [1 2 3] '(1 2 3))


;; false : only one is sequential
(= [1 2 3] #{1 2 3})


;; a sequence supports first and rest

(class (seq (hash-map :a 1)))


;; Vectors

;; into (concat) o(n)
(let [v [:a :b :c]]
  (into v (range 10)))


;; (range 10)

(into (vector-of :int) [1/6 2 1.3])
(into (vector-of :char) [100 101 102])


;; vector vs list
;; can add to right end
;; acessing index
;; reverse order

(def a (vec (map char (range 65 75))))
(nth a 4)
(get a 4)
(a 4)


;; gives error
;; (a 50)
(get a 50 :default)


;; assoc constant time -> structural sharing?

(def matrix
  [[1 2 3]
   [4 5 6]
   [7 8 9]])


(get-in matrix [1 2])
(update-in matrix [1 2] + 15)


(defn neighbors
  ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]]
                        size
                        yx))
  ([deltas size yx]
   (filter (fn [new-yx]
             (every? #(< -1 % size) new-yx))

           (map #(vec (map + yx %)) deltas))))


(neighbors 3 [1 1])


;; can be used as a stack but data is still immutable
;; peep, pop, conj => o(1)
;; can be used by ipersistantstack  => list vector
;; list adds to front => use a vector when a reverse is used
;; last o(n) use peek instead?

(subvec a 3 6)
(pop a)


;; A class in Java can be garbage-collected when nothing references it.
;; map entries are vectors

;; subvec returns new subvec object
;; pop return same object
(vector? (map identity {:a 1 :b 2}))


(for [[dimension amount] {:width 10, :height 20, :depth 15}]
  (str (name dimension) ":" amount "inches"))


(for [x (range 5) y '(:a :b :c)] [x, y])


;; => ([0 :a] [0 :b] [0 :c]
;; [1 :a] [1 :b] [1 :c]
;; [2 :a] [2 :b] [2 :c]
;; [3 :a] [3 :b] [3 :c]
;; [4 :a] [4 :b] [4 :c])

(map (fn [x]
       (map (fn [z] (conj [] x z)) '(:a :b :c))) (range 5))


;; => (([0 :a] [0 :b] [0 :c])
;; ([1 :a] [1 :b] [1 :c])
;; ([2 :a] [2 :b] [2 :c])
;; ([3 :a] [3 :b] [3 :c])
;; ([4 :a] [4 :b] [4 :c]))

;; cannot skip indices in a vector => not sparse
;; cannot be used as queue pop? use subvec? keep ref to og


;; Lists

;; singly ll

;; same differ in args
;; use conj for larger list
(cons 1 '(3 4))
(conj '(3 4) 1)


;; cons returns new list
(type (cons 1 '(3 4)))
(type (conj '(3 4) 1))


;; as stack pop next rest
;; pop on empty error
(def l '(1 2 3 4))
(pop l)
(next ())


;; if you need to use particular index use vector
;; cannot pop from end => not a queue

;; Persistant Queue

(defmethod print-method clojure.lang.PersistentQueue
  [q, w]
  (print-method '<- w)
  (print-method (seq q) w)
  (print-method '-< w))


(def schedule
  (conj clojure.lang.PersistentQueue/EMPTY
        :wake-up :shower :brush-teeth))


(peek schedule)
(pop schedule)


;; rest return seq
(rest schedule)


;; Sets

(#{:a :b :c :d} :c)
(get #{:a 1 :b 2} :b)
(get #{:a 1 :b 2} :z :default)


;; () [] are differing but are empty
;; if = true will not be added
(= () [])
(into #{[]} [()])


;; can check for any val present in set
(some #{:b} [:a 1 :b 2])
(some #{:z} [:a 1 :b 2])
(some #{:b :a :z 1} [:a 1 :b 2])

(def s (sorted-set :b :c :a))
(def ss (sorted-set :d :e :a))


;; (conj s 100)
;; avoid by using sorted set with different primitives

;; set is a map with each value as the key and value
(contains? #{1 2 4 3} 4)
(contains? [1 2 3 4] 4)

(clojure.set/intersection s ss)
(clojure.set/union s ss)
(clojure.set/difference s ss)


;; Maps

;; Hashmaps
;; keys can be of any type
(def hm (hash-map :a 1 :b 2 :c 3))


;; map entries
(class hm)
(seq hm)
(class (into {} [[:c 3] [:b 2] [:a 1]]))
(class (apply hash-map [:a 1 :b 2]))
(class (zipmap [:a :b] [1 2]))

(sorted-map :a 1 :b 2 :c 3)


;; does not support hetergenous keys, depends on comparison fn

;; use array map to keep seq in insertion order

(seq (hash-map :a 1, :c 2, :b 3))
(seq (hash-map :a 1, :b 2, :c 3))

(seq (array-map :a 1, :c 2, :b 3))
(seq (array-map :a 1, :b 2, :c 3))


;; lazy seq

;;
(def very-lazy
  (-> (iterate #(do (print \.) (inc %)) 1)

      rest rest rest))


(def less-lazy
  (-> (iterate #(do (print \.) (inc %)) 1)

      next next next))


(defn simple-range
  [i limit]

  (lazy-seq
    (when (< i limit)
      (do (prn i)
          (cons i (simple-range (inc i) limit))))))


(def a (first (simple-range 0 50)))


(defn re-chunk
  [n xs]
  (lazy-seq
    (when-let [s (seq (take n xs))]
      (let [cb (chunk-buffer n)]
        (doseq [x s]
          (chunk-append cb x))
        (chunk-cons (chunk cb)
                    (re-chunk n (drop n xs)))))))


(first (map (fn [x] (prn x) x)
            (re-chunk 3 (range))))


(first (map (fn [x] (prn x) x)
            (range 100)))


(defn fac-cps
  [n k]
  (letfn [(cont [v] (k (* v n)))]
    (if (zero? n)
      (k 1)
      (recur (dec n) cont))))


(fac-cps 3 identity)


;; n = 3
;; c1 - x ->x
;; c2 - x -> c1(x * 3)
;; c3 - x -> c2(x * 2) --- base case
;;        -> c2(k1(x * 3) * 2)
;; c3 will be called with val of 1
;; c3(1)
;; c2(2)
;; c1(6)

;;nth fibonacci number

;;recursively
(defn fib-rec
  [n]
  (if (< n 2)
    n
    (+ (fib-rec (- n 1)) (fib-rec (- n 2)))))

;;tail recursion
(defn fib-tc-helper
  [n acc1 acc2]
  (if (< n 2)
    acc1
    (recur (dec n) (+ acc1 acc2) acc1)))


(defn fib-tc [n]
        (fib-tc-helper n 1 0))

;;cps

(defn fib-cps [n k]
  (letfn [(cont [n1] (fib-cps (- n 2)
                              (fn [n2] (k (+ n1 n2)))))]
    (if (< n 2)
      (k n)
      (recur (- n 1) cont))))

;; k1 x - x
;; k2 x


;; x^n
(defn pow-2
  [x n acc]
  (if (= 0 n)
    acc
    (recur x (dec n) (* acc x))))
