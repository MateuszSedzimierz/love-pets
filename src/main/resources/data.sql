-- Authorities --
INSERT INTO authorities (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO authorities (id, name) VALUES (3, 'ROLE_ANONYMOUS');

-- Example pet types --
INSERT INTO pet_types (name, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('Dog', 'SYSTEM', now(), 'SYSTEM', now());

INSERT INTO pet_types (name, created_by, created_date, last_modified_by, last_modified_date)
VALUES ('Cat', 'SYSTEM', now(), 'SYSTEM', now());

-- Example users with addresses and authorities --

-- (login: james, password: admin, authority: admin) --
INSERT INTO addresses (city, country)
VALUES ('England', 'London');

INSERT INTO users (created_by, created_date, last_modified_by, last_modified_date, email, first_name, last_name, login, password_hash, phone_number, address_id)
VALUES ('system', now(), 'system', now(), 'bond007@example.com', 'James', 'Bond', 'james', '$2a$10$j6nqLce77MZKDQvy08UDreTaZSjBS1/SgTeNupT16Hwp/BOLqeNbu', '623 121 007', 1);

INSERT INTO users_authorities (user_id, authority_id)
VALUES (1, 1), (1, 2);

-- (login: john, password: example, authority: user) --
INSERT INTO addresses (city, country, street_address)
VALUES ('Warsaw', 'Poland', 'Złota 21');

INSERT INTO users (created_by, created_date, last_modified_by, last_modified_date, email, first_name, last_name, login, password_hash, phone_number, address_id)
VALUES ('system', now(), 'system', now(), 'j.black@example.com', 'John', 'Black', 'john', '$2a$10$398bPeS3Xru6WiBcXs9NGeQmErJ910pUHkwatogD3czZtYkZ2yosa', '654 422 122', 2);

INSERT INTO users_authorities (user_id, authority_id)
VALUES (2, 2);

-- (login: anne, password: example, authority: user) --
INSERT INTO addresses (city, country)
VALUES ('Paris', 'France');

INSERT INTO users (created_by, created_date, last_modified_by, last_modified_date, email, first_name, last_name, login, password_hash, phone_number, address_id)
VALUES ('system', now(), 'system', now(), 'a.smith@gmail.com', 'Anne', 'Smith', 'anne', '$2a$10$398bPeS3Xru6WiBcXs9NGeQmErJ910pUHkwatogD3czZtYkZ2yosa', '512 131 121', 3);

INSERT INTO users_authorities (user_id, authority_id)
VALUES (3, 2);

-- Example pets with addresses --

-- Molly --
INSERT INTO addresses (city, country)
VALUES ('Warsaw', 'Poland');

INSERT INTO pet_details(age, description, gender, size, sterilized)
VALUES ('YOUNG', 'Molly is a calm, friendly dog. She loves children.', 'FEMALE', 'MEDIUM', true);

INSERT INTO pets(created_by, created_date, last_modified_by, last_modified_date, adopted, image_url, name, address_id, owner_id, pet_details_id, pet_type_id)
VALUES ('john', now(), 'john', now(), false, '1/molly.jpg', 'Molly', 4, 2, 1, 1);

-- Shadow --
INSERT INTO addresses (city, country)
VALUES ('Paris', 'France');

INSERT INTO pet_details(age, description, gender, size, sterilized)
VALUES ('ADULT', 'Every time is good for nap ...', 'MALE', 'LARGE', false);

INSERT INTO pets(created_by, created_date, last_modified_by, last_modified_date, adopted, image_url, name, address_id, owner_id, pet_details_id, pet_type_id)
VALUES ('anne', now(), 'anne', now(), false, '2/shadow.jpg', 'Shadow', 5, 3, 2, 2);

-- Hunter --
INSERT INTO addresses (city, country)
VALUES ('Germany', 'Berlin');

INSERT INTO pet_details(age, description, gender, size, sterilized)
VALUES ('ADULT', 'Friendly dog, a true friend.', 'MALE', 'LARGE', true);

INSERT INTO pets(created_by, created_date, last_modified_by, last_modified_date, adopted, adoption_date, image_url, name, address_id, owner_id, pet_details_id, pet_type_id)
VALUES ('anne', now(), 'anne', now(), true, now(), '3/hunter.jpg','Hunter', 6, 3, 3, 1);

