--
-- Table structure for table `question_type`
--
CREATE TABLE IF NOT EXISTS `question_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(30) NOT NULL UNIQUE,
   PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8  ;

--
-- Dumping data for table `question_type`
--
INSERT INTO `question_category` (`category`) 
VALUES ('Profile'),
       ('Education'),
       ('Employment'),
       ('References'),
       ('Resume'),
       ('Cover letter'),
       ('Research'),
       ('Volunteer work'),
       ('Military experience'),
       ('Work permit'),
       ('Criminal history'),
       ('Extracurricular activities'),
       ('Hobbies/Interests'),
       ('Licenses'),
       ('Certifications'),
       ('Others');
       


