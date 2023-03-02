/*zones and units*/
CREATE TABLE `db_lift_energy`.`zones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `latitude` DECIMAL(18,9) NOT NULL,
  `longitude` DECIMAL(18,9) NOT NULL,
  `enabled` BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
  );

 CREATE TABLE `db_lift_energy`.`units` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `zone_id` INT NOT NULL,
  `settings` VARCHAR(5000) NOT NULL,
  `metrics` VARCHAR(10000) NOT NULL,
  `latitude` DECIMAL(18,9) NOT NULL,
  `longitude` DECIMAL(18,9) NOT NULL,
  `enabled` BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_units_zones`
    FOREIGN KEY (`zone_id`)
    REFERENCES `db_lift_energy`.`zones` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO zones (name, latitude, longitude, enabled) VALUES ('puerto_lopez', '4.101343084890358', '-72.96195644931187', 1);
INSERT INTO zones (name, latitude, longitude, enabled) VALUES ('puerto_gaitan', '4.311573551410244', '-72.09491823968902', 1);
INSERT INTO zones (name, latitude, longitude, enabled) VALUES ('puerto_gaitan_2', '4.311573551410244', '-72.09491823968902', 0);
INSERT INTO zones (name, latitude, longitude, enabled) VALUES ('ecopetrol', '4.311573551410244', '-72.09491823968902', 1);

INSERT INTO units (zone_id, settings, metrics, latitude, longitude, enabled) VALUES (1, '[{"name":"working_hours","value":100},{"name":"work_mode","value":"continuous"},{"name":"dinagraph_enabled","value":"true"},{"name":"well_depth_detection_enabled","value":"false"},{"name":"meassuring_mode","value":"daily"},{"name":"event_warnings","value":"true"}]', '{"well_data":[{"name":"crude_density","unit":"lb/gal","value":50},{"name":"crude_viscosity","unit":"API","value":30},{"name":"HS2_level","unit":"ppm","value":5},{"name":"sand_content","unit":"ppm","value":10},{"name":"well_temperature","unit":"°F","value":56},{"name":"water_proportion","unit":"%","value":8},{"name":"well_depth","unit":"ft","value":1000},{"name":"well_pressure","unit":"psi","value":20},{"name":"well_production","unit":"BPD","value":4}],"unit_data":[{"name":"company","value":"Lift Energy"},{"name":"stroke_length","unit":"in","value":100.5},{"name":"artificial_lift_type","value":"conventional"},{"name":"rotation","value":"cw"},{"name":"counter_balance_effect","unit":"Klb","value":9.39},{"name":"counter_balance_moment","unit":"Kin-lb","value":188.434}],"motor_data":[{"name":"motor_type","value":"electric"},{"name":"motor_rating","unit":"HP","value":25},{"name":"full_load_amps","unit":"A","value":65},{"name":"rated_rpm","unit":"rpm","value":1800},{"name":"voltage","unit":"V","value":440},{"name":"frequency","unit":"Hz","value":60},{"name":"phase","value":3}],"power_cost":[{"name":"consumption","unit":"peso/kwh","value":750},{"name":"demand","unit":"kw","value":6000}]}', '4.101343084890358', '-72.96195644931187', 1);
INSERT INTO units (zone_id, settings, metrics, latitude, longitude, enabled) VALUES (1, '[{"name":"working_hours","value":100},{"name":"work_mode","value":"continuous"},{"name":"dinagraph_enabled","value":"true"},{"name":"well_depth_detection_enabled","value":"false"},{"name":"meassuring_mode","value":"daily"},{"name":"event_warnings","value":"true"}]', '{"well_data":[{"name":"crude_density","unit":"lb/gal","value":50},{"name":"crude_viscosity","unit":"API","value":30},{"name":"HS2_level","unit":"ppm","value":5},{"name":"sand_content","unit":"ppm","value":10},{"name":"well_temperature","unit":"°F","value":56},{"name":"water_proportion","unit":"%","value":8},{"name":"well_depth","unit":"ft","value":1000},{"name":"well_pressure","unit":"psi","value":20},{"name":"well_production","unit":"BPD","value":4}],"unit_data":[{"name":"company","value":"Lift Energy"},{"name":"stroke_length","unit":"in","value":100.5},{"name":"artificial_lift_type","value":"conventional"},{"name":"rotation","value":"cw"},{"name":"counter_balance_effect","unit":"Klb","value":9.39},{"name":"counter_balance_moment","unit":"Kin-lb","value":188.434}],"motor_data":[{"name":"motor_type","value":"electric"},{"name":"motor_rating","unit":"HP","value":25},{"name":"full_load_amps","unit":"A","value":65},{"name":"rated_rpm","unit":"rpm","value":1800},{"name":"voltage","unit":"V","value":440},{"name":"frequency","unit":"Hz","value":60},{"name":"phase","value":3}],"power_cost":[{"name":"consumption","unit":"peso/kwh","value":750},{"name":"demand","unit":"kw","value":6000}]}', '4.311573551410244', '-72.09491823968902', 1);
INSERT INTO units (zone_id, settings, metrics, latitude, longitude, enabled) VALUES (2, '[{"name":"working_hours","value":100},{"name":"work_mode","value":"continuous"},{"name":"dinagraph_enabled","value":"true"},{"name":"well_depth_detection_enabled","value":"false"},{"name":"meassuring_mode","value":"daily"},{"name":"event_warnings","value":"true"}]', '{"well_data":[{"name":"crude_density","unit":"lb/gal","value":50},{"name":"crude_viscosity","unit":"API","value":30},{"name":"HS2_level","unit":"ppm","value":5},{"name":"sand_content","unit":"ppm","value":10},{"name":"well_temperature","unit":"°F","value":56},{"name":"water_proportion","unit":"%","value":8},{"name":"well_depth","unit":"ft","value":1000},{"name":"well_pressure","unit":"psi","value":20},{"name":"well_production","unit":"BPD","value":4}],"unit_data":[{"name":"company","value":"Lift Energy"},{"name":"stroke_length","unit":"in","value":100.5},{"name":"artificial_lift_type","value":"conventional"},{"name":"rotation","value":"cw"},{"name":"counter_balance_effect","unit":"Klb","value":9.39},{"name":"counter_balance_moment","unit":"Kin-lb","value":188.434}],"motor_data":[{"name":"motor_type","value":"electric"},{"name":"motor_rating","unit":"HP","value":25},{"name":"full_load_amps","unit":"A","value":65},{"name":"rated_rpm","unit":"rpm","value":1800},{"name":"voltage","unit":"V","value":440},{"name":"frequency","unit":"Hz","value":60},{"name":"phase","value":3}],"power_cost":[{"name":"consumption","unit":"peso/kwh","value":750},{"name":"demand","unit":"kw","value":6000}]}', '4.101343084890358', '-72.96195644931187', 1);
INSERT INTO units (zone_id, settings, metrics, latitude, longitude, enabled) VALUES (2, '[{"name":"working_hours","value":100},{"name":"work_mode","value":"continuous"},{"name":"dinagraph_enabled","value":"true"},{"name":"well_depth_detection_enabled","value":"false"},{"name":"meassuring_mode","value":"daily"},{"name":"event_warnings","value":"true"}]', '{"well_data":[{"name":"crude_density","unit":"lb/gal","value":50},{"name":"crude_viscosity","unit":"API","value":30},{"name":"HS2_level","unit":"ppm","value":5},{"name":"sand_content","unit":"ppm","value":10},{"name":"well_temperature","unit":"°F","value":56},{"name":"water_proportion","unit":"%","value":8},{"name":"well_depth","unit":"ft","value":1000},{"name":"well_pressure","unit":"psi","value":20},{"name":"well_production","unit":"BPD","value":4}],"unit_data":[{"name":"company","value":"Lift Energy"},{"name":"stroke_length","unit":"in","value":100.5},{"name":"artificial_lift_type","value":"conventional"},{"name":"rotation","value":"cw"},{"name":"counter_balance_effect","unit":"Klb","value":9.39},{"name":"counter_balance_moment","unit":"Kin-lb","value":188.434}],"motor_data":[{"name":"motor_type","value":"electric"},{"name":"motor_rating","unit":"HP","value":25},{"name":"full_load_amps","unit":"A","value":65},{"name":"rated_rpm","unit":"rpm","value":1800},{"name":"voltage","unit":"V","value":440},{"name":"frequency","unit":"Hz","value":60},{"name":"phase","value":3}],"power_cost":[{"name":"consumption","unit":"peso/kwh","value":750},{"name":"demand","unit":"kw","value":6000}]}', '4.311573551410244', '-72.09491823968902', 1);
INSERT INTO units (zone_id, settings, metrics, latitude, longitude, enabled) VALUES (3, '[{"name":"working_hours","value":100},{"name":"work_mode","value":"continuous"},{"name":"dinagraph_enabled","value":"true"},{"name":"well_depth_detection_enabled","value":"false"},{"name":"meassuring_mode","value":"daily"},{"name":"event_warnings","value":"true"}]', '{"well_data":[{"name":"crude_density","unit":"lb/gal","value":50},{"name":"crude_viscosity","unit":"API","value":30},{"name":"HS2_level","unit":"ppm","value":5},{"name":"sand_content","unit":"ppm","value":10},{"name":"well_temperature","unit":"°F","value":56},{"name":"water_proportion","unit":"%","value":8},{"name":"well_depth","unit":"ft","value":1000},{"name":"well_pressure","unit":"psi","value":20},{"name":"well_production","unit":"BPD","value":4}],"unit_data":[{"name":"company","value":"Lift Energy"},{"name":"stroke_length","unit":"in","value":100.5},{"name":"artificial_lift_type","value":"conventional"},{"name":"rotation","value":"cw"},{"name":"counter_balance_effect","unit":"Klb","value":9.39},{"name":"counter_balance_moment","unit":"Kin-lb","value":188.434}],"motor_data":[{"name":"motor_type","value":"electric"},{"name":"motor_rating","unit":"HP","value":25},{"name":"full_load_amps","unit":"A","value":65},{"name":"rated_rpm","unit":"rpm","value":1800},{"name":"voltage","unit":"V","value":440},{"name":"frequency","unit":"Hz","value":60},{"name":"phase","value":3}],"power_cost":[{"name":"consumption","unit":"peso/kwh","value":750},{"name":"demand","unit":"kw","value":6000}]}', '4.311573551410244', '-72.09491823968902', 0);
INSERT INTO units (zone_id, settings, metrics, latitude, longitude, enabled) VALUES (4, '[{"name":"working_hours","value":100},{"name":"work_mode","value":"continuous"},{"name":"dinagraph_enabled","value":"true"},{"name":"well_depth_detection_enabled","value":"false"},{"name":"meassuring_mode","value":"daily"},{"name":"event_warnings","value":"true"}]', '{"well_data":[{"name":"crude_density","unit":"lb/gal","value":50},{"name":"crude_viscosity","unit":"API","value":30},{"name":"HS2_level","unit":"ppm","value":5},{"name":"sand_content","unit":"ppm","value":10},{"name":"well_temperature","unit":"°F","value":56},{"name":"water_proportion","unit":"%","value":8},{"name":"well_depth","unit":"ft","value":1000},{"name":"well_pressure","unit":"psi","value":20},{"name":"well_production","unit":"BPD","value":4}],"unit_data":[{"name":"company","value":"Lift Energy"},{"name":"stroke_length","unit":"in","value":100.5},{"name":"artificial_lift_type","value":"conventional"},{"name":"rotation","value":"cw"},{"name":"counter_balance_effect","unit":"Klb","value":9.39},{"name":"counter_balance_moment","unit":"Kin-lb","value":188.434}],"motor_data":[{"name":"motor_type","value":"electric"},{"name":"motor_rating","unit":"HP","value":25},{"name":"full_load_amps","unit":"A","value":65},{"name":"rated_rpm","unit":"rpm","value":1800},{"name":"voltage","unit":"V","value":440},{"name":"frequency","unit":"Hz","value":60},{"name":"phase","value":3}],"power_cost":[{"name":"consumption","unit":"peso/kwh","value":750},{"name":"demand","unit":"kw","value":6000}]}', '4.311573551410244', '-72.09491823968902', 1);

/*Sensor and SensorData*/
CREATE TABLE `db_lift_energy`.`sensors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `unit_id` INT NOT NULL,
  `type` VARCHAR(60) NOT NULL,
  `settings` VARCHAR(10000) NOT NULL,
  `enabled` BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sensors_units`
    FOREIGN KEY (`unit_id`)
    REFERENCES `db_lift_energy`.`units` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `db_lift_energy`.`sensors_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sensor_id` INT NOT NULL,
  `data` FLOAT(25) NOT NULL,
  `unit` VARCHAR(8) NOT NULL,
  `dinagraph_reading` BOOLEAN,
  `time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_data_sensors`
    FOREIGN KEY (`sensor_id`)
    REFERENCES `db_lift_energy`.`sensors` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO sensors (unit_id, type, settings, enabled) VALUES(1, "Motor_Torque", '', 1);
INSERT INTO sensors (unit_id, type, settings, enabled) VALUES(3, "Motor_Torque", '', 1);
INSERT INTO sensors (unit_id, type, settings, enabled) VALUES(5, "Gas_Detection", '', 0);
INSERT INTO sensors (unit_id, type, settings, enabled) VALUES(7, "Gas_Detection", '', 1);
INSERT INTO sensors (unit_id, type, settings, enabled) VALUES(1, "Rod_Torque", '', 1);
INSERT INTO sensors (unit_id, type, settings, enabled) VALUES(3, "Rod_Torque", '', 1);
INSERT INTO sensors (unit_id, type, settings, enabled) VALUES(1, "Rod_Acceleration", '', 1);
INSERT INTO sensors (unit_id, type, settings, enabled) VALUES(3, "Rod_Acceleration", '', 1);

INSERT INTO sensors_data (sensor_id, data, unit, dinagraph_reading, time) VALUES();

/*UnitNotes and UnitEvents*/
CREATE TABLE `db_lift_energy`.`unit_notes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `unit_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `note` VARCHAR(280) NOT NULL,
  `time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_notes_units`
    FOREIGN KEY (`unit_id`)
    REFERENCES `db_lift_energy`.`units` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_notes_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_lift_energy`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `db_lift_energy`.`unit_events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `unit_id` INT NOT NULL,
  `event_name` VARCHAR(10) NOT NULL,
  `event_detail` VARCHAR(280) NOT NULL,
  `event_priority` TINYINT(10) NOT NULL,
  `event_attended` BOOLEAN NOT NULL,
  `time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_event_units`
    FOREIGN KEY (`unit_id`)
    REFERENCES `db_lift_energy`.`units` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

/*Nodes*/
CREATE TABLE `db_lift_energy`.`nodes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `unit_id` INT NOT NULL,
  `url` VARCHAR(280) NOT NULL,
  `asociated_nodes` VARCHAR(10000) NOT NULL,
  `enabled` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_nodes_units`
    FOREIGN KEY (`unit_id`)
    REFERENCES `db_lift_energy`.`units` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

/*Users and authorities*/

CREATE TABLE `db_lift_energy`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45),
  `password` VARCHAR(60) NOT NULL,
  `enabled` BOOLEAN NOT NULL DEFAULT 1,
  `last_login` TIMESTAMP NOT NULL,
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
INSERT INTO users (username, name, last_name, email, password, enabled, last_login) VALUES ('admin', 'Sebastian', 'Rincon', 'sebastian.rincon@actum.com.co', '$2a$10$uoxBSo1vDV5UvB8VTPItu.8jLzJwMVfAnllZbl4yjpYWcpmXV1T1S', 1, '2023-02-22 15:40:10');
INSERT INTO users (username, name, last_name, email, password, enabled, last_login) VALUES ('operario', 'Miguel', 'Gomez', 'miguel.gomez@actum.com.co','$2a$10$YV65ExjewVvYKW10p6mhwugtMlvYVKR/jcKkRC1FoN0GMr7fiht1W', 1, '2023-02-22 15:40:10');

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');