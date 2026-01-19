-- Reset database and insert test data

-- Clear existing data
DELETE FROM reservation;
DELETE FROM car;
DELETE FROM model;
DELETE FROM brand;
DELETE FROM user;

-- Insert Brands
INSERT INTO brand (id, nom) VALUES (1, 'Renault');
INSERT INTO brand (id, nom) VALUES (2, 'Peugeot');
INSERT INTO brand (id, nom) VALUES (3, 'Volkswagen');
INSERT INTO brand (id, nom) VALUES (4, 'Toyota');

-- Insert Models
INSERT INTO model (id, nom, annee, brand_id) VALUES (1, 'Clio', 2023, 1);
INSERT INTO model (id, nom, annee, brand_id) VALUES (2, 'Megane', 2022, 1);
INSERT INTO model (id, nom, annee, brand_id) VALUES (3, '208', 2023, 2);
INSERT INTO model (id, nom, annee, brand_id) VALUES (4, '308', 2022, 2);
INSERT INTO model (id, nom, annee, brand_id) VALUES (5, 'Golf', 2023, 3);
INSERT INTO model (id, nom, annee, brand_id) VALUES (6, 'Polo', 2022, 3);
INSERT INTO model (id, nom, annee, brand_id) VALUES (7, 'Corolla', 2023, 4);
INSERT INTO model (id, nom, annee, brand_id) VALUES (8, 'Yaris', 2022, 4);

-- Insert Test Users (password: admin123 and client123 - hashed with bcrypt)
INSERT INTO user (id, nom, prenom, email, password, telephone, adresse, role) 
VALUES (1, 'Admin', 'RapidLoc', 'admin@rapidloc.tn', 'admin123', '70123456', 'Tunis', 'ADMIN');

INSERT INTO user (id, nom, prenom, email, password, telephone, adresse, role) 
VALUES (2, 'Ahmed', 'Ben Salah', 'client@test.com', 'client123', '98765432', 'Sfax', 'CLIENT');

-- Insert Sample Cars
INSERT INTO car (id, immatriculation, couleur, kilometrage, prix_par_jour, statut, image_url, model_id) 
VALUES (1, '200 TUN 1234', 'Noir', 15000, 80, 'DISPONIBLE', NULL, 1);

INSERT INTO car (id, immatriculation, couleur, kilometrage, prix_par_jour, statut, image_url, model_id) 
VALUES (2, '201 TUN 5678', 'Blanc', 25000, 100, 'DISPONIBLE', NULL, 3);

INSERT INTO car (id, immatriculation, couleur, kilometrage, prix_par_jour, statut, image_url, model_id) 
VALUES (3, '202 TUN 9012', 'Gris', 8000, 120, 'DISPONIBLE', NULL, 5);

INSERT INTO car (id, immatriculation, couleur, kilometrage, prix_par_jour, statut, image_url, model_id) 
VALUES (4, '203 TUN 3456', 'Rouge', 12000, 90, 'DISPONIBLE', NULL, 7);
