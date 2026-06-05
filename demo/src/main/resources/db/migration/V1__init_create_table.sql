CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(255),
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL DEFAULT 'READER',
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    category_id BIGSERIAL PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE publishers (
    publisher_id SERIAL PRIMARY KEY,
    publisher_name VARCHAR(150) NOT NULL UNIQUE,
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE books (
    book_id BIGSERIAL PRIMARY KEY,
    category_id BIGINT NOT NULL,
    publisher_id INTEGER,
    title VARCHAR(200) NOT NULL,
    author_name VARCHAR(100),
    publisher_name VARCHAR(150),
    isbn VARCHAR(50) UNIQUE,
    publication_year INT,
    description TEXT,
    total_quantity INT NOT NULL DEFAULT 0,
    available_quantity INT NOT NULL DEFAULT 0,
    shelf_location VARCHAR(100),
    status VARCHAR(30) NOT NULL DEFAULT 'AVAILABLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_books_category
        FOREIGN KEY (category_id)
            REFERENCES categories(category_id),
    CONSTRAINT fk_book_publisher
        FOREIGN KEY (publisher_id)
            REFERENCES publishers(publisher_id)
);

CREATE TABLE borrow_tickets (
    ticket_id BIGSERIAL PRIMARY KEY,
    reader_id BIGINT NOT NULL,
    librarian_id BIGINT,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'BORROWING',
    note VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_borrow_tickets_reader
        FOREIGN KEY (reader_id)
            REFERENCES users(user_id),
    CONSTRAINT fk_borrow_tickets_librarian
        FOREIGN KEY (librarian_id)
            REFERENCES users(user_id)
);

CREATE TABLE borrow_details (
    borrow_detail_id BIGSERIAL PRIMARY KEY,
    ticket_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    return_date DATE,
    status VARCHAR(30) NOT NULL DEFAULT 'BORROWING',
    fine_amount DECIMAL(10,2) NOT NULL DEFAULT 0,
    fine_reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_borrow_details_ticket
        FOREIGN KEY (ticket_id)
            REFERENCES borrow_tickets(ticket_id),
    CONSTRAINT fk_borrow_details_book
        FOREIGN KEY (book_id)
            REFERENCES books(book_id)
);
