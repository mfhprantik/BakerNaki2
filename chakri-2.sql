-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 26, 2017 at 06:49 AM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chakri`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `category_ID` int(10) NOT NULL,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`category_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_ID`, `category_name`) VALUES
(100, 'Admin/Secretarial'),
(101, 'Administration Executive'),
(102, 'Administration Manager'),
(103, 'Computer Operator/ Data Entry'),
(104, 'Despatch Incharge'),
(105, 'Executive Secretary'),
(106, 'Facilities Manager'),
(107, 'Fresher'),
(108, 'Receptionist/ Front Desk'),
(109, 'Secretarial'),
(110, 'Travel/Immigration Coordinator'),
(111, 'Typist'),
(112, 'VP/ GM - Administration'),
(200, 'Advertising & Media'),
(201, 'Account Planning'),
(202, 'Account Servicing'),
(203, 'Art Director'),
(204, 'AV Executive/ Editor'),
(205, 'Copy Writer'),
(206, 'Correspondent/ Reporter'),
(207, 'Creative Director'),
(208, 'Editor/ Managing Editor'),
(209, 'Event Management'),
(210, 'Fresher'),
(211, 'Graduate Trainee/ Management Trainee'),
(212, 'Graphic Designer/ Animator'),
(213, 'Instructional Designer'),
(214, 'Market Research'),
(215, 'Media Buying'),
(216, 'Media Planning'),
(217, 'Proof Reader'),
(218, 'Technical Writer'),
(300, 'Bank'),
(301, 'Account'),
(302, 'MTO'),
(400, 'Banking & Financial Services'),
(401, 'Business Analyst'),
(402, 'Advisory'),
(403, 'Agency Manager'),
(404, 'Audit'),
(405, 'Bancassurance'),
(406, 'Chartered Accountant (CPA)'),
(407, 'Collections'),
(408, 'Credit Analysis/ Approval'),
(409, 'Credit Research Analyst'),
(410, 'Fresher'),
(411, 'Graduate Trainee / Management Trainee'),
(412, 'Investment Advisor'),
(413, 'Investment Banking'),
(414, 'Portfolio Manager'),
(415, 'Product Manager - Insurance'),
(416, 'Retail Branch Operations'),
(417, 'Securities Analyst/ Stock Broker'),
(418, 'Trading Advisor'),
(500, 'Construction'),
(501, 'Architect'),
(502, 'Civil Engineer'),
(503, 'Civil Foreman'),
(504, 'Contracting'),
(505, 'Design Engineer'),
(506, 'Draftsman'),
(507, 'Electrical Engineer'),
(508, 'Fresher'),
(509, 'HVAC engineering'),
(510, 'Maintenance Engineer'),
(511, 'Project Manager'),
(512, 'Proposals & Estimation'),
(513, 'Quantity surveyor'),
(514, 'Safety Officer'),
(515, 'Site engineer'),
(516, 'Structural Designing'),
(517, 'Surveyor'),
(518, 'VP/GM - Projects'),
(600, 'Customer Service/ BPO/ KPO'),
(601, 'Account Services Executive'),
(602, 'Customer Service Executive (Non-voice)'),
(603, 'Customer Service Executive (Voice)'),
(604, 'Data Processing Executive'),
(605, 'Fresher'),
(606, 'Manager - Service Delivery'),
(607, 'Operations Manager'),
(608, 'Process Trainer'),
(609, 'Quality Assurance - Manager'),
(610, 'Quality Assurance Executive'),
(611, 'Team Leader'),
(612, 'Technical Support Executive ( voice)'),
(613, 'Technical Support Representative (Non- voice)'),
(614, 'Telemarketing Executive'),
(615, 'Telesales Executive'),
(616, 'Trainee/ Management Trainee'),
(617, 'Transactions Processing Executive'),
(618, 'Voice & Accent Trainer'),
(700, 'Education/Teaching'),
(701, 'CEO/ MD'),
(702, 'IT Instructor'),
(703, 'Kindergarten/ Pre-primary Teacher'),
(704, 'Laboratory Assistant'),
(705, 'Language Teacher'),
(706, 'Lecturer/ Professor'),
(707, 'Librarian'),
(708, 'Mathematics Teacher'),
(709, 'Music/ Dance Teacher'),
(710, 'Principal/ Head of School'),
(711, 'Science Teacher'),
(712, 'Social Sciences Teacher'),
(800, 'Export / Import'),
(801, 'CEO/MD/ Country Manager'),
(802, 'Director on Board'),
(803, 'Documentation/ Shipment Management'),
(804, 'External Consultant'),
(805, 'International Business Dev Mgr'),
(806, 'Liaison'),
(807, 'Merchandiser'),
(808, 'SBU Head /Profit Centre Head'),
(809, 'Trading'),
(810, 'VP - Operations/ COO'),
(900, 'Finance & Accounts'),
(901, 'Accountant'),
(902, 'Accounts Head / GM - Accounts'),
(903, 'Book Keeper/ Accounts Assistant'),
(904, 'Chartered Accountant (CPA)'),
(905, 'Cost Accountant / ICWA'),
(906, 'Credit Control & Collections'),
(907, 'External Auditor'),
(908, 'Finance Assistant'),
(909, 'Finance Manager'),
(910, 'Financial Controller'),
(911, 'Financial/ Business Analyst'),
(912, 'Fresher'),
(913, 'Graduate Trainee/ Management Trainee'),
(914, 'Head / GM - Finance'),
(915, 'Internal Auditor'),
(916, 'Manager - Financial Planning / Budgeting'),
(917, 'Taxation - Manager'),
(918, 'VP Finance/ CFO'),
(1000, 'Health Care'),
(1001, 'Anaesthetist'),
(1002, 'Cardiologist'),
(1003, 'Chemist'),
(1004, 'Consultant'),
(1005, 'Dietician'),
(1006, 'Doctor'),
(1007, 'Gyanecologist'),
(1008, 'Lab Technician'),
(1009, 'Medical Assistant'),
(1010, 'Medical Officer'),
(1011, 'Microbiologist'),
(1012, 'Neurologist'),
(1013, 'Nurse'),
(1014, 'Pediatrician'),
(1015, 'Physician'),
(1016, 'Radiologist'),
(1017, 'Surgeon'),
(1018, 'Trainee / Intern'),
(1100, 'Hotels & Restaurants'),
(1101, 'F&B Manager'),
(1102, 'Hostess/ Host'),
(1103, 'Banquet Sales'),
(1104, 'Bartender'),
(1105, 'Chef/ Kitchen Manager'),
(1106, 'Chief Chef'),
(1107, 'Fresher'),
(1108, 'Front Office Executive'),
(1109, 'Front Office Manager'),
(1110, 'GM/ DGM'),
(1111, 'Guest Relations Executive'),
(1112, 'Guest Relations Manager'),
(1113, 'House Keeping - Head/ Manager'),
(1114, 'House Keeping - Head/ Manager'),
(1115, 'House Keeping Executive'),
(1116, 'Restaurant Manager'),
(1117, 'Sous Chef'),
(1118, 'Steward/ Waiter'),
(1119, 'Trainee/ Management Trainee'),
(1200, 'HR'),
(1201, 'Appraisals - Head/ Mgr'),
(1202, 'External Consultant'),
(1203, 'Fresher'),
(1204, 'Graduate Trainee / Management Trainee'),
(1205, 'HR Executive / Recruiter'),
(1206, 'HR Manager'),
(1207, 'Industrial Relations / Personnel Manager'),
(1208, 'Payroll/ Compensation - Head/ Mgr'),
(1209, 'Payroll/ Compensation Executive'),
(1210, 'Recruitment - Head/ Mgr'),
(1211, 'Training & Development - Head/ Mgr'),
(1212, 'Training & Development Executive'),
(1213, 'VP/ GM - HR'),
(1300, 'IT'),
(1301, 'Business Analyst'),
(1302, 'Computer Operator'),
(1303, 'Database Administrator (DBA)'),
(1304, 'Database Architect/ Designer'),
(1305, 'Delivery Manager'),
(1306, 'ERP, CRM - Functional Consultant'),
(1307, 'ERP, CRM - Technical Consultant'),
(1308, 'Fresher'),
(1309, 'Graphic Designer/ Animator'),
(1310, 'Network Administrator'),
(1311, 'Program Manager'),
(1312, 'Project Leader/ Project Manager'),
(1313, 'Software Engineer/ Programmer'),
(1314, 'Software Test Engineer'),
(1315, 'System Administrator'),
(1316, 'System Analyst/ Tech Architect'),
(1317, 'Team Leader/ Technical Leader'),
(1318, 'Technical Support Engineer'),
(1319, 'Trainee'),
(1320, 'Web Developer'),
(1400, 'Legal'),
(1401, 'Fresher'),
(1402, 'Law Officer'),
(1403, 'Lawyer/ Attorney'),
(1404, 'Legal Advisor'),
(1405, 'Legal Assistant/ Apprentcie'),
(1406, 'Legal Consultant/ Solicitor'),
(1407, 'Legal Editor'),
(1408, 'Legal Head'),
(1409, 'Legal Services - Manager'),
(1410, 'Patent'),
(1411, 'Private Practitioner / Lawyer'),
(1412, 'Proof Reader (Law)'),
(1500, 'Marketing'),
(1501, 'Advertising - Executive'),
(1502, 'Brand Manager'),
(1503, 'Business Analyst/ Consultant'),
(1504, 'Corp Communications - Manager/ Executive'),
(1505, 'Direct Marketing - Executive'),
(1506, 'Direct Marketing - Manager'),
(1507, 'Executive - Internet Marketing'),
(1508, 'Fresher'),
(1509, 'Manager / Head - Internet Marketing'),
(1510, 'Market Research - Executive'),
(1511, 'Market Research - Manager'),
(1512, 'Marketing Executive'),
(1513, 'Marketing Manager'),
(1514, 'Product Manager/ Product Head'),
(1515, 'Public Relations - Executive'),
(1516, 'Telesales/ Telemarketing Executive'),
(1517, 'Trainee/ Management Trainee'),
(1518, 'VP/ GM/ Head - Marketing'),
(1600, 'Oil & Gas'),
(1601, 'Draughtsman'),
(1602, 'Drilling expert'),
(1603, 'Engineering - Civil &Structural'),
(1604, 'Engineering - Electrical'),
(1605, 'Engineering - Field'),
(1606, 'Engineering - Inspection'),
(1607, 'Engineering - Instrument & Control Automation'),
(1608, 'Engineering - Mechanical Static'),
(1609, 'Engineering - Offshore Structures'),
(1610, 'Engineering - Oil/Gas'),
(1611, 'Engineering - Pipelines'),
(1612, 'Engineering - Quality Assurance'),
(1613, 'Engineering - Rotating Equipment'),
(1614, 'Foreman'),
(1615, 'Petroleum Engineering - Reservoir'),
(1616, 'Process Engineering'),
(1617, 'Project Engineering'),
(1618, 'Safety Engineer'),
(1700, 'Others'),
(1701, 'Architect'),
(1702, 'Construction Suptd/ Inspector'),
(1703, 'Consultant'),
(1704, 'Entrepreneur'),
(1705, 'Fashion Designer'),
(1706, 'Fitness Trainer'),
(1707, 'Horticulturist'),
(1708, 'Interior Designer'),
(1709, 'Model'),
(1710, 'Painter'),
(1711, 'Security Officer'),
(1712, 'Statistician'),
(1713, 'Teacher/ Lecturer/ Professor'),
(1800, 'Pharma & Biotech'),
(1801, 'Analytical Chemistry Scientist'),
(1802, 'Basic Research Scientist'),
(1803, 'Bio-Technology Research Scientist'),
(1804, 'Chemist'),
(1805, 'Clinical Research Scientist'),
(1806, 'Data Management/ Statistics'),
(1807, 'Documentation/ Medical Writing'),
(1808, 'Drug Regulatory Doctor'),
(1809, 'Fresher'),
(1810, 'Goods Manufacturing Practices (GMP)'),
(1811, 'Microbiologist'),
(1812, 'Nutritionist'),
(1813, 'Pharmaceutical Research Scientist'),
(1814, 'Pharmacist'),
(1815, 'Product Manager'),
(1816, 'Quality Assurance - Manager'),
(1817, 'Quality Assurance/ Control'),
(1818, 'VP/ GM - Quality'),
(1900, 'Production & Engineering'),
(1901, 'Civil Engineer'),
(1902, 'Design Manager/ Engineer'),
(1903, 'Electrical Engineer'),
(1904, 'Electronics/ Instrumentation Engineer'),
(1905, 'Fresher'),
(1906, 'Industrial Engineering'),
(1907, 'Maintenance'),
(1908, 'Mechanical Engineer'),
(1909, 'Process Manager/ Engineer'),
(1910, 'Production Manager/ Engineer'),
(1911, 'Projects'),
(1912, 'Quality Assurance - Manager'),
(1913, 'Quality Assurance/ Control'),
(1914, 'R & D Manager'),
(1915, 'Service Manager/ Engineer'),
(1916, 'Tech/ Engg - Manager'),
(1917, 'Technician'),
(1918, 'VP/ GM - Engg/ Production'),
(2000, 'Purchase & Supply Chain'),
(2001, 'Commercial - Manager'),
(2002, 'Commercial Executive'),
(2003, 'Computer Operator/ Data Entry'),
(2004, 'Distribution - Head'),
(2005, 'Fresher'),
(2006, 'Graduate Trainee/ Management Trainee'),
(2007, 'Inventory Control Manager/ Materials Manager'),
(2008, 'Logistics - Co-ordinator'),
(2009, 'Logistics - Head/ Mgr'),
(2010, 'Materials - Head / GM'),
(2011, 'Purchase - Head'),
(2012, 'Purchase Manager'),
(2013, 'Purchase Officer/ Co-ordinator/ Executive'),
(2014, 'SBU Head /Profit Centre Head'),
(2015, 'Store Keeper/ Warehouse Assistant'),
(2016, 'Store officer'),
(2017, 'Supply Chain - Head'),
(2018, 'Transportation / Shipping Supervisor'),
(2019, 'Vendor Development Manager'),
(2020, 'VP/ GM - Commercial'),
(2100, 'Real Estate'),
(2101, 'Property Management'),
(2102, 'Brokerage'),
(2103, 'CEO/ MD/ Country Manager'),
(2104, 'Fresher'),
(2105, 'Land Development'),
(2106, 'Management Trainee'),
(2107, 'Real Estate Appraising'),
(2108, 'Real Estate Counseling'),
(2109, 'Real Estate Research'),
(2110, 'SBU Head/ Profit Centre Head'),
(2111, 'VP Operations/ COO'),
(2200, 'Sales'),
(2201, 'Area / Territory Sales Manager'),
(2202, 'Branch Manager'),
(2203, 'Business Development Executive'),
(2204, 'Business Development Manager'),
(2205, 'Channel Sales Manager'),
(2206, 'Direct Sales Agent/ Commission Agent'),
(2207, 'Fresher'),
(2208, 'International Business Dev Mgr'),
(2209, 'Key Accounts Manager'),
(2210, 'National Sales Manager'),
(2211, 'Presales Consultant'),
(2212, 'Regional Sales Manager'),
(2213, 'Relationship Mgr / Account Servicing'),
(2214, 'Sales Exec/ Sales Representative'),
(2215, 'Sales Promotion Manager'),
(2216, 'Sales Trainee / Management Trainee'),
(2217, 'Telesales/ Telemarketing Executive'),
(2218, 'VP/ GM/ Head - Sales'),
(2300, 'Telecom & ISP'),
(2301, 'Customer Support Engineer/ Technician'),
(2302, 'Fresher'),
(2303, 'Graduate Trainee / Management Trainee'),
(2304, 'GSM Engineer'),
(2305, 'Network Administrator'),
(2306, 'Network Installation & Administration'),
(2307, 'Network Planning - Chief Engineer'),
(2308, 'Network Planning - Engineer'),
(2309, 'O&M Engineer'),
(2310, 'Regional Mgr/ Manager(Operations)'),
(2311, 'RF Installation & Administration Engineer'),
(2312, 'RF Planning - Chief Engineer'),
(2313, 'RF Planning Engineer'),
(2314, 'Switching - Engineer'),
(2315, 'System Administrator'),
(2316, 'System Engineer'),
(2317, 'Telecom Engineer'),
(2318, 'VP/ Head - Technology'),
(2400, 'Travel & Airlines'),
(2401, 'Documentaion & VISA'),
(2402, 'Air Hostess/ Steward/ Cabin Crew'),
(2403, 'Branch Head'),
(2404, 'CEO/MD/ Country Manager'),
(2405, 'Domestic Travel'),
(2406, 'External Consultant'),
(2407, 'Fresher'),
(2408, 'GM'),
(2409, 'Ground Staff'),
(2410, 'International Travel'),
(2411, 'Office Assistant'),
(2412, 'Pilot'),
(2413, 'Reservation and Ticketing'),
(2414, 'SBU Head /Profit Centre Head'),
(2415, 'Trainee/ Management Trainee'),
(2416, 'Travel Agent/ Tour Operator'),
(2417, 'VP - Operations/ COO');

-- --------------------------------------------------------

--
-- Table structure for table `circular`
--

DROP TABLE IF EXISTS `circular`;
CREATE TABLE IF NOT EXISTS `circular` (
  `circular_ID` int(10) NOT NULL AUTO_INCREMENT,
  `category` text NOT NULL,
  `min_deg` text NOT NULL,
  `skills_language` varchar(100) DEFAULT NULL,
  `skills_interpersonal` varchar(100) DEFAULT NULL,
  `skills_computer` varchar(100) DEFAULT NULL,
  `work_experience` int(2) NOT NULL,
  `deadline` date NOT NULL,
  `job_description` varchar(500) NOT NULL,
  `job_nature` varchar(10) NOT NULL,
  `job_location` varchar(100) NOT NULL,
  `salary_range` text,
  `ID` int(10) NOT NULL,
  PRIMARY KEY (`circular_ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `circular`
--

INSERT INTO `circular` (`circular_ID`, `category`, `min_deg`, `skills_language`, `skills_interpersonal`, `skills_computer`, `work_experience`, `deadline`, `job_description`, `job_nature`, `job_location`, `salary_range`, `ID`) VALUES
(2, '_102_105_106_', '202', 'None', 'None', 'None', 0, '2017-11-05', 'sadas dsad ', 'pertime', 'asd', 'sad', 23),
(3, '_102_109_110_', '_203_303_304', 'None', 'None', 'None', 0, '2017-11-22', 'asd asasdadasd  asd asd', 'pertime', 'asdasd ', 'asd asd ', 24),
(4, '_102_106_', '_202', 'None', 'None', 'None', 0, '2017-11-10', 'asd asdas d', 'fulltime', 'asd sad ', 'asd  sdaa', 19);

-- --------------------------------------------------------

--
-- Table structure for table `degrees`
--

DROP TABLE IF EXISTS `degrees`;
CREATE TABLE IF NOT EXISTS `degrees` (
  `degree_ID` int(10) NOT NULL,
  `degree_name` varchar(100) NOT NULL,
  PRIMARY KEY (`degree_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `degrees`
--

INSERT INTO `degrees` (`degree_ID`, `degree_name`) VALUES
(100, 'PSC / Five Pass'),
(101, 'Ebtedayee'),
(102, 'PSC'),
(103, 'Others'),
(200, 'JSC / Eight Pass'),
(201, 'JSC'),
(202, 'JDC'),
(203, 'Others'),
(300, 'Secondary Education'),
(301, 'Dhakhil'),
(302, 'O Level'),
(303, 'SSC'),
(304, 'Vocational (SSC)'),
(305, 'Others'),
(400, 'Higher Secondary Education'),
(401, 'A Level'),
(402, 'Alim'),
(403, 'HSC'),
(404, 'Vocational (HSC)'),
(405, 'Others'),
(500, 'Graduation'),
(501, 'B.Sc. in Biomedical Engineering'),
(502, 'B.Sc. in Civil Engineering (CE)'),
(503, 'B.Sc. in Electrical and Electronic Engineering (EEE)'),
(504, 'B.Sc. in Electronics and Communication Engineering (ECE)'),
(505, 'B.Sc. in Industrial Engineering and Management (IEM)'),
(506, 'B.Sc. in Leather Engineering (LE)'),
(507, 'B.Sc. in Materials and Metallurgical Engineering (MME)'),
(508, 'B.Sc. in Mechanical Engineering (ME)'),
(509, 'B.Sc. in Petroleum & Mining Engineering (PME)'),
(510, 'B.Sc. in Textile Engineering'),
(511, 'B.Sc. in Urban and Regional Planning (URP)'),
(512, 'Bachelor (Subject/Degree Not Specified)'),
(513, 'Bachelor of Architecture (B.Arch)'),
(514, 'Bachelor of Arts (B.A.)'),
(515, 'Bachelor of Business Administration (BBA)'),
(516, 'Bachelor of Business Studies (B.B.S.)'),
(517, 'Bachelor of Commerce (B. Com.)'),
(518, 'Bachelor of Computer Science & Engineering(BSc in CSE)'),
(519, 'Bachelor of Dental Surgery (BDS)'),
(520, 'Bachelor of Education (B.Ed)'),
(521, 'Bachelor of Fine Arts (B. F. A.)'),
(522, 'Bachelor of Law (LL.B)'),
(523, 'Bachelor of Pharmacy (B. Pharm)'),
(524, 'Bachelor of Science (B.Sc)'),
(525, 'Bachelor of Science Agriculture (B.Sc.Ag)'),
(526, 'Bachelor of Science Engineering (B.Sc. Eng.)'),
(527, 'Bachelor of Social Science (B.S.S.)'),
(528, 'BSC in ABC Engineering'),
(529, 'BSc. in Electronics and Telecommunication Engineering (ETE)'),
(530, 'Business Administration and Business Studies (B.A.B.S) Hons'),
(531, 'Fazil'),
(532, 'Others'),
(600, 'Post Graduation'),
(601, 'Kamil'),
(602, 'M.B.B.S'),
(603, 'M.Sc. in Civil Engineering ( CE )'),
(604, 'M.Sc. in Electrical and Electronic Engineering (EEE)'),
(605, 'M.Sc. in Electronics and Communication Engineering (ECE)'),
(606, 'M.Sc. in Industrial Engineering and Management ( IEM )'),
(607, 'M.Sc. in Mechanical Engineering (ME)'),
(608, 'Master of Architecture (M. Arch)'),
(609, 'Master of Arts (M.A.)'),
(610, 'Master of Business Administration(M.B.A)'),
(611, 'Master of Business Studies (M.B.S.)'),
(612, 'Master of Commerce (M.Com)'),
(613, 'Master of Computer Science & Engineering(MS in CSE)'),
(614, 'Master of Law (LL.M)'),
(615, 'Master of Pharmacy (M. Pharm)'),
(616, 'Master of Science (M.Sc)'),
(617, 'Master of Science Agriculture (M.Sc.Ag)'),
(618, 'Master of Science Engineering (M.Sc. Eng.)'),
(619, 'Master of Social Science (M.S.S.)'),
(620, 'MS'),
(621, 'Others'),
(700, 'Doctorate'),
(701, 'Ph.D. (Chemistry)'),
(702, 'Ph.D. (Mathematics)'),
(703, 'Ph.D. (Physics)'),
(704, 'Ph.D. Civil Engineering ( CE )'),
(705, 'Ph.D. Computer Science and Engineering ( CSE )'),
(706, 'Ph.D. Electrical and Electronic Engineering ( EEE )'),
(707, 'Ph.D. Electronics and Communication Engineering ( ECE )'),
(708, 'Ph.D. Industrial Engineering and Management( IEM )'),
(709, 'Ph.D. Mechanical Engineering ( ME )'),
(710, 'Others'),
(800, 'Professional'),
(801, 'Advanced Diploma'),
(802, 'Association of Chartered Certified Accountants (ACCA)'),
(803, 'Chartered Accountancy (C.A.)'),
(804, 'Chartered Accountant (ACA)'),
(805, 'Chartered Accountant (C.A)'),
(806, 'Chartered Accountant (FCA)'),
(807, 'Diploma'),
(808, 'M. Phil.'),
(809, 'Post Graduate Diploma'),
(810, 'Others'),
(900, 'Diploma'),
(901, 'Diploma in Business Studies'),
(902, 'Diploma in Commerce'),
(903, 'Diploma in Engineering'),
(904, 'Diploma in Medical Technology'),
(905, 'Diploma in Nursing'),
(906, 'Others');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `name` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` char(11) NOT NULL,
  `ID` int(10) NOT NULL,
  `sex` varchar(6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`name`, `birthdate`, `address`, `phone`, `ID`, `sex`) VALUES
('Moshiur', '2017-11-15', 'uttara dhaka', '01752129252', 1, 'female'),
('radif', '2017-11-04', 'dhaka', '01752129252', 2, 'female'),
('asd', '2017-11-16', 'asdasd', '01711138078', 8, 'Male'),
('asdasd', '2017-11-07', 'asddas', 'asd', 10, 'asd'),
('Moshiur Rahman', '2017-11-19', 'h-15 r -12 123 qweqwe', '01752129252', 14, 'male'),
('asd', '2017-11-08', 'asd dasas dasd das ', '0171138078', 15, 'asd'),
('moshiur', '2017-11-11', 'asdasd asd as dasdasd asd ', '01711138078', 16, 'male'),
('roof', '2017-11-03', 'roof\r\n', '01754548489', 17, 'none'),
('asd', '2017-11-08', 'asd asdasdasdasddas', '12345678910', 18, 'asddad');

-- --------------------------------------------------------

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
CREATE TABLE IF NOT EXISTS `employer` (
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` char(11) NOT NULL,
  `ID` int(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employer`
--

INSERT INTO `employer` (`name`, `address`, `phone`, `ID`) VALUES
('mcompanu', 'dhaka', '01564562485', 7),
('mcompanu', 'das  asddasdsd  das dasd', '01751225269', 19),
('ads dsad ', 'asd asdasdasd asd ', '01711138078', 20),
('walmarrt', 'asd asdasdasdasd asas d', '01711138078', 21),
('asdas asd', '25asd 65asd 65as dasd ', '01711138078', 22),
('asdasd', 'asd asd', 'asd', 23),
('mcompanu', 'asd aadasasd asd asd ', '0171113500', 24);

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE IF NOT EXISTS `jobs` (
  `ID` int(10) NOT NULL,
  `category` text NOT NULL,
  `work_details` varchar(500) DEFAULT NULL,
  `company_name` varchar(50) DEFAULT NULL,
  `work_experience` int(2) NOT NULL,
  `degree` int(11) NOT NULL,
  `degree_year` smallint(4) NOT NULL,
  `degree_uni` text NOT NULL,
  `skills_interpersonal` varchar(100) DEFAULT NULL,
  `skills_language` varchar(100) NOT NULL,
  `skills_computer` varchar(100) DEFAULT NULL,
  `achievements` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`ID`, `category`, `work_details`, `company_name`, `work_experience`, `degree`, `degree_year`, `degree_uni`, `skills_interpersonal`, `skills_language`, `skills_computer`, `achievements`) VALUES
(1, '_101_615_1407_', 'none', 'None', 0, 518, 2018, 'Brac', 'None', 'None', 'None', 'none'),
(8, '_104_106_108_', 'asdasdasd', 'asdas', 0, 304, 0, 'None', 'None', 'None', 'None', 'asddas d asdsadasdasd'),
(10, '_112_204_208_', 'asddsa', 'None', 0, 103, 2012, 'None', 'None', 'None', 'None', 'adsad'),
(14, '_105_106_205_', '', 'None', 0, 305, 2012, 'None', 'None', 'None', 'None', 'asdads'),
(15, '_101_102_103_', 'none', 'None', 0, 101, 2012, 'None', 'None', 'None', 'None', 'none'),
(16, '_102_103_104_', 'asd asd ', 'None', 0, 606, 0, 'None', 'None', 'None', 'None', 'asdasd'),
(17, '_103_106_107_', 'none', 'None', 0, 501, 2012, 'None', 'None', 'None', 'None', 'asd asd asdasdasd'),
(18, '_101_105_109_', 'asdad ', 'None', 0, 102, 0, 'None', 'None', 'None', 'None', 'asd');

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
CREATE TABLE IF NOT EXISTS `matches` (
  `circular_ID` int(10) NOT NULL,
  `candidates` varchar(500) DEFAULT NULL,
  `interested_ID` text,
  PRIMARY KEY (`circular_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `type` int(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `email`, `password`, `type`) VALUES
(1, 'moshiur.radif@gmail.com', '12345', 1),
(2, 'radif@gmail.com', '12345', 1),
(3, 'rad@gmail.com', '12345', 1),
(4, 'ra@gmail.com', '12345', 1),
(5, 'r@gmail.com', '12345', 1),
(6, 'prantik@gmail.com', '12345', 1),
(7, 'abc@abc.com', '12345', 2),
(8, 'phpdemo@phpdemo', '12345', 1),
(9, 'a@a', '12345', 1),
(10, 'radif@radif.com', '12345', 1),
(12, 'rad@rad.com', '12345', 1),
(13, 'rad@radif.com', '12345', 1),
(14, 'root@root.com', '12345', 1),
(15, 'foo@foo.com', '123456', 1),
(16, 'food@food', '12345', 1),
(17, 'roof@roof', '12345', 1),
(18, 'gg@gg.com', '12345', 1),
(19, 'admin@admin', '12345', 2),
(20, 'php@php', '12345', 2),
(21, 'asd@asd', '12345', 2),
(22, 'am@am', '12345', 2),
(23, 'asd@a', '12345', 2),
(24, 'abcd@abc', '12345', 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `circular`
--
ALTER TABLE `circular`
  ADD CONSTRAINT `circular_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `employer`
--
ALTER TABLE `employer`
  ADD CONSTRAINT `employer_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `jobs`
--
ALTER TABLE `jobs`
  ADD CONSTRAINT `jobs_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`circular_ID`) REFERENCES `circular` (`circular_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
