Note :
- Session tracking for user side isn't implemented.
- If you want track the session using the session attribute "userbean" which is added to the session while login


Welcome file is : AdminLogin
To use the User site : http://localhost:8080/LibraryManagement/UserLogin.jsp


---------------------------------------------------------📚 Library Management System------------------------------------------------------

🚀 Project Overview
--------------------
The Library Management System is a web-based application built using Java, JSP, Servlets, JDBC, and Oracle SQL. It streamlines library operations, allowing admins to manage books and book requests while enabling users to search, request, and view book records.

The system uses BCrypt password hashing for secure authentication and SweetAlert for enhanced user experience with dynamic alerts.

⚙️ Features
------------
✅ Admin Functionalities
1. Login and Registration
2. Secure admin login with password hashing.
3. Admin registration with unique email validation.

Book Management
---------------
1. Add, update, delete, and view books.
2. Book Request Approval
3. View and approve/reject book requests from users.

**Logout**
-----------
Ends the session securely.

🔥 User Functionalities
------------------------
1. User Registration
2. Secure user signup with hashed passwords.
3. Login
4. Validates credentials against the database.

Book Requests
-------------
1. Submit book requests with author details.
2. View Book Requests
3. View list of submitted book requests.

Search Books
------------
1. Dynamically search for books by name.

Logout
------
Ends the session securely.

🛠️ Tech Stack
---------------
1. Frontend: JSP, HTML, CSS, JavaScript, SweetAlert for notifications.
2. Backend:Java Servlets, JDBC (Java Database Connectivity)
3. Database: Oracle SQL
4. Security: Password hashing using BCrypt
5. Server: Apache Tomcat

🗃️ Project Structure
---------------------
📁 Java Resources

src/main/java
 ┣ 📁bean_pojo            → JavaBeans for Admin, User, Book.
 ┣ 📁controller           → Handles HTTP requests and responses.
 ┣ 📁dao                  → Database operations using JDBC.
 ┣ 📁service              → Password hashing service.
📁 Web Content

src/main/webapp
 ┣ 📁WEB-INF
    ┣ 📁lib               → Dependencies (bcrypt, ojdbc)
    ┗ 📄 web.xml          → Servlet configuration
 ┣ 📁css                  → Stylesheets
 ┣ 📄 JSP Pages           → Registration, Login, Book Requests


🗃️ Database Schema
-----------------
📚 Tables

📚 Book Table
---------------
CREATE TABLE book (
    bookid VARCHAR2(6) PRIMARY KEY,
    bookname VARCHAR2(30) NOT NULL,
    author VARCHAR2(45) NOT NULL,
    price NUMBER NOT NULL,
    quantity NUMBER NOT NULL
);

desc Book;
-----------
 Name                    Null?    Type
 ----------------------- -------- ----------------
 BOOKID                  NOT NULL VARCHAR2(6)
 BOOKNAME                         VARCHAR2(30)
 AUTHOR                           VARCHAR2(45)
 PRICE                            NUMBER
 QUANTITY                         NUMBER




👤 Admin Table
---------------
CREATE TABLE admin (
    email VARCHAR2(100) PRIMARY KEY,
    name VARCHAR2(50) NOT NULL,
    phone varchar2(10) NOT NULL
    DOB date,
    password VARCHAR2(255) NOT NULL  -- BCrypt hashed password
);


desc Admin;
------------
 Name                    Null?    Type
 ----------------------- -------- ----------------
 NAME                             VARCHAR2(50)
 EMAIL                            VARCHAR2(50)
 PHONE                            VARCHAR2(10)
 DOB                              DATE
 PASSWORD                         VARCHAR2(60)


👥 User Table
----------------
CREATE TABLE library_user (
    user_id VARCHAR2(15) PRIMARY KEY,
    name VARCHAR2(50) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    password VARCHAR2(255) NOT NULL, -- Store hashed password
    role VARCHAR2(10) NOT NULL CHECK (role IN ('USER', 'ADMIN')) -- Role-based access
);


desc library_user;
-------------------
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 USER_ID                                   NOT NULL VARCHAR2(10)
 NAME                                      NOT NULL VARCHAR2(50)
 EMAIL                                     NOT NULL VARCHAR2(100)
 PASSWORD                                  NOT NULL VARCHAR2(255)
 ROLE                                      NOT NULL VARCHAR2(10)



📄 Book Request Table
-----------------------
CREATE TABLE book_request (
  request_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  bookname VARCHAR2(50) NOT NULL,
  author VARCHAR2(50) NOT NULL,
  requested_by VARCHAR2(50) NOT NULL,
  status VARCHAR2(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED')),
  request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );

 desc book_request;
 ------------------------
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 REQUEST_ID                                NOT NULL NUMBER
 BOOKNAME                                  NOT NULL VARCHAR2(50)
 AUTHOR                                    NOT NULL VARCHAR2(50)
 REQUESTED_BY                              NOT NULL VARCHAR2(50)
 STATUS                                             VARCHAR2(20)
 REQUEST_DATE                                       TIMESTAMP(6)




🚀 Installation & Setup
-------------------------
Clone the repository: git clone https://github.com/BharatBhagyajyoti/Library-Management

Database Setup:
--------------
1, Import the provided SQL schema into your Oracle SQL database.
. Update the database connection details in DBinfo.java.

Deploy the Project:
-------------------
1. Use Apache Tomcat as the server.
2. Deploy the project and start the server.

Access the Application:

Open " http://localhost:8080/LibraryManagement/ " in your browser.

✅ Testing
-----------
Admin Side
............
1. Admin Login
2. Add, Update, Delete, and View Books
3. Approve/Reject Book Requests
4. Secure Logout

User Side
..........
1. User Registration and Login
2. Submit Book Requests
3. View Book Requests
4. Search Books
5. Logout

🔥 Future Enhancements
-----------------------
1. Add Pagination for book requests and book lists.
2. Include Forgot Password functionality with OTP verification.
3. Export book data as PDF/Excel.
4. Improve UI/UX with better animations and responsive design.

🚀 Contributor
---------------
Bharat Bhagyajyoti Mahanta - Developer
