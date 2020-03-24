-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema school
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema school
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `school` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `school` ;

-- -----------------------------------------------------
-- Table `school`.`adresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`adresses` (
  `id` INT NOT NULL,
  `country` CHAR(30) NOT NULL,
  `city` CHAR(30) NOT NULL,
  `street` VARCHAR(100) NOT NULL,
  `number` CHAR(5) NOT NULL,
  `floor` INT NOT NULL,
  `apartment_no` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`teachers` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `email` CHAR(30) NOT NULL,
  `salary` DOUBLE NOT NULL,
  `adress_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `adress_id` (`adress_id` ASC) VISIBLE,
  CONSTRAINT `adress_id`
    FOREIGN KEY (`adress_id`)
    REFERENCES `school`.`adresses` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school`.`disciplines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`disciplines` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `teacher_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `teacher_id` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `teacher_id`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `school`.`teachers` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`students` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `enrollment_date` DATE NOT NULL,
  `adressid` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `adressid` (`adressid` ASC) VISIBLE,
  CONSTRAINT `adressid`
    FOREIGN KEY (`adressid`)
    REFERENCES `school`.`adresses` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school`.`disciplines_studied`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`disciplines_studied` (
  `discipline_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  PRIMARY KEY (`discipline_id`),
  INDEX `student_id` (`student_id` ASC) VISIBLE,
  CONSTRAINT `disciplines_studied_ibfk_1`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `school`.`disciplines` (`id`),
  CONSTRAINT `disciplines_studied_ibfk_2`
    FOREIGN KEY (`student_id`)
    REFERENCES `school`.`students` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school`.`disciplines_taught`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`disciplines_taught` (
  `discipline_id` INT NOT NULL,
  `teacher_id` INT NOT NULL,
  PRIMARY KEY (`discipline_id`),
  INDEX `teacher_id` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `disciplines_taught_ibfk_1`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `school`.`teachers` (`id`),
  CONSTRAINT `disciplines_taught_ibfk_2`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `school`.`teachers` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
