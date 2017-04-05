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
INSERT INTO `question_category` (`id`,`category`) 
VALUES (1,  'Personal information'),
       (2,  'Education information'),
       (3,  'Employment information'),
       (4,  'References'),
       (5,  'Resume and cover letter'),
       (6,  'Research information'),
       (7,  'Volunteer work'),
       (8,  'Military experience'),
       (9,  'Work permit/authorization'),
       (10, 'Criminal history'),
       (11, 'Extracurricular activities'),
       (12,  'Hobbies/Interests'),
       (13, 'Licenses/Certifications'),
       (14, 'Others');
       


