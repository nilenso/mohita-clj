# Day 15

### Date: August 5, 2019

### Topic

Recursion

### Notes

- Mundane: regular recursion. Function is explicitly called unlike with recur. Large values will cause a stack overflow
- Prevent SO by using tail recursion with recur.
    - Avoids stack consumption
    - Tail-call optimization is where you are able to avoid allocating a new stack frame for a function because the calling function will simply return the value that it gets from the called function
- when generating a seq use lazy-seq macro? can’t generate a laxy seq using tail call.
- In TCO from a function A to a function B results in the deallocation of all of A local resources and the full delegation of execution to B.
- JVM does not support tco. Some langs tco automatically. Clojure does it with recur
- Reasons to use recur rather than provide tco
    - JVM does not provide →  recur an explicit optimisation clj doesn’t give the pretence of providing full tco
    - Allows compiler to detect when recur not in tail position
    - Can use anon recursion points (fn, loop)
- Trampoline: mutual recursion without stack consumption
    - all fns return another fn
    - first fn invked by trampoline
    - No SO cause trampoline handles calls

---

### Topic

Testing 

### Notes

- Don’t particularly need to test every single function. write depending upon whether its public/private. or if it’s a complex function (use ~~common~~ sense apparently)
- board-valid? over-is valid-board?
- Testing pyramid
    - unit tests → high granularity but isolated. cannot test whole thing. will be writing lots of tests
    - E2E (is UI testing same thing? probably ) → low granularity, more difficult to write. fewer tests.

---

<aside>
📌 **SUMMARY:
-Testing
-Recursion joc**

</aside>