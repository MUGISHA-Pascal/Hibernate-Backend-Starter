-- Initialize the studentdb database
-- This script will be executed when the PostgreSQL container starts for the first time

-- Create the database if it doesn't exist (this is handled by POSTGRES_DB environment variable)
-- \c studentdb;

-- You can add any initial data or schema modifications here
-- For example, if you want to create some initial tables or insert sample data:

-- Example: Create a sample table (optional, as Hibernate will create tables automatically)
-- CREATE TABLE IF NOT EXISTS sample_table (
--     id SERIAL PRIMARY KEY,
--     name VARCHAR(255) NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- Example: Insert sample data (optional)
-- INSERT INTO sample_table (name) VALUES ('Sample Entry 1'), ('Sample Entry 2');

-- The application will handle table creation through Hibernate's ddl-auto=update 