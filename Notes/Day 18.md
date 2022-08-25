# Day 18

### Date: August 25, 2022

### Topic

CPS

### Notes

- Steps for cps

```markdown
1. Pass extra parameter
2. Whenever the function returns an expression that doesn't contain function calls, send that expression to the continuation cont instead
3. Whenever a function call occurs in a tail position, call the function with the same continuation - cont
4. Whenever a function call occurs in an operand (non-tail) position, instead perform this call in a new continuation that gives a name to the result and continues with the expression
```

---

### Topic

JOC

### Notes

- Binding macro: Pushing and popping thread local bindings. Can modify a variable inside a binding. Can’t in a let.
- `^:dynamic` symbol is intended to be dynamically rebound, dynamic delegation.
- passive handling exceptions → from inner functions. 
can also use: dynamic var binding, handlers are pushed into inner functions

---

<aside>
📌 **SUMMARY:
- JOC 
- CPS
- CLI for ttt**

</aside>