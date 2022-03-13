CREATE TABLE event (
    id SERIAL PRIMARY KEY,
    delivery_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    register_date TIMESTAMP NOT NULL
);

ALTER TABLE event ADD CONSTRAINT fk_event_delivery
FOREIGN KEY (delivery_id) REFERENCES delivery (id);