package com.ottAll.ottAll;

import com.ottAll.ottAll.dto.SuggestionMediaDao;
import com.ottAll.ottAll.entity.Member;
import com.ottAll.ottAll.repository.MemberRepository;
import com.ottAll.ottAll.repository.QMediaRepository;
import com.ottAll.ottAll.repository.QMediaRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MediaTest {

    @Autowired
    private QMediaRepository qMediaRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findSuggestionMediaListTest(){
        Optional<Member> member = memberRepository.findByUserIdAndStatus("admin", Member.Status.ACTIVE);
        List<SuggestionMediaDao> suggestionMedia = qMediaRepository.findSuggestionMedia(member.get());

        for(SuggestionMediaDao s : suggestionMedia){
            System.out.println("------------------");
            System.out.println(s.getMediaId());
            System.out.println(s.getName());
            System.out.println(s.getImageUrl());
            System.out.println(s.getPlatform());
            System.out.println(s.getGenre());
        }
    }

}
