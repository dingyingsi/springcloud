CREATE TABLE `word` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `word_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '单词名称',
  `word_phonetic_sign` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '单词音标',
  `word_meaning` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '单词意思',
  `word_example` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '单词例句',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;