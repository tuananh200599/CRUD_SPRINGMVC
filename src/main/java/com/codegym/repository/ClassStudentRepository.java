package com.codegym.repository;

import com.codegym.model.ClassStudent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClassStudentRepository extends PagingAndSortingRepository<ClassStudent, Long> {
}
