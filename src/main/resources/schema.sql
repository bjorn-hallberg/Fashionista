CREATE TABLE Product(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  categoryId BIGINT NOT NULL,
  price NUMERIC(18,2),
  imageUrl TEXT,
  description TEXT
);

CREATE TABLE Category(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

ALTER TABLE Product ADD FOREIGN KEY (categoryId) REFERENCES Category(id);

CREATE TABLE Customer(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(50) NOT NULL,
  lastName VARCHAR(50) NOT NULL,
  email VARCHAR(100),
  address VARCHAR(50),
  address2 VARCHAR(50),
  country VARCHAR(50),
  state VARCHAR(50),
  zip VARCHAR(50)
);

CREATE TABLE Orders(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customerId BIGINT NOT NULL,
  orderDate DATE NOT NULL DEFAULT CURDATE(),
  totalAmount NUMERIC(18,2) NOT NULL
);

CREATE TABLE OrderRow(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  orderId BIGINT NOT NULL,
  productId BIGINT NOT NULL,
  quantity INT NOT NULL
);

ALTER TABLE Orders ADD FOREIGN KEY (customerId) REFERENCES Customer(id);
ALTER TABLE OrderRow ADD FOREIGN KEY (orderId) REFERENCES Orders(id);
ALTER TABLE OrderRow ADD FOREIGN KEY (productId) REFERENCES Product(id);
