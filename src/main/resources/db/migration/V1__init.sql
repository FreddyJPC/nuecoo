CREATE TABLE IF NOT EXISTS fill_event (
    id SERIAL,
    fill_level INT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS color_error_event (
    id SERIAL,
    detected_color VARCHAR(50) NOT NULL,
    expected_color VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );
