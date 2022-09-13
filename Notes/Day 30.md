# Day 30

### Date: September 13, 2022

### Topic

12 factor app

### Notes

- backing service: Any service that the app consumes over the network. No distinction between local and third party services. Both are resources.

---

### Topic

Stopping jetty

### Notes

- stop seems to be deprecated but is still working somehow
- Used `(map #(.getName %) (.getMethods (.getClass @server)))` to list what methods it can use and it seems stop is one. NOt sure why.

---

<aside>
📌 **SUMMARY:**

</aside>