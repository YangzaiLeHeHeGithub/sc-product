package com.yz.git.sc.product.service;

import com.yz.git.sc.product.domain.Person;

import java.util.Optional;

/**
 *
 */

public interface PersonService {

    /**
     * 插入Person
     * @param person
     * @return
     */
    Optional<Integer> insert(Person person);

    /**
     * 删除Person
     * @param pId
     * @return
     */
    int delete(Integer pId);

    /**
     *
     * @param desc
     * @param id
     * @return
     */
    int updateDescById(String desc,int id);




}
