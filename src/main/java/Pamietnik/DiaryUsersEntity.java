package Pamietnik;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DiaryUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true, nullable = false)
    String login;
    @Column (nullable = false)
    String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diaryUsersEntity" , orphanRemoval = true)
    Set<DiaryEntryEntity> diaryEntry = new HashSet<>();

    public DiaryUsersEntity() {
    }

    public DiaryUsersEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void addDiaryEntry(DiaryEntryEntity diaryEntry) {
        this.diaryEntry.add(diaryEntry);
        diaryEntry.setDiaryUser(this);
    }

    public Set<DiaryEntryEntity> getDiaryEntry() {
        return diaryEntry;
    }

    public void setDiaryEntry(Set<DiaryEntryEntity> diaryEntry) {
        this.diaryEntry = diaryEntry;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DiaryUsersEntity{" +
                "diaryEntry=" + diaryEntry +
                '}';
    }
}
