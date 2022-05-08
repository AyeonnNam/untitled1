package jpabook_jpashop.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {


    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String name;


    @Embedded
    private Period workperiod;


    @Embedded
    private Address homeAddress;

    //값 타입 컬렉션
    @ElementCollection
    @CollectionTable(name="FAVORITE_FOODS",
            joinColumns =  @JoinColumn(name="MEMBER_ID"))
    @Column(name="FOOD_NAME") //예외적으로 가능
    private Set<String> favoriteFoods = new HashSet<>();



    //값 타입 컬렉션
    @ElementCollection
    @CollectionTable(name="ADDRESS",
            joinColumns = @JoinColumn(name="MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name= "WORK_CITY")),
            @AttributeOverride(name="street",
                    column=@Column(name= "WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name = "WOKR_ZIPCODE"))
    })
    private Address workAddress;

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    public Period getWorkperiod() {
        return workperiod;
    }

    public void setWorkperiod(Period workperiod) {
        this.workperiod = workperiod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }


    private void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    private void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
