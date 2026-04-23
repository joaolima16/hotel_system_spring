CREATE TYPE room_status AS ENUM ('available', 'unavailable');

CREATE TABLE bedroom (
    id BIGSERIAL PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    number VARCHAR(20) NOT NULL,
    floor INT,
    type VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    daily_rate NUMERIC(10, 2) NOT NULL,
    status room_status NOT NULL DEFAULT 'available',
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bedroom_hotel
        FOREIGN KEY (hotel_id)
        REFERENCES hotel (id)
        ON DELETE CASCADE,
    CONSTRAINT uq_bedroom_hotel_number
        UNIQUE (hotel_id, number)
);

CREATE INDEX idx_bedroom_hotel_id ON bedroom (hotel_id);
CREATE INDEX idx_bedroom_status ON bedroom (status);
