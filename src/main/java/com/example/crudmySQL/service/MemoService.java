package com.example.crudmySQL.service;

import com.example.crudmySQL.domain.Memo;
import com.example.crudmySQL.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public long add(Memo memo){
        memoRepository.save(memo);
        memo.setComplete(false);
        return memo.getId();
    }

    public List<Memo> findMemos(){
        return memoRepository.findAll();
    }

    public void delete(Long id){
        memoRepository.deleteById(id);
    }

    public String getText(Long id) throws Exception {
        Optional<Memo> memo = findOne(id);

/*        String text = "저장된 할 일이 없습니다";

        if(memo.isPresent()){
            text = memo.get().getMemoText();
        }

        return text; //isPresent 사용
*/
        return memo.orElseThrow(() -> new Exception("저장된 할 일이 없습니다.")).getMemoText(); //예외처리
    } //위험한 코드(NPE 이슈 처리 방법 익히기)

    public Optional<Memo> findOne(Long id){
        return memoRepository.findById(id);
    }

/*
    public boolean changeComplete(Long id){
        boolean complete = memoRepository.getById(id).isComplete();

        if (memoRepository.getById(id).isComplete()){
            complete = false;
        }
        else{
            complete = true;
        }

        return complete;
    }
*/

    public void update(Long id) {
        Optional<Memo> memo = findOne(id);

        memo.ifPresent(value -> value.setComplete(!memo.get().isComplete()));

        memoRepository.save(memo.get());
    }



}
