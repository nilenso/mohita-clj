# Day 34

### Date: September 19, 2022

### Topic

Practitioner

### Notes

- Turns out mocking with redefs was not the way to go. I think `with-redefs` literally **re def**ines a var that already exists. So to mock the request i used ring mock. It hasn’t been updated in years but ring still uses it so it should be okay.
- Read through the GitHub actions docs. Pretty self explanatory. Not quite sure how to check if the workflow actually works without making commit that don’t work. This is the one place where commit wrong things is okay. Not really much else to do.
- Read through the martin fowler article on CI. It was an interesting read didn’t think integration was once a very long process.
- Added test coverage. Build fails if its below a certain threshold which is cool. Took a bit of time to get it working. Should’ve read docs a bit more throughly the first time.
- Will add formatting and lint next. also add run tests for each PR

---