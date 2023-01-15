package com.ssg.intern.mock;

import com.ssg.intern.dev.domain.feed.presentation.model.FeedSearchingConditionRequest;
import com.ssg.intern.mock.domain.hashtag.dao.HashTagRepository;
import com.ssg.intern.mock.domain.product.dao.ProductRepository;
import com.ssg.intern.mock.domain.review.dao.SpecialReviewRepository;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MockDataFacadeRepository {

    private final HashTagRepository hashTagRepository;
    private final ProductRepository productRepository;
    private final SpecialReviewRepository specialReviewRepository;

    public SpecialReview findBySpecialReviewId(long specialReviewId) {
        return specialReviewRepository.findBySpecialReviewId(specialReviewId);
    }

    public Page<SpecialReview> findBySearchingCondition(Pageable pageable, FeedSearchingConditionRequest request) {
        return specialReviewRepository.findBySearchingCondition(pageable, request);
    }
}
