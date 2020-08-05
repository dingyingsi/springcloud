package com.dys.springcloud.repository;

import com.dys.springcloud.entity.BaseAdminFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * @author dingyingsi
 */
public interface BaseAdminFunctionRepository extends JpaRepository<BaseAdminFunction, Long>, QueryByExampleExecutor<BaseAdminFunction>, JpaSpecificationExecutor<BaseAdminFunction> {

    BaseAdminFunction findByFunctionId(Long functionId);

    List<BaseAdminFunction> findByFunctionIdNotIn(List<Long> functionIds);
}