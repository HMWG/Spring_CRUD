package com.example.crudmySQL.repository;

import com.example.crudmySQL.domain.Memo;
import com.example.crudmySQL.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
//@Transactional
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;
    @Autowired
    MemoService memoService;

    @Test
    public void InsertDummies() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("Sample..." + i)
                    .build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void SelectDummies() {
        Long id = 10L;
        Optional<Memo> result = memoRepository.findById(id);
        System.out.println("=============================");
        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void UpdateDummies() {
        Memo memo = Memo.builder()
                .id(10L)
                .memoText("Update Text")
                .build();
        memoRepository.save(memo);
    }

    @Test
    public void DeleteDummies() {
        Long id = 30L;
        memoRepository.deleteById(id);
    }

    @Test
    public void add() {
        Memo memo = new Memo();
        memo.setMemoText("spring");

        Long saveId = memoService.add(memo);
    }

    @Test
    public void delete() {

        memoService.delete(48L);
    }

    @Test
    public void update() {
        Optional<Memo> one = memoService.findOne(56L);

        one.get().setComplete(!one.get().isComplete());
        memoRepository.save(one.get());
    }





}
