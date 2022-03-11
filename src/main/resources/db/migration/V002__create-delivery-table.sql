CREATE TABLE delivery (
    id SERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    fare DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    order_date TIMESTAMP NOT NULL,
    delivery_date TIMESTAMP,
    receiver_name VARCHAR(60) NOT NULL,
    receiver_street VARCHAR(255) NOT NULL,
    receiver_number VARCHAR(30) NOT NULL,
    receiver_complement VARCHAR(60) NOT NULL,
    receiver_district VARCHAR(30) NOT NULL
);

ALTER TABLE delivery ADD CONSTRAINT fk_delivery_client
FOREIGN KEY (client_id) REFERENCES client (id);