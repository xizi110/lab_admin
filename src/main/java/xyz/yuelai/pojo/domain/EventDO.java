package xyz.yuelai.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author 李泽众
 * @date 2019/7/29-13:57
 */

@ToString
@Entity
@Table(name = "large_event")
@Setter
public class EventDO {
    private Long eventId;
    private String title;
    private String author;
    private String brief;
    private String content;
    private Timestamp publishDate;
    private Byte carousel;
    private String carouselImgLink;
    private Timestamp carouselEndDate;

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getEventId() {
        return eventId;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    @Column(name = "brief")
    public String getBrief() {
        return brief;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @Column(name = "publish_date")
    public Timestamp getPublishDate() {
        return publishDate == null ? null : (Timestamp) publishDate.clone();
    }

    @Column(name = "is_carousel")
    public Byte getCarousel() {
        return carousel;
    }

    @Column(name = "carousel_img_link")
    public String getCarouselImgLink() {
        return carouselImgLink;

    }
    @Column(name = "carousel_end_date")
    public Timestamp getCarouselEndDate() {
        return carouselEndDate == null ? null : (Timestamp) carouselEndDate.clone();
    }
}
