package com.ssg.intern.dev.domain.comment.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssg.intern.common.domain.account.entity.QAccount.account;
import static com.ssg.intern.dev.domain.comment.entity.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentSingleDao> findAllInnerFetchJoinAccount() {
        return jpaQueryFactory
                .select(new QCommentSingleDao(account.email, comment.content))
                .from(comment)
                .join(account)
                .on(comment.accountId.eq(account.id))
                .fetch();
    }
}
