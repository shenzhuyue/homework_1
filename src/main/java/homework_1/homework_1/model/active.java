package homework_1.homework_1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class active {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userid;
    private String username;//发布者用户名
    private String time;//发表时间
    private int goodcount;//点赞数
    private int commentcount;//评论数
    private String content;

}
