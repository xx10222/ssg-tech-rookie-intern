package com.ssg.intern.dev.domain.bookmark.dao;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.domain.mypage.presentation.model.QBookmarkProfileResponse_Thumbnail;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssg.intern.dev.domain.bookmark.entity.QBookmark.bookmark;
import static com.ssg.intern.dev.domain.feed.entity.QFeed.feed;
import static com.ssg.intern.mock.domain.review.entity.QSpecialReview.specialReview;

@RequiredArgsConstructor
public class BookmarkRepositoryCustomImpl implements BookmarkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BookmarkProfileResponse.Thumbnail> findThumbnails(Long accountId, SortingCondition sortingCondition) {
        return queryFactory
                .select(new QBookmarkProfileResponse_Thumbnail(specialReview.imageUrl, feed.recommendCount, specialReview.description))
                .from(bookmark)
                .innerJoin(feed)
                .on(bookmark.feed.id.eq(feed.id))
                .innerJoin(specialReview)
                .on(feed.specialReviewId.eq(specialReview.id))
                .where(bookmark.accountId.eq(accountId))
                .orderBy(sort(sortingCondition))
                .fetch();
    }

    private OrderSpecifier<?> sort(final SortingCondition sortingCondition) {
        switch (sortingCondition) {
            case NEWER:
                return new OrderSpecifier<>(Order.DESC, bookmark.updatedAt);
            case OLDER:
                return new OrderSpecifier<>(Order.ASC, bookmark.updatedAt);
        }
        return null;
    }
}