package com.dys.springcloud.repository;

import com.dys.springcloud.entity.BaseAdminFunctionMenuButton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * @author dingyingsi
 */
public interface BaseAdminFunctionMenuButtonRepository extends JpaRepository<BaseAdminFunctionMenuButton, Long>, QueryByExampleExecutor<BaseAdminFunctionMenuButton>, JpaSpecificationExecutor<BaseAdminFunctionMenuButton> {

    List<BaseAdminFunctionMenuButton> findByFunctionId(Long functionId);
}