package com.dys.springcloud.repository;

import com.dys.springcloud.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * @author dingyingsi
 */
public interface WordRepository extends JpaRepository<Word, Long>, QueryByExampleExecutor<Word>, JpaSpecificationExecutor<Word> {

    List<Word> findByUserId(Long userId);
}