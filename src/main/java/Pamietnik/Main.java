package Pamietnik;

import main.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Login");
        String loginScanner = scanner.nextLine();
        System.out.println("Podaj Password");
        String passwordScanner = scanner.nextLine();
        DiaryUsersEntity diaryUsersEntity = new DiaryUsersEntity();
        DiaryUsersEntity diaryUsersEntity1 = new DiaryUsersEntity("aa", "aa");
        DiaryUsersEntity diaryUsersEntity2 = new DiaryUsersEntity("bb", "bb");
        DiaryUsersEntity diaryUsersEntity3 = new DiaryUsersEntity("cc", "cc");


        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        session.persist(diaryUsersEntity1);
//        session.persist(diaryUsersEntity2);
//        session.persist(diaryUsersEntity3);

        try {

            DiaryUsersEntity diaryUser = session.createQuery("FROM DiaryUsersEntity AS h WHERE h.login = :login AND h.password = :password", DiaryUsersEntity.class)
                    .setParameter("login", loginScanner)
                    .setParameter("password", passwordScanner)
                    .getSingleResult();
            System.out.println("Witaj! ID: " + diaryUser.getId() + " Login User: " + diaryUser.getLogin());
            System.out.println();
            System.out.println("Wybierz opcje: ");
            System.out.println("1: Nowy wpis");
            System.out.println("2: Otworz zapisany wpis");
            System.out.println("3: Edytuj wpis");
            System.out.println("4: Usuń wpis");
            System.out.println("5: Zamknij program");
            int wyborOpcji = scanner.nextInt();
            scanner.nextLine();
            if (wyborOpcji == 1) {
                System.out.println("Podaj tytul");
                String titleScanner = scanner.nextLine();
                System.out.println("Podaj treść wpisu");
                String diaryTextScanner = scanner.nextLine();

                DiaryEntryEntity diaryEntry = new DiaryEntryEntity(titleScanner, diaryTextScanner);
                session.persist(diaryEntry);
                diaryUser.addDiaryEntry(diaryEntry);
            }

            if (wyborOpcji == 2) {
                System.out.println("Podaj tytul wpisu, by odczytac zawartość");
                System.out.println();
                for (DiaryEntryEntity diaryEntryEntity : diaryUser.getDiaryEntry()) {
                    System.out.println(diaryEntryEntity);
                }

                System.out.println();
                String nameTheTitleScanner = scanner.nextLine();
                DiaryEntryEntity diaryEnter = session.createQuery("FROM DiaryEntryEntity AS h WHERE h.title = :title", DiaryEntryEntity.class)
                        .setParameter("title", nameTheTitleScanner)
                        .getSingleResult();

                System.out.println(diaryEnter.getContent());
            }

            if (wyborOpcji == 3) {
                System.out.println("Podaj tyltu by edytowac tekst");
                System.out.println();
                for (DiaryEntryEntity diaryEntryEntity : diaryUser.getDiaryEntry()) {
                    System.out.println(diaryEntryEntity);

                }
                String diaryTitle = scanner.nextLine();
                DiaryEntryEntity diaryChangeContent = session.createQuery("FROM DiaryEntryEntity AS h WHERE h.title = :title ", DiaryEntryEntity.class)
                        .setParameter("title", diaryTitle)
                        .getSingleResult();

                String textChange = scanner.nextLine();
                diaryChangeContent.setContent(textChange);
            }



            if (wyborOpcji == 4) {
                System.out.println("Podaj tytul, by usunąć wpis");
                System.out.println();
                for (DiaryEntryEntity diaryEntryEntity : diaryUser.getDiaryEntry()) {
                    System.out.println(diaryEntryEntity);
                }
                System.out.println("JESTES ZALOGOWANY JAKO: id: " + diaryUser.getId() + " name: " + diaryUser.getLogin());
                System.out.println();
                String nameTheTitleScanner = scanner.nextLine();

//                Husband husband1 = session.find(Husband.class, 3L);
//                husband1.setWife(null);
//                delete from User where name=:name


                DiaryEntryEntity diaryDelete = session.createQuery("FROM DiaryEntryEntity AS h WHERE h.title = :title ", DiaryEntryEntity.class)
                        .setParameter("title", nameTheTitleScanner)
                        .getSingleResult();
                System.out.println("WYNIK KOŃCOWY: " + diaryDelete.getTitle() + " A TU " + diaryDelete.getId());

//                System.out.println(diaryDelete);
//                diaryUser.diaryEntry.remove(diaryDelete);
//                diaryDelete.setDiaryUser(null);
                diaryDelete.setDiaryUser(null);
//                diaryUser.getDiaryEntry().remove(diaryDelete);
//                boolean remove = diaryUser.getDiaryEntry().remove(diaryDelete);
//                System.out.println(remove);
//                session.delete(diaryDelete);


//                USUWA WSZYSTKO :(
//                DiaryEntryEntity diaryEntryEntityDELETE = session.find(DiaryEntryEntity.class, diaryDelete.getId());
//                if( diaryEntryEntityDELETE != null){
//                    session.delete(diaryEntryEntityDELETE);
//                }

            }

            if (wyborOpcji == 5) {
                return;
            }
        } catch (NoResultException e) {
            System.out.println("Zły Login lub Hasło!");
        }

        transaction.commit();
        session.close();
    }

    public static void opcjaZMenu(int podajLiczbe) {

    }
}
