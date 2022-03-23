package com.example.crudmySQL.repository;

import com.example.crudmySQL.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
