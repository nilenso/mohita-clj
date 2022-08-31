# Day 16

### Date: August 23, 2022

### Topic

SICP distilled

### Notes

- recursive process → chain of deferred operations. interpreter must hold onto remaining operations.
- iterative process → fixed state variables, fixed updation rule, end rule(? i think that should also be there for recursive). If stopped can be easily resumed
- recursive fn → just calling itself
- recursive process → how fn evolves
- Tree recursion → each time fn is invoked it calls it self multiple times.
    - number of steps grows exponentially
    - space linear
    - use when working with hierarchal data
    - memoization

---

### Topic

Recursion

### Notes

- Trampoline: placement of the functions in a list?
- Continuation-passing style (Cps): fn has an extra arg ‘continuation’ , change control flow. return early. can be used tp suspend a computation.
    - Return, continuation, accept

---

<aside>
📌 **SUMMARY:**

</aside>