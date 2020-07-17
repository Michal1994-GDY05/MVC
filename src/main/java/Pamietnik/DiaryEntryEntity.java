package Pamietnik;

import javax.persistence.*;

@Entity
public class DiaryEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String content;
    @ManyToOne(cascade = CascadeType.ALL)
    DiaryUsersEntity diaryUsersEntity;

    public DiaryEntryEntity() {
    }

    public DiaryEntryEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setDiaryUser(DiaryUsersEntity diaryUsers) {
        this.diaryUsersEntity = diaryUsers;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return
                title;
    }
}
