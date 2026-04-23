CREATE TYPE booking_status AS ENUM ('pending', 'confirmed', 'cancelled', 'completed');

CREATE TYPE payment_method AS ENUM ('cash', 'credit_card', 'debit_card', 'pix');

CREATE TABLE booking (
    id BIGSERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    bedroom_id BIGINT NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    status booking_status NOT NULL DEFAULT 'pending',
    payment_method payment_method NOT NULL,
    notes VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_booking_client
        FOREIGN KEY (client_id)
        REFERENCES client (id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_booking_bedroom
        FOREIGN KEY (bedroom_id)
        REFERENCES bedroom (id)
        ON DELETE RESTRICT,
    CONSTRAINT ck_booking_dates
        CHECK (check_out > check_in),
    CONSTRAINT ck_booking_total_price
        CHECK (total_price >= 0)
);

CREATE INDEX idx_booking_client_id ON booking (client_id);
CREATE INDEX idx_booking_bedroom_id ON booking (bedroom_id);
CREATE INDEX idx_booking_status ON booking (status);
CREATE INDEX idx_booking_check_in_check_out ON booking (check_in, check_out);
