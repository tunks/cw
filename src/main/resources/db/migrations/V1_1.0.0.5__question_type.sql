--
-- Table structure for table `question_type`
--
CREATE TABLE IF NOT EXISTS `question_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL UNIQUE,
  `show_options` bit not null default 0 ,
  `description` varchar(255) NULL,
  `style` varchar(30) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8  ;

--
-- Dumping data for table `question_type`
--
INSERT INTO `question_type` (`name`,`description`,`show_options`,`style`) 
VALUES ('FILE ATTACHMENT','Applicant will attach file document', b'0','file'),
       ('MULTIPLE CHOICE','Applicant will have option to select multiple checkbox options', b'1','checkbox'),
       ('SINGLE CHOICE','Applicant will select only one option', b'1','radio'),
       ('TEXT','Applicant will provide answer in a textbox ', b'0','text'),
       ('DATE RANGE','Start and end date of two date values ', b'1','date'),
       ('EMAIL','Applicant email ', b'0','email');