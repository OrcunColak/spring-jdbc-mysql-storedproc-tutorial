CREATE TABLE product_table (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(255),
    category VARCHAR(255),
    price DECIMAL(10, 2),
    availability VARCHAR(50)
);

INSERT INTO product_table (product_id, product_name, category, price, availability) VALUES
(1, 'Laptop', 'Electronics', 1000.00, 'In Stock'),
(2, 'Smartphone', 'Electronics', 700.00, 'In Stock'),
(3, 'Shoes', 'Fashion', 50.00, 'Out of Stock'),
(4, 'Watch', 'Fashion', 150.00, 'In Stock');

DELIMITER $$

CREATE PROCEDURE getDistinctProducts(
    IN category VARCHAR(255),
    IN minPrice DECIMAL(10,2),
    IN maxPrice DECIMAL(10,2),
    IN availability VARCHAR(50)
)
BEGIN
    SELECT DISTINCT product_id, product_name, price, availability
    FROM product_table
    WHERE (category = category OR category = '')
      AND (price BETWEEN minPrice AND maxPrice)
      AND (availability = availability OR availability = '');
END$$

DELIMITER ;
