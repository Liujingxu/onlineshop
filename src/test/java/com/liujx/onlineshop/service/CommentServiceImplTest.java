package com.liujx.onlineshop.service;

import com.liujx.onlineshop.entity.Evaluate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Test
    void addComment() {
        String gid = "4658103030";
        Evaluate evaluate = new Evaluate();
        evaluate.setEid(String.valueOf(System.currentTimeMillis()));
        evaluate.setLevel(5);
        evaluate.setContext("沙发，好评");
        evaluate.setUid("u0001");

        commentService.addComment(gid, evaluate);
    }
}