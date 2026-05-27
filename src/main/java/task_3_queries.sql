// 1 task
SELECT * FROM users
WHERE age > 18;

// 2 task
SELECT DISTINCT city
FROM customers;

// 3 task
SELECT COUNT(*) AS total_orders
FROM orders;

// 4 task
SELECT * FROM employees
WHERE salary = (SELECT MAX(salary) FROM employees);

// 5 task
UPDATE users
SET email = 'new_email@example.com'
WHERE id = 5;

// 6 task
DELETE FROM sessions
WHERE expiration_date < CURRENT_DATE;

// 7 task
SELECT * FROM orders
WHERE order_date >= CURRENT_DATE - INTERVAL '1 month';

// 8 task
SELECT * FROM users u
                  INNER JOIN orders o ON u.user_id = o.user_id;