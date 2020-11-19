INSERT INTO Category (name) VALUES ('Dresses');
INSERT INTO Category (name) VALUES ('T-shirts');
INSERT INTO Category (name) VALUES ('Jackets');
INSERT INTO Category (name) VALUES ('Blouses');

INSERT INTO Product (name, categoryId, price, imageUrl, description) VALUES ('Puff-sleeved sequined dress', (SELECT id FROM Category WHERE name='Dresses'), 29.99, 'https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F97%2Ff2%2F97f2794870328c37794671f720d46f6b45984077.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5B%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]', 'Short dress in sequined mesh with a small stand-up collar, a concealed zip at the back and a hook-and-eye fastener at the back of the neck. Short, draped puff sleeves, a seam at the waist and a gently flared skirt. Lined in jersey made from recycled polyester.');
INSERT INTO Product (name, categoryId, price, imageUrl, description) VALUES ('T-shirt Long Fit', (SELECT id FROM Category WHERE name='T-shirts'), 6.99, 'https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F11%2F27%2F1127c1c0c94616e819660927e437a540e84b4db0.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bmen_tshirtstanks_shortsleeve%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]', 'Long, round-necked T-shirt in soft jersey with a curved hem.');
