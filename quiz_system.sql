-- Create Database
CREATE DATABASE IF NOT EXISTS quiz_system;
USE quiz_system;

-- Drop tables if already exist (safe clean start)
DROP TABLE IF EXISTS quiz_results;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('student', 'teacher') NOT NULL,
    subject VARCHAR(100)
);

-- Create questions table
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(100) NOT NULL,
    question TEXT NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_option CHAR(1) NOT NULL
);

-- Create quiz_results table
CREATE TABLE quiz_results (
  
    student_id INT NOT NULL,
    subject VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES users(id)
);


INSERT INTO users (username, password, role, subject) VALUES
('teacher1', 'python123', 'teacher', 'Python'),
('teacher2', 'java123', 'teacher', 'Java'),
('teacher3', 'c123', 'teacher', 'C'),
('teacher4', 'c++123', 'teacher', 'C++'),
('teacher5', 'css123', 'teacher', 'CSS');

INSERT INTO users (username, password, role) VALUES
('student1', 'pass101', 'student'),
('student2', 'pass102', 'student'),
('student3', 'pass103', 'student'),
('student4', 'pass104', 'student'),
('student5', 'pass105', 'student'),
('student6', 'pass106', 'student'),
('student7', 'pass107', 'student'),
('student8', 'pass108', 'student'),
('student9', 'pass109', 'student'),
('student10', 'pass110', 'student');

INSERT INTO questions (subject, question, option_a, option_b, option_c, option_d, correct_option) VALUES
('Python', 'Which keyword is used to define a function in Python?', 'def', 'function', 'define', 'fun', 'A'),
('Python', 'What is the output of 3 * "Python"?', 'PythonPythonPython', 'Python3', 'Error', '3Python', 'A'),
('Python', 'Which of the following is a Python data type?', 'Integer', 'Float', 'String', 'All of the above', 'D'),
('Python', 'Which symbol is used for comments in Python?', '//', '#', '--', '<!-- -->', 'B'),
('Python', 'What is the correct file extension for Python files?', '.pyth', '.pt', '.py', '.pyt', 'C');


INSERT INTO questions (subject, question, option_a, option_b, option_c, option_d, correct_option) VALUES
('Java', 'Which method is the starting point of a Java program?', 'start()', 'main()', 'run()', 'init()', 'B'),
('Java', 'Which keyword is used to inherit a class in Java?', 'this', 'super', 'extends', 'implements', 'C'),
('Java', 'Which of these is not a Java primitive type?', 'int', 'float', 'String', 'char', 'C'),
('Java', 'What is the size of int in Java?', '4 bytes', '2 bytes', '8 bytes', '1 byte', 'A'),
('Java', 'Which company originally developed Java?', 'Sun Microsystems', 'Microsoft', 'Oracle', 'IBM', 'A');


INSERT INTO questions (subject, question, option_a, option_b, option_c, option_d, correct_option) VALUES
('C', 'Who is the father of C language?', 'James Gosling', 'Bjarne Stroustrup', 'Dennis Ritchie', 'Guido van Rossum', 'C'),
('C', 'Which symbol is used to end a statement in C?', '.', ':', ';', ',', 'C'),
('C', 'Which header file is needed for printf function?', 'stdio.h', 'conio.h', 'stdlib.h', 'math.h', 'A'),
('C', 'Which of the following is not a loop in C?', 'for', 'while', 'loop', 'do-while', 'C'),
('C', 'What is the correct syntax to declare a variable in C?', 'var x;', 'int x;', 'declare x;', 'x int;', 'B');

INSERT INTO questions (subject, question, option_a, option_b, option_c, option_d, correct_option) VALUES
('C++', 'C++ is an extension of which language?', 'Python', 'Java', 'C', 'Ruby', 'C'),
('C++', 'Which operator is used for scope resolution in C++?', '::', '.', '->', ':', 'A'),
('C++', 'What is the correct syntax of a constructor in C++?', 'void constructor()', 'ClassName()', 'constructor()', 'new ClassName()', 'B'),
('C++', 'Which concept allows using same function name for different purposes?', 'Inheritance', 'Polymorphism', 'Encapsulation', 'Abstraction', 'B'),
('C++', 'What is the default access specifier for class members in C++?', 'public', 'protected', 'private', 'friend', 'C');

INSERT INTO questions (subject, question, option_a, option_b, option_c, option_d, correct_option) VALUES
('CSS', 'What does CSS stand for?', 'Computer Style Sheets', 'Cascading Style Sheets', 'Creative Style Sheets', 'Colorful Style Sheets', 'B'),
('CSS', 'Which HTML tag is used to link an external CSS file?', '<css>', '<style>', '<link>', '<script>', 'C'),
('CSS', 'Which property changes the background color?', 'color', 'background-color', 'bgcolor', 'background', 'B'),
('CSS', 'Which value of position property is used to fix an element at a specific location?', 'absolute', 'fixed', 'relative', 'static', 'B'),
('CSS', 'How do you select an element with id "header" in CSS?', '#header', '.header', 'header', '*header', 'A');
