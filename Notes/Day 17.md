# Day 17

### Date: August 24, 2022

### Topic

Java interop

### Notes

- (accidentally came across while trying to figure out what chunking does)
- `(java.util.Date.)` → `(new java.util.Date)` instantiation
- `(.d getTime)` → `(.getTime d)` instance method

---

### Topic

Chunking an Unchunked seq

### Notes

- For some reason there are no official docs on chunking functions. (seems to be intentional)
- (chunk-buffer) → buffer of given capacity. It looks like the buffer is mutable, idk how that is, its not a java struct.
- (chunk-append) → appends to buffer
- (chunk) → returns an array chunk of the buffer (iChunk type)
- (chunked-cons) ??? idk can’t figure it out

---

### Topic

CPS 

### Notes

- extra parameter return value
- inversion of control, you don’t call things, they call you
- bookmarking a place in stack and code location
    - try throw catch. re-instantiating stack, won’t get a prev variable?
- two parts redex and continuation

---

<aside>
📌 **SUMMARY:
-CPS
-Chunking
-java interop**

</aside>