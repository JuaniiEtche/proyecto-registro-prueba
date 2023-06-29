INSERT INTO rol (nombre) VALUES ('admin'), ('user');

INSERT INTO `linea_investigacion` (`id_linea`, `descripcion`, `nombre`) VALUES (NULL, 'Se desarrolla software', 'Desarrollo de software'), (NULL, 'Se diseña web', 'Diseño web');

INSERT INTO `persona` (`dni`, `estado`, `legajo`, `id_persona`, `num_telefono`, `usuario_id_usuario`, `apellido`, `email`, `nombre`) VALUES (43311953, b'0', 0, 1, 2215617704, 1, 'Etcheverry', 'juanietcheverry@gmail.com', 'Juan Ignacio'),(NULL, 2, 2, '$argon2i$v=19$m=1024,t=1,p=1$7Xc/9yV/3kRK6JL1GbstPQ$hJs+KCBn3GET/GrtZA2uu7b1L0qmF6wBabIH4T9KCHg', 'jorge');

INSERT INTO `usuario` (`role_id_rol`, `id_usuario`, `persona_id_persona`, `contraseña`, `nombre`) VALUES (NULL, 1, 1, '$argon2i$v=19$m=1024,t=1,p=1$IKvcfi6Fdr+2s4cYeTti/w$7hmSkk0lZXfhlmS9NbduM3A6AY5GqV0r1BzqbEEVLUI', 'pepito'),(43311123, b'0', 0, 2, 22156123, 2, 'Rodolfo', 'asdawdwdawd@gmail.com', 'Jorge');

INSERT INTO `usuarioxlinea` (`id_usuarioxlinea`, `linea_investigacion_id_linea`, `usuario_id_usuario`) VALUES (NULL, '1', '1'), (NULL, '2', '2');

INSERT INTO gabinete (nombre) VALUES ('gabinete1'), ('gabinete2');
