USE Boeking_Systeem;
GO

-- Clear existing data (order matters due to foreign key constraints)
DELETE FROM Boeking;
DELETE FROM Overnachting;
DELETE FROM Bestemming;
GO

-- Reset identity seeds for repeatable inserts
DBCC CHECKIDENT ('Bestemming', RESEED, 0);
DBCC CHECKIDENT ('Overnachting', RESEED, 0);
DBCC CHECKIDENT ('Boeking', RESEED, 0);
GO

-- Insert records into Bestemming (8 destinations)
INSERT INTO Bestemming (naam, beschrijving)
VALUES 
  ('Amsterdam', 'Populaire stad met grachten en musea'),
  ('Parijs', 'De stad van de liefde en de Eiffeltoren'),
  ('New York', 'De stad die nooit slaapt'),
  ('Barcelona', 'Mooie stranden en levendige cultuur'),
  ('Zwitserse Alpen', 'Perfecte bestemming voor wintersport'),
  ('Malediven', 'Luxe resorts en witte zandstranden'),
  ('Lapland', 'Noorderlicht en sneeuwavonturen'),
  ('Dubai', 'Moderne architectuur en luxe lifestyle');
GO

-- Insert records into Overnachting (extra data added)
INSERT INTO Overnachting (hotel_naam, begin_datum, eind_datum,  bestemming_id)
VALUES 
  ('Winter Retreat',  '2025-12-20', '2025-12-22',  1),
  ('Sneeuwparadijs',  '2025-12-20', '2025-12-22',  5),
  ('Island Dream',  '2025-12-20', '2025-12-22',  6),
  ('Lapland Iglo Resort',  '2025-12-20', '2025-12-22',  7),
  ('Luxury Dubai Stay', '2025-12-20', '2025-12-22', 8),
  ('Canal View Hotel',  '2025-12-20', '2025-12-22',  1),
  ('Romantic Paris Inn', '2025-12-20', '2025-12-22', 2),
  ('Times Square Hotel',  '2025-12-20', '2025-12-22', 3),
  ('Barcelona Beach Resort',  '2025-12-20', '2025-12-22', 4),
  ('Swiss Mountain Lodge',  '2025-12-20', '2025-12-22',  5),
  ('Maldives Overwater Bungalow',  '2025-12-20', '2025-12-22',  6),
  ('Northern Lights Cabin',  '2025-12-20', '2025-12-22',  7),
  ('Burj View Suite', '2025-12-20', '2025-12-22', 8),
  ('Amsterdam Central Stay', '2025-12-20', '2025-12-22', 1),
  ('Grachtengordel Boutique',  '2025-12-20', '2025-12-22', 1);
GO

-- Insert records into Boeking (extra data added)
INSERT INTO Boeking (boeking_datum, interne_boeking, hotel_naam, begin_datum, eind_datum)
VALUES 
  ('2025-12-01', 1, 'Winter Retreat',  '2025-12-20', '2025-12-22'),
  ('2025-12-02', 1, 'Sneeuwparadijs',  '2025-12-20', '2025-12-22'),
  ('2025-12-03', 1, 'Island Dream',  '2025-12-20', '2025-12-22'),
  ('2025-12-04', 1, 'Lapland Iglo Resort', '2025-12-20', '2025-12-22'),
  ('2025-12-05', 1, 'Luxury Dubai Stay',  '2025-12-20', '2025-12-22'),
  ('2025-12-06', 1, 'Canal View Hotel',  '2025-12-20', '2025-12-22'),
  ('2025-12-07', 1, 'Amsterdam Central Stay',  '2025-12-20', '2025-12-22'),
  ('2025-12-08', 1, 'Grachtengordel Boutique', '2025-12-20', '2025-12-22');
GO
