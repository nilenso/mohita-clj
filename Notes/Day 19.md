# Day 19

### Date: August 26, 2022

### Topic

Error handling

### Notes

- Two methods: With complex code base
- Throw exception
- Return response with error
    - Can tell where it’s failed without wrapping in try catch.
    - Defining an interface

---

### Topic

Solid

### Notes

- *S*ingle Responsbility Principle: count the reasons something should change. Split into different functions if a single function has multiple reasons to change.
- Open/Closed Principle: When using a library that doesnt do exactly what you need, extend the functionality without changing library
- Liskov Substitution Principle: subclass should be able to be substituted for its superclass. Eg Maps → arrayMap
- Interface Segregation Principle: separate the interface so that client will only by affected by changes in the methods they use
- Dependency Inversion Principle: High-level modules should not be affected in changes in low level modules and vice versa. Use abstractions between them.

---

<aside>
📌 **SUMMARY:
- SOLID principles
- Error handling 
- Cli for ttt**

</aside>