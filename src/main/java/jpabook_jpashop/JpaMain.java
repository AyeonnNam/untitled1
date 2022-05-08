package jpabook_jpashop;

import jpabook_jpashop.domain.Address;
import jpabook_jpashop.domain.Member;
import jpabook_jpashop.domain.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static org.jboss.logging.NDC.clear;

public class JpaMain {

    public static void main(String[] args) {




        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hi");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

//            Member member = new Member();
//            member.setName("남아연");
//            member.setHomeAddress(new Address("city","street","10000"));
//
//            LocalDate startDate = LocalDate.of(2018,12,30);
//
//            LocalDate endDate = LocalDate.of(2022,12,30);
//
//            member.setWorkperiod(new Period(startDate, endDate));
//
//            Address address = new Address("seoul", "gangnam", "2000");
//
//            member.setWorkAddress(new Address("mokpo", address.getStreet(), address.getZipcode()));
//
//            em.persist(member);
//
//

            /*embedded타입을 여러 엔티티에서 공유하면 위험함*/
//
//            Member member1 = new Member();
//            member1.setName("남아연");
//
//
//            //member1의 주소만 바꾸려면?
//
//
//            Address address = new Address("seoul", "yangcheon", "28100");
//
//            //값타입을 복사해서 사용하자!
//            Address address1 = new Address("mokpo", "sangdong", "68590");
//            member1.setHomeAddress(address1);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setName("문지수");
//            member2.setHomeAddress(address);
//            em.persist(member2);
//
//            //member1만의 주소를 바꾸고 싶다!
//            member1.getHomeAddress().setZipcode("800000");
//            member1.getHomeAddress().setStreet("sangdong");
//            member1.getHomeAddress().setCity("mokpo");

            //라이브 코딩

//            Address address = new Address("city", "street", "10000");
//            Member member = new Member();
//            member.setName("hi");
//            //member.setHomeAddress(address);
//            em.persist(member);
//
//            //대신 인스턴스를 복사해서 사용한다.
//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//
//            Member member2 = new Member();
//            member2.setName("hello");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);
//
//            //member.getHomeAddress().setCity("daegu");
//
//            //실제 값을 바꾸고 싶다면
//            Address addressNew = new Address(address.getCity(), "nonhyen",address.getZipcode());
//            member.setHomeAddress(addressNew);

            //불변이라는 작은 제약으로 부작용이라는 큰 재앙을 막을 수 있다, 추적하기 어려운 버그

            //객체 타입의 한계
            /*  항상 값을 복사해서 사용하면 공유 참조로 인해 발생하는 부작용을 피할 수 있다.
                문제는 임베디드 타입처럼 직접 정의한 값 타입은 자바의 기본 타입이 아니라 객체 타입이다.
                자바 기본타입에 값을 대입하면 값을 복사한다.
                객체 타입은 참조 값을 직접 대입하는 것을 막을 방법이 없다.
                객체의 공유 참조는 피할 수 없다.
            * */

            /*  불변 객체
            *   객체 타입을 수정할 수 없게 만들면 부작용을 원천 차단 가능
            *   값 타입은 불변 객체(immutable object)로 설계하기
            *   불변 객체: 생성 시점 이후 절대 값을 변경할 수 없는 객체
            *   생성자로만 값을 설정하고 수정자(Setter)을 만들지 않기/ 혹은 private으로 돌리기
            *   Integer, String은 자바가 제공하는 대표적인 불변 객체임.
            * */


            //값 타입의 비교
            /*  값 타입: 인스턴스가 달라도 그 안에 값이 같으면 같은 것으로 봐야 함
            *   int a = 10;
            *   int b = 10;
            *
            *   Address a = new Address("서울시")
            *   Address b = new Address("서울시")
             *
             * */


                //값타입 비교//
//            int a = 10;
//            int b = 10;
//            System.out.println("a == b : " + (a==b));
//
//            Address address1 = new Address("서울시","강남구","20000");
//            Address address2 = new Address("서울시","강남구","20000");
//
//            //동일성 비교 (인스턴스 참조 값 비교)
//            System.out.println("address1 == address2 : " + (address1 == address2));
//            //false
//
//            //동등성 비교 (인스턴스 값 비교)
//            //값 타입의 equals() 메소드를 적절하게 재정의 (주로 모든 필드 사용,재정의)
//            System.out.println("address1 == address2 : " + (address1.equals(address2)));

            //자바 == 비교는 reference(참조값)을 비교, instance가 다르기 때문

            /* 동일성(identity) 비교: 인스턴스의 참조 값을 비교, == 사용
            *  동등성(equivalence) 비교: 인스턴스의 값을 비교, equals() 사용
            *  값 타입은 a.equals(b)를 사용해서 동등성 비교를 해야 함
            *  값 타입의 equals() 메소드를 적절하게 재정의 (주로 모든 필드 사용,재정의)
            * */

            //값 타입 컬렉션 사용 -저장-
            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(new Address("CITY","STREET","10000"));


            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("튀김만두");
            member.getFavoriteFoods().add("볶음밥");

            member.getAddressHistory().add(new Address("OLD1","STREET","10000"));
            member.getAddressHistory().add(new Address("OLD2","STREET","10000"));

            em.persist(member);

            //값 타입 컬렉션은 영속성 전이(CASCADE) + 고아 객체 제거 기능을 필수로 가진다고 볼 수 있다.



            em.flush();
            em.clear();

            System.out.println("==============START=================");
            Member findMember = em.find(Member.class, member.getId());

            //조회

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("addressHistory: " + address.getCity()+", " + address.getStreet()+", " +address.getZipcode());
//            }
//
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }


            //값 타입 수정
            //새로운 인스턴스로 통으로 교체
            Address homeAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("BUSAN", homeAddress.getStreet(), homeAddress.getZipcode()));

            //값 타입 컬렉션 수정
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            favoriteFoods.remove("치킨");
            favoriteFoods.add("한식");

            //equals가 재정의가 잘 되어야하는 이유


            System.out.println("==============START=================");
            findMember.getAddressHistory().remove(new Address("OLD1","STREET","10000"));
            findMember.getAddressHistory().add(new Address("newCity1","STREET","10000"));





            tx.commit();
        }catch(Exception e ){
            tx.rollback();

        }finally {
            em.close();
        }emf.close();
    }



}
