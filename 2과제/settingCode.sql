-- MySQL Script generated by MySQL Workbench
-- Wed Mar  8 09:53:53 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema 2023지방_2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `2023지방_2` ;

-- -----------------------------------------------------
-- Schema 2023지방_2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `2023지방_2` DEFAULT CHARACTER SET utf8 ;
USE `2023지방_2` ;

-- -----------------------------------------------------
-- Table `2023지방_2`.`division`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2023지방_2`.`division` (
  `d_no` INT(11) NOT NULL AUTO_INCREMENT,
  `d_name` VARCHAR(50) NULL,
  PRIMARY KEY (`d_no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `2023지방_2`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2023지방_2`.`book` (
  `b_no` INT(11) NOT NULL AUTO_INCREMENT,
  `b_name` VARCHAR(50) NULL,
  `d_no` INT(11) NULL,
  `b_author` VARCHAR(50) NULL,
  `b_count` INT(11) NULL,
  `b_page` INT(11) NULL,
  `b_exp` VARCHAR(500) NULL,
  `b_img` LONGBLOB NULL,
  PRIMARY KEY (`b_no`),
  INDEX `fk_book_division1_idx` (`d_no` ASC) VISIBLE,
  CONSTRAINT `fk_book_division1`
    FOREIGN KEY (`d_no`)
    REFERENCES `2023지방_2`.`division` (`d_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `2023지방_2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2023지방_2`.`user` (
  `u_no` INT(11) NOT NULL AUTO_INCREMENT,
  `u_name` VARCHAR(5) NULL,
  `u_id` VARCHAR(10) NULL,
  `u_pw` VARCHAR(10) NULL,
  PRIMARY KEY (`u_no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `2023지방_2`.`likebook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2023지방_2`.`likebook` (
  `l_no` INT(11) NOT NULL AUTO_INCREMENT,
  `u_no` INT(11) NULL,
  `b_no` INT(11) NULL,
  PRIMARY KEY (`l_no`),
  INDEX `fk_likebook_book_idx` (`b_no` ASC) VISIBLE,
  INDEX `fk_likebook_user1_idx` (`u_no` ASC) VISIBLE,
  CONSTRAINT `fk_likebook_book`
    FOREIGN KEY (`b_no`)
    REFERENCES `2023지방_2`.`book` (`b_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_likebook_user1`
    FOREIGN KEY (`u_no`)
    REFERENCES `2023지방_2`.`user` (`u_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `2023지방_2`.`rental`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `2023지방_2`.`rental` (
  `r_no` INT(11) NOT NULL AUTO_INCREMENT,
  `u_no` INT(11) NULL,
  `b_no` INT(11) NULL,
  `r_date` DATE NULL,
  `r_count` INT(11) NULL,
  `r_reading` INT(11) NULL,
  `r_returnday` DATE NULL,
  PRIMARY KEY (`r_no`),
  INDEX `fk_rental_book1_idx` (`b_no` ASC) VISIBLE,
  INDEX `fk_rental_user1_idx` (`u_no` ASC) VISIBLE,
  CONSTRAINT `fk_rental_book1`
    FOREIGN KEY (`b_no`)
    REFERENCES `2023지방_2`.`book` (`b_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rental_user1`
    FOREIGN KEY (`u_no`)
    REFERENCES `2023지방_2`.`user` (`u_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

set global local_infile = true;
load data local infile './datafiles/user.txt' into table 2023지방_2.user lines terminated by '\r' ignore 1 lines;
load data local infile './datafiles/division.txt' into table 2023지방_2.division lines terminated by '\r' ignore 1 lines;
load data local infile './datafiles/book.txt' into table 2023지방_2.book lines terminated by '\r\n' ignore 1 lines;
load data local infile './datafiles/likebook.txt' into table 2023지방_2.likebook lines terminated by '\r' ignore 1 lines;
load data local infile './datafiles/rental.txt' into table 2023지방_2.rental lines terminated by '\r' ignore 1 lines;

drop user if exists 'user'@'localhost';
create user 'user'@'localhost' identified by '1234';
grant select, insert, update, delete on 2023지방_2.* to 'user'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
