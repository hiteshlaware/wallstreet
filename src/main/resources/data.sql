-- Insert default securities with random prices
INSERT INTO security (id, security_type, price, symbol) VALUES (1, 'COM', 2845.50, 'GOOG');  -- Google
INSERT INTO security (id, security_type, price, symbol) VALUES (2, 'COM', 3456.75, 'AMZN');  -- Amazon
INSERT INTO security (id, security_type, price, symbol) VALUES (3, 'COM', 325.20, 'META');   -- Meta
INSERT INTO security (id, security_type, price, symbol) VALUES (4, 'COM', 378.90, 'MSFT');   -- Microsoft
INSERT INTO security (id, security_type, price, symbol) VALUES (5, 'COM', 189.45, 'AAPL');   -- Apple

-- Insert sample accounts with dummy data
INSERT INTO account (id, name, username, password, email, phone) VALUES 
(1001, 'John Smith', 'jsmith', 'pass123', 'john.smith@email.com', '555-0101'),
(1002, 'Sarah Johnson', 'sjohnson', 'pass456', 'sarah.j@email.com', '555-0102'),
(1003, 'Michael Brown', 'mbrown', 'pass789', 'michael.b@email.com', '555-0103'),
(1004, 'Emily Davis', 'edavis', 'pass321', 'emily.d@email.com', '555-0104'),
(1005, 'David Wilson', 'dwilson', 'pass654', 'david.w@email.com', '555-0105');

INSERT INTO orders (id, account_id, security_id, order_type, price, quantity, amount) VALUES 
(1001, 1001, 1, 'BUY', 2800.00, 1.0, 2800.00),
(1002, 1002, 2, 'SELL', 3500.00, 1.0, 3500.00),
(1003, 1003, 3, 'BUY', 330.00, 1.0, 330.00),
(1004, 1004, 4, 'SELL', 380.00, 1.0, 380.00),
(1005, 1005, 5, 'BUY', 190.00, 1.0, 190.00);