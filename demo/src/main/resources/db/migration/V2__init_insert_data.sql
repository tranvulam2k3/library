INSERT INTO users
(full_name, email, phone, address, username, password_hash, role, status)
VALUES
    ('Admin User', 'admin@example.com', '0900000001', 'Da Nang', 'admin', '123456', 'ADMIN', 'ACTIVE'),
    ('Librarian User', 'librarian@example.com', '0900000002', 'Da Nang', 'librarian', '123456', 'LIBRARIAN', 'ACTIVE'),
    ('Reader User', 'reader@example.com', '0900000003', 'Da Nang', 'reader', '123456', 'READER', 'ACTIVE');

INSERT INTO categories
(category_name, description)
VALUES
    ('Information Technology', 'Books about programming, database, and software development'),
    ('Business', 'Books about business and management'),
    ('Literature', 'Novels and literary books');

INSERT INTO publishers
(publisher_name, email, phone, address)
VALUES
    ('Tech Publisher', 'tech@example.com', '0987654321', 'Ha Noi'),
    ('Business Publisher', 'business@example.com', '0987654322', 'Ho Chi Minh');

INSERT INTO books
(category_id, publisher_id, title, author_name, publisher_name, isbn, publication_year, description, total_quantity, available_quantity, shelf_location, status)
VALUES
    (1, 1, 'Java Programming Basic', 'Nguyen Van A', 'Tech Publisher', 'ISBN001', 2024, 'Basic Java book', 10, 10, 'A1', 'AVAILABLE'),
    (1, 1, 'Spring Boot for Beginners', 'Tran Van B', 'Tech Publisher', 'ISBN002', 2025, 'Spring Boot basic guide', 5, 5, 'A2', 'AVAILABLE'),
    (2, 2, 'Business Management', 'Le Van C', 'Business Publisher', 'ISBN003', 2023, 'Basic business management book', 7, 7, 'B1', 'AVAILABLE');
