CREATE TABLE `db_lift_energy`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

  CREATE TABLE `db_lift_energy`.`authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_authorities_unique` (`user_id` ASC, `authority` ASC) VISIBLE,
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_lift_energy`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*Users and authorities*/
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$uoxBSo1vDV5UvB8VTPItu.8jLzJwMVfAnllZbl4yjpYWcpmXV1T1S', 1);
INSERT INTO users (username, password, enabled) VALUES ('operario', '$2a$10$YV65ExjewVvYKW10p6mhwugtMlvYVKR/jcKkRC1FoN0GMr7fiht1W', 1);

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');