UPDATE products p
SET icon_path = REPLACE(icon_path, 'src/main/resources/', '')
WHERE icon_path LIKE 'src/main/resources/%';

UPDATE products p
SET icon_path = REPLACE(icon_path, 'mega-image/', 'http://129.152.1.248/mega-image/')
WHERE icon_path LIKE 'mega-image/%';
