# Day 33

### Date: September 16, 2022

### Topic

Practitioner

### Notes

- Fixed up mount. Added a hook to the main so it can stop gracefully. Now it can start the server too.
- Apparently the db connection cannot be reused so a new one has to be created each time so it cannot be added to the state.
- Added a health check, not sure if its written well but i think it should be fine. Also wrote a test for it. Think i needed to use a fixtures so thats what I did. Not entirely sure if I’ve done it correctly probably need a test profile in the config.
- Also mocked the request with `with-refs`. There’s an example that does this so I guess that must be okay.
- Made a PR as well

---

<aside>
📌 **SUMMARY:**

</aside>