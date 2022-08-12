# Day 10

### Date: August 12, 2022

### Topic

Lazyness

### Notes

- Head → first unrealized element
- Thunk → inside every head, uninvoked closure
- Evaluated → Partially evaluated → unevaluated
- [To make a lazy sequence](https://otee.dev/2022/01/17/lazy-clojure.html) use the `lazy-seq` fn wrapped around the recusive call.
    - Cannot use recur here as it will not be a tail call.
    - use cons instead of conj → (Idk why it causes a stackoverflow for some reason)
    - when Clojure sees lazy-seq it stops computation until it is realized.
- Realizes in chunks. 32 members will be evaluated
- This behaviour seemed weird. `(range)` uses iterate which doesn’t support chunking. `(range end)` uses LongRange that supports chunking

```clojure
(first (map (fn [x] (prn x) x) (range)))
0
=>0
(first (map (fn [x] (prn x) x) (range 100)))
0-31
=> 0
```

---

### Topic

WEWUT

### Notes

- Tests should be as small as possible. Trivial to understand.Make the number of places to inspect as small as possible.
- Each test should be consided it’s own universe
- Expect literals. Some tests are so small calling a fn that returns a string is pointless
- Remove setup, make direct calls
- DAMP over DRY

---

### Topic

[Re-Implement Iteration](https://4clojure.oxal.org/#/problem/62)

### Notes

- Similar to 60. Conj didn’t work here either for the same reason I’m assuming. Don’t know what that reason is :’)
- Maybe [this](https://stackoverflow.com/questions/12389303/clojure-cons-vs-conj-with-lazy-seq#:~:text=Cons%20object%2C%20which%20is%20what,call%20on%20the%20collection%20itself.).

<aside>
📌 **SUMMARY:
- Lazyness
- Why Conj causes so and Cons doesn’t 
-wewut (first chapter)**

</aside>