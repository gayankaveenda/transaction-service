-- Create the Customer table
/*
CREATE TABLE IF NOT EXISTS customer (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        first_name VARCHAR(255),
                                        last_name VARCHAR(255),
                                        email VARCHAR(255),
                                        location VARCHAR(255)
);

-- Create the Product table
CREATE TABLE IF NOT EXISTS product (
                                       product_code VARCHAR(255) PRIMARY KEY,
                                       cost DECIMAL(10, 2),
                                       status VARCHAR(255)
);

-- Create the Transaction table
CREATE TABLE IF NOT EXISTS transaction (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           transaction_time TIMESTAMP,
                                           customer_id BIGINT,
                                           quantity INT,
                                           product_code VARCHAR(255),
                                           FOREIGN KEY (customer_id) REFERENCES customer(id),
                                           FOREIGN KEY (product_code) REFERENCES product(product_code)
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_customer_location ON customer(location);
CREATE INDEX IF NOT EXISTS idx_transaction_customer_id ON transaction(customer_id);
CREATE INDEX IF NOT EXISTS idx_transaction_product_code ON transaction(product_code);
*/
