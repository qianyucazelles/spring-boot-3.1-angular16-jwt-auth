DROP SCHEMA IF EXISTS `backenddb`;
CREATE SCHEMA IF NOT EXISTS `backenddb`;
USE `backenddb`;

-- CREATE TABLE `app_user` (
--   `id` bigint AUTO_INCREMENT NOT NULL,
--   `nom` varchar(255) NOT NULL,
--   `prenom` varchar(255) NOT NULL,
--   `password` varchar(255) NOT NULL,
--   `user_name` varchar(255) unique,
--   PRIMARY KEY (`id`)
-- );

-- CREATE TABLE `roles` (
--   `id` bigint AUTO_INCREMENT NOT null,
--   `name` varchar(255) NOT NULL unique,
--   PRIMARY KEY (`id`)
-- );

-- INSERT INTO roles (name) 
-- VALUES 
-- ("USER"),
-- ("ADMIN")

-- CREATE TABLE `user_roles` (
--   `user_id` bigint ,
--   `role_id` bigint,
--   CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
--   CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
--   PRIMARY KEY (`user_id`,`role_id`)
-- );