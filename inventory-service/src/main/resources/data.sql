

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-001', 'MacBook Pro 16', 'MacBook Pro ARM 64GB ', 50, 5, 1200.00, 4.5, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-002', 'Magic Trackpad', 'Magic Trackpad', 200, 20, 25.50, 0.1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-003', 'Mechanical Keyboard', 'Mechanical keyboard with bluetooth', 75, 10, 89.99, 0.8, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-004', 'Apple Watch', 'Apple Watch series 4', 150, 15, 450.00, 0.2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-005', 'Apple Home mini', 'Apple Home mini', 150, 33, 99.00, 0.2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-006', 'iPhone X', 'iPhone X', 150, 15, 45.00, 0.2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-007', 'Apple Display XDR', 'Apple Display XDR', 150, 15, 4500.00, 0.2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-008', 'Apple Mac Studio', 'Apple Mac Studio', 150, 15, 3599.00, 0.2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-009', 'iPad Pro 11 inch', 'iPad Pro 11 inch', 150, 15, 999.00, 0.2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, current_stock, min_stock, unit_price, weight, active, created_at, updated_at)
VALUES ('PROD-010', 'USB-C Hub', '7-in-1 multi-port adapter for USB-C laptops', 150, 15, 45.00, 0.2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--
INSERT INTO movements (product_id, quantity, reason, operation_name, timestamp)
VALUES (1, 10, 'Reabastecimiento de las MacBook Pro', 'in', CURRENT_TIMESTAMP);

INSERT INTO movements (product_id, quantity, reason, operation_name, timestamp)
VALUES (2, 50, 'Se recibio un nuevo pedido para ser enviado.', 'in', CURRENT_TIMESTAMP);

INSERT INTO movements (product_id, quantity, reason, operation_name, timestamp)
VALUES (3, 20, 'Liquidacion de Keyboards', 'in', CURRENT_TIMESTAMP);

INSERT INTO movements (product_id, quantity, reason, operation_name, timestamp)
VALUES (1, -2, 'Unidades dañadas', 'out', CURRENT_TIMESTAMP);

INSERT INTO movements (product_id, quantity, reason, operation_name, timestamp)
VALUES (4, 30, 'Compra en lote', 'out', CURRENT_TIMESTAMP);
