# Library

## How to run locally with Docker

1.  Ensure you have Docker installed and running.
2.  Navigate to the project's root directory in your terminal.
3.  Build the Docker image:
    ```sh
    docker build -t library .
    ```
4.  Run the application in a Docker container:
    ```sh
    docker run -p 8080:8080 library
    ```
5.  Access the application by navigating to `http://localhost:8080` in your web browser.

Library is a Rest API for managing books and reviews implemented with Jakarta EE 4.
On the initial run, an H2 database will be created and populated with some sample data.
There are available the following endpoints:

## Available Endpoints

1. GET http://localhost:8080/api/books/search?term=term 
Part 1: Returns a list of books that match (title or author) the search term.

2. POST http://localhost:8080/api/books/review
body: { bookId: integer, rating: integer (1-5), review: string }
Part 2: Adds a new review for a book.

3. GET http://localhost:8080/api/books/details?id=id
Part 3: Returns a book's details with the given id.

4. GET http://localhost:8080/api/books/topRated?limit=limit
Part 2 Extension: Returns a list with the top N rated books.

5. GET http://localhost:8080/api/books/averagePerMonth?id=id
Returns the average rating per month for a book.

6. GET http://localhost:8080/api/books/allReviews 
Returns a list with all reviews available in the database

7. GET http://localhost:8080/api/books/reviews?id=id
Return a list with all reviews for a book.

A Cache mechanism is implemented to improve the performance of the application and has been applied on every external call.