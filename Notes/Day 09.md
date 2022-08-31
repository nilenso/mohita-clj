# Day 9

### Date: August 11, 2022

### Topic

[Function Composition](https://4clojure.oxal.org/#/problem/58)

### Notes

- Initially thought could just do it with different arities. Might work irl but not so much as a general solution.
- Trying with loop recur cause I am not creative
- Made it work with a single argument but had some tiny issues when using & args.
- Translate from recursion to using reduce, still finding it difficult to think directly in Clojure-y terms
- Turns out the actual comp function does it with multiple arities (? don’t think thats the word but okay). More efficient than unpacking all the values.
- Used lots of nested anon functions, not very readable. Instead try:
    - Naming the functions
    - different args diff lines
    - Use the #() kinda function instead
    - letfn (sometimes, depends really)
    - Threading `→`

---

### Topic

[Juxtaposition](https://4clojure.oxal.org/#/problem/59) 

### Notes

- Tried to make it more readable
- Couldn’t use letfn, need args from both fns. same for threading. Finally settled on this.

```clojure
(fn my-juxt [& fs]
                (fn juxt-fn[& args]
                  (map
                    #(apply % args)
                    fs)
                  ))
```

---

### Topic

[Sequence Reductions](https://4clojure.oxal.org/#/problem/60) 

### Notes

- Apparently I need to know more about Lazy Sequences first

---

### Topic

Lazyness 

### Notes

- Evaluating something partially. Eval is delayed till it is needed for computation.
- Acc to JOC there’s a difference between these two? Can’t see it in my repl?
    - Turns out implementation of iterate has [changed](https://stackoverflow.com/questions/35082833/the-joy-of-clojure-rest-vs-next-sample-doesnt-produce-the-same-result-in-my) and [next](https://github.com/clojure/clojure/blob/5ffe3833508495ca7c635d47ad7a1c8b820eab76/src/jvm/clojure/lang/RT.java#L711) is [now lazy](https://github.com/clojure/clojure/blob/5ffe3833508495ca7c635d47ad7a1c8b820eab76/src/jvm/clojure/lang/Iterate.java#L54) because of the way iterate works (probably the unrealised seed will look into tomorrow)

```clojure
(def very-lazy (-> (iterate #(do (print \.) (inc %)) 1)

                 rest rest rest))
```

```clojure
(def less-lazy (-> (iterate #(do (print \.) (inc %)) 1)

                 next next next))
```

- (ignoring that) ‘next’ checks (realises)if there is another element in the sequence vs ‘rest’ which only realises the number of elements it needs

<aside>
📌 **SUMMARY:  
- Readability
- Lazyness in JOC
- Comp is defined funny**

</aside>