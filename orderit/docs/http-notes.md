# HTTP Request and Response Notes

## Request 1 — Create a product

- **Request:** `POST /products/create`
- **Headers:** `Content-Type: application/json`
- **Body:** `{"id":1,"name":"Laptop","price":50000}`
- **My guess:** `201 Created`
- **Actual:** Fill in the status, response `Content-Type`, and body.
- **Why:** The controller creates the product and declares `HttpStatus.CREATED` for a successful request.

## Request 2 — List products

- **Request:** `GET /products/all`
- **Headers:** No request body required.
- **My guess:** `200 OK`
- **Actual:** Fill in the status, response `Content-Type`, and body.
- **Why:** The controller returns the collection of products as JSON.

## Request 3 — Search

- **Request:** `GET /products/search?name=lap`
- **Headers:** No request body required.
- **My guess:** `200 OK`
- **Actual:** Fill in the status, response `Content-Type`, and body.
- **Why:** The controller searches for products matching the supplied query parameter.

## Request 4 — Invalid body

- **Request:** `POST /products/create`
- **Headers:** `Content-Type: application/json`
- **Body:** `{"id":2,"name":"","price":-5}`
- **My guess:** `400 Bad Request`
- **Actual:** Fill in the status, response `Content-Type`, and body.
- **Why:** Bean Validation rejects a blank name and a non-positive price when the constraints are configured.

## Request 5 — Wrong method

- **Request:** `DELETE /products/all`
- **Headers:** No request body required.
- **My guess:** `405 Method Not Allowed`
- **Actual:** Fill in the status, response `Content-Type`, and body.
- **Why:** The endpoint supports GET, not DELETE, assuming no other matching handler exists.

## Request 6 — Wrong content type

- **Request:** `POST /products/create`
- **Headers:** `Content-Type: text/plain`
- **Body:** `{"id":3,"name":"Mouse","price":500}`
- **My guess:** `415 Unsupported Media Type`
- **Actual:** Fill in the status, response `Content-Type`, and body.
- **Why:** Spring normally cannot use a JSON message converter for a request labelled as plain text.

## Extra experiments

Record what happens when the search parameter is omitted, the JSON body is malformed, and an existing product ID is submitted again.

Also run `curl.exe -v` to inspect request headers sent automatically by curl.
