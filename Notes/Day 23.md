# Day 23

### Date: September 2, 2022

### Topic

JOC macros

### Notes

- Syntax quote ` â Resolves symbol,  unquote by using ~
- unquote splicing ~@ â unquote a list and insert its elements to quoted list
- `'~v â `(list 'quote v)`

---

### Topic

Deps vs Lein

### Notes

- Lein starts 2 JVMs, versions.
- Lein relies on maven algo for picking library simpler algo, Deps use more recent versions.
- Can get dependancies from git rep with url, difficult in Lein
- deps is faster and simpler? (yeah idk about that one)

---

### Topic

Cli

### Notes

- Added deps support, took a while to get the tests running cause i didnât read all the docs properly also they were a bit difficult to understand T.T

---

<aside>
ð **SUMMARY:**

</aside>