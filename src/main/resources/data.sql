-- Marcas
INSERT INTO marcas (nombre, pais_origen) VALUES ('Fender', 'EE.UU.');
INSERT INTO marcas (nombre, pais_origen) VALUES ('Gibson', 'EE.UU.');
INSERT INTO marcas (nombre, pais_origen) VALUES ('Yamaha', 'Japón');
INSERT INTO marcas (nombre, pais_origen) VALUES ('Ibanez', 'Japón');
INSERT INTO marcas (nombre, pais_origen) VALUES ('Taylor', 'EE.UU.');
INSERT INTO marcas (nombre, pais_origen) VALUES ('Roland', 'Japón');

-- Categorías
INSERT INTO categorias (nombre, descripcion) VALUES ('Cuerdas', 'Instrumentos que producen sonido mediante la vibración de cuerdas.');
INSERT INTO categorias (nombre, descripcion) VALUES ('Viento', 'Instrumentos que producen sonido por la vibración del aire en su interior.');
INSERT INTO categorias (nombre, descripcion) VALUES ('Percusión', 'Instrumentos que se tocan golpeándolos o sacudiéndolos.');
INSERT INTO categorias (nombre, descripcion) VALUES ('Teclados', 'Instrumentos con teclas que activan un mecanismo para producir sonido.');

-- Instrumentos
-- Guitarras (Cuerdas)
INSERT INTO instrumentos (nombre, tipo, precio, descripcion, stock, marca_id, categoria_id) VALUES ('Stratocaster', 'Eléctrica', 1200.00, 'Guitarra eléctrica versátil, ideal para rock, blues y pop.', 15, 1, 1);
INSERT INTO instrumentos (nombre, tipo, precio, descripcion, stock, marca_id, categoria_id) VALUES ('Les Paul', 'Eléctrica', 2500.00, 'Guitarra icónica con un sonido potente y sostenido.', 10, 2, 1);
INSERT INTO instrumentos (nombre, tipo, precio, descripcion, stock, marca_id, categoria_id) VALUES ('Pacifica 112V', 'Eléctrica', 300.00, 'Excelente guitarra para principiantes y nivel intermedio.', 25, 3, 1);
INSERT INTO instrumentos (nombre, tipo, precio, descripcion, stock, marca_id, categoria_id) VALUES ('214ce', 'Acústica', 999.00, 'Guitarra acústica con gran resonancia y comodidad.', 12, 5, 1);

-- Teclados
INSERT INTO instrumentos (nombre, tipo, precio, descripcion, stock, marca_id, categoria_id) VALUES ('P-45', 'Piano Digital', 450.00, 'Piano digital de 88 teclas contrapesadas, ideal para estudiantes.', 20, 3, 4);
INSERT INTO instrumentos (nombre, tipo, precio, descripcion, stock, marca_id, categoria_id) VALUES ('Juno-DS88', 'Sintetizador', 1000.00, 'Sintetizador de 88 teclas con una amplia variedad de sonidos profesionales.', 8, 6, 4);

-- Baterías (Percusión)
INSERT INTO instrumentos (nombre, tipo, precio, descripcion, stock, marca_id, categoria_id) VALUES ('Imperialstar', 'Acústica', 750.00, 'Set de batería completo, perfecto para empezar a tocar.', 18, 4, 3);

-- Reseñas
INSERT INTO resenas (usuario, calificacion, comentario, instrumento_id) VALUES ('rockstar82', 5, 'El sonido de la Stratocaster es simplemente legendario. Vale cada centavo.', 1);
INSERT INTO resenas (usuario, calificacion, comentario, instrumento_id) VALUES ('guitar_hero', 4, 'Muy cómoda de tocar, aunque un poco pesada. El sustain es increíble.', 2);
INSERT INTO resenas (usuario, calificacion, comentario, instrumento_id) VALUES ('principiante_feliz', 5, 'No puedo creer la calidad de la Pacifica por este precio. La recomiendo 100%.', 3);
INSERT INTO resenas (usuario, calificacion, comentario, instrumento_id) VALUES ('pianista_clasico', 4, 'Las teclas del P-45 se sienten muy bien. El sonido de piano es bastante realista.', 5);
INSERT INTO resenas (usuario, calificacion, comentario, instrumento_id) VALUES ('productor_musical', 5, 'El Juno es una navaja suiza de sonidos. Increíblemente versátil para el estudio.', 6);
