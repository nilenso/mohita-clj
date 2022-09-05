# Day 24

### Date: August 5, 2019

### Topic

Macros (JOC and [Brave](https://www.braveclojure.com/writing-macros/))

### Notes

- The single-quote operator returns the expression or symbol that you are quoting without evaluating it. (Cannot be undone with ~
- The syntax-quote operator returns the expression or symbol that you are quoting (with namespaces added), without evaluating it
- (Generally macros starting with do return nil and are meant to have side effects)
- macros receive unevaluated, arbitrary data structures as arguments and return data structures(usually lists). The *unevaluated* operand forms. The return value of the macro is then evaluated in its place.
- Syntax quoting → prevents naming collisions
- Variable capture: macro add its own binding. Use gensym to produce unique symbols in a macro
- Double Evaluation: arguments get evaluated twice
- Don’t write too many macros? is that bad practice? Probably depends on the kind of project.
- When to use macros
    - Run code at compile time
    - need unevaluated args
    - produce ********inline code?

---

<aside>
📌 **SUMMARY:
- Macros
- Fixing tests with deps**

</aside>