
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

DROP TABLE `game_player`;
DROP TABLE `game`;
DROP TABLE `player`;

--
-- Database: `dbi322496`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `map` varchar(128) NOT NULL,
  `victor` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `game_player`
--

CREATE TABLE `game_player` (
  `player_name` varchar(128) NOT NULL,
  `game_id` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `game_player`
--
ALTER TABLE `game_player`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `game_player`
--
ALTER TABLE `game`
  ADD CONSTRAINT FK_VICTOR_NAME
  FOREIGN KEY (victor) REFERENCES `player`(name);
ALTER TABLE `game_player`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `game_player`
  ADD CONSTRAINT FK_GameID_ID 
  FOREIGN KEY (game_id) REFERENCES `game`(id);  
ALTER TABLE `game_player`
  ADD CONSTRAINT FK_PlayerName_Name
  FOREIGN KEY (player_name) REFERENCES `player`(name);
COMMIT;