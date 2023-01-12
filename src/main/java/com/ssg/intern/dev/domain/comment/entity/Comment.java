package com.ssg.intern.dev.domain.comment.entity;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long accountId;

    private int reportCount;

    public Comment(final Feed feed, final String content, Long accountId) {
        this.feed = feed;
        this.content = content;
        this.accountId = accountId;
        this.reportCount = 0;
    }

    public static Comment of(final Feed feed, final String content, Long accountId) {
        return new Comment(feed, content, accountId);
    }
}
