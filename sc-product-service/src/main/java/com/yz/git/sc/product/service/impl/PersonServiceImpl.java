package com.yz.git.sc.product.service.impl;

import com.yz.git.sc.product.dao.PersonMapper;
import com.yz.git.sc.product.domain.Person;
import com.yz.git.sc.product.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

import static java.util.Optional.of;

/**
 * <p>@ClassName PersonServiceImpl<p>
 * <p>@Description Person实现<p>
 * <p>@Author xuYang<p>
 * <p>@Date 2020/6/29 14:23<p>
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    PersonMapper personMapper;

    //1111
    //2222


    @Override
    public Optional<Integer> insert(Person person) {
        return of(personMapper.insert(person));
    }

    @Override
    public int delete(Integer pId) {
        return personMapper.deleteById(pId);
    }

    @Override
    public int updateDescById(String desc, int id) {
        return 0;
    }
}
