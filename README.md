# TriangleAPI automated test coverage

## Test cases
### Functional tests (can be used as smoke tests)
* Add a triangle
* Add a triangle with another separator
* Delete a triangle
* Get all triangles
* Get a triangle
* Get perimeter
* Get area

### Edge tests
* Add over 10 triangles
* Add a triangle with a long side
* Add a triangle with a negative side
* Add a triangle with zero-side
* Add a triangle with 2 sides
* Get an unexisting triangle
* Perform a request using invalid token

## Existing bugs
* It is possible to add 11 triangles to the subset. Expected: only 10 triangles can be added
* It is possible to add a triangle with a negative side length value. 
