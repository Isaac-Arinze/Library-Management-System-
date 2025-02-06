# Library Management System
A modern JavaFX and Spring Boot application for managing library books with features including pagination, search functionality, and CRUD operation

JavaFX Scene Builder for UI design

![Image](https://github.com/user-attachments/assets/3efcdca9-fe5b-460f-b9e4-9d4df62eea95)

Features

📚 Complete CRUD operations for book management

🔍 Real-time search functionality

📄 Pagination support

📱 Responsive UI design using JavaFX Scene Builder

🔒 H2 Database with web console access

📊 RESTful API endpoints

🎨  UI design

## Setup Instructions

### Clone the Repository

To get started, clone this repository using the following command:

UI after implementation

![Image](https://github.com/user-attachments/assets/dbdac441-87ea-4e5d-ac86-470c0da08b0e)


## Prerequisites
Before running the application, ensure you have the following installed:

 * Java Development Kit (JDK) 21 or later
 * Maven 3.6 or later
 * JavaFX SDK 21
 * An IDE (IntelliJ IDEA or VS Code recommended)

## Technology Stack

## Backend:

 * Spring Boot 3.x
 * Spring Data JPA
 * H2 Database
 * RESTful APIs


## Frontend:

 * JavaFX 21
 * Scene Builder
 * CSS for styling

![Image](https://github.com/user-attachments/assets/baeec8d2-c810-4bad-a3eb-87677ef5ba73)


## Installation & Setup

 1. Clone the Repository
```sh
git clone https://github.com/Isaac-Arinze/Library-Management-System.git
cd Library-Management-System

cd backend 
mvn clean install
mvn spring-boot:run
 
 
Run the Frontend:

Open the frontend directory in your IDE.

Ensure the JavaFX SDK is configured in your IDE.

Run the MainApp class to launch the JavaFX application

Usage
Add a Book:

Fill in the book details (Title, Author, ISBN, Published Date) in the input fields.

Click the Add Book button to save the book to the database.

Update a Book:

Select a book from the table.

Modify the details in the input fields.

Click the Update Book button to save the changes.

Delete a Book:

Select a book from the table.

Click the Delete Book button to remove the book from the database.

Search for a Book:

Use the search bar to filter books by title, author, or ISBN in real-time.

Navigate Through Pages:

Use the Previous and Next buttons to navigate through paginated results.

Database Access
The application uses an H2 in-memory database for development. You can access the H2 database console at:

Copy
http://localhost:8080/h2-console
Use the following credentials to log in:

JDBC URL: jdbc:h2:mem:librarydb

Username: sa

Password: (leave empty)

API Endpoints
The backend exposes the following RESTful API endpoints:

GET /api/books - Retrieve all books.

GET /api/books/{id} - Retrieve a specific book by ID.

POST /api/books - Add a new book.

PUT /api/books/{id} - Update an existing book.

DELETE /api/books/{id} - Delete a book by ID.


