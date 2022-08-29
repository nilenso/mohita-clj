# Day 20

### Date: August 29, 2022


### Topic

Nil punning

### Notes

- `(assoc nil :a 1) ⇒ {:a 1}`
- `(map identity nil) → nil`
- (+ nil 1) → null pointer exception
- Clojure handles nil gracefully. Can use nil without worrying about exceptions, corner cases etc.

---

### Topic

Assertion 

### Notes

- Validation: Checking if user input is right
- Assertion: You know something will always be true and if it fails something has gone wrong. Assert something you know will always be true
    - Never used in practice add performance penalty, extra checks.
    - Hopes that its passed correctly
    - Clojure its resilient, works with wrong input. Eg nil in map
    - Used during runtime in libraries
    

---

### Topic

Error Handling

### Notes

- Use vectors wherever possible
    - Conj order not obvious
    - Index look up better in vec
    - Not overloading a list to store data or eval forms
    - easier to handle
- failjure
- When passing exceptions, there won’t be a generalised interface. If the API changes then the other fns will all need to change.
- You won’t know where the error came from. not immediately obvious.
- delegating error handling responsibility
- return data that can be manipulated vs
    - eg validation. if you’re using error exception can show multiple errors at once
- Who should handle exception in multiple threads. Return data instead to parent thread.
- Expectations not clear when reading the code

---

### Topic

CLI

### Notes

- Use atom, java array? (left it with loop recur)

---

### Topic

 Lein Trampoline

### Notes

- executes -main as a separate process, so main leiningen process can exit → one process running
- 

---

<aside>
📌 **SUMMARY:**

</aside>