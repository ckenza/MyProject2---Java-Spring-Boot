CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS category(
    id_category int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    category_name VARCHAR(255),
    id_user int FOREIGN KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS film(
    id_film int PRIMARY KEY NOT NULL,
    title_film VARCHAR(255),
    image_url VARCHAR(300),
    genre VARCHAR(255),
    released_date date,
    overview TEXT
);