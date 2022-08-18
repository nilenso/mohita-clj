# Day 13

### Date: August 18, 2022

### Topic

Unit Testing

### Notes

- Clojure has a builtin testing library, use `is` for a single assertion and `are` for multiple assertions
- Basic structure looks like:

```clojure
(ns tic-tac-toe-test
  (:useclojure.test))

(deftest add-1-to-1
  (is (= 2 (+ 1 1))))

(deftest add-x-to-y-a-using-are
  (are [x y] (= 5 (+ x y))
    2 3
    1 4
    3 2))

(run-all-tests)
```

- How does naming work? Think it looks something like [this](https://enterprisecraftsmanship.com/posts/you-naming-tests-wrong/) but not entirely sure (why would i even need multiple assertions if name is so specific?)

---

### Topic

[Word Sorting](https://4clojure.oxal.org/#/problem/70)

### Notes

- Problem itself was easy, discovered macroexpand.
- Threading works here too, data is not being unexpectedly transformed. Don’t use if you’re (for example) changing between a string and number.
- Follow [this](https://guide.clojure.style/) for formatting. It looks much better now.

---

### Topic

[Merge with a function](https://4clojure.oxal.org/#/problem/69)

### Notes

- Initially wrote with nested reduces. Looked very complicated so tried with `letfn` and still looks a bit messy. apparently it’s fine but gotta remember to name thing properly

---

### Topic

Prime Number

### Notes

- Should i `→` here? (answer is yes, yes you should) Use threads only if the data is not transforming.

---

<aside>
📌 **SUMMARY:
- When to use `→`
- Name and format things properly
- unit testing**

</aside>