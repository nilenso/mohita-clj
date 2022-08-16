# Day 11

### Date: August 16, 2022

### Topic

[Tic Tac Toe](https://4clojure.oxal.org/#/problem/73)

### Notes

- Divided it up into smaller problems and working on them.

---

### Topic

Lazyness ( again )

### Notes

- Body of lazy seq should return an `ISeq` but I returned a vector which is not iseq. Guess that answers it but still don’t really get it.

---

### Topic

[Black box testing](https://4clojure.oxal.org/#/problem/65)

### Notes

- Initially did it by emptying the collection and seeing what they’re equal to but this doesn’t work for differentiating between lists and vectors (both are seq)
- Only diff i know is adding elements, and used that for an answer I’m not entirely happy with but works.

---

### Topic

WEWUT

### Notes

- A more generalised test data builder than an ObjectMother. Allows more control over whether to share an object and build objects not tied to any scenario

---

<aside>
📌 **SUMMARY: 
- read through wewut
- laziness 
- more 4 clojure problems 
- started tic-tac-toe**

</aside>