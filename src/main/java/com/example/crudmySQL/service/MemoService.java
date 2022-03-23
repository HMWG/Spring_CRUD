package com.example.crudmySQL.service;

import com.example.crudmySQL.domain.Memo;
import com.example.crudmySQL.repository.MemoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public String getText(Long id){
        return memoRepository.getById(id).getMemoText();
    }

    public Optional<Memo> findOne(Long id){
        return memoRepository.findById(id);
    }

/*    public boolean changeComplete(Long id){
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
